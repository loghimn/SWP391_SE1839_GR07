package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.MaterialCreateRequest;
import swp391_gr7.hivsystem.model.Material;

public interface MaterialService {
    Material addMaterial(MaterialCreateRequest request);
    Material updateInformationMaterial(int id, Material updateContent);
    void deleteMaterial(int id);
}
