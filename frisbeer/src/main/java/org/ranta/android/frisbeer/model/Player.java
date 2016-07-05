package org.ranta.android.frisbeer.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Player model class
 */
public class Player {
    private int id;

    private String name;

    private int elo;

    private String rank;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getElo() {
        return elo;
    }

    public String getRank() {
        return rank;
    }

    public Player(int id, @NonNull String name, int elo, @Nullable String rank) {
        this.id = id;
        this.name = name;
        this.elo = elo;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        if (elo != player.elo) return false;
        return name != null ? name.equals(player.name) : player.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + elo;
        return result;
    }
}
