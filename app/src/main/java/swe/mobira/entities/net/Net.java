package swe.mobira.entities.net;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import swe.mobira.entities.site.Site;

@Entity(foreignKeys = {@ForeignKey(entity = Site.class,
        parentColumns = "siteID", childColumns = "siteID",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)}, indices = {@Index("siteID")})
public class Net implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int netID;
    private final int siteID;
    private final int netNumber;
    private final String description;
    private final int meshSize;
    private final int length;
    private final int height;
    private final int shelves;
    private final double latitude;
    private final double longitude;
    private final String habitatType;
    private final String comment;

    public Net(int siteID, int netNumber, String description, int meshSize, int length, int height, int shelves, double latitude, double longitude, String habitatType, String comment) {
        this.siteID = siteID;
        this.netNumber = netNumber;
        this.description = description;
        this.meshSize = meshSize;
        this.length = length;
        this.height = height;
        this.shelves = shelves;
        this.latitude = latitude;
        this.longitude = longitude;
        this.habitatType = habitatType;
        this.comment = comment;
    }

    @Ignore
    public Net(int netID, int siteID, int netNumber, String description, int meshSize, int length, int height, int shelves, double latitude, double longitude, String habitatType, String comment) {
        this.netID = netID;
        this.siteID = siteID;
        this.netNumber = netNumber;
        this.description = description;
        this.meshSize = meshSize;
        this.length = length;
        this.height = height;
        this.shelves = shelves;
        this.latitude = latitude;
        this.longitude = longitude;
        this.habitatType = habitatType;
        this.comment = comment;
    }

    public int getNetID() {
        return netID;
    }

    public int getSiteID() {
        return siteID;
    }

    public int getNetNumber() {
        return netNumber;
    }

    public String getDescription() {
        return description;
    }

    public int getMeshSize() {
        return meshSize;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getShelves() {
        return shelves;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getHabitatType() {
        return habitatType;
    }

    public String getComment() {
        return comment;
    }

    public void setNetID(int netID) {
        this.netID = netID;
    }

    // make parcelable //

    public Net (Parcel in) {
        String[] data = new String[12];
        in.readStringArray(data);
        this.netID = Integer.parseInt(data[0]);
        this.siteID = Integer.parseInt(data[1]);
        this.netNumber = Integer.parseInt(data[2]);
        this.description = data[3];
        this.meshSize = Integer.parseInt(data[4]);
        this.length = Integer.parseInt(data[5]);
        this.height = Integer.parseInt(data[6]);
        this.shelves = Integer.parseInt(data[7]);
        this.latitude = Double.parseDouble(data[8]);
        this.longitude = Double.parseDouble(data[9]);
        this.habitatType = data[10];
        this.comment = data[11];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                String.valueOf(this.netID),
                String.valueOf(this.siteID),
                String.valueOf(this.netNumber),
                this.description,
                String.valueOf(this.meshSize),
                String.valueOf(this.length),
                String.valueOf(this.height),
                String.valueOf(this.shelves),
                String.valueOf(this.latitude),
                String.valueOf(this.longitude),
                this.habitatType,
                this.comment
        });
    }

    public static final Parcelable.Creator<Net> CREATOR = new Parcelable.Creator<Net>() {

        @Override
        public Net createFromParcel(Parcel parcel) {
            return new Net(parcel);
        }

        @Override
        public Net[] newArray(int i) {
            return new Net[1];
        }
    };

}
