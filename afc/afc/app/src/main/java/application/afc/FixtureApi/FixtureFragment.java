package application.afc.FixtureApi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import application.afc.R;
import application.afc.home;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import com.google.gson.Gson;
import android.widget.ImageButton;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FixtureFragment extends Fragment {

    private FixtureAdapter fixtureAdapter;

    // Class to handle response from API containing a list of fixtures
    public class FixtureResponse {
        private List<Fixture> data;

        public List<Fixture> getFixtures() {
            return data;
        }

        public void setFixtures(List<Fixture> fixtures) {
            this.data = fixtures;
        }
    }

    // Class to handle error response from API
    public class ErrorResponse {
        private String message;

        public String getMessage() {
            return message;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fixture, container, false);

        // Initialize RecyclerView to display fixtures
        RecyclerView recyclerView = rootView.findViewById(R.id.fixturesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the adapter for the RecyclerView
        fixtureAdapter = new FixtureAdapter(new ArrayList<>());
        recyclerView.setAdapter(fixtureAdapter);

        // Retrofit initialization with logging interceptor for API calls
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // Set up Retrofit for API communication
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.sportmonks.com/v3/") // Base URL for the SportMonks API
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Create an instance of the ApiService interface for making API calls
        ApiService apiService = retrofit.create(ApiService.class);

        int teamId = 273; // Aberdeen team ID
        String apiKey = "OwY1IKzYBcJ74ylh6mvxIQfKDasYZiJ2SFIZUC10s9jH4w4dbE96bHuO3NUs"; // Example API key

        // Create a call to the SportMonks API to get fixtures for a specific club
        Call<FixtureResponse> call = apiService.getClubFixtures(apiKey);

        // Asynchronously execute the API call
        call.enqueue(new Callback<FixtureResponse>() {
            @Override
            public void onResponse(Call<FixtureResponse> call, Response<FixtureResponse> response) {
                if (response.isSuccessful()) {
                    // Handle a successful API response
                    FixtureResponse fixtureResponse = response.body();
                    List<Fixture> fixtures = fixtureResponse.getFixtures();
                    updateFixtureAdapter(fixtures); // Update adapter with fetched fixtures
                } else {
                    handleErrorResponse(response); // Handle error response from the API
                }
            }

            @Override
            public void onFailure(Call<FixtureResponse> call, Throwable t) {
                // Handle API call failure
                handleFailure(t);
            }
        });

        // ImageButton initialization and OnClickListener to navigate back to HomeFragment
        ImageButton backButton = rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new home()); // Replace the current fragment with HomeFragment
            }
        });

        return rootView; // Return the prepared view
    }

    // Method to replace current fragment with another fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // Method to update the FixtureAdapter with the fetched fixtures on the UI thread
    private void updateFixtureAdapter(List<Fixture> fixtures) {
        requireActivity().runOnUiThread(() -> {
            fixtureAdapter.updateFixtures(fixtures);
            Log.d("FixtureAdapter", "Adapter count: " + fixtureAdapter.getItemCount());
        });
    }

    // Method to handle error response from the API
    private void handleErrorResponse(Response<FixtureResponse> response) {
        try {
            // Parse the error response JSON and display the error message
            ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
            String errorMessage = errorResponse.getMessage();
            Log.e("API Error", "Error response: " + errorMessage);
            showToast(errorMessage); // Show error message as a toast
        } catch (IOException e) {
            Log.e("API Error", "Error parsing error response: " + e.getMessage());
        }
    }

    // Method to handle failure in API call
    private void handleFailure(Throwable t) {
        Log.e("API Error", "API call failed: " + t.getMessage());
        showToast("API call failed. Please check your internet connection."); // Show a toast for API call failure
    }

    // Method to display a toast message
    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
