package org.ranta.android.frisbeer.model;

import android.support.v4.util.Pair;


import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Set;

/**
 * Model representing one frisbeer game
 */
public class Game implements Comparable<Game> {
    private int id;

    private Set<Player> team1;
    private Set<Player> team2;
    private String name;
    private DateTime date;
    private String description;

    private int team1_score;
    private int team2_score;

    public Game(int id,
                Set<Player> team1,
                Set<Player> team2,
                String name,
                DateTime date,
                String description,
                int team1_score,
                int team2_score) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.name = name;
        this.date = date;
        this.description = description;
        this.team1_score = team1_score;
        this.team2_score = team2_score;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        this.name = mName;
    }

    public int getId() {
        return id;
    }

    public void setId(int mId) {
        this.id = mId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime mDate) {
        this.date = mDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    public Pair<Team, Team> getTeams() {
        return new Pair<>(new Team(Arrays.asList(team1.toArray(new Player[3]))),
                new Team(Arrays.asList(team2.toArray(new Player[3]))));
    }

    public Pair<Integer, Integer> getScore() {
        return new Pair<>(team1_score, team2_score);
    }

    public void setScore(int team1score, int team2score) {
        team1_score = team1score;
        team2_score = team2score;
    }

    public void setTeam1Score(int score) {
        team1_score = score;
    }

    public void setTeam2Score(int score) {
        team2_score = score;
    }


    /**
     * Comparison for sorting by date
     * @param another
     * @return
     */
    @Override
    public int compareTo(Game another) {
        return (this.date.compareTo(another.date));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (team1_score != game.team1_score) return false;
        if (team2_score != game.team2_score) return false;
        if (team1 != null ? !team1.equals(game.team1) : game.team1 != null) return false;
        if (team2 != null ? !team2.equals(game.team2) : game.team2 != null) return false;
        if (name != null ? !name.equals(game.name) : game.name != null) return false;
        if (date != null ? !date.equals(game.date) : game.date != null) return false;
        return description != null ? description.equals(game.description) : game.description == null;

    }

    @Override
    public int hashCode() {
        int result = team1 != null ? team1.hashCode() : 0;
        result = 31 * result + (team2 != null ? team2.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + team1_score;
        result = 31 * result + team2_score;
        return result;
    }
}
