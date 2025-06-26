package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.*;
import swp391_gr7.hivsystem.model.Appointments;
import swp391_gr7.hivsystem.service.AppointmentService;
import swp391_gr7.hivsystem.service.JWTUtils;

import java.util.List;


@RestController
//CRUD
@RequestMapping("/api/user/customer")
@SecurityRequirement(name = "bearerAuth")
public class CustommerController {
    @Autowired
    public AppointmentService appointmentService;

//    @PostMapping("/appoint/book")
//    public ApiResponse<Boolean> appointmentRequest(@RequestBody AppointmentCreateRequest request) {
//        Appointments appointments = appointmentService.addAppointment(request);// gọi từ service
//        boolean result = appointments != null;
//
//        if(appointments == null){
//            AppointmentServiceImp imp = new AppointmentServiceImp();
//            if(imp.error != null){
//                return  ApiResponse.<Boolean>builder()
//                        .result(result)
//                        .message(imp.error)
//                        .build();
//            }
//        }
//        return ApiResponse.<Boolean>builder()
//                .result(result)
//                .message("Success")
//                .build();
//    }


    @PreAuthorize("hasRole('Customer')")
    @PostMapping("/appoint/book")
    public ApiResponse<Boolean> appointmentRequest(@RequestBody @Valid AppointmentCreateRequest request,
                                                   @RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int customerId = new JWTUtils().extractCustomerId(token);

        Appointments appointments = appointmentService.addAppointment(customerId, request);
        boolean result = appointments != null;
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/appoint/anonymous")
    public ApiResponse<List> appointmentList() {
        List<Appointments> appointmentsList = appointmentService.getAllAppointmentsAnonymous();// gọi từ service
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/appoint/ecceptAnonymous")
    public ApiResponse<List> appointmentListEcceptAnonymous() {
        List<Appointments> appointmentsList = appointmentService.getAllAppointmentsEcceptAnonymous();// gọi từ service
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/appoint/fullInfor")
    public ApiResponse<List> appointmentListFullInfor() {
        List<Appointments> appointmentsList = appointmentService.getAllAppointmentsFullInfor();// gọi từ service
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/appoint/{id}")
    public ApiResponse<Appointments> getAppointmentById(@PathVariable int id) {
        Appointments appointment = appointmentService.getAppointmentById(id);
        boolean result = appointment != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<Appointments>builder()
                .result(appointment)
                .message(resultString)
                .build();
    }

    // Changed to DELETE mapping and unique path
    @PreAuthorize("hasRole('Doctor')")
    @DeleteMapping("/appoint/delete/{id}")
    public ApiResponse<Appointments> deleteAppointment(@PathVariable int id) {
        Appointments appointment = appointmentService.deleteAppointment(id);
        if (appointment == null) {
            return ApiResponse.<Appointments>builder()
                    .message("Failed to delete appointment")
                    .build();
        }
        return ApiResponse.<Appointments>builder()
                .result(appointment)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/appoint/getAppointmentByCustomerId/{id}")
    public ApiResponse<List<Appointments>> getAppointmentByCustomerId(@PathVariable int id) {
        List<Appointments> appointments = appointmentService.getAppointmentsByCustomerId(id);
        if (appointments == null || appointments.isEmpty()) {
            return ApiResponse.<List<Appointments>>builder()
                    .message("No appointments found for this customer")
                    .build();
        }
        return ApiResponse.<List<Appointments>>builder()
                .result(appointments)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Customer')")
    @GetMapping("/appoint/getCustomerAppointment")
    public ApiResponse<List<Appointments>> getCustomerAppointment(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract customerId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int currentCustomerId = new JWTUtils().extractCustomerId(token);

        List<Appointments> appointments = appointmentService.getCustomerAppointment(currentCustomerId);
        if (appointments == null || appointments.isEmpty()) {
            return ApiResponse.<List<Appointments>>builder()
                    .message("No appointments found for this customer")
                    .build();
        }
        return ApiResponse.<List<Appointments>>builder()
                .result(appointments)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Customer')")
    @PutMapping("/appoint/update/{id}")
    public ApiResponse<Appointments> updateAppointment(@PathVariable int id, @RequestBody @Valid AppointmentCreateRequest request) {
        Appointments updatedAppointment = appointmentService.updateAppointment(id, request);
        if (updatedAppointment == null) {
            return ApiResponse.<Appointments>builder()
                    .message("Failed to update appointment")
                    .build();
        }
        return ApiResponse.<Appointments>builder()
                .result(updatedAppointment)
                .message("Success")
                .build();
    }
}
