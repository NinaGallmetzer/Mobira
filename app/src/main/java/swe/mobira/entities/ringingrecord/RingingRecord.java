package swe.mobira.entities.ringingrecord;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import swe.mobira.entities.site.Site;

@Entity(foreignKeys = {@ForeignKey(entity = Site.class,
        parentColumns = "siteID", childColumns = "siteID",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
public class RingingRecord implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int recordID;
    private int siteID;
    private String recordDate;
    private String startTime;
    private String endTime;
    private double startTemperature;
    private double endTemperature;
    private String weather;
    private int wind;
    private String coordinator;
    private String comment;

    public RingingRecord(int siteID, String recordDate, String startTime, String endTime, double startTemperature, double endTemperature, String weather, int wind, String coordinator, String comment) {
        this.siteID = siteID;
        this.recordDate = recordDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTemperature = startTemperature;
        this.endTemperature = endTemperature;
        this.weather = weather;
        this.wind = wind;
        this.coordinator = coordinator;
        this.comment = comment;
    }

    @Ignore
    public RingingRecord(int recordID, int siteID, String recordDate, String startTime, String endTime, double startTemperature, double endTemperature, String weather, int wind, String coordinator, String comment) {
        this.recordID = recordID;
        this.siteID = siteID;
        this.recordDate = recordDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTemperature = startTemperature;
        this.endTemperature = endTemperature;
        this.weather = weather;
        this.wind = wind;
        this.coordinator = coordinator;
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

    public int getWind() {return wind; }

    public String getCoordinator() {return coordinator; }

    public String getComment() {
        return comment;
    }

    public void setRecordID(int recordID) {this.recordID = recordID; }


    // make Site parcelable //

    public RingingRecord (Parcel in) {
        String[] data = new String[11];
        in.readStringArray(data);
        this.recordID = Integer.parseInt(data[0]);
        this.siteID = Integer.parseInt(data[1]);
        this.recordDate = data[2];
        this.startTime = data[3];
        this.endTime = data[4];
        this.startTemperature = Double.parseDouble(data[5]);
        this.endTemperature = Double.parseDouble(data[6]);
        this.weather = data[7];
        this.wind = Integer.parseInt(data[8]);
        this.coordinator = data[9];
        this.comment = data[10];
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                String.valueOf(this.recordID),
                String.valueOf(this.siteID),
                this.recordDate,
                this.startTime,
                this.endTime,
                String.valueOf(this.startTemperature),
                String.valueOf(this.endTemperature),
                this.weather,
                String.valueOf(this.wind),
                this.coordinator,
                this.comment


        });
    }

    public static final Parcelable.Creator<RingingRecord> CREATOR = new Parcelable.Creator<RingingRecord>() {
        @Override
        public RingingRecord createFromParcel(Parcel parcel) {
            return new RingingRecord(parcel);
        }

        @Override
        public RingingRecord[] newArray(int i) {
            return new RingingRecord[1];
        }
    };
}
