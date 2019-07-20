package com.example.MRVC;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.StringTokenizer;

public class application_manager extends AppCompatActivity {
    String login_url = "https://appdevpractice.000webhostapp.com//getImage.php";
    String result;
    ImageView e;
    int delimiter_count;
    final String encodedString = "data:image/jpg;base64, ....";
    final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);


    String []username=new String[500];
    String []selected_work=new String[500];
    String []selected_activity=new String[500];
    String []selected_quantity=new String[500];
    String []selected_lines=new String[500];
    String []selected_station=new String[500];
    String []selected_checkboxes=new String[500];
    String []locsend=new String[500];
    String []dat=new String[500];
    public static final String EXTRA_MESSAGE = "com.example.MRVC.extra.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.backStack.add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_manager);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();//gets the intent which activated this activity
        e=findViewById(R.id.imageView4);
        uploadImage();


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_man_mode, menu);
        return true;
    }
    public void man_mode_method(MenuItem item) {
        Intent man_Intent = new Intent(this, man_mode.class);
        //setResult(RESULT_OK,man_Intent);

        startActivity(man_Intent);
    }
    public void user_layout(MenuItem item) {
        /*Intent man_Intent = new Intent(this, post_login_page.class);
        //setResult(RESULT_OK,man_Intent);
        man_Intent.putExtra(EXTRA_MESSAGE,"man_mode");
        startActivity(man_Intent);
        Intent intent = new Intent(this, Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }
    public void databasemode(MenuItem item){;}
    public void uploadImage() {
        class UploadImage extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;
            //RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(MainActivity.this, "Uploading Image", "Please wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                String login;
                String message = result;
                   delimiter_count=0;
                    StringTokenizer strok=new StringTokenizer(message);
                   login=strok.nextToken("~");
                    while(strok.hasMoreTokens())
                    {   String temp=strok.nextToken("~");
                        StringTokenizer strtok=new StringTokenizer(temp);
                        username[delimiter_count]=strtok.nextToken("*");
                        if(strtok.hasMoreTokens()){
                            selected_work[delimiter_count]=strtok.nextToken("*");
                            selected_activity[delimiter_count]=strtok.nextToken("*");
                            selected_lines[delimiter_count]=strtok.nextToken("*");
                            selected_station[delimiter_count]=strtok.nextToken("*");

                            selected_quantity[delimiter_count]=strtok.nextToken("*");
                            selected_checkboxes[delimiter_count]=strtok.nextToken("*");
                            locsend[delimiter_count]=strtok.nextToken();
                            dat[delimiter_count]=strtok.nextToken();
                            delimiter_count++;}
                    }

                //final byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                byte[] decodedString = Base64.decode(login, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                e.setImageBitmap(decodedByte);
                TextView t=findViewById(R.id.Man_mode_text);
                t.setText("Latest Update1:"+username[0]+"-"+selected_work[0]+"-"+selected_activity[0]+"-"+selected_lines[0]+"-"+selected_station[0]+"-"+selected_checkboxes[0]+"-"+selected_quantity[0]+"-"+locsend[0]+"-"+dat[0]);
                TextView t1=findViewById(R.id.man_mode_text1);
                t1.setText("Latest Update2:"+username[1]+"-"+selected_work[1]+"-"+selected_activity[1]+"-"+selected_lines[1]+"-"+selected_station[1]+"-"+selected_checkboxes[1]+"-"+selected_quantity[1]+"-"+locsend[1]+"-"+dat[1]);
                TextView t2=findViewById(R.id.man_mode_text);
                t2.setText(username[2]+"-"+selected_work[2]+"-"+selected_activity[2]+"-"+selected_lines[2]+"-"+selected_station[2]+"-"+selected_checkboxes[2]+"-"+selected_quantity[2]+"-"+locsend[2]+"-"+dat[2]);

                TextView t3=findViewById(R.id.man_mode_text2);
                t3.setText(username[3]+"-"+selected_work[3]+"-"+selected_activity[3]+"-"+selected_lines[3]+"-"+selected_station[3]+"-"+selected_checkboxes[3]+"-"+selected_quantity[3]+"-"+locsend[3]+"-"+dat[3]);




                //loading.dismiss();
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                            //data.put(EXTRA_REPLY, uploadImage);

                try {
                    //String arr=getPostDataString(data);
                    URL url = new URL(login_url);
                    HttpURLConnection htconn = (HttpURLConnection) url.openConnection();
                    htconn.setRequestMethod("POST");
                    htconn.setDoInput(true);
                    htconn.setDoOutput(true);
                    OutputStream os = htconn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    //String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("selected_work", "UTF-8") + "=" + URLEncoder.encode(selected_work, "UTF-8") + "&" + URLEncoder.encode("selected_activity", "UTF-8") + "=" + URLEncoder.encode(selected_activity, "UTF-8") + "&" + URLEncoder.encode("selected_lines", "UTF-8") + "=" + URLEncoder.encode(selected_lines, "UTF-8") + "&" + URLEncoder.encode("selected_station", "UTF-8") + "=" + URLEncoder.encode(selected_station, "UTF-8") + "&" + URLEncoder.encode("selected_quantity", "UTF-8") + "=" + URLEncoder.encode(selected_quantity, "UTF-8") + "&" + URLEncoder.encode("selected_checkboxes", "UTF-8") + "=" + URLEncoder.encode(selected_checkboxes, "UTF-8") + "&" + URLEncoder.encode("locsend", "UTF-8") + "=" + URLEncoder.encode(locsend, "UTF-8")+"&"+URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(uploadImage1, "UTF-8");
                    //bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream inputStream = htconn.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                        result = result + "\n";
                    }
                    bufferedReader.close();
                    inputStream.close();
                    htconn.disconnect();

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
        }
        UploadImage ui = new UploadImage();
        ui.execute();

    }
}
