package swe.mobira.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import swe.mobira.entities.RingingRecord;
import swe.mobira.entities.Site;

public class SiteWithRecords {
    @Embedded
    public Site site;
    @Relation(
            parentColumn = "siteID",
            entityColumn = "siteID"
    )
    public List<RingingRecord> ringingRecords;
}
