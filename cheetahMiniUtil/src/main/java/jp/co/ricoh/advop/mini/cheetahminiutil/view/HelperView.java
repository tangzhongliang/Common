package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.BaseOnClickListener;

/**
 * Created by baotao on 8/1/2016.
 */
public class HelperView extends FrameLayout {
    public HelperView(Context context) {
        this(context, null);
    }

    public HelperView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HelperView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private ImageView imgHelper;

    private void init() {
        FrameLayout view = (FrameLayout) View.inflate(getContext(), R.layout.layout_helper, null);
        imgHelper = (ImageView) view.getChildAt(0);
        view.findViewById(R.id.img_close).setOnClickListener(new BaseOnClickListener() {
            @Override
            public void onWork(View v) {
                dismiss();
            }
        });
        imgHelper.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addView(view);
    }


    public void setImage(Drawable drawable) {
        imgHelper.setImageDrawable(drawable);
    }


    public void show() {
        setVisibility(VISIBLE);
    }

    public void dismiss() {
        setVisibility(INVISIBLE);
    }

    public boolean isShow() {
        return getVisibility() == VISIBLE;
    }
}
