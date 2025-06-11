package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.request.MaterialCreateRequest;
import swp391_gr7.hivsystem.model.Material;
import swp391_gr7.hivsystem.service.MaterialService;
import swp391_gr7.hivsystem.service.MaterialServiceImp;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/create")
    public ApiReponse<Boolean> createMaterial(@RequestBody MaterialCreateRequest request) {
        Material material = materialService.addMaterial(request);
        // Kiểm tra material có null không, gán vào result
        boolean result = material != null;
        if (material == null) {
            MaterialServiceImp materialServiceImp = new MaterialServiceImp();
            // in ra lỗi đã thêm vào biến error ở bên BlogServiceImp
            if(materialServiceImp.error != null){
                return ApiReponse.<Boolean>builder()
                        .result(result)
                        .message(materialServiceImp.error)
                        .build();
            }
        }
        // Nếu tạo thành công, trả về tin nhắn success
        return ApiReponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/update/{id}")
    public ApiReponse<Boolean> updateContentMaterial(@PathVariable int id, @RequestBody Material updateContent){
        materialService.updateInformationMaterial(id, updateContent);
        return ApiReponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{id}")
    public ApiReponse<Boolean> deleteMaterial(@PathVariable int id){
        materialService.deleteMaterial(id);
        return ApiReponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

}
