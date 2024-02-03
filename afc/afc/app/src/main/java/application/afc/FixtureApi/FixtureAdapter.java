package application.afc.FixtureApi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import application.afc.R;

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.ViewHolder> {

    // List of fixtures to be displayed
    private List<Fixture> fixtures;
    // Map to associate team names with their respective image resources
    private Map<String, Integer> teamImageMap;

    // Method to update fixtures in the adapter
    public void updateFixtures(List<Fixture> newFixtures) {
        fixtures.clear(); // Clear existing fixtures
        if (newFixtures != null) {
            fixtures.addAll(newFixtures); // Add new fixtures if not null
        }
        notifyDataSetChanged(); // Notify RecyclerView of data changes
    }

    // Constructor initializing the adapter with a list of fixtures
    public FixtureAdapter(List<Fixture> fixtures) {
        this.fixtures = fixtures;

        // Initialize and populate team image map with team names and their respective images
        teamImageMap = new HashMap<>();
        teamImageMap.put("Celtic", R.drawable.team_53);
        teamImageMap.put("Rangers", R.drawable.team_62);
        teamImageMap.put("Aberdeen", R.drawable.team_273);
        teamImageMap.put("St. Mirren", R.drawable.team_496);
        teamImageMap.put("Hibernian", R.drawable.team_66);
        teamImageMap.put("Hearts", R.drawable.team_314);
        teamImageMap.put("Ross County", R.drawable.team_246);
        teamImageMap.put("St. Johnstone", R.drawable.team_734);
        teamImageMap.put("Dundee", R.drawable.team_284);
        teamImageMap.put("Kilmarnock", R.drawable.team_180);
        teamImageMap.put("Motherwell", R.drawable.team_309);
        teamImageMap.put("Livingston", R.drawable.team_258);
    }

    // ViewHolder class representing views for individual items in the RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView startingTextView;
        private TextView resultTextView;
        private TextView lengthTextView;
        private ImageView team1ImageView;
        private ImageView team2ImageView;

        // Constructor initializing views within the ViewHolder
        public ViewHolder(View itemView) {
            super(itemView);
            // Find and initialize TextViews and ImageViews by their ids
            nameTextView = itemView.findViewById(R.id.Name);
            startingTextView = itemView.findViewById(R.id.Starting);
            resultTextView = itemView.findViewById(R.id.Result);
            lengthTextView = itemView.findViewById(R.id.Length);
            team1ImageView = itemView.findViewById(R.id.Team1Image);
            team2ImageView = itemView.findViewById(R.id.Team2Image);
        }
    }

    // Create a ViewHolder instance for each item in the RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for an individual fixture item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fixture, parent, false);
        return new ViewHolder(itemView); // Return a new ViewHolder associated with the inflated view
    }

    // Bind data to views within each ViewHolder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fixture fixture = fixtures.get(position); // Get fixture at the given position
        String fixtureName = fixture.getName(); // Get fixture name

        // Split the fixture name by " vs " to get team names
        String[] teams = fixtureName.split(" vs ");

        if (teams.length == 2) { // Ensure there are two teams
            String team1Name = teams[0].trim(); // Get the first team name
            String team2Name = teams[1].trim(); // Get the second team name

            // Set fixture details to there respective TextViews
            holder.nameTextView.setText("Fixture: " + fixtureName);
            holder.startingTextView.setText("Date & Time: " + fixture.getStarting());

            // Check if the result is null, if so set it to "To Be Played" accordingly
            String result = fixture.getResult();
            if (result == null) {
                holder.resultTextView.setText("Fixture Result: To Be Played");
            } else {
                holder.resultTextView.setText("Fixture Result: " + result);
            }

            holder.lengthTextView.setText("Fixture Time: " + fixture.getLength());

            // Set team images based on team names
            int team1ImageResource = getTeamImageResource(team1Name);
            int team2ImageResource = getTeamImageResource(team2Name);

            // Set team1 image
            holder.team1ImageView.setImageResource(team1ImageResource);

            // Set team2 image
            holder.team2ImageView.setImageResource(team2ImageResource);
        }
    }

    // Helper method to get the image for the given team name
    private int getTeamImageResource(String teamName) {
        if (teamImageMap.containsKey(teamName)) {
            return teamImageMap.get(teamName); // Return the image associated with the team name
        }
        return R.drawable.default_image; // If team name not found then return default image
    }

    // Return the number of fixtures in the list
    // if null, return 0
    @Override
    public int getItemCount() {
        return fixtures != null ? fixtures.size() : 0;
    }

    // Set fixtures in the adapter
    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures.clear(); // Clear existing fixtures
        if (fixtures != null) {
            this.fixtures.addAll(fixtures); // Add new fixtures if not null
        }
        notifyDataSetChanged(); // Notify RecyclerView of data changes
    }
}
