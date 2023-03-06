package swe.mobira.entities.site;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import swe.mobira.R;

public class ActivityEditSite extends AppCompatActivity {
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";

    private Site currentSite;

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // get currentSiteData that got passed from prev screen > load edit-site-screen
        setContentView(R.layout.activity_edit_site);
        setTitle("Edit Site");

        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextLatitude = findViewById(R.id.edit_text_latitude);
        editTextLongitude = findViewById(R.id.edit_text_longitude);
        editTextComment = findViewById(R.id.edit_text_comment);

        // get home logo on left upper corner
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24);

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // fill text fields
        editTextTitle.setText(currentSite.getTitle());
        editTextDescription.setText(currentSite.getDescription());
        editTextLatitude.setText(String.valueOf(currentSite.getLatitude()));
        editTextLongitude.setText(String.valueOf(currentSite.getLongitude()));
        editTextComment.setText(currentSite.getComment());

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
        double latitude =  Double.parseDouble(editTextLatitude.getText().toString());
        double longitude = Double.parseDouble(editTextLongitude.getText().toString());
        String comment = editTextComment.getText().toString();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a site title", Toast.LENGTH_SHORT).show();
            return;
        }

        Site updatedSite = new Site(currentSite.getSiteID(), title, description, latitude, longitude, comment);

        Intent intent = new Intent();
        intent.putExtra(ActivityShowSiteDetails.EXTRA_SITE, updatedSite);

        setResult(RESULT_OK, intent);
        finish();
    }
}