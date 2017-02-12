/*
 * Copyright (C) 2013-2017 RICOH Co.,LTD
 * All rights reserved
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;


public class EditTextEx extends FrameLayout {
    private boolean editable = false;
    private boolean editableOnTouch;
    private String text;
    private EditText editText;
    private TextView textView;
    private OnClickListener listener;
    private boolean error;

    private static EditTextEx preView;

    public EditTextEx(Context context) {
        super(context);
    }

    public EditTextEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextEx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setEditableOnTouch(true);
        setPadding(0, 0, 0, 0);
        editText = new EditText(context, attrs);
        textView = new TextView(context, attrs);
        LogC.i(editText.getText().toString());
        editText.setVisibility(INVISIBLE);
        textView.setVisibility(VISIBLE);
        setClickable(true);
        setEnabled(true);
        setFocusable(true);
        this.listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preView != null && v != preView) {
                    preView.setEditable(false);
                }
                preView = (EditTextEx) v;
                if (isEditable()) {
                    HideKeyboard(EditTextEx.this);
                    setEditable(false);
                } else {
                    setEditable(true);
                    ShowKeyboard(EditTextEx.this);
                }
            }
        };
        setOnFocusChangeListener(null);
        setOnClickListener(null);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(true);
        addView(textView);
        addView(editText);
    }

    public void setEditable(boolean b) {
        textView.setText(editText.getText().toString());
        if (b) {
            textView.setVisibility(INVISIBLE);
            editText.setVisibility(VISIBLE);
            editText.requestFocus();
        } else {
            textView.setVisibility(VISIBLE);
            editText.setVisibility(GONE);
        }
        editable = b;
    }

    public boolean isEditable() {
        return editable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (listener != null)
                listener.onClick(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setEditableOnTouch(boolean b) {
        this.editableOnTouch = b;
    }

    public String getText() {
        if (editText.getVisibility() == VISIBLE) {
            return editText.getText().toString();
        } else {
            return textView.getText().toString();
        }
    }

    public void addTextChangedListener(TextWatcher listener) {
        editText.addTextChangedListener(listener);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {

    }

    public void setError(boolean error) {
        if (error) {
//            textView.setBackgroundColor(Color.RED);
            textView.setTextColor(Color.RED);
        } else {
//            textView.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public void setOnFocusChangeListener(final OnFocusChangeListener l) {
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ShowKeyboard(v);
//                    editText.setSelection(editText.length()==0?0:editText.length());
                } else {

                }
                if (l != null)
                    l.onFocusChange(v, hasFocus);
            }
        });
    }

    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    public static void HideKeyboard(Activity context) {
        View view = context.getCurrentFocus();
        if (view == null) {
            return;
        }
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
    }

    //显示虚拟键盘
    public static void ShowKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

    }

    public void setText(CharSequence text) {
        textView.setText(text);
        editText.setText(text);
    }

    public void setSelection(int selection) {
        int length = editText.getText().toString().length();
        if (selection >= length) {
            editText.setSelection(length == 0 ? 0 : length - 1);
        } else {
            editText.setSelection(selection);
        }
    }

    public int getSelection() {
        return editText.getSelectionEnd();
    }
}
