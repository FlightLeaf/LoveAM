package com.stop.loveam.inter;

public interface UploadCallback {
    void onComplete(String response);
    void onFailure(String errorMessage);
}

