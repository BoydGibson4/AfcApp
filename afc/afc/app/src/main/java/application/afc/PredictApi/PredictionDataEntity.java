// Package containing code related to the PredictApi
package application.afc.PredictApi;

// Importing necessary Room library classes for defining an entity
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Entity class representing PredictionData in the database
@Entity(tableName = "prediction_data_table")
public class PredictionDataEntity {

    @PrimaryKey(autoGenerate = true)
    private int id; // Primary key for the entity

    // Getters and setters for the fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Fields representing predictions
    private String leaguePrediction;
    private String scupPrediction;
    private String lcupPrediction;
    private String europePrediction;
    private String leagueWinner;
    private String topGoalScorer;
    private String playerOfTheSeason;
    private String youngPlayerOfTheSeason;
    private String relegatedTeam;

    // Getters and setters for each field


    public String getLeaguePrediction() {
        return leaguePrediction;
    }

    public void setLeaguePrediction(String leaguePrediction) {
        this.leaguePrediction = leaguePrediction;
    }

    public String getScupPrediction() {
        return scupPrediction;
    }

    public void setScupPrediction(String scupPrediction) {
        this.scupPrediction = scupPrediction;
    }

    public String getLcupPrediction() {
        return lcupPrediction;
    }

    public void setLcupPrediction(String lcupPrediction) {
        this.lcupPrediction = lcupPrediction;
    }

    public String getEuropePrediction() {
        return europePrediction;
    }

    public void setEuropePrediction(String europePrediction) {
        this.europePrediction = europePrediction;
    }

    public String getLeagueWinner() {
        return leagueWinner;
    }

    public void setLeagueWinner(String leagueWinner) {
        this.leagueWinner = leagueWinner;
    }

    public String getTopGoalScorer() {
        return topGoalScorer;
    }

    public void setTopGoalScorer(String topGoalScorer) {
        this.topGoalScorer = topGoalScorer;
    }

    public String getPlayerOfTheSeason() {
        return playerOfTheSeason;
    }

    public void setPlayerOfTheSeason(String playerOfTheSeason) {
        this.playerOfTheSeason = playerOfTheSeason;
    }

    public String getYoungPlayerOfTheSeason() {
        return youngPlayerOfTheSeason;
    }

    public void setYoungPlayerOfTheSeason(String youngPlayerOfTheSeason) {
        this.youngPlayerOfTheSeason = youngPlayerOfTheSeason;
    }

    public String getRelegatedTeam() {
        return relegatedTeam;
    }

    public void setRelegatedTeam(String relegatedTeam) {
        this.relegatedTeam = relegatedTeam;
    }
}