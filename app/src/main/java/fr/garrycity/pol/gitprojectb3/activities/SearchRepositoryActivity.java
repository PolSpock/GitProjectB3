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
import fr.garrycity.pol.gitprojectb3.adapters.RepositoryAdapter;
import fr.garrycity.pol.gitprojectb3.models.Repository;
import fr.garrycity.pol.gitprojectb3.tasks.GetFeedTask;
import fr.garrycity.pol.gitprojectb3.tasks.ParseJSONTask;
import fr.garrycity.pol.gitprojectb3.tasks.TaskDelegate;

/**
 * Created by Pol on 11/04/2017.
 */

public class SearchRepositoryActivity extends AppCompatActivity implements TaskDelegate {

    LinearLayout layout = null;
    List<Repository> listRepository = new ArrayList<>();
    RepositoryAdapter repositoryAdapter;
    private SearchView gitSearch = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (LinearLayout) LinearLayout.inflate(this, R.layout.activity_search_repository, null);

        gitSearch = (SearchView) layout.findViewById(R.id.searchRepositoryGit);
        gitSearch.setQueryHint("Search Repository");

        gitSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(query);

                //Intent intent = new Intent(SearchRepositoryActivity.this, SearchRepositoryActivity.class);

                //intent.putExtra("query", query);
                //startActivity(intent);

                GetFeedTask apiRequest = new GetFeedTask(SearchRepositoryActivity.this, query, "search", "repositories");
                apiRequest.execute();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        listView = (ListView) layout.findViewById(R.id.listRepositorySearch);

        repositoryAdapter = new RepositoryAdapter(this, R.id.listRepositorySearch, listRepository);

        listView.setAdapter(repositoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                Repository repository = (Repository) listView.getItemAtPosition(pos);

                changeSearchRepositoryToRepositoryView(repository);
            }
        });

        setContentView(layout);
    }

    @Override
    public void onTaskResult(String response) {
        new ParseJSONTask().RepositorySearchParse(response, listRepository);
        repositoryAdapter.notifyDataSetChanged();
    }

    private void changeSearchRepositoryToRepositoryView(Repository repository) {
        Intent intent = new Intent(this, RepositoryViewActivity.class);

        intent.putExtra("repository", repository);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
