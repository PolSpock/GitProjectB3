package fr.garrycity.pol.gitprojectb3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.models.Profile;
import fr.garrycity.pol.gitprojectb3.tasks.ImageLoadTask;

/**
 * Created by Pol on 29/04/2017.
 */

public class ProfileAdapter extends BaseAdapter {
    Context context;
    int layoutResourceId;
    List<Profile> listProfile;

    public ProfileAdapter(Context context, int layoutResourceId, List<Profile> listProfile) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.listProfile = listProfile;
    }

    @Override
    public int getCount() {
        return listProfile.size();
    }

    @Override
    public Object getItem(int position) {
        return listProfile.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_search_profile, parent, false);
        }
        TextView profileName = (TextView) convertView.findViewById(R.id.profileName);
        ImageView profileImg = (ImageView) convertView.findViewById(R.id.profileImg);

        Profile profile = listProfile.get(position);

        profileName.setText(profile.getLogin());

        new ImageLoadTask(profileImg)
                .execute(profile.getUrlAvatar());



        return convertView;
    }
}
