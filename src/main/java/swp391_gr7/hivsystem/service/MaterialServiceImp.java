package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.MaterialCreateRequest;
import swp391_gr7.hivsystem.model.Admins;
import swp391_gr7.hivsystem.model.Materials;
import swp391_gr7.hivsystem.repository.AdminRepository;
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
    public Materials addMaterial(MaterialCreateRequest request) {
        error = "";
        // Xử lý admin
        Optional<Admins> adminOpt = adminRepository.findAdminByMail(request.getAdminMail());
        Admins admins = null;
        if(adminOpt.isEmpty()){
            error += "Admin not found with mail";
        } else {
            admins = adminOpt.get();
        }

        // Tạo mới Materials
        Materials materials = new Materials();
        materials.setAdmins(admins);
        materials.setTitle(request.getTitle());
        materials.setContent(request.getContent());
        materials.setCreateAt(LocalDate.now());

        // lưu materials mới tạo
        return materialRepository.save(materials);
    }

    @Override
    public Materials updateInformationMaterial(int id, Materials updateMaterials) {
        // Tìm ID material
        Materials materials = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material ID not found"));

        // Thay đổi thông tin
        materials.setTitle(updateMaterials.getTitle());
        materials.setContent(updateMaterials.getContent());
        materials.setCreateAt(LocalDate.now());

        // Lưu thông tin thay đổi
        return materialRepository.save(materials);
    }

    @Override
    public void deleteMaterial(int id) {
        // Tìm ID material
        Materials materials = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material ID not found"));

        // Xóa material
        materialRepository.delete(materials);
    }
}
