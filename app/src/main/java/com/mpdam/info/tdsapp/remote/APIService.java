package com.mpdam.info.tdsapp.remote;

import com.mpdam.info.tdsapp.Model.DevisResp;
import com.mpdam.info.tdsapp.Model.FeedbackResp;
import com.mpdam.info.tdsapp.Model.Planning;
import com.mpdam.info.tdsapp.Model.PlanningResp;
import com.mpdam.info.tdsapp.Model.ProjetResp;
import com.mpdam.info.tdsapp.Model.Projetdetails;
import com.mpdam.info.tdsapp.Model.Projetpostresp;
import com.mpdam.info.tdsapp.Model.RapportAll;
import com.mpdam.info.tdsapp.Model.RapportMsg;
import com.mpdam.info.tdsapp.Model.RapportResp;
import com.mpdam.info.tdsapp.Model.RegionResp;
import com.mpdam.info.tdsapp.Model.RegistreResp;
import com.mpdam.info.tdsapp.Model.ServiceResp;
import com.mpdam.info.tdsapp.Model.Token;
import com.mpdam.info.tdsapp.Model.User;
import com.mpdam.info.tdsapp.Model.UserResponse;
import com.mpdam.info.tdsapp.Model.devisUpdate;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
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
                            @Field("password")String password
    );
    @GET("user/show")
    Call<User>show(
            @Query("id") int id,
            @Query("token") String token);
    @POST("user/store")
    @FormUrlEncoded
    Call<UserResponse>store(@Field("email")String email,
                            @Field("password")String password,
                         @Field("nom")String nom,
                            @Field("prenom")String prenom,
                            @Field("societe")String societe,
                            @Field("telephone")String telephone,
                            @Field("role")String role,
                            @Field("adresse")String adresse

    );
    @POST("projet")
    @FormUrlEncoded
    Call<Projetpostresp>save(@Query("token") String token,
                             @Field("objet")String objet,
                             @Field("description")String description,
                             @Field("lieu")String adr,
                             @Field("service_id")int service,
                             @Field("region_id")int region);
    @GET("projet")
    Call<ProjetResp> getmyprojects(@Query("token") String token);
    @GET("propositiond")
    Call<DevisResp> getmyprojetdevis(@Query("projet_id") int id,
            @Query("token") String token);

    @PATCH("propositiond/{id}")
    Call<devisUpdate> accepterdevis(@Path("id") int id,
                                    @Query("token") String token,
                                     @Query("status") int status);

    @GET("propositiondcount")
    Call<devisUpdate> getcountdevis(@Query("id") int id,
            @Query("projet_id") int projet_id,
                                                    @Query("token") String token);
    @GET("propositiondstatus")
    Call<devisUpdate> propositiondstatus(
            @Query("projet_id") int id,
                                    @Query("token") String token);

    @GET("propositiond/{id}")
    Call<Planning> showdevis(@Path("id") int id,
     @Query("token") String token);
    @POST("services/registration")
    @FormUrlEncoded
    Call<RegistreResp>registre(@Query("token") String token,
                           @Field("service_id[]") String[] service);
    @POST("regions/registration")
    @FormUrlEncoded
    Call<RegistreResp>registre_region(@Query("token") String token,
                               @Field("region_id[]") String[] region);

    @GET("user/logout")
    Call<Token>logout(@Query("token") String token);
    @GET("services")
    Call<ServiceResp>view_all();
    @GET("regions")
    Call<RegionResp>view_allregion();
    @GET("user/verify")
    Call<Token>verify(@Query("token") String token);
    @GET("user/get")
    Call<User>getUser(@Query("token") String token);
    @GET("projetf")
    Call<ProjetResp> getall(@Query("token") String token);
    @GET("projetcas")
    Call<PlanningResp> getallcas(@Query("token") String token
            ,@Query("etat") int etat
            );
    @GET("ffeedback")
    Call<FeedbackResp> getallfeed(@Query("token") String token);
    @GET("proposition")
    Call<DevisResp> getalldevis(@Query("token") String token);
    @Multipart
    @POST("rapport")
     Call<RapportResp>create(@Part("title") RequestBody  title,
                             @Part("note") RequestBody  note,
                             @Part("projet_id") RequestBody  projet_id,
                             @Part MultipartBody.Part file);


    @POST("proposition")
    @FormUrlEncoded
    Call<devisUpdate>proposez(@Query("token") String token,
                              @Field("description") String  description,
                              @Field("echenance") String  echenance,
                              @Field("devis") String  devis,
                              @Field("start_date") String  start_date,
                              @Field("projet_id") int projet_id
                              );

    @GET("rapport")
    Call<RapportAll> getrapports(@Query("token") String token);

    @GET("drapport/{id}")
    Call<RapportMsg>getRapportDetails(@Path("id") int id,
                                      @Query("token") String token);



    @GET("planning")
    Call<PlanningResp> getplanning(@Query("token") String token,
                                   @Query("date") String date);


    @GET("projet/{id}")
    Call<Projetdetails> getprojetdetails(@Path("id") int id,
                                         @Query("token") String token);



    @GET("proposition/{id}")
    Call<Planning> getproposition(@Path("id") int id,
                                    @Query("realisateur_id") int realisateur_id );
    @GET("dfeedback")
    Call<FeedbackResp> get_f_feedback(@Query("id") int id
           );
    @POST("dfeedback")
    @FormUrlEncoded
    Call<devisUpdate>feedback(@Query("token") String token,
                             @Field("note")String note,
                             @Field("avis")float avis,
                             @Field("realisateur_id")int realisateur_id);
    @GET("drapport")
    Call<RapportAll>get_f_rapport(@Query("id") int id );
}


















