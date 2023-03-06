package swe.mobira.entities.ringingrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import swe.mobira.R;
import swe.mobira.entities.site.Site;

public class ActivityEditRingingRecord extends AppCompatActivity {
    public static final String EXTRA_R_RECORD = "swe.mobira.EXTRA_R_RECORD";
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";

    private RingingRecord currentRingingRecord;
    private Site currentSite;

    private TextInputEditText editTextStartTime;
    private TextInputEditText editTextEndTime;
    private TextInputEditText editTextStartTemperature;
    private TextInputEditText editTextEndTemperature;
    private TextInputEditText editTextWeather;
    private TextInputEditText editTextWind;
    private TextInputEditText editTextCoordinator;
    private TextInputEditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ringing_record);
        setTitle("Edit Ringing Record");

        currentRingingRecord = getIntent().getParcelableExtra(EXTRA_R_RECORD);
        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);

        TextView textViewSiteTitle = findViewById(R.id.record_site_title);
        TextInputEditText editTextDate = findViewById(R.id.record_date);
        editTextStartTime = findViewById(R.id.record_start_time);
        editTextEndTime = findViewById(R.id.record_end_time);
        editTextStartTemperature = findViewById(R.id.record_start_temperature);
        editTextEndTemperature = findViewById(R.id.record_end_temperature);
        editTextWeather = findViewById(R.id.record_general_weather);
        editTextWind = findViewById(R.id.record_wind);
        editTextCoordinator = findViewById(R.id.record_coordinator);
        editTextComment = findViewById(R.id.record_comment);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.baseline_home_24);

        textViewSiteTitle.setText(currentSite.getTitle());
        editTextDate.setText(currentRingingRecord.getRecordDate());
        editTextStartTime.setText(currentRingingRecord.getStartTime());
        editTextEndTime.setText(currentRingingRecord.getEndTime());
        editTextStartTemperature.setText(String.valueOf(currentRingingRecord.getStartTemperature()));
        editTextEndTemperature.setText(String.valueOf(currentRingingRecord.getEndTemperature()));
        editTextWeather.setText(currentRingingRecord.getWeather());
        editTextWind.setText(String.valueOf(currentRingingRecord.getWind()));
        editTextCoordinator.setText(currentRingingRecord.getCoordinator());
        editTextComment.setText(currentRingingRecord.getComment());

        FloatingActionButton buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRingingRecord();
            }
        });

    }

    private void saveRingingRecord() {
        String recordDate = String.valueOf(currentRingingRecord.getRecordDate());
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();
        double startTemperature = Double.parseDouble(editTextStartTemperature.getText().toString());
        double endTemperature = Double.parseDouble(editTextEndTemperature.getText().toString());
        String weather = editTextWeather.getText().toString();
        int wind = Integer.parseInt(editTextWind.getText().toString());
        String coordinator = editTextCoordinator.getText().toString();
        String comment = editTextComment.getText().toString();

        RingingRecord updatedRingingRecord = new RingingRecord(currentRingingRecord.getRingingRecordID(),
                currentSite.getSiteID(), recordDate, startTime, endTime,
                startTemperature, endTemperature, weather, wind, coordinator, comment);

        Intent intent = new Intent();
        intent.putExtra(ActivityShowRingingRecordDetails.EXTRA_R_RECORD, updatedRingingRecord);
        intent.putExtra(ActivityShowRingingRecordDetails.EXTRA_SITE, currentSite);

        setResult(RESULT_OK, intent);
        finish();
    }
}