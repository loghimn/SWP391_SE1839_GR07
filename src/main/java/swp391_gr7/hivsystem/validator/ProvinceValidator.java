package swp391_gr7.hivsystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ProvinceValidator implements ConstraintValidator<ValidProvinceConstraint, String> {

    private static final Set<String> VALID_PROVINCES = Set.of(
            "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bạc Liêu", "Bến Tre", "Bình Định",
            "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Đồng Nai", "Đồng Tháp", "Gia Lai",
            "Hà Nam", "Hà Tĩnh", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Lâm Đồng", "Long An",
            "Nam Định", "Nghệ An", "Ninh Bình", "Phú Thọ", "Quảng Bình", "Quảng Nam", "Quảng Ngãi",
            "Thanh Hóa", "Thành phố Hà Nội", "Thành phố Hồ Chí Minh", "Thành phố Hải Phòng",
            "Thành phố Đà Nẵng", "Thành phố Cần Thơ", "Thành phố Huế"
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
