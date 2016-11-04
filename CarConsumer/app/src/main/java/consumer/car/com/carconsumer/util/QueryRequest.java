package consumer.car.com.carconsumer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import consumer.car.com.carconsumer.path.Path;


public class QueryRequest {

    private static final String TAG = "QueryRequest";

    /**
     * This method is responsible for make the connection with the API from the
     * Company OpenWeatherMap, available on the web site http://openweathermap.org/ and
     * from there we get the JSON data and use it to be shown on the screen
     *
     * @return The String that contains the JSON info if any connection problem occur
     * @throws IOException - if a network connection problem or any other problem occur during the request
     * @Author edersonjseder
     *
     */

    public String doQuery() throws IOException {
        Log.i(TAG, "QueryRequest.doQuery() inside method ");
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        String result = "";
        String basicAuth = "";
        URL url = null;

        try{
            Log.i(TAG, "QueryRequest.doQuery() inside try/catch block ");
            // Gets the URL with the param cityName, and with it concatenated on the url gets the JSON weather info
            url = new URL(Path.URL_PATH);
            Log.i(TAG, "QueryRequest.doQuery() inside try/catch block - url value: " + url);

            // Set the credentials for Basic Authentication
            basicAuth = "Basic " + getBasicAuthenticationEncoding();
            Log.i(TAG, "QueryRequest.doQuery() Basic Authentication - basicAuth: " + basicAuth);

            // Open the connection with the URL here
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", basicAuth);

            // Check if the open connection is returned
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK){
                Log.i(TAG, "QueryRequest.doQuery() inside try/catch block - inside if (" + connection.getResponseCode() + ") == (" + HttpURLConnection.HTTP_OK + ")");
                // Gets the JSON info
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader, 8192);
                String line = null;

                // Read the info here
                while ((line = bufferedReader.readLine()) != null){
                    Log.i(TAG, "QueryRequest.doQuery() - inside if - while: " + line);
                    result += line;
                }
            }

        } catch (IOException e) {
            Log.i(TAG, "QueryRequest.doQuery() inside catch block - message: " + e.getMessage());
            e.printStackTrace();
            result = e.getMessage();

        } finally {
            Log.i(TAG, "QueryRequest.doQuery() inside finally block");
            if (connection != null){
                bufferedReader.close();
                connection.disconnect();
            }
        }

        return result;
    }

    /**
     *
     * This method is responsible for make the connection with the API from the
     * Company OpenWeatherMap, available on the web site http://openweathermap.org/ and
     * from there we get the Image data through the String containing the code of image
     * and use it to be shown on the screen
     *
     * @param imageUrl
     * @return
     * @throws IOException
     */
    public Bitmap getImageWithQuery(String imageUrl) throws IOException {
        Log.i(TAG, "QueryRequest.getImageWithQuery() inside method - param value: " + imageUrl);
        HttpURLConnection connection = null;
        Bitmap imageBitmap = null;
        InputStream inputStream = null;

        try{
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside try/catch block - param value: " + imageUrl);

            // Make the image URL through the code number from the JSON file
            URL url = new URL(imageUrl);
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside try/catch block - url value: " + url);

            // Open the connection with the URL here
            connection = (HttpURLConnection) url.openConnection();

            // Get the input stream from the web
            inputStream = connection.getInputStream();
            // Decode the input stream to a bitmap image
            imageBitmap = BitmapFactory.decodeStream(inputStream);
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside try/catch block - imageBitmap value: " + imageBitmap);

            if (imageBitmap == null){
                imageBitmap = null;
            }

        } catch (IOException e) {
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside catch block: " + e.getMessage());
            e.printStackTrace();

        } finally {
            Log.i(TAG, "QueryRequest.getImageWithQuery() inside finally block");
            if (connection != null){
                inputStream.close();
                connection.disconnect();
            }
        }

        return imageBitmap;
    }

    // Convert the String data to Base 64
    private String getBasicAuthenticationEncoding() {

        String userPassword = Path.username + ":" + Path.password;
        return new String(Base64.encodeBase64(userPassword.getBytes()));

    }
}
