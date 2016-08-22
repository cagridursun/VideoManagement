package com.cagri.videomanagement.integration;

import com.cagri.videomanagement.model.*;
import org.jcodec.api.JCodecException;

import java.io.IOException;
import java.util.List;

/**
 * Created by cagri.dursun on 16.8.2016.
 */
public interface VideoManagerApi {

    AuthenticationResponseModel authentication();

    AuthenticationResponseModel refreshAuthentication(AuthRefreshRequestModel request);

    VideoListModel getVideoList();

    VideoModel getVideo(String videoId);

    List<VideoCDNDownloadModel> getDownloadUrls(String videoId);

    AuthenticationResponseModel getAuth();

    void setAuth(AuthenticationResponseModel auth);



}
