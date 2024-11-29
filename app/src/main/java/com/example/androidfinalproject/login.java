package com.example.androidfinalproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.ListenerRegistration;
import java.util.Objects;

public class login extends AppCompatActivity {
    //busqueda    email
    private FirebaseFirestore db;
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //busqueda    email
        db = FirebaseFirestore.getInstance();
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()){

                } else {
                    checkUser();
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);

                intent.putExtra("datalog", loginUsername.getText().toString());
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername(){
        String val = loginUsername.getText().toString();
        if (val.isEmpty()){
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        loginUsername.setError(null);

                        //Pass the data using intent

                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                        Intent intent = new Intent(login.this, MainActivity.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("datalog", loginUsername.getText().toString());

                        intent.putExtra("emaillog", emailFromDB);

                        startActivity(intent);
                    } else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void getUserByEmail(String name) {
        db.collection("users") // Nombre de tu colección
                .whereEqualTo("name", name) // Consulta por campo 'email'
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            for (DocumentSnapshot document : querySnapshot) {
                                // Aquí puedes acceder a los datos del usuario
                                String userName = document.getString("name");
                                email= document.getString("email");
                                // Haz lo que necesites con los datos
                                System.out.println("User Name: " + userName);
                                System.out.println("User Name: " + email);
                            }
                        } else {
                            // Si no se encuentra el usuario
                            System.out.println("No user found with that email");
                        }
                    } else {
                        // Manejo de errores
                        System.out.println("Error getting documents: " + task.getException());
                    }
                });
    }





}