// Package containing code for the login database
package application.afc.logindatabase;

// Importing necessary Room library classes for DAO (Data Access Object)
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

// Interface defining database operations for UserEntity objects
@Dao
public interface UserDao {

    // Method to insert(register) a user into the database
    @Insert
    void registerUser(UserEntity userEntity);

    // Method to perform a login query based on userID and password
    @Query("SELECT * from users where userID=(:userId) and password=(:password)")
    UserEntity login(String userId, String password);
}