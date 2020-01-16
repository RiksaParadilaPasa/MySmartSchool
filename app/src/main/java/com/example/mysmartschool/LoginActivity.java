package com.example.mysmartschool;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysmartschool.dataclass.LoginRespone;
import com.example.mysmartschool.dataclass.Respone;
import com.example.mysmartschool.retrofit.ApiClientKt;
import com.example.mysmartschool.retrofit.ApiRequest;
import com.example.mysmartschool.retrofit.EventService;
import com.google.firebase.auth.FirebaseAuth;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText edtEmail, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();

        initListener();
    }

    private void initComponent() {
        edtEmail = findViewById(R.id.loginEmail);
        edtPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.loginLogin);
//        mAuth = FirebaseAuth.getInstance();
    }

    private void initListener() {
        btnLogin.setOnClickListener(view -> callLoginEvent());
    }

    private void callLoginEvent() {
        EventService request = ApiRequest.getClient(ApiClientKt.loginClient()).create(EventService.class);
        Call<Respone<LoginRespone>> call = request.getToken(
                RequestBody.create(edtEmail.getText().toString(),MediaType.parse("text/plain")),
                RequestBody.create(edtPassword.getText().toString(),MediaType.parse("text/plain")),
                RequestBody.create("true",MediaType.parse("text/plain"))
        );
        call.enqueue(new Callback<Respone<LoginRespone>>() {
            @Override
            public void onResponse(Call<Respone<LoginRespone>> call, Response<Respone<LoginRespone>> response) {
                if (response.body() == null) {
                    return;
                }

            }

            @Override
            public void onFailure(Call<Respone<LoginRespone>> call, Throwable t) {
                Log.d("login", call.request().url().toString());
            }
        });
    }
}
