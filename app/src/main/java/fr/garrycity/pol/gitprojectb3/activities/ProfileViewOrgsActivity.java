package fr.garrycity.pol.gitprojectb3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.adapters.OrganizationAdapter;
import fr.garrycity.pol.gitprojectb3.models.Organization;
import fr.garrycity.pol.gitprojectb3.tasks.GetFeedTask;
import fr.garrycity.pol.gitprojectb3.tasks.ParseJSONTask;
import fr.garrycity.pol.gitprojectb3.tasks.TaskDelegate;

/**
 * Created by Pol on 06/05/2017.
 */

public class ProfileViewOrgsActivity extends AppCompatActivity implements TaskDelegate {

    LinearLayout layout = null;
    List<Organization> listOrganization = new ArrayList<>();
    OrganizationAdapter organizationAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (LinearLayout) LinearLayout.inflate(this, R.layout.activity_profile_organization, null);

        this.setTitle("Organizations list");

        Intent i = getIntent();
        String query = (String) i.getSerializableExtra("orgs_url");

        GetFeedTask apiRequest = new GetFeedTask(this, query, "orgs", "");
        apiRequest.execute();

        listView = (ListView) layout.findViewById(R.id.listOrganizationProfile);

        organizationAdapter = new OrganizationAdapter(this, R.id.listOrganizationProfile, listOrganization);

        listView.setAdapter(organizationAdapter);

        setContentView(layout);
    }

    @Override
    public void onTaskResult(String response) {
        new ParseJSONTask().OrganizationProfileParse(response, listOrganization);
        organizationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
