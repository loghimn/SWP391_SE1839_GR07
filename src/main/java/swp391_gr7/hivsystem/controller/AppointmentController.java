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
import swp391_gr7.hivsystem.dto.response.CustomerReponse;
import swp391_gr7.hivsystem.dto.response.MeetingLinkResponse;
import swp391_gr7.hivsystem.model.Appointments;
import swp391_gr7.hivsystem.service.AppointmentService;
import swp391_gr7.hivsystem.service.JWTUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
//CRUD
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class AppointmentController {
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
    @PostMapping("/appointment/book")
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

    @PreAuthorize("hasAnyRole('Doctor', 'Staff')")
    @GetMapping("/appointment/anonymous/list")
    public ApiResponse<List> appointmentList() {
        List<Appointments> appointmentsList = appointmentService.getAllAppointmentsAnonymous();// gọi từ service
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasAnyRole('Doctor' , 'Staff')")
    @GetMapping("/appointment/ecceptAnonymous/list")
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
    @GetMapping("/appointment/fullInfor")
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
    @GetMapping("/appointment/{appointmentid}")
    public ApiResponse<Appointments> getAppointmentById(@PathVariable int appointmentid) {
        Appointments appointment = appointmentService.getAppointmentById(appointmentid);
        boolean result = appointment != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<Appointments>builder()
                .result(appointment)
                .message(resultString)
                .build();
    }

    // Changed to DELETE mapping and unique path
    @PreAuthorize("hasRole('Doctor')")
    @DeleteMapping("/appointment/delete/{appointmentid}")
    public ApiResponse<Appointments> deleteAppointment(@PathVariable int appointmentid) {
        Appointments appointment = appointmentService.deleteAppointment(appointmentid);
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
    @GetMapping("/appointment/get/{customerid}")
    public ApiResponse<List<Appointments>> getAppointmentByCustomerId(@PathVariable int customerid) {
        List<Appointments> appointments = appointmentService.getAppointmentsByCustomerId(customerid);
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
    @GetMapping("/customer/appointment/get/my-appointment")
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
    @PutMapping("/appointment/update/{appointmentid}")
    public ApiResponse<Appointments> updateAppointment(@PathVariable int appointmentid, @RequestBody @Valid AppointmentCreateRequest request) {
        Appointments updatedAppointment = appointmentService.updateAppointment(appointmentid, request);
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

    @PreAuthorize("hasAnyRole('Doctor' , 'Staff')")
    @GetMapping("/customer/appointment/doctorview/{appointmentid}")
    public ApiResponse<CustomerReponse> getCustomerAppointmentInDoctorView(@PathVariable int appointmentid) {
        CustomerReponse customerReponse = appointmentService.getCustomerAppointmentInDocorView(appointmentid);
        if (customerReponse == null) {
            return ApiResponse.<CustomerReponse>builder()
                    .message("Bạn không có quyền xem thông tin")
                    .build();
        }
        return ApiResponse.<CustomerReponse>builder()
                .result(customerReponse)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/appointment/get/my-appointment")
    public ApiResponse<List<Appointments>> getMyAppointmentsDoc(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        List<Appointments> appointments = appointmentService.getMyAppointmentsDoc(doctorId);
        if (appointments == null || appointments.isEmpty()) {
            return ApiResponse.<List<Appointments>>builder()
                    .message("Hiện tại, bác sĩ không có lịch hẹn nào")
                    .build();
        }
        return ApiResponse.<List<Appointments>>builder()
                .result(appointments)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/appointment-consultation/get/my-appointment")
    public ApiResponse<List<Appointments>> getMyAppointmentsConsultationDoc(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        List<Appointments> appointments = appointmentService.getMyAppointmentsConsultationDoc(doctorId);
        if (appointments == null || appointments.isEmpty()) {
            return ApiResponse.<List<Appointments>>builder()
                    .message("Hiện tại, bác sĩ không có lịch hẹn nào")
                    .build();
        }
        return ApiResponse.<List<Appointments>>builder()
                .result(appointments)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/appointment-testhiv/get/my-appointment")
    public ApiResponse<List<Appointments>> getMyAppointmentsTestHIVDoc(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        List<Appointments> appointments = appointmentService.getMyAppointmentsTestHIVDoc(doctorId);
        if (appointments == null || appointments.isEmpty()) {
            return ApiResponse.<List<Appointments>>builder()
                    .message("Hiện tại, bác sĩ không có lịch hẹn nào")
                    .build();
        }
        return ApiResponse.<List<Appointments>>builder()
                .result(appointments)
                .message("Success")
                .build();
    }


    @PreAuthorize("hasRole('Staff')")
    @GetMapping("/staff/appointment/list")
    public ApiResponse<List> getappointmentListFullInfor() {
        List<Appointments> appointmentsList = appointmentService.getAllAppointmentsFullInfor();// gọi từ service
        boolean result = appointmentsList != null;
        String resultString = result ? "Thành Công" : "Thất Bại";
        return ApiResponse.<List>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/appointment/testhiv/list")
    public ApiResponse<List<Appointments>> getAppointmentsHaveTypeTestHIV() {
        List<Appointments> appointmentsList = appointmentService.getAppointmentsHaveTypeTestHIVAndActive();
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List<Appointments>>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/appointment/consultation/list")
    public ApiResponse<List<Appointments>> getAppointmentsHaveTypeConsultation() {
        List<Appointments> appointmentsList = appointmentService.getAppointmentsHaveTypeConsultationAndActive();
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List<Appointments>>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }

    @PreAuthorize("hasRole('Staff')")
    @GetMapping("/staff/appointment/testhiv/list")
    public ApiResponse<List<Appointments>> getStaffsAppointmentsHaveTypeTestHIV() {
        List<Appointments> appointmentsList = appointmentService.getStaffAppointmentsHaveTypeTestHIVAndActive();
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List<Appointments>>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }


    // get appointments by day for a specific doctor
    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/doctor/appointment/{day}")
    public ApiResponse<List<Appointments>> getAppointmentsByDay(@PathVariable LocalDate day) {
        List<Appointments> appointmentsList = appointmentService.getAppointmentsByDay(day);
        boolean result = appointmentsList != null;
        String resultString = result ? "Success" : "Failed";
        return ApiResponse.<List<Appointments>>builder()
                .result(appointmentsList)
                .message(resultString)
                .build();
    }


    @GetMapping("/doctor/appointment/{appointmentId}/meeting")
    @PreAuthorize("hasRole('Doctor')")
    public ApiResponse<MeetingLinkResponse> createAppointmentMeeting(@PathVariable int appointmentId) {
        // Verify appointment exists
        Appointments appointment = appointmentService.getAppointmentByIdIgnoreAnonymous(appointmentId);

        if (appointment == null) {
            return ApiResponse.<MeetingLinkResponse>builder()
                    .message("Appointment not found")
                    .build();
        }

        // Generate meeting ID using appointment ID
        String meetingId = "hiv-" + appointment.getDoctors().getUsers().getFullName() + "-" + appointmentId;
        String baseLink = "https://meet.jit.si/" + meetingId;
        appointment.setUrlMeeting(baseLink);
        appointmentService.saveAppointment(appointment);
        // Doctor link with host privileges
        String meetingLink = baseLink + "#" + String.join("&",
                "config.prejoinPageEnabled=false",
                "userType=host",
                "interfaceConfig.TOOLBAR_BUTTONS=[\"microphone\",\"camera\",\"closedcaptions\",\"desktop\",\"fullscreen\",\"recording\",\"settings\",\"raisehand\",\"videoquality\",\"filmstrip\",\"chat\",\"tileview\"]"
        );

        MeetingLinkResponse response = MeetingLinkResponse.builder()
                .meetingLink(meetingLink)
                .build();

        return ApiResponse.<MeetingLinkResponse>builder()
                .result(response)
                .message("Tạo Meeting Link thành công")
                .build();
    }

    @GetMapping("/customer/appointment/{appointmentId}/meeting")
    @PreAuthorize("hasRole('Customer')")
    public ApiResponse<MeetingLinkResponse> getCustomerMeetingLink(@PathVariable int appointmentId) {
        // Verify appointment exists
        Appointments appointment = appointmentService.getAppointmentByIdIgnoreAnonymous(appointmentId);
        if (appointment == null) {
            return ApiResponse.<MeetingLinkResponse>builder()
                    .message("Appointment not found")
                    .build();
        }

        // Generate meeting ID using appointment ID
        String meetingId = "hiv-" + appointment.getDoctors().getUsers().getFullName() + "-" + appointmentId;
        String baseLink = "https://meet.jit.si/" + meetingId;

        appointment.setUrlMeeting(baseLink);
        appointmentService.saveAppointment(appointment);
        // Patient link with limited privileges
        String meetingLink = baseLink + "#" + String.join("&",
                "config.prejoinPageEnabled=true",
                "userType=participant",
                "interfaceConfig.TOOLBAR_BUTTONS=[\"microphone\",\"camera\",\"raisehand\",\"chat\",\"tileview\"]"
        );

        MeetingLinkResponse response = MeetingLinkResponse.builder()
                .meetingLink(meetingLink)
                .build();

        return ApiResponse.<MeetingLinkResponse>builder()
                .result(response)
                .message("Tạo Meeting Link thành công")
                .build();
    }


}
