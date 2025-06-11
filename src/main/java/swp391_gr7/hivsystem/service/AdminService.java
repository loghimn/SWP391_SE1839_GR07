package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndAdminCreateRequest;
import swp391_gr7.hivsystem.model.Admin;
import swp391_gr7.hivsystem.model.User;

public interface AdminService {
    Admin saveAdmin(UserAndAdminCreateRequest request, User user);
}
