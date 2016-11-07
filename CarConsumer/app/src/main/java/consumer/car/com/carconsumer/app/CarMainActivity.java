package consumer.car.com.carconsumer.app;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.fragments.CarDetailFragment;
import consumer.car.com.carconsumer.fragments.CarListFragment;

public class CarMainActivity extends AppCompatActivity {

    // key for storing row ID in Bundle passed to a fragment
    public static final String ROW_ID = "row_id";

    private FragmentTransaction transaction;
    private CarListFragment mCarListFragment;
    private CarDetailFragment mCarDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_main_activity);

        // return if Activity is being restored, no need to recreate GUI
        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            startFragmentForecast();
        }
    }



    /**
     * This method start the fragment request web
     *
     **/
    private void startFragmentForecast() {
        mCarListFragment = new CarListFragment();

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.id_car_main_activity, mCarListFragment);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
