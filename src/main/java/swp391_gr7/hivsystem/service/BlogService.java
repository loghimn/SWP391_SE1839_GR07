package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.BlogCreateRequest;
import swp391_gr7.hivsystem.model.Blog;

public interface BlogService {
    Blog addBlog(BlogCreateRequest request);
    Blog updateInformationBlog(int id, Blog updateContent);
    void deleteBlog(int id);
}
