package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.UpdatePasswordRequest;
import swp391_gr7.hivsystem.dto.request.UserAndCustomerCreateRequest;
import swp391_gr7.hivsystem.dto.request.UserAndCustomerUpdateRequest;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Users;

import java.util.List;


public interface CustomerService {
    Customers saveCustomer(UserAndCustomerCreateRequest userAndCustomerCreateRequest, Users users);

    Customers getCustomerById(int id);

    List<Customers> getAllCustomers();

    Customers updateCustomer(UserAndCustomerUpdateRequest request, Users users);

    Customers getMyCustomerInfo();

    boolean updatePasswordCustomer(UpdatePasswordRequest request);
}
