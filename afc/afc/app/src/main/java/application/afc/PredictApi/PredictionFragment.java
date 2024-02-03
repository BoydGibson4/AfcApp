package application.afc.PredictApi;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Arrays;
import java.util.List;
import application.afc.R;
import android.app.AlertDialog;
import android.content.DialogInterface;

// Fragment responsible for predictions input
public class PredictionFragment extends Fragment {

    // Declaration of UI elements
    private Spinner dropdownSpinnerLeague;
    private Spinner dropdownSpinnerScup;
    private Spinner dropdownSpinnerLcup;
    private Spinner dropdownSpinnerEurope;

    private EditText textInputLeague;
    private EditText textInputGoals;
    private EditText textInputPots;
    private EditText textInputYpots;
    private EditText textInputRelegated;

    private ProgressBar progressBar;

    // Overridden method for fragment view creation
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_predict, container, false);

        // Lock the screen orientation to portrait
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        // Initializing UI elements
        dropdownSpinnerLeague = rootView.findViewById(R.id.dropdownSpinnerLeague);
        dropdownSpinnerScup = rootView.findViewById(R.id.dropdownSpinnerScup);
        dropdownSpinnerLcup = rootView.findViewById(R.id.dropdownSpinnerLcup);
        dropdownSpinnerEurope = rootView.findViewById(R.id.dropdownSpinnerEurope);

        textInputLeague = rootView.findViewById(R.id.textInputLeague);
        textInputGoals = rootView.findViewById(R.id.textInputGoals);
        textInputPots = rootView.findViewById(R.id.textInputPots);
        textInputYpots = rootView.findViewById(R.id.textInputYpots);
        textInputRelegated = rootView.findViewById(R.id.textInputRelegated);
        progressBar = rootView.findViewById(R.id.progressBar);

        // Set up visibility of components as needed
        setupVisibility();

        // Populate spinner options
        setupSpinnerOptions();

        // Spinner item selection listeners
        dropdownSpinnerLeague.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Perform actions based on the selected item in dropdownSpinnerLeague
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle no selection in dropdownSpinnerLeague
            }
        });

        // Button click listener
        ImageButton saveButton = rootView.findViewById(R.id.saveButton); // Replace saveButton with the actual button Id
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonOnClick();
            }
        });

        return rootView;
    }

    // Method for handling onDestroyView
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Restore screen orientation to unspecified when view is destroyed
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }

    // Method to set up visibility of components as needed
    private void setupVisibility() {
        // TODO: Set up visibility of components as needed
    }

    // Method to populate spinner options
    private void setupSpinnerOptions() {
        try {
            // Setting up options for the League spinner
            List<String> leagueOptions = Arrays.asList("1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th");
            ArrayAdapter<String> leagueAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, leagueOptions);
            leagueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdownSpinnerLeague.setAdapter(leagueAdapter);

            // Setting up options for the Scup spinner
            List<String> scupOptions = Arrays.asList("4th Round", "5th Round", "Quarter Final", "Semi Final", "Final", "Winner");
            ArrayAdapter<String> scupAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, scupOptions);
            scupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdownSpinnerScup.setAdapter(scupAdapter);

            // Setting up options for the Lcup spinner
            List<String> lcupOptions = Arrays.asList("Group Stage", "2nd Round", "Quarter Final", "Semi Final", "Final", "Winner");
            ArrayAdapter<String> lcupAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, lcupOptions);
            lcupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdownSpinnerLcup.setAdapter(lcupAdapter);

            // Setting up options for the Europe spinner
            List<String> europeOptions = Arrays.asList("Qualifiers", "Group Stage", "Ro 16", "Quarter Final", "Semi Final", "Final", "Winner");
            ArrayAdapter<String> europeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, europeOptions);
            europeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdownSpinnerEurope.setAdapter(europeAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to replace fragments in the frame layout
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // Method to handle save button click
    private void saveButtonOnClick() {
        // Retrieving values from spinners and EditText fields
        String leaguePrediction = dropdownSpinnerLeague.getSelectedItem().toString();
        String scupPrediction = dropdownSpinnerScup.getSelectedItem().toString();
        String lcupPrediction = dropdownSpinnerLcup.getSelectedItem().toString();
        String europePrediction = dropdownSpinnerEurope.getSelectedItem().toString();

        String leagueWinnerPrediction = textInputLeague.getText().toString();
        String goalsPrediction= textInputGoals.getText().toString();
        String potsPrediction = textInputPots.getText().toString();
        String ypotsPrediction = textInputYpots.getText().toString();
        String relegatedPrediction = textInputRelegated.getText().toString();
        // Similarly, get other EditText values
        // Validation checks
        if (leagueWinnerPrediction.isEmpty() || goalsPrediction.isEmpty() || potsPrediction.isEmpty() ||
                ypotsPrediction.isEmpty() || relegatedPrediction.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a string with user input details
        String userInputDetails = "League Prediction: " + leaguePrediction + "\n" +
                "SCUP Prediction: " + scupPrediction + "\n" +
                "LCUP Prediction: " + lcupPrediction + "\n" +
                "Europe Prediction: " + europePrediction + "\n" +
                "League Winner Prediction: " + leagueWinnerPrediction + "\n" +
                "Goals Prediction: " + goalsPrediction + "\n" +
                "Pots Prediction: " + potsPrediction + "\n" +
                "Ypots Prediction: " + ypotsPrediction + "\n" +
                "Relegated Prediction: " + relegatedPrediction;

        // Show the user input details in a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("User Input Details");
        builder.setMessage(userInputDetails);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Save data to Room database
                PredictionDataEntity predictionData = new PredictionDataEntity();
                predictionData.setLeaguePrediction(leaguePrediction);
                predictionData.setScupPrediction(scupPrediction);
                predictionData.setLcupPrediction(lcupPrediction);
                predictionData.setEuropePrediction(europePrediction);
                predictionData.setLeagueWinner(leagueWinnerPrediction);
                predictionData.setTopGoalScorer(goalsPrediction);
                predictionData.setPlayerOfTheSeason(potsPrediction);
                predictionData.setYoungPlayerOfTheSeason(ypotsPrediction);
                predictionData.setRelegatedTeam(relegatedPrediction);

                saveToDatabase(predictionData);
            }
        });
        builder.setNegativeButton("Cancel", null); // You can handle cancel action if needed

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to save data to the Room database
    private void saveToDatabase(PredictionDataEntity predictionDataEntity) {
        PredictionDatabase database = PredictionDatabase.getInstance(requireContext());
        PredictionDao dao = database.predictionDao();

        new Thread(() -> {
            dao.insertPredictionData(predictionDataEntity);
        }).start();

        Toast.makeText(requireContext(), "Data saved to database", Toast.LENGTH_SHORT).show();
    }
}
