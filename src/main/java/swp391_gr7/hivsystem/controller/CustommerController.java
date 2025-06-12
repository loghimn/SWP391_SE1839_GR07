package swp391_gr7.hivsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
        import swp391_gr7.hivsystem.dto.reponse.ApiResponse;
import swp391_gr7.hivsystem.dto.request.*;
        import swp391_gr7.hivsystem.model.Appointments;
import swp391_gr7.hivsystem.service.AppointmentService;
import swp391_gr7.hivsystem.service.AppointmentServiceImp;

import java.util.List;


@RestController
//CRUD
@RequestMapping("/api/user/customer")
public class CustommerController {
    @Autowired
    public AppointmentService appointmentService;

    @PostMapping("/appoint/book")
    public ApiResponse<Boolean> appointmentRequest(@RequestBody AppointmentCreateRequest request) {
        Appointments appointments = appointmentService.addAppointment(request);// gọi từ service
        boolean result = appointments != null;

        if(appointments == null){
            AppointmentServiceImp imp = new AppointmentServiceImp();
            if(imp.error != null){
                return  ApiResponse.<Boolean>builder()
                        .result(result)
                        .message(imp.error)
                        .build();
            }
        }
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }
    @GetMapping("/appoint/anonymous")
    public ApiResponse<List> appointmentList() {
        List <Appointments> appointmentsList = appointmentService.getAllAppointmentsFullInfor();// gọi từ service
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }
    @GetMapping("/appoint")
    public ApiResponse<List> appointmentListEcceptAnonymous() {
        List <Appointments> appointmentsList = appointmentService.getAllAppointmentsEcceptAnonymous();// gọi từ service
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }
}
