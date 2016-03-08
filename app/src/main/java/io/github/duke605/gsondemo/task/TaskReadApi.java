package io.github.duke605.gsondemo.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.github.duke605.gsondemo.MainActivity;
import io.github.duke605.gsondemo.Mod;
import io.github.duke605.gsondemo.R;

/**
 * Created by Cole on 3/8/2016.
 */
public class TaskReadApi extends AsyncTask<String, Void, String> {

    private MainActivity activity;

    public TaskReadApi(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new Gson();

        Mod[] mods = gson.fromJson(s, Mod[].class);
        activity.mods = mods;
        activity.numMods.setText("" + mods.length);
        activity.button.setEnabled(true);
        activity.index.setEnabled(true);
        Toast.makeText(activity, "Fetched mods from API", Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader in;
        URL url;
        StringBuilder builder;
        HttpURLConnection con;
        String line;

        try {
            // Initializing
            url = new URL(params[0]);
            con = (HttpURLConnection) url.openConnection();
            builder = new StringBuilder();

            // Opening stream
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // Reading from stream
            while((line = in.readLine()) != null)
                builder.append(line);

            // Closing and returning
            in.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            this.cancel(false);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
