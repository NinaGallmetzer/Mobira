package swe.mobira.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import swe.mobira.entities.site.SiteAdapter;
import swe.mobira.entities.site.SiteViewModel;
import swe.mobira.R;
import swe.mobira.entities.site.Site;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_SITE_ACTIVITY_REQUEST_CODE = 1;
    private SiteViewModel siteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
        // find RecyclerView in activity_main.xml
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SiteAdapter adapter = new SiteAdapter();
        recyclerView.setAdapter(adapter);

        // VIEW MODEL (https://www.youtube.com/watch?v=JLwW5HivZg4)
        // don't create new ViewModel (otherwise new view model for every new activity of app, but
        // one app just has one view model)
        // let Android handle view models with ViewModelProvider > Android decides whether to
        // create new view model (first activity of the app) or use existing view model
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);

        // RECYCLERVIEW + ADAPTER (https://www.youtube.com/watch?v=reSPN7mgshI)
        // LiveData in ViewModel is observed, every time data changes > Adapter gets updated
        siteViewModel.getAllSites().observe(this, new Observer<List<Site>>() {
            @Override
            public void onChanged(List<Site> sites) {
                adapter.setSites(sites);
            }
        });

        // create intent that waits for data (gets them, when activity ends)
        FloatingActionButton buttonAddSite = findViewById(R.id.button_add_site);
        buttonAddSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddSiteActivity.class);
                startActivityForResult(intent, ADD_SITE_ACTIVITY_REQUEST_CODE);
            }
        });

        setTitle("Sites");

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // start activity with intent (from > to), collect information from selected site (incl id) and pass to edit screen
        adapter.setOnItemClickListener(new SiteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Site site) {
                Intent intent = new Intent(MainActivity.this, ShowSiteDetailsActivity.class);
                intent.putExtra(ShowSiteDetailsActivity.EXTRA_SITE, site);
                intent.putExtra(ShowSiteDetailsActivity.EXTRA_ID, site.getSiteID());
                intent.putExtra(ShowSiteDetailsActivity.EXTRA_TITLE, site.getTitle());
                intent.putExtra(ShowSiteDetailsActivity.EXTRA_DESCRIPTION, site.getDescription());
                // lat and long need to be passed along as stings
                intent.putExtra(ShowSiteDetailsActivity.EXTRA_LATITUDE, String.valueOf(site.getLatitude()));
                intent.putExtra(ShowSiteDetailsActivity.EXTRA_LONGITUDE, String.valueOf(site.getLongitude()));
                intent.putExtra(ShowSiteDetailsActivity.EXTRA_COMMENT, site.getComment());
                startActivity(intent);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // if "activity result" originates from add activity (requestCode = add_site...) >
        // create site using data from intent
        if (requestCode == ADD_SITE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddSiteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddSiteActivity.EXTRA_DESCRIPTION);
            // in intent/data, lat and long are stored as strings and need to be converted first
            double latitude = Double.parseDouble(data.getStringExtra(AddSiteActivity.EXTRA_LATITUDE));
            double longitude = Double.parseDouble(data.getStringExtra(AddSiteActivity.EXTRA_LONGITUDE));
            String comment = data.getStringExtra(AddSiteActivity.EXTRA_COMMENT);

            Site site = new Site(title, description, latitude, longitude, comment);
            siteViewModel.insertSite(site);
            Toast.makeText(getApplicationContext(), "Site saved", Toast.LENGTH_LONG).show();
        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // if "activity result" originates from edit activity (requestCode = edit_site...) >
        // update site using data from intent
        } else {
            Toast.makeText(getApplicationContext(), "Site not saved", Toast.LENGTH_LONG).show();
        }
    }

}
