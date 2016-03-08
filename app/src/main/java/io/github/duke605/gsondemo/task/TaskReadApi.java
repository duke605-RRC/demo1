package io.github.duke605.gsondemo.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.github.duke605.gsondemo.Mod;
import io.github.duke605.gsondemo.R;

/**
 * Created by Cole on 3/8/2016.
 */
public class TaskReadApi extends AsyncTask<String, Void, String> {

    private Activity activity;

    public TaskReadApi(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new Gson();

        Mod[] mods = gson.fromJson(s, Mod[].class);
        ((TextView)activity.findViewById(R.id.num_mods)).setText(mods[0].name);
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
