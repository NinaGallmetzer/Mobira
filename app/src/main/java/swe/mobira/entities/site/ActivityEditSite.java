package swe.mobira.entities.site;

import static swe.mobira.MainActivity.EXTRA_SITE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import swe.mobira.R;
import swe.mobira.entities.ringingrecord.RingingRecordViewModel;

public class ActivityEditSite extends AppCompatActivity {
    private SiteViewModel siteViewModel;
    private Site currentSite;

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextHabitatType;
    private EditText editTextAltitude;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // get currentSiteData that got passed from prev screen > load edit-site-screen
        setContentView(R.layout.activity_add_edit_site);
        setTitle("Edit Site");

        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);

        editTextTitle = findViewById(R.id.edit_text_site_title);
        editTextDescription = findViewById(R.id.edit_text_site_description);
        editTextHabitatType = findViewById(R.id.edit_text_site_habitat_type);
        editTextAltitude = findViewById(R.id.edit_text_site_altitude);
        editTextLatitude = findViewById(R.id.edit_text_site_latitude);
        editTextLongitude = findViewById(R.id.edit_text_site_longitude);
        editTextComment = findViewById(R.id.edit_text_site_comment);

        // get home logo on left upper corner
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24);

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // fill text fields
        editTextTitle.setText(currentSite.getTitle());
        editTextDescription.setText(currentSite.getDescription());
        editTextHabitatType.setText(currentSite.getHabitatType());
        editTextAltitude.setText(String.valueOf(currentSite.getAltitude()));
        editTextLatitude.setText(String.valueOf(currentSite.getLatitude()));
        editTextLongitude.setText(String.valueOf(currentSite.getLongitude()));
        editTextComment.setText(currentSite.getComment());

        FloatingActionButton buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSite();
            }
        });
    }

    private void updateSite() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String habitatType = editTextHabitatType.getText().toString();
        double altitude = Double.parseDouble(editTextAltitude.getText().toString());
        double latitude =  Double.parseDouble(editTextLatitude.getText().toString());
        double longitude = Double.parseDouble(editTextLongitude.getText().toString());
        String comment = editTextComment.getText().toString();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a site title", Toast.LENGTH_SHORT).show();
            return;
        }

        Site updatedSite = new Site(currentSite.getSiteID(), title, description, habitatType, altitude, latitude, longitude, comment);

        siteViewModel.updateSite(updatedSite);

        setResult(RESULT_OK);
        finish();
    }
}