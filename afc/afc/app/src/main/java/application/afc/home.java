// Package containing code related to the home screen
package application.afc;

// Imports for necessary functionalities
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.res.Configuration;
import application.afc.FixtureApi.FixtureFragment;
import application.afc.PredictApi.PredictionFragment;
import application.afc.TableApi.TableFragment;

// Class representing the home screen
public class home extends Fragment {

    // Declare ImageButtons for different functionalities
    ImageButton predict;
    ImageButton fixtures;
    ImageButton table;

    ImageButton NewsButton;
    ImageButton WomenButton;
    ImageButton StadiumButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        // Check the device orientation to determine the layout to use
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Use the landscape layout
            view = inflater.inflate(R.layout.temp, container, false);
            // Handle landscape-specific functionalities here
        } else {
            // Use the default portrait layout
            view = inflater.inflate(R.layout.fragment_home, container, false);
            // Handle portrait-specific functionalities here
        }

        // Initialize ImageButtons by finding their IDs in the layout
        predict = view.findViewById(R.id.predict);
        fixtures = view.findViewById(R.id.fixtures);
        table = view.findViewById(R.id.table);

        StadiumButton = view.findViewById(R.id.stadium); // Retrieve the Stadium button
        WomenButton = view.findViewById(R.id.women); // Retrieve the Women button
        NewsButton = view.findViewById(R.id.news); // Retrieve the News button

        // Check if ImageButtons are initialized
        if (NewsButton != null && WomenButton != null && StadiumButton != null) {

            // Set onClickListeners for different functionalities
            predict.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(new PredictionFragment());
                }
            });

            fixtures.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(new FixtureFragment());
                }
            });

            table.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(new TableFragment());
                }
            });

            // Handles clicks on NewsButton to open a specific URL in the browser
            NewsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonClicked", "News button clicked");
                    Intent myLink = new Intent(Intent.ACTION_VIEW);
                    myLink.setData(Uri.parse("https://www.afc.co.uk/news/"));
                    startActivity(myLink);
                }
            });

            // Handles clicks on WomenButton to open a specific URL in the browser
            WomenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonClicked", "Women button clicked");
                    Intent myLink = new Intent(Intent.ACTION_VIEW);
                    myLink.setData(Uri.parse("https://www.afc.co.uk/matches-teams/womens-team/"));
                    startActivity(myLink);
                }
            });

            // Handles clicks on StadiumButton to open a specific location in Maps
            StadiumButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonClicked", "Stadium button clicked");
                    openMaps();
                }
            });
        } else {
            Log.e("Buttons", "One or both buttons not found in the layout");
        }

        return view;
    }

    // Method to replace the current fragment with a new one
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null); // Add transaction to back stack
        fragmentTransaction.commit();
    }

    // Method to open a specific website in the browser
    private void openWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Sorry, you don't have a stable internet connection.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to open a specific location in Maps
    private void openMaps() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:57.159196,-2.088405"));
        Intent chooser = Intent.createChooser(intent, "Launch Maps");
        startActivity(chooser);
    }
}
