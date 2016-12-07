package longteng.pda.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import longteng.pda.vo.Order;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class AppDatabaseManager {
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private static AppDatabaseManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;
    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            instance = new AppDatabaseManager();
            mDatabaseHelper = helper;
        }
    }

    public static synchronized AppDatabaseManager getInstance(
            SQLiteOpenHelper helper) {
        if (instance == null) {
            initializeInstance(helper);
        }
        return instance;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getReadableDatabase();
        }
        return mDatabase;
    }

    public Cursor writeTable(String table) {
        Cursor cursor = getWritableDatabase().query(table, null, null, null,
                null, null, null);
        return cursor;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();
        }
    }

    public ArrayList<Map<String, Object>> getOrderList(){
        ArrayList<Map<String, Object>> listMap  = new ArrayList<Map<String, Object>>();
        Cursor cursor = getWritableDatabase().rawQuery("select * from Order_List",null);
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("OrderNO", cursor.getString(cursor.getColumnIndex("OrderNO")));
            map.put("CreateTime", cursor.getString(cursor.getColumnIndex("CreateTime")));
            map.put("AmendTime", cursor.getString(cursor.getColumnIndex("AmendTime")));
            map.put("Remark", cursor.getString(cursor.getColumnIndex("Remark")));
            map.put("Items", cursor.getString(cursor.getColumnIndex("Items")));
            map.put("SUM", cursor.getString(cursor.getColumnIndex("SUM")));
            map.put("OrderName", cursor.getString(cursor.getColumnIndex("OrderName")));
            listMap.add(map);
        }
        cursor.close();
        return listMap;
    }

    public void addOrder(Order order) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("AmendTime", order.getAmendTime());
        contentValues.put("CreateTime", order.getCreateTime());
        contentValues.put("OrderNO", order.getOrderNO());
        contentValues.put("OrderName", order.getOrderName());
        contentValues.put("Remark", order.getRemark());
        contentValues.put("Items", order.getItems());
        contentValues.put("SUM", order.getSUM());
        SQLiteDatabase W = getReadableDatabase();
        W.insert("Order_List", null, contentValues);
    }
}
