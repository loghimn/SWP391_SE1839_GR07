package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndStaffCreateRequest;
import swp391_gr7.hivsystem.model.Staff;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.StaffRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.util.List;

@Service
public class StaffServiceImp implements StaffService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Staff saveStaff(UserAndStaffCreateRequest request, User user) {
        Staff staff = new Staff();
        staff.setUser(user);
        staff.setDepartment(request.getDepartment());
        staff.setWorkShift(request.getWorkShift());
       // staff.setAssignedModule(request.getAssignedModule());
        return staffRepository.save(staff);

    }

    // Tim Staff co lich thap nhat
    @Override
    public Staff findStaffHasLeastAppointment() {
        List<Staff> staffs = staffRepository.findAllStaff();
        if (staffs.isEmpty()) {
            return null;
        }

        Staff minStaff = staffs.get(0);
        int minAppointments = minStaff.getAppointments().size();

        for (int i = 1; i < staffs.size(); i++) {
            Staff current = staffs.get(i);
            int currentAppointments = current.getAppointments().size();

            if (currentAppointments < minAppointments) {
                minStaff = current;
                minAppointments = currentAppointments;

                if (minAppointments == 0) {
                    break; // không cần tìm tiếp
                }
            }
        }

        return minStaff;
    }

}
