package swe.mobira.entities.ringingrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import swe.mobira.R;
import swe.mobira.entities.site.Site;

public class AddRingingRecordActivity extends AppCompatActivity {
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";

    private Site site;

    private TextView textViewSiteTitle;
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
        setContentView(R.layout.activity_add_ringing_record);
        setTitle("Add Ringing Record");

        textViewSiteTitle = findViewById(R.id.record_site_title);
        editTextRecordDate = findViewById(R.id.record_date);
        editTextStartTime = findViewById(R.id.record_start_time);
        editTextEndTime = findViewById(R.id.record_end_time);
        editTextStartTemperature = findViewById(R.id.record_start_temperature);
        editTextEndTemperature = findViewById(R.id.record_end_temperature);
        editTextWeather = findViewById(R.id.record_weather);
        editTextWind = findViewById(R.id.record_wind);
        editTextCoordinator = findViewById(R.id.record_coordinator);
        editTextComment = findViewById(R.id.record_comment);

        site = getIntent().getParcelableExtra(EXTRA_SITE);
        textViewSiteTitle.setText(site.getTitle());

        FloatingActionButton buttonSave = findViewById(R.id.button_add);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRingingRecord();
            }
        });
    }

    private void saveRingingRecord() {
        String recordDate = editTextRecordDate.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();
        double startTemperature = Double.parseDouble(editTextStartTemperature.getText().toString());
        double endTemperature = Double.parseDouble(editTextEndTemperature.getText().toString());
        String weather = editTextWeather.getText().toString();
        int wind = Integer.parseInt(editTextWind.getText().toString());
        String coordinator = editTextCoordinator.getText().toString();
        String comment = editTextComment.getText().toString();

        RingingRecord newRingingRecord = new RingingRecord(site.getSiteID(), recordDate, startTime, endTime,
                startTemperature, endTemperature, weather, wind, coordinator, comment);

        Intent intent = new Intent();
        intent.putExtra(ListRingingRecordsActivity.EXTRA_R_RECORD, newRingingRecord);
        intent.putExtra(ListRingingRecordsActivity.EXTRA_SITE, site);

        setResult(RESULT_OK, intent);
        finish();
    }
}