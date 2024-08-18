package info.fickle.fickleserver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Bharath on 03/11/16.
 */

public class AddressActivity extends AppCompatActivity {
    ImageView imgv;
    EditText add;
    private TextInputLayout input_tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_address);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        imgv = (ImageView) findViewById(R.id.addr_image);
        add = (EditText) findViewById(R.id.address_text);
        input_tid = (TextInputLayout) findViewById(R.id.input_add);
    }
    public void onClickAddress(View view){
        String addr = add.getText().toString();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("address", addr);
        editor.commit();
        validate();


    }
    public void validate(){
        String address= add.getText().toString();
        if(address.isEmpty()){
            input_tid.setError("Invalid Email");
            return;
        }else {
            input_tid.setErrorEnabled(false);

        }
        Intent i= new Intent(this,OfferActivity.class);
        startActivity(i);
    }
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
}
