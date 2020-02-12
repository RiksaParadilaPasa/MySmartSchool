package com.example.mysmartschool.Guru.Myclass.MyTeacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.R;
import com.example.mysmartschool.Guru.SmartInfo.InfoActivity;

import java.util.List;

public class DataAdapter_myTeacher extends RecyclerView.Adapter<DataAdapter_myTeacher.ViewHolder> {

    String name;


    private List<Entity_myteacher> myteacherList;
    private Context mContext;



    public DataAdapter_myTeacher(Context mContext, List<Entity_myteacher> myteacherList){
        this.mContext = mContext;
        this.myteacherList = myteacherList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{


        //        ini buat variabel biasa
        public TextView nameGuru;
        LinearLayout lr;
//        Button btnChange;


        //        ini untuk manggil ID dari XML dan ditampung pada Variabel yang tadi dibuat
        public ViewHolder(View v){
            super(v);
            nameGuru = (TextView)v.findViewById(R.id.tv_name_recycler);

//            btnChange = (Button)v.findViewById(R.id.btn_change);
//
            lr = (LinearLayout)v.findViewById(R.id.linear1_container);
        }
    }

    //    onCREATEview berfungsi untuk memanggil dan menSET LIST ITEM RECYCLERVIEW (XML)
    @Override
    public DataAdapter_myTeacher.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //ini memanggil list item REcycleerView(XML) dan ditampung pada variabel v
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_myteacher,
                parent,
                false);

        //ini untuk menset VIewHolder dengan item list di atas
        ViewHolder vh = new ViewHolder(v);

        return vh;

    }

    //onBINDviewHolder berfungsi untuk MenSET Entity dan akan ditampilkan pada XML

    @Override
    public void onBindViewHolder(DataAdapter_myTeacher.ViewHolder holder, final int position) {
//          ini untuk inisialisasi entity
        Entity_myteacher myteacher = myteacherList.get(position);

//             ini untuk mengambil data dari ENtity dan nanti akan diambil dan di set/tampilkan oleh MainACtivity(RecyclerActivity)
        holder.nameGuru.setText(String.valueOf(myteacher.getNama_guru()));




//            ini untuk ditekan pindah activity
        holder.lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
//                    ini ngirim data untuk di ambil pada activity lain
                bundle.putString("nip",myteacherList.get(position).getNip_guru());
                bundle.putString("nama",myteacherList.get(position).getNama_guru());
                bundle.putString("nomorhp",myteacherList.get(position).getNomorHp_guru());
                //                    ini pindah activity
                Intent inten = new Intent(mContext, MyTeacher.class);
                inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inten.putExtras(bundle);

//                    ini untuk menjalankan inten
                mContext.startActivity(inten);
            }
        });
//
//        holder.btnChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Entitiy_smartinfo info = infoList.get(position);
//                String id = info.getIdInfo();
//                kirims = info.getPengirim();
//                juduls = info.getJudul();
//                tanggals = info.getDate();
//                abouts = info.getAbout();
//                showDialogWhatToDo(id);
//            }
//        });




    }


    //    ini untuk inisialisasi ukuran dari list/arraylist/recyclerview
    @Override
    public int getItemCount() {
        return myteacherList.size();
    }

//    private void showDialogWhatToDo(final String idInfo) {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        View dialogView = inflater.inflate(R.layout.layout_update_or_delete,null);
//        dialogBuilder.setView(dialogView);
//
//        Button btnDoUpdate = (Button) dialogView.findViewById(R.id.doUpdate);
//        Button btnDoDelete = (Button) dialogView.findViewById(R.id.doDelete);
//
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        btnDoUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDialogUpdateInfo(idInfo);
//                alertDialog.dismiss();
//            }
//        });
//
//        btnDoDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteInfo(idInfo);
//                alertDialog.dismiss();
//            }
//        });
//
//    }
    //
//

//    private void deleteInfo(String idInfo) {
//        DatabaseReference dInfo = FirebaseDatabase.getInstance().getReference("Info").child(idInfo);
//        dInfo.removeValue();
//        Toast.makeText(mContext, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show();
//
//    }


//    private void showDialogUpdateInfo(final String idInfo) {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        final View dialogView = inflater.inflate(R.layout.layout_edit_info,null);
//        dialogBuilder.setView(dialogView);
//
//        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.editInfo);
//
//        dialogBuilder.setTitle("Update Kelas ");
//        final AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//        final EditText etKirim = (EditText)dialogView.findViewById(R.id.et_kirim);
//        etKirim.setText(kirims);
//        final EditText etJudul = (EditText)dialogView.findViewById(R.id.et_judul);
//        etJudul.setText(juduls);
//        final EditText etDate = (EditText)dialogView.findViewById(R.id.et_date);
//        etDate.setText(tanggals);
//        final EditText etAbout = (EditText)dialogView.findViewById(R.id.et_about);
//        etAbout.setText(abouts);
//
//        buttonUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String pengirim = etKirim.getText().toString();
//                String penjudul = etJudul.getText().toString();
//                String tanggal = etDate.getText().toString();
//                String perinci = etAbout.getText().toString();
//
//                updateInfo(idInfo , pengirim , penjudul,tanggal,perinci);
//                alertDialog.dismiss();
//            }
//        });
//    }

//    private boolean updateInfo(String idInfo, String pengirim, String penjudul, String tanggal, String perinci){
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Info").child(idInfo);
//        Entitiy_smartinfo info = new Entitiy_smartinfo(idInfo , pengirim, penjudul,tanggal,perinci);
//        databaseReference.setValue(info);
//
//        Toast.makeText(mContext, "Behasil Update Data", Toast.LENGTH_SHORT).show();
//
//        return true;
//    }

}
