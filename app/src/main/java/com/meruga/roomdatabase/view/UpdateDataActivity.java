package com.meruga.roomdatabase.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.meruga.roomdatabase.controller.DatabaseClient;
import com.meruga.roomdatabase.model.FeatureData;
import com.meruga.roomdatabase.R;

/**
 * @author Mehareesh.M
 *       This will perform Update and Delete operations to our database
 */
public class UpdateDataActivity extends AppCompatActivity {

    private EditText mTypeET;
    private EditText mNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        mTypeET = findViewById(R.id.editTextType);
        mNameET = findViewById(R.id.editTextName);

        final FeatureData featureData = (FeatureData) getIntent().getSerializableExtra("featureData");

        loadTask(featureData);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(featureData);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDataActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteFeatureData(featureData);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void loadTask(FeatureData task) {
        mTypeET.setText(task.getType());
        mNameET.setText(task.getName());
    }

    private void updateTask(final FeatureData featureData) {
        final String type = mTypeET.getText().toString().trim();
        final String name = mNameET.getText().toString().trim();

        if (type.isEmpty()) {
            mTypeET.setError("Type required");
            mTypeET.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            mNameET.setError("Equnr required");
            mNameET.requestFocus();
            return;
        }

        // AsyncTask to perform our operation because if we will try to perform the database operation in main thread
        // it will crash our application
        @SuppressLint("StaticFieldLeak")
        class UpdateFeatureData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                featureData.setType(type);
                featureData.setName(name);
                DatabaseClient.getInstance(getApplicationContext()).getFeaturesDatabase().featureDataDAO().update(featureData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateDataActivity.this, MainActivity.class));
            }
        }
        UpdateFeatureData updateFeatureData = new UpdateFeatureData();
        updateFeatureData.execute();
    }

    private void deleteFeatureData(final FeatureData featureData) {
        @SuppressLint("StaticFieldLeak")
        class DeleteFeatureData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getFeaturesDatabase().featureDataDAO().delete(featureData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateDataActivity.this, MainActivity.class));
            }
        }
        DeleteFeatureData deleteFeatureData = new DeleteFeatureData();
        deleteFeatureData.execute();
    }
}
