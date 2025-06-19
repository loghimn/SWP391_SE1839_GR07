package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.MaterialCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Admins;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.model.Materials;
import swp391_gr7.hivsystem.repository.AdminRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;
import swp391_gr7.hivsystem.repository.MaterialRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    public static String error = "";

    @Override
    public Materials addMaterial(MaterialCreateRequest request) {
        // Xử lý doctor
        Doctors doctor = doctorRepository.findDoctorByMail(request.getDoctorMail())
                .orElseThrow(() -> new AppException(ErrorCode.MATERIAL_DOCTOR_NOT_FOUND));

        // Tạo mới Materials
        Materials materials = new Materials();
        materials.setDoctor(doctor);
        materials.setTitle(request.getTitle());
        materials.setContent(request.getContent());
        materials.setImageUrl(request.getImageUrl());
        materials.setSource(request.getSource());
        materials.setCreateAt(LocalDate.now());

        // lưu materials mới tạo
        return materialRepository.save(materials);
    }

    @Override
    public Materials updateInformationMaterial(int id, Materials updateMaterials) {
        // Tìm ID material
        Materials materials = materialRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MATERIAL_NOT_FOUND));

        // Thay đổi thông tin
        materials.setTitle(updateMaterials.getTitle());
        materials.setContent(updateMaterials.getContent());
        materials.setImageUrl(updateMaterials.getImageUrl());
        materials.setSource(updateMaterials.getSource());
        materials.setCreateAt(LocalDate.now());

        // Lưu thông tin thay đổi
        return materialRepository.save(materials);
    }

    @Override
    public void deleteMaterial(int id) {
        // Tìm ID material
        Materials materials = materialRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MATERIAL_NOT_FOUND));

        // Xóa material
        materialRepository.delete(materials);
    }

    @Override
    public Materials getMaterialById(int id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.MATERIAL_NOT_FOUND));
    }
}
