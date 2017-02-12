package jp.co.ricoh.advop.mini.cheetahminiutil.logic;

/**
 * Created by duqiang on 8/3/2016.
 */
public class MediaInfo {

    public static final int TYPE_SD_CARD = 0;
    public static final int TYPE_USB = 1;

    public MediaInfo(String path, int type) {
        this.path = path;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;
    private int type;
}
