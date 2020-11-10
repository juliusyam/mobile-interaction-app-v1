package com.juliusyam.mobileinteractionappv1;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class FetchData extends AsyncTask<URL, Void, JSONArray> {

    private JsonFunction mJsonFunction;

//    String nameParsed = "";
//    String addressParsed = "";
//    String crossStreetParsed = "";
//    String distanceParsed = "";
//    String postcodeParsed = "";

//    public FetchData(String nameParsed, String addressParsed, String crossStreetParsed, String distanceParsed, String postcodeParsed) {
////        this.latitude = latitude;
////        this.longitude = longitude;
//        this.nameParsed = nameParsed;
//        this.addressParsed = addressParsed;
//        this.crossStreetParsed = crossStreetParsed;
//        this.distanceParsed = distanceParsed;
//        this.postcodeParsed = postcodeParsed;
//    }

    //Go read Lambda expressions

    //Structure of callback, callback of type JsonFunction
    interface JsonFunction {
        //from w3c schools https://www.w3schools.com/java/java_lambda.asp
        void run(JSONArray jsonArray);
    }

    public FetchData(JsonFunction jsonFunction) {
        mJsonFunction = jsonFunction;
        //give it a lambda
    }

//    @Override
//    protected JSONObject doInBackground(Void... voids) {
//        JSONObject jsonObject = null;
//
//        try {
//            String path = new StringBuilder()
//                    .append("https://api.foursquare.com/v2/venues/search?client_id=")
//                    .append(CLIENT_ID)
//                    .append("&client_secret=")
//                    .append(CLIENT_SECRET)
//                        .append("&v=20130815&ll=")
//                    .append(latitude)
//                    .append(',')
//                    .append(longitude)
//                    .toString();
//            URL url = new URL(path);
//
//            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
//            InputStream inputStream = httpsURLConnection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line = "";
//            StringBuilder data = new StringBuilder();
//            while (line !=null) {
//                data.append(line);
//                line = bufferedReader.readLine();
//            }
//
//            jsonObject = new JSONObject(data.toString()).getJSONObject("response").getJSONArray("venues").getJSONObject(jsonCount);
//            //dataParsed = dataParsed + jsonObject + "\n";
//
//            nameParsed = jsonObject.getString("name");
//            addressParsed = jsonObject.getJSONObject("location").getString("address");
//            crossStreetParsed = jsonObject.getJSONObject("location").getString("crossStreet");
//            distanceParsed = jsonObject.getJSONObject("location").getString("distance") + " metres";
//            postcodeParsed = jsonObject.getJSONObject("location").getString("postalCode");
//
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//
//        return jsonObject;
//    }
//
//    @Override
//    protected void onPostExecute(JSONObject aVoid) {
//        super.onPostExecute(aVoid);
//
//        //MainActivity.data.setText(this.dataParsed);
//        ExampleAdapter.ExampleViewHolder.name.setText(this.nameParsed);
//        ExampleAdapter.ExampleViewHolder.address.setText(this.addressParsed);
//        ExampleAdapter.ExampleViewHolder.crossStreet.setText(this.crossStreetParsed);
//        ExampleAdapter.ExampleViewHolder.distance.setText(this.distanceParsed);
//        ExampleAdapter.ExampleViewHolder.postcode.setText(this.postcodeParsed);
//
//    }

    @Override
    protected JSONArray doInBackground(URL... urls) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        try {

            //(HttpsURLConnection) is a subclass to openConnection(), openConnection() returns a connection, by casting it, it returns a HttpsConnection to HttpsURLConnection
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urls[0].openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            StringBuilder data = new StringBuilder();
            while (line !=null) {
                data.append(line);
                line = bufferedReader.readLine();
            }

            jsonObject = new JSONObject(data.toString()).getJSONObject("response");
            jsonArray = jsonObject.getJSONArray("venues");



        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);

        mJsonFunction.run(jsonArray);

// send the JSON Array into the callback
    }
}
