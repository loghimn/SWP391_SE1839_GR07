package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.roleDto.DoctorDto;
import swp391_gr7.hivsystem.message.registerMessage;

public interface DoctorService {
    public registerMessage save(DoctorDto doctorDto);
}
