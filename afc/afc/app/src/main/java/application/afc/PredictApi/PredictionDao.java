// Package containing code related to the PredictApi
package application.afc.PredictApi;

// Importing necessary Room library classes for DAO (Data Access Object)
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

// DAO interface defining database operations for PredictionDataEntity objects
@Dao
public interface PredictionDao {

    // Method to insert PredictionDataEntity into the database
    @Insert
    void insertPredictionData(PredictionDataEntity predictionDataEntity);

    // Method to get all PredictionDataEntity from the database
    @Query("SELECT * FROM prediction_data_table")
    List<PredictionDataEntity> getAllPredictionData();
}
