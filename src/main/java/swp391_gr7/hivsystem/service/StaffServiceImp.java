package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndStaffCreateRequest;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Staffs;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.ManagerRepository;
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
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Staffs saveStaff(UserAndStaffCreateRequest request, Users users) {
        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            return null; // Manager not found
        }
        Staffs staffs = new Staffs();
        staffs.setUsers(users);
        staffs.setDepartment(request.getDepartment());
        staffs.setWorkShift(request.getWorkShift());
        staffs.setAssignedArea(request.getAssignedArea());
        staffs.setManagers(manager);
       // staff.setAssignedModule(request.getAssignedModule());
        return staffRepository.save(staffs);

    }

    // Tim Staff co lich thap nhat
    @Override
    public Staffs findStaffHasLeastAppointment() {
        List<Staffs> staffs = staffRepository.findAllStaff();
        if (staffs.isEmpty()) {
            return null;
        }

        Staffs minStaffs = staffs.get(0);
        int minAppointments = minStaffs.getAppointments().size();

        for (int i = 1; i < staffs.size(); i++) {
            Staffs current = staffs.get(i);
            int currentAppointments = current.getAppointments().size();

            if (currentAppointments < minAppointments) {
                minStaffs = current;
                minAppointments = currentAppointments;

                if (minAppointments == 0) {
                    break; // không cần tìm tiếp
                }
            }
        }

        return minStaffs;
    }

}
