package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_clickyClicky extends AppCompatActivity implements View.OnClickListener {
    Button button_a, button_b, button_c, button_d, button_e, button_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_clicky_clicky);
        button_a = findViewById(R.id.button_a);
        button_b = findViewById(R.id.button_b);
        button_c = findViewById(R.id.button_c);
        button_d = findViewById(R.id.button_d);
        button_e = findViewById(R.id.button_e);
        button_f = findViewById(R.id.button_f);
        button_a.setOnClickListener(this);
        button_b.setOnClickListener(this);
        button_c.setOnClickListener(this);
        button_d.setOnClickListener(this);
        button_e.setOnClickListener(this);
        button_f.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView clickyTestView = findViewById(R.id.textView_clicky);
        String txt = "Pressed: " + view.getTag().toString();
        clickyTestView.setText(txt);
    }
}