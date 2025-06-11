package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndManagerCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndStaffCreateRequest;
import swp391_gr7.hivsystem.model.Manager;
import swp391_gr7.hivsystem.model.Staff;
import swp391_gr7.hivsystem.model.User;

import java.util.List;


public interface StaffService {
    Staff saveStaff(UserAndStaffCreateRequest request, User user);
    Staff findStaffHasLeastAppointment();
}
