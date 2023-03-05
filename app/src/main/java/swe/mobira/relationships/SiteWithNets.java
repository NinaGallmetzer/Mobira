package swe.mobira.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import swe.mobira.entities.net.Net;
import swe.mobira.entities.site.Site;

public class SiteWithNets {
    @Embedded
    public Site site;

    @Relation(
            parentColumn = "siteID",
            entityColumn = "siteID"
    )

    public List<Net> nets;
}
