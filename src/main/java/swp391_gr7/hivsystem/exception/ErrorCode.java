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
    UNKNOWN_ERROR(9999, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR), // 500

    // Wrong key
    WRONG_KEY(900, "Wrong key provided", HttpStatus.BAD_REQUEST), // 400

    // Reminder errors
    REMINDER_NOT_FOUND(900, "Reminder not found", HttpStatus.NOT_FOUND), // 404

    // Test result errors
    TEST_RESULT_NOT_FOUND(901, "Test result not found", HttpStatus.NOT_FOUND), // 404
    TEST_RESULT_NOT_HAVE_DOSAGE_TIME(902, "Test result does not have dosage time", HttpStatus.BAD_REQUEST), // 400

    // Medical record errors
    MEDICAL_RECORD_NOT_FOUND(901, "Medical record not found", HttpStatus.NOT_FOUND), // 404
    MEDICAL_RECORD_ALREADY_EXISTS(902, "Medical record already exists", HttpStatus.BAD_REQUEST), // 400
    MEDICAL_RECORD_INVALID(903, "Invalid medical record data", HttpStatus.BAD_REQUEST), // 400
    MEDICAL_RECORD_DOCTOR_NOT_FOUND(904, "Doctor not found for medical record", HttpStatus.NOT_FOUND), // 404
    MEDICAL_RECORD_CUSTOMER_NOT_FOUND(905, "Customer not found for medical record", HttpStatus.NOT_FOUND), // 404

    // ARV medication errors
    ARV_MEDICATION_NOT_FOUND(901, "ARV medication not found", HttpStatus.NOT_FOUND), // 404
    ARV_MEDICATION_ALREADY_EXISTS(902, "ARV medication already exists", HttpStatus.BAD_REQUEST), // 400
    ARV_MEDICATION_INVALID(903, "Invalid ARV medication data", HttpStatus.BAD_REQUEST), // 400
    ARV_MEDICATION_DOCTOR_NOT_FOUND(904, "Doctor not found for ARV medication", HttpStatus.NOT_FOUND), // 404
    ARV_MEDICATION_CUSTOMER_NOT_FOUND(905, "Customer not found for ARV medication", HttpStatus.NOT_FOUND), // 404
    ARV_MEDICATION_ARV_REGIMENT_NOT_FOUND(906, "ARV regiment not found for ARV medication", HttpStatus.NOT_FOUND), // 404

    // ARV regiment errors
    ARV_REGIMENT_NOT_FOUND(901, "ARV regiment not found", HttpStatus.NOT_FOUND), // 404
    ARV_REGIMENT_ALREADY_EXISTS(902, "ARV regiment already exists", HttpStatus.BAD_REQUEST), // 400
    ARV_REGIMENT_INVALID(903, "Invalid ARV regiment data", HttpStatus.BAD_REQUEST), // 400
    ARV_REGIMENT_DOCTOR_NOT_FOUND(904, "Doctor not found for ARV regiment", HttpStatus.NOT_FOUND), // 404
    ARV_REGIMENT_CUSTOMER_NOT_FOUND(905, "Customer not found for ARV regiment", HttpStatus.NOT_FOUND), // 404


    //Treatment plan errors
    TREATMENT_PLAN_NOT_FOUND(901, "Treatment plan not found", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_ALREADY_EXISTS(902, "Treatment plan already exists", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID(903, "Invalid treatment plan data", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_DOCTOR_NOT_FOUND(904, "Doctor not found for treatment plan", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_CUSTOMER_NOT_FOUND(905, "Customer not found for treatment plan", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_ARV_REGIMENT_NOT_FOUND(906, "ARV regiment not found for treatment plan", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_APPOINTMENT_NOT_FOUND(907, "Appointment not found for treatment plan", HttpStatus.NOT_FOUND), // 404
    TREATMENT_PLAN_INVALID_DATE(908, "Invalid date for treatment plan", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_TIME(909, "Invalid time for treatment plan", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_DOSAGE(910, "Invalid dosage for treatment plan", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_FREQUENCY(911, "Invalid frequency for treatment plan", HttpStatus.BAD_REQUEST), // 400
    TREATMENT_PLAN_INVALID_DURATION(912, "Invalid duration for treatment plan", HttpStatus.BAD_REQUEST), // 400

    // Common errors
    UNAUTHENTICATED(989, "You are not authenticated", HttpStatus.UNAUTHORIZED), // 401
    USER_NOT_FOUND(990, "User not found", HttpStatus.NOT_FOUND), // 404
    ACCESS_DENIED(999, "You do not have permission to access", HttpStatus.FORBIDDEN), // 403
    WRONG_USERNAME_PASSWORD(1000, "Wrong username or password", HttpStatus.UNAUTHORIZED), // 401
    USER_EXIST_USERNAME(1001, "Username already exists", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST_PHONE(1002, "Phone number already exists", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST_EMAIL(1003, "Email already exists", HttpStatus.BAD_REQUEST), // 400
    USER_EXIST(1004, "User already exists", HttpStatus.BAD_REQUEST), // 400
    USER_NOT_FOUND_BY_ID(1005, "User not found with ID", HttpStatus.NOT_FOUND), // 404

    // Add error for username
    USER_INVALID_USERNAME_SIZE(1004, "Username must be at least 5 characters", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_USERNAME_NOTBLANK(1005, "Username is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_USERNAME_FORMAT(1006, "Username must contain only letters, digits, and underscores", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_USERNAME_EXIST(1007, "Username already exists", HttpStatus.BAD_REQUEST), // 400

    // Add error for password
    USER_INVALID_PASSWORD_SIZE(1006, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PASSWORD_NOTBLANK(1007, "Password is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PASSWORD_FORMAT(1008, "Password must contain at least one lowercase letter, one digit, and one special character", HttpStatus.BAD_REQUEST), // 400

    // Add error for email
    USER_INVALID_EMAIL_NOTBLANK(1009, "Email is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_EMAIL_FORMAT(1010, "Email must be in a valid format and belong to gmail or example domain ending with com, vn, org", HttpStatus.BAD_REQUEST), // 400

    // Add error for phone
    USER_INVALID_PHONE_NOTBLANK(1011, "Phone number is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PHONE_SIZE(1012, "Phone number must be exactly 10 digits", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_PHONE_FORMAT(1013, "Phone number must start with 0 and be followed by 9 digits", HttpStatus.BAD_REQUEST), // 400

    // Add error for full name
    USER_INVALID_FULLNAME_NOTBLANK(1014, "Full Name is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_FULLNAME_SIZE(1015, "Full Name must be at least 6 characters", HttpStatus.BAD_REQUEST), // 400

    // Add error for gender
    USER_INVALID_GENDER_NOTBLANK(1016, "Gender is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_GENDER_FORMAT(1017, "Gender is male or female", HttpStatus.BAD_REQUEST), // 400

    // Add error for date of birth
    USER_DATEOFBIRTH_NOTNULL(1018, "Date of Birth is required", HttpStatus.BAD_REQUEST), // 400
    USER_INVALID_DATEOFBIRTH(1019, "Date of Birth must be at least 1", HttpStatus.BAD_REQUEST), // 400

    // Add error for customer
    CUSTOMER_INVALID_ADDRESS_NOTBLANK(1020, "Address is required", HttpStatus.BAD_REQUEST), // 400
    CUSTOMER_INVALID_ADDRESS(1021, "Address must be the name of a province/city in Vietnam", HttpStatus.BAD_REQUEST), // 400
    CUSTOMER_NOT_FOUND(1022, "Customer not found with ID", HttpStatus.NOT_FOUND), // 404
    CUSTOMER_NOT_FOUND_BY_ID(1023, "Customer not found with ID", HttpStatus.NOT_FOUND), // 404
    CUSTOMER_NOT_FOUND_GET_ALL_NULL(1024, "No customers found", HttpStatus.NOT_FOUND), // 404

    // Add error for staff
    STAFF_INVALID_DEPARTMENT_NOTBLANK(1022, "Department is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_WORKSHIFT_NOTNULL(1023, "Work shift is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_WORKSHIFT(1024, "Work shift include 1, 2, 3", HttpStatus.BAD_REQUEST), // 400
    STAFF_INVALID_ASSIGNED_AREA_NOTBLANK(1025, "Assigned area is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_NOT_FOUND(1026, "Staff not found with ID", HttpStatus.NOT_FOUND), // 404

    // Add error for manager
    MANAGER_INVALID_DEPARTMENT_NOTBLANK(1026, "Department is required", HttpStatus.BAD_REQUEST), // 400
    MANAGER_INVALID_OFFICE_PHONE_NOTBLANK(1027, "Office phone is required", HttpStatus.BAD_REQUEST), // 400
    MANAGER_INVALID_OFFICE_PHONE_FORMAT(1028, "Office phone must start with 0 and be followed by 9 digits", HttpStatus.BAD_REQUEST), // 400
    MANAGER_NOT_FOUND(1029, "Manager not found with ID", HttpStatus.NOT_FOUND), // 404

    // Add error for doctor
    DOCTOR_INVALID_DEPARTMENT_NOTBLANK(1029, "Department is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_YEAR_EXPERIENCE_NOTNULL(1030, "Year experience is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_YEAR_EXPERIENCE(1032, "Years of experience must be at least 0", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_NOTBLANK(1033, "License number is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_FORMAT(1034, "License number must follow the format DC-{4 number}", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_INVALID_LICENSE_NUMBER_EXIST(1035, "License number already exists", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_NOT_FOUND(1036, "Doctor not found with ID", HttpStatus.NOT_FOUND), // 404
    DOCTOR_NOT_FOUND_BY_ID(1037, "Doctor not found with ID", HttpStatus.NOT_FOUND), // 404

    // Add error for appointment
    APPOINTMENT_CUSTOMER_NOT_FOUND(1036, "Customer not found with ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_DOCTOR_NOT_FOUND(1037, "Doctor not found with ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_SCHEDULE_NOT_FOUND(1038, "Schedule not found with ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_DOCTOR_NOT_WORKING(1039, "Doctor is not working on the selected appointment day", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_DUPLICATE_CUSTOMER(1040, "Appointment time with this customer already exists", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_DUPLICATE_DOCTOR(1041, "Appointment time with this doctor already exists", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_TIME_PAST(1042, "Appointment time must be today or later", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_INVALID_TYPE(1042, "Appointment type must be either 'Test HIV' or 'Consultation'", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_NOT_FOUND(1042, "Appointment not found with ID", HttpStatus.NOT_FOUND), // 404
    APPOINTMENT_ALREADY_DELETED(1042, "Appointment has already been deleted", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_NOT_HAVE_TIME(1042, "Appointment does not have a time set", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_TYPE_IS_NOT_HIV_TEST(1042, "Appointment is not for HIV test", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_ALREADY_IS_NOT_ACTIVE(1042, "Appointment is already inactive", HttpStatus.BAD_REQUEST), // 400

    // Add error for schedule
    SCHEDULE_DOCTOR_NOT_FOUND(1043, "Doctor not found with ID", HttpStatus.NOT_FOUND), // 404
    SCHEDULE_MANAGER_NOT_FOUND(1044, "Manager not found with ID", HttpStatus.NOT_FOUND), // 404
    SCHEDULE_INVALID_DATE(1045, "Schedule date must be today or later", HttpStatus.BAD_REQUEST), // 400
    SCHEDULE_NOT_FOUND(1046, "Schedule not found with ID", HttpStatus.NOT_FOUND), // 404
    SCHEDULE_NOT_FOUND_GET_ALL_NULL(1047, "No schedules found", HttpStatus.NOT_FOUND), // 404

    // Add error for OAuth2
    OAUTH2_INVALID_USERNAME_EXIST(1046, "Username already exists", HttpStatus.BAD_REQUEST), // 400
    OAUTH2_INVALID_EMAIL_EXIST(1047, "Email already exists", HttpStatus.BAD_REQUEST), // 400
    OAUTH2_INVALID_PHONE_EXIST(1048, "Phone number already exists", HttpStatus.BAD_REQUEST), // 400

    // Add error for appointment request
    APPOINTMENT_REQUEST_CUSTOMER_NOTNULL(1049, "Customer ID is required", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_DOCTOR_NOTNULL(1050, "Doctor ID is required", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_SCHEDULE_NOTNULL(1051, "Schedule ID is required", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_TIME_NOTNULL(1052, "Appointment time is required", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_STAFF_NOTNULL(1053, "Staff ID is required", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_TYPE_NOTBLANK(1054, "Appointment type is required", HttpStatus.BAD_REQUEST), // 400
    APPOINTMENT_REQUEST_TYPE_INVALID_FORMAT(1055, "Appointment type must be either 'Test HIV' or 'Consultation'", HttpStatus.BAD_REQUEST), // 400

    // Add error for blogs
    BLOG_DOCTOR_NOT_FOUND(1056, "Doctor not found with ID", HttpStatus.NOT_FOUND), // 404
    BLOG_NOT_FOUND(1057, "Blog not found with ID", HttpStatus.NOT_FOUND), // 404
    BLOG_DOCTOR_INVALID_MAIL_NOTBLANK(1058, "Doctor email is required", HttpStatus.BAD_REQUEST), // 400
    BLOG_TITLE_NOTBLANK(1059, "Blog title is required", HttpStatus.BAD_REQUEST), // 400
    BLOG_CONTENT_NOTBLANK(1060, "Blog content is required", HttpStatus.BAD_REQUEST), // 400
    BLOG_IMAGEURL_NOTBLANK(1061, "Blog image URL is required", HttpStatus.BAD_REQUEST), // 400
    BLOG_SOURCE_NOTBLANK(1062, "Blog source is required", HttpStatus.BAD_REQUEST), // 400

    // Add error for materials
    MATERIAL_DOCTOR_NOT_FOUND(1063, "Doctor not found with ID", HttpStatus.NOT_FOUND), // 404
    MATERIAL_NOT_FOUND(1064, "Material not found with ID", HttpStatus.NOT_FOUND), // 404
    MATERIAL_DOCTOR_INVALID_MAIL_NOTBLANK(1065, "Doctor email is required", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_TITLE_NOTBLANK(1066, "Material title is required", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_CONTENT_NOTBLANK(1067, "Material content is required", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_IMAGEURL_NOTBLANK(1068, "Material image URL is required", HttpStatus.BAD_REQUEST), // 400
    MATERIAL_SOURCE_NOTBLANK(1069, "Material source is required", HttpStatus.BAD_REQUEST), // 400

    // Add error for re-examination
    RE_EXAMINATION_ORIGINAL_APPOINTMENT_NOT_FOUND(1070, "Original appointment not found", HttpStatus.NOT_FOUND), // 404
    RE_EXAMINATION_SCHEDULE_NOT_FOUND_FOR_DOCTOR(1071, "No schedule found for doctor", HttpStatus.NOT_FOUND), // 404
    RE_EXAMINATION_NO_SCHEDULE_DOCTOR_FOUND_AFTER_ORIGINAL_APPOINTMENT_DATE(1072, "No upcoming schedule found for doctor after original appointment date", HttpStatus.NOT_FOUND), // 404
    RE_EXAMINATION_NO_AVAILABLE_RE_EXAM_DATE_IN_NEXT_30_DAYS(1073, "No available re-exam date in next 30 days", HttpStatus.NOT_FOUND), // 404

    // Add error for consultation
    CONSULTATION_APPOINTMENT_NOT_FOUND(1074, "Appointment not found with ID", HttpStatus.NOT_FOUND), // 404
    CONSULTATION_INVALID_APPOINTMENT_TYPE(1075, "Appointment is not for consultation", HttpStatus.BAD_REQUEST), // 400
    CONSULTATION_NOT_FOUND_BY_ID(1076, "Consultation not found with ID", HttpStatus.NOT_FOUND), // 404

    // Add error for consultation request
    CONSULTATION_REQUEST_APPOINTMENT_NOT_NULL(1077, "Appointment ID is required", HttpStatus.BAD_REQUEST), // 400
    CONSULTATION_REQUEST_NOTES_NOTBLANK(1078, "Notes cannot be blank", HttpStatus.BAD_REQUEST), // 400

    // Add error for update user request
    USER_UPDATE_INVALID_USERNAME_SIZE(1079, "Username update must be at least 5 characters", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_USERNAME_NOTBLANK(1080, "Username update is required", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PASSWORD_SIZE(1081, "Password update must be at least 8 characters", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PASSWORD_NOTBLANK(1082, "Password update is required", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PASSWORD_FORMAT(1083, "Password update must contain at least one letter, one digit, and one special character", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_EMAIL_NOTBLANK(1084, "Email update is required", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_EMAIL_FORMAT(1085, "Email update must be in a valid format and belong to gmail or example domain ending with com, vn, org", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PHONE_NOTBLANK(1086, "Phone number update is required", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PHONE_SIZE(1087, "Phone number update must be exactly 10 digits", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_PHONE_FORMAT(1088, "Phone number update must start with 0 and be followed by 9 digits", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_FULLNAME_NOTBLANK(1089, "Full Name update is required", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_DATEOFBIRTH_NOTNULL(1090, "Date of Birth update is required", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_DATEOFBIRTH(1091, "Date of Birth update must be at least 18", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_GENDER_NOTBLANK(1092, "Gender update is required", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_INVALID_GENDER_FORMAT(1093, "Gender update is male or female", HttpStatus.BAD_REQUEST), // 400

    // Add error for update doctor
    DOCTOR_UPDATE_INVALID_DEPARTMENT_NOTBLANK(1094, "Department update is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_YEAR_EXPERIENCE_NOTNULL(1095, "Year experience update is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_YEAR_EXPERIENCE(1096, "Years of experience update must be at least 0", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_NOTBLANK(1097, "License number update is required", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_FORMAT(1098, "License number update must follow the format DC-{4 number}", HttpStatus.BAD_REQUEST), // 400
    DOCTOR_UPDATE_INVALID_LICENSE_NUMBER_EXIST(1099, "License number update already exists", HttpStatus.BAD_REQUEST), // 400

    // Add error for user exists with email, phone, username, not found
    USER_UPDATE_EXIST_USERNAME(1100, "Username update already exists", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_EXIST_EMAIL(1101, "Email update already exists", HttpStatus.BAD_REQUEST), // 400
    USER_UPDATE_EXIST_PHONE(1102, "Phone number update already exists", HttpStatus.BAD_REQUEST), // 400
    USER_NEED_UPDATE_NOT_FOUND(1103, "User needs to update not found", HttpStatus.NOT_FOUND), // 404

    // Add error fot update staff
    STAFF_UPDATE_INVALID_DEPARTMENT_NOTBLANK(1104, "Department update is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_UPDATE_INVALID_WORKSHIFT_NOTNULL(1105, "Work shift update is required", HttpStatus.BAD_REQUEST), // 400
    STAFF_UPDATE_INVALID_WORKSHIFT(1106, "Work shift update must be 1, 2, or 3", HttpStatus.BAD_REQUEST), // 400
    STAFF_UPDATE_INVALID_ASSIGNED_AREA_NOTBLANK(1107, "Assigned area update is required", HttpStatus.BAD_REQUEST), // 400

    // Add error for reminder
    REMINDER_NOT_FOUND_TEST_RESULT(1108, "Test result not found to set reminder", HttpStatus.NOT_FOUND), // 404
    REMINDER_NOT_FOUND_CUSTOMER(1109, "Customer not found to set reminder", HttpStatus.NOT_FOUND), // 404
    REMINDER_NOT_FOUND_STAFF(1110, "Staff not found to set reminder", HttpStatus.NOT_FOUND), // 404
    REMINDER_NOT_FOUND_APPOINTMENT(1111, "Appointment not found to set reminder", HttpStatus.NOT_FOUND), // 404
    REMINDER_ALREADY_DELETED(1112, "Reminder has already been deleted", HttpStatus.BAD_REQUEST), // 400

    // Login
    USER_WAS_DELETED(1121, "The account is not exist", HttpStatus.NOT_FOUND), //404

    // appointment inactive
    APPOINTMENT_TEST_RESULT_INACTIVE(1122, "The appointment is inactive", HttpStatus.NOT_FOUND),

    // List Appointment
    APPOINTMENT_LIST_NOT_FOUND(1120, "Appointment is empty", HttpStatus.NOT_FOUND), // 404

    // ARV Regiment request
    ARV_REGIMENT_NAME_NOT_BLANK(1123, "Name is mandatory", HttpStatus.BAD_REQUEST),
    ARV_REGIMENT_LEVEL_NOT_NULL(1124, "ARV regiment type is mandatory", HttpStatus.BAD_REQUEST),
    ARV_REGIMENT_DESCRIPTION_NOT_BLANK(1125, "Description is mandatory", HttpStatus.BAD_REQUEST),

    // Report request
    REPORT_CREATE_REQUEST_TYPE_NOT_BLANK(1126, "Report type is not blank", HttpStatus.BAD_REQUEST),

    // Schedule request
    SCHEDULES_CREATE_REQUEST_DOCTOR_ID_NOT_NULL(1127, "Schedule is required", HttpStatus.BAD_REQUEST),
    SCHEDULES_CREATE_REQUEST_WORK_DATE_NOT_NULL(1128, "Work date is required", HttpStatus.BAD_REQUEST),
    SCHEDULES_CREATE_REQUEST_NOTES_NOT_BLANK(1129, "Note is not null", HttpStatus.BAD_REQUEST),

    // Suggest medication request
    SUGGEST_MEDICATION_REQUEST_TEST_RESULT_ID_NOT_NULL(1130, "Test result is required", HttpStatus.BAD_REQUEST),
    SUGGEST_MEDICATION_REQUEST_TREATMENT_PLANS_ID_NOT_NULL(1131, "Treatment plan is required", HttpStatus.BAD_REQUEST),

    // Test Result request
    TEST_RESULT_CREATE_REQUEST_TREATMENT_PLAN_ID_NOT_NULL(1132, "Treatment plan is required", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_RESULT_VALUE_NOT_NULL(1133, "Result value is required", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_NOTES_NOT_BLANK(1134, "Notes is required", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_RE_EXAMINATION_NOT_NULL(1135, "Re_examination is required", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_CD4_NOT_NULL(1136, "CD4 is required", HttpStatus.BAD_REQUEST),
    TEST_RESULT_CREATE_REQUEST_HIV_VIRAL_LOAD_NOT_NULL(1137, "HIV viral load is required", HttpStatus.BAD_REQUEST),

    // Treatment plan request
    TREATMENT_PLAN_REQUEST_APPOINTMENT_NOT_NULL(1138, "Appointment is required", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_DESCRIPTION_NOT_BLANK(1139, "Description is required", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_PREGNANCY_NOT_NULL(1140, "Pregnancy is required", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_DOSAGE_TIME_NOT_NULL(1141, "Dosage time is required", HttpStatus.BAD_REQUEST),
    TREATMENT_PLAN_REQUEST_HISTORY_NOT_NULL(1142, "History is required", HttpStatus.BAD_REQUEST),

    // Update Admin
    USER_AND_ADMIN_UPDATE_REQUEST_ASSIGNED_AREA_NOT_BLANK(1143, "Assigned area update is required", HttpStatus.BAD_REQUEST),

    // Update Customer
    CUSTOMER_UPDATE_INVALID_ADDRESS_NOTBLANK(1144, "Address update is required", HttpStatus.BAD_REQUEST),
    CUSTOMER_UPDATE_INVALID_ADDRESS(1145, "Address update must be in Viet Nam", HttpStatus.BAD_REQUEST),

    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
