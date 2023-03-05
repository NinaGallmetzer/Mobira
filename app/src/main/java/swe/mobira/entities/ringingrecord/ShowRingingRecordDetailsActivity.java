package swe.mobira.entities.ringingrecord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import swe.mobira.R;
import swe.mobira.entities.site.Site;

public class ShowRingingRecordDetailsActivity extends AppCompatActivity {
    public static final int EDIT_R_RECORD_ACTIVITY_REQUEST_CODE = 4;
    public static final String EXTRA_R_RECORD = "swe.mobira.EXTRA_RECORD";
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";

    private RingingRecordViewModel ringingRecordViewModel;
    private RingingRecord currentRingingRecord;
    private Site currentSite;

    private TextView textViewSiteTitle;
    private TextView textViewDate;
    private TextView textViewStartTime;
    private TextView textViewEndTime;
    private TextView textViewStartTemperature;
    private TextView textViewEndTemperature;
    private TextView textViewWeather;
    private TextView textViewWind;
    private TextView textViewCoordinator;
    private TextView textViewComment;

    ExtendedFloatingActionButton actionsFab;
    FloatingActionButton showFab, editFab, deleteFab;
    TextView showFabLabel, editFabLabel, deleteFabLabel;

    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ringing_record_details);
        setTitle("Ringing Record Details");
        setUpActionButtons();

        currentRingingRecord = getIntent().getParcelableExtra(EXTRA_R_RECORD);
        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);
        int siteID = currentRingingRecord.getSiteID();

        ringingRecordViewModel = new ViewModelProvider(this, new RingingRecordViewModelFactory(this.getApplication(), siteID)).get(RingingRecordViewModel.class);

        textViewSiteTitle = findViewById(R.id.record_site_title);
        textViewDate = findViewById(R.id.record_date);
        textViewStartTime = findViewById(R.id.record_start_time);
        textViewEndTime = findViewById(R.id.record_end_time);
        textViewStartTemperature = findViewById(R.id.record_start_temperature);
        textViewEndTemperature = findViewById(R.id.record_end_temperature);
        textViewWeather = findViewById(R.id.record_general_weather);
        textViewWind = findViewById(R.id.record_wind);
        textViewCoordinator = findViewById(R.id.record_coordinator);
        textViewComment = findViewById(R.id.record_comment);

        final Observer<RingingRecord> nameObserver = new Observer<RingingRecord>() {
            @Override
            public void onChanged(@Nullable final RingingRecord changedRingingRecord) {
                Intent update = new Intent();
                update.putExtra(EXTRA_R_RECORD, changedRingingRecord);
                currentRingingRecord = changedRingingRecord;

                if (currentRingingRecord == null) {
                    finish();
                } else {
                    textViewSiteTitle.setText(currentSite.getTitle());
                    textViewDate.setText(currentRingingRecord.getRecordDate());
                    textViewStartTime.setText(currentRingingRecord.getStartTime());
                    textViewEndTime.setText(currentRingingRecord.getEndTime());
                    textViewStartTemperature.setText(String.valueOf(currentRingingRecord.getStartTemperature()));
                    textViewEndTemperature.setText(String.valueOf(currentRingingRecord.getEndTemperature()));
                    textViewWeather.setText(currentRingingRecord.getWeather());
                    textViewWind.setText(String.valueOf(currentRingingRecord.getWind()));
                    textViewCoordinator.setText(currentRingingRecord.getCoordinator());
                    textViewComment.setText(currentRingingRecord.getComment());
                }
            }
        };

        ringingRecordViewModel.getRingingRecordByID(currentRingingRecord.getRingingRecordID()).observe(this, nameObserver);

        actionsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {
                    showFab.show();
                    editFab.show();
                    deleteFab.show();
                    showFabLabel.setVisibility(View.VISIBLE);
                    editFabLabel.setVisibility(View.VISIBLE);
                    deleteFabLabel.setVisibility(View.VISIBLE);

                    actionsFab.extend();
                    isAllFabsVisible = true;
                } else {
                    showFab.hide();
                    editFab.hide();
                    deleteFab.hide();
                    showFabLabel.setVisibility(View.GONE);
                    editFabLabel.setVisibility(View.GONE);
                    deleteFabLabel.setVisibility(View.GONE);

                    actionsFab.shrink();
                    isAllFabsVisible = false;
                }
            }
        });

        showFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ShowRingingRecordDetailsActivity.this, ListRingingRecordsActivity.class);
//                intent.putExtra(ListRingingRecordsActivity.EXTRA_RECORD, currentRingingRecord);
                setUpActionButtons();
//                startActivity(intent);
            }
        });

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowRingingRecordDetailsActivity.this, EditRingingRecordActivity.class);
                intent.putExtra(EditRingingRecordActivity.EXTRA_R_RECORD, currentRingingRecord);
                intent.putExtra(EditRingingRecordActivity.EXTRA_SITE, currentSite);
                setUpActionButtons();
                startActivityForResult(intent, EDIT_R_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });

        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ringingRecordViewModel.deleteRingingRecord(currentRingingRecord);
                Toast.makeText(ShowRingingRecordDetailsActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
                setUpActionButtons();
                finish();
            }
        });
    }

    void setUpActionButtons() {
        actionsFab = findViewById(R.id.actions_fab);
        showFab = findViewById(R.id.show_fab);
        editFab = findViewById(R.id.edit_fab);
        deleteFab = findViewById(R.id.delete_fab);
        showFabLabel = findViewById(R.id.show_fab_label);
        editFabLabel = findViewById(R.id.edit_fab_label);
        deleteFabLabel = findViewById(R.id.delete_fab_label);

        showFab.setVisibility(View.GONE);
        editFab.setVisibility(View.GONE);
        deleteFab.setVisibility(View.GONE);
        showFabLabel.setVisibility(View.GONE);
        editFabLabel.setVisibility(View.GONE);
        deleteFabLabel.setVisibility(View.GONE);

        isAllFabsVisible = false;
        actionsFab.shrink();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_R_RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            RingingRecord updatedRingingRecord = data.getParcelableExtra(EXTRA_R_RECORD);
            ringingRecordViewModel.updateRingingRecord(updatedRingingRecord);
            Toast.makeText(getApplicationContext(), "Record updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No changes saved", Toast.LENGTH_LONG).show();
        }
    }

}