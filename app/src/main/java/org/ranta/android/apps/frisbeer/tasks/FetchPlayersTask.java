package org.ranta.android.apps.frisbeer.tasks;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.ranta.android.frisbeer.api.FrisbeerClient;
import org.ranta.android.frisbeer.api.RestResponse;
import org.ranta.android.frisbeer.model.Player;
import org.ranta.android.frisbeer.api.JsonUtil;

import java.io.IOException;
import java.util.List;

import timber.log.Timber;

/**
 * Task for fetching the player list from API
 */
public class FetchPlayersTask extends AsyncTask<String,Void,List<Player>> {
    final FrisbeerClient mClient;
    final FetchPlayersListener mListener;

    public FetchPlayersTask(FrisbeerClient client, FetchPlayersListener listener) {
        mClient = client;
        mListener = listener;

    }

    public interface FetchPlayersListener {
        void onPlayersLoaded(List<Player> players);
    }

    @Override
    protected List<Player> doInBackground(String... params) {

        try {
            Timber.v("Fetching players");
            RestResponse<JSONArray> playerResponse = mClient.getPlayers().execute();

            if (!playerResponse.isSuccess()) {
                Timber.e("Failed fetching players");
                return null;
            }

            return JsonUtil.getPlayerList(playerResponse.getResult());
        } catch (IOException e) {
            Timber.e(e, "Error fetching players");
        } catch (JSONException e) {
            Timber.e(e, "Error parsing JSON");
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Player> players) {
        mListener.onPlayersLoaded(players);
    }


}
