package com.cagri.videomanagement.controller;

import com.cagri.videomanagement.integration.VideoManagerApi;
import com.cagri.videomanagement.model.AuthenticationResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cagri.dursun on 22.8.2016.
 */
public abstract class AbstractController {

    @Autowired
    private VideoManagerApi videoManagerApi;

    public void checkSession(HttpServletRequest request){
        if(StringUtils.isEmpty(request.getSession().getAttribute("auth"))) {
            AuthenticationResponseModel auth = videoManagerApi.authentication();
            request.getSession().setAttribute("auth",auth);
            videoManagerApi.setAuth(auth);
        }
    }
}
