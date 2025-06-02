package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.roleDto.CustomerDto;
import swp391_gr7.hivsystem.message.registerMessage;
import swp391_gr7.hivsystem.model.Customer;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

@Service
public class CustomerServiceImp implements CustomerService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder PasswordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public registerMessage save(CustomerDto customerDto) {
        User existingPhone = userRepository.findByPhone(customerDto.getPhone());
        User existingEmail = userRepository.findByEmail(customerDto.getEmail());
        if (existingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        } else {
            User user = new User();
            user.setUsername(customerDto.getUsername());
            user.setPassword(PasswordEncoder.encode(customerDto.getPassword()));
            user.setEmail(customerDto.getEmail());
            user.setPhone(customerDto.getPhone());
            user.setFullName(customerDto.getFullName());
            user.setDateOfBirth(customerDto.getDateOfBirth());
            user.setGender(customerDto.getGender());
            user.setRole(customerDto.getRole());
            userRepository.save(user);
            if (customerDto.getRole().equalsIgnoreCase("customer")) {
                Customer customer = new Customer();
                customer.setUser(user);
                customer.setAddress(customerDto.getAddress());
                customerRepository.save(customer);
                return new registerMessage("Customer saved successfully", true);
            } else {
                return new registerMessage("Customer saved failure", false);
            }
        }
    }
}
