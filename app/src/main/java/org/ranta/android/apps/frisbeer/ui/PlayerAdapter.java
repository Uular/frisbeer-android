package org.ranta.android.apps.frisbeer.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.ranta.android.apps.frisbeer.R;
import org.ranta.android.frisbeer.model.Player;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for displaying players in a list
 */
public class PlayerAdapter extends ArrayAdapter<Player> {

    private final LayoutInflater mInflater;

    public PlayerAdapter(Context context, List<Player> players) {
        super(context, 0, players);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.list_item_player, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final Player player = getItem(position);
        holder.name.setText(player.getName());
        holder.elo.setText(String.valueOf(player.getElo()));

        return view;
    }

    static final class ViewHolder {
        @BindView(R.id.text_player_name)
        TextView name;

        @BindView(R.id.text_player_elo)
        TextView elo;

        @BindView(R.id.check_player_select)
        CheckBox checkBox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
