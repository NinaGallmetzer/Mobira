package swe.mobira.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import swe.mobira.R;

public class ShowSiteDetailsActivity extends AppCompatActivity {

    ExtendedFloatingActionButton actionsFab;
    FloatingActionButton showRecordsFab, editSiteFab, deleteSiteFab;
    TextView showRecordsActionText, editSiteActionText, deleteSiteActionText;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

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
        setContentView(R.layout.activity_show_site_details);
        setTitle("Site Details");
        setUpActionButtons();

        Intent currentSiteData = getIntent();

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextLatitude = findViewById(R.id.edit_text_latitude);
        editTextLongitude = findViewById(R.id.edit_text_longitude);
        editTextComment = findViewById(R.id.edit_text_comment);

        editTextTitle.setText(currentSiteData.getStringExtra(EXTRA_TITLE));
        editTextDescription.setText(currentSiteData.getStringExtra(EXTRA_DESCRIPTION));
        editTextLatitude.setText(currentSiteData.getStringExtra(EXTRA_LATITUDE));
        editTextLongitude.setText(currentSiteData.getStringExtra(EXTRA_LONGITUDE));
        editTextComment.setText(currentSiteData.getStringExtra(EXTRA_COMMENT));

        actionsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {

                    // when isAllFabsVisible becomes true make all the action name texts and FABs VISIBLE.
                    showRecordsFab.show();
                    editSiteFab.show();
                    deleteSiteFab.show();
                    showRecordsActionText.setVisibility(View.VISIBLE);
                    editSiteActionText.setVisibility(View.VISIBLE);
                    deleteSiteActionText.setVisibility(View.VISIBLE);

                    // Now extend the parent FAB, as user clicks on the shrink parent FAB
                    actionsFab.extend();

                    // make the boolean variable true as we have set the sub FABs visibility to GONE
                    isAllFabsVisible = true;
                } else {

                    // when isAllFabsVisible becomes true make all the action name texts and FABs GONE.
                    showRecordsFab.hide();
                    editSiteFab.hide();
                    deleteSiteFab.hide();
                    showRecordsActionText.setVisibility(View.GONE);
                    editSiteActionText.setVisibility(View.GONE);
                    deleteSiteActionText.setVisibility(View.GONE);

                    // Set the FAB to shrink after user closes all the sub FABs
                    actionsFab.shrink();

                    // make the boolean variable false as we have set the sub FABs visibility to GONE
                    isAllFabsVisible = false;
                }
            }
        });

        showRecordsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowSiteDetailsActivity.this, "Show Records", Toast.LENGTH_SHORT).show();
            }
        });

        editSiteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowSiteDetailsActivity.this, "Edit Site", Toast.LENGTH_SHORT).show();
            }
        });

        deleteSiteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowSiteDetailsActivity.this, "Delete Site", Toast.LENGTH_SHORT).show();
            }
        });


    }

    void setUpActionButtons() {
        // Register all the FABs with their appropriate IDs
        actionsFab = findViewById(R.id.actions_fab);
        showRecordsFab = findViewById(R.id.show_records_fab);
        editSiteFab = findViewById(R.id.edit_site_fab);
        deleteSiteFab = findViewById(R.id.delete_site_fab);

        // Also register the action name text, of all the FABs. except parent FAB action name text
        showRecordsActionText = findViewById(R.id.show_records_action_text);
        editSiteActionText = findViewById(R.id.edit_site_action_text);
        deleteSiteActionText = findViewById(R.id.delete_site_action_text);

        // Now set all the FABs and all the action name texts as GONE (except parent FAB)
        showRecordsFab.setVisibility(View.GONE);
        editSiteFab.setVisibility(View.GONE);
        deleteSiteFab.setVisibility(View.GONE);

        showRecordsActionText.setVisibility(View.GONE);
        editSiteActionText.setVisibility(View.GONE);
        deleteSiteActionText.setVisibility(View.GONE);

        // Set boolean variable to false, as all the action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        // Set the Extended floating action button to shark state initially
        actionsFab.shrink();

    }
}