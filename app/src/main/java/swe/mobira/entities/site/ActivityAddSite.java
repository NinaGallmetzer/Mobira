package swe.mobira.entities.site;

import static swe.mobira.MainActivity.EXTRA_SITE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import swe.mobira.R;

public class ActivityAddSite extends AppCompatActivity {
    private SiteViewModel siteViewModel;

    private TextInputEditText editTextTitle;
    private TextInputEditText editTextDescription;
    private TextInputEditText editTextHabitatType;
    private TextInputEditText editTextAltitude;
    private TextInputEditText editTextLatitude;
    private TextInputEditText editTextLongitude;
    private TextInputEditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_site);
        setTitle("Add Site");

        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);

        editTextTitle = findViewById(R.id.edit_text_site_title);
        editTextDescription = findViewById(R.id.edit_text_site_description);
        editTextHabitatType = findViewById(R.id.edit_text_site_habitat_type);
        editTextAltitude = findViewById(R.id.edit_text_site_altitude);
        editTextLatitude = findViewById(R.id.edit_text_site_latitude);
        editTextLongitude = findViewById(R.id.edit_text_site_longitude);
        editTextComment = findViewById(R.id.edit_text_site_comment);

        FloatingActionButton buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSite();
            }
        });
    }

    private void saveSite() {
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

        Site newSite = new Site(title, description, habitatType, altitude, latitude, longitude, comment);

        siteViewModel.insertSite(newSite);

        setResult(RESULT_OK);
        finish();
    }
}