package com.example.mysmartschool.Guru.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mysmartschool.Guru.LoadingFragment;
import com.example.mysmartschool.R;
import com.example.mysmartschool.Guru.dataclass.SiswaProfileRespone;
import com.example.mysmartschool.Guru.api.ApiClient;
import com.example.mysmartschool.Guru.api.ApiRequest;
import com.example.mysmartschool.Guru.api.EventService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {


    private TextView tvNama, tvEmail, tvNamaLengkap, tvNis, tvKelas;
    private LoadingFragment loadingFragment;
    private Call<SiswaProfileRespone> call;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    private void initComponent(View view) {
        tvNama = view.findViewById(R.id.profileNama);
        tvEmail = view.findViewById(R.id.profileEmail);
        tvNamaLengkap = view.findViewById(R.id.profileNamaLengkap);
        tvNis = view.findViewById(R.id.profileNis);
        tvKelas = view.findViewById(R.id.profileKelas);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initComponent(view);

        callProfileEvent();
    }


    public void requestData(){

    }


    private void callProfileEvent() {
//        loadingFragment = new LoadingFragment();
//        EventService request = ApiRequest.getClient(new ApiClient().resourceClient()).create(EventService.class);
//        call = request.getProfile();
//        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_frame, loadingFragment, "").commit();
//        call.enqueue(new Callback<SiswaProfileRespone>() {
//            @Override
//            public void onResponse(Call<SiswaProfileRespone> call, Response<SiswaProfileRespone> response) {
//                if (response.body() == null) {
//                    Toast.makeText(ProfileFragment.this.getActivity(), "Aplikasi sedang bermasalah", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                SiswaProfileRespone body = response.body();
//                tvNama.setText(body.getNama());
//                tvEmail.setText(body.getEmail());
//                tvNamaLengkap.setText(body.getNamaLengkap());
//                tvNis.setText(body.getNis());
//                tvKelas.setText(body.getKelas());
//
//                getActivity().getSupportFragmentManager().beginTransaction().remove(loadingFragment).commit();
//            }
//
//            @Override
//            public void onFailure(Call<SiswaProfileRespone> call, Throwable t) {
//                getActivity().getSupportFragmentManager().beginTransaction().remove(loadingFragment).commit();
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        call.cancel();
    }
}
