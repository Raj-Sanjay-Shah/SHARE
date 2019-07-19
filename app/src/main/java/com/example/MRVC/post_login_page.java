package com.example.MRVC;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class post_login_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String locsend;
    protected LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
            try {
                List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (list != null && list.size() > 0) {
                    Address address = list.get(0);
                    // sending back first address line and locality
                    String result = address.getAddressLine(0) + ", " + address.getLocality();
                    locsend=result;
                    location1.setText("Current Location: " + location.getLatitude() + ", " + location.getLongitude()+result);
                }

                } catch (IOException e) {
                e.printStackTrace();
            }


            int t = 0;
            while (t < 10000)
                t++;
            check_location_set=true;
            locationManager.removeUpdates(locationListener);
            }

            @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    File file;
    boolean check = false;
    Context ctx = this;
    TextView location1;
    public static final String EXTRA_REPLY =
            "com.example.MRVC.extra.REPLY";

    public static final String EXTRA_REPLY1 =
            "com.example.MRVC.extra.REPLY1";
    public static final String EXTRA_REPLY2 =
            "com.example.MRVC.extra.REPLY2";
    public static final String EXTRA_REPLY3 =
            "com.example.MRVC.extra.REPLY3";
    public static final String EXTRA_REPLY4 =
            "com.example.MRVC.extra.REPLY4";
    public static final String EXTRA_REPLY5 =
            "com.example.MRVC.extra.REPLY5";
    public static final String EXTRA_REPLY6 =
            "com.example.MRVC.extra.REPLY6";

    public static final String EXTRA_REPLY7 =
            "com.example.MRVC.extra.REPLY7";
    public static final String EXTRA_REPLY8 =
            "com.example.MRVC.extra.REPLY8";
    public static final String EXTRA_REPLY9 =
            "com.example.MRVC.extra.REPLY9";
    private TextView mReply;
    private static final int result_load_image = 1;
    private static final int CAMERA_TAKE_REQUEST=2;
    ImageView selected_img;
    String user;
    LocationManager locationManager;
    // boolean checkers below
    boolean check_work_set=false;
    boolean check_image_set=false;
    boolean check_boxes_tick=false;
    boolean check_location_set=false;
    //booleans checkers over
    int delimiter_count=0;
    String[]  total_id=new String[500];
    String[]  Work_name=new String[500];
    String[]  Generic_name=new String[500];
    String[]  activity_name=new String[500];
    String[] quantity=new String[500];
    String username;
    String selected_work;
    String selected_activity;
    String selected_quantity;
    String selected_lines;
    String selected_station;
    String selected_checkboxes="Data: ";
    List<String> myArraySpinner1 = new ArrayList<String>();
    List<String> myArraySpinner2 = new ArrayList<String>();
    String quantity_tot="";
    List<String> myArraySpin1 = new ArrayList<String>();
    List<String> myArraySpin = new ArrayList<String>();
    Bitmap b;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.backStack.add(this);
        setContentView(R.layout.activity_post_login_page);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mReply = findViewById(R.id.textView21);
        check = false;
        Intent intent = getIntent();//gets the intent which activated this activity


        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if(intent!=null&& !message.equals("man_mode")){
            {   delimiter_count=0;
            StringTokenizer strok=new StringTokenizer(message);
            String login=strok.nextToken();
            while(strok.hasMoreTokens())
            {   String temp=strok.nextToken("~");
                StringTokenizer strtok=new StringTokenizer(temp);
                username=strtok.nextToken("*");
                if(strtok.hasMoreTokens()){
                total_id[delimiter_count]=strtok.nextToken("*");
                Work_name[delimiter_count]=strtok.nextToken("*");
                Generic_name[delimiter_count]=strtok.nextToken("*");
                activity_name[delimiter_count]=strtok.nextToken("*");

                quantity[delimiter_count]=strtok.nextToken("*");
                delimiter_count++;}
            }
        }
        }

        mReply.setText("welcome:"+username);


        location1 = findViewById(R.id.textView21);
        Spinner spin=findViewById(R.id.spinner4);
        if (spin != null) {
            spin.setOnItemSelectedListener(this);
        }

        myArraySpin1.add("Central");
        myArraySpin1.add("Western");
        ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,myArraySpin1);
        adapter1.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        if (spin != null) {
            spin.setAdapter(adapter1);
        }

        Spinner spinner = findViewById(R.id.spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        Spinner spinner1 = findViewById(R.id.spinner1);
        if (spinner1 != null) {
            spinner1.setOnItemSelectedListener(this);
        }
        List<String> myArraySpinner = new ArrayList<String>();
        int i=0;
        myArraySpinner.add(Work_name[0]);
        while(i<delimiter_count)
        {   int a=0;
        int check=0;
            while(a<myArraySpinner.size())
            {if(myArraySpinner.get(a).equals(Work_name[i]))
                check=1;

                a++;
            }
            if(check==0)
                myArraySpinner.add(Work_name[i]);
            i++;
        }

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,myArraySpinner);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void logo_click(View view) {
        String url = "https://mrvc.indianrailways.gov.in/";
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void logout(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(post_login_page.this);
        myAlertBuilder.setTitle(R.string.alert_title);
        myAlertBuilder.setMessage(R.string.alert_message);
        myAlertBuilder.setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked OK button.
                        // ... Action to take when OK is clicked
                        Intent replyIntent = new Intent(ctx, MainActivity.class);
                        String reply = mReply.getText().toString();
                        replyIntent.putExtra(EXTRA_REPLY, reply);
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

    public void openLocation(View view) {
        check = true;
       /* MenuItem m=Menu.findItem(R.id.app_bar_search);
        m.setVisible(true);
        m=(MenuItem)findViewById(R.id.action_settings);
        m.setVisible(true);*/
        String loc = "my location";
        getLocation();
        /*Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    //check=true;

        if(parent.getId()==R.id.spinner) {
            myArraySpinner1.clear();
            selected_work=Work_name[position];
            if (position == 0) {

                //List<String> myArraySpinner1 = new ArrayList<String>();
                int i = 0;
                int a=0;
                //myArraySpinner1.add(Generic_name[0]);
                while (i < delimiter_count) {
                    if (Work_name[position].equals(Work_name[i]))
                    {if(myArraySpinner1.isEmpty())
                            myArraySpinner1.add(Generic_name[i]);
                    a=0;
                        while(!myArraySpinner1.get(a).equals(Generic_name[i])) {
                            a++;
                            myArraySpinner1.add(Generic_name[i]);
                        }}i++;
                }
                Spinner spinner = findViewById(R.id.spinner1);
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
                int i = 0;
                int a=0;
                //myArraySpinner1.add(Generic_name[0]);

                while (i < delimiter_count) {
                    if (Work_name[position].equals(Work_name[i]))
                    {if(myArraySpinner1.isEmpty())
                            myArraySpinner1.add(Generic_name[i]);
                    a=0;
                    while(!myArraySpinner1.get(a).equals(Generic_name[i])) {
                        a++;
                        myArraySpinner1.add(Generic_name[i]);
                    }}
                    i++;
                }
                Spinner spinner = findViewById(R.id.spinner1);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner1);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            if (position == 2) {
                //List<String> myArraySpinner1 = new ArrayList<String>();
                int i = 0;
                int a=0;
               // myArraySpinner1.add(Generic_name[0]);
                while (i < delimiter_count) {
                    if (Work_name[position].equals(Work_name[i])){
                        if(myArraySpinner1.isEmpty())
                            myArraySpinner1.add(Generic_name[i]);
                        a=0;
                        while(!myArraySpinner1.get(a).equals(Generic_name[i])) {
                            a++;
                            myArraySpinner1.add(Generic_name[i]);
                        }}
                    i++;
                }
                Spinner spinner = findViewById(R.id.spinner1);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner1);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            if (position == 3) {
                //List<String> myArraySpinner1 = new ArrayList<String>();
                int i = 0;
                int a=0;
                myArraySpinner1.add(Generic_name[0]);
                while (i < delimiter_count) {
                    if (Work_name[position].equals(Work_name[i])) {
                        if (myArraySpinner1.isEmpty())
                            myArraySpinner1.add(Generic_name[i]);
                        a=0;
                        while (!myArraySpinner1.get(a).equals(Generic_name[i])) {
                            a++;
                            myArraySpinner1.add(Generic_name[i]);
                        }
                    }i++;
                }
                Spinner spinner = findViewById(R.id.spinner1);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner1);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            Spinner spinner1 = findViewById(R.id.spinner1);
            if (spinner1 != null) {
                spinner1.setOnItemSelectedListener(this);
            }
        }
        if(parent.getId()==R.id.spinner1) {//mReply.setText(activity_name[0]);
            myArraySpinner2.clear();
            if (position == 0) {
                //List<String> myArraySpinner2 = new ArrayList<String>();
                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner1.get(position).equals(Generic_name[i])) {
                        int a = 0;
                        int check = 0;
                        while (a < myArraySpinner2.size()) {
                            if (myArraySpinner2.get(a).equals(activity_name[i]))
                                check = 1;

                            a++;
                        }
                        if (check == 0)
                            myArraySpinner2.add(activity_name[i]);

                    }i++;
                }
                Spinner spinner = findViewById(R.id.spinner3);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner2);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            if (position == 1) {
                //List<String> myArraySpinner2 = new ArrayList<String>();
                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner1.get(position).equals(Generic_name[i])) {
                        int a = 0;
                        int check = 0;
                        while (a < myArraySpinner2.size()) {
                            if (myArraySpinner2.get(a).equals(activity_name[i]))
                                check = 1;

                            a++;
                        }
                        if (check == 0)
                            myArraySpinner2.add(activity_name[i]);

                    }i++;
                }
                Spinner spinner = findViewById(R.id.spinner3);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner2);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            if (position == 2) {
                //List<String> myArraySpinner2 = new ArrayList<String>();
                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner1.get(position).equals(Generic_name[i])) {
                        int a = 0;
                        int check = 0;
                        while (a < myArraySpinner2.size()) {
                            if (myArraySpinner2.get(a).equals(activity_name[i]))
                                check = 1;

                            a++;
                        }
                        if (check == 0)
                            myArraySpinner2.add(activity_name[i]);

                    }i++;
                }
                Spinner spinner = findViewById(R.id.spinner3);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner2);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            if (position == 3) {
                //List<String> myArraySpinner2 = new ArrayList<String>();
                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner1.get(position).equals(Generic_name[i])) {
                        int a = 0;
                        int check = 0;
                        while (a < myArraySpinner2.size()) {
                            if (myArraySpinner2.get(a).equals(activity_name[i]))
                                check = 1;

                            a++;
                        }
                        if (check == 0)
                            myArraySpinner2.add(activity_name[i]);
                    }
                        i++;
                      //  myArraySpinner2.add(activity_name[i]);
                    i++;
                }
                Spinner spinner = findViewById(R.id.spinner3);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner2);
                adapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spinner != null) {
                    spinner.setAdapter(adapter);
                }
            }
            Spinner spinner1 = findViewById(R.id.spinner3);
            if (spinner1 != null) {
                spinner1.setOnItemSelectedListener(this);
            }
        }
        if(parent.getId()==R.id.spinner3) {//mReply.setText(activity_name[0]);
            selected_activity=myArraySpinner2.get(position);
            if (position == 0) {

                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner2.get(position).equals(activity_name[i]))
                    quantity_tot=quantity[i];
                i++;
                }

            }
            if (position == 1) {
                //List<String> myArraySpinner2 = new ArrayList<String>();
                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner2.get(position).equals(activity_name[i]))
                        quantity_tot=quantity[i];
                    i++;
                }

            }
            if (position == 2) {
                //List<String> myArraySpinner2 = new ArrayList<String>();
                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner2.get(position).equals(activity_name[i]))
                        quantity_tot=quantity[i];
                    i++;
                }

            }
            if (position == 3) {
                //List<String> myArraySpinner2 = new ArrayList<String>();
                int i = 0;
                while (i < delimiter_count) {
                    if (myArraySpinner2.get(position).equals(activity_name[i]))
                        quantity_tot=quantity[i];
                    i++;
                }

            }
            TextView q=findViewById(R.id.textView2);
            q.setText(quantity_tot);


        }
        if(parent.getId()==R.id.spinner4) {//mReply.setText(activity_name[0]);
            //myArraySpinner2.clear();
            selected_lines=myArraySpin1.get(position);
            if (position == 0)
            {
                Spinner spin=findViewById(R.id.spinner5);
                if (spin != null) {
                    spin.setOnItemSelectedListener(this);
                }myArraySpin.clear();
                //List<String> myArraySpin = new ArrayList<String>();
                myArraySpin.add("Thane");
                myArraySpin.add("Diva");
                myArraySpin.add("Kalva");
                myArraySpin.add("Mulund");
                myArraySpin.add("Nahur");
                myArraySpin.add("Atgaon");
                myArraySpin.add("Thakurli");
                myArraySpin.add("Ambarnath");
                myArraySpin.add("Atgaon");
                myArraySpin.add("Kasara");
                myArraySpin.add("Govandi");
                myArraySpin.add("Neral");
                myArraySpin.add("Other");
                ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,myArraySpin);
                adapter1.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spin != null) {
                    spin.setAdapter(adapter1);
                }
            }
            if (position == 1)
            {
                Spinner spin=findViewById(R.id.spinner5);
                if (spin != null) {
                    spin.setOnItemSelectedListener(this);
                }myArraySpin.clear();
                //List<String> myArraySpin = new ArrayList<String>();
                myArraySpin.add("Churchgate");
                myArraySpin.add("Parel");
                myArraySpin.add("Andheri");
                myArraySpin.add("Dadar");
                myArraySpin.add("Goregaon");
                myArraySpin.add("Mira Road");
                myArraySpin.add("Dahisar");
                myArraySpin.add("Borivali");
                myArraySpin.add("Malad");
                myArraySpin.add("Kandivali");
                myArraySpin.add("Dahanu Road");
                myArraySpin.add("Nalasopara");
                myArraySpin.add("Other");
                ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,myArraySpin);
                adapter1.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
                if (spin != null) {
                    spin.setAdapter(adapter1);
                }
            }
        }
        if(parent.getId()==R.id.spinner5)
        {

            selected_station=myArraySpin.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_mail, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                man_mode(item);
                return true;
            case R.id.app_bar_search:
                databasemode(item);
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }
    public void get_image(View view)
    {   check=true;
       openLocation(view);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Alert!")
                .setMessage("Choose image from Gallery or Camera?")
                .setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent gallery_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(gallery_intent,result_load_image);

                    }
                })
                .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Uri uri;
                        Intent camera_intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        /*file = new File(Environment.getExternalStorageDirectory(), String.valueOf(System.currentTimeMillis()) + ".jpg");
                        uri = FileProvider.getUriForFile(ctx, ctx.getApplicationContext().getPackageName() + ".provider", file);
                        */
                        startActivityForResult(camera_intent, CAMERA_TAKE_REQUEST);


                    }
                });

        dialog.show();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == result_load_image) {
            if(resultCode == Activity.RESULT_OK && data!=null){
                Uri contentURI = data.getData();
                try {selected_img=findViewById(R.id.imageView4);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    selected_img.setImageBitmap(bitmap);
                   // filename=createImageFromBitmap(bitmap);
                    b=bitmap;
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(post_login_page.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
                }


        }
        else if(requestCode==CAMERA_TAKE_REQUEST){
            if(resultCode == Activity.RESULT_OK && data!=null) {
                selected_img=findViewById(R.id.imageView4);
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                selected_img.setImageBitmap(bitmap);
                b=bitmap;
               //filename=createImageFromBitmap(bitmap);
            }
        }
        return;

    }

    public void man_mode(MenuItem item) {
        if(!username.equals("Mr. D B Patil"))
        {
            Toast.makeText(this,"You do not have access",Toast.LENGTH_SHORT).show();
            return;

        }
        if (check == false) {
            Intent man_Intent = new Intent(this, man_mode.class);
            String reply = mReply.getText().toString();
            man_Intent.putExtra(EXTRA_REPLY1, reply);
            //setResult(RESULT_OK,man_Intent);

            startActivity(man_Intent);
        } else{
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Alert!")
                    .setMessage("Any unsaved data will be lost")
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            Intent man1_Intent = new Intent(ctx, man_mode.class);
                            String reply = mReply.getText().toString();
                            man1_Intent.putExtra(EXTRA_REPLY1, reply);
                            //setResult(RESULT_OK,man_Intent);

                            startActivity(man1_Intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        }
                    });

            dialog.show();
        }
    }

    public void databasemode(MenuItem item) {
        if(!username.equals("Mr. D B Patil"))
        {
            Toast.makeText(this,"You do not have access",Toast.LENGTH_SHORT).show();
            return;

        }
        if(check==false)
        {
            Intent man1_Intent = new Intent(this, application_manager.class);
        String reply = mReply.getText().toString();
        man1_Intent.putExtra(EXTRA_REPLY1, reply);
        //setResult(RESULT_OK,man_Intent);

        startActivity(man1_Intent);}
        else {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Alert!")
                    .setMessage("Any unsaved data will be lost")
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            Intent man1_Intent = new Intent(ctx, application_manager.class);
                            String reply = mReply.getText().toString();
                            man1_Intent.putExtra(EXTRA_REPLY1, reply);
                            //setResult(RESULT_OK,man_Intent);

                            startActivity(man1_Intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        }
                    });

            dialog.show();
        }
        }
    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(isLocationEnabled()==0)
            {
                showAlert();
            }
            if(isLocationEnabled()==1)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 5, locationListener);
            else if(isLocationEnabled()==2)
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50, 5, locationListener);

        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    private int isLocationEnabled() {
       if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
           return 1;
       else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
           return 2;
       }
       else return 0;
    }

    public void gonext(View view) {
        user= (String) mReply.getText();
        EditText e=findViewById(R.id.editText3);
        String quant=e.getText().toString();
        String tot;
        if(quantity_tot.isEmpty())
            quantity_tot="0";
         tot=quantity_tot.substring(0,5);
        if(Integer.parseInt(quant)>Integer.parseInt(tot.trim()))
        {
            Toast.makeText(this,"Error in quantity",Toast.LENGTH_SHORT).show();
            return;
        }
        //while(check_location_set!=true){
        selected_quantity=quant;
        //}
        Intent intent1 = new Intent(this, next_post_login_page.class);
        Bundle intent = new Bundle();

        intent.putString(EXTRA_REPLY1, username);
        intent.putString(EXTRA_REPLY2,selected_work);
        intent.putString(EXTRA_REPLY3, selected_activity);
        intent.putString(EXTRA_REPLY4, selected_lines);
        intent.putString(EXTRA_REPLY5, selected_station);
        intent.putString(EXTRA_REPLY6,selected_quantity+" out of "+quantity_tot);
        String help=saveToInternalStorage(b);
        //Toast.makeText(ctx,help,Toast.LENGTH_SHORT).show();
       intent.putString(EXTRA_REPLY7,help);
        CheckBox a= findViewById(R.id.checkBox);
        if(a.isChecked())
            selected_checkboxes=":Erection";
        a= findViewById(R.id.checkBox3);
        if(a.isChecked())
            selected_checkboxes+=":Supply";
        a= findViewById(R.id.checkBox4);
        if(a.isChecked())
            selected_checkboxes+=":Others";
        a= findViewById(R.id.checkBox5);
        if(a.isChecked())
            selected_checkboxes+=":UPTH";
        a= findViewById(R.id.checkBox6);
        if(a.isChecked())
            selected_checkboxes+=":DNTH";
         a= findViewById(R.id.checkBox7);
        if(a.isChecked())
            selected_checkboxes+=":UPLL";
        a= findViewById(R.id.checkBox8);
        if(a.isChecked())
            selected_checkboxes+=":DNLL";
        a= findViewById(R.id.checkBox9);
        if(a.isChecked())
            selected_checkboxes+=":5thLine";
         a= findViewById(R.id.checkBox10);
        if(a.isChecked())
            selected_checkboxes+=":6thLine";

        intent.putString(EXTRA_REPLY8,selected_checkboxes);
        intent.putString(EXTRA_REPLY9,locsend);
        intent1.putExtras(intent);
        this.startActivity(intent1);
    }
    public String createImageFromBitmap(Bitmap bitmap) {
        String fileName = "myImage";//no .png or .jpg needed
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
            fo.write(bytes.toByteArray());
            // remember close file output
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
            fileName = null;
        }
        return fileName;
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 80, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
