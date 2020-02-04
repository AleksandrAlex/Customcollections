package com.suslovalex.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.suslovalex.customcollections.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private List<Card> mCards;
    //private OnClickListener onclickListener;


    public RecyclerAdapter(List<Card> cards/*, OnClickListener onClickListener*/) {
        mCards = cards;
       // this.onclickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_recycle_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Card card = mCards.get(position);
        holder.title.setText(card.getTitle());
        holder.description.setText(card.getDescription());
        holder.image.setImageResource(card.getImageId());

        holder.mConstrainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCards.get(position);
                Toast toast = Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT);
                toast.show();


            }
        });
   //    holder.itemView.setOnClickListener(new View.OnClickListener() {
   //        @Override
   //        public void onClick(View v) {

   //        }
   //    });
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

        public TextView title;
        public TextView description;
        public ImageView image;
        public ConstraintLayout mConstrainLayout;

        //public OnClickListener onClickListener;

        public ViewHolder(@NonNull final View itemView/*, OnClickListener onClickListener*/) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);
            mConstrainLayout = itemView.findViewById(R.id.constrainLayout);

           // this.onClickListener = onClickListener;

      //      itemView.setOnClickListener(new View.OnClickListener() {
      //          @Override
      //          public void onClick(View v) {
//
      //          }
      //      });
        }

 //      @Override
 //      public void onClick(View v) {
 //          onClickListener.onClick(getAdapterPosition());

 //      }
   }
//  public interface OnClickListener {
//      void onClick(int position);
//  }
}
