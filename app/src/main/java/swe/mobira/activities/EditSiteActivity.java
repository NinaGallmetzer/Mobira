package swe.mobira.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import swe.mobira.R;

public class EditSiteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "swe.mobira.EXTRA_ID";
    public static final String EXTRA_TITLE = "swe.mobira.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "swe.mobira.EXTRA_DESCRIPTION";
    public static final String EXTRA_LATITUDE = "swe.mobira.EXTRA_LATITUDE";
    public static final String EXTRA_LONGITUDE = "swe.mobira.EXTRA_LONGITUDE";
    public static final String EXTRA_COMMENT = "swe.mobira.EXTRA_COMMENT";
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

        Intent currentSiteData = getIntent();

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextLatitude = findViewById(R.id.edit_text_latitude);
        editTextLongitude = findViewById(R.id.edit_text_longitude);
        editTextComment = findViewById(R.id.edit_text_comment);

        // get home logo on left upper corner
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24);

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // fill text fields

        editTextTitle.setText(currentSiteData.getStringExtra(EXTRA_TITLE));
        editTextDescription.setText(currentSiteData.getStringExtra(EXTRA_DESCRIPTION));
        editTextLatitude.setText(currentSiteData.getStringExtra(EXTRA_LATITUDE));
        editTextLongitude.setText(currentSiteData.getStringExtra(EXTRA_LONGITUDE));
        editTextComment.setText(currentSiteData.getStringExtra(EXTRA_COMMENT));

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
        String latitude =  editTextLatitude.getText().toString();
        String longitude = editTextLongitude.getText().toString();
        String comment = editTextComment.getText().toString();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a site title", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent currentSiteData = new Intent();
        currentSiteData.putExtra(EXTRA_TITLE, title);
        currentSiteData.putExtra(EXTRA_DESCRIPTION, description);
        currentSiteData.putExtra(EXTRA_LATITUDE, latitude);
        currentSiteData.putExtra(EXTRA_LONGITUDE, longitude);
        currentSiteData.putExtra(EXTRA_COMMENT, comment);

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // extract id from intent (if exists, otherwise = -1) and add to currentSiteData
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            currentSiteData.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, currentSiteData);
        finish();
    }
}