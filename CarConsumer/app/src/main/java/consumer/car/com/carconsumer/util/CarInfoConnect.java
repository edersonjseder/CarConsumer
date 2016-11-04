package consumer.car.com.carconsumer.util;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import consumer.car.com.carconsumer.interfaces.OnPostTaskInterface;
import consumer.car.com.carconsumer.model.Car;

/**
 * Created by ederson.js on 31/10/2016.
 */

public class CarInfoConnect extends AsyncTask<String, Void, List<Car>> {
    private static final String TAG = "CarInfoConnect";

    private OnPostTaskInterface mOnPostTaskInterface;
    private QueryRequest queryRequest;
    private ParseJSONToJava parseJSONToJava;
    private List<Car> carsList;

    public CarInfoConnect(OnPostTaskInterface mOnPostTaskInterface){
        this.mOnPostTaskInterface = mOnPostTaskInterface;
    }

    @Override
    protected List<Car> doInBackground(String... params) {

        String queryReturn = "";
        queryRequest = new QueryRequest();
        parseJSONToJava = new ParseJSONToJava(queryRequest);

        try {
            //Execute connection to get the JSON Object
            queryReturn = queryRequest.doQuery();
            carsList = (List<Car>) parseJSONToJava.convertToJava(queryReturn);

        } catch (Exception e) {
            e.printStackTrace();
            carsList = null;

        }

        return carsList;
    }

    @Override
    protected void onPostExecute(List<Car> cars) {
        Log.i(TAG, "CarInfoConnect.onPostExecute() inside method - param: " + cars.size());

        mOnPostTaskInterface.onTaskCompleted(cars);

    }
}
