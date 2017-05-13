package fr.garrycity.pol.gitprojectb3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.garrycity.pol.gitprojectb3.models.Item;
import fr.garrycity.pol.gitprojectb3.R;

/**
 * Created by Pol on 12/04/2017.
 */

public final class HomeAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;

    public HomeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);

        mItems.add(new Item("Repository", R.mipmap.ic_project, "repository"));
        mItems.add(new Item("Profile", R.mipmap.ic_account, "profile"));
        mItems.add(new Item("Gist", R.mipmap.ic_write, "gist"));
        mItems.add(new Item("Connection", R.mipmap.ic_connection, "connection"));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.item_home, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);

        Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
    }
}