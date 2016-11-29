package com.util.dialog.dialogutilapplication;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DialogUtil {

    private AlertDialog alertDialog;
    private LinearLayout layout;
    public SureInterfance sureListener;
    public ICancelListener cancelListener;


    /**
     *
     * @param context
     * @param l 是否显示取消按钮
     * @param r 是否显示确定按钮
     * @param AlertTitle 标题
     * @param AlertContent 内容
     * @param LContent  左边按钮内容
     * @param RContent  右边按钮内容
     * @param listener  左边按钮确定监听
     * @param iCancelListener 右边按钮确定监听
     */
    public DialogUtil(Context context, boolean l, boolean r,String AlertTitle, String AlertContent, String LContent, String RContent,
                      SureInterfance listener,
                      ICancelListener iCancelListener) {

        layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_util, null);
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCanceledOnTouchOutside(false);
        sureListener = listener;
        cancelListener = iCancelListener;
        TextView txtContent = (TextView) layout.findViewById(R.id.txt_content);
        TextView txt_title = (TextView) layout.findViewById(R.id.txt_title);
        txtContent.setText(AlertContent);
        txt_title.setText(AlertTitle);
        Button btnCancle = (Button) layout.findViewById(R.id.btn_cancle);
        btnCancle.setText(LContent);
        btnCancle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (alertDialog.isShowing())

                    cancelListener.cancelTodo();
                alertDialog.dismiss();
            }
        });

        Button btnSure = (Button) layout.findViewById(R.id.btn_sure);
        btnSure.setText(RContent);
        btnSure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                sureListener.sureTodo();
                alertDialog.dismiss();
            }
        });

        if (!l) {
            btnCancle.setVisibility(View.GONE);
        }
        if (!r) {
            btnSure.setVisibility(View.GONE);
        }

    }

    public interface SureInterfance {
        void sureTodo();
    }

    public interface ICancelListener {
        void cancelTodo();
    }


    public void showCustomDialog() {
        alertDialog.show();

        alertDialog.setContentView(layout);
        Window window = alertDialog.getWindow();
        LayoutParams layoutParams = window.getAttributes();
        WindowManager manager = window.getWindowManager();
        Display display = manager.getDefaultDisplay();
        //设置dialog的宽度为 屏幕宽度的 90%
        layoutParams.width = (int) (display.getWidth() * 0.9);
        window.setAttributes((android.view.WindowManager.LayoutParams) layoutParams);
    }

    public void dismiss() {
        alertDialog.dismiss();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        alertDialog.setOnDismissListener(listener);
    }


}
