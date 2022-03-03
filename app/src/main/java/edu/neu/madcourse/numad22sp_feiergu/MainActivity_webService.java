package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity_webService extends AppCompatActivity {
    Button button;
    TextView textView;
    String result = null;
    ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web_service);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView2);

        executor = Executors.newSingleThreadExecutor();

    }

    public void onButtonClick(View view) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                StringBuilder data = new StringBuilder();
                try {
                    URL url = new URL("https://dog.ceo/api/breed/bulldog/french/images/random");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        data.append(line);
                    }
                    if (data.length() > 0) {
                        JSONObject jsonObject = new JSONObject(data.toString());
                        result = jsonObject.getString("message");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(result);
                    }
                });
            }
        });
    }
}