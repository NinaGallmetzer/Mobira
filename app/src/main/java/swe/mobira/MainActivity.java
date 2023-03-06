package swe.mobira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import swe.mobira.entities.site.ActivityListSites;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_SITE_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_SITE_ACTIVITY_REQUEST_CODE = 2;
    public static final int ADD_R_RECORD_ACTIVITY_REQUEST_CODE = 3;
    public static final int EDIT_R_RECORD_ACTIVITY_REQUEST_CODE = 4;
    public static final int ADD_NET_ACTIVITY_REQUEST_CODE = 5;
    public static final int EDIT_NET_ACTIVITY_REQUEST_CODE = 6;
    public static final int ADD_B_RECORD_ACTIVITY_REQUEST_CODE = 7;
    public static final int EDIT_B_RECORD_ACTIVITY_REQUEST_CODE = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityListSites.class);
                startActivity(intent);

                // Do something in response to button click
            }
        });
    }
}