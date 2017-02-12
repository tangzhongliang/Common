/*
 *  Copyright (C) 2016 RICOH Co.,LTD.
 *  All rights reserved.
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.RemoteViews.RemoteView;
import android.widget.TextView;

import java.lang.reflect.Field;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;

/**
 * Special text.
 */
@RemoteView
public class StrokeTextView extends TextView {

    public static final String TAG = "StrokeTextView";

    private boolean m_bDrawSideLine = true;
    TextPaint m_TextPaint;
    int mInnerColor;
    int mOuterColor;

    public StrokeTextView(Context context) {
        super(context);
        m_TextPaint = this.getPaint();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_TextPaint = this.getPaint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        mInnerColor = typedArray.getColor(R.styleable.StrokeTextView_innerColor, Color.WHITE);
        mOuterColor = typedArray.getColor(R.styleable.StrokeTextView_outerColor, Color.BLACK);
        typedArray.recycle();
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        m_TextPaint = this.getPaint();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView);
        mInnerColor = typedArray.getColor(R.styleable.StrokeTextView_innerColor, Color.WHITE);
        mOuterColor = typedArray.getColor(R.styleable.StrokeTextView_outerColor, Color.BLACK);

        typedArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (m_bDrawSideLine) {
            // out line
            setTextColorUseReflection(mOuterColor);
            m_TextPaint.setStrokeWidth(3);
            m_TextPaint.setStyle(Style.FILL_AND_STROKE);
            m_TextPaint.setFakeBoldText(true);
            m_TextPaint.setShadowLayer(1, 0, 0, 0);
            super.onDraw(canvas);

            // inner line
            setTextColorUseReflection(mInnerColor);
            m_TextPaint.setStrokeWidth(0);
            m_TextPaint.setStyle(Style.FILL_AND_STROKE);
            m_TextPaint.setFakeBoldText(false);
            m_TextPaint.setShadowLayer(0, 0, 0, 0);
        }
        super.onDraw(canvas);
    }

    private void setTextColorUseReflection(int color) {
        Field textColorField;
        try {
            textColorField = TextView.class.getDeclaredField("mCurTextColor");
            textColorField.setAccessible(true);
            textColorField.set(this, color);
            textColorField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        m_TextPaint.setColor(color);
    }
}
