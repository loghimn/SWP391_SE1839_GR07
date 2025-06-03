package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndManagerCreateRequest;
import swp391_gr7.hivsystem.model.Manager;
import swp391_gr7.hivsystem.model.User;

public interface ManagerService {

    Manager saveManager(UserAndManagerCreateRequest request, User user);
}
