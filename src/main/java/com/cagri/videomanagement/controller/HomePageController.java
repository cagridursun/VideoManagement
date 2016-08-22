package com.cagri.videomanagement.controller;

import com.cagri.videomanagement.integration.VideoManagerApi;
import com.cagri.videomanagement.model.*;
import com.cagri.videomanagement.service.VideoManagerService;
import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by cagri.dursun on 16.8.2016.
 */
@Controller
@RequestMapping(value = "/vid")
public class HomePageController extends AbstractController {

    @Autowired
    private VideoManagerApi videoManagerApi;

    @Autowired
    private VideoManagerService videoManagerService;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }


    @RequestMapping(value = "/still/{video_id}", method = RequestMethod.GET)
    public ModelAndView getVideo(@PathVariable("video_id") String videoId, HttpServletRequest request){

        checkSession(request);

        VideoModel videoModel = videoManagerApi.getVideo(videoId);
        List<VideoCDNDownloadModel> downloadUrls = videoManagerApi.getDownloadUrls(videoId);

        String downloadUrl = videoManagerService.getBestQualityVideoUrl(downloadUrls);
        String downloadedUrl = "";
        String err = "";
        if(!StringUtils.isEmpty(downloadUrl)) {

            try {
                String realPath = getRealPath(request);
                downloadedUrl = videoManagerService.downloadVideo(realPath,downloadUrl);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            err = "Couldnt find download url for this video";
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("video",videoModel);
        modelAndView.addObject("downloadedUrl",downloadedUrl);
        modelAndView.addObject("err",err);
        modelAndView.setViewName("video");
        return modelAndView;
    }

    @RequestMapping(value = "/still/{video_id}", method = RequestMethod.POST)
    public ModelAndView getVideo(@PathVariable("video_id") String videoId, @ModelAttribute StillImageRequest stillImageRequest, HttpServletRequest request){

        String realPath = getRealPath(request);
        try {
            videoManagerService.createStillImage(realPath,videoId,stillImageRequest.getDownloadedVideoUrl(),stillImageRequest.getTimeStamp());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JCodecException e) {
            e.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/vid/still/"+videoId+"/"+stillImageRequest.getTimeStamp());
        return modelAndView;
    }

    @RequestMapping(value = "/still/{video_id}/{timeStamp:.+}", method = RequestMethod.GET)
    public ModelAndView getVideo(@PathVariable("video_id") String videoId,@PathVariable("timeStamp") double timeStamp, HttpServletRequest request){

        String realPath = getRealPath(request);

        boolean imageExist = videoManagerService.checkStillImageExist(realPath,videoId,timeStamp);
        String err = "";
        if(!imageExist){
            err = "Still image has not been created!. Go back and create still image via timestamp.";
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stillImagePath",videoId+timeStamp+".jpg");
        modelAndView.addObject("err",err);
        modelAndView.setViewName("stillimage");
        return modelAndView;
    }


    private String getRealPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/");
    }


}