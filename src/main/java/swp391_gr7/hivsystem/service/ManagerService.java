package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.roleDto.ManagerDto;
import swp391_gr7.hivsystem.message.registerMessage;

public interface ManagerService {
    public registerMessage save(ManagerDto managerDto);
}
