package com.example.mysmartschool;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysmartschool.api.ApiClientKt;
import com.example.mysmartschool.api.ApiRequest;
import com.example.mysmartschool.api.EventService;
import com.example.mysmartschool.dataclass.LoginRespone;
import com.example.mysmartschool.dataclass.Respone;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Call<Respone<LoginRespone>> call;
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
    }

    private void initListener() {
        btnLogin.setOnClickListener(view -> callLoginEvent());
    }

    private void callLoginEvent() {
        EventService request = ApiRequest.getClient(ApiClientKt.loginClient()).create(EventService.class);
        call = request.getToken(
                RequestBody.create(edtEmail.getText().toString(), MediaType.parse("text/plain")),
                RequestBody.create(edtPassword.getText().toString(), MediaType.parse("text/plain")),
                RequestBody.create("true", MediaType.parse("text/plain"))
        );
        LoadingFragment loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(loadingFragment, "").commit();
        call.enqueue(new Callback<Respone<LoginRespone>>() {
            @Override
            public void onResponse(Call<Respone<LoginRespone>> call, Response<Respone<LoginRespone>> response) {
                if (response.body() == null) {
                    Toast.makeText(LoginActivity.this, "Bermasalah saat login!", Toast.LENGTH_LONG).show();
                    call.cancel();
                    return;
                }

                Respone<LoginRespone> body = response.body();
                if (body.getCode() == 200) {
                    Config.API_TOKEN = body.getData().getToken();
                    Config.API_TOKEN_TYPE = body.getData().getTokenType();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else if (body.getCode() == 401) {
                    Toast.makeText(LoginActivity.this, "Periksa ulang email/password Anda!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                }
                getSupportFragmentManager().beginTransaction().remove(loadingFragment);
            }

            @Override
            public void onFailure(Call<Respone<LoginRespone>> call, Throwable t) {
                getSupportFragmentManager().beginTransaction().remove(loadingFragment);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        call.cancel();
    }
}
