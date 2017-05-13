package fr.garrycity.pol.gitprojectb3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.models.Gist;
import fr.garrycity.pol.gitprojectb3.tasks.GetFeedTask;
import fr.garrycity.pol.gitprojectb3.tasks.TaskDelegate;

/**
 * Created by Pol on 06/05/2017.
 */

public class GistViewActivity extends AppCompatActivity implements TaskDelegate {

    private Gist activeGist;
    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.view_gist, null);

        Intent i = getIntent();
        activeGist = (Gist)i.getSerializableExtra("gist");

        this.setTitle(activeGist.getFilename());

        GetFeedTask apiRequest = new GetFeedTask(GistViewActivity.this, activeGist.getRawUrl(), "raw_url", "");
        apiRequest.execute();

        setContentView(layout);
    }

    @Override
    public void onTaskResult(String response) {
        TextView gistRaw = (TextView) layout.findViewById(R.id.gistRaw);
        gistRaw.setMovementMethod(new ScrollingMovementMethod());

        gistRaw.setText(response);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
