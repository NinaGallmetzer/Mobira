package swe.mobira;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_SITE_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_SITE_ACTIVITY_REQUEST_CODE = 2;
    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        AppAdapter adapter = new AppAdapter();
        recyclerView.setAdapter(adapter);

        appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        appViewModel.getAllSites().observe(this, sites -> adapter.setSites(sites));

        // create intent that waits for data (gets them, when activity ends)
        FloatingActionButton buttonAddSite = findViewById(R.id.button_add_site);
        buttonAddSite.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, AddEditSiteActivity.class);
            startActivityForResult(intent, ADD_SITE_ACTIVITY_REQUEST_CODE);
        });

        setTitle("Sites");

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // start activity, collect information from selected site (incl id) and pass to add/edit screen
        adapter.setOnItemClickListener(new AppAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Site site) {
                Intent intent = new Intent(MainActivity.this, AddEditSiteActivity.class);
                intent.putExtra(AddEditSiteActivity.EXTRA_ID, site.getId());
                intent.putExtra(AddEditSiteActivity.EXTRA_TITLE, site.getTitle());
                intent.putExtra(AddEditSiteActivity.EXTRA_DESCRIPTION, site.getDescription());
                // lat and long need to be passed along as stings
                intent.putExtra(AddEditSiteActivity.EXTRA_LATITUDE, String.valueOf(site.getLatitude()));
                intent.putExtra(AddEditSiteActivity.EXTRA_LONGITUDE, String.valueOf(site.getLongitude()));
                intent.putExtra(AddEditSiteActivity.EXTRA_COMMENT, site.getComment());
                startActivityForResult(intent, EDIT_SITE_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if "activity result" originates from edit activity (requestCode = add_site...) >
        // create site using data from intent
        if (requestCode == ADD_SITE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditSiteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditSiteActivity.EXTRA_DESCRIPTION);
            // in intent/data, lat and long are stored as strings and need to be converted first
            double latitude = Double.parseDouble(data.getStringExtra(AddEditSiteActivity.EXTRA_LATITUDE));
            double longitude = Double.parseDouble(data.getStringExtra(AddEditSiteActivity.EXTRA_LONGITUDE));
            String comment = data.getStringExtra(AddEditSiteActivity.EXTRA_COMMENT);

            Site site = new Site(title, description, latitude, longitude, comment);
            appViewModel.insertSite(site);
            Toast.makeText(getApplicationContext(), "Site saved", Toast.LENGTH_LONG).show();
        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // if "activity result" originates from edit activity (requestCode = edit_site...) >
        // update site using data from intent
        } else if (requestCode == EDIT_SITE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddEditSiteActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(getApplicationContext(), "Site can't be updated", Toast.LENGTH_LONG).show();
                return;
            }
            String title = data.getStringExtra(AddEditSiteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditSiteActivity.EXTRA_DESCRIPTION);
            // in intent/data, lat and long are stored as strings and need to be converted first
            double latitude = Double.parseDouble(data.getStringExtra(AddEditSiteActivity.EXTRA_LATITUDE));
            double longitude = Double.parseDouble(data.getStringExtra(AddEditSiteActivity.EXTRA_LONGITUDE));
            String comment = data.getStringExtra(AddEditSiteActivity.EXTRA_COMMENT);

            Site site = new Site(title, description, latitude, longitude, comment);
            site.setId(id);
            appViewModel.updateSite(site);
            Toast.makeText(getApplicationContext(), "Site saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Site not saved", Toast.LENGTH_LONG).show();
        }
    }

}
