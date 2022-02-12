package edu.neu.madcourse.numad22sp_feiergu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity_linkEditPage extends AppCompatActivity {
    private EditText editText_linkName;
    private EditText editText_linkUrl;
    private Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_link_edit_page);

        editText_linkName = findViewById(R.id.editText_linkName);
        editText_linkUrl = findViewById(R.id.editText_linkUrl);
        button_save = findViewById(R.id.editPage_saveButton);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("linkName", editText_linkName.getText().toString());
                intent.putExtra("linkUrl", editText_linkUrl.getText().toString());
                setResult(88, intent);

                finish();
            }
        });

    }
}