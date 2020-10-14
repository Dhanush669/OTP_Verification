package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ReceiveOtp extends AppCompatActivity {
    EditText otpinput1,otpinput2,otpinput3,otpinput4,otpinput5,otpinput6;
    TextView number,resendotp;
    String verificationid,otp;
    CardView verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_otp);
        resendotp=findViewById(R.id.resendotp);
        number=findViewById(R.id.numberr);
        otpinput1=findViewById(R.id.otpno1);
        otpinput2=findViewById(R.id.otpno2);
        otpinput3=findViewById(R.id.otpno3);
        otpinput4=findViewById(R.id.otpno4);
        otpinput5=findViewById(R.id.otpno5);
        otpinput6=findViewById(R.id.otpno6);
        verify=findViewById(R.id.verofyotp);
        Intent intent=getIntent();
        number.setText(intent.getStringExtra("mobile"));
        verificationid=intent.getStringExtra("id");

        otpinput1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    otpinput2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        otpinput2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    otpinput3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        otpinput3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    otpinput4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        otpinput4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    otpinput5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        otpinput5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    otpinput6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         if(otpinput1.getText().toString().isEmpty()|| otpinput2.getText().toString().isEmpty()|| otpinput3.getText().toString().isEmpty()||
                 otpinput4.getText().toString().isEmpty()|| otpinput5.getText().toString().isEmpty()||otpinput6.getText().toString().isEmpty()){
             Toast.makeText(ReceiveOtp.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
             return;
         }
         

        if(verificationid!=null){
                Log.i("id",verificationid);
                otp=otpinput1.getText().toString()+otpinput2.getText().toString()+otpinput3.getText().toString()+
                        otpinput4.getText().toString()+ otpinput5.getText().toString()+
                        otpinput6.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, otp);
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(ReceiveOtp.this, "LoggedIn Successfully",
                                Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(getApplicationContext(),MainPage.class);
                        startActivity(intent1);
                        finish();
                    }
                });
        }

            }
        });



        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+number.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        ReceiveOtp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(ReceiveOtp.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            }
                        });

            }
        });
    }
}