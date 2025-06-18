package swp391_gr7.hivsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Unknown error
    UNKNOWN_ERROR(9999, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR), // 500

    // Wrong key
    WRONG_KEY(900, "Wrong key provided", HttpStatus.BAD_REQUEST), // 400

    // Common errors
    UNAUTHENTICATED(989, "You are not authenticated", HttpStatus.UNAUTHORIZED), // 401
    USER_NOT_FOUND(990, "User not found", HttpStatus.NOT_FOUND), // 404
    ACCESS_DENIED(999, "You do not have permission to access", HttpStatus.FORBIDDEN), // 403
    WRONG_USERNAME_PASSWORD(1000, "Wrong username or password", HttpStatus.UNAUTHORIZED), // 401
    USER_EXIST_USERNAME(1001, "Username already exists", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST_PHONE(1002, "Phone number already exists", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST_EMAIL(1003, "Email already exists", HttpStatus.BAD_REQUEST), // 400

    // Add error for username
    USER_INVALID_USERNAME_SIZE(1004, "Username must be at least 5 characters", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_USERNAME_NOTBLANK(1005, "Username is required", HttpStatus.BAD_REQUEST), // 400

    // Add error for password
    USER_INVALID_PASSWORD_SIZE(1006, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PASSWORD_NOTBLANK(1007, "Password is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PASSWORD_FORMAT(1008, "Password must contain at least one lowercase letter, one digit, and one special character", HttpStatus.BAD_REQUEST), // 400

    // Add error for email
    USER_INVALID_EMAIL_NOTBLANK(1009, "Email is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_EMAIL_FORMAT(1010, "Email must be in a valid format and belong to gmail or example domain ending with com, vn, org", HttpStatus.BAD_REQUEST), // 400

    // Add error for phone
    USER_INVALID_PHONE_NOTBLANK(1011, "Phone number is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PHONE_SIZE(1012, "Phone number must be exactly 10 digits", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PHONE_FORMAT(1013, "Phone number must start with 0 and be followed by 9 digits", HttpStatus.BAD_REQUEST), // 400

    // Add error for full name
    USER_INVALID_FULLNAME_NOTBLANK(1014, "Full Name is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_FULLNAME_SIZE(1015, "Full Name must be at least 6 characters", HttpStatus.BAD_REQUEST), // 400

    // Add error for gender
    USER_INVALID_GENDER_NOTBLANK(1016, "Gender is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_GENDER_FORMAT(1017, "Gender is male or female", HttpStatus.BAD_REQUEST), // 400

    // Add error for date of birth
    USER_DATEOFBIRTH_NOTNULL(1018, "Date of Birth is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_DATEOFBIRTH(1019, "Date of Birth must be at least 18", HttpStatus.BAD_REQUEST), // 400

    // Add error for customer
    CUSTOMER_INVALID_ADDRESS_NOTBLANK(1020, "Address is required", HttpStatus.BAD_REQUEST), // 400
    CUSTOMER_INVALID_ADDRESS(1021, "Address must be the name of a province/city in Vietnam", HttpStatus.BAD_REQUEST), // 400

    // Add error for staff
    STAFF_INVALID_DEPARTMENT_NOTBLANK(1022, "Department is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_WORKSHIFT_NOTNULL(1023, "Work shift is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_WORKSHIFT(1024, "Work shift include 1, 2, 3", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_ASSIGNED_AREA_NOTBLANK(1025, "Assigned area is required", HttpStatus.BAD_REQUEST), // 400

    // Add error for manager
    MANAGER_INVALID_DEPARTMENT_NOTBLANK(1026, "Department is required", HttpStatus.BAD_REQUEST), // 400
    MANAGER_INVALID_OFFICE_PHONE_NOTBLANK(1027, "Office phone is required", HttpStatus.BAD_REQUEST), // 400
    MANAGER_INVALID_OFFICE_PHONE_FORMAT(1028, "Office phone must start with 0 and be followed by 9 digits", HttpStatus.BAD_REQUEST), // 400

    // Add error for doctor
    DOCTOR_INVALID_DEPARTMENT_NOTBLANK(1029, "Department is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_YEAR_EXPERIENCE_NOTNULL(1030, "Year experience is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_YEAR_EXPERIENCE(1032, "Years of experience must be at least 0", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_NOTBLANK(1033, "License number is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_FORMAT(1034, "License number must follow the format DC-{4 number}", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_EXIST(1035, "License number already exists", HttpStatus.BAD_REQUEST), // 400

    // Add error for blog
    BLOG_DOCTOR_NOT_FOUND(1035, "Doctor not found with mail", HttpStatus.NOT_FOUND), // 404
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
