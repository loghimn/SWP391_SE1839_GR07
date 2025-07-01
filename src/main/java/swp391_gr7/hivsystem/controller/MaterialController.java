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
    public ApiResponse<Materials> createMaterial(@RequestBody @Valid MaterialCreateRequest request,
                                               @RequestHeader("Authorization") String authorizationHeader) {

        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        return ApiResponse.<Materials>builder()
                .result(materialService.addMaterial(request, doctorId))
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/update/{id}")
    public ApiResponse<Materials> updateContentMaterial(@PathVariable int id, @RequestBody @Valid MaterialCreateRequest updateContent) {
        return ApiResponse.<Materials>builder()
                .result(materialService.updateInformationMaterial(id, updateContent))
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Materials> deleteMaterial(@PathVariable int id) {
        Materials materials = materialService.getMaterialById(id);
        if (materials != null) {
            materialService.deleteMaterial(id);
        }
        return ApiResponse.<Materials>builder()
                .result(materials)
                .message("Success")
                .build();
    }

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

}
