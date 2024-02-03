package application.afc.FixtureApi;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //api call to get fixtures for a specific club within a date range
    @GET("football/fixtures/between/2023-08-05/2024-07-01/273")
    Call<FixtureFragment.FixtureResponse> getClubFixtures(
            @Query("api_token") String apiKey // Passes api key as a query parameter

    );
}