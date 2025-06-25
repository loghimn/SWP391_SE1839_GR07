package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateRequest {

    /*
    @NotBlank(message = "BLOG_DOCTOR_INVALID_MAIL_NOTBLANK")
    private String doctorMail;
    */

    @NotBlank(message = "BLOG_TITLE_NOTBLANK")
    private String title;

    @NotBlank(message = "BLOG_CONTENT_NOTBLANK")
    private String content;

    @NotBlank(message = "BLOG_IMAGEURL_NOTBLANK")
    private String imageUrl;

    @NotBlank(message = "BLOG_SOURCE_NOTBLANK")
    private String source;

//    private LocalDate createAt;
}
