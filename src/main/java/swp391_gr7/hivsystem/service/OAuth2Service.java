package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;

public interface OAuth2Service {
    boolean registerGoogleUsers(GoogleRegisterRequest request);
}