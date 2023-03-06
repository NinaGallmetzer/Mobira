package swe.mobira.entities.ringingrecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import swe.mobira.R;
import swe.mobira.entities.site.Site;

public class ActivityAddRingingRecord extends AppCompatActivity {
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";

    private RingingRecordViewModel ringingRecordViewModel;
    private Site currentSite;

    private TextInputEditText editTextRecordDate;
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
        setContentView(R.layout.activity_add_ringing_record);
        setTitle("Add Ringing Record");

        TextView textViewSiteTitle = findViewById(R.id.record_site_title);
        editTextRecordDate = findViewById(R.id.record_date);
        editTextStartTime = findViewById(R.id.record_start_time);
        editTextEndTime = findViewById(R.id.record_end_time);
        editTextStartTemperature = findViewById(R.id.record_start_temperature);
        editTextEndTemperature = findViewById(R.id.record_end_temperature);
        editTextWeather = findViewById(R.id.record_general_weather);
        editTextWind = findViewById(R.id.record_wind);
        editTextCoordinator = findViewById(R.id.record_coordinator);
        editTextComment = findViewById(R.id.record_comment);

        currentSite = getIntent().getParcelableExtra(EXTRA_SITE);
        textViewSiteTitle.setText(currentSite.getTitle());

        ringingRecordViewModel = new ViewModelProvider(this, new RingingRecordViewModelFactory(this.getApplication(), currentSite.getSiteID())).get(RingingRecordViewModel.class);

        FloatingActionButton buttonSave = findViewById(R.id.button_add);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRingingRecord();
            }
        });
    }

    private void addRingingRecord() {
        String recordDate = Objects.requireNonNull(editTextRecordDate.getText()).toString();
        String startTime = Objects.requireNonNull(editTextStartTime.getText()).toString();
        String endTime = Objects.requireNonNull(editTextEndTime.getText()).toString();
        double startTemperature = Double.parseDouble(Objects.requireNonNull(editTextStartTemperature.getText()).toString());
        double endTemperature = Double.parseDouble(Objects.requireNonNull(editTextEndTemperature.getText()).toString());
        String weather = Objects.requireNonNull(editTextWeather.getText()).toString();
        int wind = Integer.parseInt(Objects.requireNonNull(editTextWind.getText()).toString());
        String coordinator = Objects.requireNonNull(editTextCoordinator.getText()).toString();
        String comment = Objects.requireNonNull(editTextComment.getText()).toString();

        RingingRecord newRingingRecord = new RingingRecord(
                currentSite.getSiteID(), recordDate, startTime, endTime,
                startTemperature, endTemperature, weather, wind, coordinator, comment);

        ringingRecordViewModel.insertRingingRecord(newRingingRecord);

        setResult(RESULT_OK);
        finish();
    }
}