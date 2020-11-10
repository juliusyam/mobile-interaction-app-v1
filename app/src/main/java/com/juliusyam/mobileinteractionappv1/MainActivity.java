package com.juliusyam.mobileinteractionappv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ExampleAdapter mAdapter;

    private final float latitude = 53.947033f;
    private final float longitude = -1.032109f;

    private final String CLIENT_ID = "CMB2RQVQV5VLWSFLAZJVAZTM1FAVPRN3DUUSNHSX45LEFOQB";
    private final String CLIENT_SECRET = "Y5Z2QNICJCDKAUVZFHPDOXCNUJA3OQBWTSY311CTA0X0JQ42";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jsonButton = findViewById(R.id.json_button);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        jsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = new StringBuilder()
                        .append("https://api.foursquare.com/v2/venues/search?client_id=")
                        .append(CLIENT_ID)
                        .append("&client_secret=")
                        .append(CLIENT_SECRET)
                        .append("&v=20130815&ll=")
                        .append(latitude)
                        .append(',')
                        .append(longitude)
                        .toString();
                try {
                    URL url = new URL(path);
                    new FetchData((JSONArray jsonArray) -> {
                        // something to do with your jsonArray
                        mAdapter.updateJsonArray(jsonArray);
                    }).execute(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void homePressed(View view) {
        Intent intent = new Intent (this, MainActivity. class);
        startActivity(intent);
    }
    public void dicePressed(View view) {
        Intent intent = new Intent (this, DiceRoller. class);
        startActivity(intent);
    }
    public void deckPressed(View view) {
        Intent intent = new Intent (this, CardDeck. class);
        startActivity(intent);
    }
}