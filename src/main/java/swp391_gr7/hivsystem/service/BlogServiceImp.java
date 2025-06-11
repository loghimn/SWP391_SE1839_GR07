package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.model.Admin;
import swp391_gr7.hivsystem.model.Blog;
import swp391_gr7.hivsystem.repository.AdminRepository;
import swp391_gr7.hivsystem.repository.BlogRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BlogServiceImp implements BlogService{

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AdminRepository adminRepository;
    public static String error = "";

    @Override
    public Blog addBlog(BlogCreateRequest request) {
        error = "";
        // Xử lý admin
        Optional<Admin> adminOpt = adminRepository.findAdminByMail(request.getAdminMail());
        Admin admin = null;
        if(adminOpt.isEmpty()){
            error += "Admin not found with mail";
        } else {
            admin = adminOpt.get();
        }

        // Tạo mới blogs
        Blog blog = new Blog();
        blog.setAdmin(admin);
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setCreateAt(LocalDate.now());

        // lưu blogs mới tạo
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateInformationBlog(int id, Blog updateContent) {
        // tìm blog bằng id
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog ID not found"));
        blog.setTitle(updateContent.getTitle());
        blog.setContent(updateContent.getContent());
        blog.setCreateAt(LocalDate.now());

        // lưu thông tin thay đổi
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(int id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        blogRepository.delete(blog);
    }
}
