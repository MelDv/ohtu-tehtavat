package ohtu;

import java.util.Comparator;

public class Player {

    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;

    public int getSum() {
        return goals + assists;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    @Override
    public String toString() {
        for (int i = name.length(); i < 25; i++) {
            this.name = this.name.concat(" ");
        }
        return name + team + "\t   " + goals + " + " + assists + "\t  =    " + (goals + assists) + "\n";
    }
}
