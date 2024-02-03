// Package containing code for the login database
package application.afc.logindatabase;

// Importing necessary Android and Room library classes
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Database class for handling UserEntity objects
@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String dbName = "user"; // Database name
    private static UserDatabase userDatabase; // Singleton instance of the database

    // Method to get an instance of the UserDatabase
    public static synchronized UserDatabase getUserDatabase(Context context) {
        if (userDatabase == null) {
            // If the database instance is null, create a new instance using Room's databaseBuilder
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, dbName)
                    .fallbackToDestructiveMigration() // Handle schema changes by dropping and recreating tables
                    .build();
        }
        return userDatabase; // Return the singleton instance of the database
    }

    // Abstract method to retrieve the UserDao interface for database operations
    public abstract UserDao userDao();
}
