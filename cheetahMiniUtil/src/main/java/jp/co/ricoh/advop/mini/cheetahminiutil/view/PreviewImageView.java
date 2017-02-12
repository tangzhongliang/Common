package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.BaseOnClickListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.ImageUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

/**
 * Created by baotao on 6/27/2016.
 */
public class PreviewImageView extends RelativeLayout implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener {

    protected DrawRectImageView mImgPreview;

    protected ImageView mImgZoomIn;
    protected ImageView mImgZoomOut;

//    protected ImageView mImgRotateLeft;
//    protected ImageView mImgRotateRight;

    protected Bitmap virtualBitmap;
    protected int mScaleSize;

    protected Matrix mScaleMatrix;

    protected float anchorX, anchorY;

    protected float mViewToImageScale = 1;

    private static final float MAX_SCALE = 4.76f;
    private static final float MIN_SCALE = 1.0f;

    protected ThumbnailImageView mThumbnailImageView;

    private int BITMAP_EDGE_WIDTH = 120;


    /**
     * can change size  and  drag
     */
    public static final int PREVIEW_GESTURE = 1;
    /**
     * no gesture change size and drag , but can draw line
     */
    public static final int PREVIEW_DRAW = 2;

    /**
     * set view  mode:   gesture or  draw
     *
     * @param preview_mode
     */
    public void setPreviewMode(int preview_mode) {
        this.previewMode = preview_mode;
    }

    private int previewMode = PREVIEW_GESTURE;

    private ScaleGestureDetector mScaleGestureDetector;

    private float mLastX, mLastY;
    private int mTouchSlop;
    private boolean shouldCheckTopAndBottom;
    private int mLastPointCount;
    private boolean shouldCheckLeftAndRight;

    private boolean isShowSelectedRect;

    public void setShowSelectedRect(boolean showSelectedRect) {
        isShowSelectedRect = showSelectedRect;
        mImgPreview.setShowSelectedRect(isShowSelectedRect);
    }

    public PreviewImageView(Context context) {
        this(context, null);
    }

    public PreviewImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PreviewImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mImgPreview = new DrawRectImageView(getContext());

        addView(mImgPreview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout layout = (LinearLayout) View.inflate(getContext(), R.layout.layout_zoom, null);
        mImgZoomIn = (ImageView) layout.findViewById(R.id.img_zoom_in);
        mImgZoomOut = (ImageView) layout.findViewById(R.id.img_zoom_out);
        mImgZoomIn.setOnClickListener(new BaseOnClickListener() {
            @Override
            public void onWork(View v) {
                zoomIn();
            }
        });
        mImgZoomOut.setOnClickListener(new BaseOnClickListener() {
            @Override
            public void onWork(View v) {
                zoomOut();
            }
        });

//        mImgRotateLeft = (ImageView) layout.findViewById(R.id.img_rotate_left);
//        mImgRotateLeft.setOnClickListener(new BaseOnClickListener() {
//            @Override
//            public void onWork(View v) {
//                rotateLeft();
//            }
//
//        });
//        mImgRotateRight = (ImageView) layout.findViewById(R.id.img_rotate_right);
//        mImgRotateRight.setOnClickListener(new BaseOnClickListener() {
//            @Override
//            public void onWork(View v) {
//                rotateRight();
//            }
//        });

        RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.setMargins(0, 0, 10, 10);
        addView(layout, params);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), this);
        mScaleMatrix = new Matrix();
        mImgPreview.setScaleType(ImageView.ScaleType.MATRIX);
        paint = ImageUtil.createPaint();
    }

    public void setmImgZoomIn(ImageView imgZoomIn) {
        if (this.mImgZoomIn != null) {
            this.mImgZoomIn.setVisibility(GONE);
        }
        this.mImgZoomIn = imgZoomIn;
        this.mImgZoomIn.setOnClickListener(new BaseOnClickListener() {
            @Override
            public void onWork(View v) {
                zoomIn();
            }
        });

    }

    public void setmImgZoomOut(ImageView imgZoomOut) {
        if (this.mImgZoomOut != null) {
            this.mImgZoomOut.setVisibility(GONE);
        }
        this.mImgZoomOut = imgZoomOut;
        this.mImgZoomOut.setOnClickListener(new BaseOnClickListener() {
            @Override
            public void onWork(View v) {
                zoomOut();
            }
        });
    }

    public void setImgZoomOutVisibility(int visibility){
        mImgZoomOut.setVisibility(visibility);
    }

    public void setImgZoomInVisibility(int visibility){
        mImgZoomIn.setVisibility(visibility);
    }
    protected static final int MATRIX_VALUES_NUM = 9;//***
    protected float[] mMatrixValues = new float[MATRIX_VALUES_NUM];
    protected int mVirtualBitmapWidth = 0;
    protected int mVirtualBitmapHeight = 0;
    // image display range
    protected float displayAreaWidth;//***
    protected float displayAreaHeight;
    protected int virtualImgScale = 1;

    protected float mTopOffset = 0;

    public float getmTopOffset() {
        return mTopOffset;
    }

    public float getmLeftOffset() {
        return mLeftOffset;
    }

    protected float mLeftOffset = 0;
    protected float mDefaultScale = 0.0f;
    protected float mRealScale = 0.0f;

    protected int mRealBitmapHeight;
    protected int mRealBitmapWidth;

    private boolean mIsFirst = true;

    protected String mImgPath;
    protected int mRotate = 0;

    protected Canvas canvas;
    protected Paint paint;

