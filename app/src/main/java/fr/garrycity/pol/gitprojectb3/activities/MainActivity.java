package fr.garrycity.pol.gitprojectb3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.adapters.HomeAdapter;
import fr.garrycity.pol.gitprojectb3.models.Item;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);

        final GridView gridView = (GridView) layout.findViewById(R.id.homeGridView);
        gridView.setAdapter(new HomeAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {

                Item item = (Item) gridView.getItemAtPosition(pos);

                changeHomeToItemUsage(item);
            }
        });

        setContentView(layout);

    }

    private void changeHomeToItemUsage(Item item) {

        Intent intent;

        switch(item.getUsage()) {
            case "repository":
                intent = new Intent(this, SearchRepositoryActivity.class);
                break;
            case "profile":
                intent = new Intent(this, SearchProfileActivity.class);
                break;
            case "gist":
                intent = new Intent(this, SearchGistActivity.class);
                break;
            default:
                intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
    }
}