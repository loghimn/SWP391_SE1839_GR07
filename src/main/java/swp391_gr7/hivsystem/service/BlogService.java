package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.model.Blogs;

public interface BlogService {
    Blogs addBlog(BlogCreateRequest request);
    Blogs updateInformationBlog(int id, Blogs updateContent);
    void deleteBlog(int id);
}
