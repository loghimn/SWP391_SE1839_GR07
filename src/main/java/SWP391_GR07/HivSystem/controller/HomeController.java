package SWP391_GR07.HivSystem.controller;

import SWP391_GR07.HivSystem.dto.CustomerDto;
import SWP391_GR07.HivSystem.dto.DoctorDto;
import SWP391_GR07.HivSystem.dto.ManagerDto;
import SWP391_GR07.HivSystem.dto.StaffDto;
import SWP391_GR07.HivSystem.message.registerMessage;
import SWP391_GR07.HivSystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-customer")
    public ResponseEntity<registerMessage> register(@RequestBody CustomerDto customerDto) {
        registerMessage result = userService.save(customerDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register-staff")
    public ResponseEntity<registerMessage> registerStaff(@RequestBody StaffDto staffDto) {
        registerMessage result = userService.save(staffDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register-manager")
    public ResponseEntity<registerMessage> registerManager(@RequestBody ManagerDto managerDto) {
        registerMessage result = userService.save(managerDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register-doctor")
    public ResponseEntity<registerMessage> registerDoctor(@RequestBody DoctorDto doctorDto) {
        registerMessage result = userService.save(doctorDto);
        return ResponseEntity.ok(result);
    }

}
