package swe.mobira.entities.birdrecord;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import swe.mobira.entities.ringingrecord.RingingRecord;

@Entity(foreignKeys = {@ForeignKey(entity = RingingRecord.class,
        parentColumns = "ringingRecordID", childColumns = "ringingRecordID",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE)})
public class BirdRecord implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int birdRecordID;
    private final int ringingRecordID;
    private final String time;
    private final int netNumber;
    private final String netSide;
    private final int shelfNumber;
    private final String species;
    private final boolean recapture;
    private final String ringNumber;
    private final String topLeftColor;
    private final String bottomLeftColor;
    private final String topRightColor;
    private final String bottomRightColor;
    private final int sex;
    private final int age;
    private final int fat;
    private final int muscle;
    private final double tarsus;
    private final double wingLength;
    private final double featherLength;
    private final double weight;
    private final String pictureNumber;
    private final String ringer;
    private final String comment;

    public BirdRecord(int ringingRecordID, String time, int netNumber, String netSide, int shelfNumber, String species, boolean recapture, String ringNumber, String topLeftColor, String bottomLeftColor, String topRightColor, String bottomRightColor, int sex, int age, int fat, int muscle, double tarsus, double wingLength, double featherLength, double weight, String pictureNumber, String ringer, String comment) {
        this.ringingRecordID = ringingRecordID;
        this.time = time;
        this.netNumber = netNumber;
        this.netSide = netSide;
        this.shelfNumber = shelfNumber;
        this.species = species;
        this.recapture = recapture;
        this.ringNumber = ringNumber;
        this.topLeftColor = topLeftColor;
        this.bottomLeftColor = bottomLeftColor;
        this.topRightColor = topRightColor;
        this.bottomRightColor = bottomRightColor;
        this.sex = sex;
        this.age = age;
        this.fat = fat;
        this.muscle = muscle;
        this.tarsus = tarsus;
        this.wingLength = wingLength;
        this.featherLength = featherLength;
        this.weight = weight;
        this.pictureNumber = pictureNumber;
        this.ringer = ringer;
        this.comment = comment;
    }
    @Ignore
    public BirdRecord(int birdRecordID, int ringingRecordID, String time, int netNumber, String netSide, int shelfNumber, String species, boolean recapture, String ringNumber, String topLeftColor, String bottomLeftColor, String topRightColor, String bottomRightColor, int sex, int age, int fat, int muscle, double tarsus, double wingLength, double featherLength, double weight, String pictureNumber, String ringer, String comment) {
            this.birdRecordID = birdRecordID;
        this.ringingRecordID = ringingRecordID;
        this.time = time;
        this.netNumber = netNumber;
        this.netSide = netSide;
        this.shelfNumber = shelfNumber;
        this.species = species;
        this.recapture = recapture;
        this.ringNumber = ringNumber;
        this.topLeftColor = topLeftColor;
        this.bottomLeftColor = bottomLeftColor;
        this.topRightColor = topRightColor;
        this.bottomRightColor = bottomRightColor;
        this.sex = sex;
        this.age = age;
        this.fat = fat;
        this.muscle = muscle;
        this.tarsus = tarsus;
        this.wingLength = wingLength;
        this.featherLength = featherLength;
        this.weight = weight;
        this.pictureNumber = pictureNumber;
        this.ringer = ringer;
        this.comment = comment;
    }

    public int getBirdRecordID() {
        return birdRecordID;
    }

    public int getRingingRecordID() {
        return ringingRecordID;
    }

    public String getTime() {
        return time;
    }

    public int getNetNumber() {
        return netNumber;
    }

    public String getNetSide() {
        return netSide;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isRecapture() {
        return recapture;
    }

    public String getRingNumber() {
        return ringNumber;
    }

    public String getTopLeftColor() {
        return topLeftColor;
    }

    public String getBottomLeftColor() {
        return bottomLeftColor;
    }

    public String getTopRightColor() {
        return topRightColor;
    }

    public String getBottomRightColor() {
        return bottomRightColor;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public int getFat() {
        return fat;
    }

    public int getMuscle() {
        return muscle;
    }

    public double getTarsus() {
        return tarsus;
    }

    public double getWingLength() {
        return wingLength;
    }

    public double getFeatherLength() {
        return featherLength;
    }

    public double getWeight() {
        return weight;
    }

    public String getPictureNumber() {
        return pictureNumber;
    }

    public String getRinger() {
        return ringer;
    }

    public String getComment() {
        return comment;
    }

    public void setBirdRecordID(int birdRecordID) {
        this.birdRecordID = birdRecordID;
    }

    // make parcelable //

    public BirdRecord(Parcel in) {
        String[] data = new String[24];
        in.readStringArray(data);
        this.birdRecordID = Integer.parseInt(data[0]);
        this.ringingRecordID = Integer.parseInt(data[1]);
        this.time = data[2];
        this.netNumber = Integer.parseInt(data[3]);
        this.netSide = data[4];
        this.shelfNumber = Integer.parseInt(data[5]);
        this.species = data[6];
        this.recapture = Boolean.parseBoolean(data[7]);
        this.ringNumber = data[8];
        this.topLeftColor = data[9];
        this.bottomLeftColor = data[10];
        this.topRightColor = data[11];
        this.bottomRightColor = data[12];
        this.sex = Integer.parseInt(data[13]);
        this.age = Integer.parseInt(data[14]);
        this.fat = Integer.parseInt(data[15]);
        this.muscle = Integer.parseInt(data[16]);
        this.tarsus = Double.parseDouble(data[17]);
        this.wingLength = Double.parseDouble(data[18]);
        this.featherLength = Double.parseDouble(data[19]);
        this.weight = Double.parseDouble(data[20]);
        this.pictureNumber = data[21];
        this.ringer = data[22];
        this.comment = data[23];

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                String.valueOf(this.birdRecordID),
                String.valueOf(this.ringingRecordID),
                this.time,
                String.valueOf(this.netNumber),
                this.netSide,
                String.valueOf(this.shelfNumber),
                this.species,
                String.valueOf(this.recapture),
                this.ringNumber,
                this.topLeftColor,
                this.bottomLeftColor,
                this.topRightColor,
                this.bottomRightColor,
                String.valueOf(this.sex),
                String.valueOf(this.age),
                String.valueOf(this.fat),
                String.valueOf(this.muscle),
                String.valueOf(this.tarsus),
                String.valueOf(this.wingLength),
                String.valueOf(this.featherLength),
                String.valueOf(this.weight),
                this.pictureNumber,
                this.ringer,
                this.comment
        });

    }

    public static final Parcelable.Creator<BirdRecord> CREATOR = new Parcelable.Creator<BirdRecord>() {

        @Override
        public BirdRecord createFromParcel(Parcel parcel) {
            return new BirdRecord(parcel);
        }

        @Override
        public BirdRecord[] newArray(int i) {
            return new BirdRecord[1];
        }
    };

}
