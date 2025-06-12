package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.MedicalRecordCreateRequest;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.MedicalRecords;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.MedicalRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordServiceImp implements MedicalRecordService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public List<MedicalRecords> getAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public Optional<MedicalRecords> getById(int id) {
        return medicalRecordRepository.findById(id);
    }

    @Override
    public MedicalRecords addMedicalRecord(MedicalRecordCreateRequest request) {
        // Validate and fetch Customer
        Optional<Customers> customerOpt = customerRepository.findById(request.getCustomerId());
        if (customerOpt.isEmpty()) {
            System.out.println("Customer not found with ID: " + request.getCustomerId());
            return null;
        }

        // Validate and fetch Doctor
        Optional<Doctors> doctorOpt = doctorRepository.findById(request.getDoctorId());
        if (doctorOpt.isEmpty()) {
            System.out.println("Doctor not found with ID: " + request.getDoctorId());
            return null;
        }

        // Create new MedicalRecord
        MedicalRecords record = new MedicalRecords();
        record.setCustomers(customerOpt.get());
        record.setDoctors(doctorOpt.get());
        record.setDiagnosis(request.getDiagnosis());
        record.setTreatment(request.getTreatment());
        record.setRecordDate(request.getRecordDate());

        return medicalRecordRepository.save(record);
    }


    @Override
    public void deleteById(int id) {
        medicalRecordRepository.deleteById(id);
    }
}