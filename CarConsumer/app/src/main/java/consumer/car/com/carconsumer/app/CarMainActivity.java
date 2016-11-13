package consumer.car.com.carconsumer.app;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.fragments.CarDetailFragment;
import consumer.car.com.carconsumer.fragments.CarListFragment;
import consumer.car.com.carconsumer.interfaces.OnCarItemSelectedListener;
import consumer.car.com.carconsumer.model.Car;

public class CarMainActivity extends AppCompatActivity implements OnCarItemSelectedListener {

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

    @Override
    public void onCarItemSelected(Car car, int position) {
        mCarDetailFragment = new CarDetailFragment();

        getIntent().putExtra(CarDetailFragment.CAR, car);

        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.id_car_main_activity, mCarDetailFragment);
            transaction.addToBackStack("Previous");
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
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
