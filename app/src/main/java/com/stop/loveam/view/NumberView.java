package com.stop.loveam.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.stop.loveam.R;

public class NumberView extends LinearLayout {
    private EditText mEditValue;
    private int mCurrentValue;
    private OnValueChangeListener mOnValueChangeListener;


    public void setMCurrentValue(int mCurrentValue) {
        this.mCurrentValue = mCurrentValue;
    }

    public int getMCurrentValue() {
        return mCurrentValue;
    }

    public NumberView(Context context) {
        this(context, null, 0);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    //获取自定义属性

    //加载布局，定义控件以及设置监听
    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.number_op_item, this, false);
        this.addView(inflate);
        ImageView mIvAdd = inflate.findViewById(R.id.add);
        ImageView mIvMinus = inflate.findViewById(R.id.clear);
        mEditValue = inflate.findViewById(R.id.editText);
        mIvAdd.setOnClickListener(v -> {
            mCurrentValue += 1;
            updateText();
            if (mOnValueChangeListener != null) {
                mOnValueChangeListener.onValueChange(mCurrentValue);
            }
        });
        mIvMinus.setOnClickListener(v -> {
            mCurrentValue -= 1;
            if(mCurrentValue < 0){

            } else {
                updateText();
                if (mOnValueChangeListener != null) {
                    mOnValueChangeListener.onValueChange(mCurrentValue);
                }
            }
        });
    }

    private void updateText() {
        mEditValue.setText(String.valueOf(mCurrentValue));
    }

    public interface OnValueChangeListener {
        void onValueChange(int value);
    }

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        mOnValueChangeListener = onValueChangeListener;
    }
}