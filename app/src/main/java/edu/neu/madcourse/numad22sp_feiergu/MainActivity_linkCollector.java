package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity_linkCollector extends AppCompatActivity {
    private FloatingActionButton floatingAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_link_collector);

        floatingAddButton = findViewById(R.id.floatingAddButton);
        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "clicked folating add button", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}