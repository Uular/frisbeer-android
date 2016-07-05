package org.ranta.android.apps.frisbeer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.ranta.android.apps.frisbeer.R;
import org.ranta.android.frisbeer.api.FrisbeerClient;
import org.ranta.android.frisbeer.model.Player;
import org.ranta.android.apps.frisbeer.tasks.FetchPlayersTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlayersFragment extends Fragment implements FetchPlayersTask.FetchPlayersListener {

    @BindView(R.id.list_players)
    ListView mPlayerList;

    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_players, container, false);
        ButterKnife.bind(this, view);

        final FrisbeerClient client = ((MainActivity) getActivity()).getClient();
        FetchPlayersTask task = new FetchPlayersTask(client, this);
        task.execute("");

        return view;
    }

    @Override
    public void onPlayersLoaded(List<Player> players) {
        if (players != null) {
            mPlayerList.setAdapter(new PlayerAdapter(getActivity(), players));
        }
    }
}