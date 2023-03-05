package swe.mobira.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import swe.mobira.entities.birdrecords.BirdRecord;
import swe.mobira.entities.ringingrecord.RingingRecord;

public class RingingRecordWithBirdRecords {
    @Embedded
    public RingingRecord ringingRecord;

    @Relation(
            parentColumn = "ringingRecordID",
            entityColumn = "ringingRecordID"
    )

    public List<BirdRecord> birdRecords;
}
