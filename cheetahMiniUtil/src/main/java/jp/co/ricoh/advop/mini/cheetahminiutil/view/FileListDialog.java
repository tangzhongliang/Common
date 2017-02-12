package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Buzzer;


/**
 * Created by baotao on 8/5/2016.
 */
public class FileListDialog extends PopupWindow {

    private TextView mTvTitle;
    private TextView mTvPath;
    private TextView mTvInformation;
    private GridView mImageLogoGridView;

    private FileListAdapter mFileListAdapter;

    private View contentView;

    public FileListDialog(Context context) {
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.view_image_list, null);
        contentView.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvInformation = (TextView) contentView.findViewById(R.id.tv_information);
        mTvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        mTvPath = (TextView) contentView.findViewById(R.id.tv_path);
        mImageLogoGridView = (GridView) contentView.findViewById(R.id.grid_view_logo_list);
        mFileListAdapter = new FileListAdapter(context);
        mImageLogoGridView.setAdapter(mFileListAdapter);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0x90000000));
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                Buzzer.play();
                if (ImageLoader.getInstance() != null) {
                    ImageLoader.getInstance().stop();
                    ImageLoader.getInstance().clearMemoryCache();
                }
            }
        });
    }


    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setPath(String path) {
        if (path == null || path.isEmpty()) {
            mTvPath.setVisibility(View.GONE);
        }
        mTvPath.setVisibility(View.VISIBLE);
        mTvPath.setText(path);
    }

    public void setInformation(String information) {
        mTvInformation.setText(information);
    }

    public void setPathList(List<File> list) {
        mFileListAdapter.updateFile(list);
    }

    public void show(View view) {
        showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mImageLogoGridView.setOnItemClickListener(onItemClickListener);
    }

    public void setBackground(int id) {
        contentView.findViewById(R.id.layout_main).setBackgroundResource(id);
    }

    public void setTitleBackground(int id) {
        contentView.findViewById(R.id.layout_title).setBackgroundResource(id);
    }

    public void setTitleBackgroundColor(int color) {
        contentView.findViewById(R.id.layout_title).setBackgroundColor(color);
    }

    public void setBodyBackground(int id) {
        mImageLogoGridView.setBackgroundResource(id);
    }

    public void setBodyBackgroundColor(int color) {
        mImageLogoGridView.setBackgroundColor(color);
    }

    public void setCloseImage(int id) {
        ((ImageView) contentView.findViewById(R.id.img_close)).setImageResource(id);
    }

    public void setBackgroundSize(int width, int height) {
        LinearLayout mainLayout = (LinearLayout) contentView.findViewById(R.id.layout_main);
        ViewGroup.LayoutParams params = mainLayout.getLayoutParams();
        params.width = width;
        params.height = height;
        mainLayout.setLayoutParams(params);
    }

    public void setLoadFailImage(int id) {
        mFileListAdapter.setLoadFailImage(id);
    }

    public void setDefaultImage(int id) {
        mFileListAdapter.setDefaultImage(id);
    }

    public void setBitmapLoadSize(int size) {
        mFileListAdapter.setBitmapLoadSize(size);
    }
}
