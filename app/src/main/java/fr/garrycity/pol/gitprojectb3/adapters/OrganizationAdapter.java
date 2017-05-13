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
import fr.garrycity.pol.gitprojectb3.models.Organization;
import fr.garrycity.pol.gitprojectb3.tasks.ImageLoadTask;

/**
 * Created by Pol on 11/04/2017.
 */

public class OrganizationAdapter extends BaseAdapter {
    Context context;
    int layoutResourceId;
    List<Organization> listOrganization;

    public OrganizationAdapter(Context context, int layoutResourceId, List<Organization> listOrganization) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.listOrganization = listOrganization;
    }

    @Override
    public int getCount() {
        return listOrganization.size();
    }

    @Override
    public Object getItem(int position) {
        return listOrganization.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_organization, parent, false);
        }
        TextView organizationLogin = (TextView) convertView.findViewById(R.id.organizationLogin);
        TextView organizationDescription = (TextView) convertView.findViewById(R.id.organizationDescription);
        ImageView organizationImg = (ImageView) convertView.findViewById(R.id.organizationImg);


        Organization organization = listOrganization.get(position);

        organizationLogin.setText(organization.getLogin());
        organizationDescription.setText(organization.getDescription());

        new ImageLoadTask(organizationImg)
                .execute(organization.getAvatarUrl());

        return convertView;
    }
}
