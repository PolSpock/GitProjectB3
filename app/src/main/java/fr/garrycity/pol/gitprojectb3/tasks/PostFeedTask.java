package fr.garrycity.pol.gitprojectb3.tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pol on 06/05/2017.
 */

public class PostFeedTask extends AsyncTask<String, String, String> {
    private TaskDelegate mContext;
    private JSONObject jsonObject;
    private String queryString;

    public PostFeedTask(TaskDelegate mContext, JSONObject jsonObject){
        this.mContext = mContext;
        this.jsonObject = jsonObject;

        this.queryString = "https://api.github.com/gists";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(queryString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);


            Log.i("JSON", jsonObject.toString());
            DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
            os.writeBytes(jsonObject.toString());

            os.flush();
            os.close();

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();

                System.out.println(stringBuilder.toString());

                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }

        } catch (IOException e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }


    @Override
    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);

        mContext.onTaskResult(response);
    }
}
