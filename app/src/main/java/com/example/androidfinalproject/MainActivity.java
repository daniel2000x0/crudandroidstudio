package com.example.androidfinalproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScrollView taskScrollView;
    private ArrayAdapter<String> taskAdapter;
    private PassDBHelper dbHelper;
    private List<Data> passData;
    FloatingActionButton fabAddPass;
    RecyclerView recyclerView;
    private PassAdapter adapter;
 private   String   datologico;
   private String emailaog;

    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passData=new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        fabAddPass = findViewById(R.id.fab_add_pass);
        dbHelper = new PassDBHelper(this);
        loadTasksFromSQLite(passData);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PassAdapter(this, passData);
        recyclerView.setAdapter(adapter);
        tv1 = (TextView)findViewById(R.id.textView1);
        datologico= getIntent().getStringExtra("datalog");
         emailaog= getIntent().getStringExtra("emaillog");
        tv1.setText("Hola " + datologico);
        Toast.makeText(getApplicationContext(), "Informacion"+ datologico+"+"+emailaog, Toast.LENGTH_SHORT).show();


        fabAddPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add_password.class);
                intent.putExtra("datalog", datologico);
                intent.putExtra("emaillog", emailaog);
                startActivity(intent);
            }
        });

    }


    public void loadTasksFromSQLite(List<Data> data) {
        // Assuming you have a SQLiteOpenHelper instance named dbHelper
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+PassContract.PassEntry.TABLE_NAME, null);
        Toast.makeText(this, "loadindg "+ cursor, Toast.LENGTH_SHORT).show();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(PassContract.PassEntry.COLUMN_USER));
            @SuppressLint("Range") String passwords = cursor.getString(cursor.getColumnIndex(PassContract.PassEntry.COLUMN_PASSWORD));
            @SuppressLint("Range") String webapps = cursor.getString(cursor.getColumnIndex(PassContract.PassEntry.COLUMN_WEBAPP));



            data.add(new Data(username,passwords,webapps ));
            //Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }


    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
    public class Data{
        String password;
        String user;
     ;
        String webapp;

        Data(String password, String user, String webapp) {
            this.password = password;
            this.user = user;

            this.webapp = webapp;

        }


        public String getPassword() {
            return password;
        }

        public String getUser() {
            return user;
        }



        public String getWebapp() {
            return webapp;
        }


    }
}