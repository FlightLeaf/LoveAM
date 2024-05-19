package com.stop.loveam.utils;

public interface UploadCallback {
    void onComplete(String response);
    void onFailure(String errorMessage);
}

