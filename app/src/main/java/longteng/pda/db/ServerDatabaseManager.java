package longteng.pda.db;

import android.database.sqlite.SQLiteDatabase;

import longteng.pda.common.CommonHelper;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ServerDatabaseManager {
    private SQLiteDatabase mDatabase;

     public void ServerDatabaseManager(){
       this.mDatabase = SQLiteDatabase.openOrCreateDatabase("/data/data/longteng/databases/"+ CommonHelper.SERVER_DB_NAME,null);
    }



}
