package swp391_gr7.hivsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.model.Blogs;
import swp391_gr7.hivsystem.service.BlogService;
import swp391_gr7.hivsystem.service.BlogServiceImp;
import swp391_gr7.hivsystem.service.JWTUtils;

@RestController
@RequestMapping("/api/blogs")
@SecurityRequirement(name = "bearerAuth")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<Boolean> blogCreate(@RequestBody @Valid BlogCreateRequest request,
                                           @RequestHeader("Authorization") String authorizationHeader) {

        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);


        Blogs blogs = blogService.addBlog(request, doctorId);
        boolean result = blogs != null;
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/update/{id}")
    public ApiResponse<Boolean> updateContentBlog(@PathVariable int id, @RequestBody Blogs updateContent){
        blogService.updateInformationBlog(id, updateContent);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> deleteBlog(@PathVariable int id){
        blogService.deleteBlog(id);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @GetMapping("/get/{id}")
    public ApiResponse<Blogs> getBlogById(@PathVariable int id) {
        Blogs blog = blogService.getBlogById(id);
        return ApiResponse.<Blogs>builder()
                .result(blog)
                .message("Success")
                .build();
    }
}
