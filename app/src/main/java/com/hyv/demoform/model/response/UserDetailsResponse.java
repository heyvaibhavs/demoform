package com.hyv.demoform.model.response;

import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse {
    @SerializedName("response") private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
