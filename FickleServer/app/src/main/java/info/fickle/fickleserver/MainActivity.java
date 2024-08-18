package info.fickle.fickleserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<MainModel> offers;
    RecyclerView rv;
    EditText off;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.recycler_view);
        off = (EditText) findViewById(R.id.offer_text);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        MainAdapter adapter = new MainAdapter(offers);
        rv.setAdapter(adapter);
        initializeData();
        initializeAdapter();
    }
    private void initializeData(){
        offers = new ArrayList<>();
        offers.add(new MainModel("\n" +
                "Amazing Recharge Offers on MyAirtel App\n" +
                "+ Upto 3.5% Cashback From CouponDunia ",true));
        offers.add(new MainModel("Get amazing offers on International Roaming Smartpicks. Offer is valid only for postpaid sims.",false));
        offers.add(new MainModel("Get amazing offers and promo codes on various transactions made via Airtel Money. Offer valid for prepaid recharges only.",false));
    }
    private void initializeAdapter(){
        MainAdapter adapter = new MainAdapter(offers);
        rv.setAdapter(adapter);
    }
    public void onClickNew(View view){

    }
}
