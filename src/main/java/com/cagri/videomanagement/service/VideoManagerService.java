package com.cagri.videomanagement.service;

import com.cagri.videomanagement.model.VideoCDNDownloadModel;
import org.jcodec.api.JCodecException;

import java.io.IOException;
import java.util.List;

/**
 * Created by cagri.dursun on 18.8.2016.
 */
public interface VideoManagerService {

    String downloadVideo(String realPath, String videoDownloadUrl) throws IOException;

    void createStillImage(String realPath, String videoId, String downloadedUrl,double timestamp) throws IOException, JCodecException;

    String getBestQualityVideoUrl(List<VideoCDNDownloadModel> videoCDNDownloadModelList);

    boolean checkStillImageExist(String realPath, String videoId, double timestamp);

}
