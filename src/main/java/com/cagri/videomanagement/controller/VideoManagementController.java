package com.cagri.videomanagement.controller;

import com.cagri.videomanagement.integration.VideoManagerApi;
import com.cagri.videomanagement.integration.impl.VideoManagerApiImpl;
import com.cagri.videomanagement.model.AuthCreateRequestModel;
import com.cagri.videomanagement.model.AuthenticationResponseModel;
import com.cagri.videomanagement.model.VideoListModel;
import com.cagri.videomanagement.model.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cagri.dursun on 16.8.2016.
 */

@RestController
public class VideoManagementController extends AbstractController {

    @Autowired
    private VideoManagerApi videoManagerApi;

    @RequestMapping(value = "/videoList", method = RequestMethod.GET)
    public ResponseEntity<List<VideoModel>> getVideoList(HttpServletRequest request){

        checkSession(request);

        VideoListModel videoListModel = videoManagerApi.getVideoList();

        return new ResponseEntity<>(videoListModel.getVideos(), HttpStatus.OK);
    }

}
