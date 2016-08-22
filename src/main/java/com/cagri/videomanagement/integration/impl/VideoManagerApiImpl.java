package com.cagri.videomanagement.integration.impl;

import com.cagri.videomanagement.integration.VideoManagerApi;
import com.cagri.videomanagement.model.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Entity;
import java.util.List;


/**
 * Created by cagri.dursun on 16.8.2016.
 */

@Service
public class VideoManagerApiImpl implements VideoManagerApi {


    private AuthenticationResponseModel auth;

    public AuthenticationResponseModel getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationResponseModel auth) {
        this.auth = auth;
    }

    @Override
    public AuthenticationResponseModel authentication() {

        AuthCreateRequestModel request = new AuthCreateRequestModel();
        request.setUsername("Joing1930@superrito.com");
        request.setPassword("2JmLUGu");

        Entity payLoad = Entity.json("{"
                + "\"username\":\"" + request.getUsername() + "\","
                + "\"password\":\"" + request.getPassword() + "\""
                + "}" );

        final HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
        final HttpEntity<String> requestEntity = new HttpEntity<String>((String)payLoad.getEntity(),
                requestHeaders);

        // Create a new RestTemplate instance
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        final ResponseEntity<AuthenticationResponseModel> response = restTemplate
                .exchange("https://api-qa.video-cdn.net/v1/vms/auth/login", HttpMethod.POST, requestEntity, AuthenticationResponseModel.class);

        this.setAuth(response.getBody());

        return response.getBody();
    }

    @Override
    public AuthenticationResponseModel refreshAuthentication(AuthRefreshRequestModel request) {
        Entity payLoad = Entity.json("{"
                + "\"refreshToken\":\"" + request.getRefreshToken() + "\""
                + "}" );

        final HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
        final HttpEntity<String> requestEntity = new HttpEntity<String>((String)payLoad.getEntity(),
                requestHeaders);

        // Create a new RestTemplate instance
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        final ResponseEntity<AuthenticationResponseModel> response = restTemplate
                .exchange("https://api-qa.video-cdn.net/v1/vms/auth/refresh/"+request.getVideoManagerId(), HttpMethod.POST, requestEntity, AuthenticationResponseModel.class);

        this.setAuth(response.getBody());

        return response.getBody();
    }

    @Override
    public VideoListModel getVideoList() {

        final HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setContentType(MediaType.TEXT_PLAIN);
        requestHeaders.add("Authorization", "Bearer " + getAuth().getAccessToken());

        // Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
        final HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            final ResponseEntity<VideoListModel> response = restTemplate.exchange(
                    "https://api-qa.video-cdn.net/v1/vms/" + getAuth().getVideoManagerList().get(0).getId() + "/videos",
                    HttpMethod.GET, requestEntity, VideoListModel.class);
            return response.getBody();
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                AuthRefreshRequestModel refreshRequest = new AuthRefreshRequestModel();
                refreshRequest.setRefreshToken(getAuth().getRefreshToken());
                refreshRequest.setVideoManagerId(getAuth().getVideoManagerList().get(0).getId());
                refreshAuthentication(refreshRequest);
                return getVideoList();
            }
        }

        return null;
    }

    @Override
    public VideoModel getVideo(String videoId) {
        final HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setContentType(MediaType.TEXT_PLAIN);
        requestHeaders.add("Authorization", "Bearer " + getAuth().getAccessToken());

        // Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
        final HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            final ResponseEntity<VideoModel> response = restTemplate.exchange(
                    "https://api-qa.video-cdn.net/v1/vms/" + getAuth().getVideoManagerList().get(0).getId() + "/videos/" + videoId,
                    HttpMethod.GET, requestEntity, VideoModel.class);
            return response.getBody();
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                AuthRefreshRequestModel refreshRequest = new AuthRefreshRequestModel();
                refreshRequest.setRefreshToken(getAuth().getRefreshToken());
                refreshRequest.setVideoManagerId(getAuth().getVideoManagerList().get(0).getId());
                refreshAuthentication(refreshRequest);
                return getVideo(videoId);
            }
        }

        return null;
    }

    @Override
    public List<VideoCDNDownloadModel> getDownloadUrls(String videoId) {
        final HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setContentType(MediaType.TEXT_PLAIN);
        requestHeaders.add("Authorization", "Bearer " + getAuth().getAccessToken());

        // Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
        final HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            final ResponseEntity<List<VideoCDNDownloadModel>> response = restTemplate.exchange(
                    "https://api-qa.video-cdn.net/v1/vms/" + getAuth().getVideoManagerList().get(0).getId() + "/videos/" + videoId + "/download-urls",
                    HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<VideoCDNDownloadModel>>(){});
            return response.getBody();
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                AuthRefreshRequestModel refreshRequest = new AuthRefreshRequestModel();
                refreshRequest.setRefreshToken(getAuth().getRefreshToken());
                refreshRequest.setVideoManagerId(getAuth().getVideoManagerList().get(0).getId());
                refreshAuthentication(refreshRequest);
                return getDownloadUrls(videoId);
            }
        }

        return null;
    }



}
