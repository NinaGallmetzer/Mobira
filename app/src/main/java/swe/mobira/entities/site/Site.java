package swe.mobira.entities.site;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity
public class Site implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int siteID;
    @NonNull
    private final String title;
    private final String description;
    private final String habitatType;
    private final double altitude;
    private final double latitude;
    private final double longitude;
    private final String comment;

    public Site(@NonNull String title, String description, String habitatType, double altitude, double latitude, double longitude, String comment) {
        this.title = title;
        this.description = description;
        this.habitatType = habitatType;
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comment = comment;
    }

    @Ignore
    public Site(int siteID, @NonNull String title, String description, String habitatType, double altitude, double latitude, double longitude, String comment) {
        this.siteID = siteID;
        this.title = title;
        this.description = description;
        this.habitatType = habitatType;
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comment = comment;
    }

    public int getSiteID() {
        return siteID;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public String getDescription() {return description; }

    public String getHabitatType() {return habitatType; }

    public double getAltitude() {
        return altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getComment() {
        return comment;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }


    // make Site parcelable //

    public Site (Parcel in) {
        String[] data = new String[8];
        in.readStringArray(data);
        this.siteID = Integer.parseInt(data[0]);
        this.title = data[1];
        this.description = data[2];
        this.habitatType = data[3];
        this.altitude = Double.parseDouble(data[4]);
        this.latitude = Double.parseDouble(data[5]);
        this.longitude = Double.parseDouble(data[6]);
        this.comment = data[7];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                String.valueOf(this.siteID),
                this.title,
                this.description,
                this.habitatType,
                String.valueOf(this.altitude),
                String.valueOf(this.latitude),
                String.valueOf(this.longitude),
                this.comment
        });
    }

    public static final Parcelable.Creator<Site> CREATOR = new Parcelable.Creator<Site>() {
        @Override
        public Site createFromParcel(Parcel parcel) {
            return new Site(parcel);
        }

        @Override
        public Site[] newArray(int i) {
            return new Site[i];
        }
    };
}
