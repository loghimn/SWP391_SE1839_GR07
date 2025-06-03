package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndStaffCreateRequest;
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

    @Override
    public Staff saveStaff(UserAndStaffCreateRequest request, User user) {
        Staff staff = new Staff();
        staff.setUser(user);
        staff.setDepartment(request.getDepartment());
        staff.setWorkShift(request.getWorkShift());
        staff.setAssignedModule(request.getAssignedModule());
        return staffRepository.save(staff);

    }

}
