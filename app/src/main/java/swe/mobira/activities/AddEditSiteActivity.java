package swe.mobira.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import swe.mobira.R;

public class AddEditSiteActivity extends AppCompatActivity {
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
        // get intent that got passed from prev screen (through click on either
        // floatingActionButton or specific site) and check if it contains an id
        // if contains id > comes from edit and contains note > load edit site screen,
        // else load add site screen

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setContentView(R.layout.activity_edit_site);
        } else {
            setContentView(R.layout.activity_add_site);
        }

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextLatitude = findViewById(R.id.edit_text_latitude);
        editTextLongitude = findViewById(R.id.edit_text_longitude);
        editTextComment = findViewById(R.id.edit_text_comment);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // check if intent that got passed from prev screen (through click on either
        // floatingActionButton or specific site) contains an id
        // if contains id > comes from edit and contains note > fill text fields,
        // else keep text fields empty
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Site");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextLatitude.setText(intent.getStringExtra(EXTRA_LATITUDE));
            editTextLongitude.setText(intent.getStringExtra(EXTRA_LONGITUDE));
            editTextComment.setText(intent.getStringExtra(EXTRA_COMMENT));
        } else {
            setTitle("Add Site");
        }

        Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> saveSite());
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

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_LATITUDE, latitude);
        data.putExtra(EXTRA_LONGITUDE, longitude);
        data.putExtra(EXTRA_COMMENT, comment);

        // EDIT SITES ON ITEM CLICK (https://www.youtube.com/watch?v=dYbbTGiZ2sA)
        // extract id from intent (if exists otherwise = -1) and add to data
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}