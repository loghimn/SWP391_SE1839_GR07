package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndCustomerCreateRequest;
import swp391_gr7.hivsystem.model.Customer;
import swp391_gr7.hivsystem.model.User;


public interface CustomerService {
    Customer saveCustomer(UserAndCustomerCreateRequest userAndCustomerCreateRequest, User user);
}
