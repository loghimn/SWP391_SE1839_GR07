package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndManagerCreateRequest;
import swp391_gr7.hivsystem.model.Manager;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

@Service
public class ManagerServiceImp implements ManagerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager saveManager(UserAndManagerCreateRequest request, User user) {
        Manager manager = new Manager();
        manager.setUser(user);
        manager.setDepartment(request.getDepartment());
        manager.setOfficePhone(request.getOfficePhone());
        managerRepository.save(manager);
        return managerRepository.save(manager);
    }
}
