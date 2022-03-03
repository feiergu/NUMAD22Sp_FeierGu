package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity_webService extends AppCompatActivity {
    Button button_randomDog;
    TextView textView_dogLink;
    String result = null;
    ExecutorService executor;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web_service);

        button_randomDog = findViewById(R.id.button_randomDog);
        textView_dogLink = findViewById(R.id.textView_dogLink);
        imageView = findViewById(R.id.imageView_randomDog);

        executor = Executors.newSingleThreadExecutor();

    }

    public void onButtonClick(View view) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                StringBuilder data = new StringBuilder();
                try {
                    // fetch data
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
                        System.out.println(result);
                        // fetch image
                        URL url_dog = new URL(result);
                        HttpURLConnection connection2 = (HttpURLConnection) url_dog.openConnection();
                        connection2.connect();
                        InputStream in = connection2.getInputStream();
                        bitmap = BitmapFactory.decodeStream(in);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView_dogLink.setText(result);
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }
}