package swp391_gr7.hivsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
        import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.request.*;
        import swp391_gr7.hivsystem.model.Appointment;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.service.AppointmentService;
import swp391_gr7.hivsystem.service.UserService;

import java.util.List;


@RestController
//CRUD
@RequestMapping("/user/customer")
public class CustommerController {
    @Autowired
    public AppointmentService appointmentService;

    @PostMapping("/appoint/register")
    public ApiReponse<Boolean> appointmentRequest(@RequestBody AppointmentCreateRequest request) {
        Appointment appointment = appointmentService.addAppointment(request);// gọi từ service
        boolean result = appointment != null;
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }
    @GetMapping("/appoint/list")
    public ApiReponse<List> appointmentList() {
        List <Appointment> appointmentList = appointmentService.getAllAppointmentsFullInfor();// gọi từ service
        boolean result = appointmentList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiReponse.<List>builder()
                .result(appointmentList)
                .message(resultString)
                .build();
    }
    @GetMapping("/appoint/list/notanonymus")
    public ApiReponse<List> appointmentListEcceptAnonymous() {
        List <Appointment> appointmentList = appointmentService.getAllAppointmentsEcceptAnonymous();// gọi từ service
        boolean result = appointmentList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiReponse.<List>builder()
                .result(appointmentList)
                .message(resultString)
                .build();
    }
}
