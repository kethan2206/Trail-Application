package com.example.testing2;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.databinding.DataBindingUtil;

import com.example.testing2.databinding.LayoutPromotionBinding;

import java.io.File;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Executor;

public class PromotionDisplayManager {

    // Holders
    private Context mCx;

    // UI
    private LayoutPromotionBinding mDataBinding;
    private AlertDialog mPromotionDialog;

    // TTs
    private TextToSpeech mTextToSpeech;
    private boolean mTextToSpeechReady = false;

    public PromotionDisplayManager(Context cx) {
        this.mCx = cx;
        mDataBinding = DataBindingUtil.inflate(LayoutInflater.from(cx), R.layout.layout_promotion,
                null, false);
        mTextToSpeech = new TextToSpeech(cx, status -> mTextToSpeechReady = true);
        mTextToSpeech.setLanguage(Locale.getDefault());
    }

    public void showAlertDialogPromotion(String title, String message, String imageFilePath,
                                         @Nullable String offerText) {
        mDataBinding.title.setText(title);
        mDataBinding.message.setText(message);
        if (!TextUtils.isEmpty(offerText)) {
            mDataBinding.offerText.setVisibility(View.VISIBLE);
            mDataBinding.offerText.setText(offerText);
        } else {
            mDataBinding.offerText.setVisibility(View.GONE);
        }
        mDataBinding.offerText.setText(offerText);
        File imageFile = new File(imageFilePath);
        if (imageFile.exists()) {
            mDataBinding.image.setVisibility(View.VISIBLE);
            mDataBinding.image.setImageURI(Uri.fromFile(imageFile));
        } else {
            mDataBinding.image.setVisibility(View.GONE);
        }


        if (mPromotionDialog != null && mPromotionDialog.isShowing()) {
            mPromotionDialog.dismiss();
        }

        if (mDataBinding.getRoot().getParent() != null) {
            ViewGroup view = (ViewGroup) mDataBinding.getRoot().getParent();
            view.removeView(mDataBinding.getRoot());
        }

        mPromotionDialog = new AlertDialog
                .Builder(new ContextThemeWrapper(mCx, R.style.myDialog))
                .setView(mDataBinding.getRoot())
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create();

        mPromotionDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        mPromotionDialog.show();
    }

    public void playTtsPromotion(String textToSpeak) {
        if (mTextToSpeechReady) {
            mTextToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_ADD,
                    null, UUID.randomUUID().toString());
        } else {
            new Handler(Looper.myLooper()).postDelayed(() -> playTtsPromotion(textToSpeak), 1000);
        }
    }
}
