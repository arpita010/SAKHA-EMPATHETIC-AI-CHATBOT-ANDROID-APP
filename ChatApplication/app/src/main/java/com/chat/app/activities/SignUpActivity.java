package com.chat.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.chat.app.R;
import com.chat.app.databinding.ActivitySignUpBinding;
import com.chat.app.helper.UserRequest;
import com.chat.app.models.User;
import com.chat.app.retrofit.RetrofitService;
import com.chat.app.retrofit.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private RetrofitService retrofitService;
    private UserApi userApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        retrofitService=new RetrofitService();
        userApi=retrofitService.getRetrofit().create(UserApi.class);

        binding.buttonSignUp.setOnClickListener(v->signUp());

    }
    private void setListeners()
    {
        binding.textAlreadySignIn.setOnClickListener(v-> onBackPressed());
    }
    private void signUp()
    {
        String username=binding.inputName.getText().toString();
        String userEmail=binding.inputSignUpEmail.getText().toString();
        String password=binding.inputSignUpPassword.getText().toString();
        String phoneNumber=binding.inputPhoneNumber.getText().toString();
        UserRequest userRequest=new UserRequest();
        userRequest.setUserEmail(userEmail);
        userRequest.setUsername(username);
        userRequest.setPassword(password);
        userRequest.setPhoneNumber(phoneNumber);
        userApi.saveUser(userRequest).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // it means there exist an user with the same email address. hence we don't create new entry.
                if(response.code()==400)
                {
                    Toast.makeText(SignUpActivity.this, "Email Already exist. Try signing up with another email.", Toast.LENGTH_SHORT).show();
                }
                else
                Toast.makeText(SignUpActivity.this, response.body().getUsername()+" ,Thank you for registering yourself with us !!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
//
                Toast.makeText(SignUpActivity.this, "Some technical issue occured. Please try again..", Toast.LENGTH_SHORT).show();
            }
        });
        binding.inputName.setText("");
        binding.inputSignUpEmail.setText("");
        binding.inputPhoneNumber.setText("");
        binding.inputSignUpPassword.setText("");
    }
}