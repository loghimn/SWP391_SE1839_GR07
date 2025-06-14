package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.model.Admins;
import swp391_gr7.hivsystem.model.Blogs;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.repository.AdminRepository;
import swp391_gr7.hivsystem.repository.BlogRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BlogServiceImp implements BlogService{

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    public static String error = "";

    @Override
    public Blogs addBlog(BlogCreateRequest request) {
        error = "";
        // Xử lý doctor
        Optional<Doctors> doctorOpt = doctorRepository.findDoctorByMail(request.getDoctorMail());
        Doctors doctor = null;
        if(doctorOpt.isEmpty()){
            error += "Doctor not found with mail";
        } else {
            doctor = doctorOpt.get();
        }

        // Tạo mới blogs
        Blogs blogs = new Blogs();
        blogs.setDoctors(doctor);
        blogs.setTitle(request.getTitle());
        blogs.setContent(request.getContent());
        blogs.setImageUrl(request.getImageUrl());
        blogs.setSource(request.getSource());
        blogs.setCreateAt(LocalDate.now());

        // lưu blogs mới tạo
        return blogRepository.save(blogs);
    }

    @Override
    public Blogs updateInformationBlog(int id, Blogs updateContent) {
        // tìm blog bằng id
        Blogs blogs = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog ID not found"));
        blogs.setTitle(updateContent.getTitle());
        blogs.setContent(updateContent.getContent());
        blogs.setImageUrl(updateContent.getImageUrl());
        blogs.setSource(updateContent.getSource());
        blogs.setCreateAt(LocalDate.now());

        // lưu thông tin thay đổi
        return blogRepository.save(blogs);
    }

    @Override
    public void deleteBlog(int id) {
        Blogs blogs = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        blogRepository.delete(blogs);
    }

    @Override
    public Blogs getBlogById(int id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with ID: " + id));
    }
}
