package com.cagri.videomanagement.service.impl;

import com.cagri.videomanagement.model.VideoCDNDownloadModel;
import com.cagri.videomanagement.service.VideoManagerService;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by cagri.dursun on 18.8.2016.
 */
@Service
public class VideoManagerServiceImpl implements VideoManagerService{

    @Override
    public String downloadVideo(String realPath, String videoDownloadUrl) throws IOException {

        String fileName = videoDownloadUrl.substring(videoDownloadUrl.lastIndexOf("/") + 1,
                videoDownloadUrl.length());

        String saveFilePath = realPath + "videos/" + File.separator + fileName;

        File f = new File(saveFilePath);

        if(f.exists()){
            System.out.println("This video file has been already downloaded");
            return saveFilePath;
        }

        URL url = null;
        try {
            url = new URL(videoDownloadUrl);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {

            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();


            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");

            return  saveFilePath;
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
        return null;
    }

    @Override
    public void createStillImage(String realPath,String videoId, String videoDownloadedUrl,double timeStamp) throws IOException, JCodecException {
        File f = new File(realPath + "images/"+videoId+timeStamp+".jpg");
        if(!f.exists()) {

            Picture frame = FrameGrab.getNativeFrame(new File(videoDownloadedUrl), timeStamp);

            Transform transform = ColorUtil.getTransform(frame.getColor(), ColorSpace.RGB);
            Picture rgb = Picture.create(frame.getWidth(), frame.getHeight(), ColorSpace.RGB);
            transform.transform(frame, rgb);
            BufferedImage bi = AWTUtil.toBufferedImage(rgb);
            ImageIO.write(bi, "jpg", f);

        }
    }

    @Override
    public String getBestQualityVideoUrl(List<VideoCDNDownloadModel> videoCDNDownloadModelList) {

        if(videoCDNDownloadModelList == null && videoCDNDownloadModelList.isEmpty()){
            return "";
        }

        Collections.sort(videoCDNDownloadModelList,(o1,o2)-> o1.getQuality().compareTo(o2.getQuality()));
        Collections.reverse(videoCDNDownloadModelList);
        String url = "";
        for (VideoCDNDownloadModel videoCdn: videoCDNDownloadModelList) {
            if(videoCdn.getFileExtension().equals("mp4")){
                url = videoCdn.getUrl();
                break;
            }
        }
        if(StringUtils.isEmpty(url)){
            url = videoCDNDownloadModelList.get(0).getUrl();
        }

        return url;
    }

    @Override
    public boolean checkStillImageExist(String realPath, String videoId, double timeStamp) {
        File f = new File(realPath + "images/"+videoId+timeStamp+".jpg" );

        return f.exists();
    }

}
