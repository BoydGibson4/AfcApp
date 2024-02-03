// Package containing code related to resolving team names and their respective image resources
package application.afc.TableApi;

// Importing necessary resources and classes
import application.afc.R;
import android.content.res.Resources;

// This links the team id to a team name
// Class responsible for resolving team names and their image resources
public class TeamNameResolver {

    // Method to resolve team names based on team numbers
    public static String resolveTeamName(int teamNumber, Resources resources) {
        String teamName;
        // Switch case to determine the team name based on the team number
        switch (teamNumber) {
            case 53:
                teamName = "Celtic";
                break;
            case 62:
                teamName = "Rangers";
                break;
            case 496:
                teamName = "St. Mirren";
                break;
            case 314:
                teamName = "Hearts";
                break;
            case 284:
                teamName = "Dundee";
                break;
            case 180:
                teamName = "Kilmarnock";
                break;
            case 66:
                teamName = "Hibernian";
                break;
            case 309:
                teamName = "Motherwell";
                break;
            case 273:
                teamName = "Aberdeen";
                break;
            case 734:
                teamName = "St. Johnstone";
                break;
            case 246:
                teamName = "Ross County";
                break;
            case 258:
                teamName = "Livingstone";
                break;
            default:
                teamName = "Unknown"; // Default team name if no match found
                break;
        }
        return teamName;
    }

    // Method to resolve team image resources based on team numbers
    public static int resolveTeamImageResource(int teamNumber, Resources resources, String packageName) {
        int imageResId = resources.getIdentifier("team_" + teamNumber, "drawable", packageName);
        if (imageResId == 0) {
            // If no matching image resource found, use a default image resource
            imageResId = R.drawable.default_image;
        }
        return imageResId;
    }
}
