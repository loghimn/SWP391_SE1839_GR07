package swp391_gr7.hivsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialCreateRequest {
    private String doctorMail;
    private String title;
    private String content;
    private String imageUrl;
    private String source;
    private LocalDate createAt;
}
