package com.example.MRVC;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {
    public static ArrayList <Activity> backStack = new ArrayList<>();
    Context context;
    public static final String EXTRA_MESSAGE = "com.example.MRVC.extra.MESSAGE";
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    private int mcount=0;
    TextView show;
    EditText username,password;
    private static final String KEY_NAME = "yourKey";
    private Cipher cipher;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private FingerprintManager.CryptoObject cryptoObject;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        killBackStack();
        super.onCreate(savedInstanceState);
        //MainActivity.backStack.add(this);
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
        if(user_check.trim().equals("ProjectManager"))
        {
            Intent man1_Intent = new Intent(this, application_manager.class);
            //String reply = mReply.getText().toString();
           startActivity(man1_Intent);}
            // man1_Intent.putExtra(EXTRA_REPLY1, reply);

        else

    {
        background_worker backgroundworker = new background_worker(this);
        backgroundworker.execute(type, user_check, pass);

    }

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
    private boolean checkFinger() {

        // Keyguard Manager
        KeyguardManager keyguardManager = (KeyguardManager)
                getSystemService(KEYGUARD_SERVICE);

         FingerprintManager fingerprintManager = (FingerprintManager)
                getSystemService(FINGERPRINT_SERVICE);

        try {TextView message= findViewById(R.id.textView);
            // Check if the fingerprint sensor is present
            if (!fingerprintManager.isHardwareDetected()) {
                // Update the UI with a message

                message.setText("Fingerprint authentication not supported");
                return false;
            }

            if (!fingerprintManager.hasEnrolledFingerprints()) {
                message.setText("No fingerprint configured.");
                return false;
            }

            if (!keyguardManager.isKeyguardSecure()) {
                message.setText("Secure lock screen not enabled");
                return false;
            }
        }
        catch(SecurityException se) {
            se.printStackTrace();
        }
        return true;
    }

    public void fingerprintcheck(View view) {
        boolean check = checkFinger();
        if (check == true) {
            try {
                generateKey();
            } catch (FingerprintException e) {
                e.printStackTrace();
            }
            if (initCipher()) {
                //If the cipher is initialized successfully, then create a CryptoObject instance//
                cryptoObject = new FingerprintManager.CryptoObject(cipher);

                // Here, I’m referencing the FingerprintHandler class that we’ll create in the next section. This class will be responsible
                // for starting the authentication process (via the startAuth method) and processing the authentication process events//
                FingerprintHandler helper = new FingerprintHandler(this);
                helper.startAuth(fingerprintManager, cryptoObject);


            }
        }

    }
        //Create a new method that we’ll use to initialize our cipher//
        public boolean initCipher () {
            try {
                //Obtain a cipher instance and configure it with the properties required for fingerprint authentication//
                cipher = Cipher.getInstance(
                        KeyProperties.KEY_ALGORITHM_AES + "/"
                                + KeyProperties.BLOCK_MODE_CBC + "/"
                                + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            } catch (NoSuchAlgorithmException |
                    NoSuchPaddingException e) {
                throw new RuntimeException("Failed to get Cipher", e);
            }

            try {
                keyStore.load(null);
                SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                        null);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                //Return true if the cipher has been initialized successfully//
                return true;
            } catch (KeyPermanentlyInvalidatedException e) {

                //Return false if cipher initialization failed//
                return false;
            } catch (KeyStoreException | CertificateException
                    | UnrecoverableKeyException | IOException
                    | NoSuchAlgorithmException | InvalidKeyException e) {
                throw new RuntimeException("Failed to init Cipher", e);
            }
        }

        private class FingerprintException extends Exception {
            public FingerprintException(Exception e) {
                super(e);
            }
        }
    private void generateKey() throws FingerprintException {
        try {
            // Obtain a reference to the Keystore using the standard Android keystore container identifier (“AndroidKeystore”)//
            keyStore = KeyStore.getInstance("AndroidKeyStore");

            //Generate the key//
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            //Initialize an empty KeyStore//
            keyStore.load(null);

            //Initialize the KeyGenerator//
            keyGenerator.init(new

                    //Specify the operation(s) this key can be used for//
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)

                    //Configure this key so that the user has to confirm their identity with a fingerprint each time they want to use it//
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());

            //Generate the key//
            keyGenerator.generateKey();

        } catch (KeyStoreException
                | NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | CertificateException
                | IOException exc) {
            exc.printStackTrace();
            throw new FingerprintException(exc);
        }
    }
    public static void killBackStack () {
        for (Activity ac : backStack) {
            if (ac != null)
                ac.finish();
        }
    }


}
