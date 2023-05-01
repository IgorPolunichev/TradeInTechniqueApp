package com.example.tradeintechniqueapp.config.castomSuccessHandler;

import com.example.tradeintechniqueapp.database.entity.Role;
import com.example.tradeintechniqueapp.database.repository.UserRepository;
import com.example.tradeintechniqueapp.dto.CustomUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CastomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final String URL_FOR_ADMIN = "/users";
//    private final String URL_FOR_ADMIN = "/v3/api-docs/";
    private final String URL_FOR_ALL = "/users/%d";

    private RequestCache requestCache = new HttpSessionRequestCache();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        if (authentication.getAuthorities().contains(Role.ADMIN)) {
            super.setDefaultTargetUrl(URL_FOR_ADMIN);
        } else {
            super.setDefaultTargetUrl(String.format(URL_FOR_ALL,
                    getUserId(authentication)));
        }
        SavedRequest savedRequest = this.requestCache.getRequest(request, response);
        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
            || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            this.requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        clearAuthenticationAttributes(request);
        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private Long getUserId(Authentication authentication) {
        return ((CustomUserDetails) authentication.getPrincipal()).getId();

    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }


}
