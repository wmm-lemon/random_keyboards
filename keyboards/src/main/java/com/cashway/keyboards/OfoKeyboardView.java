package com.cashway.keyboards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wangmm
 * @date 2022/10/18
 * @description
 */
public class OfoKeyboardView extends KeyboardView {

    private Context context;
    private Keyboard keyboard;

    public OfoKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        Log.i(">>>>>", "构造函数被调用");
    }

    /**
     * 重新画一些按键
     */
    @Override
    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        keyboard = this.getKeyboard();
        List<Keyboard.Key> keys = null;
        if (keyboard != null) {
            keys = keyboard.getKeys();
        }

        if (keys != null) {
            for (Keyboard.Key key : keys) {
                // 数字键盘的处理
                if (key.codes[0] == -4) {
                    drawKeyBackground(R.drawable.bg_keyboardview_yes, canvas, key);
                }else {
                    drawKeyBackground(R.drawable.bg_keyboardview, canvas, key);
                }
                drawText(canvas, key);
            }
        }
    }

    private void drawKeyBackground(int drawableId, Canvas canvas, Keyboard.Key key) {
        Drawable npd = context.getResources().getDrawable(
                drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        npd.setBounds(key.x, key.y, key.x + key.width, key.y
                + key.height);
        npd.draw(canvas);
    }

    private void drawText(Canvas canvas, Keyboard.Key key) {
        Rect bounds = new Rect();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);

        paint.setAntiAlias(true);

        paint.setColor(Color.BLACK);
        if (key.label != null) {
//            String label = key.label.toString();

            Field field;

            if (key.codes[0] < 0) {
                int labelTextSize = 0;
                try {
                    field = KeyboardView.class.getDeclaredField("mLabelTextSize");
                    field.setAccessible(true);
//                    labelTextSize = (int) field.get(this);
                    labelTextSize = (int)dp2px((int) field.get(this));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                paint.setTextSize(labelTextSize);
                paint.setTypeface(Typeface.DEFAULT);
            } else {
                int keyTextSize = 0;
                try {
                    field = KeyboardView.class.getDeclaredField("mLabelTextSize");
                    field.setAccessible(true);
//                    keyTextSize = (int) field.get(this);
                    keyTextSize = (int)dp2px((int) field.get(this));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                paint.setTextSize(keyTextSize);
                paint.setTypeface(Typeface.DEFAULT);
            }

            paint.getTextBounds(key.label.toString(), 0, key.label.toString()
                    .length(), bounds);
            canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                    (key.y + key.height / 2) + bounds.height() / 2, paint);
        } else if (key.icon != null) {
            key.icon.setBounds(key.x + (key.width - key.icon.getIntrinsicWidth()) / 2, key.y + (key.height - key.icon.getIntrinsicHeight()) / 2,
                    key.x + (key.width - key.icon.getIntrinsicWidth()) / 2 + key.icon.getIntrinsicWidth(), key.y + (key.height - key.icon.getIntrinsicHeight()) / 2 + key.icon.getIntrinsicHeight());
            key.icon.draw(canvas);
        }

    }

    private float dp2px(int index){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,index, Resources.getSystem().getDisplayMetrics());
    }
}
