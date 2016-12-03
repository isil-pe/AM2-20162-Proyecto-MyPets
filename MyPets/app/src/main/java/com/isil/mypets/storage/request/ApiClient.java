package com.isil.mypets.storage.request;

import com.isil.mypets.storage.entity.ComentarioRaw;
import com.isil.mypets.storage.entity.ComentarioResponse;
import com.isil.mypets.storage.entity.ComentariosResponse;
import com.isil.mypets.storage.entity.LogInRaw;
import com.isil.mypets.storage.entity.LogInResponse;
import com.isil.mypets.storage.entity.NoticeResponse;
import com.isil.mypets.storage.entity.NoticesResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


/**
 * Created by em on 8/06/16.
 */
public class ApiClient {

    private static final String TAG = "ApiClient";
    private static final String API_BASE_URL="http://api.backendless.com";

    private static ServicesApiInterface servicesApiInterface;
    private static OkHttpClient.Builder httpClient;


    public static ServicesApiInterface getMyApiClient() {

        if (servicesApiInterface == null) {

            Retrofit.Builder builder =new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            httpClient =new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            servicesApiInterface = retrofit.create(ServicesApiInterface.class);
        }
        return servicesApiInterface;
    }

    public interface ServicesApiInterface {

        @Headers({
                "Content-Type: application/json",
                "application-id: 14978619-2A3F-51BC-FF56-373EEA044200",
                "secret-key: 1871DA7C-942D-F8A7-FF0E-14E790A24800",
                "application-type: REST"
        })
        //v1/users/login
        @POST("/v1/users/login")
        Call<LogInResponse> login(@Body LogInRaw raw);


        @Headers({
                "Content-Type: application/json",
                "application-id: 14978619-2A3F-51BC-FF56-373EEA044200",
                "secret-key: 1871DA7C-942D-F8A7-FF0E-14E790A24800",
                "application-type: REST"
        })
        @GET("/v1/data/Noticia")
        Call<NoticesResponse> lstNotices();

        @Headers({
                "Content-Type: application/json",
                "application-id: 14978619-2A3F-51BC-FF56-373EEA044200",
                "secret-key: 1871DA7C-942D-F8A7-FF0E-14E790A24800",
                "application-type: REST"
        })
        @GET
        Call<NoticeResponse> obtenerNoticia(@Url String url);

        @Headers({
                "Content-Type: application/json",
                "application-id: 14978619-2A3F-51BC-FF56-373EEA044200",
                "secret-key: 1871DA7C-942D-F8A7-FF0E-14E790A24800",
                "application-type: REST"
        })
        //v1/users/login
        @POST("/v1/data/ComentarioNoticia")
        Call<ComentarioResponse> comentarNoticia(@Body ComentarioRaw raw);

        @Headers({
                "Content-Type: application/json",
                "application-id: 14978619-2A3F-51BC-FF56-373EEA044200",
                "secret-key: 1871DA7C-942D-F8A7-FF0E-14E790A24800",
                "application-type: REST"
        })
        @GET
        Call<ComentariosResponse> obtenerComentariosNoticia(@Url String url);



    }

    private  static  HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
