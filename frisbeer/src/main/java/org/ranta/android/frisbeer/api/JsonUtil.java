package org.ranta.android.frisbeer.api;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ranta.android.frisbeer.model.Game;
import org.ranta.android.frisbeer.model.Player;
import org.ranta.android.frisbeer.model.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Uula on 2016-06-07.
 */
public class JsonUtil {

    public static JSONObject toJson(final Player player) throws JSONException {
        JSONObject pObject = new JSONObject();
        pObject.put("id", player.getId());
        pObject.put("name", player.getName());
        pObject.put("elo", player.getElo());
        return pObject;
    }


    public static JSONObject toJson(final Game game) throws JSONException {
        final JSONObject gObject = new JSONObject();
        gObject.put("id", game.getId());
        gObject.put("date", game.getDate().toString());
        gObject.put("name", game.getName() == null ? "" : game.getName());
        gObject.put("description", game.getDescription() == null ? "" : game.getDescription());
        gObject.put("team1_score", game.getScore().first.intValue());
        gObject.put("team2_score", game.getScore().second.intValue());
        final Team team1 = game.getTeams().first;
        final Team team2 = game.getTeams().second;
        final JSONArray team1Array = new JSONArray();
        final JSONArray team2Array = new JSONArray();
        for (int i=0; i < 3; i++) {
            team1Array.put(team1.getPlayer(i).getId());
            team2Array.put(team2.getPlayer(i).getId());
        }
        return gObject;
    }


    private static Team formTeam(JSONArray teamIds, Set<Player> players) throws JSONException {
        List<Player> team = new ArrayList<>(3);
        for (int i=0; i < teamIds.length(); i++) {
            final int id = teamIds.getInt(i);
            for (Player p: players) {
                if (p.getId() == id) {
                    team.add(p);
                    break;
                }
            }
        }
        return new Team(team.get(0), team.get(1), team.get(2));
    }

    public static Player getPlayer(JSONObject obj) throws JSONException {
        final int id = obj.getInt("id");
        final String name = obj.getString("name");
        final int elo = obj.getInt("elo");
        final String rank = obj.getString("rank");
        return new Player(id, name, elo, rank);
    }

    public static List<Player> getPlayerList(JSONArray array) throws JSONException {
        final List<Player> players = new ArrayList<>(array.length());
        for (int i = 0; i < array.length(); i++) {
            players.add(getPlayer(array.getJSONObject(i)));
        }
        return players;
    }

    public static Game getGame(JSONObject obj, Collection<Player> allPlayers) throws JSONException {
        final int id = obj.getInt("id");
        final String name = obj.getString("name");
        final String descr = obj.getString("description");
        final int team1score = obj.getInt("team1_score");
        final int team2score = obj.getInt("team2_score");
        final Set<Player> team1 = getPlayersById(allPlayers, getIntArray(obj.getJSONArray("team1")));
        final Set<Player> team2 = getPlayersById(allPlayers, getIntArray(obj.getJSONArray("team2")));
        final DateTime date = new DateTime(obj.getString("date"));

        return new Game(id, team1, team2, name, date, descr, team1score, team2score);
    }

    public static List<Game> getGameList(JSONArray array, Collection<Player> allPlayers) throws JSONException {
        final List<Game> games = new ArrayList<>(array.length());
        for (int i = 0; i < array.length(); i++) {
            games.add(getGame(array.getJSONObject(i), allPlayers));
        }
        return games;
    }

    public static int[] getIntArray(JSONArray array) throws JSONException {
        final int[] ints = new int[array.length()];
        for (int i = 0; i < array.length(); i++) {
            ints[i] = array.getInt(i);
        }
        return ints;
    }

    public static Player findPlayerById(Collection<Player> players, int id) {
        for (Player p: players) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }

    public static Set<Player> getPlayersById(Collection<Player> players, int[] ids) {
        Set<Player> ret = new HashSet<>(3);
        for (int id: ids) {
            ret.add(findPlayerById(players, id));
        }

        return ret;
    }
}
