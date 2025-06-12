package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndManagerCreateRequest;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Users;

public interface ManagerService {
    Managers saveManager(UserAndManagerCreateRequest request, Users users);

}
