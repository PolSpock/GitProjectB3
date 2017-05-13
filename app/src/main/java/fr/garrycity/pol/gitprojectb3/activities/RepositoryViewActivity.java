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
import fr.garrycity.pol.gitprojectb3.models.Repository;
import fr.garrycity.pol.gitprojectb3.tasks.ImageLoadTask;

/**
 * Created by Pol on 11/04/2017.
 */

public class RepositoryViewActivity extends AppCompatActivity {
    private Repository activeRepository;
    LinearLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (LinearLayout) LinearLayout.inflate(this, R.layout.view_repository, null);

        Intent i = getIntent();
        activeRepository = (Repository)i.getSerializableExtra("repository");

        this.setTitle(activeRepository.getName());

        ImageView repoOwnerImg = (ImageView) layout.findViewById(R.id.repoOwnerImg);
        new ImageLoadTask(repoOwnerImg)
                .execute(activeRepository.getAvatar());

        TextView repoName = (TextView) layout.findViewById(R.id.repoName);
        TextView repoFullName = (TextView) layout.findViewById(R.id.repoFullName);
        TextView repoDescription = (TextView) layout.findViewById(R.id.repoDescription);
        TextView repoCreated = (TextView) layout.findViewById(R.id.repoCreated);
        TextView repoPushed = (TextView) layout.findViewById(R.id.repoPushed);
        TextView repoUpdated = (TextView) layout.findViewById(R.id.repoUpdated);
        TextView repoLogin = (TextView) layout.findViewById(R.id.repoLogin);
        TextView repoWatchers = (TextView) layout.findViewById(R.id.repoWatchers);
        TextView repoForks = (TextView) layout.findViewById(R.id.repoForks);
        TextView repoLanguage = (TextView) layout.findViewById(R.id.repoLanguage);

        repoName.setText(getString(R.string.repo_name) + " " + activeRepository.getName());
        repoFullName.setText(getString(R.string.repo_fullname) + " " + activeRepository.getFullName());
        repoDescription.setText(getString(R.string.repo_description) + " " + activeRepository.getDescription());
        repoCreated.setText(getString(R.string.repo_created) + " " + activeRepository.getCreated());
        repoPushed.setText(getString(R.string.repo_pushed) + " " + activeRepository.getPushed());
        repoUpdated.setText(getString(R.string.repo_updated) + " " + activeRepository.getUpdated());
        repoLogin.setText(getString(R.string.repo_login) + " " + activeRepository.getLogin());
        repoWatchers.setText(getString(R.string.repo_watchers) + " " + activeRepository.getWatchers());
        repoForks.setText(getString(R.string.repo_forks) + " " + activeRepository.getForks());
        repoLanguage.setText(getString(R.string.repo_language) + " " + activeRepository.getLanguage());

        Button repoShare = (Button) layout.findViewById(R.id.repoShare);
        repoShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, activeRepository.getFullName());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, activeRepository.getHtmlUrl());
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });


        setContentView(layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
