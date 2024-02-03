// Package containing code related to the TableApi
package application.afc.TableApi;

// Imports required for Android functionality
import android.content.pm.ActivityInfo;
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
import application.afc.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import com.google.gson.Gson;

// Fragment class to display table data using RecyclerView
public class TableFragment extends Fragment {

    private RecyclerView recyclerView;
    private TableAdapter tableAdapter;

    // Inner class representing the response object for the table data
    public class TableResponse {
        private List<Table> data;

        // Getter method for the list of tables
        public List<Table> getTables() {
            return data;
        }

        // Setter method for the list of tables if needed
        public void setTables(List<Table> tables) {
            this.data = tables;
        }
    }

    // Inner class representing error response from the API
    public class ErrorResponse {
        private String message;

        public String getMessage() {
            return message;
        }
    }

    // Fragment lifecycle method for creating the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_table, container, false);

        // Locking the screen orientation to portrait
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        // Initializing RecyclerView and its layout manager and adapter
        recyclerView = rootView.findViewById(R.id.tablesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tableAdapter = new TableAdapter(new ArrayList<>());
        recyclerView.setAdapter(tableAdapter);

        // Initializing Retrofit with logging interceptor for API calls
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.sportmonks.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Creating an instance of TableApiService using Retrofit
        TableApiService apiService = retrofit.create(TableApiService.class);

        //api key
        String apiKey = "OwY1IKzYBcJ74ylh6mvxIQfKDasYZiJ2SFIZUC10s9jH4w4dbE96bHuO3NUs";

        // Making an asynchronous call to fetch table data
        Call<TableResponse> call = apiService.getClubTable(apiKey);
        call.enqueue(new Callback<TableResponse>() {
            @Override
            public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {
                if (response.isSuccessful()) {
                    handleSuccessfulResponse(response.body());
                } else {
                    handleErrorResponse(response);
                }
            }

            @Override
            public void onFailure(Call<TableResponse> call, Throwable t) {
                Log.e("API Error", "API call failed: " + t.getMessage());
                // Handle network or other errors
            }
        });

        return rootView;
    }

    // Fragment lifecycle method for destroying the view
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Resetting the screen orientation when the fragment is destroyed or detached
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }

    // Method to handle a successful api response
    private void handleSuccessfulResponse(TableResponse tableResponse) {
        // Printing the JSON response body for debugging
        Log.d("API Response", "Response body: " + new Gson().toJson(tableResponse));

        // Checking if the response body is not empty
        if (tableResponse != null && tableResponse.getTables() != null && !tableResponse.getTables().isEmpty()) {
            // Updating the adapter with the table data
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    List<Table> tables = tableResponse.getTables();
                    tableAdapter.updateTables(tables);
                    Log.d("TableAdapter", "Adapter count: " + tableAdapter.getItemCount());
                }
            });
        } else {
            Log.w("TableFragment", "Received empty list of tables from the API");
            // Handling empty list case, showing a message, or performing necessary actions
        }
    }

    // Method to handle an error response from the API
    private void handleErrorResponse(Response<TableResponse> response) {
        // Handling the error response
        try {
            ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
            String errorMessage = errorResponse.getMessage();
            Log.e("API Error", "Error response: " + errorMessage);
            // Handling the error
        } catch (IOException e) {
            Log.e("API Error", "Error parsing error response: " + e.getMessage());
        }
    }
}
