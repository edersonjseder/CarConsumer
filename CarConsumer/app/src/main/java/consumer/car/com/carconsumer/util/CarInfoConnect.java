package consumer.car.com.carconsumer.util;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import consumer.car.com.carconsumer.R;
import consumer.car.com.carconsumer.interfaces.OnPostTaskListener;
import consumer.car.com.carconsumer.model.Car;

/**
 * Created by ederson.js on 31/10/2016.
 */

public class CarInfoConnect extends AsyncTask<String, Void, List<Car>> {
    private static final String TAG = "CarInfoConnect";

    private OnPostTaskListener mOnPostTaskListener;
    private QueryRequest queryRequest;
    private ParseJSONToJava parseJSONToJava;
    private List<Car> carsList;
    private Dialog progress;
    private Context context;

    public CarInfoConnect(OnPostTaskListener mOnPostTaskListener, Context context){
        this.mOnPostTaskListener = mOnPostTaskListener;
        this.context = context;
    }

    /**
     * This method execute the progress bar on layout while process the user request
     */
    @Override
    protected void onPreExecute() {

        progress = new Dialog(context, R.style.CustomProgressBar);
        progress.setContentView(R.layout.component_progress_bar);
        progress.setTitle("Loading data...");

        progress.show();

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
        // Stops the progress bar
        if (progress.isShowing()){
            progress.dismiss();
        }

        mOnPostTaskListener.onTaskCompleted(cars);

    }
}
