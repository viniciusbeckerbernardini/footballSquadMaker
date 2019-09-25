package fadergs.squadmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class TeamAdapter extends BaseAdapter {

    private List<Team> teamsList;
    private Context context;
    private LayoutInflater inflater;

    public TeamAdapter(Context context, List<Team> teamList, LayoutInflater inflater){
        this.context = context;
        this.teamsList = teamList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return this.teamsList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.teamsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_layout_list,null);

        return view;
    }
}
