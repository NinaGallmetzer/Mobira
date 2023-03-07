package swe.mobira.entities.birdrecord;

import static swe.mobira.MainActivity.ADD_B_RECORD_ACTIVITY_REQUEST_CODE;
import static swe.mobira.MainActivity.EXTRA_B_RECORD;
import static swe.mobira.MainActivity.EXTRA_R_RECORD;
import static swe.mobira.MainActivity.EXTRA_SITE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import swe.mobira.R;
import swe.mobira.entities.ringingrecord.ActivityAddRingingRecord;
import swe.mobira.entities.ringingrecord.RingingRecord;
import swe.mobira.entities.site.Site;

public class ActivityListBirdRecords extends AppCompatActivity {
    private RingingRecord currentRingingRecord;
    private Site currentSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bird_records);
        setTitle("Recorded Bird");
        TextView textViewSiteTitle = findViewById(R.id.text_view_site_title);
        TextView textViewDate = findViewById(R.id.text_view_date);

        Intent intent = getIntent();
        currentRingingRecord = intent.getParcelableExtra(EXTRA_R_RECORD);
        currentSite = intent.getParcelableExtra(EXTRA_SITE);

        int ringingRecordID = currentRingingRecord.getRingingRecordID();
        textViewSiteTitle.setText(currentSite.getTitle());
        textViewDate.setText(currentRingingRecord.getRecordDate());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        BirdRecordAdapter adapter = new BirdRecordAdapter();
        recyclerView.setAdapter(adapter);

        BirdRecordViewModel birdRecordViewModel = new ViewModelProvider(this, new BirdRecordViewModelFactory(this.getApplication(), ringingRecordID)).get(BirdRecordViewModel.class);

        birdRecordViewModel.getBirdRecordsByRingingRecordID(ringingRecordID).observe(this, new Observer<List<BirdRecord>>() {
            @Override
            public void onChanged(List<BirdRecord> birdRecords) {
                adapter.setBirdRecords(birdRecords);
            }
        });

        FloatingActionButton buttonAdd = findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityListBirdRecords.this, ActivityAddBirdRecord.class);
                intent.putExtra(EXTRA_R_RECORD, currentRingingRecord);
                intent.putExtra(EXTRA_SITE, currentSite);
                startActivityForResult(intent, ADD_B_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });

        adapter.setOnItemClickListener(new BirdRecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BirdRecord selectedBirdRecord) {
                Intent intent = new Intent(ActivityListBirdRecords.this, ActivityShowBirdRecordDetails.class);
                intent.putExtra(EXTRA_B_RECORD, selectedBirdRecord);
                intent.putExtra(EXTRA_R_RECORD, currentRingingRecord);
                intent.putExtra(EXTRA_SITE, currentSite);
                startActivity(intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_B_RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Record added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Record not saved", Toast.LENGTH_LONG).show();
        }
    }
}