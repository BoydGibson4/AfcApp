//package application.afc;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import java.util.List;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class FixtureActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fixture); // Make sure you have a corresponding XML layout file
//
//        // Retrofit initialization
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.sportmonks.com/v2.0/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        int teamId = 21787;
//        String apiKey = "OwY1IKzYBcJ74ylh6mvxIQfKDasYZiJ2SFIZUC10s9jH4w4dbE96bHuO3NUs"; // Replace with your actual API key
//
//        // Make the API request
//        Call<List<Fixture>> call = apiService.getClubFixtures(teamId, apiKey);
//        call.enqueue(new Callback<List<Fixture>>() {
//            @Override
//            public void onResponse(Call<List<Fixture>> call, Response<List<Fixture>> response) {
//                if (response.isSuccessful()) {
//                    List<Fixture> fixtures = response.body();
//                    // Handle and display fixtures in your app
//                } else {
//                    Log.e("API Error", "Error response: " + response.message());
//                    // Handle the error
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Fixture>> call, Throwable t) {
//                Log.e("API Error", "API call failed: " + t.getMessage());
//                // Handle network or other errors
//            }
//        });
//    }
//}