package com.meruga.roomdatabase.controller;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.meruga.roomdatabase.model.FeatureData;

import java.util.List;

/**
 * @author Mehareesh.M
 *      Data Access Object - It is an interface that defines all the operations that we need to perform in our database.
 */
@Dao
public interface FeatureDataDAO {

    @Query("SELECT * FROM featuredata")
    List<FeatureData> getAllFeatures();

    @Insert
    void insert(FeatureData featureData);

    @Delete
    void delete(FeatureData featureData);

    @Update
    void update(FeatureData featureData);

}
