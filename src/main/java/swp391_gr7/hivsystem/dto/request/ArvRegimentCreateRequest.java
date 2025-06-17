package swp391_gr7.hivsystem.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swp391_gr7.hivsystem.model.TestResults;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArvRegimentCreateRequest {
    private String code;
    private String name;
    private int level; // 1: bậc 1, 2: bậc 2
    private boolean isForChildren;
    private boolean isForPregnancy;
    private int age;
    private boolean History;
    private String description;
    //private int testResultId;
}
