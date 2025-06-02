package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.roleDto.ManagerDto;
import swp391_gr7.hivsystem.message.registerMessage;
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

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public registerMessage save(ManagerDto managerDto) {
        User existingEmail = userRepository.findByEmail(managerDto.getEmail());
        User existingPhone = userRepository.findByPhone(managerDto.getPhone());

        if (existingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        }

        User user = new User();
        user.setUsername(managerDto.getUsername());
        user.setPassword(passwordEncoder.encode(managerDto.getPassword()));
        user.setEmail(managerDto.getEmail());
        user.setPhone(managerDto.getPhone());
        user.setFullName(managerDto.getFullName());
        user.setDateOfBirth(managerDto.getDateOfBirth());
        user.setGender(managerDto.getGender());
        user.setRole(managerDto.getRole());
        userRepository.save(user);

        if ("manager".equalsIgnoreCase(managerDto.getRole())) {
            Manager manager = new Manager();
            manager.setUser(user);
            manager.setDepartment(managerDto.getDepartment());
            manager.setOfficePhone(managerDto.getOfficePhone());
            managerRepository.save(manager);
            return new registerMessage("Manager saved successfully", true);
        } else {
            return new registerMessage("Manager saved failure", false);
        }
    }
}
