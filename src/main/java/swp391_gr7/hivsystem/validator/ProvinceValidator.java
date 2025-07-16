package swp391_gr7.hivsystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ProvinceValidator implements ConstraintValidator<ValidProvinceConstraint, String> {

    private static final Set<String> VALID_PROVINCES = Set.of(
            "An Giang",
            "Bắc Ninh",
            "Cà Mau",
            "Cao Bằng",
            "Đắk Lắk",
            "Điện Biên",
            "Đồng Nai",
            "Đồng Tháp",
            "Gia Lai",
            "Hà Tĩnh",
            "Hưng Yên",
            "Khánh Hòa",
            "Lai Châu",
            "Lạng Sơn",
            "Lào Cai",
            "Lâm Đồng",
            "Nghệ An",
            "Ninh Bình",
            "Phú Thọ",
            "Quảng Ngãi",
            "Quảng Ninh",
            "Quảng Trị",
            "Sơn La",
            "Tây Ninh",
            "Thanh Hóa",
            "Thái Nguyên",
            "Thành phố Cần Thơ",
            "Thành phố Hà Nội",
            "Thành phố Hải Phòng",
            "Thành phố Hồ Chí Minh",
            "Thành phố Huế",
            "Thành phố Đà Nẵng",
            "Tuyên Quang",
            "Vĩnh Long"
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
