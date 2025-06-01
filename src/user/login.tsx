import React, { useState } from "react";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [rememberMe, setRememberMe] = useState(false);

    const [errorUsername, setErrorUsername] = useState("");
    const [errorPassword, setErrorPassword] = useState("");
    const [submitMessage, setSubmitMessage] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    const validateUsername = (value: string) => {
        if (!value) return "Username không được để trống";
        if (value.length < 3) return "Username phải có ít nhất 3 ký tự";
        return "";
    };

    const validatePassword = (value: string) => {
        if (!value) return "Password không được để trống";
        if (value.length < 6) return "Password phải có ít nhất 6 ký tự";
        return "";
    };

    const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setUsername(value);
        setErrorUsername(validateUsername(value));
        setSubmitMessage("");
    };

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setPassword(value);
        setErrorPassword(validatePassword(value));
        setSubmitMessage("");
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const usernameError = validateUsername(username);
        const passwordError = validatePassword(password);

        setErrorUsername(usernameError);
        setErrorPassword(passwordError);

        if (usernameError || passwordError) {
            setSubmitMessage("");
            return;
        }

        setIsLoading(true);
        try {
            const loginData = {
                username,
                password,
                rememberMe
            };

            console.log("Đăng nhập với thông tin:", loginData);
            await new Promise(resolve => setTimeout(resolve, 1500));

            const isSuccess = Math.random() > 0.3;

            if (isSuccess) {
                setSubmitMessage("Đăng nhập thành công!");
            } else {
                setSubmitMessage("Sai Username hoặc password. Vui lòng thử lại!");
            }
        } catch (error) {
            setSubmitMessage("Có lỗi xảy ra khi đăng nhập. Vui lòng thử lại!");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-4">
            <div className="bg-white rounded-2xl shadow-xl p-8 w-full max-w-md">
                <form onSubmit={handleSubmit} className="space-y-6">
                    <div className="text-center mb-8">
                        <h1 className="text-3xl font-bold text-gray-800 mb-2">Đăng Nhập</h1>
                        <p className="text-gray-600">Chào mừng bạn trở lại!</p>
                    </div>

                    {/* Username */}
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-2">Username</label>
                        <input
                            type="text"
                            value={username}
                            onChange={handleUsernameChange}
                            disabled={isLoading}
                            className={`w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-blue-500 transition-all ${
                                errorUsername ? "border-red-500 bg-red-50" : "border-gray-300"
                            }`}
                            placeholder="Nhập Username của bạn"
                        />
                        {errorUsername && (
                            <p className="text-red-500 text-sm mt-1 flex items-center">
                                <span className="mr-1">⚠️</span>{errorUsername}
                            </p>
                        )}
                    </div>

                    {/* Password */}
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-2">Password</label>
                        <input
                            type="password"
                            value={password}
                            onChange={handlePasswordChange}
                            disabled={isLoading}
                            className={`w-full px-4 py-3 border rounded-lg focus:ring-2 focus:ring-blue-500 transition-all ${
                                errorPassword ? "border-red-500 bg-red-50" : "border-gray-300"
                            }`}
                            placeholder="Nhập password của bạn"
                        />
                        {errorPassword && (
                            <p className="text-red-500 text-sm mt-1 flex items-center">
                                <span className="mr-1">⚠️</span>{errorPassword}
                            </p>
                        )}
                    </div>

                    {/* Remember Me */}
                    <div className="flex items-center justify-between">
                        <label className="flex items-center">
                            <input
                                type="checkbox"
                                checked={rememberMe}
                                onChange={(e) => setRememberMe(e.target.checked)}
                                disabled={isLoading}
                                className="w-4 h-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                            />
                            <span className="ml-2 text-sm text-gray-600">Ghi nhớ đăng nhập</span>
                        </label>
                        <button
                            type="button"
                            onClick={() => alert("Chức năng quên mật khẩu sẽ có trong phiên bản sau!")}
                            className="text-sm text-blue-600 hover:text-blue-800"
                        >
                            Quên mật khẩu?
                        </button>
                    </div>

                    {/* Submit */}
                    <button
                        type="submit"
                        disabled={isLoading}
                        className={`w-full py-3 px-4 rounded-lg font-medium text-white shadow-lg transition-all transform ${
                            isLoading
                                ? "bg-gray-400 cursor-not-allowed"
                                : "bg-blue-600 hover:bg-blue-700 hover:scale-105 active:scale-95"
                        }`}
                    >
                        {isLoading ? (
                            <div className="flex items-center justify-center">
                                <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-white mr-2"></div>
                                Đang đăng nhập...
                            </div>
                        ) : (
                            "Đăng Nhập"
                        )}
                    </button>

                    {/* Message */}
                    {submitMessage && (
                        <div className={`p-4 rounded-lg text-center font-medium ${
                            submitMessage.includes("thành công") ? "bg-green-100 text-green-800" : "bg-red-100 text-red-800"
                        }`}>
                            {submitMessage.includes("thành công") ? "✅" : "❌"} {submitMessage}
                        </div>
                    )}

                    {/* Register link */}
                    <div className="text-center mt-8">
                        <p className="text-gray-600">
                            Chưa có tài khoản?{" "}
                            <button
                                type="button"
                                className="text-blue-600 hover:text-blue-800 font-medium"
                                onClick={() => alert("Chuyển sang trang đăng ký!")}
                            >
                                Đăng ký ngay
                            </button>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;
