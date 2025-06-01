package swp391_gr7.hivsystem.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swp391_gr7.hivsystem.dto.reponse.ApiReponse;
import swp391_gr7.hivsystem.dto.reponse.AuthenticationReponse;
import swp391_gr7.hivsystem.dto.request.AuthenticationRequest;
import swp391_gr7.hivsystem.service.AuthenticationService;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
//Chuc nang login Don nhan request tu client va xuat ra reponse
//http://localhost:8080/auth/login
    @PostMapping("/login")
    ApiReponse<AuthenticationReponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws JOSEException {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiReponse.<AuthenticationReponse>builder()
                //Cau truc tra ve json (mess, result(token, authen(Status auth))
                        .message("Authentication Successful")
                        .result(result)
                        .build();

    }
}
