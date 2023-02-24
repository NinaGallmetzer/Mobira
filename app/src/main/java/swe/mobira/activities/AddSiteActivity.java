package swe.mobira.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import swe.mobira.R;

public class AddSiteActivity extends AppCompatActivity {
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

        setContentView(R.layout.activity_add_site);
        setTitle("Add Site");

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextLatitude = findViewById(R.id.edit_text_latitude);
        editTextLongitude = findViewById(R.id.edit_text_longitude);
        editTextComment = findViewById(R.id.edit_text_comment);

        // get x on left upper corner
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Button AddSiteButton = findViewById(R.id.button_add);
        AddSiteButton.setOnClickListener(view -> saveSite());
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

        Intent newSiteData = new Intent();
        newSiteData.putExtra(EXTRA_TITLE, title);
        newSiteData.putExtra(EXTRA_DESCRIPTION, description);
        newSiteData.putExtra(EXTRA_LATITUDE, latitude);
        newSiteData.putExtra(EXTRA_LONGITUDE, longitude);
        newSiteData.putExtra(EXTRA_COMMENT, comment);

        setResult(RESULT_OK, newSiteData);
        finish();
    }
}