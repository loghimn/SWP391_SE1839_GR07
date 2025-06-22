package swp391_gr7.hivsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import swp391_gr7.hivsystem.dto.response.ApiResponse;

@ControllerAdvice // nơi xử lý các ngoại lệ toàn cục
public class GlobalExceptionHandling {

    @ExceptionHandler(value = Exception.class) // Xử lý ngoại lệ chung RuntimeException
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ErrorCode errorCode = ErrorCode.UNKNOWN_ERROR; // Mã lỗi mặc định
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNKNOWN_ERROR.getCode());
        apiResponse.setMessage(ErrorCode.UNKNOWN_ERROR.getMessage());
        apiResponse.setResult("Fail");

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class) // Xử lý ngoại lệ tùy chỉnh AppException
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setResult("Fail");
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.ACCESS_DENIED; // Mã lỗi truy cập bị từ chối
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .result("Fail")
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // Xử lý ngoại lệ khi có lỗi xác thực dữ liệu
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        // Sai key thì in ra WRONG_KEY
        ErrorCode errorCode = ErrorCode.WRONG_KEY; // Default error code

        try{
            // Chuyển đổi enum key sang ErrorCode
            // Nếu không tìm thấy thì sẽ ném ra IllegalArgumentException
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ignored){}

        // Tạo ApiResponse với mã lỗi và thông điệp tương ứng
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setResult("Fail");
        return ResponseEntity.badRequest().body(apiResponse);
    }

}
