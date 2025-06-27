package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.MedicalRecordCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Customers;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.MedicalRecords;
import swp391_gr7.hivsystem.repository.CustomerRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.MedicalRecordRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordServiceImp implements MedicalRecordService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    @Override
    public List<MedicalRecords> getAll() {
        if (medicalRecordRepository == null) {
            throw new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND);
        }
        return medicalRecordRepository.findAll();
    }

    @Override
    public Optional<MedicalRecords> getById(int id) {
        return medicalRecordRepository.findById(id);
    }

    @Override
    public MedicalRecords addMedicalRecord(int id, MedicalRecordCreateRequest request) {


        // Check if customer exists
        Customers customer = customerRepository.findById(id)
                .orElse(null);
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        // Check if customer already has a medical record
        Optional<MedicalRecords> existingRecord = medicalRecordRepository.findByCustomers(customer);
        if (existingRecord.isPresent()) {
            throw new AppException(ErrorCode.MEDICAL_RECORD_ALREADY_EXISTS);
        }

        MedicalRecords record = new MedicalRecords();
        record.setCustomers(customer);
        record.setDiagnosis(request.getDiagnosis());
        record.setTreatment(request.getTreatment());
        record.setRecordDate(LocalDate.now());

        return medicalRecordRepository.save(record);
    }

    @Override
    public MedicalRecords updateMedicalRecord(int cusId, MedicalRecordCreateRequest request) {


        // Check if customer exists
        Customers customer = customerRepository.findById(cusId)
                .orElse(null);
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        // Check if record exists
        MedicalRecords existingRecord = medicalRecordRepository.findByCustomers(customer)
                .orElse(null);
        if (existingRecord == null) {
            throw new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND);
        }

        // Update fields
        existingRecord.setDiagnosis(request.getDiagnosis());
        existingRecord.setTreatment(request.getTreatment());
        existingRecord.setRecordDate(LocalDate.now());

        return medicalRecordRepository.save(existingRecord);
    }


    @Override
    public MedicalRecords getMyMedicalRecord(int customerId) {


        // Check if customer exists
        Customers customer = customerRepository.findById(customerId)
                .orElse(null);
        if (customer == null) {
            throw new AppException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

        // Find medical record by customer
        Optional<MedicalRecords> record = medicalRecordRepository.findByCustomers(customer);
        if (record.isEmpty()) {
            throw new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND);
        }

        return record.get();
    }


}