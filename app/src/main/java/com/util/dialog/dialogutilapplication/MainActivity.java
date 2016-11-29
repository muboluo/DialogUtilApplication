package com.util.dialog.dialogutilapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_dialog;
    DialogUtil mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_dialog = (Button) findViewById(R.id.button_dialog);
        button_dialog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (mDialog == null) {
            mDialog = new DialogUtil(this, true, true, "我是神龙", "请说出你的愿望", "更帅一点", "世界和平", new DialogUtil.SureInterfance() {
                @Override
                public void sureTodo() {

                    Toast.makeText(MainActivity.this,"太难了，换一个吧",Toast.LENGTH_LONG).show();

                }
            }, new DialogUtil.ICancelListener() {
                @Override
                public void cancelTodo() {

                    Toast.makeText(MainActivity.this,"你的愿望已经实现了",Toast.LENGTH_LONG).show();

                }
            });
        }

        mDialog.showCustomDialog();

    }
}
