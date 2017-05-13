package fr.garrycity.pol.gitprojectb3.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pol on 11/04/2017.
 */

public class GetFeedTask extends AsyncTask<Void, Void, String> {
    private TaskDelegate mContext;
    private String args;
    private String type;
    private String what;
    private String queryString;

    public GetFeedTask(TaskDelegate mContext, String query, String type, String what) {
        this.mContext = mContext;
        this.args = query;
        this.type = type;
        this.what = what;

        if (type.equals("search")) {
            queryString = "https://api.github.com/" + type + "/" + what + "?q=" + args;
        }
        else if (type.equals("users") || type.equals("raw_url") || type.equals("repos") || type.equals("orgs")) {
            queryString = args;
        }
        else if (type.equals("gists")) {
            if (args.equals("")) {
                queryString = "https://api.github.com/"+ type +"/" + what;
            } else {
                queryString = "https://api.github.com/"+ what +"/" + args + "/" + type;
            }
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(queryString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);

        mContext.onTaskResult(response);

    }

}
