package in.equipshare.escms.rest;

import java.util.List;
import java.util.Map;

import in.equipshare.escms.model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("/signup")
    Call<Result>signup(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/login")
    Call<Result>login(@Field(value="username",encoded = true) String username,@Field(value="password",encoded = true) String password);


}
