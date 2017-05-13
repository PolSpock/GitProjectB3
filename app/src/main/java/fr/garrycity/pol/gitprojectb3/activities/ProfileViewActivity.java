package fr.garrycity.pol.gitprojectb3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.models.Profile;
import fr.garrycity.pol.gitprojectb3.tasks.GetFeedTask;
import fr.garrycity.pol.gitprojectb3.tasks.ImageLoadTask;
import fr.garrycity.pol.gitprojectb3.tasks.ParseJSONTask;
import fr.garrycity.pol.gitprojectb3.tasks.TaskDelegate;

/**
 * Created by Pol on 11/04/2017.
 */

public class ProfileViewActivity extends AppCompatActivity implements TaskDelegate  {
    private Profile activeProfile;
    LinearLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (LinearLayout) LinearLayout.inflate(this, R.layout.view_profile, null);

        Intent i = getIntent();
        activeProfile = (Profile)i.getSerializableExtra("profile");

        this.setTitle(activeProfile.getLogin());

        GetFeedTask apiRequest = new GetFeedTask(ProfileViewActivity.this, activeProfile.getUrl(), "users", "profile");
        apiRequest.execute();

        setContentView(layout);
    }

    @Override
    public void onTaskResult(String response) {
        new ParseJSONTask().ProfileParse(response, activeProfile);
        loadProfile();
    }

    private void loadProfile() {
        ImageView profileAvatar = (ImageView) layout.findViewById(R.id.profileAvatar);
        new ImageLoadTask(profileAvatar)
                .execute(activeProfile.getUrlAvatar());

        TextView profileLogin = (TextView) layout.findViewById(R.id.profileLogin);
        TextView profileName = (TextView) layout.findViewById(R.id.profileName);
        TextView profileCompany = (TextView) layout.findViewById(R.id.profileCompany);
        TextView profileLocation = (TextView) layout.findViewById(R.id.profileLocation);
        TextView profileEmail = (TextView) layout.findViewById(R.id.profileEmail);
        TextView profileBio = (TextView) layout.findViewById(R.id.profileBio);
        TextView profilePublicRepos = (TextView) layout.findViewById(R.id.profilePublicRepos);
        TextView profilePublicGists = (TextView) layout.findViewById(R.id.profilePublicGists);
        TextView profileFollowers = (TextView) layout.findViewById(R.id.profileFollowers);
        TextView profileFollowing = (TextView) layout.findViewById(R.id.profileFollowing);
        TextView profileCreated = (TextView) layout.findViewById(R.id.profileCreated);
        TextView profileUpdated = (TextView) layout.findViewById(R.id.profileUpdated);

        profileLogin.setText(getString(R.string.profile_login) + " " + activeProfile.getLogin());
        profileName.setText(getString(R.string.profile_name) + " " + activeProfile.getName());
        profileCompany.setText(getString(R.string.profile_company) + " " + activeProfile.getCompany());
        profileLocation.setText(getString(R.string.profile_location) + " " + activeProfile.getLocation());
        profileEmail.setText(getString(R.string.profile_email) + " " + activeProfile.getEmail());
        profileBio.setText(getString(R.string.profile_bio) + " " + activeProfile.getBio());
        profilePublicRepos.setText(getString(R.string.profile_public_repos) + " " + activeProfile.getPublic_repos());
        profilePublicGists.setText(getString(R.string.profile_public_gists) + " " + activeProfile.getPublic_gists());
        profileFollowers.setText(getString(R.string.profile_followers) + " " + activeProfile.getFollowers());
        profileFollowing.setText(getString(R.string.profile_following) + " " + activeProfile.getFollowing());
        profileCreated.setText(getString(R.string.profile_created) + " " + activeProfile.getCreated());
        profileUpdated.setText(getString(R.string.profile_updated) + " " + activeProfile.getUpdated());

        Button profileListRepos = (Button) layout.findViewById(R.id.profileListRepos);
        Button profileListOrgz = (Button) layout.findViewById(R.id.profileListOrgz);

        profileListRepos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileViewActivity.this, ProfileViewReposActivity.class);
                intent.putExtra("repos_url", activeProfile.getRepos_url());
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        profileListOrgz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileViewActivity.this, ProfileViewOrgsActivity.class);
                intent.putExtra("orgs_url", activeProfile.getOrganizations_url());
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
