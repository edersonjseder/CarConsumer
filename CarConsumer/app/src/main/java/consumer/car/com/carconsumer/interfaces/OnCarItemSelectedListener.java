package consumer.car.com.carconsumer.interfaces;

import consumer.car.com.carconsumer.model.Car;

/**
 * Created by root on 06/11/16.
 */

public interface OnCarItemSelectedListener {

    // called when user selects a contact
    public void onCarItemSelected(Car car, int position);

}
