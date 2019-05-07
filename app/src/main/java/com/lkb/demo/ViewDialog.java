package com.lkb.demo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class ViewDialog {
    interface OnCustomDialogClickListener{
        void onTakePhotoClicked();
        void onChoosePhotoClicked();
        void onCancelDialog();
    }
    private Context mCtx;
    private OnCustomDialogClickListener listener;
    public void showDialog(WeakReference<MainActivity> activity) {
        mCtx = activity.get();
        setOnCustomDialogClickListener((OnCustomDialogClickListener) mCtx);
        final Dialog dialog = new Dialog(mCtx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.popup_dialog_layout);
        TextView textTakePhoto = dialog.findViewById(R.id.tvTakePhoto);
        TextView textChoosePhoto = dialog.findViewById(R.id.tvChoosePhoto);
        TextView textCancel = dialog.findViewById(R.id.tvCancel);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        textCancel.setOnClickListener(v -> {
            dialog.cancel();
            listener.onCancelDialog();
        });

        textTakePhoto.setOnClickListener(v -> {
          listener.onTakePhotoClicked();
            dialog.cancel();
        });
        textChoosePhoto.setOnClickListener(v -> {
            listener.onChoosePhotoClicked();
            dialog.cancel();
        });

    }

    public void setOnCustomDialogClickListener(OnCustomDialogClickListener listener){
        this.listener = listener;
    }
}
