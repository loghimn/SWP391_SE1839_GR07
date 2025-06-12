package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UserAndCustomerCreateRequest;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;


public interface CustomerService {
    Customers saveCustomer(UserAndCustomerCreateRequest userAndCustomerCreateRequest, Users users);
}
