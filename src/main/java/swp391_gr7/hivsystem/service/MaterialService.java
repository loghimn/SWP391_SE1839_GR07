package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.MaterialCreateRequest;
import swp391_gr7.hivsystem.model.Materials;

import java.util.List;

public interface MaterialService {
    Materials addMaterial(MaterialCreateRequest request, int doctorId);

    Materials updateInformationMaterial(int id, MaterialCreateRequest updateContent);

    void deleteMaterial(int id);

    Materials getMaterialById(int id);

    List<Materials> getAll();
}
