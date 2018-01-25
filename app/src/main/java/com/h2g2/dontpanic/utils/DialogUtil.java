package com.h2g2.dontpanic.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.h2g2.dontpanic.R;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/25
 */
public class DialogUtil {

    public static final int CRATE_MILISECONDS = 1200;
    public static boolean canAnimate = true;
    public static int VELOCITY_DIALOG = 1200;
    public static int VELOCITY_DIALOG_1 = 500;
    public static int VELOCITY_DIALOG_2 = 700;
    public static int VELOCITY_ENTRY_HOST = 700;
    public static int VELOCITY_DISMISS_HOST = 500;

    public static int VELOCITY_BELOW_ANIMATION_FADE_IN_OUT, VELOCITY_CONTAINER_ANIMATION_IN_OUT = 500;
    private static Runnable runnableShake;
    private static Handler handler;

    public static void showGenericAlert(
            final Activity activity,
            String title,
            String message,
            String btnTextConfirm,
            String btnTextCancel,
            final PostCallback postCallback) {

        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(btnTextCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(btnTextConfirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //finishAndRemoveTask ();
                    }
                }).create().show();
    }

    public static void showGenericDialog(final Activity activity, String title, String message,
                                         String btnTextConfirm, String btnTextCancel,
                                         final PostCallback postCallback) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.DialogTheme);
            LayoutInflater inflater = activity.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_general_dialog, null);
            builder.setView(view);
            final AlertDialog _dialog = builder.create();
            builder.setCancelable(true);

            TextView textViewDialogTitle = (TextView) view.findViewById(R.id.textViewDialogTitle);
            TextView textViewDialogMessage = (TextView) view.findViewById(R.id.textViewDialogMessage);
            Button buttonConfirm = (Button) view.findViewById(R.id.buttonConfirm);
            Button buttonCancel = (Button) view.findViewById(R.id.buttonCancel);

            final View _belowView = view.findViewById(R.id.belowView);
            final View _viewContainer = view.findViewById(R.id.layoutContainer);

            if (message != null && !message.isEmpty()) {
                textViewDialogMessage.setText(message);
            }

            if (title != null && !title.isEmpty()) {
                textViewDialogTitle.setText(title);
            }

            if (btnTextConfirm != null && !btnTextConfirm.isEmpty()) {
                buttonConfirm.setText(btnTextConfirm);
            }

            if (btnTextCancel != null && !btnTextCancel.isEmpty()) {
                buttonCancel.setText(btnTextCancel);
            }
            _dialog.show();

            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postCallback.callback();
                    if (_dialog.isShowing()) {
                        _dialog.dismiss();
                    }
                }
            });

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_dialog.isShowing()) {
                        _dialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showLayersDialogEffect(final View _belowView, final View _containerView, AlertDialog _dialog) {

        _belowView.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn).duration(VELOCITY_BELOW_ANIMATION_FADE_IN_OUT).playOn(_belowView); // show view below
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _containerView.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn).duration(VELOCITY_CONTAINER_ANIMATION_IN_OUT).playOn(_containerView); // show view container
            }
        }, VELOCITY_BELOW_ANIMATION_FADE_IN_OUT);
    }
}
