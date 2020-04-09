package com.meruga.roomdatabase.controller;

import com.meruga.roomdatabase.model.FeatureData;

public class DataBaseManager {

    private FeatureData featureData;
    private static DataBaseManager mInstance;

    public static DataBaseManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataBaseManager();
        }
        return mInstance;
    }

    public void init() {
        featureData = new FeatureData();
    }

    public void setId(int id) {
        featureData.setId(id);
    }

    public int getId() {
        return featureData.getId();
    }

    public void setType(String type) {
        featureData.setType(type);
    }

    public String getType() {
        return featureData.getType();
    }

    public void setName(String name) {
        featureData.setName(name);
    }

    public String getName() {
        return featureData.getName();
    }

    public void setStatus(String status) {
        featureData.setStatus(status);
    }

    public String getStatus() {
        return featureData.getStatus();
    }

    public void setPriority(String priority) {
        featureData.setPriority(priority);
    }

    public String getPriority() {
        return featureData.getPriority();
    }

    public void setArchived(int archived) {
        featureData.setArchived(archived);
    }

    public int getArchived() {
        return featureData.getArchived();
    }
}
