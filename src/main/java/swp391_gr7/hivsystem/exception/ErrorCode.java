package swp391_gr7.hivsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import swp391_gr7.hivsystem.model.Users;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Unknown error
    UNKNOWN_ERROR(9999, "Đã xảy ra lỗi không xác định", HttpStatus.INTERNAL_SERVER_ERROR), // 500

    // Wrong key
    WRONG_KEY(900, "Khóa không hợp lệ", HttpStatus.BAD_REQUEST), // 400

    // Reminder errors
    REMINDER_NOT_FOUND(900, "Không tìm thấy nhắc nhở", HttpStatus.NOT_FOUND), // 404

    // Test result errors
    TEST_RESULT_NOT_FOUND(901, "Không tìm thấy kết quả xét nghiệm", HttpStatus.NOT_FOUND), // 404
    TEST_RESULT_NOT_HAVE_DOSAGE_TIME(902, "Kết quả xét nghiệm không có thời gian dùng thuốc", HttpStatus.BAD_REQUEST), // 400

    // Medical record errors
    MEDICAL_RECORD_NOT_FOUND(901, "Không tìm thấy hồ sơ y tế", HttpStatus.NOT_FOUND), // 404
    MEDICAL_RECORD_ALREADY_EXISTS(902, "Hồ sơ y tế đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    MEDICAL_RECORD_INVALID(903, "Dữ liệu hồ sơ y tế không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    MEDICAL_RECORD_DOCTOR_NOT_FOUND(904, "Không tìm thấy bác sĩ trong hồ sơ y tế", HttpStatus.NOT_FOUND), // 404
    MEDICAL_RECORD_CUSTOMER_NOT_FOUND(905, "Không tìm thấy khách hàng trong hồ sơ y tế", HttpStatus.NOT_FOUND), // 404

    // ARV medication errors
    ARV_MEDICATION_NOT_FOUND(901, "Không tìm thấy thuốc ARV", HttpStatus.NOT_FOUND), // 404
    ARV_MEDICATION_ALREADY_EXISTS(902, "Thuốc ARV đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    ARV_MEDICATION_INVALID(903, "Dữ liệu thuốc ARV không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    ARV_MEDICATION_DOCTOR_NOT_FOUND(904, "Không tìm thấy bác sĩ kê thuốc ARV", HttpStatus.NOT_FOUND), // 404
    ARV_MEDICATION_CUSTOMER_NOT_FOUND(905, "Không tìm thấy khách hàng sử dụng thuốc ARV", HttpStatus.NOT_FOUND), // 404
    ARV_MEDICATION_ARV_REGIMENT_NOT_FOUND(906, "Không tìm thấy phác đồ ARV cho thuốc", HttpStatus.NOT_FOUND), // 404

    // ARV regiment errors
    ARV_REGIMENT_NOT_FOUND(901, "Không tìm thấy phác đồ ARV", HttpStatus.NOT_FOUND), // 404
    ARV_REGIMENT_ALREADY_EXISTS(902, "Phác đồ ARV đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    ARV_REGIMENT_INVALID(903, "Dữ liệu phác đồ ARV không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    ARV_REGIMENT_DOCTOR_NOT_FOUND(904, "Không tìm thấy bác sĩ cho phác đồ ARV", HttpStatus.NOT_FOUND), // 404
    ARV_REGIMENT_CUSTOMER_NOT_FOUND(905, "Không tìm thấy khách hàng trong phác đồ ARV", HttpStatus.NOT_FOUND), // 404

    //Treatment plan errors
    TREATMENT_PLAN_NOT_FOUND(901, "Không tìm thấy kế hoạch điều trị", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_ALREADY_EXISTS(902, "Kế hoạch điều trị đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID(903, "Dữ liệu kế hoạch điều trị không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_DOCTOR_NOT_FOUND(904, "Không tìm thấy bác sĩ cho kế hoạch điều trị", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_CUSTOMER_NOT_FOUND(905, "Không tìm thấy khách hàng trong kế hoạch điều trị", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_ARV_REGIMENT_NOT_FOUND(906, "Không tìm thấy phác đồ ARV cho kế hoạch điều trị", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_APPOINTMENT_NOT_FOUND(907, "Không tìm thấy lịch hẹn cho kế hoạch điều trị", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_INVALID_DATE(908, "Ngày điều trị không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_TIME(909, "Thời gian điều trị không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_DOSAGE(910, "Liều lượng điều trị không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_FREQUENCY(911, "Tần suất điều trị không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_DURATION(912, "Thời gian điều trị không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_IS_NOT_ACTIVE(913, "Kế hoạch điều trị đã có kết quả hoặc không hợp lệ", HttpStatus.BAD_REQUEST),

    // Common errors
    UNAUTHENTICATED(989, "Bạn chưa xác thực", HttpStatus.UNAUTHORIZED), // 401
    USER_NOT_FOUND(990, "Không tìm thấy người dùng", HttpStatus.NOT_FOUND), // 404
    ACCESS_DENIED(999, "Bạn không có quyền truy cập", HttpStatus.FORBIDDEN), // 403
    WRONG_USERNAME_PASSWORD(1000, "Sai tên đăng nhập hoặc mật khẩu", HttpStatus.UNAUTHORIZED), // 401
    USER_EXIST_USERNAME(1001, "Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST_PHONE(1002, "Số điện thoại đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST_EMAIL(1003, "Email đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST(1004, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    USER_NOT_FOUND_BY_ID(1005, "Không tìm thấy người dùng theo ID", HttpStatus.NOT_FOUND), // 404

    // Add error for username
    USER_INVALID_USERNAME_SIZE(1004, "Tên đăng nhập phải có ít nhất 5 ký tự", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_USERNAME_NOTBLANK(1005, "Tên đăng nhập là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_USERNAME_FORMAT(1006, "Tên đăng nhập chỉ được chứa chữ cái, số và dấu gạch dưới", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_USERNAME_EXIST(1007, "Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST), // 400

    // Add error for password
    USER_INVALID_PASSWORD_SIZE(1006, "Mật khẩu phải có ít nhất 8 ký tự", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PASSWORD_NOTBLANK(1007, "Mật khẩu là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PASSWORD_FORMAT(1008, "Mật khẩu phải chứa ít nhất một chữ thường, một số và một ký tự đặc biệt", HttpStatus.BAD_REQUEST), // 400

    // Add error for email
    USER_INVALID_EMAIL_NOTBLANK(1009, "Email là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_EMAIL_FORMAT(1010, "Email phải đúng định dạng và thuộc miền gmail, fpt.edu hoặc example kết thúc bằng com, vn, org", HttpStatus.BAD_REQUEST), // 400

    // Add error for phone
    USER_INVALID_PHONE_NOTBLANK(1011, "Số điện thoại là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PHONE_SIZE(1012, "Số điện thoại phải đúng 10 chữ số", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PHONE_FORMAT(1013, "Số điện thoại phải bắt đầu bằng 0 và theo sau là 9 chữ số", HttpStatus.BAD_REQUEST), // 400

    // Add error for full name
    USER_INVALID_FULLNAME_NOTBLANK(1014, "Họ và tên là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_FULLNAME_SIZE(1015, "Họ và tên phải có ít nhất 6 ký tự", HttpStatus.BAD_REQUEST), // 400

    // Add error for gender
    USER_INVALID_GENDER_NOTBLANK(1016, "Giới tính là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_GENDER_FORMAT(1017, "Giới tính chỉ có thể là nam hoặc nữ", HttpStatus.BAD_REQUEST), // 400

    // Add error for date of birth
    USER_DATEOFBIRTH_NOTNULL(1018, "Ngày sinh là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_DATEOFBIRTH(1019, "Tuổi không hợp lệ", HttpStatus.BAD_REQUEST), // 400

    // Add error for customer
    CUSTOMER_INVALID_ADDRESS_NOTBLANK(1020, "Địa chỉ là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    CUSTOMER_INVALID_ADDRESS(1021, "Địa chỉ phải là tên tỉnh/thành phố tại Việt Nam", HttpStatus.BAD_REQUEST), // 400
    CUSTOMER_NOT_FOUND(1022, "Không tìm thấy khách hàng", HttpStatus.NOT_FOUND), // 404
    CUSTOMER_NOT_FOUND_BY_ID(1023, "Không tìm thấy khách hàng với ID", HttpStatus.NOT_FOUND), // 404
    CUSTOMER_NOT_FOUND_GET_ALL_NULL(1024, "Không tìm thấy khách hàng nào", HttpStatus.NOT_FOUND), // 404

    // Add error for staff
    STAFF_INVALID_DEPARTMENT_NOTBLANK(1022, "Department is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_WORKSHIFT_NOTNULL(1023, "Ca làm việc là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_WORKSHIFT(1024, "Ca làm việc phải là 1 hoặc 2", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_ASSIGNED_AREA_NOTBLANK(1025, "Assigned area is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_NOT_FOUND(1026, "Không tìm thấy nhân viên với ID", HttpStatus.NOT_FOUND), // 404

    // Add error for manager
    MANAGER_INVALID_DEPARTMENT_NOTBLANK(1026, "Department is required", HttpStatus.BAD_REQUEST), // 400
    MANAGER_INVALID_OFFICE_PHONE_NOTBLANK(1027, "Số điện thoại cơ quan là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    MANAGER_INVALID_OFFICE_PHONE_FORMAT(1028, "Số điện thoại cơ quan phải bắt đầu bằng 0 và có 9 chữ số theo sau", HttpStatus.BAD_REQUEST), // 400
    MANAGER_NOT_FOUND(1029, "Không tìm thấy quản lý", HttpStatus.NOT_FOUND), // 404

    // Add error for doctor
    DOCTOR_INVALID_DEPARTMENT_NOTBLANK(1029, "Department is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_YEAR_EXPERIENCE_NOTNULL(1030, "Số năm kinh nghiệm là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_YEAR_EXPERIENCE(1032, "Số năm kinh nghiệm phải lớn hơn hoặc bằng 0", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_NOTBLANK(1033, "Số giấy phép hành nghề là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_FORMAT(1034, "Số giấy phép hành nghề phải theo định dạng DC-{4 chữ số}", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_EXIST(1035, "Số giấy phép hành nghề đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_NOT_FOUND(1036, "Không tìm thấy bác sĩ với ID", HttpStatus.NOT_FOUND), // 404
    DOCTOR_NOT_FOUND_BY_ID(1037, "Không tìm thấy bác sĩ với ID", HttpStatus.NOT_FOUND), // 404

    // Add error for appointment
    APPOINTMENT_CUSTOMER_NOT_FOUND(1036, "Không tìm thấy khách hàng với ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_DOCTOR_NOT_FOUND(1037, "Không tìm thấy bác sĩ với ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_SCHEDULE_NOT_FOUND(1038, "Không tìm thấy lịch làm việc với ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_DOCTOR_NOT_WORKING(1039, "Bác sĩ không làm việc vào ngày hẹn đã chọn", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_DUPLICATE_CUSTOMER(1040, "Đã tồn tại lịch hẹn với khách hàng này vào thời điểm đó", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_DUPLICATE_DOCTOR(1041, "Đã tồn tại lịch hẹn với bác sĩ này vào thời điểm đó", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_TIME_PAST(1042, "Thời gian hẹn phải là hôm nay hoặc sau đó", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_INVALID_TYPE(1042, "Loại lịch hẹn phải là 'Xét nghiệm HIV' hoặc 'Tư vấn'", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_NOT_FOUND(1042, "Không tìm thấy lịch hẹn với ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_ALREADY_DELETED(1042, "Lịch hẹn đã bị xóa", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_NOT_HAVE_TIME(1042, "Lịch hẹn chưa có thời gian cụ thể", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_TYPE_IS_NOT_HIV_TEST(1042, "Lịch hẹn không phải là xét nghiệm HIV", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_ALREADY_IS_NOT_ACTIVE(1042, "Lịch hẹn hiện không còn hoạt động", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_ALREADY_EXISTS(1042, "Lịch hẹn đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_CANNOT_UPDATE_RE_EXAMINATION(1042, "Không thể cập nhật lịch hẹn tái khám", HttpStatus.BAD_REQUEST), // 400

    // Add error for schedule
    SCHEDULE_DOCTOR_NOT_FOUND(1043, "Không tìm thấy bác sĩ với ID", HttpStatus.NOT_FOUND), // 404
    SCHEDULE_MANAGER_NOT_FOUND(1044, "Không tìm thấy quản lý với ID", HttpStatus.NOT_FOUND), // 404
    SCHEDULE_INVALID_DATE(1045, "Ngày làm việc phải là hôm nay hoặc sau đó", HttpStatus.BAD_REQUEST), // 400
    SCHEDULE_NOT_FOUND(1046, "Không tìm thấy lịch làm việc với ID", HttpStatus.NOT_FOUND), // 404
    SCHEDULE_NOT_FOUND_GET_ALL_NULL(1047, "Không có lịch làm việc nào", HttpStatus.NOT_FOUND), // 404
    SCHEDULE_ALREADY_EXISTS(1048, "Đã tồn tại lịch làm việc cho bác sĩ này vào ngày đã chọn", HttpStatus.BAD_REQUEST), // 400

    // Add error for OAuth2
    OAUTH2_INVALID_USERNAME_EXIST(1046, "Tên người dùng đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    OAUTH2_INVALID_EMAIL_EXIST(1047, "Email đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    OAUTH2_INVALID_PHONE_EXIST(1048, "Số điện thoại đã tồn tại", HttpStatus.BAD_REQUEST), // 400

    // Add error for appointment request
    APPOINTMENT_REQUEST_CUSTOMER_NOTNULL(1049, "ID khách hàng là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_DOCTOR_NOTNULL(1050, "ID bác sĩ là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_SCHEDULE_NOTNULL(1051, "ID lịch làm việc là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_TIME_NOTNULL(1052, "Thời gian hẹn là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_STAFF_NOTNULL(1053, "ID nhân viên là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_TYPE_NOTBLANK(1054, "Loại lịch hẹn là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_TYPE_INVALID_FORMAT(1055, "Loại lịch hẹn phải là 'Xét nghiệm HIV' hoặc 'Tư vấn'", HttpStatus.BAD_REQUEST), // 400

    // Add error for blogs
    BLOG_DOCTOR_NOT_FOUND(1056, "Không tìm thấy bác sĩ với ID", HttpStatus.NOT_FOUND), // 404
    BLOG_NOT_FOUND(1057, "Không tìm thấy blog với ID", HttpStatus.NOT_FOUND), // 404
    BLOG_DOCTOR_INVALID_MAIL_NOTBLANK(1058, "Email của bác sĩ là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    BLOG_TITLE_NOTBLANK(1059, "Tiêu đề blog là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    BLOG_CONTENT_NOTBLANK(1060, "Nội dung blog là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    BLOG_IMAGEURL_NOTBLANK(1061, "URL hình ảnh blog là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    BLOG_SOURCE_NOTBLANK(1062, "Nguồn blog là bắt buộc", HttpStatus.BAD_REQUEST), // 400

    // Add error for materials
    MATERIAL_DOCTOR_NOT_FOUND(1063, "Không tìm thấy bác sĩ với ID", HttpStatus.NOT_FOUND), // 404
    MATERIAL_NOT_FOUND(1064, "Không tìm thấy tài liệu với ID", HttpStatus.NOT_FOUND), // 404
    MATERIAL_DOCTOR_INVALID_MAIL_NOTBLANK(1065, "Email của bác sĩ là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_TITLE_NOTBLANK(1066, "Tiêu đề tài liệu là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_CONTENT_NOTBLANK(1067, "Nội dung tài liệu là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_IMAGEURL_NOTBLANK(1068, "URL hình ảnh tài liệu là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_SOURCE_NOTBLANK(1069, "Nguồn tài liệu là bắt buộc", HttpStatus.BAD_REQUEST), // 400

    // Add error for re-examination
    RE_EXAMINATION_ORIGINAL_APPOINTMENT_NOT_FOUND(1070, "Không tìm thấy lịch hẹn gốc", HttpStatus.NOT_FOUND), // 404
    RE_EXAMINATION_SCHEDULE_NOT_FOUND_FOR_DOCTOR(1071, "Không tìm thấy lịch làm việc của bác sĩ", HttpStatus.NOT_FOUND), // 404
    RE_EXAMINATION_NO_SCHEDULE_DOCTOR_FOUND_AFTER_ORIGINAL_APPOINTMENT_DATE(1072, "Không tìm thấy lịch làm việc sắp tới của bác sĩ sau ngày hẹn gốc", HttpStatus.NOT_FOUND), // 404
    RE_EXAMINATION_NO_AVAILABLE_RE_EXAM_DATE_IN_NEXT_30_DAYS(1073, "Không có ngày tái khám khả dụng trong 30 ngày tới", HttpStatus.NOT_FOUND), // 404

    // Add error for consultation
    CONSULTATION_APPOINTMENT_NOT_FOUND(1074, "Không tìm thấy lịch hẹn với ID", HttpStatus.NOT_FOUND), // 404
    CONSULTATION_INVALID_APPOINTMENT_TYPE(1075, "Lịch hẹn không phải cho tư vấn", HttpStatus.BAD_REQUEST), // 400
    CONSULTATION_NOT_FOUND_BY_ID(1076, "Không tìm thấy tư vấn với ID", HttpStatus.NOT_FOUND), // 404

    // Add error for consultation request
    CONSULTATION_REQUEST_APPOINTMENT_NOT_NULL(1077, "ID lịch hẹn là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    CONSULTATION_REQUEST_NOTES_NOTBLANK(1078, "Ghi chú không được để trống", HttpStatus.BAD_REQUEST), // 400

    // Add error for update user request
    USER_UPDATE_INVALID_USERNAME_SIZE(1079, "Tên người dùng cập nhật phải có ít nhất 5 ký tự", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_USERNAME_NOTBLANK(1080, "Tên người dùng cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PASSWORD_SIZE(1081, "Mật khẩu cập nhật phải có ít nhất 8 ký tự", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PASSWORD_NOTBLANK(1082, "Mật khẩu cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PASSWORD_FORMAT(1083, "Mật khẩu cập nhật phải có ít nhất một chữ cái, một chữ số và một ký tự đặc biệt", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_EMAIL_NOTBLANK(1084, "Email cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_EMAIL_FORMAT(1085, "Email cập nhật phải đúng định dạng và thuộc domain gmail hoặc example, kết thúc bằng com, vn, org", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PHONE_NOTBLANK(1086, "Số điện thoại cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PHONE_SIZE(1087, "Số điện thoại cập nhật phải đúng 10 chữ số", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PHONE_FORMAT(1088, "Số điện thoại cập nhật phải bắt đầu bằng 0 và theo sau là 9 chữ số", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_FULLNAME_NOTBLANK(1089, "Họ và tên cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_DATEOFBIRTH_NOTNULL(1090, "Ngày sinh cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_DATEOFBIRTH(1091, "Tuổi không hợp lệ", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_GENDER_NOTBLANK(1092, "Giới tính cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_GENDER_FORMAT(1093, "Giới tính cập nhật phải là 'nam' hoặc 'nữ'", HttpStatus.BAD_REQUEST), // 400

    // Add error for update doctor
    DOCTOR_UPDATE_INVALID_DEPARTMENT_NOTBLANK(1094, "Phòng ban cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_YEAR_EXPERIENCE_NOTNULL(1095, "Số năm kinh nghiệm cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_YEAR_EXPERIENCE(1096, "Số năm kinh nghiệm cập nhật phải từ 0 trở lên", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_NOTBLANK(1097, "Số giấy phép hành nghề cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_FORMAT(1098, "Số giấy phép hành nghề cập nhật phải theo định dạng DC-{4 chữ số}", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_EXIST(1099, "Số giấy phép hành nghề cập nhật đã tồn tại", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_IS_NOT_ACTIVE(1100, "Bác sĩ hiện không hoạt động", HttpStatus.BAD_REQUEST), // 400

    // Add error for user exists with email, phone, username, not found
    USER_UPDATE_EXIST_USERNAME(1100, "Username update already exists", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_EXIST_EMAIL(1101, "Email update already exists", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_EXIST_PHONE(1102, "Phone number update already exists", HttpStatus.BAD_REQUEST), // 400
    USER_NEED_UPDATE_NOT_FOUND(1103, "User needs to update not found", HttpStatus.NOT_FOUND), // 404

    // Add error fot update staff
    STAFF_UPDATE_INVALID_DEPARTMENT_NOTBLANK(1104, "Phòng ban cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    STAFF_UPDATE_INVALID_WORKSHIFT_NOTNULL(1105, "Ca làm việc cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400
    STAFF_UPDATE_INVALID_WORKSHIFT(1106, "Ca làm việc cập nhật phải là 1 hoặc 2", HttpStatus.BAD_REQUEST), // 400
    STAFF_UPDATE_INVALID_ASSIGNED_AREA_NOTBLANK(1107, "Khu vực được phân công cập nhật là bắt buộc", HttpStatus.BAD_REQUEST), // 400

    // Add error for reminder
    REMINDER_NOT_FOUND_TEST_RESULT(1108, "Không tìm thấy kết quả xét nghiệm để đặt nhắc nhở", HttpStatus.NOT_FOUND), // 404
    REMINDER_NOT_FOUND_CUSTOMER(1109, "Không tìm thấy khách hàng để đặt nhắc nhở", HttpStatus.NOT_FOUND), // 404
    REMINDER_NOT_FOUND_STAFF(1110, "Không tìm thấy nhân viên để đặt nhắc nhở", HttpStatus.NOT_FOUND), // 404
    REMINDER_NOT_FOUND_APPOINTMENT(1111, "Không tìm thấy lịch hẹn để đặt nhắc nhở", HttpStatus.NOT_FOUND), // 404
    REMINDER_ALREADY_DELETED(1112, "Nhắc nhở đã bị xóa", HttpStatus.BAD_REQUEST), // 400

    // Login
    USER_WAS_DELETED(1121, "Tài khoản không tồn tại", HttpStatus.NOT_FOUND), // 404

    // appointment inactive
    APPOINTMENT_TEST_RESULT_INACTIVE(1122, "Lịch hẹn không còn hoạt động", HttpStatus.NOT_FOUND),

    // List Appointment
    APPOINTMENT_LIST_NOT_FOUND(1120, "Không có lịch hẹn nào", HttpStatus.NOT_FOUND), // 404

    // ARV Regiment request
    ARV_REGIMENT_NAME_NOT_BLANK(1123, "Tên là bắt buộc", HttpStatus.BAD_REQUEST),
    ARV_REGIMENT_LEVEL_NOT_NULL(1124, "Loại phác đồ ARV là bắt buộc", HttpStatus.BAD_REQUEST),
    ARV_REGIMENT_DESCRIPTION_NOT_BLANK(1125, "Mô tả là bắt buộc", HttpStatus.BAD_REQUEST),

    // Report request
    REPORT_CREATE_REQUEST_TYPE_NOT_BLANK(1126, "Loại báo cáo không được để trống", HttpStatus.BAD_REQUEST),

    // Schedule request
    SCHEDULES_CREATE_REQUEST_DOCTOR_ID_NOT_NULL(1127, "Bác sĩ là bắt buộc", HttpStatus.BAD_REQUEST),
    SCHEDULES_CREATE_REQUEST_WORK_DATE_NOT_NULL(1128, "Ngày làm việc là bắt buộc", HttpStatus.BAD_REQUEST),
    SCHEDULES_CREATE_REQUEST_NOTES_NOT_BLANK(1129, "Ghi chú không được để trống", HttpStatus.BAD_REQUEST),

    // Suggest medication request
    SUGGEST_MEDICATION_REQUEST_TEST_RESULT_ID_NOT_NULL(1130, "Kết quả xét nghiệm là bắt buộc", HttpStatus.BAD_REQUEST),
    SUGGEST_MEDICATION_REQUEST_TREATMENT_PLANS_ID_NOT_NULL(1131, "Kế hoạch điều trị là bắt buộc", HttpStatus.BAD_REQUEST),

    // Test Result request
    TEST_RESULT_CREATE_REQUEST_TREATMENT_PLAN_ID_NOT_NULL(1132, "Kế hoạch điều trị là bắt buộc", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_RESULT_VALUE_NOT_NULL(1133, "Giá trị kết quả là bắt buộc", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_NOTES_NOT_BLANK(1134, "Ghi chú là bắt buộc", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_RE_EXAMINATION_NOT_NULL(1135, "Tái khám là bắt buộc", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_CD4_NOT_NULL(1136, "CD4 là bắt buộc", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_HIV_VIRAL_LOAD_NOT_NULL(1137, "Tải lượng HIV là bắt buộc", HttpStatus.BAD_REQUEST),

    // Treatment plan request
    TREATMENT_PLAN_REQUEST_APPOINTMENT_NOT_NULL(1138, "Lịch hẹn là bắt buộc", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_DESCRIPTION_NOT_BLANK(1139, "Mô tả là bắt buộc", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_PREGNANCY_NOT_NULL(1140, "Tình trạng mang thai là bắt buộc", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_DOSAGE_TIME_NOT_NULL(1141, "Thời gian dùng thuốc là bắt buộc", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_HISTORY_NOT_NULL(1142, "Tiền sử bệnh là bắt buộc", HttpStatus.BAD_REQUEST),

    // Update Admin
    USER_AND_ADMIN_UPDATE_REQUEST_ASSIGNED_AREA_NOT_BLANK(1143, "Khu vực được phân công cập nhật là bắt buộc", HttpStatus.BAD_REQUEST),

    // Update Customer
    CUSTOMER_UPDATE_INVALID_ADDRESS_NOTBLANK(1144, "Địa chỉ cập nhật là bắt buộc", HttpStatus.BAD_REQUEST),
    CUSTOMER_UPDATE_INVALID_ADDRESS(1145, "Địa chỉ cập nhật phải nằm trong Việt Nam", HttpStatus.BAD_REQUEST),

    // Medical record of customer
    MEDICAL_RECORD_NOT_FOUND_WITH_CUSTOMER(1146, "Không tìm thấy hồ sơ y tế của khách hàng này", HttpStatus.NOT_FOUND),

    // doctor not schedule
    SCHEDULE_DOCTOR_NOT(1147, "Bác sĩ chưa có lịch làm việc", HttpStatus.NOT_FOUND),

    // old password
    USER_INVALID_OLD_PASSWORD(1148, "Mật khẩu cũ không chính xác", HttpStatus.BAD_REQUEST), // 400

    //Time Apponitment
    TIME_APPOINTMENT_NOT_FOUND(1149, "Thời gian hẹn không hợp lệ", HttpStatus.NOT_FOUND),

    AUTHENTICATION_REQUEST_USERNAME_NOT_BLANK(1150, "Tên đăng nhập không được để trống", HttpStatus.BAD_REQUEST), // 400
    AUTHENTICATION_REQUEST_PASSWORD_NOT_BLANK(1151, "Mật khẩu không được để trống", HttpStatus.BAD_REQUEST) // 400
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
