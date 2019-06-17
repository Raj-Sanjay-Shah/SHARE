package com.example.MRVC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class man_mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_mode);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();//gets the intent which activated this activity
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_man_mode, menu);
        return true;
    }
    public void databasemode(MenuItem item) {
        Intent man_Intent = new Intent(this, application_manager.class);
        startActivity(man_Intent);
    }
    public void user_layout(MenuItem item) {
        Intent man_Intent = new Intent(this, post_login_page.class);
        //setResult(RESULT_OK,man_Intent);

        startActivity(man_Intent);
    }
    public void man_mode_method(MenuItem item)
    {
        ;
    }

}
