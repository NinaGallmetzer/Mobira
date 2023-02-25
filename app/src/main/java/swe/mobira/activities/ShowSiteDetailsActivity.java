package swe.mobira.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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
import swe.mobira.entities.site.Site;
import swe.mobira.entities.site.SiteViewModel;

public class ShowSiteDetailsActivity extends AppCompatActivity {
    public static final int EDIT_SITE_ACTIVITY_REQUEST_CODE = 2;
    private SiteViewModel siteViewModel;

    ExtendedFloatingActionButton actionsFab;
    FloatingActionButton showRecordsFab, editSiteFab, deleteSiteFab;
    TextView showRecordsActionText, editSiteActionText, deleteSiteActionText;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;

    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";
    public static final String EXTRA_ID = "swe.mobira.EXTRA_ID";
    public static final String EXTRA_TITLE = "swe.mobira.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "swe.mobira.EXTRA_DESCRIPTION";
    public static final String EXTRA_LATITUDE = "swe.mobira.EXTRA_LATITUDE";
    public static final String EXTRA_LONGITUDE = "swe.mobira.EXTRA_LONGITUDE";
    public static final String EXTRA_COMMENT = "swe.mobira.EXTRA_COMMENT";
    private TextView editTextTitle;
    private TextView editTextDescription;
    private TextView editTextLatitude;
    private TextView editTextLongitude;
    private TextView editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_site_details);
        setTitle("Site Details");
        setUpActionButtons();
        siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);

        Intent currentSiteData = getIntent();

        editTextTitle = findViewById(R.id.text_view_title);
        editTextDescription = findViewById(R.id.text_view_description);
        editTextLatitude = findViewById(R.id.text_view_latitude);
        editTextLongitude = findViewById(R.id.text_view_longitude);
        editTextComment = findViewById(R.id.text_view_comment);

        // Create the observer which updates the UI.
        final Observer<Site> nameObserver = new Observer<Site>() {
            @Override
            public void onChanged(@Nullable final Site changedSite) {
                Intent update = new Intent();
                update.putExtra(EXTRA_ID, changedSite.getSiteID());
                update.putExtra(EXTRA_TITLE, changedSite.getTitle());
                update.putExtra(EXTRA_DESCRIPTION, changedSite.getDescription());
                // lat and long need to be passed along as stings
                update.putExtra(EXTRA_LATITUDE, String.valueOf(changedSite.getLatitude()));
                update.putExtra(EXTRA_LONGITUDE, String.valueOf(changedSite.getLongitude()));
                update.putExtra(EXTRA_COMMENT, changedSite.getComment());
                currentSiteData.replaceExtras(update);

                // Update the UI.
                editTextTitle.setText(currentSiteData.getStringExtra(EXTRA_TITLE));
                editTextDescription.setText(currentSiteData.getStringExtra(EXTRA_DESCRIPTION));
                editTextLatitude.setText(currentSiteData.getStringExtra(EXTRA_LATITUDE));
                editTextLongitude.setText(currentSiteData.getStringExtra(EXTRA_LONGITUDE));
                editTextComment.setText(currentSiteData.getStringExtra(EXTRA_COMMENT));
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        siteViewModel.getSiteByID(currentSiteData.getIntExtra(EXTRA_ID, -1)).observe(this, nameObserver);

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
                Toast.makeText(ShowSiteDetailsActivity.this, "TODO: Implement Show Records", Toast.LENGTH_SHORT).show();
            }
        });

        editSiteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowSiteDetailsActivity.this, EditSiteActivity.class);
                intent.putExtras(currentSiteData.getExtras());
                startActivityForResult(intent, EDIT_SITE_ACTIVITY_REQUEST_CODE);
            }
        });

        deleteSiteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShowSiteDetailsActivity.this, "TODO: Implement Delete Site", Toast.LENGTH_SHORT).show();
                finish();
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_SITE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditSiteActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(getApplicationContext(), "Site can't be updated", Toast.LENGTH_LONG).show();
                return;
            }
            String title = data.getStringExtra(EditSiteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(EditSiteActivity.EXTRA_DESCRIPTION);
            // in intent/data, lat and long are stored as strings and need to be converted first
            double latitude = Double.parseDouble(data.getStringExtra(EditSiteActivity.EXTRA_LATITUDE));
            double longitude = Double.parseDouble(data.getStringExtra(EditSiteActivity.EXTRA_LONGITUDE));
            String comment = data.getStringExtra(EditSiteActivity.EXTRA_COMMENT);

            Site site = new Site(title, description, latitude, longitude, comment);
            site.setSiteID(id);
            siteViewModel.updateSite(site);
            Toast.makeText(getApplicationContext(), "Site saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Site not saved", Toast.LENGTH_LONG).show();
        }
    }

}