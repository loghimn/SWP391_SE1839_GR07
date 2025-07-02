package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UpdatePasswordRequest;
import swp391_gr7.hivsystem.dto.request.UserAndStaffCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndStaffUpdateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Staffs;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.StaffRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.util.ArrayList;
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
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }
        Staffs staffs = new Staffs();
        staffs.setUsers(users);
        staffs.setDepartment(request.getDepartment());
        staffs.setWorkShift(request.getWorkShift());
        staffs.setAssignedArea(request.getAssignedArea());
        staffs.setManagers(manager);
        return staffRepository.save(staffs);

    }

    // Tim Staff co lich thap nhat
    @Override
    public Staffs findStaffHasLeastAppointment() {
        List<Staffs> staffs = staffRepository.findAllStaff();
        if (staffs.isEmpty()) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
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

    @Override
    public Staffs updateStaff(UserAndStaffUpdateRequest request, Users users) {
        Staffs staff = staffRepository.findStaffByUser_UserId(users.getUserId().toString()).get();
        if (staff == null) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }
        staff.setUsers(users);
        staff.setDepartment(request.getDepartment());
        staff.setWorkShift(request.getWorkShift());
        staff.setAssignedArea(request.getAssignedArea());
        return staffRepository.save(staff);
    }

    @Override
    public Staffs getStaffById(int id) {
        return staffRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));
    }

    @Override
    public List<Staffs> getAllStaffs() {
        Iterable<Staffs> iterable = staffRepository.findAll(); // iterable là một đối tượng Iterable chứa tất cả các Staffs
        if(!iterable.iterator().hasNext()) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }
        List<Staffs> list = new ArrayList<>();  // iterable là kiểu dữ liệu là cha của mọi kiểu collection như List, Set, Queue..
        iterable.forEach(list::add);
        return list;
    }
    @Override
    public boolean updatePasswordStaff(int staffId, UpdatePasswordRequest request) {
        Staffs staff = staffRepository.findById(staffId).orElse(null);
        if (staff == null) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }
        Users user = staff.getUsers();
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.USER_INVALID_OLD_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        userRepository.save(staff.getUsers());
        return true;
    }
}
