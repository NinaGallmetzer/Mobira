package swe.mobira.entities.birdrecord;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import swe.mobira.R;
import swe.mobira.entities.ringingrecord.RingingRecord;
import swe.mobira.entities.site.Site;

public class ActivityAddBirdRecord extends AppCompatActivity {
    public static final String EXTRA_R_RECORD = "swe.mobira.EXTRA_R_RECORD";
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";

    private RingingRecord currentRingingRecord;
    private BirdRecordViewModel birdRecordViewModel;

    private TextInputEditText editTextBirdTime;
    private TextInputEditText editTextBirdNetNumber;
    private TextInputEditText editTextBirdNetSide;
    private TextInputEditText editTextBirdShelfNumber;
    private TextInputEditText editTextBirdSpecies;
    private TextInputEditText editTextBirdRingNumber;
    private TextInputEditText editTextBirdRecapture;
    private TextInputEditText editTextBirdTopLeftColor;
    private TextInputEditText editTextBirdTopRightColor;
    private TextInputEditText editTextBirdBottomLeftColor;
    private TextInputEditText editTextBirdBottomRightColor;
    private TextInputEditText editTextBirdSex;
    private TextInputEditText editTextBirdAge;
    private TextInputEditText editTextBirdFat;
    private TextInputEditText editTextBirdMuscle;
    private TextInputEditText editTextBirdTarsus;
    private TextInputEditText editTextBirdWingLength;
    private TextInputEditText editTextBirdFeatherLength;
    private TextInputEditText editTextBirdWeight;
    private TextInputEditText editTextBirdRinger;
    private TextInputEditText editTextBirdPictureNumber;
    private TextInputEditText editTextBirdComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bird_record);
        setTitle("Add Bird Record");

        TextView textViewSiteTitle = findViewById(R.id.text_view_site_title);
        TextView textViewRecordDate = findViewById(R.id.text_view_date);
        editTextBirdTime = findViewById(R.id.edit_text_bird_time);
        editTextBirdNetNumber = findViewById(R.id.edit_text_bird_net_number);
        editTextBirdNetSide = findViewById(R.id.edit_text_bird_net_side);
        editTextBirdShelfNumber = findViewById(R.id.edit_text_bird_shelf_number);
        editTextBirdSpecies = findViewById(R.id.edit_text_bird_species);
        editTextBirdRecapture = findViewById(R.id.edit_text_bird_recapture);
        editTextBirdRingNumber = findViewById(R.id.edit_text_bird_ring_number);
        editTextBirdTopLeftColor = findViewById(R.id.edit_text_bird_top_left_color);
        editTextBirdTopRightColor = findViewById(R.id.edit_text_bird_top_right_color);
        editTextBirdBottomLeftColor = findViewById(R.id.edit_text_bird_bottom_left_color);
        editTextBirdBottomRightColor = findViewById(R.id.edit_text_bird_bottom_right_color);
        editTextBirdSex = findViewById(R.id.edit_text_bird_sex);
        editTextBirdAge = findViewById(R.id.edit_text_bird_age);
        editTextBirdFat = findViewById(R.id.edit_text_bird_fat);
        editTextBirdMuscle = findViewById(R.id.edit_text_bird_muscle);
        editTextBirdTarsus = findViewById(R.id.edit_text_bird_tarsus);
        editTextBirdWingLength = findViewById(R.id.edit_text_bird_wing_length);
        editTextBirdFeatherLength = findViewById(R.id.edit_text_bird_feather_length);
        editTextBirdWeight = findViewById(R.id.edit_text_bird_weight);
        editTextBirdRinger = findViewById(R.id.edit_text_bird_ringer);
        editTextBirdPictureNumber = findViewById(R.id.edit_text_bird_picture_number);
        editTextBirdComment = findViewById(R.id.edit_text_bird_comment);

        currentRingingRecord = getIntent().getParcelableExtra(EXTRA_R_RECORD);
        Site currentSite = getIntent().getParcelableExtra(EXTRA_SITE);
        textViewSiteTitle.setText(currentSite.getTitle());
        textViewRecordDate.setText(currentRingingRecord.getRecordDate());

        birdRecordViewModel = new ViewModelProvider(this, new BirdRecordViewModelFactory(this.getApplication(), currentRingingRecord.getRingingRecordID())).get(BirdRecordViewModel.class);

        FloatingActionButton buttonSave = findViewById(R.id.button_add);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBirdRecord();
            }
        });
    }

    private void addBirdRecord() {
        String time = editTextBirdTime.getText().toString();
        int netNumber = Integer.parseInt(editTextBirdNetNumber.getText().toString());
        String netSide = editTextBirdNetSide.getText().toString();
        int shelfNumber = Integer.parseInt(editTextBirdShelfNumber.getText().toString());
        String species = editTextBirdSpecies.getText().toString();
        boolean recapture = Boolean.parseBoolean(editTextBirdRecapture.getText().toString());
        String ringNumber = editTextBirdRingNumber.getText().toString();
        String topLeftColor = editTextBirdTopLeftColor.getText().toString();
        String topLightColor = editTextBirdTopRightColor.getText().toString();
        String bottomLeftColor = editTextBirdBottomLeftColor.getText().toString();
        String bottomLightColor = editTextBirdBottomRightColor.getText().toString();
        int sex = Integer.parseInt(editTextBirdSex.getText().toString());
        int age = Integer.parseInt(editTextBirdAge.getText().toString());
        int fat = Integer.parseInt(editTextBirdFat.getText().toString());
        int muscle = Integer.parseInt(editTextBirdMuscle.getText().toString());
        double tarsus = Double.parseDouble(editTextBirdTarsus.getText().toString());
        double wingLength = Double.parseDouble(editTextBirdWingLength.getText().toString());
        double featherLength = Double.parseDouble(editTextBirdFeatherLength.getText().toString());
        double weight = Double.parseDouble(editTextBirdWeight.getText().toString());
        String ringer = editTextBirdRinger.getText().toString();
        String pictureNumber = editTextBirdPictureNumber.getText().toString();
        String comment = editTextBirdComment.getText().toString();

        BirdRecord newBirdRecord = new BirdRecord(currentRingingRecord.getRingingRecordID(), time,
                netNumber, netSide, shelfNumber, species, recapture, ringNumber, topLeftColor,
                topLightColor, bottomLeftColor, bottomLightColor, sex, age, fat, muscle, tarsus,
                wingLength, featherLength, weight, ringer, pictureNumber, comment);

        birdRecordViewModel.insertBirdRecord(newBirdRecord);

        setResult(RESULT_OK);
        finish();
    }
}
