package fr.garrycity.pol.gitprojectb3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONObject;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.models.Gist;
import fr.garrycity.pol.gitprojectb3.tasks.ParseJSONTask;
import fr.garrycity.pol.gitprojectb3.tasks.PostFeedTask;
import fr.garrycity.pol.gitprojectb3.tasks.TaskDelegate;

/**
 * Created by Pol on 06/05/2017.
 */

public class CreateGistActivity extends AppCompatActivity implements TaskDelegate {
    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_create_gist, null);

        final EditText gistCreateFilename = (EditText) layout.findViewById(R.id.gistCreateFilename);
        final EditText gistCreateDescription = (EditText) layout.findViewById(R.id.gistCreateDescription);
        final EditText gistCreateContent = (EditText) layout.findViewById(R.id.gistCreateContent);
        final CheckBox gistCreateIsPublic = (CheckBox) layout.findViewById(R.id.gistCreateIsPublic);
        final Button buttonSubmit = (Button) layout.findViewById(R.id.gistCreateSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!gistCreateFilename.getText().equals("") && !gistCreateDescription.getText().equals("") && !gistCreateContent.getText().equals("")) {

                    JSONObject jsonObject = new ParseJSONTask().GistCreate(gistCreateFilename.getText().toString(), gistCreateDescription.getText().toString(), gistCreateContent.getText().toString(), gistCreateIsPublic.isPressed());
                    PostFeedTask apiRequest = new PostFeedTask(CreateGistActivity.this, jsonObject);
                    apiRequest.execute();
                }

            }
        });

        setContentView(layout);

    }

    @Override
    public void onTaskResult(String response) {
        Gist createdGist = new Gist();
        new ParseJSONTask().GistOneParse(response, createdGist);

        System.out.println(createdGist.getFilename() + createdGist.getLanguage() + createdGist.getRawUrl() + createdGist.getType());

        Intent intent = new Intent(this, GistViewActivity.class);
        intent.putExtra("gist", createdGist);
        startActivity(intent);
    }
}
