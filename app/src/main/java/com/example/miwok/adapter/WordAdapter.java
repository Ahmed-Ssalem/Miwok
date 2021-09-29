package com.example.miwok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwok.R;
import com.example.miwok.pojo.Word;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.wordViewHolder> {

    List<Word> word = new ArrayList<Word>();
    private int mColorResourceId;
    private OnItemClickListener mOnItemClickListener;


    public WordAdapter(List<Word> word, int mColorResourceId, OnItemClickListener onItemClickListener) {
        this.word = word;
        this.mColorResourceId = mColorResourceId;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public wordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new wordViewHolder(holder, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull wordViewHolder holder, int position) {

        Context context = holder.textContainer.getContext();
        Word w = word.get(position);

        if (w.hasImage()) {
            holder.pic.setImageResource(w.getImage());
        } else {
            holder.pic.setVisibility(View.GONE);
        }

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(context, mColorResourceId);

        // Set the background color of the text container View
        holder.textContainer.setBackgroundColor(color);

        holder.miwokTranslation.setText(w.getmMiwokTranslation());
        holder.defaultTranslation.setText(w.getmDefaultTranslation());

    }

    @Override
    public int getItemCount() {
        if (word == null)
            return 0;
        else
            return word.size();
    }

    public class wordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pic;
        TextView miwokTranslation;
        TextView defaultTranslation;
        View textContainer;
        OnItemClickListener onItemClickListener;

        public wordViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            pic = itemView.findViewById(R.id.image);
            miwokTranslation = itemView.findViewById(R.id.miwok_text_view);
            defaultTranslation = itemView.findViewById(R.id.default_text_view);
            textContainer = itemView.findViewById(R.id.text_container);

            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}







