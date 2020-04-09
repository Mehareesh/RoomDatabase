package com.meruga.roomdatabase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * @author Mehareesh.M
 *      Instead of creating the SQLite table, we will create the Entity.
 *      Entity is nothing but a model class annotated with @Entity.
 *      The variables of this class is our columns, and the class is our table.
 */
@Entity
public class FeatureData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "priority")
    private String priority;

    @ColumnInfo(name = "archived")
    private int archived;

    // Getters and Setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    public int getArchived() {
        return archived;
    }
}
