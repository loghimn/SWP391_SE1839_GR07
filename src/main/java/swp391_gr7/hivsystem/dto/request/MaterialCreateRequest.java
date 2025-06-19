package swp391_gr7.hivsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialCreateRequest {

    @NotBlank(message = "MATERIAL_DOCTOR_INVALID_MAIL_NOTBLANK")
    private String doctorMail;

    @NotBlank(message = "MATERIAL_TITLE_NOTBLANK")
    private String title;

    @NotBlank(message = "MATERIAL_CONTENT_NOTBLANK")
    private String content;

    @NotBlank(message = "MATERIAL_IMAGEURL_NOTBLANK")
    private String imageUrl;

    @NotBlank(message = "MATERIAL_SOURCE_NOTBLANK")
    private String source;

    private LocalDate createAt;
}
