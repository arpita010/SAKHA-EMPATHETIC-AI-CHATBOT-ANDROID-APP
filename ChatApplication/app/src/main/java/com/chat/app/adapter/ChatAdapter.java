package com.chat.app.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.chat.app.R;
import com.chat.app.databinding.ActivityChatBinding;
import com.chat.app.models.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Message> messageList;

    public ChatAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder
    {
        ConstraintLayout leftChatView,rightChatView;
        TextView leftChatTextView,rightChatTextView;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            leftChatView=itemView.findViewById(R.id.leftChatView);
            rightChatView=itemView.findViewById(R.id.rightChatView);
            leftChatTextView=itemView.findViewById(R.id.leftChatTextView);
            rightChatTextView=itemView.findViewById(R.id.rightChatTextView);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView=LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,null);
        ChatViewHolder chatViewHolder=new ChatViewHolder(chatView);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatViewHolder chatViewHolder=(ChatViewHolder) holder;
        Message message=messageList.get(position);
//        Log.d("receivedMesage",message.getMessage());
        if(message.getSent_by()==1)
        {
            chatViewHolder.leftChatView.setVisibility(View.GONE);
            chatViewHolder.rightChatView.setVisibility(View.VISIBLE);
            chatViewHolder.rightChatTextView.setText(message.getMessage());
        }else
        {
            chatViewHolder.rightChatView.setVisibility(View.GONE);
            chatViewHolder.leftChatView.setVisibility(View.VISIBLE);
            chatViewHolder.leftChatTextView.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
