package swe.mobira.entities.site;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity
public class Site implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int siteID;
    @NonNull
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private String comment;

    public Site(@NonNull String title, String description,  double latitude, double longitude, String comment) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comment = comment;
    }

    @Ignore
    public Site(int siteID, @NonNull String title, String description,  double latitude, double longitude, String comment) {
        this.siteID = siteID;
        this.title = title;
        this.description = description;
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

    public Site (Parcel in) {
        String[] data = new String[6];

        in.readStringArray(data);
        this.siteID = Integer.parseInt(data[0]);
        this.title = data[1];
        this.description = data[2];
        this.latitude = Double.parseDouble(data[3]);
        this.longitude = Double.parseDouble(data[4]);
        this.comment = data[5];
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
