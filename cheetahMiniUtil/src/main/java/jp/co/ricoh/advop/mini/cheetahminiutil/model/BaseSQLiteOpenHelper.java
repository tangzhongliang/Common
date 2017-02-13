package jp.co.ricoh.advop.mini.cheetahminiutil.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;

/**
 * Copyright (C) 2013-2016 RICOH Co.,LTD
 * All rights reserved
 */

public class BaseSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String db_name = "sparserss/sparserss.db";
    protected int mNewVersion;
    protected SQLiteDatabase mDatabase;

    protected static final String ALTER_TABLE = "ALTER TABLE ";

    protected static final String ADD = " ADD ";

    public BaseSQLiteOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
        mNewVersion = version;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    protected String createTable(String tableName, String[] columns, String[] types) {
        if (tableName == null || columns == null || types == null || types.length != columns.length || types.length == 0) {
            throw new IllegalArgumentException("Invalid parameters for creating table " + tableName);
        } else {
            StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");

            stringBuilder.append(tableName);
            stringBuilder.append(" (");
            for (int n = 0, i = columns.length; n < i; n++) {
                if (n > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(columns[n]).append(' ').append(types[n]);
            }
            return stringBuilder.append(");").toString();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }

    protected void executeCatchedSQL(SQLiteDatabase database, String query) {
        try {
            database.execSQL(query);
        } catch (Exception e) {
        }
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return getSqLiteDatabaseLocked(false);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onDowngrade(db, oldVersion, newVersion);
    }

    protected synchronized SQLiteDatabase getSqLiteDatabaseLocked(boolean writable) {
        if (mDatabase != null) {
            if (!mDatabase.isOpen()) {
                // Darn!  The user closed the database by calling mDatabase.close().
                mDatabase = null;
            } else if (!writable || !mDatabase.isReadOnly()) {
                // The database is already open for business.
                return mDatabase;
            }
        }
        File dir = new File(Const.PATH_PACKAGE_FOLDER);
        if (!dir.exists())
            dir.mkdirs();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(new File(Const.PATH_PACKAGE_FOLDER, db_name), null);
        final int version = db.getVersion();
        if (version != mNewVersion) {
            if (db.isReadOnly()) {
                throw new SQLiteException("Can't upgrade read-only database from version " +
                        db.getVersion() + " to " + mNewVersion + ": " + "database");
            }

            db.beginTransaction();
            try {
                if (version == 0) {
                    onCreate(db);
                } else {
                    if (version > mNewVersion) {
                        onDowngrade(db, version, mNewVersion);
                    } else {
                        onUpgrade(db, version, mNewVersion);
                    }
                }
                db.setVersion(mNewVersion);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

        onOpen(db);

        if (db.isReadOnly()) {
        }
        mDatabase = db;
        return db;
    }
}
