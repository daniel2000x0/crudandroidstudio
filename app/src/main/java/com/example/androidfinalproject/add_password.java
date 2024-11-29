package com.example.androidfinalproject;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.Objects;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.UUID;
public class add_password extends AppCompatActivity {
    ProgressDialog progressDialog;
    private EditText password;
    private EditText user;
    private EditText webapp;
    private ImageView logo;
    private PassDBHelper dbHelper;
    CharSequence text = "Hello toast!";
    int duration = Toast.LENGTH_SHORT;
    private static final int COD_SEL_IMAGE = 300;
    String idd;
    Button btn_cu_photo, btn_r_photo;
    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;
    StorageReference storageReference;
    String storage_path = "photos/*";
    String photo = "photo";
    private Uri image_url;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
  private String  datologico;

  private  String email;

    FirebaseDatabase database;
    DatabaseReference reference;

    LinearLayout linearLayout_image_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.pass_password);
        user = findViewById(R.id.pass_username);
        webapp = findViewById(R.id.pass_web);
        logo = findViewById(R.id.imageView3);
        initFirebase();
        btn_r_photo = findViewById(R.id.btn_remove_photo);
        datologico= getIntent().getStringExtra("datalog");

        email= getIntent().getStringExtra("emaillog");
        String id = getIntent().getStringExtra("id_logo");
        Button btn_cu_photoS = findViewById(R.id.btn_photo);

        Button addTaskButton = findViewById(R.id.button_add_task);
        mAuth = FirebaseAuth.getInstance();

        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("password");
       // if (id == null || id == ""){
        //    linearLayout_image_btn.setVisibility(View.GONE);
        //    addTaskButton.setOnClickListener(new View.OnClickListener() {
          //      @Override
          //      public void onClick(View v) {
           //         String users = user.getText().toString().trim();
                //    String passwords = password.getText().toString().trim();
            //        String webapps = webapp.getText().toString().trim();


              //      if(users.isEmpty() && passwords.isEmpty() && webapps.isEmpty()){
              //          Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
               //     }else{
                      //  postPass(passwords,users,webapps );

                //    }
              //  }
           // });
      //  }
        btn_cu_photoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(add_password.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
             //


            }
        });





        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emails=  email;
                String usersapp = datologico.toString().trim();
                String users = user.getText().toString().trim();
                String passwords = password.getText().toString().trim();
                String webapps = webapp.getText().toString().trim();


                if(users.isEmpty() && passwords.isEmpty() && webapps.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postPass(passwords,users,webapps,usersapp,emails);
                    // addTask();
                }
                Intent intent = new Intent(add_password.this, MainActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new PassDBHelper(this);
    }



            private void uploadPhoto() {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");

                startActivityForResult(i, COD_SEL_IMAGE);
            }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast toast = Toast.makeText(add_password.this, text, duration);
          toast.show();



                image_url = data.getData();

                logo.setImageURI(image_url);
               // subirPhoto(image_url);



    }

    private void subirPhoto(Uri image_url) {

        String rute_storage_photo = storage_path + "" + photo + "" + mAuth.getUid() +""+ idd;
        StorageReference reference = storageReference.child(rute_storage_photo);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful()){
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String download_uri = uri.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("logo", download_uri);
                            mfirestore.collection("logos").document(idd).update(map);
                            Toast.makeText(add_password.this, "Foto actualizada", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }


    private void postPass(String password, String user, String webapp,String usersapp, String  Emails) {
       /// CollectionReference usuariosRef = db.collection("passwords");

        if (Objects.requireNonNull(this.password.getText()).toString().isEmpty()) {
            Toast.makeText(add_password.this, "el campo  es  requerido", Toast.LENGTH_SHORT).show();
        } else if (Objects.requireNonNull(this.password.getText()).toString().isEmpty()) {
            Toast.makeText(add_password.this, "Fallo  en el  campo", Toast.LENGTH_SHORT).show();
        } else {
            Password pass = new Password();
            pass.setApp_user(datologico);
            pass.setKey(UUID.randomUUID().toString());
            pass.setUser(user);
            pass.setWebapp(webapp);
            pass.setEmail(Emails);
            pass.setPassword(password);
            databaseReference.child("passwords").child(pass.getKey()).setValue(pass);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();


            //saql lite
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(PassContract.PassEntry.COLUMN_USER, user);
            values.put(PassContract.PassEntry.COLUMN_PASSWORD, password);
            values.put(PassContract.PassEntry.COLUMN_WEBAPP, webapp);

            values.put(PassContract.PassEntry.COLUMN_COMPLETED, 0);
            long newRowId = db.insert(PassContract.PassEntry.TABLE_NAME, null, values);

            db.close();
            if (newRowId == -1) {
                Toast.makeText(this, "Failed to add task"+ newRowId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Task added successfully"+newRowId, Toast.LENGTH_SHORT).show();
            }
        }





    }





    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        /*firebaseDatabase.setPersistenceEnabled(true);*/ //using just without fragments
        databaseReference = firebaseDatabase.getReference();
    }




}