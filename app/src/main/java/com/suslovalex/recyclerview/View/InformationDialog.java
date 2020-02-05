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


    public InformationDialog(MainMenuItem mainMenuItem) {
        mMainMenuItem = mainMenuItem;
    }

    private MainMenuItem mMainMenuItem;
    private TextView mTitleDialogText;
    private TextView mDescriptionDialogText;
    private ImageView mDialogImage;
    private TextView mSummaryClose;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_window_menu, container, false);
        mTitleDialogText = view.findViewById(R.id.textView_dialog_title);
        mTitleDialogText.setText(mMainMenuItem.getTitle());
        mDescriptionDialogText = view.findViewById(R.id.textView_dialog_description);
        mDescriptionDialogText.setText(mMainMenuItem.getDescription());
        mDialogImage = view.findViewById(R.id.dialog_image);
        mDialogImage.setImageResource(mMainMenuItem.getImageId());
        mSummaryClose = view.findViewById(R.id.textView_dialog_summary);
        mSummaryClose.setText("hello");
        mSummaryClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        return view;
    }
}
