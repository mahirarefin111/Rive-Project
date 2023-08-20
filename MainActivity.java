package com.edutock.anim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.startup.AppInitializer;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.edutock.anim.databinding.ActivityMainBinding;

import app.rive.runtime.kotlin.RiveInitializer;
import app.rive.runtime.kotlin.core.Rive;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private String stateMachineName = "Login Machine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Rive.INSTANCE.init(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Setting an onFocusChangeListener for the Email EditText
        binding.Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // Code to be executed when the EditText gains focus
                    binding.loginCracter.setBooleanState(stateMachineName, "isChecking", true);
                } else {
                    // Code to be executed when the EditText loses focus
                    binding.loginCracter.setBooleanState(stateMachineName, "isChecking", false);
                }
            }
        });

        binding.Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // Code to be executed when the EditText gains focus
                    binding.loginCracter.setBooleanState(stateMachineName, "isHandsUp", true);
                } else {
                    // Code to be executed when the EditText loses focus
                    binding.loginCracter.setBooleanState(stateMachineName, "isHandsUp", false);
                }
            }
        });



        binding.Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Called before text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Called when text is being changed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Called after text has been changed
                String stateMachineName = "Login Machine"; // Set your state machine name here

                String text = s.toString(); // Get the text from the Editable

                try {
                    binding.loginCracter.getController().setNumberState(stateMachineName, "numLook", (float) text.length());
                }catch (Exception e){

                }
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.Password.clearFocus();

                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String email = binding.Email.getText().toString();
                        String password = binding.Password.getText().toString();

                        if (!email.isEmpty() && !password.isEmpty() && email.equals("admin@gmail.com") && password.equals("12345678")) {
                            binding.loginCracter.getController().fireState(stateMachineName, "trigSuccess");
                        } else {
                            binding.loginCracter.getController().fireState(stateMachineName, "trigFail");
                        }
                    }
                }, 2000);
            }
        });


    }
}