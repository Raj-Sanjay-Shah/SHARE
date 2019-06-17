package com.example.MRVC;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class post_login_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
                    location1.setText("Current Location: " + location.getLatitude() + ", " + location.getLongitude()+result);
                }

                } catch (IOException e) {
                e.printStackTrace();
            }



            int t = 0;
            while (t < 10000)
                t++;
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
    boolean check = false;
    Context ctx = this;
    TextView location1;
    public static final String EXTRA_REPLY =
            "com.example.MRVC.extra.REPLY";
    public static final String EXTRA_REPLY1 =
            "com.example.MRVC.extra.REPLY";
    private TextView mReply;
    private static final int result_load_image = 1;
    ImageView selected_img;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login_page);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mReply = findViewById(R.id.textView21);
        check = false;
        Intent intent = getIntent();//gets the intent which activated this activity
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mReply.setText("welcome:" + message);

        location1 = findViewById(R.id.textView21);
        Spinner spinner = findViewById(R.id.spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
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
        Intent gallery_intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery_intent,result_load_image);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == result_load_image) {
            if(resultCode == Activity.RESULT_OK && data!=null){
                Uri contentURI = data.getData();
                try {selected_img=findViewById(R.id.imageView4);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    selected_img.setImageBitmap(bitmap);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(post_login_page.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
                }


        }
        return;

    }

    public void man_mode(MenuItem item) {
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

}
