package com.example.mysmartschool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mysmartschool.Guru.LoadingFragment;
import com.example.mysmartschool.Guru.MainActivity;
import com.example.mysmartschool.Guru.api.ApiClientKt;
import com.example.mysmartschool.Guru.api.ApiRequest;
import com.example.mysmartschool.Guru.api.EventService;
import com.example.mysmartschool.Guru.dataclass.LoginRespone;
import com.example.mysmartschool.Guru.dataclass.Respone;
import com.example.mysmartschool.Murid.Main3Activity;
import com.example.mysmartschool.admin.Admin.Entity_Admin_Admin;
import com.example.mysmartschool.admin.Guru.Entity_Guru_Admin;
import com.example.mysmartschool.admin.Main2Activity;
import com.example.mysmartschool.admin.Murid.Entity_Murid_Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Call<Respone<LoginRespone>> call;
    EditText edtEmail, edtPassword;
    Button btnLogin;


    private FirebaseAuth mAuth;
    private DatabaseReference refMurid,refAdmin,refGuru;
    String users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        initComponent();

        initListener();



    }

    private void initComponent() {
        edtEmail = findViewById(R.id.loginEmail);
        edtPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.loginLogin);
    }

    private void initListener() {
        btnLogin.setOnClickListener(view -> callLoginEvent());
    }

    private void callLoginEvent() {

        String emails = edtEmail.getText().toString()+"@gmail.com";
        String pass = edtPassword.getText().toString();



        mAuth.signInWithEmailAndPassword(emails, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("", "signInWithEmail:success");
//                    FirebaseUser user = mAuth.getCurrentUser();
                   users = mAuth.getCurrentUser().getUid();
                    refMurid = FirebaseDatabase.getInstance().getReference("User").child("Murid").child(users);
                    refAdmin = FirebaseDatabase.getInstance().getReference("User").child("Admin").child(users);
                    refGuru = FirebaseDatabase.getInstance().getReference("User").child("Guru").child(users);

                    String admin = refAdmin.toString();
//                    String guru = refGuru.toString();

                    if (edtEmail.getText().length()== 12){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        Toast.makeText(LoginActivity.this, "guru", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if(edtEmail.getText().length() == 5){
                        startActivity(new Intent(LoginActivity.this,Main2Activity.class));
                        Toast.makeText(LoginActivity.this, "admin", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if(edtEmail.getText().length() == 10){
                        startActivity(new Intent(LoginActivity.this,Main3Activity.class));
                        Toast.makeText(LoginActivity.this, "murid", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("", "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });


//        EventService request = ApiRequest.getClient(ApiClientKt.loginClient()).create(EventService.class);
//        call = request.getToken(
//                RequestBody.create(edtEmail.getText().toString(), MediaType.parse("text/plain")),
//                RequestBody.create(edtPassword.getText().toString(), MediaType.parse("text/plain")),
//                RequestBody.create("true", MediaType.parse("text/plain"))
//        );
//        LoadingFragment loadingFragment = new LoadingFragment();
//        getSupportFragmentManager().beginTransaction().add(loadingFragment, "").commit();
//        call.enqueue(new Callback<Respone<LoginRespone>>() {
//            @Override
//            public void onResponse(Call<Respone<LoginRespone>> call, Response<Respone<LoginRespone>> response) {
//                if (response.body() == null) {
//                    Toast.makeText(LoginActivity.this, "Bermasalah saat login!", Toast.LENGTH_LONG).show();
//                    call.cancel();
//                    return;
//                }
//
//                Respone<LoginRespone> body = response.body();
//                if (body.getCode() == 200) {
//                    Config.API_TOKEN = body.getData().getToken();
//                    Config.API_TOKEN_TYPE = body.getData().getTokenType();
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                } else if (body.getCode() == 401) {
//                    Toast.makeText(LoginActivity.this, "Periksa ulang email/password Anda!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
//                }
//                getSupportFragmentManager().beginTransaction().remove(loadingFragment);
//            }
//
//            @Override
//            public void onFailure(Call<Respone<LoginRespone>> call, Throwable t) {
//                getSupportFragmentManager().beginTransaction().remove(loadingFragment);
//            }
//        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        call.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();

            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                Log.d("anjing",user.getEmail().length()+"");
                if (user.getEmail().length() == 15){
                    startActivity(new Intent(LoginActivity.this,Main2Activity.class));
                    Toast.makeText(LoginActivity.this, "admin", Toast.LENGTH_SHORT).show();
                    finish();
                } else if(user.getEmail().length() == 20){
                    startActivity(new Intent(LoginActivity.this,Main3Activity.class));
                    Toast.makeText(LoginActivity.this, "murid", Toast.LENGTH_SHORT).show();
                    finish();
                } else if(user.getEmail().length() == 22){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    Toast.makeText(LoginActivity.this, "guru", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
    }
}
