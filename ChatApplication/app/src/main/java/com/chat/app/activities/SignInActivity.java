package com.chat.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.chat.app.databinding.ActivitySignInBinding;
import com.chat.app.helper.LoginRequestBody;
import com.chat.app.models.User;
import com.chat.app.retrofit.RetrofitService;
import com.chat.app.retrofit.UserApi;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private RetrofitService retrofitService;

    private UserApi userApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitService=new RetrofitService();
        userApi=retrofitService.getRetrofit().create(UserApi.class);
        setListeners();
        binding.buttonSignIn.setOnClickListener(v->signIn());
    }

    private void setListeners()
    {
        binding.textCreateNewAccount.setOnClickListener(v->startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
    }

    private void signIn()
    {
        String userEmail=binding.inputEmail.getText().toString();
        String password=binding.inputPassword.getText().toString();
        LoginRequestBody loginRequestBody=new LoginRequestBody();
        loginRequestBody.setUserEmail(userEmail);
        loginRequestBody.setPassword(password);

        userApi.getUser(loginRequestBody).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.code()==400)
                    Toast.makeText(SignInActivity.this, "Invalid email or password ! Try again..", Toast.LENGTH_SHORT).show();
                else
                {
                    User user=response.body();
                    Toast.makeText(SignInActivity.this, "Welcome back , "+user.getUsername(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),ChatActivity.class);
                    intent.putExtra("current_user_id",user.getUserId());
                    intent.putExtra("current_user_name",user.getUsername());
                    startActivity(intent);
                }

            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        binding.inputEmail.setText("");
        binding.inputPassword.setText("");
    }
}

