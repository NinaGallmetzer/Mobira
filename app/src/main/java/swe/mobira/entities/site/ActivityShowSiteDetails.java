package swe.mobira.entities.site;

import static swe.mobira.MainActivity.ADD_R_RECORD_ACTIVITY_REQUEST_CODE;
import static swe.mobira.MainActivity.EDIT_SITE_ACTIVITY_REQUEST_CODE;
import static swe.mobira.MainActivity.EXTRA_SITE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import swe.mobira.R;
import swe.mobira.entities.ringingrecord.ActivityAddRingingRecord;
import swe.mobira.entities.ringingrecord.ActivityListRingingRecords;

public class ActivityShowSiteDetails extends AppCompatActivity {
    private SiteViewModel siteViewModel;
    private Site currentSite;

    private TextView editTextTitle;
    private TextView editTextDescription;
    private TextView editTextHabitatType;
    private TextView editTextAltitude;
    private TextView editTextLatitude;
    private TextView editTextLongitude;
    private TextView editTextComment;

    ExtendedFloatingActionButton actionsFab;
    FloatingActionButton addFab, showFab, editFab, deleteFab;
    TextView addRecordActionText, showRecordsActionText, editSiteActionText, deleteSiteActionText;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_site_details);
        setTitle("Site Details");
        setUpActionButtons();
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);

        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);

        editTextTitle = findViewById(R.id.text_view_title);
        editTextDescription = findViewById(R.id.text_view_site_description);
        editTextHabitatType = findViewById(R.id.text_view_site_habitat_type);
        editTextAltitude = findViewById(R.id.text_view_site_altitude);
        editTextLatitude = findViewById(R.id.text_view_site_latitude);
        editTextLongitude = findViewById(R.id.text_view_site_longitude);
        editTextComment = findViewById(R.id.text_view_site_comment);

        // Create the observer which updates the UI.
        final Observer<Site> nameObserver = new Observer<Site>() {
            @Override
            public void onChanged(@Nullable final Site changedSite) {
                Intent update = new Intent();
                update.putExtra(EXTRA_SITE, changedSite);
                currentSite = changedSite;

                // Update the UI.
                if (currentSite == null) {
                    finish();
                } else {
                    editTextTitle.setText(currentSite.getTitle());
                    editTextDescription.setText(currentSite.getDescription());
                    editTextHabitatType.setText(currentSite.getHabitatType());
                    editTextAltitude.setText(String.valueOf(currentSite.getAltitude()));
                    editTextLatitude.setText(String.valueOf(currentSite.getLatitude()));
                    editTextLongitude.setText(String.valueOf(currentSite.getLongitude()));
                    editTextComment.setText(currentSite.getComment());
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        siteViewModel.getSiteByID(currentSite.getSiteID()).observe(this, nameObserver);

        actionsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {

                    // when isAllFabsVisible becomes true make all the action name texts and FABs VISIBLE.
                    addFab.show();
                    showFab.show();
                    editFab.show();
                    deleteFab.show();
                    addRecordActionText.setVisibility(View.VISIBLE);
                    showRecordsActionText.setVisibility(View.VISIBLE);
                    editSiteActionText.setVisibility(View.VISIBLE);
                    deleteSiteActionText.setVisibility(View.VISIBLE);

                    // Now extend the parent FAB, as user clicks on the shrink parent FAB
                    actionsFab.extend();

                    // make the boolean variable true as we have set the sub FABs visibility to GONE
                    isAllFabsVisible = true;
                } else {

                    // when isAllFabsVisible becomes true make all the action name texts and FABs GONE.
                    addFab.hide();
                    showFab.hide();
                    editFab.hide();
                    deleteFab.hide();
                    addRecordActionText.setVisibility(View.GONE);
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

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityShowSiteDetails.this, ActivityAddRingingRecord.class);
                intent.putExtra(EXTRA_SITE, currentSite);
                setUpActionButtons();
                startActivityForResult(intent, ADD_R_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });

        showFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityShowSiteDetails.this, ActivityListRingingRecords.class);
                intent.putExtra(EXTRA_SITE, currentSite);
                setUpActionButtons();
                startActivity(intent);
            }
        });

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityShowSiteDetails.this, ActivityEditSite.class);
                intent.putExtra(EXTRA_SITE, currentSite);
                setUpActionButtons();
                startActivityForResult(intent, EDIT_SITE_ACTIVITY_REQUEST_CODE);
            }
        });

        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siteViewModel.deleteSite(currentSite);
                Toast.makeText(ActivityShowSiteDetails.this, "Site deleted", Toast.LENGTH_SHORT).show();
                setUpActionButtons();
                finish();
            }
        });
    }

    void setUpActionButtons() {
        // Register all the FABs with their appropriate IDs
        actionsFab = findViewById(R.id.actions_fab);
        addFab = findViewById(R.id.add_record_fab);
        showFab = findViewById(R.id.show_records_fab);
        editFab = findViewById(R.id.edit_site_fab);
        deleteFab = findViewById(R.id.delete_site_fab);

        // Also register the action name text, of all the FABs. except parent FAB action name text
        addRecordActionText = findViewById(R.id.add_record_action_text);
        showRecordsActionText = findViewById(R.id.show_records_action_text);
        editSiteActionText = findViewById(R.id.edit_site_action_text);
        deleteSiteActionText = findViewById(R.id.delete_site_action_text);

        // Now set all the FABs and all the action name texts as GONE (except parent FAB)
        addFab.setVisibility(View.GONE);
        showFab.setVisibility(View.GONE);
        editFab.setVisibility(View.GONE);
        deleteFab.setVisibility(View.GONE);

        addRecordActionText.setVisibility(View.GONE);
        showRecordsActionText.setVisibility(View.GONE);
        editSiteActionText.setVisibility(View.GONE);
        deleteSiteActionText.setVisibility(View.GONE);

        // Set boolean variable to false, as all the action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        // Set the Extended floating action button to shark state initially
        actionsFab.shrink();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_SITE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Site updated", Toast.LENGTH_LONG).show();
        } else if (requestCode == ADD_R_RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Record added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No changes saved", Toast.LENGTH_LONG).show();
        }
    }

}