package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndManagerCreateRequest;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

@Service
public class ManagerServiceImp implements ManagerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Managers saveManager(UserAndManagerCreateRequest request, Users users) {
        Managers managers = new Managers();
        managers.setUsers(users);
        managers.setOfficePhone(request.getOfficePhone());
        managerRepository.save(managers);
        return managerRepository.save(managers);
    }
}