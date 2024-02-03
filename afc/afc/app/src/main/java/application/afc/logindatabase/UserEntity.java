// This package contains code related to the login database
package application.afc.logindatabase;

// Import necessary Room annotations for database setup
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// An entity class representing a User in the database
@Entity(tableName = "users") // Specifies the table name as "users"
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    Integer id; // Unique identifier for the user

    @ColumnInfo(name = "userId")
    String userID; // User's ID

    @ColumnInfo(name = "password")
    String password; // User's password

    @ColumnInfo(name = "name")
    String name; // User's name

    // Getter method for retrieving the user's ID
    public Integer getId() {
        return id;
    }

    // Setter method for setting the user's ID
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter method for retrieving the user's ID
    public String getUserID() {
        return userID;
    }

    // Setter method for setting the user's ID
    public void setUserID(String userID) {
        this.userID = userID;
    }

    // Getter method for retrieving the user's password
    public String getPassword() {
        return password;
    }

    // Setter method for setting the user's password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter method for retrieving the user's name
    public String getName() {
        return name;
    }

    // Setter method for setting the user's name
    public void setName(String name) {
        this.name = name;
    }
}
