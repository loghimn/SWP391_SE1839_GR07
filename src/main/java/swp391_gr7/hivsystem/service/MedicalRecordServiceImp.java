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
    private MedicalRecordRepository medicalRecordRepository;

    private String error = "";

    @Override
    public List<MedicalRecords> getAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public Optional<MedicalRecords> getById(int id) {
        return medicalRecordRepository.findById(id);
    }

    @Override
    public MedicalRecords addMedicalRecord(int id, MedicalRecordCreateRequest request) {
        error = "";

        // Check if customer exists
        Customers customer = customerRepository.findById(id)
                .orElse(null);
        if (customer == null) {
            error += "Customer not found. ";
            return null;
        }

        // Check if customer already has a medical record
        Optional<MedicalRecords> existingRecord = medicalRecordRepository.findByCustomers(customer);
        if (existingRecord.isPresent()) {
            error += "Customer already has a medical record. ";
            return null;
        }

        MedicalRecords record = new MedicalRecords();
        record.setCustomers(customer);
        record.setDiagnosis(request.getDiagnosis());
        record.setTreatment(request.getTreatment());
        record.setRecordDate(request.getRecordDate());

        return medicalRecordRepository.save(record);
    }

    @Override
    public MedicalRecords updateMedicalRecord(int cusId, MedicalRecordCreateRequest request) {
        error = "";

        // Check if customer exists
        Customers customer = customerRepository.findById(cusId)
                .orElse(null);
        // Check if record exists
        MedicalRecords existingRecord = medicalRecordRepository.findByCustomers(customer)
                .orElse(null);
        if (existingRecord == null) {
            error += "Medical record not found. ";
            return null;
        }

        // Update fields
        existingRecord.setDiagnosis(request.getDiagnosis());
        existingRecord.setTreatment(request.getTreatment());
        existingRecord.setRecordDate(request.getRecordDate());

        return medicalRecordRepository.save(existingRecord);
    }


    @Override
    public MedicalRecords getMyMedicalRecord(int customerId) {
        error = "";

        // Check if customer exists
        Customers customer = customerRepository.findById(customerId)
                .orElse(null);
        if (customer == null) {
            error += "Customer not found. ";
            return null;
        }

        // Find medical record by customer
        Optional<MedicalRecords> record = medicalRecordRepository.findByCustomers(customer);
        if (record.isEmpty()) {
            error += "No medical record found for this customer. ";
            return null;
        }

        return record.get();
    }

    @Override
    public String getError() {
        return error;
    }
}