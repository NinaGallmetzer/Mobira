package swe.mobira.entities.ringingrecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import swe.mobira.R;
import swe.mobira.entities.site.AddSiteActivity;
import swe.mobira.entities.site.Site;

public class ListRingingRecordsActivity extends AppCompatActivity {
    public static final int ADD_R_RECORD_ACTIVITY_REQUEST_CODE = 3;
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";
    public static final String EXTRA_R_RECORD = "swe.mobira.EXTRA_R_RECORD";

    private RingingRecordViewModel ringingRecordViewModel;
    private int siteID;

    private TextView textViewSiteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ringing_records);
        setTitle("Ringing Records");
        textViewSiteTitle = findViewById(R.id.text_view_site_title);

        Intent intent = getIntent();
        Site currentSite = intent.getParcelableExtra(EXTRA_SITE);
        siteID = currentSite.getSiteID();
        textViewSiteTitle.setText(currentSite.getTitle());

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

        FloatingActionButton buttonAddSite = findViewById(R.id.button_add_site);
        buttonAddSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRingingRecordsActivity.this, AddSiteActivity.class);
                startActivityForResult(intent, ADD_R_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });

    }
}