//    protected float mRotateDegree;

    public static int ROTATE_DEGREE_90 = 90;
    public static int ROTATE_DEGREE_180 = 180;
    public static int ROTATE_DEGREE_270 = 270;

    private void getOriginalImageSize(String path) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opt);

        mRealBitmapHeight = opt.outHeight;
        mRealBitmapWidth = opt.outWidth;

        if (mRotate == ROTATE_DEGREE_90 || mRotate == ROTATE_DEGREE_270) {
            mRealBitmapHeight = opt.outWidth;
            mRealBitmapWidth = opt.outHeight;
        }

    }

    private void createBitmap() {
        Bitmap bitmap = ImageUtil.createThumbnail(mImgPath, mScaleSize);
        ImageUtil.DrawImage drawImage = ImageUtil.createDrawImage(bitmap,
                mRotate);
        bitmap.recycle();
        this.canvas = drawImage.canvas;
        this.virtualBitmap = drawImage.drawBitmap;

    }

    public void setBitmap(String path, int rotate, int scaleSize) {
        mScaleSize = scaleSize;
        initSize(path, rotate);

        createBitmap();

        setImageBitmap(virtualBitmap);

        translateAndScaleBitmap();

        mVirtualBitmapWidth = virtualBitmap.getWidth();
        mVirtualBitmapHeight = virtualBitmap.getHeight();
        initOffset();

    }

    public void resetBitmap(boolean isZoom) {

        createBitmap();

        setImageBitmap(virtualBitmap);
        if (isZoom) {
            translateAndScaleBitmap();
        }
        mVirtualBitmapWidth = virtualBitmap.getWidth();
        mVirtualBitmapHeight = virtualBitmap.getHeight();

        initOffset();

    }

    public void destroyDrawImageObj() {

        destroyDrawingCache();
        setImageBitmap(null);
        if (virtualBitmap != null && !virtualBitmap.isRecycled()) {
            virtualBitmap.recycle();
        }
        canvas = null;
    }

    private void initSize(String path, int rotate) {
        this.mImgPath = path;
        this.mRotate = rotate;

        getOriginalImageSize(mImgPath);
        virtualImgScale = ImageUtil.getScale((float) mRealBitmapWidth,
                (float) mVirtualBitmapHeight, displayAreaWidth, displayAreaHeight);
    }

    private void initOffset() {
        mScaleMatrix.getValues(mMatrixValues);
        mDefaultScale = mMatrixValues[Matrix.MSCALE_X];

        mRealScale = Math.min((float) mVirtualBitmapWidth / (float) mRealBitmapWidth,
                (float) mVirtualBitmapHeight / (float) mRealBitmapHeight) * mDefaultScale;

        mLeftOffset = mMatrixValues[Matrix.MTRANS_X];
        mTopOffset = mMatrixValues[Matrix.MTRANS_Y];
    }

