package swp391_gr7.hivsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.exception.AppException;
import swp391_gr7.hivsystem.exception.ErrorCode;
import swp391_gr7.hivsystem.model.Admins;
import swp391_gr7.hivsystem.model.Blogs;
import swp391_gr7.hivsystem.model.Doctors;
import swp391_gr7.hivsystem.repository.AdminRepository;
import swp391_gr7.hivsystem.repository.BlogRepository;
import swp391_gr7.hivsystem.repository.DoctorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    public static String error = "";

    @Override
    public Blogs addBlog(BlogCreateRequest request, int doctorId) {
        // Xử lý doctor
        Doctors doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_DOCTOR_NOT_FOUND));

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
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        // thay đổi thông tin
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
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        blogRepository.delete(blogs);
    }

    @Override
    public Blogs getBlogById(int id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
    }

    @Override
    public List<Blogs> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blogs> getAll() {
        List<Blogs> blogsList = blogRepository.findAll();
        if (blogsList.isEmpty()) {
            throw new AppException(ErrorCode.BLOG_NOT_FOUND);
        }
        return blogsList;
    }
}
