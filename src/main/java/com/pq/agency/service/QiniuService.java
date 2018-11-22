package com.pq.agency.service;

/**
 * Created by liutao on 15/12/29.
 */
public interface QiniuService {
    String uploadFile(byte[] data, String platform);

    void deleteFile(String url);
}