//    public void rotateLeft() {
//        mScaleMatrix = new Matrix();
//        mRotateDegree -= 90;
//        int width = getWidth();
//        int height = getHeight();
//
//        anchorX = width / 2;
//        anchorY = height / 2;
//        Drawable d = mImgPreview.getDrawable();
//        if (d == null) {
//            return;
//        }
//        int dw = d.getIntrinsicWidth();
//        int dh = d.getIntrinsicHeight();
//        float scale;
//        int dx,dy;
//        if (mRotateDegree % 180 == 0) {
//            scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
//
//        } else {
//            scale = Math.min(width * 1.0f / dh, height * 1.0f / dw);
////            dx = width / 2 - dh / 2;
////            dy = height / 2 - dw / 2;
//        }
//
//        dx = width / 2 - dw / 2;
//        dy = height / 2 - dh / 2;
//        mViewToImageScale = scale;
//        mScaleMatrix.postTranslate(dx, dy);
////        mScaleMatrix.postScale(mViewToImageScale, mViewToImageScale, width / 2, height / 2);
////        mScaleMatrix.postScale(mViewToImageScale, mViewToImageScale, width / 2, height / 2);
////        mScaleMatrix.setRotate(mRotateDegree, width / 2, height / 2);
//
//        mImgPreview.setImageMatrix(mScaleMatrix);
//    }
//
//
//
//    public void rotateRight() {
//        mRotateDegree += 90;
////        mScaleMatrix.setRotate(mRotateDegree, getWidth() / 2, getHeight() / 2);
////        mImgPreview.setImageMatrix(mScaleMatrix);
//        translateAndScaleBitmap();
//    }

    public void zoomIn() {
        if (mImgPreview.getDrawable() == null) {
            return;
        }

        if (mImgPreview.getDrawable() instanceof BitmapDrawable && ((BitmapDrawable) mImgPreview.getDrawable()).getBitmap() == null) {
            return;
        }

        if (getScale() < MAX_SCALE) {
            float scale = 1.25f;
            if (getScale() * 1.25 > MAX_SCALE) {
                scale = MAX_SCALE / getScale();
            }
            mScaleMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            mImgPreview.setImageMatrix(mScaleMatrix);
            initOffset();
            if (mThumbnailImageView != null) {
                mThumbnailImageView.onScaleChange(getScale(), anchorX, anchorY);
            } else {
                checkBorderAndCenter();
                mImgPreview.setImageMatrix(mScaleMatrix);
            }

            mImgZoomOut.setEnabled(true);
            if (getScale() >= MAX_SCALE) {
                mImgZoomIn.setEnabled(false);
            }
        }
    }

    public void zoomOut() {
        if (mImgPreview.getDrawable() == null) {
            return;
        }
        if (mImgPreview.getDrawable() instanceof BitmapDrawable && ((BitmapDrawable) mImgPreview.getDrawable()).getBitmap() == null) {
            return;
        }
        if (getScale() >= MIN_SCALE) {
            float scale = 0.8f;
            mScaleMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            if (getScale() < MIN_SCALE) {
                float[] values = new float[9];
                mScaleMatrix.getValues(values);
                mScaleMatrix.postScale(mViewToImageScale / values[Matrix.MSCALE_X], mViewToImageScale / values[Matrix.MSCALE_X], getWidth() / 2, getHeight() / 2);
            }
            mImgPreview.setImageMatrix(mScaleMatrix);
            initOffset();
            if (mThumbnailImageView != null) {
                mThumbnailImageView.onScaleChange(getScale(), anchorX, anchorY);
            } else {
                checkBorderAndCenter();
                mImgPreview.setImageMatrix(mScaleMatrix);
            }

            mImgZoomIn.setEnabled(true);
            if (getScale() <= MIN_SCALE) {
                mImgZoomOut.setEnabled(false);
            }
        }
    }

    public void setScale(float scale) {
        translateAndScaleBitmap();
        mScaleMatrix.postScale(scale, scale, getWidth() / 2, getHeight() / 2);
        mImgPreview.setImageMatrix(mScaleMatrix);
    }

    public void setImageBitmap(Bitmap bm) {
        mImgPreview.setImageBitmap(bm);
        if (bm != null && virtualBitmap == null) {
            translateAndScaleBitmap();

            mRealBitmapHeight = bm.getHeight();
            mRealBitmapWidth = bm.getWidth();

            initOffset();
        }
    }

    public void setImageBitmap(Bitmap bm, boolean isZoom) {
        mImgPreview.setImageBitmap(bm);
        if (bm != null && virtualBitmap == null && isZoom) {
            translateAndScaleBitmap();

            mRealBitmapHeight = bm.getHeight();
            mRealBitmapWidth = bm.getWidth();

            initOffset();
        }
    }
