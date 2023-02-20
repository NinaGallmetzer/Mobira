package swe.mobira;

import android.util.EventLogTags;

import androidx.annotation.NonNull;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "site_table")
public class Site {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private String comment;

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public String getDescription() {return description; }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getComment() {
        return comment;
    }

    public Site(@NonNull String title, String description,  double latitude, double longitude, String comment) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }
}
