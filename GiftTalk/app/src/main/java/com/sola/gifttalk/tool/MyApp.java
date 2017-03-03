package com.sola.gifttalk.tool;

import android.app.Application;
import android.content.Context;

import com.sola.gifttalk.db.DaoMaster;
import com.sola.gifttalk.db.DaoSession;

/**
 * Created by dllo on 17/2/23.
 */

public class MyApp extends Application {
    public static Context context;

    private static DaoMaster daoMaster;

    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }

    public static DaoMaster getDaoMaster(){

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(),"Search.db",null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster;
    }

    public static DaoSession getDaoSession(){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
