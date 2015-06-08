package com.google.android.gms.location.sample.locationsettings.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.gms.location.sample.locationsettings.GooglePlace;
import com.google.android.gms.location.sample.locationsettings.R;
import com.google.android.gms.location.sample.locationsettings.Util.FoodieHubUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GooglePlaceVenues extends AsyncTask<View, Void, String> {
    private String temp;
    private ArrayList<GooglePlace> venuesList;

    private final String TAG = this.getClass().getName();
    private double mLatitude;
    private double mLongitutde;
    private GooglePlaceVenuesCallBack mGooglePlaceVenuesCallBack;

    public GooglePlaceVenues(double mLatitude, double mLongitutde, GooglePlaceVenuesCallBack googlePlaceVenuesCallBack) {
        this.mLatitude = mLatitude;
        this.mLongitutde = mLongitutde;
        this.mGooglePlaceVenuesCallBack = googlePlaceVenuesCallBack;
    }

    @Override
    protected String doInBackground(View... urls) {
        // make Call to the url
        temp = makeCall("https://maps.googleapis.com/maps/api/place/search/json?location="
                + String.valueOf(mLatitude) + ","
                + String.valueOf(mLongitutde)
                + "&radius=200&types=restaurant&key=" + FoodieHubUtil.GOOGLE_API_KEY);
        Log.i(TAG, "ConnectToGooglePlaceAPI");
        return "";
    }

    @Override
    protected void onPreExecute() {
        // we can start a progress bar here
        Log.i(TAG, "onPreExecute");
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "ProcessFoursquareData");
        if (temp == null) {

        } else {
            // all things went right

            // parse Google Place search result
            venuesList = (ArrayList) parseGooglePlace(temp);

            List listTitle = new ArrayList();

            for (int i = 0; i < venuesList.size(); i++) {
                // make a list of the venus that are loaded in the list.
                // show the name, the category and the city
                listTitle.add(i, venuesList.get(i).getName() + "\nOpen Now: " + venuesList.get(i).getOpenNow());
            }

            if (mGooglePlaceVenuesCallBack != null){
                mGooglePlaceVenuesCallBack.onPostExecute(listTitle);
            }
        }
    }

    private String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "makeCall");
        // trim the whitespaces
        return replyString.trim();
    }


    private ArrayList parseGooglePlace(final String response) {

        ArrayList temp = new ArrayList();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    GooglePlace poi = new GooglePlace();
                    if (jsonArray.getJSONObject(i).has("name")) {
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setRating(jsonArray.getJSONObject(i).optString("rating", " "));

                        if (jsonArray.getJSONObject(i).has("opening_hours")) {
                            if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").has("open_now")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").getString("open_now").equals("true")) {
                                    poi.setOpenNow("YES");
                                } else {
                                    poi.setOpenNow("NO");
                                }
                            }
                        } else {
                            poi.setOpenNow("Not Known");
                        }
                        if (jsonArray.getJSONObject(i).has("types")) {
                            JSONArray typesArray = jsonArray.getJSONObject(i).getJSONArray("types");

                            for (int j = 0; j < typesArray.length(); j++) {
                                poi.setCategory(typesArray.getString(j) + ", " + poi.getCategory());
                            }
                        }
                    }
                    temp.add(poi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
        return temp;

    }

    public interface GooglePlaceVenuesCallBack{
        public void onPostExecute(List<String> result);
    }
}