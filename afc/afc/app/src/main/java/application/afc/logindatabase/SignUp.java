// Package containing code related to the login database
package application.afc.logindatabase;

// Importing necessary Android classes
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import application.afc.R;

// Activity for user registration
public class SignUp extends AppCompatActivity {

    EditText userId, password, name; // Input fields for user details
    Button register; // Button to register a user
    TextView textViewLogin; // Text view to navigate to the login page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Setting the layout for this activity

        // Initializing UI elements
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        register = findViewById(R.id.register);
        textViewLogin = findViewById(R.id.loginText);

        // Click listener for the registration button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a UserEntity instance from the input fields
                UserEntity userEntity = new UserEntity();
                userEntity.setUserID(userId.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setName(name.getText().toString());

                // Validating user input before registration
                if (validateInput(userEntity)){
                    // Performing database operation on a separate thread
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Registering the user in the database
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Showing a success message using Toast
                                    Toast.makeText(getApplicationContext(), "User registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();
                } else {
                    // Notifying user to fill all required fields
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Click listener to navigate to the login page
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent); // Starting the login activity
                finish(); // Finishing the current activity
            }
        });
    }

    // Method to validate user input
    private Boolean validateInput(UserEntity userEntity){
        return !userEntity.getName().isEmpty() &&
                !userEntity.getPassword().isEmpty() &&
                !userEntity.getUserID().isEmpty();
    }
}
