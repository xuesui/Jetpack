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

public class RecommandRecyclerAdapter extends RecyclerView.Adapter<RecommandRecyclerAdapter.ViewHolder> {
    private List<String> recommandContent=new ArrayList<>();
    private List<String> recommandAuthor=new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public RecommandRecyclerAdapter(List<String> recommandContent, List<String> recommandAuthor) {
        this.recommandContent = recommandContent;
        this.recommandAuthor = recommandAuthor;
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recommand_recycler,parent,false);
        return new RecommandRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.content.setText(recommandContent.get(position));
        holder.author.setText(recommandAuthor.get(position));
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
        return recommandAuthor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private TextView author;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=(TextView)itemView.findViewById(R.id.recommand_content);
            author=(TextView)itemView.findViewById(R.id.recommand_author);
        }
    }
}
