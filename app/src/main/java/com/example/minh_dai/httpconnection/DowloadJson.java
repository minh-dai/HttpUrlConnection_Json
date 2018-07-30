package com.example.minh_dai.httpconnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DowloadJson extends AsyncTask<String, Void, String> {

    private ImageView img;
    private TextView mTxt;
    private Context mContext;
    private int timeout=20000;

    public DowloadJson(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        final String mUrl = strings[0];
        StringBuffer result = new StringBuffer();

        try {
            URL url = new URL(mUrl);
            HttpsURLConnection mConnection = (HttpsURLConnection) url.openConnection();
//            mConnection.setRequestMethod("GET");
//            m
//
//
// Connection.setRequestProperty("Content-length", "application/json; charset=utf-8");
           /* mConnection.setUseCaches(false);
            mConnection.setDoInput(false);
            mConnection.setAllowUserInteraction(false);
            mConnection.setConnectTimeout(timeout);
            mConnection.setReadTimeout(timeout);*/


            mConnection.setInstanceFollowRedirects(false);

            mConnection.connect();

            int rescode = mConnection.getResponseCode();
            if (rescode == 201){
                Log.d("trueabc" , rescode+"trueabc");
            }

            InputStream mInputStream = new BufferedInputStream(mConnection.getInputStream());

            BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(mInputStream));

            String line="";

            while ((line = mBufferedReader.readLine()) != null){
                result.append(line);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("abc1" , result.toString());
        return result.toString();

    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        try {
            JSONArray mJsonArray = new JSONArray(aVoid);
            for (int i=0; i<mJsonArray.length(); ++i){
                JSONObject mObject = mJsonArray.getJSONObject(i);
                JSONObject owner = mObject.getJSONObject("owner");
                Toast.makeText(mContext,owner.getString("avatar_url"), Toast.LENGTH_SHORT).show();
                break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
