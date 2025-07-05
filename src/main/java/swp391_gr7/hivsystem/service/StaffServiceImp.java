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
    public Staffs findStaffHasLeastAppointment(int workShift) {
        List<Staffs> staffs = staffRepository.findAllStaff();

        // Lọc thủ công: chỉ lấy staff có ca làm việc phù hợp (ví dụ: ca sáng)
        int targetWorkShift = workShift; // Hoặc 2 tùy nhu cầu, bạn có thể truyền từ controller

        List<Staffs> matchedStaffs = new ArrayList<>();
        for (Staffs staff : staffs) {
            if (staff.getWorkShift() == targetWorkShift) {
                matchedStaffs.add(staff);
            }
        }

        if (matchedStaffs.isEmpty()) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }

        Staffs minStaff = matchedStaffs.get(0);
        int minAppointments = minStaff.getAppointments().size();

        for (int i = 1; i < matchedStaffs.size(); i++) {
            Staffs current = matchedStaffs.get(i);
            int currentAppointments = current.getAppointments().size();

            if (currentAppointments < minAppointments) {
                minStaff = current;
                minAppointments = currentAppointments;

                if (minAppointments == 0) {
                    break; // tối ưu: gặp staff chưa có lịch hẹn thì return luôn
                }
            }
        }

        return minStaff;
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
