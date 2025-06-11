package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.MaterialCreateRequest;
import swp391_gr7.hivsystem.model.Admin;
import swp391_gr7.hivsystem.model.Material;
import swp391_gr7.hivsystem.repository.AdminRepository;
import swp391_gr7.hivsystem.repository.BlogRepository;
import swp391_gr7.hivsystem.repository.MaterialRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MaterialServiceImp implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private AdminRepository adminRepository;
    public static String error = "";

    @Override
    public Material addMaterial(MaterialCreateRequest request) {
        error = "";
        // Xử lý admin
        Optional<Admin> adminOpt = adminRepository.findAdminByMail(request.getAdminMail());
        Admin admin = null;
        if(adminOpt.isEmpty()){
            error += "Admin not found with mail";
        } else {
            admin = adminOpt.get();
        }

        // Tạo mới Materials
        Material material = new Material();
        material.setAdmin(admin);
        material.setTitle(request.getTitle());
        material.setContent(request.getContent());
        material.setCreateAt(LocalDate.now());

        // lưu materials mới tạo
        return materialRepository.save(material);
    }

    @Override
    public Material updateInformationMaterial(int id, Material updateMaterial) {
        // Tìm ID material
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material ID not found"));

        // Thay đổi thông tin
        material.setTitle(updateMaterial.getTitle());
        material.setContent(updateMaterial.getContent());
        material.setCreateAt(LocalDate.now());

        // Lưu thông tin thay đổi
        return materialRepository.save(material);
    }

    @Override
    public void deleteMaterial(int id) {
        // Tìm ID material
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material ID not found"));

        // Xóa material
        materialRepository.delete(material);
    }
}
