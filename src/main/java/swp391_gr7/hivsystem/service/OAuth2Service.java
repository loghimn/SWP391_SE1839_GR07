package swp391_gr7.hivsystem.service;

import com.nimbusds.jose.JOSEException;
import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;
import swp391_gr7.hivsystem.dto.request.OAuth2CreateRequest;
import swp391_gr7.hivsystem.dto.response.AuthenticationResponse;

public interface OAuth2Service {
    AuthenticationResponse registerOAuth2User(OAuth2CreateRequest request) throws JOSEException;
}