package org.ranta.android.apps.frisbeer.util;

import android.util.Pair;

import junit.framework.Assert;

import org.ranta.android.frisbeer.model.Player;
import org.ranta.android.frisbeer.model.Team;

import java.util.List;

/**
 * Created by Uula on 2016-06-08.
 */
public class TeamUtil {
    private final static int[][] COMBINATIONS = {
            {0, 1, 2, 3, 4, 5},
            {0, 1, 3, 2, 4, 5},
            {0, 1, 4, 2, 3, 5},
            {0, 1, 5, 2, 3, 4},
            {0, 2, 3, 1, 4, 5},
            {0, 2, 4, 1, 3, 5},
            {0, 2, 5, 1, 3, 4},
            {0, 3, 4, 1, 2, 5},
            {0, 3, 5, 1, 2, 4},
            {0, 4, 5, 1, 2, 3}
    };

    public static Pair<Team, Team> buildTeams(List<Player> players) {
        Assert.assertEquals("Should only call with 6 players", players.size(), 6);
        Team team1 = null;
        Team team2 = null;
        int sum = 1 + 2 + 3 + 4 + 5;
        float minDiff = 900001;
        int first = 0;

        for (int[] combo: COMBINATIONS) {
            final Team t1 = new Team(players.get(combo[0]), players.get(combo[1]), players.get(combo[2]));
            final Team t2 = new Team(players.get(combo[3]), players.get(combo[4]), players.get(combo[5]));
            final float diff = Math.abs(t1.eloSum() - t2.eloSum());
            if (diff < minDiff) {
                minDiff = diff;
                team1 = t1;
                team2 = t2;
            }
        }

        if (team1 != null) {
            return new Pair<>(team1, team2);
        } else {
            return null;
        }
    }
}
