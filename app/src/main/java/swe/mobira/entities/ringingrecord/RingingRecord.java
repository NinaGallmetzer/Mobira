package swe.mobira.entities.ringingrecord;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import swe.mobira.entities.site.Site;

@Entity(foreignKeys = {@ForeignKey(entity = Site.class,
        parentColumns = "siteID", childColumns = "siteID",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class RingingRecord {
    @PrimaryKey(autoGenerate = true)
    private int recordID;
    private int siteID;
    private String recordDate;
    private String startTime;
    private String endTime;
    private double startTemperature;
    private double endTemperature;
    private String weather;
    private String comment;

    public RingingRecord(int siteID, String recordDate, String startTime, String endTime, double startTemperature, double endTemperature, String weather, String comment) {
        this.siteID = siteID;
        this.recordDate = recordDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTemperature = startTemperature;
        this.endTemperature = endTemperature;
        this.weather = weather;
        this.comment = comment;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getSiteID() {
        return siteID;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public double getStartTemperature() {
        return startTemperature;
    }

    public double getEndTemperature() {
        return endTemperature;
    }

    public String getWeather() {
        return weather;
    }

    public String getComment() {
        return comment;
    }

    public void setRecordID(int recordID) {this.recordID = recordID; }
}
