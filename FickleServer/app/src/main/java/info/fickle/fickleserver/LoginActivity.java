package info.fickle.fickleserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Bharath on 02/11/16.
 */

public class LoginActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    private EditText fullname,email,phno;
    Boolean flag_img=Boolean.FALSE;
    Bitmap bitmap;
    TextView warn;
    private TextInputLayout inp_email,inp_full,inp_phno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        fullname = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.emailid);
        phno = (EditText) findViewById(R.id.phno);
        inp_email =(TextInputLayout) findViewById(R.id.input_email);
        inp_phno =(TextInputLayout) findViewById(R.id.input_phno);
        inp_full =(TextInputLayout) findViewById(R.id.input_fullname);
        warn = (TextView) findViewById(R.id.warn_upload);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.profile_image);
                imageView.setImageBitmap(bitmap);
                flag_img=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickUploadImage(View view){
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void onClickLogin(View v){

        validate();
    }
    public void validate(){
        String emaili = email.getText().toString();
        String fullnamei = fullname.getText().toString();
        String phnoi = phno.getText().toString();
        if(!flag_img){
            warn.setVisibility(View.VISIBLE);
            return;
        }else {
            warn.setVisibility(View.GONE);

        }
        if(fullnamei.isEmpty()){
            inp_full.setError("Enter your Name!");
            return;
        }else{
            inp_full.setErrorEnabled(false);
        }
        if(phnoi.isEmpty()){
            inp_phno.setError("Enter your Phone No!");
            return;
        }else{
            inp_phno.setErrorEnabled(false);
        }
        if(emaili.isEmpty() || !isValidEmail(emaili)){
            inp_email.setError("Invalid Email");
            return;
        }else{
            inp_email.setErrorEnabled(false);

        }
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("fullname", fullnamei);
        editor.putString("phno", phnoi);
        editor.putString("email", emaili);
        editor.commit();
        Intent i= new Intent(this,AddressActivity.class);
        startActivity(i);

    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
