package com.chat.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chat.app.R;
import com.chat.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}