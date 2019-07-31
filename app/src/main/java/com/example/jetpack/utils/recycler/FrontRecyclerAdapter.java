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
import java.util.zip.Inflater;

public class FrontRecyclerAdapter extends RecyclerView.Adapter<FrontRecyclerAdapter.ViewHolder> {
    private List<String> frontContent=new ArrayList<>();
    private List<String> frontAuthor=new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public FrontRecyclerAdapter(List<String> content, List<String> author) {
        this.frontContent = content;
        this.frontAuthor = author;
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
    public FrontRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.front_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrontRecyclerAdapter.ViewHolder holder, final int position) {
        holder.content.setText(frontContent.get(position));
        holder.author.setText(frontAuthor.get(position));
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
        return frontContent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView content;
        private TextView author;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=(TextView)itemView.findViewById(R.id.front_content);
            author=(TextView)itemView.findViewById(R.id.front_author);
        }
    }
}
