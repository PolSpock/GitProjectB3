package fr.garrycity.pol.gitprojectb3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.adapters.GistAdapter;
import fr.garrycity.pol.gitprojectb3.models.Gist;
import fr.garrycity.pol.gitprojectb3.tasks.ParseJSONTask;
import fr.garrycity.pol.gitprojectb3.tasks.GetFeedTask;
import fr.garrycity.pol.gitprojectb3.tasks.TaskDelegate;

/**
 * Created by Pol on 05/05/2017.
 */

public class SearchGistActivity extends AppCompatActivity implements TaskDelegate {
    RelativeLayout layout = null;
    private SearchView gitGistSearch = null;
    List<Gist> listGist = new ArrayList<>();
    GistAdapter gistAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_search_gist, null);

        // Called onCreate for display public gist
        GetFeedTask apiRequest = new GetFeedTask(SearchGistActivity.this, "", "gists", "public");
        apiRequest.execute();

        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchGistActivity.this, CreateGistActivity.class);
                startActivity(intent);
            }
        });

        gitGistSearch = (SearchView) layout.findViewById(R.id.searchGist);
        gitGistSearch.setQueryHint("Search USER Gist");
        gitGistSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(query);

                GetFeedTask apiRequest = new GetFeedTask(SearchGistActivity.this, query, "gists", "users");
                apiRequest.execute();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        listView = (ListView) layout.findViewById(R.id.listGistSearch);

        gistAdapter = new GistAdapter(this, R.id.listGistSearch, listGist);

        listView.setAdapter(gistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Gist gist = (Gist) listView.getItemAtPosition(pos);

                changeSearchGistToGistView(gist);
            }
        });

        setContentView(layout);
    }

    @Override
    public void onTaskResult(String response) {
        new ParseJSONTask().GistSearchParse(response, listGist);
        gistAdapter.notifyDataSetChanged();
    }

    private void changeSearchGistToGistView(Gist gist) {
        Intent intent = new Intent(this, GistViewActivity.class);

        intent.putExtra("gist", gist);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
