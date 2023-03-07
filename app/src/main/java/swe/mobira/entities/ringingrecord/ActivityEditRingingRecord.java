package swe.mobira.entities.ringingrecord;

import static swe.mobira.MainActivity.EXTRA_R_RECORD;
import static swe.mobira.MainActivity.EXTRA_SITE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import swe.mobira.R;
import swe.mobira.entities.site.Site;

public class ActivityEditRingingRecord extends AppCompatActivity {
    private RingingRecordViewModel ringingRecordViewModel;
    private RingingRecord currentRingingRecord;
    private Site currentSite;

    private EditText editTextRecordDate;
    private EditText editTextStartTime;
    private EditText editTextEndTime;
    private EditText editTextStartTemperature;
    private EditText editTextEndTemperature;
    private EditText editTextWeather;
    private EditText editTextWind;
    private EditText editTextCoordinator;
    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ringing_record);
        setTitle("Edit Ringing Record");

        currentRingingRecord = getIntent().getParcelableExtra(EXTRA_R_RECORD);
        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);

        ringingRecordViewModel = new ViewModelProvider(this, new RingingRecordViewModelFactory(this.getApplication(), currentSite.getSiteID())).get(RingingRecordViewModel.class);

        TextView textViewSiteTitle = findViewById(R.id.text_view_site_title);
        editTextRecordDate = findViewById(R.id.edit_text_record_date);
        editTextStartTime = findViewById(R.id.edit_text_record_start_time);
        editTextEndTime = findViewById(R.id.edit_text_record_end_time);
        editTextStartTemperature = findViewById(R.id.edit_text_record_start_temperature);
        editTextEndTemperature = findViewById(R.id.edit_text_record_end_temperature);
        editTextWeather = findViewById(R.id.edit_text_record_general_weather);
        editTextWind = findViewById(R.id.edit_text_record_wind);
        editTextCoordinator = findViewById(R.id.edit_text_record_coordinator);
        editTextComment = findViewById(R.id.edit_text_record_comment);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.baseline_home_24);

        textViewSiteTitle.setText(currentSite.getTitle());
        editTextRecordDate.setText(currentRingingRecord.getRecordDate());
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
                updateRingingRecord();
            }
        });
    }

    private void updateRingingRecord() {
        String recordDate = editTextRecordDate.getText().toString();
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

        ringingRecordViewModel.updateRingingRecord(updatedRingingRecord);

        setResult(RESULT_OK);
        finish();
    }
}