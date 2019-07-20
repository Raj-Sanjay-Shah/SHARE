package com.example.MRVC;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class next_post_login_page extends AppCompatActivity {
    String username;
    String selected_work;
    String selected_activity;
    String selected_quantity;
    String selected_lines;
    String selected_station;
    String selected_checkboxes;
    String locsend;
    Bitmap b = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_4444);
    TextView mReply;
    String login_url = "https://appdevpractice.000webhostapp.com//update.php";
    public static final String EXTRA_REPLY = "Activity Update Done";
    Context ctx = this;
    String result="";
    String file;
    ImageView i;
    final String encodedString = "data:image/jpg;base64, ....";
    final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.backStack.add(this);
        setContentView(R.layout.activity_next_post_login_page);
        Intent intent = getIntent();//gets the intent which activated this activity
        mReply = findViewById(R.id.textView6);
        username = intent.getExtras().getString(post_login_page.EXTRA_REPLY1);
        selected_work = intent.getExtras().getString(post_login_page.EXTRA_REPLY2);
        selected_activity = intent.getExtras().getString(post_login_page.EXTRA_REPLY3);
        selected_lines = intent.getExtras().getString(post_login_page.EXTRA_REPLY4);
        selected_station = intent.getExtras().getString(post_login_page.EXTRA_REPLY5);
        selected_quantity = intent.getExtras().getString(post_login_page.EXTRA_REPLY6);
        file=intent.getExtras().getString(post_login_page.EXTRA_REPLY7);
       // Bitmap b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra(post_login_page.EXTRA_REPLY7),0,getIntent().getByteArrayExtra(post_login_page.EXTRA_REPLY7).length);
       // b=loadImageFromStorage(file);
        selected_checkboxes = intent.getExtras().getString(post_login_page.EXTRA_REPLY8);
        locsend = intent.getExtras().getString(post_login_page.EXTRA_REPLY9);
b=loadImageFromStorage(file);
i=findViewById(R.id.imageViewa);
i.setImageBitmap(b);
        //this.upload();
result=selected_checkboxes;

       // mReply.setText("Status: Data successfully updated");
            this.uploadImage();


        CountDownTimer c = new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //mReply.setText("Status: Data successfully updated"+result);
            }
        };
        c.start();


    }

    public void logout(View view) {
        Intent replyIntent = new Intent(ctx, MainActivity.class);
        String reply = mReply.getText().toString();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);

        ctx.startActivity(replyIntent);
    }




    public void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

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
                Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
                mReply.setText("Status: Data successfully updated");
                //loading.dismiss();
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage1 = getStringImage(b);

                HashMap<String, String> data = new HashMap<>();
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
                    String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("selected_work", "UTF-8") + "=" + URLEncoder.encode(selected_work, "UTF-8") + "&" + URLEncoder.encode("selected_activity", "UTF-8") + "=" + URLEncoder.encode(selected_activity, "UTF-8") + "&" + URLEncoder.encode("selected_lines", "UTF-8") + "=" + URLEncoder.encode(selected_lines, "UTF-8") + "&" + URLEncoder.encode("selected_station", "UTF-8") + "=" + URLEncoder.encode(selected_station, "UTF-8") + "&" + URLEncoder.encode("selected_quantity", "UTF-8") + "=" + URLEncoder.encode(selected_quantity, "UTF-8") + "&" + URLEncoder.encode("selected_checkboxes", "UTF-8") + "=" + URLEncoder.encode(selected_checkboxes, "UTF-8") + "&" + URLEncoder.encode("locsend", "UTF-8") + "=" + URLEncoder.encode(locsend, "UTF-8")+"&"+URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(uploadImage1, "UTF-8");
                    bufferedWriter.write(post_data);
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
        ui.execute(b);

    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        i.setImageBitmap(bmp);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        //mReply.setText(encodedImage);
        return encodedImage;
    }
    public String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
    private Bitmap loadImageFromStorage(String path)
    {
        Bitmap x;
        try {
            File f=new File(path, "profile.jpg");
            x= BitmapFactory.decodeStream(new FileInputStream(f));
            return x;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

