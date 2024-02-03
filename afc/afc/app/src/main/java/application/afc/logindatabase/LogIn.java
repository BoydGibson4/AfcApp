// Package containing code related to the login database
package application.afc.logindatabase;

// Importing necessary Android classes
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import application.afc.R;

// Activity for user login
public class LogIn extends AppCompatActivity {

    EditText userId, password; // Input fields for user ID and password
    Button buttonLogIn; // Button for login
    TextView textViewSignUp; // Text view to navigate to the sign-up page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Setting the layout for this activity
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Locking orientation to portrait

        // Initializing UI elements
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        buttonLogIn = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.signUpText);

        // Click listener for the login button
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();

                // Validating user input before attempting login
                if(userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Performing a query to check user credentials in the database
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Attempting login by querying the database
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            if (userEntity == null){
                                // Notifying user of an invalid login attempt
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                // Redirecting to the home activity upon successful login
                                startActivity(new Intent(LogIn.this, application.afc.HomeActivity.class));
                            }
                        }
                    }).start();
                }
            }
        });

        // Click listener to navigate to the sign-up page
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent); // Starting the sign-up activity
                finish(); // Finishing the current activity
            }
        });
    }
}
