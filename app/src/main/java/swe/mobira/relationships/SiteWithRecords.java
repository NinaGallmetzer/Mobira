package swe.mobira.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import swe.mobira.entities.ringingrecord.RingingRecord;
import swe.mobira.entities.site.Site;

public class SiteWithRecords {
    @Embedded
    public Site site;
    @Relation(
            parentColumn = "siteID",
            entityColumn = "siteID"
    )
    public List<RingingRecord> ringingRecords;
}
