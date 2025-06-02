package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.roleDto.StaffDto;
import swp391_gr7.hivsystem.message.registerMessage;
import swp391_gr7.hivsystem.model.Staff;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.StaffRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

@Service
public class StaffServiceImp implements StaffService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  StaffRepository staffRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    public registerMessage save(StaffDto staffDto) {
        User existingEmail = userRepository.findByEmail(staffDto.getEmail());
        User existingPhone = userRepository.findByPhone(staffDto.getPhone());

        if (existingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        }

        User user = new User();
        user.setUsername(staffDto.getUsername());
        user.setPassword(passwordEncoder.encode(staffDto.getPassword()));
        user.setEmail(staffDto.getEmail());
        user.setPhone(staffDto.getPhone());
        user.setFullName(staffDto.getFullName());
        user.setDateOfBirth(staffDto.getDateOfBirth());
        user.setGender(staffDto.getGender());
        user.setRole(staffDto.getRole());

        userRepository.save(user);

        if ("staff".equalsIgnoreCase(staffDto.getRole())) {
            Staff staff = new Staff();
            staff.setUser(user);
            staff.setDepartment(staffDto.getDepartment());
            staff.setWorkShift(staffDto.getWorkShift());
            staff.setAssignedModule(staffDto.getAssignedModule());
            staffRepository.save(staff);

            return new registerMessage("Staff saved successfully", true);
        }

        return new registerMessage("User is not a staff. Only staff can be saved.", false);
    }
}
