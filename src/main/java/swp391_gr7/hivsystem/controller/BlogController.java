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

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@SecurityRequirement(name = "bearerAuth")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PreAuthorize("hasRole('Doctor')")
    @PostMapping("/create")
    public ApiResponse<Blogs> blogCreate(@RequestBody @Valid BlogCreateRequest request,
                                           @RequestHeader("Authorization") String authorizationHeader) {

        // Extract doctorId from the token
        String token = authorizationHeader.replace("Bearer ", "");
        int doctorId = new JWTUtils().extractDoctorId(token);

        //Blogs blogs = blogService.addBlog(request, doctorId);
        //boolean result = blogs != null;
            return ApiResponse.<Blogs>builder()
                    .result(blogService.addBlog(request, doctorId))
                    .message("Success")
                    .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @PutMapping("/update/{blogid}")
    public ApiResponse<Blogs> updateContentBlog(@PathVariable int blogid, @RequestBody @Valid BlogCreateRequest updateContent) {
        return ApiResponse.<Blogs>builder()
                .result(blogService.updateInformationBlog(blogid, updateContent))
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Doctor')")
    @DeleteMapping("/delete/{blogid}")
    public ApiResponse<Blogs> deleteBlog(@PathVariable int blogid) {
        Blogs blog = blogService.getBlogById(blogid);
        if(blog != null) {
            blogService.deleteBlog(blogid);
        }
        return ApiResponse.<Blogs>builder()
                .result(blog)
                .message("Delete Successfully")
                .build();
    }

    @GetMapping("/get/{blogid}")
    public ApiResponse<Blogs> getBlogById(@PathVariable int blogid) {
        Blogs blogs = blogService.getBlogById(blogid);
        return ApiResponse.<Blogs>builder()
                .result(blogs)
                .message("Success")
                .build();
    }

//    @PreAuthorize("hasRole('Doctor')")
//    @GetMapping("/get/all")
//    public ApiResponse<List<Blogs>> getAllBlogs() {
//        List<Blogs> blogs = blogService.getAllBlogs();
//        return ApiResponse.<List<Blogs>>builder()
//                .result(blogs)
//                .message("Success")
//                .build();
//    }



    @GetMapping("/getAll")
    public ApiResponse<?> getAllBlogs() {
        return ApiResponse.<Object>builder()
                .result(blogService.getAll())
                .message("Success")
                .build();
    }
}
