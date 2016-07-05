package org.ranta.android.apps.frisbeer.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.ranta.android.apps.frisbeer.R;
import org.ranta.android.frisbeer.api.FrisbeerClient;
import org.ranta.android.frisbeer.model.Game;
import org.ranta.android.apps.frisbeer.tasks.FetchGamesTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GamesFragment extends Fragment implements FetchGamesTask.FetchGamesListener {
    @BindView(R.id.list_games)
    ListView mGamesList;

    public GamesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_games, container, false);
        ButterKnife.bind(this, view);


        final FrisbeerClient client = ((MainActivity) getActivity()).getClient();
        FetchGamesTask task = new FetchGamesTask(client, this);
        task.execute("");

        return view;
    }

    @Override
    public void onGamesLoaded(List<Game> games) {
        if (games != null) {
            mGamesList.setAdapter(new GameAdapter(getActivity(), games));
        }
    }
}