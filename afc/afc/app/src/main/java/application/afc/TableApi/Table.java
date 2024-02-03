// Package containing code related to table data
package application.afc.TableApi;


// Class representing table data for a team
public class Table {

    private int participant_id; // Unique identifier for a team
    private String position; // Position of the team in the table
    private String points; // Points earned by the team

    // Getter method for retrieving the team's participant Id
    public int getTeam() {
        return participant_id;
    }

    // Getter method for retrieving the team's position in the table
    public String getPosition() {
        return position;
    }

    // Getter method for retrieving the teams points in the table
    public String getPoints() {
        return points;
    }

    // Inner class representing additional table data if needed
    public static class TableData {
        private String dataField1; // Additional data field 1
        private String dataField2; // Additional data field 2
    }
}
