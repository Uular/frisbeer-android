package org.ranta.android.apps.frisbeer.tasks;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.ranta.android.frisbeer.api.FrisbeerClient;
import org.ranta.android.frisbeer.api.JsonUtil;
import org.ranta.android.frisbeer.api.RestResponse;
import org.ranta.android.frisbeer.model.Game;
import org.ranta.android.frisbeer.model.Player;

import java.io.IOException;
import java.util.List;

import timber.log.Timber;

/**
 * Task for fetching the player list from API
 */
public class FetchGamesTask extends AsyncTask<String,Void,List<Game>> {
    final FrisbeerClient mClient;

    final FetchGamesListener mListener;

    public FetchGamesTask(@NonNull FrisbeerClient client, FetchGamesListener listener) {
        mClient = client;
        mListener = listener;
    }

    public interface FetchGamesListener {
        void onGamesLoaded(List<Game> games);
    }

    @Override
    protected List<Game> doInBackground(String... params) {

        try {
            Timber.v("Fetching players");
            RestResponse<JSONArray> playerResponse = mClient.getPlayers().execute();

            if (!playerResponse.isSuccess()) {
                Timber.e("Failed fetching players");
                return null;
            }

            List<Player> players = JsonUtil.getPlayerList(playerResponse.getResult());

            Timber.v("Fetching games");
            RestResponse<JSONArray> gameResponse = mClient.getGames().execute();

            if (!gameResponse.isSuccess()) {
                Timber.e("Failed fetching games");
            }

            return JsonUtil.getGameList(gameResponse.getResult(), players);

        } catch (IOException e) {
            Timber.e(e, "Error fetching from API");
        } catch (JSONException e) {
            Timber.e(e, "Error parsing json");
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Game> games) {
        mListener.onGamesLoaded(games);
    }
}
