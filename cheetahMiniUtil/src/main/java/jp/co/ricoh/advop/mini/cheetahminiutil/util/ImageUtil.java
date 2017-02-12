package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import jp.co.ricoh.advop.mini.cheetahminiutil.view.PreviewImageView;


public class ImageUtil {

    public static int getScale(float orgWidth, float orgHeight, float dstWidth,
                               float dstHeight) {
        return (int) Math.max(orgWidth / (float) dstWidth, orgHeight / (float) dstHeight) + 1;
    }

    public static Bitmap createThumbnail(String path, int scale) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = scale;
        LogC.d("opt.inSampleSize = " + opt.inSampleSize);
        Bitmap bitmap = BitmapFactory.decodeFile(path, opt);
        LogC.d("scaled.getWidth()=" + bitmap.getWidth()
                + " scaled.getHeight()=" + bitmap.getHeight());
        return bitmap;
    }

    public static Bitmap createBitmap(String path, int rotate, int scale) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = scale;
        opt.inMutable = true;
        opt.inPreferredConfig= Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(path,opt);
        if (bitmap == null){
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(rotate, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return bmp;

    }

    public static Paint createPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        return paint;
    }

    public static boolean saveBitmap2File(Bitmap bmp, String filename)
            throws IOException {
        CompressFormat format = CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        boolean res = false;
        try {
            stream = new FileOutputStream(filename);
            res = bmp.compress(format, quality, stream);
        } catch (FileNotFoundException e) {
            LogC.w(e);
        } finally {
                stream.close();
        }

        return res;
    }


    public static DrawImage createDrawImage(Bitmap bitmap, int rotate) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (rotate == PreviewImageView.ROTATE_DEGREE_90
                || rotate == PreviewImageView.ROTATE_DEGREE_270) {
            width = bitmap.getHeight();
            height = bitmap.getWidth();
        }

        Bitmap destBitmap = Bitmap.createBitmap(width, height,
                bitmap.getConfig());

        Canvas canvas = new Canvas(destBitmap);
        Matrix matrix = new Matrix();

        if (rotate == PreviewImageView.ROTATE_DEGREE_90
                || rotate == PreviewImageView.ROTATE_DEGREE_180
                || rotate == PreviewImageView.ROTATE_DEGREE_270) {

            matrix.preRotate(rotate, 0, 0);

            if (rotate == PreviewImageView.ROTATE_DEGREE_90) {
                matrix.postTranslate(bitmap.getHeight(), 0);
            } else if (rotate == PreviewImageView.ROTATE_DEGREE_270) {
                matrix.postTranslate(0, bitmap.getWidth());
            } else if (rotate == PreviewImageView.ROTATE_DEGREE_180) {
                matrix.postTranslate(bitmap.getWidth(), bitmap.getHeight());
            }
        }
        canvas.drawBitmap(bitmap, matrix, null);
        bitmap.recycle();
        return new DrawImage(canvas, destBitmap);
    }

    public static boolean drawAndSaveBitmap(String srcImage, String dstImage,
                                            ArrayList<DrawPath> list, int rotate) {
        if (list.isEmpty()) {
            return false;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(srcImage);
        DrawImage drawImage = createDrawImage(bitmap, rotate);
        Paint paint = createPaint();

        for (int i = 0; i < list.size(); i++) {
            DrawPath path = list.get(i);
            paint.setStrokeWidth(path.strokenWidth);
            paint.setColor(path.color);
            drawImage.canvas.drawPath(path.path, paint);
        }

        boolean res = false;
        try {
            res = saveBitmap2File(drawImage.drawBitmap, dstImage);
        } catch (IOException e) {
            LogC.w(e);
        }
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        if (drawImage.drawBitmap != null && !drawImage.drawBitmap.isRecycled()) {
            drawImage.drawBitmap.recycle();
        }
        return res;
    }

    public static InputStream Bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    public static class DrawImage {
        public Canvas canvas;
        public Bitmap drawBitmap;

        public DrawImage(Canvas canvas, Bitmap bmp) {
            this.canvas = canvas;
            this.drawBitmap = bmp;
        }
    }

    public static class DrawPath {
        public Path path;
        public float strokenWidth;
        public int color;

        public DrawPath(float strokenWidth, int color) {
            this.path = new Path();
            this.strokenWidth = strokenWidth;
            this.color = color;
        }
    }
}
