package com.example.MRVC;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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
import java.net.URLEncoder;


public class background_worker extends AsyncTask<String,String,String> {
    Context context;
    public static final String EXTRA_MESSAGE = "com.example.MRVC.extra.MESSAGE";
    String user1;
    background_worker(Context ctx)
    {
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type= params[0];
        String user= params[1];
        String pass= params[2];
        user1=user;
        String login_url= "https://appdevpractice.000webhostapp.com//login.php";
        if(type.equals("login"))
        {
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
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,"Welcome", Toast.LENGTH_LONG).show();
        if(result.indexOf("login success")>0) {

            Intent intent = new Intent(context, post_login_page.class);
            intent.putExtra(EXTRA_MESSAGE, result);
            context.startActivity(intent);
        }
        else
        {
            Toast.makeText(context, "Wrong Username or Password!", Toast.LENGTH_SHORT).show();
        }
    }

}
