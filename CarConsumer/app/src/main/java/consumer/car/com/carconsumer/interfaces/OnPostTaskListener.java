package consumer.car.com.carconsumer.interfaces;

import java.util.List;

import consumer.car.com.carconsumer.model.Car;

/**
 * Created by ederson.js on 31/10/2016.
 */

public interface OnPostTaskListener {
    public void onTaskCompleted(List<Car> car);
}
