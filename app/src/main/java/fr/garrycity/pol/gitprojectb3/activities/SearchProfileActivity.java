package fr.garrycity.pol.gitprojectb3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.adapters.ProfileAdapter;
import fr.garrycity.pol.gitprojectb3.models.Profile;
import fr.garrycity.pol.gitprojectb3.tasks.ParseJSONTask;
import fr.garrycity.pol.gitprojectb3.tasks.GetFeedTask;
import fr.garrycity.pol.gitprojectb3.tasks.TaskDelegate;

/**
 * Created by Pol on 29/04/2017.
 */

public class SearchProfileActivity extends AppCompatActivity implements TaskDelegate {
    LinearLayout layout = null;
    private SearchView gitProfileSearch = null;
    List<Profile> listProfile = new ArrayList<>();
    ProfileAdapter profileAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (LinearLayout) LinearLayout.inflate(this, R.layout.activity_search_profile, null);

        gitProfileSearch = (SearchView) layout.findViewById(R.id.searchProfileGit);
        gitProfileSearch.setQueryHint("Search Profile");

        gitProfileSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(query);


                GetFeedTask apiRequest = new GetFeedTask(SearchProfileActivity.this, query, "search", "users");
                apiRequest.execute();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        listView = (ListView) layout.findViewById(R.id.listProfileSearch);

        profileAdapter = new ProfileAdapter(this, R.id.listProfileSearch, listProfile);

        listView.setAdapter(profileAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Profile profile = (Profile) listView.getItemAtPosition(pos);

                changeSearchProfileToProfileView(profile);
            }
        });

        setContentView(layout);
    }

    @Override
    public void onTaskResult(String response) {
        new ParseJSONTask().ProfileSearchParse(response, listProfile);
        profileAdapter.notifyDataSetChanged();
    }


    private void changeSearchProfileToProfileView(Profile profile) {
        Intent intent = new Intent(this, ProfileViewActivity.class);

        intent.putExtra("profile", profile);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
