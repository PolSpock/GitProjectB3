package fr.garrycity.pol.gitprojectb3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.models.Repository;

/**
 * Created by Pol on 11/04/2017.
 */

public class RepositoryAdapter extends BaseAdapter {
    Context context;
    int layoutResourceId;
    List<Repository> listRepository;

    public RepositoryAdapter(Context context, int layoutResourceId, List<Repository> listRepository) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.listRepository = listRepository;
    }

    @Override
    public int getCount() {
        return listRepository.size();
    }

    @Override
    public Object getItem(int position) {
        return listRepository.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_search_repository, parent, false);
        }
        TextView lblName = (TextView) convertView.findViewById(R.id.lblName);
        TextView lblDescription = (TextView) convertView.findViewById(R.id.lblDescription);
        TextView lblFullName = (TextView) convertView.findViewById(R.id.lblFullName);

        Repository repository = listRepository.get(position);

        lblName.setText(repository.getName());
        lblFullName.setText(repository.getFullName());
        lblDescription.setText(repository.getDescription());


        return convertView;
    }
}
