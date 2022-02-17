package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void aboutMe(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity_aboutMe.class);

        startActivity(intent);
    }

    public void clickyClicky(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity_clickyClicky.class);

        startActivity(intent);
    }

    public void linkCollector(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity_linkCollector.class);

        startActivity(intent);
    }

    public void displayLocation(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity_displayLocation.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}