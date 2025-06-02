package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.roleDto.DoctorDto;
import swp391_gr7.hivsystem.message.registerMessage;
import swp391_gr7.hivsystem.model.Doctor;
import swp391_gr7.hivsystem.model.User;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.UserRepository;

@Service
public class DoctorServiceImp implements DoctorService{



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public registerMessage save(DoctorDto doctorDto) {

        Doctor existingLisenceNumber = doctorRepository.findByLicenseNumber(doctorDto.getLicenseNumber());
        User existingEmail = userRepository.findByEmail(doctorDto.getEmail());
        User existingPhone = userRepository.findByPhone(doctorDto.getPhone());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (existingLisenceNumber != null) {
            return new registerMessage("License number already exists", false);
        }
        if (existingEmail != null || existingPhone != null) {
            return new registerMessage("Email or Phone already exists", false);
        } else {
            User user = new User();
            user.setUsername(doctorDto.getUsername());
            user.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
            user.setEmail(doctorDto.getEmail());
            user.setPhone(doctorDto.getPhone());
            user.setFullName(doctorDto.getFullName());
            user.setDateOfBirth(doctorDto.getDateOfBirth());
            user.setGender(doctorDto.getGender());
            user.setRole(doctorDto.getRole());
            userRepository.save(user);
            if (doctorDto.getRole().equalsIgnoreCase("doctor")) {
                Doctor doctor = new Doctor();
                doctor.setUser(user);
                doctor.setDepartment(doctorDto.getDepartment());
                doctor.setYearExperience(doctorDto.getYearExperience());
                doctor.setLicenseNumber(doctorDto.getLicenseNumber());
                doctorRepository.save(doctor);
                return new registerMessage("Doctor saved successfully", true);
            } else {
                return new registerMessage("Doctor saved failure", false);
            }
        }  }
}
