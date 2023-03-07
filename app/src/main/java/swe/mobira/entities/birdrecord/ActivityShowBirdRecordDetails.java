package swe.mobira.entities.birdrecord;

import static swe.mobira.MainActivity.EDIT_B_RECORD_ACTIVITY_REQUEST_CODE;
import static swe.mobira.MainActivity.EXTRA_B_RECORD;
import static swe.mobira.MainActivity.EXTRA_R_RECORD;
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
import swe.mobira.entities.ringingrecord.RingingRecord;
import swe.mobira.entities.site.Site;

public class ActivityShowBirdRecordDetails extends AppCompatActivity {
    private BirdRecordViewModel birdRecordViewModel;
    private Site currentSite;
    private BirdRecord currentBirdRecord;
    private RingingRecord currentRingingRecord;

    private TextView textViewBirdTime;
    private TextView textViewBirdNetNumber;
    private TextView textViewBirdNetSide;
    private TextView textViewBirdShelfNumber;
    private TextView textViewBirdSpecies;
    private TextView textViewBirdRingNumber;
    private TextView textViewBirdRecapture;
    private TextView textViewBirdTopLeftColor;
    private TextView textViewBirdTopRightColor;
    private TextView textViewBirdBottomLeftColor;
    private TextView textViewBirdBottomRightColor;
    private TextView textViewBirdSex;
    private TextView textViewBirdAge;
    private TextView textViewBirdFat;
    private TextView textViewBirdMuscle;
    private TextView textViewBirdTarsus;
    private TextView textViewBirdWingLength;
    private TextView textViewBirdFeatherLength;
    private TextView textViewBirdWeight;
    private TextView textViewBirdRinger;
    private TextView textViewBirdPictureNumber;
    private TextView textViewBirdComment;

