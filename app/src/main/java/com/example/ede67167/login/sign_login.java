package com.example.ede67167.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ede67167.R;
import com.example.ede67167.MainActivity;
import com.example.ede67167.okhttpdemo.okhttpUnity;

public class sign_login extends AppCompatActivity {
    private EditText mUserIdView;
    private EditText mPassWordView;
    private Button mSignInBtn;
    private TextView mSignUpTxt;
    private UrlAction mUrlAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_login);
        mUrlAction=new UrlAction();
        mUserIdView =(EditText) findViewById(R.id.id_entry);
        mPassWordView = (EditText)findViewById(R.id.password_entry);
        mSignUpTxt=(TextView)findViewById(R.id.sign_up);
        mSignUpTxt.setOnClickListener( new View.OnClickListener(){
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(sign_login.this, SignUp.class);
                                               startActivity(intent);
                                           }
                                       });
        mSignInBtn=(Button)findViewById(R.id.sign_in);
        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AttempLogin();


            }
        });

        mPassWordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP)
                {//enter key,and up
                   AttempLogin();
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }
    void AttempLogin()
    {
        String userID;
        String password;
        boolean cancel = false;
        View focusView = null;
        userID=mUserIdView.getText().toString();
        password=mPassWordView.getText().toString();

        mUserIdView.setError(null);
        mPassWordView.setError(null);
        if (TextUtils.isEmpty(password))
        {
            mPassWordView.setError("Please input your password");
            focusView=mPassWordView;
        }
        /*
        else if(password.length()<6){

            mPassWordView.setError("Your password not correct");
            focusView=mPassWordView;
        }
        */
        else if (TextUtils.isEmpty(userID)) {
            mUserIdView.setError("Please input your ID");
            focusView=mUserIdView;
        }
        if(focusView!=null)
        {
            focusView.requestFocus();
        }
        else
        {
            mUrlAction.LogIn(userID,password,new okhttpUnity.RequestInterface<String>(){

                @Override
                public void onFailure(String errorMessage) {
                    mUserIdView.requestFocus();
                    mUserIdView.setError(errorMessage);
                }

                @Override
                public void onResponse(String response) {
                      if(response.equals("success"))
                      {
                          Intent intent = new Intent(sign_login.this, MainActivity.class);
                          startActivity(intent);
                      }
                      else
                      {
                          mUserIdView.requestFocus();
                          mUserIdView.setError("Your userID or password not correct");
                      }
                }
            });
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
