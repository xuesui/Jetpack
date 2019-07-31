package com.example.jetpack.utils.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpack.R;

import java.util.ArrayList;
import java.util.List;

public class AndroidRecyclerAdapter extends RecyclerView.Adapter<AndroidRecyclerAdapter.ViewHolder> {
    private List<String> androidContent=new ArrayList<>();
    private List<String> androidAuthor=new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public AndroidRecyclerAdapter(List<String> androidContent, List<String> androidAuthor) {
        this.androidContent = androidContent;
        this.androidAuthor = androidAuthor;
    }

    public interface OnItemClickListener{
        void onClick( int position,View view);
        void onLongClick( int position,View view);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.android_recycler,parent,false);
        return new AndroidRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.content.setText(androidContent.get(position));
        holder.author.setText(androidAuthor.get(position));
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position,v);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position,v);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return androidAuthor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private TextView author;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=(TextView)itemView.findViewById(R.id.android_content);
            author=(TextView)itemView.findViewById(R.id.android_author);
        }
    }
}
