package swp391_gr7.hivsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import swp391_gr7.hivsystem.model.Blog;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog,Integer>{
}
