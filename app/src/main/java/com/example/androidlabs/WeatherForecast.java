package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.xmlpull.v1.XmlPullParser.END_TAG;
import static org.xmlpull.v1.XmlPullParser.START_TAG;
import static org.xmlpull.v1.XmlPullParser.TEXT;

public class WeatherForecast extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView imageViewIcon;
    private TextView textViewCurrentTemp;
    private TextView textViewMaxTemp;
    private TextView textViewMinTemp;
    private TextView textViewUV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageViewIcon = findViewById(R.id.imageViewIcon);
        textViewCurrentTemp = findViewById(R.id.textViewCurrentTemp);
        textViewMaxTemp= findViewById(R.id.textViewMaxTemp);
        textViewMinTemp = findViewById(R.id.textViewMinTemp);
        textViewUV = findViewById(R.id.textViewUV);

        progressBar.setMax(10);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(1);

        ForecastQuery URL = new ForecastQuery();//define
        URL.execute( );//The start point of the AsyncTask
    }

    private class ForecastQuery extends AsyncTask<String, Integer, String> {

        private String currentTemp;
        private String MaxTemp;
        private String MinTemp;
        private String UV;
        private Bitmap weatherIcon;

        private final String wURL = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=json&units=metric";


        @Override                       //Type 1
        protected String doInBackground(String... params) {
            String ret = null;
            HttpURLConnection c = null;
            Integer count =1;
            for (; count <= 5; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //String queryURL = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=json&units=metric";
                    double lon =0;
                    double lat=0;
                    String icon="";
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(getJSON(wURL));

            lon=jObject.getJSONObject("coord").getDouble("lon");
                    lat=jObject.getJSONObject("coord").getDouble("lat");
                    icon=jObject.getJSONArray("weather").getJSONObject(0).getString("icon");
                    currentTemp = jObject.getJSONObject("main").getDouble("temp")+"";//!11111
                    MaxTemp = jObject.getJSONObject("main").getDouble("temp_max")+"";
                    MinTemp = jObject.getJSONObject("main").getDouble("temp_min")+"";

                    String uvUrl= "http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat="+lat+"&lon="+lon;

                jObject = new JSONObject(getJSON(uvUrl));

                UV = jObject.getDouble("value")+"";




            } catch (JSONException e) {
                e.printStackTrace();
            }







//            try {       // Connect to the server:
//                //get the string URL
//
//                //create the network connection
//                URL url = new URL(myURL);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream inStream = urlConnection.getInputStream();
//
//                //Set up the XML pull parser:
//                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//                factory.setNamespaceAware(false);
//                XmlPullParser xpp = factory.newPullParser();
//                xpp.setInput( inStream  , "UTF-8");
//
//                //Iterate over the XML tags:
//                int EVENT_TYPE;         //While not the end of the document:
//                while((EVENT_TYPE = xpp.getEventType()) != XmlPullParser.END_DOCUMENT)
//                {
//                    switch(EVENT_TYPE)
//                    {
//                        case START_TAG:         //This is a start tag < ... >
//                            String tagName = xpp.getName(); // What kind of tag?
//                            if(tagName.equals("temperature"))
//                            {
//                                String value = xpp.getAttributeValue(null, "value"); //What is the String associated with message?
//                                publishProgress (25);
//                                Log.d("temperature value: ",value);
//
//                                String min = xpp.getAttributeValue(null, "min");
//                                publishProgress (50);
//                                Log.d("Min temp:",min);
//
//                                String max = xpp.getAttributeValue(null, "max");
//                                publishProgress (70);
//                                Log.d("Max temp:",max);
//
//                                String unit = xpp.getAttributeValue(null, "unit");
//                            }
//                            else if(tagName.equals("Weather"))
//                            {
//                                //String number = xpp.getAttributeValue(null, "number");
//                                //publishProgress("Outlook", outlook);
//
//                                //String value = xpp.getAttributeValue(null, "value");
//                                //publishProgress("Windy", windy);
//                                String icon = xpp.getAttributeValue(null, "icon");
//                            }
//                            else if(tagName.equals("Temperature"))
//                            {
//                                xpp.next(); //There is a text right after the opening of <Temperature> so move the pointer next
//                                String text = xpp.getText();
//                                //publishProgress("Temperature", text);
//                            }
//                            break;
//                        case END_TAG:           //This is an end tag: </ ... >
//                            break;
//                        case TEXT:              //This is text between tags < ... > Hello world </ ... >
//                            break;
//                    }
//                    xpp.next(); // move the pointer to next XML element
//                }
//            }
//            catch(MalformedURLException mfe){ ret = "Malformed URL exception"; }
//            catch(IOException ioe)          { ret = "IO Exception. Is the Wifi connected?";}
//            catch(XmlPullParserException pe){ ret = "XML Pull exception. The XML is not properly formed" ;}
//            //What is returned here will be passed as a parameter to onPostExecute:
            return ret;
        }

        private String getJSON(String url) {
            HttpURLConnection c = null;
            try {
                URL u = new URL(url);
                c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setRequestProperty("Content-length", "0");
                c.setUseCaches(false);
                c.setAllowUserInteraction(false);
                c.setConnectTimeout(1000);
                c.setReadTimeout(1000);
                c.connect();
                int status = c.getResponseCode();

                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line+"\n");
                        }
                        br.close();
                        return sb.toString();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (c != null) {
                    try {
                        c.disconnect();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
           // progressBar.setVisibility(View.GONE);// progress bar gone after finishe
            progressBar.setProgress(10);
            textViewUV.setText(UV);
            textViewCurrentTemp.setText(currentTemp);
            textViewMaxTemp.setText(MaxTemp);
            textViewMinTemp.setText(MinTemp);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

    }
}















