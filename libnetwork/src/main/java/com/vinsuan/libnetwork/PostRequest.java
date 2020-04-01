package com.vinsuan.libnetwork;

import java.util.Map;

import okhttp3.FormBody;

public class PostRequest<T> extends Request<T,PostRequest> {
    public PostRequest(String url) {
        super(url);
    }

    @Override
    protected okhttp3.Request generateRequest(okhttp3.Request.Builder builder) {
        FormBody.Builder bodybuilder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            bodybuilder.add(entry.getKey(),String.valueOf(entry.getValue()));
        }
        okhttp3.Request request = builder.url(mUrl).post(bodybuilder.build()).build();
        return request;
    }
}
