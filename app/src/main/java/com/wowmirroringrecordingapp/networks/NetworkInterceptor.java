package com.wowmirroringrecordingapp.networks;


import com.wowmirroringrecordingapp.R;
import com.wowmirroringrecordingapp.sqlite.App;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response;
        if (!new ServiceManager(App.getContext()).isNetworkAvailable()) {
            throw (new IOException(App.getContext().getString(R.string.no_internet)));
        } else {
            response = chain.proceed(chain.request());
            try {
                String dataToPrint = "";
                Request request = response.request();
                dataToPrint += request.url() + "\n\n";
                if (request.body() != null)
                    dataToPrint += request.body().toString() + "\n\n";
                String bodyString = response.body().string();
                response = response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
                dataToPrint += bodyString + "\n\n";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
