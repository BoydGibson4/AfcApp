// Package containing code related to the TableApi
package application.afc.TableApi;

// Importing necessary Retrofit classes for defining API endpoints
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Retrofit service interface for fetching club table data
public interface TableApiService {

    // GET request to fetch club table standings for a specific season
    @GET("football/standings/seasons/21787")
    Call<TableFragment.TableResponse> getClubTable(
            @Query("api_token") String apiKey
    );
}
