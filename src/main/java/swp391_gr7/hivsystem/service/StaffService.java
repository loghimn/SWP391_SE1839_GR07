package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndStaffCreateRequest;
import swp391_gr7.hivsystem.model.Staffs;
import swp391_gr7.hivsystem.model.Users;


public interface StaffService {
    Staffs saveStaff(UserAndStaffCreateRequest request, Users users);
    Staffs findStaffHasLeastAppointment();
}
