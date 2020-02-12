package com.example.mysmartschool.Guru.SmartVideo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mysmartschool.R;

import java.util.List;

public class Adapter_smartvideo extends RecyclerView.Adapter<Adapter_smartvideo.ViewHolder> {

    String juduls,mapels;

    private List<Entity_smartvideo> smartvideoList;
    private Context mContext;


    //    ini seperti constructor
    public Adapter_smartvideo(Context mContext, List<Entity_smartvideo> smartvideoList){
        this.mContext = mContext;
        this.smartvideoList = smartvideoList;
    }

    //    ini class viewHolder dibuat karena pada CLASS Adapter waktu ngeExtends pada belakangnya ada ViewHOlder
//    dan pada class Viewholder ini harus extends ke recyclerview
    public static class ViewHolder extends RecyclerView.ViewHolder{


        //        ini buat variabel biasa
        public TextView videoJudul,VideoMapel;
//        LinearLayout lr;
        Button btnChange;


        //        ini untuk manggil ID dari XML dan ditampung pada Variabel yang tadi dibuat
        public ViewHolder(View v){
            super(v);
            videoJudul = (TextView)v.findViewById(R.id.video_judul);
            VideoMapel = (TextView)v.findViewById(R.id.video_mapel);
//            btnChange = (Button)v.findViewById(R.id.btn_change);

//            lr = (LinearLayout)v.findViewById(R.id.linearlayout1);
        }
    }

    //    onCREATEview berfungsi untuk memanggil dan menSET LIST ITEM RECYCLERVIEW (XML)
    @Override
    public Adapter_smartvideo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //ini memanggil list item REcycleerView(XML) dan ditampung pada variabel v
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_smartvideo,parent,
                false);

        //ini untuk menset VIewHolder dengan item list di atas
        Adapter_smartvideo.ViewHolder vh = new Adapter_smartvideo.ViewHolder(v);

        return vh;

    }

    //onBINDviewHolder berfungsi untuk MenSET Entity dan akan ditampilkan pada XML

    @Override
    public void onBindViewHolder(Adapter_smartvideo.ViewHolder holder, final int position) {
//          ini untuk inisialisasi entity
        Entity_smartvideo video = smartvideoList.get(position);

//             ini untuk mengambil data dari ENtity dan nanti akan diambil dan di set/tampilkan oleh MainACtivity(RecyclerActivity)
        holder.videoJudul.setText(String.valueOf(video.getJudul()));
        holder.VideoMapel.setText(String.valueOf(video.getMapel()));



//            ini untuk ditekan pindah activity
//        holder.lr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Bundle bundle = new Bundle();
////                    ini ngirim data untuk di ambil pada activity lain
//                bundle.putString("judul",infoList.get(position).getJudul());
//                bundle.putString("tgl",infoList.get(position).getDate());
//                bundle.putString("dari",infoList.get(position).getPengirim());
//                bundle.putString("rinci",infoList.get(position).getAbout());
//                //                    ini pindah activity
//                Intent inten = new Intent(mContext, InfoActivity.class);
//                inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                inten.putExtras(bundle);
//
////                    ini untuk menjalankan inten
//                mContext.startActivity(inten);
//            }
//        });

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
        return smartvideoList.size();
    }

}
