package fr.garrycity.pol.gitprojectb3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.garrycity.pol.gitprojectb3.R;
import fr.garrycity.pol.gitprojectb3.models.Gist;

/**
 * Created by Pol on 11/04/2017.
 */

public class GistAdapter extends BaseAdapter {
    Context context;
    int layoutResourceId;
    List<Gist> listGist;

    public GistAdapter(Context context, int layoutResourceId, List<Gist> listGist) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.listGist = listGist;
    }

    @Override
    public int getCount() {
        return listGist.size();
    }

    @Override
    public Object getItem(int position) {
        return listGist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_search_gist, parent, false);
        }
        TextView gistFilename = (TextView) convertView.findViewById(R.id.gistFilename);
        TextView gistType = (TextView) convertView.findViewById(R.id.gistType);
        TextView gistLanguage = (TextView) convertView.findViewById(R.id.gistLanguage);

        Gist gist = listGist.get(position);

        gistFilename.setText(gist.getFilename());
        gistType.setText(gist.getType());
        gistLanguage.setText(gist.getLanguage());

        return convertView;
    }
}
