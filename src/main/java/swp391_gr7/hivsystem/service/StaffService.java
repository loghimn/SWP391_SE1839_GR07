package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.roleDto.StaffDto;
import swp391_gr7.hivsystem.message.registerMessage;


public interface StaffService {
    public registerMessage save(StaffDto staffDto);
}
