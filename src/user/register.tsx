import React, { useState } from "react";

function Register() {
    const [Username, setUsername] = useState("");
    const [Password, setPassword] = useState("");
    const [Fullname, setFullname] = useState("");
    const [PhoneNumber, setPhoneNumber] = useState("");
    const [Email, setEmail] = useState("");
    const [Gender, setGender] = useState("1");
    const [ConfirmPassword, setConfirmPassword] = useState("");

    // Error states
    const [errorUsername, setErrorUsername] = useState("");
    const [errorPassword, setErrorPassword] = useState("");
    const [errorFullname, setErrorFullname] = useState("");
    const [errorPhoneNumber, setErrorPhoneNumber] = useState("");
    const [errorEmail, setErrorEmail] = useState("");
    const [errorConfirmPassword, setErrorConfirmPassword] = useState("");
    const [submitMessage, setSubmitMessage] = useState("");

    // Validation functions
    const validateUsername = (username: string) => {
        if (!username) {
            return "Username không được để trống";
        }
        if (username.length < 3) {
            return "Username phải có ít nhất 3 ký tự";
        }
        if (!/^[a-zA-Z0-9_]+$/.test(username)) {
            return "Username chỉ được chứa chữ cái, số và dấu gạch dưới";
        }
        return "";
    };

    const validatePassword = (password: string) => {
        if (!password) {
            return "Password không được để trống";
        }
        if (password.length < 6) {
            return "Password phải có ít nhất 6 ký tự";
        }
        return "";
    };

    const validateEmail = (email: string) => {
        if (!email) {
            return "Email không được để trống";
        }
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            return "Email không hợp lệ";
        }
        return "";
    };

    const validatePhoneNumber = (phone: string) => {
        if (!phone) {
            return "Số điện thoại không được để trống";
        }
        if (!/^[0-9]{10,11}$/.test(phone)) {
            return "Số điện thoại phải có 10-11 chữ số";
        }
        return "";
    };

    const validateFullname = (fullname: string) => {
        if (!fullname) {
            return "Họ tên không được để trống";
        }
        if (fullname.length < 2) {
            return "Họ tên phải có ít nhất 2 ký tự";
        }
        return "";
    };

    const validateConfirmPassword = (confirmPassword: string, password: string) => {
        if (!confirmPassword) {
            return "Vui lòng xác nhận mật khẩu";
        }
        if (confirmPassword !== password) {
            return "Mật khẩu xác nhận không khớp";
        }
        return "";
    };

    // Handle change functions
    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setUsername(value);
        setErrorUsername(validateUsername(value));
    };

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setPassword(value);
        setErrorPassword(validatePassword(value));
        if (ConfirmPassword) {
            setErrorConfirmPassword(validateConfirmPassword(ConfirmPassword, value));
        }
    };

    const handleFullnameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setFullname(value);
        setErrorFullname(validateFullname(value));
    };

    const handlePhoneNumberChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setPhoneNumber(value);
        setErrorPhoneNumber(validatePhoneNumber(value));
    };

    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setEmail(value);
        setErrorEmail(validateEmail(value));
    };

    const handleConfirmPasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setConfirmPassword(value);
        setErrorConfirmPassword(validateConfirmPassword(value, Password));
    };

    const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        
        // Validate all fields
        const usernameError = validateUsername(Username);
        const passwordError = validatePassword(Password);
        const fullnameError = validateFullname(Fullname);
        const phoneError = validatePhoneNumber(PhoneNumber);
        const emailError = validateEmail(Email);
        const confirmPasswordError = validateConfirmPassword(ConfirmPassword, Password);

        setErrorUsername(usernameError);
        setErrorPassword(passwordError);
        setErrorFullname(fullnameError);
        setErrorPhoneNumber(phoneError);
        setErrorEmail(emailError);
        setErrorConfirmPassword(confirmPasswordError);

        // Check if there are any errors
        if (usernameError || passwordError || fullnameError || phoneError || emailError || confirmPasswordError) {
            setSubmitMessage("");
            return;
        }

        // If no errors, proceed with registration
        try {
            const formData = {
                username: Username,
                password: Password,
                fullname: Fullname,
                phoneNumber: PhoneNumber,
                email: Email,
                gender: Gender
            };

            console.log("Đăng ký với thông tin:", formData);
            
            // Simulate API call
            await new Promise(resolve => setTimeout(resolve, 1000));
            
            setSubmitMessage("Đăng ký thành công!");
            
            // Reset form
            setUsername("");
            setPassword("");
            setFullname("");
            setPhoneNumber("");
            setEmail("");
            setGender("1");
            setConfirmPassword("");
            
        } catch (error) {
            setSubmitMessage("Có lỗi xảy ra khi đăng ký. Vui lòng thử lại!");
        }
    };

    return (
        <div className="min-h-screen bg-gray-50 py-8">
            <div className="max-w-md mx-auto bg-white rounded-lg shadow-md p-6">
                <h1 className="text-2xl font-bold text-center text-gray-800 mb-6">Đăng Ký Tài Khoản</h1>
                
                <div className="space-y-4">
                    {/* Username */}
                    <div>
                        <label htmlFor="Username" className="block text-sm font-medium text-gray-700 mb-1">
                            Tên đăng nhập *
                        </label>
                        <input
                            type="text"
                            id="Username"
                            className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 ${
                                errorUsername ? 'border-red-500' : 'border-gray-300'
                            }`}
                            value={Username}
                            onChange={handleUsernameChange}
                            placeholder="Nhập tên đăng nhập"
                        />
                        {errorUsername && <div className="text-red-500 text-sm mt-1">{errorUsername}</div>}
                    </div>

                    {/* Fullname */}
                    <div>
                        <label htmlFor="Fullname" className="block text-sm font-medium text-gray-700 mb-1">
                            Họ và tên *
                        </label>
                        <input
                            type="text"
                            id="Fullname"
                            className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 ${
                                errorFullname ? 'border-red-500' : 'border-gray-300'
                            }`}
                            value={Fullname}
                            onChange={handleFullnameChange}
                            placeholder="Nhập họ và tên"
                        />
                        {errorFullname && <div className="text-red-500 text-sm mt-1">{errorFullname}</div>}
                    </div>

                    {/* Email */}
                    <div>
                        <label htmlFor="Email" className="block text-sm font-medium text-gray-700 mb-1">
                            Email *
                        </label>
                        <input
                            type="email"
                            id="Email"
                            className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 ${
                                errorEmail ? 'border-red-500' : 'border-gray-300'
                            }`}
                            value={Email}
                            onChange={handleEmailChange}
                            placeholder="Nhập email"
                        />
                        {errorEmail && <div className="text-red-500 text-sm mt-1">{errorEmail}</div>}
                    </div>

                    {/* Phone Number */}
                    <div>
                        <label htmlFor="PhoneNumber" className="block text-sm font-medium text-gray-700 mb-1">
                            Số điện thoại *
                        </label>
                        <input
                            type="tel"
                            id="PhoneNumber"
                            className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 ${
                                errorPhoneNumber ? 'border-red-500' : 'border-gray-300'
                            }`}
                            value={PhoneNumber}
                            onChange={handlePhoneNumberChange}
                            placeholder="Nhập số điện thoại"
                        />
                        {errorPhoneNumber && <div className="text-red-500 text-sm mt-1">{errorPhoneNumber}</div>}
                    </div>

                    {/* Gender */}
                    <div>
                        <label htmlFor="Gender" className="block text-sm font-medium text-gray-700 mb-1">
                            Giới tính
                        </label>
                        <select
                            id="Gender"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                            value={Gender}
                            onChange={(e: React.ChangeEvent<HTMLSelectElement>) => setGender(e.target.value)}
                        >
                            <option value="1">Nam</option>
                            <option value="2">Nữ</option>
                            <option value="3">Khác</option>
                        </select>
                    </div>

                    {/* Password */}
                    <div>
                        <label htmlFor="Password" className="block text-sm font-medium text-gray-700 mb-1">
                            Mật khẩu *
                        </label>
                        <input
                            type="password"
                            id="Password"
                            className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 ${
                                errorPassword ? 'border-red-500' : 'border-gray-300'
                            }`}
                            value={Password}
                            onChange={handlePasswordChange}
                            placeholder="Nhập mật khẩu"
                        />
                        {errorPassword && <div className="text-red-500 text-sm mt-1">{errorPassword}</div>}
                    </div>

                    {/* Confirm Password */}
                    <div>
                        <label htmlFor="ConfirmPassword" className="block text-sm font-medium text-gray-700 mb-1">
                            Xác nhận mật khẩu *
                        </label>
                        <input
                            type="password"
                            id="ConfirmPassword"
                            className={`w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 ${
                                errorConfirmPassword ? 'border-red-500' : 'border-gray-300'
                            }`}
                            value={ConfirmPassword}
                            onChange={handleConfirmPasswordChange}
                            placeholder="Nhập lại mật khẩu"
                        />
                        {errorConfirmPassword && <div className="text-red-500 text-sm mt-1">{errorConfirmPassword}</div>}
                    </div>

                    {/* Submit Button */}
                    <button
                        onClick={handleSubmit}
                        className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition duration-200"
                    >
                        Đăng Ký
                    </button>

                    {/* Submit Message */}
                    {submitMessage && (
                        <div className={`text-center mt-3 ${
                            submitMessage.includes('thành công') ? 'text-green-600' : 'text-red-600'
                        }`}>
                            {submitMessage}
                        </div>
                    )}
                </div>

                <div className="text-center mt-4">
                    <p className="text-sm text-gray-600">
                        Đã có tài khoản? 
                        <a href="#" className="text-blue-600 hover:underline ml-1">Đăng nhập ngay</a>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default Register;