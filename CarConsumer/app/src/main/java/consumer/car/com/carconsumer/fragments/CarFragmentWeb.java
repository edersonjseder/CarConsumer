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
import consumer.car.com.carconsumer.interfaces.OnPostTaskInterface;
import consumer.car.com.carconsumer.model.Car;
import consumer.car.com.carconsumer.util.CarInfoConnect;

/**
 * Created by ederson.js on 31/10/2016.
 */

public class CarFragmentWeb extends Fragment implements OnPostTaskInterface {
    private static final String TAG = "CarFragmentWeb";

    private CarFragmentWeb.OnFragmentInteractionListener mListener;
    private OnPostTaskInterface mOnPostTaskInterface;
    private CarInfoConnect carConnect;
    private RecyclerView recyclerViewCars;
    private CarsListAdapter carsListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car_list, container, false);

        recyclerViewCars = (RecyclerView) v.findViewById(R.id.recycler_view_cars);
        recyclerViewCars.setHasFixedSize(true);
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(getActivity()));

        instantiateObjects();

        return v;
    }

    private void instantiateObjects(){
        mOnPostTaskInterface = this;
        carConnect = new CarInfoConnect(mOnPostTaskInterface, getContext());
        carConnect.execute();
    }

    @Override
    public void onTaskCompleted(List<Car> car) {
        Log.i(TAG, "CarFragmentWeb.onTaskCompleted() inside method - param value: " + car.size());
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

        carsListAdapter = new CarsListAdapter(car);
        recyclerViewCars.setAdapter(carsListAdapter);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
