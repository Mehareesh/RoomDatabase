package com.meruga.roomdatabase.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
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
 *      Perform the create operation, which is adding a feature data to the database
 */
public class AddDataActivity extends AppCompatActivity {

    private EditText editTextType;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        editTextType = findViewById(R.id.editTextType);
        editTextName = findViewById(R.id.editTextName);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String type = editTextType.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();

        if (type.isEmpty()) {
            editTextType.setError("Type required");
            editTextType.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        // AsyncTask to perform our operation because if we will try to perform the database operation in main thread
        // it will crash our application
        @SuppressLint("StaticFieldLeak")
        class SaveFeatureData extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                // creating a featureData
                FeatureData featureData = new FeatureData();
                featureData.setType(type);
                featureData.setName(name);

                // adding to database
                DatabaseClient.getInstance(getApplicationContext()).getFeaturesDatabase().featureDataDAO().insert(featureData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveFeatureData saveFeatureData = new SaveFeatureData();
        saveFeatureData.execute();
    }
}