package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.model.Blogs;

public interface BlogService {
    Blogs addBlog(BlogCreateRequest request, int doctorId);
    Blogs updateInformationBlog(int id, Blogs updateContent);
    void deleteBlog(int id);
    Blogs getBlogById(int id);
}
