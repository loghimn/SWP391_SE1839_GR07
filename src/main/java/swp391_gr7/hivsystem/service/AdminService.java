package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndAdminCreateRequest;
import swp391_gr7.hivsystem.model.Admins;
import swp391_gr7.hivsystem.model.Users;

public interface AdminService {
    Admins saveAdmin(UserAndAdminCreateRequest request, Users users);
}
