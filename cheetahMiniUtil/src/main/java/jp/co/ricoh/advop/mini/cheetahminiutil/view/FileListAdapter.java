package jp.co.ricoh.advop.mini.cheetahminiutil.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;

/**
 * Created by baotao on 7/1/2016.
 */
public class FileListAdapter extends BaseAdapter {

    private Context mContext;
    private List<File> mFileList;

    private int mLoadFailImage = R.drawable.img_fail;
    private int mDefaultImage;

    private int mBitmapLoadSize = 3000 * 2000;

    public FileListAdapter(Context context) {
        mContext = context;
        mFileList = new ArrayList<File>();
    }

    public void updateFile(List<File> fileList) {
        mFileList = new ArrayList<File>();
        mFileList.addAll(fileList);
        notifyDataSetChanged();
    }

    public void setBitmapLoadSize(int size){
        mBitmapLoadSize = size;
    }

    @Override
    public int getCount() {
        return mFileList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.logo_list_adapter_item_view, null);
            holder.imgLogo = (ImageView) convertView.findViewById(R.id.img_logo);
            holder.tvFileName = (TextView) convertView.findViewById(R.id.tv_file_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvFileName.setText(mFileList.get(position).getName());
        String path = null;
        File file = mFileList.get(position);
        if (CHolder.instance().getInitParameters().isLoadImageAvailable() && file.isFile() && (file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpg"))) {
            ImageLoader.getInstance().displayImage(null, holder.imgLogo);
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getPath(), opt);
            int height = opt.outHeight;
            int width = opt.outWidth;
            if (height * width > mBitmapLoadSize || file.length() > 1024 * 1024 * 10) {
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mLoadFailImage);
                holder.imgLogo.setImageBitmap(bitmap);
                return convertView;
            }

            float scale = width / 124.0f > height / 84.0f ? width / 124.0f : height / 84.0f;
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = (int) scale + 1;
            bitmapOptions.inDensity = DisplayMetrics.DENSITY_LOW;
            bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            if (mLoadFailImage == 0) {
                mLoadFailImage = R.drawable.img_fail;
            }
            try {
                path = "file://" + file.getCanonicalPath();
                DisplayImageOptions options = new DisplayImageOptions.Builder()
//                    .showImageOnLoading(R.drawable.ic_stub)
                        .showImageForEmptyUri(mLoadFailImage)
                        .showImageOnFail(mLoadFailImage)
                        .decodingOptions(bitmapOptions)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .considerExifParams(true)
                        //.displayer(new RoundedBitmapDisplayer(20))
                        .build();
                //ImageLoader.getInstance().displayImage(path, holder.imgLogo, options);
                ImageLoader.getInstance().displayImage(path, holder.imgLogo, options);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            holder.imgLogo.setImageResource(mDefaultImage);
        }

        return convertView;
    }

    public class ViewHolder {
        ImageView imgLogo;
        TextView tvFileName;
    }

    public void setLoadFailImage(int id) {
        mLoadFailImage = id;
    }

    public void setDefaultImage(int id) {
        mDefaultImage = id;
    }
}
