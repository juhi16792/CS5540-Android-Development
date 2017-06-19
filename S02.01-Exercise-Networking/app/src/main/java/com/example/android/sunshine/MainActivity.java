/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        // TODO (4) Delete the dummy weather data. You will be getting REAL data from the Internet in this lesson.
        // --> Completed
        /*
         * This String array contains dummy weather data. Later in the course, we're going to get
         * real weather data. For now, we want to get something on the screen as quickly as
         * possible, so we'll display this dummy data.
         */

        // TODO (3) Delete the for loop that populates the TextView with dummy data --> Completed
        /*
         * Iterate through the array and append the Strings to the TextView. The reason why we add
         * the "\n\n\n" after the String is to give visual separation between each String in the
         * TextView. Later, we'll learn about a better way to display lists of data.
         */
        // TODO (9) Call loadWeatherData to perform the network request to get the weather
        loadWeatherData();
    }

    // TODO (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData
    private void loadWeatherData(){
        String location = SunshinePreferences.getPreferredWeatherLocation(this);
        new NetworkRequests().execute(location);
    }

    // TODO (5) Create a class that extends AsyncTask to perform network requests --> Completed
    public class NetworkRequests extends AsyncTask<String, Void, String[]> {

        // TODO (6) Override the doInBackground method to perform your network requests --> Completed
        @Override
        protected String[] doInBackground(String... params) {
            //tasks to do here
            // 1. this process should work in background and get the weather data from the URL built from TODO (1)
            // 2. before starting this task complete TOOD (1) which is NetworkUtils
            // 3. params[0] should have the location
            // 4. URL should be built with the location specified
            // 5. And then pass the JSON
            // 6. if params[0] is null then just return null
            if(params.length==0){
                return null;
            }
            String location = params[0];
            URL builtURLFromLocation =  NetworkUtils.buildUrl(location);
            try {
                String weatherResponseInJSON = NetworkUtils
                        .getResponseFromHttpUrl(builtURLFromLocation);

                String[] weatherData = OpenWeatherJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, weatherResponseInJSON);

                return weatherData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // TODO (7) Override the onPostExecute method to display the results of the network request
        // -->Completed
        @Override
        protected void onPostExecute(String[] weatherData){
            for(String data: weatherData){
                mWeatherTextView.append(data);
            }
        }
    }
}