package com.chat.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chat.app.adapter.ChatAdapter;
import com.chat.app.databinding.ActivityChatBinding;
import com.chat.app.helper.MessageRequest;
import com.chat.app.models.Message;
import com.chat.app.models.User;
import com.chat.app.retrofit.ChatApi;
import com.chat.app.retrofit.RetrofitService;
import com.chat.app.retrofit.UserApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private RecyclerView recyclerView;

    private List<Message> messageList;

    private RetrofitService retrofitService;

    private UserApi userApi;
    private ChatAdapter chatAdapter;
    private User currentUser;
    private String currentUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView=binding.chatRecyclerView;
        messageList=new ArrayList<>();
//binding.sakhaWelcomeMessage
        chatAdapter=new ChatAdapter(messageList);
        recyclerView.setAdapter(chatAdapter);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setStackFromEnd(true);

        recyclerView.setLayoutManager(llm);
        Intent intent=getIntent();
        int currentUserId=intent.getIntExtra("current_user_id",0);
        currentUsername=intent.getStringExtra("current_user_name");
        binding.textName.setText(currentUsername);
        retrofitService=new RetrofitService();
        userApi= retrofitService.getRetrofit().create(UserApi.class);
        currentUser=getCurrentUser(currentUserId);
        // setting retrofit for getting response
        binding.sendButton.setOnClickListener(v->{
            String question=binding.userMessage.getText().toString().trim();
            binding.userMessage.setText("");
            binding.sakhaWelcomeMessage.setVisibility(View.GONE);
            Message message=new Message(question,1);
            addToChat(message);
            getResponse(question,currentUserId);
        });


        binding.imageBack.setOnClickListener(v->onBackPressed());
        // logout
        binding.imageLogout.setOnClickListener(v->logout());
    }
    private void addToChat(Message message)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(message);
                chatAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(chatAdapter.getItemCount());
            }
        });
    }
    private void getResponse(String question,int currentUserId)
    {
        //send question to chatbot;
        // set userId using sharedPreferences;
        MessageRequest messageRequest=new MessageRequest(currentUserId,question);
        final Message[] receivedMessage = {new Message()};
        userApi.getResponseFromSakha(messageRequest).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
//                receivedMessage[0] =response.body();
                addToChat(response.body());
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                addToChat(new Message("Service not available",2));
            }
        });
    }
    private User getCurrentUser(int userId)
    {
        final User[] user = {null};
        userApi.getCurrentUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user[0] =response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                user[0] =null;
            }
        });
        return user[0];
    }

    private void logout()
    {
        Intent intent=getIntent();
        intent.removeExtra("current_user_id");
        intent.removeExtra("current_user_name");
        Intent nextIntent=new Intent(getApplicationContext(),SignInActivity.class);
        Toast.makeText(this, "Good bye ,"+currentUsername+"! We hope you enjoyed, Come back soon ! ", Toast.LENGTH_SHORT).show();
        startActivity(nextIntent);
    }
}