//
//
//
//    public Point obtainRotationPoint(Point center, Point source, float degree) {
//        Point disPoint = new Point();
//        disPoint.x = source.x - center.x;
//        disPoint.y = source.y - center.y;
//
//        double originRadian = 0;
//        double originDegree = 0;
//
//        double resultDegree = 0;
//        double resultRadian = 0;
//
//        Point resultPoint = new Point();
//
//        double distance = Math.sqrt(disPoint.x * disPoint.x + disPoint.y * disPoint.y);
//        if (disPoint.x == 0 && disPoint.y == 0) {
//            return center;
//        } else if (disPoint.x >= 0 && disPoint.y >= 0) {
//            originRadian = Math.asin(disPoint.y / distance);
//        } else if (disPoint.x < 0 && disPoint.y >= 0) {
//            originRadian = Math.asin(Math.abs(disPoint.x) / distance);
//            originRadian = originRadian + Math.PI / 2;
//        } else if (disPoint.x < 0 && disPoint.y < 0) {
//            originRadian = Math.asin(Math.abs(disPoint.y) / distance);
//            originRadian = originRadian + Math.PI;
//        } else if (disPoint.x >= 0 && disPoint.y < 0) {
//            originRadian = Math.asin(disPoint.x / distance);
//            originRadian = originRadian + Math.PI * 3 / 2;
//        }
//
//        originDegree = radianToDegree(originRadian);
//        resultDegree = originDegree + degree;
//
//        resultRadian = degreeToRadian(resultDegree);
//
//        resultPoint.x = (int) Math.round(distance * Math.cos(resultRadian));
//        resultPoint.y = (int) Math.round(distance * Math.sin(resultRadian));
//        resultPoint.x += center.x;
//        resultPoint.y += center.y;
//
//        return resultPoint;
//    }
//
//    public double radianToDegree(double radian) {
//        return radian * 180 / Math.PI;
//    }
//
//    public double degreeToRadian(double degree) {
//        return degree * Math.PI / 180;
//    }

    protected OnTouchListener mOnTouchListener;
    private int pointCount;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (previewMode == PREVIEW_DRAW) {
            if (mOnTouchListener != null) {
                return mOnTouchListener.onTouch(this, event);
            }
            return true;
        }
        //让detector来处理
        boolean res = mScaleGestureDetector.onTouchEvent(event);

        pointCount = event.getPointerCount();

        if (pointCount > 1) {
            return res;
        }
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastPointCount = pointCount;
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;
                RectF rectF = getMatrixRectF();
                if (mImgPreview.getDrawable() != null) {
                    shouldCheckTopAndBottom = shouldCheckLeftAndRight = true;
                    //宽度小于控件宽度，不允许横向移动
                    if (rectF.width() < getWidth()) {
                        shouldCheckLeftAndRight = false;
                        dx = 0;
                    }
                    if (rectF.height() < getHeight()) {
                        shouldCheckTopAndBottom = false;
                        dy = 0;

                    }
                    if (Math.abs(dx) < 20 && Math.abs(dy) < 20 && pointCount == 1 && mLastPointCount == 1) {
                        mScaleMatrix.postTranslate(dx, dy);
                        checkBorderWhenTranslate();
                        mImgPreview.setImageMatrix(mScaleMatrix);

                    }
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mLastPointCount = 0;
                pointCount = 0;
                break;
            case MotionEvent.ACTION_CANCEL:
                mLastPointCount = 0;
                pointCount = 0;
                break;
        }

        return true;
    }

    /**
     * 判断是否足以出发move
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isMoveAction(float dx, float dy) {
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }

    /**
     * 在缩放是检查图片左右边界是否不再屏幕边界
     */
    public ImageView getImageView() {
        return mImgPreview;
    }

    private void checkBorderAndCenter() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        if (rectF.width() > width) {
            if (rectF.left > 0) {
                deltaX -= rectF.left;
            }
            if (rectF.right < width) {
                deltaX += width - rectF.right;
            }
        }

        if (rectF.height() > height) {
            if (rectF.top > 0) {
                deltaY -= rectF.top;
            }
            if (rectF.bottom < height) {
                deltaY += height - rectF.bottom;
            }
        }

        if (rectF.width() <= width) {
            deltaX = width / 2 - rectF.right + rectF.width() / 2f;
        }
        if (rectF.height() <= height) {
            deltaY = height / 2 - rectF.bottom + rectF.height() / 2f;
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 获取图片的l,r,t,b，以及宽高
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();
        Drawable d = mImgPreview.getDrawable();
        if (d != null) {
            rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    /**
     * 在移动时进行边界检查
     */
    private void checkBorderWhenTranslate() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        if (shouldCheckTopAndBottom) {
            if (rectF.top > 0) {
                deltaY = -rectF.top;
            }

            if (rectF.bottom < getHeight()) {
                deltaY = getHeight() - rectF.bottom;

            }
        }
        if (shouldCheckLeftAndRight) {
            if (rectF.left > 0) {
                deltaX = -rectF.left;
            }
            if (rectF.right < getWidth()) {
                deltaX = getWidth() - rectF.right;
            }
        }

        mScaleMatrix.postTranslate(deltaX, deltaY);

    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {

        float cur_scale = getScale() * mViewToImageScale;
        float scale_factor = detector.getScaleFactor();
        float new_scale = scale_factor;
        if (scale_factor > 1.0f) {
            new_scale = Math.min(scale_factor, MAX_SCALE / cur_scale);
            if (!mImgZoomIn.isEnabled()) {
                new_scale = 1;
            }
        } else if (scale_factor < 1.0f) {
            new_scale = Math.max(scale_factor, mViewToImageScale / cur_scale);
        }
        mScaleMatrix.postScale(new_scale, new_scale, detector.getFocusX(), detector.getFocusY());

        mImgZoomIn.setEnabled(true);
        mImgZoomOut.setEnabled(true);
        if (getScale() >= MAX_SCALE) {
            mImgZoomIn.setEnabled(false);
        }
        if (new_scale <= mViewToImageScale / cur_scale) {
            mImgZoomOut.setEnabled(false);
        }

        checkBorderAndCenter();
        mImgPreview.setImageMatrix(mScaleMatrix);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    protected void translateAndScaleBitmap() {
        mScaleMatrix = new Matrix();
        int width = getWidth();
        int height = getHeight();

        anchorX = width / 2;
        anchorY = height / 2;
        Drawable d = mImgPreview.getDrawable();
        if (d == null) {
            return;
        }
        int dw = d.getIntrinsicWidth() + BITMAP_EDGE_WIDTH;
        int dh = d.getIntrinsicHeight() + BITMAP_EDGE_WIDTH;

        float scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
        LogC.d("scale:" + scale);
        mViewToImageScale = scale;

        int dx = width / 2 - (dw - BITMAP_EDGE_WIDTH) / 2;
        int dy = height / 2 - (dh - BITMAP_EDGE_WIDTH) / 2;
        mScaleMatrix.postTranslate(dx, dy);
        mScaleMatrix.postScale(mViewToImageScale, mViewToImageScale, width / 2, height / 2);
        mImgPreview.setImageMatrix(mScaleMatrix);

        mImgZoomOut.setEnabled(false);
        mImgZoomIn.setEnabled(true);

        if (mThumbnailImageView != null) {
            mThumbnailImageView.onScaleChange(getScale(), anchorX, anchorY);
        }
    }

    public float getScale() {
        float[] values = new float[9];
        mScaleMatrix.getValues(values);
        return values[Matrix.MSCALE_X] / mViewToImageScale;
    }


    @Override
    public void onGlobalLayout() {
        if (mIsFirst) {
            translateAndScaleBitmap();
            displayAreaWidth = getWidth();
            displayAreaHeight = getHeight();
            mIsFirst = false;
            if (mThumbnailImageView != null) {
                mThumbnailImageView.setmThumbnailScale((float) getWidth() / (float) mThumbnailImageView.getWidth());
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    public void setmThumbnailImageView(final ThumbnailImageView mThumbnailImageView) {
        this.mThumbnailImageView = mThumbnailImageView;
        mThumbnailImageView.setOnMoveListener(new ThumbnailImageView.OnMoveListener() {
            @Override
            public void onMove(float dx, float dy) {
                anchorX = anchorX + dx;
                anchorY = anchorY + dy;
                mLeftOffset -= dx * getScale();
                mTopOffset -= dy * getScale();
                mScaleMatrix.postTranslate(-dx * getScale(), -dy * getScale());
                mThumbnailImageView.onPointChange(anchorX, anchorY);
                mImgPreview.setImageMatrix(mScaleMatrix);
            }
        });
    }

    public ThumbnailImageView getmThumbnailImageView() {
        return mThumbnailImageView;
    }

    public int getBitmapX(int x) {
        mScaleMatrix.getValues(mMatrixValues);
        int toX = (int) ((x - mMatrixValues[Matrix.MTRANS_X]) / mMatrixValues[Matrix.MSCALE_X]);

        if (toX < 0) {
            return -1;
        }
        if (toX > mRealBitmapWidth) {
            return -1;
        }
        return toX;
    }

    public int getBitmapY(int y) {
        mScaleMatrix.getValues(mMatrixValues);
        int toY = (int) ((y - mMatrixValues[Matrix.MTRANS_Y]) / mMatrixValues[Matrix.MSCALE_X]);

        if (toY < 0) {
            return -1;
        }
        if ((toY > mRealBitmapHeight)) {
            return -1;
        }
        return toY;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
