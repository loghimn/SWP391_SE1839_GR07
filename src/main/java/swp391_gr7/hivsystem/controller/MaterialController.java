package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.MaterialCreateRequest;
import swp391_gr7.hivsystem.model.Materials;
import swp391_gr7.hivsystem.service.JWTUtils;
import swp391_gr7.hivsystem.service.MaterialService;
import swp391_gr7.hivsystem.service.MaterialServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@SecurityRequirement(name = "bearerAuth")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<Boolean> createMaterial(@RequestBody @Valid MaterialCreateRequest request,
                                               @RequestHeader("Authorization") String authorizationHeader) {

        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        Materials materials = materialService.addMaterial(request, doctorId);
        boolean result = materials != null;
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/update/{id}")
    public ApiResponse<Boolean> updateContentMaterial(@PathVariable int id, @RequestBody Materials updateContent) {
        materialService.updateInformationMaterial(id, updateContent);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterial(id);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/get/{id}")
    public ApiResponse<Materials> getMaterialById(@PathVariable int id) {
        Materials materials = materialService.getMaterialById(id);
        return ApiResponse.<Materials>builder()
                .result(materials)
                .message("Success")
                .build();
    }

    @GetMapping("/getAll")
    public ApiResponse<?> getAllMaterials() {
        return ApiResponse.<Object>builder()
                .result(materialService.getAll())
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @GetMapping("/get/all")
    public ApiResponse<List<Materials>> getAllMaterials(){
        List<Materials> materials = materialService.getAllMaterials();
        return ApiResponse.<List<Materials>>builder()
                .result(materials)
                .message("Success")
                .build();
    }

}
