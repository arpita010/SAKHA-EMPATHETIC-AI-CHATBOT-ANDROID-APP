package com.chat.app.retrofit;

import com.chat.app.helper.LoginRequestBody;
import com.chat.app.helper.MessageRequest;
import com.chat.app.helper.UserRequest;
import com.chat.app.models.Message;
import com.chat.app.models.User;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/authenticateLogin")
    Call<User> getUser(@Body LoginRequestBody loginRequestBody);

    @POST("/saveUser")
    Call<User> saveUser(@Body UserRequest userRequest);

    @POST("/getMessage")
    Call<Message> getResponseFromSakha(@Body MessageRequest message);


    @POST("/getCurrentUser")
    Call<User> getCurrentUser(@Body int userId);


}
