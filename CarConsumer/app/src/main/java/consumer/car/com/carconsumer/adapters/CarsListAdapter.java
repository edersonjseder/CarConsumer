package consumer.car.com.carconsumer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.interfaces.CarListFragmentListener;
import consumer.car.com.carconsumer.interfaces.ItemClickListener;
import consumer.car.com.carconsumer.model.Car;
import consumer.car.com.carconsumer.viewHolders.CarsViewHolder;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class CarsListAdapter extends RecyclerView.Adapter<CarsViewHolder> {

    private List<Car> carList;
    private Context context;
    private CarsViewHolder carsViewHolder;
    private View.OnClickListener onClickListener;

    public CarsListAdapter(List<Car> carList, Context context) {
        this.carList = carList;
        this.context = context;
    }

    @Override
    public CarsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_card_view_cars_list, viewGroup, false);
        CarsViewHolder carsViewHolder = new CarsViewHolder(view);
        view.setTag(carsViewHolder);

        return carsViewHolder;
    }

    @Override
    public void onBindViewHolder(CarsViewHolder carsViewHolder, int position) {

        // Gets the position of the item on the List and add the object information
        final Car car = carList.get(position);
        carsViewHolder.getImageViewCarType().setImageBitmap(car.getCarImageByte());
        carsViewHolder.getTextViewName().setText(car.getName());
        carsViewHolder.getTextViewType().setText(car.getType());

        // Listener to the card view item to show a detail when is clicked
        carsViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ((CarListFragmentListener)context).onCarItemSelected(car, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Car car) {
        carList.add(position, car);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Car car) {
        int position = carList.indexOf(car);
        carList.remove(position);
        notifyItemRemoved(position);
    }

}
