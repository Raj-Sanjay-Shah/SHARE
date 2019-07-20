package com.example.MRVC;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class man_mode extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.backStack.add(this);
        setContentView(R.layout.activity_man_mode);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();//gets the intent which activated this activity

        Spinner spin=findViewById(R.id.spinner2);
        if (spin != null) {
            spin.setOnItemSelectedListener(this);
        }
        List<String> myArraySpin = new ArrayList<String>();
        myArraySpin.add("Id:1-Leena:MSTP:Concrete");
        myArraySpin.add("Id:2-Leena:FOB:Bracket");
        ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,myArraySpin);
        adapter1.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        if (spin != null) {
            spin.setAdapter(adapter1);
        }
        Spinner spin1=findViewById(R.id.spinner7);
        if (spin1 != null) {
            spin1.setOnItemSelectedListener(this);
        }
        List<String> myArraySpin1 = new ArrayList<String>();
        myArraySpin1.add("Central Line-Thane, Diva, Vashind");
        myArraySpin1.add("Western Line-Goregaon, Dahanu");
        ArrayAdapter<String> adapter12 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,myArraySpin1);
        adapter12.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        if (spin1 != null) {
            spin1.setAdapter(adapter12);
        }
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
    public void gonext(View view) {
        String s="Alloted";

        CountDownTimer c = new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                TextView m=findViewById(R.id.Man_mode_text);
                m.setText("Successfully updated");
                //mReply.setText("Status: Data successfully updated"+result);
            }
        };
        c.start();

        return;
        //user= (String) mReply.getText();

        //while(check_location_set!=true){

      /* String login_url= "https://appdevpractice.000webhostapp.com//login.php";

            try {
                URL url=new URL(login_url);
                HttpURLConnection htconn= (HttpURLConnection) url.openConnection();
                htconn.setRequestMethod("POST");
                htconn.setDoInput(true);
                htconn.setDoOutput(true);
                OutputStream os=htconn.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8") ;
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=htconn.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    result+=line;
                    result=result+"\n";
                }
                bufferedReader.close();
                inputStream.close();
                htconn.disconnect();
                //return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }








        //}
        Intent intent = new Intent(this, next_post_login_page.class);
        //intent.putExtra(EXTRA_REPLY1, username);
        this.startActivity(intent);*/
    }
    public void logout(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(ctx);
        myAlertBuilder.setTitle(R.string.alert_title);
        myAlertBuilder.setMessage(R.string.alert_message);
        myAlertBuilder.setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked OK button.
                        // ... Action to take when OK is clicked
                        Intent replyIntent = new Intent(ctx, MainActivity.class);
                       // String reply = mReply.getText().toString();
                        //replyIntent.putExtra(EXTRA_REPLY, reply);
                        setResult(RESULT_OK, replyIntent);
                        ctx.startActivity(replyIntent);
                    }
                });
        myAlertBuilder.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked the CANCEL button.
                        // ... Action to take when CANCEL is clicked.
                    }
                });
        AlertDialog alertDialog = myAlertBuilder.create();
        alertDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.spinner2)
        {
            if (position == 0) {
                List<String> myArraySpinner1 = new ArrayList<String>();
                myArraySpinner1.add("Concrete for foundations & plinth in other than hard soil & rocky soil.");
                myArraySpinner1.add("Concrete for foundations & plinth in hard soil and rocky soil.");
                myArraySpinner1.add("Reinforced concrete for foundation & plinth including SWS cable trench.");
                Spinner spinner = findViewById(R.id.spinner6);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner1);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            if (position == 1) {
                //List<String> myArraySpinner1 = new ArrayList<String>();
                List<String> myArraySpinner1 = new ArrayList<String>();
                myArraySpinner1.add("Supply & erection of pull-off arrangement for one OHE(conventional)");
                Spinner spinner = findViewById(R.id.spinner6);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner1);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
        }}

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
