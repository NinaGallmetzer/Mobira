package swe.mobira.entities.net;

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

public class ListNetsActivity extends AppCompatActivity {
    public static final int ADD_NET_ACTIVITY_REQUEST_CODE = 5;
    public static final String EXTRA_SITE = "swe.mobira.EXTRA_SITE";
    public static final String EXTRA_NET = "swe.mobira.EXTRA_NET";

    private NetViewModel netViewModel;
    private Site currentSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nets);
        setTitle("Mist Nets");
        TextView textViewSiteTitle = findViewById(R.id.text_view_site_title);

        Intent intent = getIntent();
        currentSite = intent.getParcelableExtra(EXTRA_SITE);

        int siteID = currentSite.getSiteID();
        String siteTitle = currentSite.getTitle();
        textViewSiteTitle.setText(siteTitle);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        NetAdapter adapter = new NetAdapter();
        recyclerView.setAdapter(adapter);

        netViewModel = new ViewModelProvider(this, new NetViewModelFactory(this.getApplication(), siteID)).get(NetViewModel.class);

        netViewModel.getNetsBySiteID(siteID).observe(this, new Observer<List<Net>>() {
            @Override
            public void onChanged(List<Net> nets) {
                adapter.setNets(nets);
            }
        });

        FloatingActionButton buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                Intent intent = new Intent(ListNetsActivity.this, AddNetActivity.class);
                intent.putExtra(AddNetActivity.EXTRA_SITE, currentSite);
                startActivityForResult(intent, ADD_NET_ACTIVITY_REQUEST_CODE);
*/
            }
        });

        adapter.setOnItemClickListener(new NetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Net net) {
/*
                Intent intent = new Intent(ListNetsActivity.this, ShowNetDetailsActivity.class);
                intent.putExtra(ShowNetDetailsActivity.EXTRA_NET, net);
                intent.putExtra(ShowNetDetailsActivity.EXTRA_SITE, currentSite);
                startActivity(intent);
*/
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NET_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Net newNet = data.getParcelableExtra(EXTRA_NET);
            netViewModel.insertNet(newNet);
            Toast.makeText(getApplicationContext(), "Net saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Net not saved", Toast.LENGTH_LONG).show();
        }
    }
}