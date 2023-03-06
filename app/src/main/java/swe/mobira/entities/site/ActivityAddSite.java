package swe.mobira.entities.site;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import swe.mobira.R;

public class ActivityAddSite extends AppCompatActivity {
    private TextInputEditText editTextTitle;
    private TextInputEditText editTextDescription;
    private TextInputEditText editTextLatitude;
    private TextInputEditText editTextLongitude;
    private TextInputEditText editTextComment;

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

        FloatingActionButton buttonSave = findViewById(R.id.button_add);
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

        Site newSite = new Site(title, description, latitude, longitude, comment);

        Intent intent = new Intent();
        intent.putExtra(ActivityListSites.EXTRA_SITE, newSite);

        setResult(RESULT_OK, intent);
        finish();
    }
}