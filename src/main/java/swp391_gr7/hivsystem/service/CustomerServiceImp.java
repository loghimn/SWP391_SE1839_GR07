package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndCustomerCreateRequest;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;
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
    public Customers saveCustomer(UserAndCustomerCreateRequest userAndCustomerCreateRequest, Users users) {
        Customers customers = new Customers();
        customers.setUsers(users);
        customers.setAddress(userAndCustomerCreateRequest.getAddress());
        return customerRepository.save(customers);
    }

}
