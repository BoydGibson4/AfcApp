// Package containing code related to the PredictApi
package application.afc.PredictApi;

// Importing necessary Room library classes for defining a database
import androidx.room.Database;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.room.Room;

// Database class for handling PredictionDataEntity objects
@Database(entities = {PredictionDataEntity.class}, version = 1, exportSchema = false)
public abstract class PredictionDatabase extends RoomDatabase {

    // Abstract method to retrieve the PredictionDao interface for database operations
    public abstract PredictionDao predictionDao();

    private static PredictionDatabase instance; // Singleton instance of the database

    // Method to get an instance of PredictionDatabase
    public static synchronized PredictionDatabase getInstance(Context context) {
        if (instance == null) {
            // If the database instance is null, create a new instance using Rooms databaseBuilder
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PredictionDatabase.class, "prediction_database")
                    .fallbackToDestructiveMigration() // Handle schema changes by dropping and recreating tables
                    .build();
        }
        return instance; // Return the singleton instance of the database
    }
}
