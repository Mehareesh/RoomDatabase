package com.meruga.roomdatabase.controller;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.meruga.roomdatabase.model.FeatureData;

/**
 * @author Mehareesh.M
 *      It is an abstract class where we define all our entities and the database version.
 */
@Database(entities = {FeatureData.class}, version = 1)
public abstract class FeaturesDatabase extends RoomDatabase {
    public abstract FeatureDataDAO featureDataDAO();
}
