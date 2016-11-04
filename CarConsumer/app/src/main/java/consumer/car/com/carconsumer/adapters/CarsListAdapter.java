package consumer.car.com.carconsumer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.model.Car;
import consumer.car.com.carconsumer.viewHolders.CarsViewHolder;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class CarsListAdapter extends RecyclerView.Adapter<CarsViewHolder> {

    private List<Car> carList;

    public CarsListAdapter(List<Car> carList){
        this.carList = carList;
    }

    @Override
    public CarsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_card_view_cars, viewGroup, false);
        CarsViewHolder carsViewHolder = new CarsViewHolder(view);

        return carsViewHolder;
    }

    @Override
    public void onBindViewHolder(CarsViewHolder carsViewHolder, int position) {

        Car car = carList.get(position);
        carsViewHolder.getImageViewCarType().setImageBitmap(car.getCarImageByte());
        carsViewHolder.getTextViewName().setText(car.getName());
        carsViewHolder.getTextViewType().setText(car.getType());
        carsViewHolder.getTextViewdescription().setText(car.getDescription());

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
