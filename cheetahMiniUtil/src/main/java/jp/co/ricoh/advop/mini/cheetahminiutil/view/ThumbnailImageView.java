package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.ImageUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

public class ThumbnailImageView extends ImageView implements View.OnTouchListener {


    private Paint paint;

    private float mRectX = 0;
    private float mRectY = 0;

    private float tImgWidth;
    private float tImgHeight;

    private float mThumbnailScale;

    public void setmThumbnailScale(float mThumbnailScale) {
        this.mThumbnailScale = mThumbnailScale;
    }

    public ThumbnailImageView(Context context) {
        this(context, null);
    }

    public ThumbnailImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThumbnailImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOnTouchListener(this);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        setPadding((int) (120*mThumbnailScale),(int) (120*mThumbnailScale),(int) (120*mThumbnailScale),(int) (120*mThumbnailScale));
    }

    public void setOnMoveListener(OnMoveListener listener) {
        this.listener = listener;
    }

    private OnMoveListener listener;


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float lastRectx = mRectX;
        float lastRecty = mRectY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mRectX = event.getX() - tImgWidth / 2;
                if (mRectX < 0)
                    mRectX = 0;
                if (mRectX > getWidth() - tImgWidth)
                    mRectX = getWidth() - tImgWidth;

                mRectY = event.getY() - tImgHeight / 2;
                if (mRectY < 0) {
                    mRectY = 0;
                }
                if (mRectY > getHeight() - tImgHeight) {
                    mRectY = getHeight() - tImgHeight;
                }
                listener.onMove((mRectX - lastRectx) * mThumbnailScale, (mRectY - lastRecty) * mThumbnailScale);

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                mRectX = event.getX() - tImgWidth / 2;
                if (mRectX < 0)
                    mRectX = 0;
                if (mRectX > getWidth() - tImgWidth)
                    mRectX = getWidth() - tImgWidth;

                mRectY = event.getY() - tImgHeight / 2;
                if (mRectY < 0) {
                    mRectY = 0;
                }
                if (mRectY > getHeight() - tImgHeight) {
                    mRectY = getHeight() - tImgHeight;
                }
                listener.onMove((mRectX - lastRectx) * mThumbnailScale, (mRectY - lastRecty) * mThumbnailScale);
                break;
        }
        invalidate();
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        if (tImgWidth == 0) {
            tImgWidth = getWidth();
        }
        if (tImgHeight == 0) {
            tImgHeight = getHeight();
        }
        canvas.drawRect(mRectX+1, mRectY, mRectX + tImgWidth, mRectY + tImgHeight-1, paint);
    }

    float mpx;
    float mpy;

    public void onScaleChange(float scale, float px, float py) {
        mpx = px / mThumbnailScale;
        mpy = py / mThumbnailScale;
        tImgHeight = getHeight() / scale;
        tImgWidth = getWidth() / scale;
        mRectX = mpx - tImgWidth / 2;
        mRectY = mpy - tImgHeight / 2;
        float lastRectX = mRectX;
        float lastRectY = mRectY;
        if (mRectX < 0) {
            mRectX = 0;
            listener.onMove((mRectX - lastRectX) * mThumbnailScale, 0);
        }
        if (mRectY < 0) {
            mRectY = 0;
            listener.onMove(0, (mRectY - lastRectY) * mThumbnailScale);
        }
        if (mRectX + tImgWidth > getWidth()) {
            mRectX = getWidth() - tImgWidth;
            listener.onMove((mRectX - lastRectX) * mThumbnailScale, 0);
        }
        if (mRectY + tImgHeight > getHeight()) {
            mRectY = getHeight() - tImgHeight;
            listener.onMove(0, (mRectY - lastRectY) * mThumbnailScale);
        }
        invalidate();
    }


    public void onPointChange(float px, float py) {
        mpx = px / mThumbnailScale;
        mpy = py / mThumbnailScale;
    }


    public void createThumbnail(String path, int rotate) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = 20;
        LogC.d("opt.inSampleSize = " + opt.inSampleSize);

        Bitmap bitmap = BitmapFactory.decodeFile(path, opt);
        ImageUtil.DrawImage drawImage = ImageUtil.createDrawImage(bitmap,
                rotate);

        LogC.d("scaled.getWidth()=" + bitmap.getWidth()
                + " scaled.getHeight()=" + bitmap.getHeight());

        setImageBitmap(drawImage.drawBitmap);

        invalidate();

    }
    interface OnMoveListener {
        void onMove(float dx, float dy);
    }}