    ExtendedFloatingActionButton actionsFab;
    FloatingActionButton editFab, deleteFab;
    TextView editFabLabel, deleteFabLabel;

    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bird_record_details);
        setTitle("Record Details");
        setUpActionButtons();

        currentBirdRecord = getIntent().getParcelableExtra(EXTRA_B_RECORD);
        currentRingingRecord = getIntent().getParcelableExtra(EXTRA_R_RECORD);
        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);
        int ringingRecordID = currentRingingRecord.getRingingRecordID();
        
        birdRecordViewModel = new ViewModelProvider(this, new BirdRecordViewModelFactory(this.getApplication(), ringingRecordID)).get(BirdRecordViewModel.class);

        TextView textViewSiteTitle = findViewById(R.id.site_title);
        TextView textViewRecordDate = findViewById(R.id.text_view_date);
        textViewBirdTime = findViewById(R.id.bird_time);
        textViewBirdNetNumber = findViewById(R.id.bird_net_number);
        textViewBirdNetSide = findViewById(R.id.bird_net_side);
        textViewBirdShelfNumber = findViewById(R.id.bird_shelf_number);
        textViewBirdSpecies = findViewById(R.id.bird_species);
        textViewBirdRecapture = findViewById(R.id.bird_recapture);
        textViewBirdRingNumber = findViewById(R.id.bird_ring_number);
        textViewBirdTopLeftColor = findViewById(R.id.bird_top_left_color);
        textViewBirdTopRightColor = findViewById(R.id.bird_top_right_color);
        textViewBirdBottomLeftColor = findViewById(R.id.bird_bottom_left_color);
        textViewBirdBottomRightColor = findViewById(R.id.bird_bottom_right_color);
        textViewBirdSex = findViewById(R.id.bird_sex);
        textViewBirdAge = findViewById(R.id.bird_age);
        textViewBirdFat = findViewById(R.id.bird_fat);
        textViewBirdMuscle = findViewById(R.id.bird_muscle);
        textViewBirdTarsus = findViewById(R.id.bird_tarsus);
        textViewBirdWingLength = findViewById(R.id.bird_wing_length);
        textViewBirdFeatherLength = findViewById(R.id.bird_feather_length);
        textViewBirdWeight = findViewById(R.id.bird_weight);
        textViewBirdRinger = findViewById(R.id.bird_ringer);
        textViewBirdPictureNumber = findViewById(R.id.bird_picture_number);
        textViewBirdComment = findViewById(R.id.bird_comment);


        final Observer<BirdRecord> nameObserver = new Observer<BirdRecord>() {
            @Override
            public void onChanged(@Nullable final BirdRecord changedBirdRecord) {
                Intent update = new Intent();
                update.putExtra(EXTRA_B_RECORD, changedBirdRecord);
                currentBirdRecord = changedBirdRecord;

                if (currentBirdRecord == null) {
                    finish();
                } else {
                    textViewSiteTitle.setText(currentSite.getTitle());
                    textViewRecordDate.setText(currentRingingRecord.getRecordDate());
                    textViewBirdTime.setText(currentBirdRecord.getTime());
                    textViewBirdNetNumber.setText(String.valueOf(currentBirdRecord.getNetNumber()));
                    textViewBirdNetSide.setText(currentBirdRecord.getNetSide());
                    textViewBirdShelfNumber.setText(String.valueOf(currentBirdRecord.getShelfNumber()));
                    textViewBirdSpecies.setText(currentBirdRecord.getSpecies());
                    textViewBirdRecapture.setText(String.valueOf(currentBirdRecord.isRecapture()));
                    textViewBirdRingNumber.setText(currentBirdRecord.getRingNumber());
                    textViewBirdTopLeftColor.setText(currentBirdRecord.getTopLeftColor());
                    textViewBirdTopRightColor.setText(currentBirdRecord.getTopRightColor());
                    textViewBirdBottomLeftColor.setText(currentBirdRecord.getBottomLeftColor());
                    textViewBirdBottomRightColor.setText(currentBirdRecord.getBottomRightColor());
                    textViewBirdSex.setText(String.valueOf(currentBirdRecord.getSex()));
                    textViewBirdAge.setText(String.valueOf(currentBirdRecord.getAge()));
                    textViewBirdFat.setText(String.valueOf(currentBirdRecord.getFat()));
                    textViewBirdMuscle.setText(String.valueOf(currentBirdRecord.getMuscle()));
                    textViewBirdTarsus.setText(String.valueOf(currentBirdRecord.getTarsus()));
                    textViewBirdWingLength.setText(String.valueOf(currentBirdRecord.getWingLength()));
                    textViewBirdFeatherLength.setText(String.valueOf(currentBirdRecord.getFeatherLength()));
                    textViewBirdWeight.setText(String.valueOf(currentBirdRecord.getWeight()));
                    textViewBirdRinger.setText(currentBirdRecord.getRinger());
                    textViewBirdPictureNumber.setText(currentBirdRecord.getPictureNumber());
                    textViewBirdComment.setText(currentBirdRecord.getComment());
                }
            }
        };

        birdRecordViewModel.getBirdRecordByID(currentBirdRecord.getBirdRecordID()).observe(this, nameObserver);

        actionsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {
                    editFab.show();
                    deleteFab.show();
                    editFabLabel.setVisibility(View.VISIBLE);
                    deleteFabLabel.setVisibility(View.VISIBLE);

                    actionsFab.extend();
                    isAllFabsVisible = true;
                } else {
                    editFab.hide();
                    deleteFab.hide();
                    editFabLabel.setVisibility(View.GONE);
                    deleteFabLabel.setVisibility(View.GONE);

                    actionsFab.shrink();
                    isAllFabsVisible = false;
                }
            }
        });

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityShowBirdRecordDetails.this, ActivityEditBirdRecord.class);
                intent.putExtra(EXTRA_B_RECORD, currentBirdRecord);
                intent.putExtra(EXTRA_R_RECORD, currentRingingRecord);
                intent.putExtra(EXTRA_SITE, currentSite);
                setUpActionButtons();
                startActivityForResult(intent, EDIT_B_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });

        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                birdRecordViewModel.deleteBirdRecord(currentBirdRecord);
                Toast.makeText(ActivityShowBirdRecordDetails.this, "Record deleted", Toast.LENGTH_SHORT).show();
                setUpActionButtons();
                finish();
            }
        });
    }

    void setUpActionButtons() {
        actionsFab = findViewById(R.id.actions_fab);
        editFab = findViewById(R.id.edit_fab);
        deleteFab = findViewById(R.id.delete_fab);
        editFabLabel = findViewById(R.id.edit_fab_label);
        deleteFabLabel = findViewById(R.id.delete_fab_label);

        editFab.setVisibility(View.GONE);
        deleteFab.setVisibility(View.GONE);
        editFabLabel.setVisibility(View.GONE);
        deleteFabLabel.setVisibility(View.GONE);

        isAllFabsVisible = false;
        actionsFab.shrink();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_B_RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Record updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No changes saved", Toast.LENGTH_LONG).show();
        }
    }
}