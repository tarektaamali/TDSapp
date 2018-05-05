package com.mpdam.info.tdsapp.remote;

import com.mpdam.info.tdsapp.Model.ProjetResp;
import com.mpdam.info.tdsapp.Model.RapportAll;
import com.mpdam.info.tdsapp.Model.RapportMsg;
import com.mpdam.info.tdsapp.Model.RapportResp;
import com.mpdam.info.tdsapp.Model.Token;
import com.mpdam.info.tdsapp.Model.User;
import com.mpdam.info.tdsapp.Model.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Info on 4/18/2018.
 */

public interface APIService {


    @POST("user/signin")
    @FormUrlEncoded
    Call<UserResponse>login(@Field("email")String email,
                            @Field("password")String password);


    @GET("user/logout")
    Call<Token>logout(@Query("token") String token);

    @GET("user/verify")
    Call<Token>verify(@Query("token") String token);

    @GET("projet")
    Call<ProjetResp> getall(@Query("token") String token,
                            @Query("etat")int etat);

    @Multipart
    @POST("rapport")
     Call<RapportResp>create(@Part("title") RequestBody  title,
                             @Part("note") RequestBody  note,
                             @Part("projet_id") RequestBody  projet_id,
                             @Part MultipartBody.Part file);


    @GET("rapport")
    Call<RapportAll> getrapports(@Query("token") String token);

    @GET("rapport/{id}")
    Call<RapportMsg>getRapportDetails(@Path("id") int id,
                                      @Query("token") String token);

}


















