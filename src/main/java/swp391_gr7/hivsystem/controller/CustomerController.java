package swp391_gr7.hivsystem.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.request.UserAndCustomerUpdateRequest;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.service.CustomerService;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.UserService;

@RestController
@RequestMapping("/api/customer")
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('Customer')")
    @PostMapping("/update")
    public ApiResponse<Boolean> updateCustomer(@RequestBody @Valid UserAndCustomerUpdateRequest request,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractCustomerId(token);

        boolean result = userService.updateUserAndCustomer(customerId, request);
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message(result ? "Update successful" : "Update failed")
                .build();
    }

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/customer-info")
    @PostAuthorize("returnObject.users.username == authentication.name")
    // Only the user can access their own information
    public Customers getCusInfo() {
        Customers user = customerService.getMyCustomerInfo();
        if (user == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return user;
    }


}
