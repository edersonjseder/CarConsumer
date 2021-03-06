package consumer.car.com.carconsumer.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.adapters.CarsListAdapter;
import consumer.car.com.carconsumer.interfaces.OnCarItemSelectedListener;
import consumer.car.com.carconsumer.interfaces.OnPostTaskListener;
import consumer.car.com.carconsumer.model.Car;
import consumer.car.com.carconsumer.util.CarInfoConnect;

/**
 * Created by ederson.js on 31/10/2016.
 */

public class CarListFragment extends Fragment implements OnPostTaskListener {
    private static final String TAG = "CarListFragment";

    private CarListFragment.OnFragmentInteractionListener mListener;
    private OnCarItemSelectedListener fragmentListener;
    private OnPostTaskListener mOnPostTaskListener;
    private CarInfoConnect carConnect;
    private RecyclerView recyclerViewCars;
    private CarsListAdapter carsListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
            return;

        if (savedInstanceState == null) {
            instantiateObjects();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car_list, container, false);

        recyclerViewCars = (RecyclerView) v.findViewById(R.id.id_recycler_view_cars_list);
        recyclerViewCars.setHasFixedSize(true);
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    private void instantiateObjects(){
        mOnPostTaskListener = this;
        carConnect = new CarInfoConnect(mOnPostTaskListener, getContext());
        carConnect.execute();
    }

    @Override
    public void onTaskCompleted(List<Car> car) {
        Log.i(TAG, "CarListFragment.onTaskCompleted() inside method - param value: " + car.size());
        try {
            if (car != null){

                showCarsList(car);

            }else{
                throw new Exception();
            }

        } catch (Exception e){
            Log.i(TAG, "FragmentRequestWeb.onTaskCompleted() inside catch block - message: " + e.getMessage());

        }
    }

    private void showCarsList(List<Car> car) {

        carsListAdapter = new CarsListAdapter(car, getContext());
        recyclerViewCars.setAdapter(carsListAdapter);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
