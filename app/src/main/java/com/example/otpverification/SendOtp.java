package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOtp extends AppCompatActivity {
    EditText mobileno;
    CardView getotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mobileno=findViewById(R.id.mobileno);
        getotp=findViewById(R.id.getotp);
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOtp();
            }
        });
    }
    public void getOtp(){
        if(mobileno.getText().toString().isEmpty()){
            Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+mobileno.getText().toString(),
                60,
                TimeUnit.SECONDS,
                SendOtp.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(SendOtp.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificaitonid,
                                           @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        Intent intent=new Intent(getApplicationContext(),ReceiveOtp.class);
                        intent.putExtra("mobile",mobileno.getText().toString());
                        intent.putExtra("id",verificaitonid);
                        startActivity(intent);
                    }
                });


    }
}