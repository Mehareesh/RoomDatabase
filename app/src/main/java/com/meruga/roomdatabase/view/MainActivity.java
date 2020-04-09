package com.meruga.roomdatabase.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.meruga.roomdatabase.controller.DatabaseClient;
import com.meruga.roomdatabase.model.FeatureData;
import com.meruga.roomdatabase.util.FeatureDataAdapter;
import com.meruga.roomdatabase.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView_data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
                startActivity(intent);
            }
        });
        getFeaturesData();
    }

    private void getFeaturesData() {
        @SuppressLint("StaticFieldLeak")
        class GetFeaturesData extends AsyncTask<Void, Void, List<FeatureData>> {

            @Override
            protected List<FeatureData> doInBackground(Void... voids) {
                return DatabaseClient.getInstance(getApplicationContext()).getFeaturesDatabase().featureDataDAO().getAllFeatures();
            }

            @Override
            protected void onPostExecute(List<FeatureData> tasks) {
                super.onPostExecute(tasks);
                FeatureDataAdapter adapter = new FeatureDataAdapter(MainActivity.this, tasks);
                mRecyclerView.setAdapter(adapter);
            }
        }
        GetFeaturesData featuresData = new GetFeaturesData();
        featuresData.execute();
    }
}