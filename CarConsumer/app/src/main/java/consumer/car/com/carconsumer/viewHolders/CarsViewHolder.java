package consumer.car.com.carconsumer.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import consumer.car.com.carconsumer.R;

/**
 * Created by root on 01/11/16.
 */

public class CarsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewName;
    private TextView textViewdescription;
    private TextView textViewType;
    private ImageView imageViewCarType;


    public CarsViewHolder(View itemView) {
        super(itemView);

        imageViewCarType = (ImageView) itemView.findViewById(R.id.imageView_car_type);
        textViewName = (TextView) itemView.findViewById(R.id.textview_name);
        textViewType = (TextView) itemView.findViewById(R.id.textview_type);
        textViewdescription = (TextView) itemView.findViewById(R.id.textview_description);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), getAdapterPosition() + " - " + textViewdescription.getText(), Toast.LENGTH_SHORT).show();

    }

    public ImageView getImageViewCarType() {
        return imageViewCarType;
    }

    public void setImageViewCarType(ImageView imageViewCarType) {
        this.imageViewCarType = imageViewCarType;
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public void setTextViewName(TextView textViewName) {
        this.textViewName = textViewName;
    }

    public TextView getTextViewdescription() {
        return textViewdescription;
    }

    public void setTextViewdescription(TextView textViewdescription) {
        this.textViewdescription = textViewdescription;
    }

    public TextView getTextViewType() {
        return textViewType;
    }

    public void setTextViewType(TextView textViewType) {
        this.textViewType = textViewType;
    }

}
