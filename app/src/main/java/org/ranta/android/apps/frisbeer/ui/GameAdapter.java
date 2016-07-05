package org.ranta.android.apps.frisbeer.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ranta.android.apps.frisbeer.R;
import org.ranta.android.frisbeer.model.Game;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for displaying Game info
 */
public class GameAdapter extends ArrayAdapter<Game> {
    LayoutInflater mInflater;

    public GameAdapter(Context context, List<Game> games) {
        super(context, 0, games);
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.list_item_game, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Game game = getItem(position);
        final String[] team1Names = new String[3];
        final String[] team2Names = new String[3];
        for (int i=0; i < 3; i++) {
            team1Names[i] = game.getTeams().first.getPlayer(i).getName();
            team2Names[i] = game.getTeams().second.getPlayer(i).getName();
        }

        holder.team1.setText(TextUtils.join(", ", team1Names));
        holder.team2.setText(TextUtils.join(", ", team2Names));

        holder.score1.setText(String.valueOf(game.getScore().first));
        holder.score2.setText(String.valueOf(game.getScore().second));

        return view;
    }

    static final class ViewHolder {
        @BindView(R.id.text_team1_players) TextView team1;
        @BindView(R.id.text_team1_score) TextView score1;

        @BindView(R.id.text_team2_players) TextView team2;
        @BindView(R.id.text_team2_score) TextView score2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
