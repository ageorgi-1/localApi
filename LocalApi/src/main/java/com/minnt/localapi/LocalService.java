package com.minnt.localapi;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LocalService {

    public static final String filekey = "filePath" ;

    public static final String filePath = filekey + ":" ;


    private static boolean isDeveloperModeActive = false ;

    public static void setDeveloperMode(boolean developerModeActive) {
        isDeveloperModeActive = developerModeActive  ;
    }


    private static boolean isLocalApiRequired = false ;

    private static void callLiveApi() {
        isLocalApiRequired = false ;
    }

    public static void callLocalApi(){
        isLocalApiRequired = true ;
    }



    public static OkHttpClient LocalApiBuilder(final Context context) {

        return  new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();

                        Response response = chain.proceed(request);

                        if (isLocalApiRequired && isDeveloperModeActive && BuildConfig.DEBUG ) {

                            MediaType contentType = Objects.requireNonNull(response.body()).contentType();
                            String jsonString =  getResponseData( context , chain.request().headers().get(filekey)  ) ;
                            ResponseBody body = ResponseBody.create( jsonString , contentType);
                            callLiveApi();
                            return response.newBuilder().body(body).build();

                        }
                        else {
                            return response ;
                        }
                    }
                }).build() ;


    }



    private static String getResponseData( Context context , String fileName) {

        String json = null;
        try {
            InputStream is = context.getAssets().open( fileName );

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}

