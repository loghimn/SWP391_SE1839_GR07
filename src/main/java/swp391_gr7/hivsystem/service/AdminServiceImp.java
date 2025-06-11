package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndAdminCreateRequest;
import swp391_gr7.hivsystem.model.Admin;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(UserAndAdminCreateRequest request, User user) {
        Admin admin = new Admin();
        admin.setUser(user);
        admin.setAssignedArea(request.getAssignedArea());
        return adminRepository.save(admin);
    }
}
