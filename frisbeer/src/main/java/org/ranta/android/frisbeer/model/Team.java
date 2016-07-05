package org.ranta.android.frisbeer.model;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Uula on 2016-06-07.
 */
public class Team {

    private List<Player> players;

    public Team(List<Player> players) {
        Assert.assertEquals(players.size(), 3);
        this.players = players;
    }


    public Team(Player captain, Player p2, Player p3) {
        this.players = new ArrayList<>();
        this.players.add(captain);
        this.players.add(p2);
        this.players.add(p3);
    }

    public Player getPlayer(int index) {
        return this.players.get(index);
    }

    public Collection<Player> getPlayers() {
        return this.players;
    }

    public float averageElo() {
        return eloSum() / 3f;
    }

    public int eloSum() {
        int r = 0;
        for (Player p: players) {
            r += p.getElo();
        }

        return r;
    }
}
