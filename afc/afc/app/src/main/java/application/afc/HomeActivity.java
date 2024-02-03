package application.afc;

// Import necessary libraries
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

// Class representing the HomeActivity
public class HomeActivity extends AppCompatActivity {
    ImageButton home; // Declare ImageButton for home

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check device orientation to determine which layout to load
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.load); // Load landscape layout
        } else {
            setContentView(R.layout.activity_home); // Load portrait layout
        }

        // Find the home ImageButton in the layout
        home = findViewById(R.id.home);

        // Set click listener for the home ImageButton to replace fragment
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new home());
            }
        });

        // Listener for keyboard visibility changes
        final View activityRootView = findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = activityRootView.getRootView().getHeight() - r.height();


                int threshold = 300;

                // Show or hide the home ImageButton based on keyboard visibility
                if (heightDiff > threshold) {
                    home.setVisibility(View.GONE);
                } else {
                    home.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // Method to replace the current fragment with a new one
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}

