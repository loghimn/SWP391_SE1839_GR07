package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndAdminCreateRequest;
import swp391_gr7.hivsystem.model.Admins;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admins saveAdmin(UserAndAdminCreateRequest request, Users users) {
        Admins admins = new Admins();
        admins.setUsers(users);
        admins.setAssignedArea(request.getAssignedArea());
        return adminRepository.save(admins);
    }
}
