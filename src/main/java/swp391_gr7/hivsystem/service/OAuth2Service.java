package swp391_gr7.hivsystem.service;

import swp391_gr7.hivsystem.dto.request.GoogleRegisterRequest;
import swp391_gr7.hivsystem.dto.request.OAuth2CreateRequest;

public interface OAuth2Service {
    boolean registerOAuth2User(OAuth2CreateRequest request);
}