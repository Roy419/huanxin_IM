package com.goodsure.frameworkdemo.common.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cly.greendaotest.gen.DaoMaster;
import com.cly.greendaotest.gen.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by admin on 2018/2/19.
 */

public class DbManager {
    // 是否加密
    public static final boolean ENCRYPTED = true;

    public static   String DB_NAME = "test.db";
    private static DbManager mDbManager;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private Context mContext;

    private DbManager(Context context) {
        this.mContext = context;
        // 初始化数据库信息
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        getDaoMaster(context);
        getDaoSession(context);
    }

    private DbManager(Context context, String dbName, String passwprd) {
        // 初始化数据库信息
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, dbName);
        getDaoMaster(context, dbName, passwprd);
        getDaoSession(context, dbName, passwprd);
    }

    public static DbManager getInstance(Context context, String dbName, String passwprd) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context, dbName, passwprd);
                }
            }
        }
        return mDbManager;
    }

    public static DbManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context);
                }
            }
        }
        return mDbManager;
    }

    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }

        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    MyOpenHelper helper = new MyOpenHelper(context,DB_NAME,null);
                    mDaoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    public static void setManagerNull(){
        mDaoMaster = null;
        mDaoSession=null;
        mDevOpenHelper= null;
        mDbManager = null;

    }

    /**
     * 获取DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }

        return mDaoSession;
    }


    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    public static Database getReadableDatabase(Context context, String dbName, String passwprd) {
        if (null == mDevOpenHelper) {
            getInstance(context, dbName, passwprd);
        }
        if (ENCRYPTED) {//加密
            return mDevOpenHelper.getEncryptedReadableDb(passwprd);
        } else {
            return mDevOpenHelper.getReadableDb();
        }
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @param dbName
     * @param passwprd
     * @return
     */
    public static Database getWritableDatabase(Context context, String dbName, String passwprd) {
        if (null == mDevOpenHelper) {
            getInstance(context, dbName, passwprd);
        }
        if (ENCRYPTED) {//加密
            return mDevOpenHelper.getEncryptedWritableDb(passwprd);
        } else {
            return mDevOpenHelper.getWritableDb();
        }
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @param dbName
     * @param passwprd
     * @return
     */
    public static DaoMaster getDaoMaster(Context context, String dbName, String passwprd) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase(context, dbName, passwprd));
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 获取DaoSession
     *
     * @param context
     * @param dbName
     * @param passwprd
     * @return
     */
    public static DaoSession getDaoSession(Context context, String dbName, String passwprd) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
//                mDaoSession = getDaoMaster(context,dbName,passwprd).newSession();
                mDaoSession = getDaoMaster(context, dbName, passwprd).newDevSession(context, dbName);
            }
        }

        return mDaoSession;
    }
}
