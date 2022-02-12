package edu.neu.madcourse.numad22sp_feiergu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

public class MainActivity_linkCollector extends AppCompatActivity {
    private FloatingActionButton floatingAddButton;

    private ArrayList<Link> linksList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String tempLinkName;
    private String tempLinkUrl;

    ActivityResultLauncher<Intent> editPageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == 88) {
                Intent intent = result.getData();
                if (intent != null) {
                    // extract data
                    tempLinkName = intent.getStringExtra("linkName");
                    tempLinkUrl = intent.getStringExtra("linkUrl");
                    if (tempLinkName != null && tempLinkUrl != null) {
                        int pos = 0;
                        addItem(pos, tempLinkName, tempLinkUrl);
                        tempLinkName = null;
                        tempLinkUrl = null;
                    }
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_link_collector);

        recyclerView = findViewById(R.id.recyclerView);

        floatingAddButton = findViewById(R.id.floatingAddButton);
        floatingAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "clicked floating add button", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(MainActivity_linkCollector.this, MainActivity_linkEditPage.class);
                editPageLauncher.launch(intent);
            }

        });

        updateData(savedInstanceState);
        setAdapter();
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = linksList == null ? 0 : linksList.size();
        outState.putInt("link_numbers", size);

        for (int i = 0; i < size; i++) {
            outState.putString("link_name" + i, linksList.get(i).getLinkName());
            outState.putString("link_url" + i, linksList.get(i).getUrl());
        }

        super.onSaveInstanceState(outState);
    }

    private void updateData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("link_numbers")) {
            if (linksList == null || linksList.size() == 0) {
                int size = savedInstanceState.getInt("link_numbers");
                for (int i = 0; i < size; i++) {
                    String linkName = savedInstanceState.getString("link_name" + i);
                    String linkUrl = savedInstanceState.getString("link_url" + i);
                    Link link = new Link(linkName, linkUrl);
                    linksList.add(link);
                }
            }
        }
    }

    private void setAdapter() {
        recyclerAdapter = new RecyclerAdapter(linksList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void addItem(int position, String name, String url) {
        linksList.add(position, new Link(name, url));
        Snackbar snackbar = Snackbar.make(recyclerView, "Added a link!", Snackbar.LENGTH_SHORT);
        snackbar.show();
        recyclerAdapter.notifyItemInserted(position);
    }
}