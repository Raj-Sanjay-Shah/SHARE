package com.example.MRVC;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;
    public static final String EXTRA_MESSAGE = "com.example.MRVC.extra.MESSAGE";
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    private int mcount=0;
    TextView show;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username =findViewById(R.id.editText);
        username.requestFocus();
    }

    public void launch_post_login(View view)
    {   Log.d(LOG_TAG, "Button clicked!");

        password=findViewById(R.id.editText2);
        String user_check = username.getText().toString();
        String pass = password.getText().toString();
        String type="login";
        background_worker backgroundworker= new background_worker(this);
        backgroundworker.execute(type,user_check,pass);



    }

    /*public void showToast(View view) {

        mShowCount =findViewById(R.id.editText);
        mcount++;
        mShowCount.setText("Tries:"+Integer.toString(mcount));
    }*/
    public void reset(View view) {
        Toast toast_1=Toast.makeText(this,R.string.toast_message1,Toast.LENGTH_SHORT);
        toast_1.show();
        mcount=0;
        show=findViewById(R.id.editText2);
        username =findViewById(R.id.editText);
        show.setText("");
        username.setText("");
        username.setHint("Enter username");
    }

}
