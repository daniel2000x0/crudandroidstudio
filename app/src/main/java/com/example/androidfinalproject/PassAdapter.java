package com.example.androidfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PassAdapter extends RecyclerView.Adapter<PassAdapter.TaskViewHolder>  {


    private Context mContext;
    private List<MainActivity.Data> mPassList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);

    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public PassAdapter(Context context, List<MainActivity.Data> passList) {
        mContext = context;
        mPassList = passList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_info, parent, false);
        return new TaskViewHolder(view, mListener);
    }



    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        MainActivity.Data currentItem = mPassList.get(position);

        holder.mTextViewName.setText(currentItem.getWebapp());
        holder.text_user.setText(currentItem.getUser());
        holder.mTextViewPassword.setText(currentItem.getPassword());


    }

    @Override
    public int getItemCount() {
        return mPassList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public TextView  mTextViewPassword;
        public TextView  text_user;


     public ImageView image_view;
        public Button mButtonEdit;
        public Button mButtonDelete;

        public TaskViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textapp_name);
            mTextViewPassword = itemView.findViewById(R.id.text_password);

            text_user = itemView.findViewById(R.id.text_user);
            image_view = itemView.findViewById(R.id.imagelogo);

            mButtonEdit = itemView.findViewById(R.id.btn_edit);
            mButtonDelete = itemView.findViewById(R.id.btn_delete);

            mButtonEdit.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(position);
                    }
                }
            });

            mButtonDelete.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });


        }
    }

}
