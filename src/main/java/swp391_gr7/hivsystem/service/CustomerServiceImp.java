package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.UserAndCustomerCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Managers;
import swp391_gr7.hivsystem.model.Users;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.ManagerRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Customers saveCustomer(UserAndCustomerCreateRequest userAndCustomerCreateRequest, Users users) {
        Managers manager = managerRepository.findManagerById(1);
        if (manager == null) {
            throw new AppException(ErrorCode.MANAGER_NOT_FOUND);
        }
        Customers customers = new Customers();
        customers.setUsers(users);
        customers.setAddress(userAndCustomerCreateRequest.getAddress());
        customers.setManagers(manager);
        return customerRepository.save(customers);
    }

    @Override
    public Customers getCustomerById(int id) {

        return customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
    }

    @Override
    public List<Customers> getAllCustomers() {
        if (customerRepository.count() == 0) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return customerRepository.findAll();
    }

}
