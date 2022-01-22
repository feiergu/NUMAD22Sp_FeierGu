package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view) {
        Log.i("About me", "Button pressed!");
        Toast.makeText(this, "Feier Gu\ngu.fei@northeastern.edu", Toast.LENGTH_SHORT).show();
    }

    public void greetFunction(View view) {
        Log.i("Greet", "Button pressed!");
        Toast.makeText(this, "Have a nice day!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}