package com.suslovalex.recyclerview.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.suslovalex.customcollections.R;
import com.suslovalex.recyclerview.Model.MainMenuItem;


public class InformationDialog extends DialogFragment {

    private MainMenuItem mMainMenuItem;
    private TextView mTitleDialogText;
    private TextView mDescriptionDialogText;
    private ImageView mDialogImage;
    private TextView mSummaryClose;
    private int mPosition;

    public InformationDialog(MainMenuItem mainMenuItem, int position) {
        mMainMenuItem = mainMenuItem;
        mPosition = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_window_menu, container, false);
        bindViewById(view);
        setValuesForViews();
        mSummaryClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        return view;
    }
    private void bindViewById (View v){
        mTitleDialogText = v.findViewById(R.id.textView_dialog_title);
        mDescriptionDialogText = v.findViewById(R.id.textView_dialog_description);
        mDialogImage = v.findViewById(R.id.dialog_image);
        mSummaryClose = v.findViewById(R.id.textView_dialog_summary);
    }
    private void setValuesForViews(){
        mTitleDialogText.setText(mMainMenuItem.getTitle());
        mDescriptionDialogText.setText(mMainMenuItem.getDescription());
        mDialogImage.setImageResource(mMainMenuItem.getImageId());
        mSummaryClose.setText(mPosition+1+"");
    }
}
