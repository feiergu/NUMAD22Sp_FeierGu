package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void aboutMe(View view) {
        Log.i("About me", "Button pressed!");
        Toast.makeText(this, "Feier Gu\ngu.fei@northeastern.edu", Toast.LENGTH_SHORT).show();
    }

    public void clickyClicky(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity_clickyClicky.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}