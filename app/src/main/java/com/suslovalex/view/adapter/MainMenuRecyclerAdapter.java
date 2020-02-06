package com.suslovalex.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.suslovalex.customcollections.R;
import com.suslovalex.model.MainMenuItem;
import com.suslovalex.view.dialog.InformationDialog;
import com.suslovalex.view.activity.MainMenuActivity;

import java.util.List;

/**
 * MainMenuRecyclerAdapter extends RecyclerView.Adapter
 */

public class MainMenuRecyclerAdapter extends RecyclerView.Adapter<MainMenuRecyclerAdapter.ViewHolder> {

    private List<MainMenuItem> mMainMenuItems;

    public MainMenuRecyclerAdapter(List<MainMenuItem> mainMenuItems) {
        mMainMenuItems = mainMenuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_element_recycle_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MainMenuItem mainMenuItem = mMainMenuItems.get(position);
        holder.title.setText(mainMenuItem.getTitle());
        holder.description.setText(mainMenuItem.getDescription());
        holder.image.setImageResource(mainMenuItem.getImageId());

        holder.mConstrainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new InformationDialog(mainMenuItem, position);
                MainMenuActivity activity = (MainMenuActivity) v.getContext();

                fragment.show(activity.getSupportFragmentManager(),"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMainMenuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private ImageView image;
        private ConstraintLayout mConstrainLayout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);
            mConstrainLayout = itemView.findViewById(R.id.constrainLayout);
        }
    }
}
