package SWP391_GR07.HivSystem.service.user;

import SWP391_GR07.HivSystem.dto.CustomerDto;
import SWP391_GR07.HivSystem.dto.DoctorDto;
import SWP391_GR07.HivSystem.dto.ManagerDto;
import SWP391_GR07.HivSystem.dto.StaffDto;
import SWP391_GR07.HivSystem.message.registerMessage;
import SWP391_GR07.HivSystem.model.User;

public interface UserService {
    registerMessage save(CustomerDto customerDto);
    registerMessage save(StaffDto staffDto);
    registerMessage save(DoctorDto doctorDto);
    registerMessage save(ManagerDto managerDto);
}
