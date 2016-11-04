package consumer.car.com.carconsumer.app;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.fragments.CarFragmentWeb;

public class CarMainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private CarFragmentWeb mCarFragmentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_main_activity);

        if (savedInstanceState == null) {
            startFragmentForecast();
        }
    }

    /**
     * This method start the fragment request web
     *
     **/
    private void startFragmentForecast() {
        mCarFragmentWeb = new CarFragmentWeb();

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.car_main_activity, mCarFragmentWeb);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
