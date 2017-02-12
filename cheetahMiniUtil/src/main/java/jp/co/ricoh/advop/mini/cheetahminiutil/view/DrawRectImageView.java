package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by baotao on 9/6/2016.
 */
public class DrawRectImageView extends ImageView {

    private RectF bitmapRectF = new RectF();
    private RectF mapRectF = new RectF();

    private RectF selectedRectF = new RectF();
    private RectF mapSelectedRectF = new RectF();

    private Paint rectPaint = new Paint();
    private Paint selectedRectPaint = new Paint();

    private boolean isShowSelectedRect;
    public void setShowSelectedRect(boolean showSelectedRect) {
        isShowSelectedRect = showSelectedRect;
        invalidate();
    }


    public DrawRectImageView(Context context) {
        this(context, null);
    }

    public DrawRectImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawRectImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(0xffff0000);

        selectedRectPaint.setStyle(Paint.Style.STROKE);
        selectedRectPaint.setColor(0xffff0000);
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        selectedRectPaint.setPathEffect(effects);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if (bm != null) {
            mapRectF.set(0, 0, bm.getWidth(), bm.getHeight());
            bitmapRectF.set(0, 0, bm.getWidth(), bm.getHeight());
            selectedRectF.set(0, bm.getHeight() / 6.0f, bm.getWidth(), bm.getHeight() * 5.0f / 6);
            mapSelectedRectF.set(0, bm.getHeight() / 6.0f, bm.getWidth(), bm.getHeight() * 5.0f / 6);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mapRectF, rectPaint);
        if (isShowSelectedRect) {
            canvas.drawRect(mapSelectedRectF, selectedRectPaint);
        }
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
        matrix.mapRect(mapRectF, bitmapRectF);
        matrix.mapRect(mapSelectedRectF, selectedRectF);
    }
}
