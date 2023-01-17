package tn.esprit.spring.entity;

public class TeamSearchCriteria {
    private String teamName;
    private Integer points;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String firstName) {
        this.teamName = teamName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
