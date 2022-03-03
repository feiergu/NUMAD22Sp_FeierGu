package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    AlertDialog dialog;
    EditText editText_dogBreed;
    String breed;
    String customizedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web_service);

        button_randomDog = findViewById(R.id.button_randomDog);
        textView_dogLink = findViewById(R.id.textView_dogLink);
        imageView = findViewById(R.id.imageView_randomDog);
        editText_dogBreed = findViewById(R.id.editText_dogBreed);

        Toast.makeText(getApplicationContext(), "Try bulldog, husky or any breed you like!", Toast.LENGTH_SHORT).show();

        executor = Executors.newSingleThreadExecutor();

    }

    public void onButtonClick(View view) {
        breed = editText_dogBreed.getText().toString();
        customizedUrl = "https://dog.ceo/api/breed/" + breed + "/images/random";

        // loading dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_webService.this);
        LayoutInflater inflater = MainActivity_webService.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                result = null;
                bitmap = null;
                StringBuilder data = new StringBuilder();
                try {
                    // fetch data
                    URL url = new URL(customizedUrl);
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
                        // check if user input is correct
                        if (!URLUtil.isValidUrl(result)) {
                            return;
                        }
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
                        // dismiss loading dialog
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }

                        // display link and image for valid user input
                        if (result != null && bitmap != null) {
                            textView_dogLink.setText(result);
                            imageView.setImageBitmap(bitmap);
                        } else {
                            // invalid user input
                            // empty previous data & toast
                            textView_dogLink.setText(null);
                            imageView.setImageBitmap(null);
                            Toast.makeText(getApplicationContext(), "Invalid breed, please enter again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}