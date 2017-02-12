/*
 * Copyright (C) 2013-2017 RICOH Co.,LTD
 * All rights reserved
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import java.util.ArrayList;


public class FileObject {
    int rotate;
    String path;
    ArrayList<FileObject> childs;

    public FileObject() {
        childs = new ArrayList<FileObject>();
    }

    public FileObject(int rotate, String path) {
        this();
        this.rotate = rotate;
        this.path = path;
    }

    public int getRotate() {
        return rotate;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<FileObject> getChilds() {
        return childs;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
