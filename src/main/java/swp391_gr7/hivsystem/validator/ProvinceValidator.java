package swp391_gr7.hivsystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ProvinceValidator implements ConstraintValidator<ValidProvinceConstraint, String> {

    private static final Set<String> VALID_PROVINCES = Set.of(
            "An Giang", "Ba Ria - Vung Tau", "Bac Giang", "Bac Kan", "Bac Lieu",
            "Bac Ninh", "Ben Tre", "Binh Dinh", "Binh Duong", "Binh Phuoc", "Binh Thuan",
            "Ca Mau", "Cao Bang", "Can Tho", "Da Nang", "Dak Lak", "Dak Nong",
            "Dien Bien", "Dong Nai", "Dong Thap", "Gia Lai", "Ha Giang", "Ha Nam",
            "Ha Noi", "Ha Tinh", "Hai Duong", "Hai Phong", "Hau Giang", "Hoa Binh",
            "Hung Yen", "Ho Chi Minh", "Khanh Hoa", "Kien Giang", "Kon Tum", "Lai Chau",
            "Lam Dong", "Lang Son", "Lao Cai", "Long An", "Nam Dinh", "Nghe An",
            "Ninh Binh", "Ninh Thuan", "Phu Tho", "Phu Yen", "Quang Binh", "Quang Nam",
            "Quang Ngai", "Quang Ninh", "Quang Tri", "Soc Trang", "Son La", "Tay Ninh",
            "Thai Binh", "Thai Nguyen", "Thanh Hoa", "Thua Thien Hue", "Tien Giang",
            "Tra Vinh", "Tuyen Quang", "Vinh Long", "Vinh Phuc", "Yen Bai"
    );

    @Override
    public boolean isValid(String province, ConstraintValidatorContext constraintValidatorContext) {
        return province != null && VALID_PROVINCES.contains(province.trim());
    }

    @Override
    public void initialize(ValidProvinceConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
