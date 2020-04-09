package com.meruga.roomdatabase.controller;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * @author Mehareesh.M
 *      Creating FeaturesDatabase’s object is expensive so we will create a single instance of it.
 */
public class DatabaseClient {

    private static DatabaseClient mInstance;

    //our app database object
    private FeaturesDatabase featuresDatabase;

    private DatabaseClient(Context context) {
        //creating the app database with Room database builder
        //MyFeatureDataDoa is the name of the database
        featuresDatabase = Room.databaseBuilder(context, FeaturesDatabase.class, "MyFeatureDataDoa").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public FeaturesDatabase getFeaturesDatabase() {
        return featuresDatabase;
    }
}
