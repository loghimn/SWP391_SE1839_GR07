package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.roleDto.CustomerDto;
import swp391_gr7.hivsystem.message.registerMessage;


public interface CustomerService {
    registerMessage save(CustomerDto customerDto);
}
