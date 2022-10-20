package com.cashway.randomkeystool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cashway.keyboards.OfoKeyboard;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_numberplate);
        final OfoKeyboard keyboard = new OfoKeyboard(MainActivity.this);//获取到keyboard对象

        editText.clearFocus();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("Edit", "焦点状态：" + hasFocus);
                if (hasFocus) {
                    Log.i("Edit", "获取焦点");
                    keyboard.attachTo(editText,true);//eiditext绑定keyboard，true表示随机数字
                } else {
                    Log.i("Edit", "失去焦点");
                    keyboard.hideKeyBoard();
                }
            }
        });


        //确定按钮
        keyboard.setOnOkClick(new OfoKeyboard.OnOkClick() {
            @Override
            public void onOkClick() {
                Log.i(">>>>>>","点击了确定");
                Toast.makeText(MainActivity.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        //隐藏键盘按钮
        keyboard.setOnCancelClick(new OfoKeyboard.OnCancelClcik() {
            @Override
            public void onCancelClick() {
                Toast.makeText(MainActivity.this,"隐藏键盘",Toast.LENGTH_SHORT).show();
            }
        });
    }
}