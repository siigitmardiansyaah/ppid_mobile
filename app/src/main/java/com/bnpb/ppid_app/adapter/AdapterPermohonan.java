package com.bnpb.ppid_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bnpb.ppid_app.Detail_Pengajuan;
import com.bnpb.ppid_app.R;
import com.bnpb.ppid_app.model.PermohonanData;

import java.util.List;

public class AdapterPermohonan extends RecyclerView.Adapter<AdapterPermohonan.HolderData> {
    private Context ctx;
    private List<PermohonanData> listData;
    String status,keterangan,pengajuan_id;

    public AdapterPermohonan(Context ctx, List<PermohonanData> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        PermohonanData dm = listData.get(position);
        holder.id_pengajuan.setText(dm.getPengajuan_id());
        if(dm.getStatus_pengajuan().equals("1")) {
            status = "Proses";
            keterangan = dm.getWaktu() + " - " + "Pengajuan Informasimu telah diterima sistem";
            holder.status_p.setText(status);
            holder.detail_p.setText(keterangan);
            holder.gambar.setImageResource(R.drawable.nanya);
        }else if(dm.getStatus_pengajuan().equals("2")) {
            status = "Diterima Admin Direktorat";
            keterangan = dm.getSend_bidang() + " - " + "Pengajuan sudah diterima Admin Direktorat terkait";
            holder.status_p.setText(status);
            holder.detail_p.setText(keterangan);
            holder.gambar.setImageResource(R.drawable.in);
        }else if(dm.getStatus_pengajuan().equals("3")) {
            status = "Proses Menjawab";
            keterangan = dm.getDiterima() + " - " +"Admin Direktorat Sedang Memproses Jawaban";
            holder.status_p.setText(status);
            holder.detail_p.setText(keterangan);
            holder.gambar.setImageResource(R.drawable.out);
        }else {
            status = "Jawaban Diterima";
            keterangan = dm.getSend_pemohon() + " - "+ "Jawaban Telah diterima";
            holder.status_p.setText(status);
            holder.detail_p.setText(keterangan);
            holder.gambar.setImageResource(R.drawable.jawab);
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView status_p, detail_p,id_pengajuan;
        ImageView gambar;
        CardView detail_pengajuan;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            status_p = itemView.findViewById(R.id.textViewSub1Title);
            detail_p = itemView.findViewById(R.id.sub_detail);
            gambar = itemView.findViewById(R.id.status_image);
            id_pengajuan = itemView.findViewById(R.id.pengajuan_id);
            detail_pengajuan = itemView.findViewById(R.id.btn_view);
            detail_pengajuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub

                    pengajuan_id = id_pengajuan.getText().toString();
                    Intent intent = new Intent(arg0.getContext(), Detail_Pengajuan.class);
                    intent.putExtra("pengajuan_id",pengajuan_id);
                    ctx.startActivity(intent);
                }
            });
        }


    }




}


