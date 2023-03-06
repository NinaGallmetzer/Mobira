package swe.mobira.entities.ringingrecord;

import static swe.mobira.MainActivity.ADD_R_RECORD_ACTIVITY_REQUEST_CODE;

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
import swe.mobira.entities.site.Site;

public class ActivityListRingingRecords extends AppCompatActivity {
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";

    private RingingRecordViewModel ringingRecordViewModel;
    private Site currentSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ringing_records);
        setTitle("Ringing Records");
        TextView textViewSiteTitle = findViewById(R.id.text_view_site_title);

        Intent intent = getIntent();
        currentSite = intent.getParcelableExtra(EXTRA_SITE);

        int siteID = currentSite.getSiteID();
        String siteTitle = currentSite.getTitle();
        textViewSiteTitle.setText(siteTitle);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RingingRecordAdapter adapter = new RingingRecordAdapter();
        recyclerView.setAdapter(adapter);

        ringingRecordViewModel = new ViewModelProvider(this, new RingingRecordViewModelFactory(this.getApplication(), siteID)).get(RingingRecordViewModel.class);

        ringingRecordViewModel.getRingingRecordsBySiteID(siteID).observe(this, new Observer<List<RingingRecord>>() {
            @Override
            public void onChanged(List<RingingRecord> ringingRecords) {
                adapter.setRingingRecords(ringingRecords);
            }
        });

        FloatingActionButton buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityListRingingRecords.this, ActivityAddRingingRecord.class);
                intent.putExtra(ActivityAddRingingRecord.EXTRA_SITE, currentSite);
                startActivityForResult(intent, ADD_R_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });

        adapter.setOnItemClickListener(new RingingRecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RingingRecord ringingRecord) {
                Intent intent = new Intent(ActivityListRingingRecords.this, ActivityShowRingingRecordDetails.class);
                intent.putExtra(ActivityShowRingingRecordDetails.EXTRA_R_RECORD, ringingRecord);
                intent.putExtra(ActivityShowRingingRecordDetails.EXTRA_SITE, currentSite);
                startActivity(intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_R_RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Record added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Record not saved", Toast.LENGTH_LONG).show();
        }
    }
}