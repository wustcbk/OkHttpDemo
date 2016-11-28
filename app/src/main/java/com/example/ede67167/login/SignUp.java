package com.example.ede67167.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ede67167.R;
import com.example.ede67167.MainActivity;
import com.example.ede67167.okhttpdemo.okhttpUnity;
import com.example.ede67167.userDB.Userdata;

public class SignUp extends AppCompatActivity {
    private Button mSignUpBtn;
    private EditText mUserIdView;
    private EditText mPassWordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mUserIdView =(EditText) findViewById(R.id.signup_userId_entry);
        mPassWordView = (EditText)findViewById(R.id.signup_pwd_entry);
        mSignUpBtn=(Button)findViewById(R.id.sign_up_btn);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userdata userBaseInfo=new Userdata();
                userBaseInfo.setUserId(mUserIdView.getText().toString());
                userBaseInfo.setPassword(mPassWordView.getText().toString());
                UrlAction urlAction=new UrlAction();
                urlAction.Register(userBaseInfo, new okhttpUnity.RequestInterface<String>() {
                            @Override
                            public void onFailure(String errorMessage) {
                                mUserIdView.setError(errorMessage);
                                mUserIdView.requestFocus();
                            }

                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success"))
                                {

                                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(intent);

                                }
                                else
                                {
                                    mUserIdView.setError("Have exist the userID!");
                                    mUserIdView.requestFocus();
                                }
                            }
                        }
                );

            }
        });
    }
}
