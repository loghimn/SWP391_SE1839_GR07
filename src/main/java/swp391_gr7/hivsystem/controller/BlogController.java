package swp391_gr7.hivsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.response.ApiResponse;
import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.model.Blogs;
import swp391_gr7.hivsystem.service.BlogService;
import swp391_gr7.hivsystem.service.BlogServiceImp;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/create")
    public ApiResponse<Boolean> blogCreate(@RequestBody BlogCreateRequest request) {
        Blogs blogs = blogService.addBlog(request);
        // Kiểm tra blog có null không, gán vào result
        boolean result = blogs != null;

        if (blogs == null) {
            BlogServiceImp blogServiceImp = new BlogServiceImp();
            // in ra lỗi đã thêm vào biến error ở bên BlogServiceImp
            if(blogServiceImp.error != null){
                return ApiResponse.<Boolean>builder()
                        .result(result)
                        .message(blogServiceImp.error)
                        .build();
            }
        }
        // Nếu tạo thành công, trả về tin nhắn success
        return ApiResponse.<Boolean>builder()
                .result(result)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/update/{id}")
    public ApiResponse<Boolean> updateContentBlog(@PathVariable int id, @RequestBody Blogs updateContent){
        blogService.updateInformationBlog(id, updateContent);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> deleteBlog(@PathVariable int id){
        blogService.deleteBlog(id);
        return ApiResponse.<Boolean>builder()
                .result(true)
                .message("Success")
                .build();
    }
}
