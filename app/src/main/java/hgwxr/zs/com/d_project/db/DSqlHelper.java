package hgwxr.zs.com.d_project.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hgwxr on 2017/12/11.
 */

public class DSqlHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "D_Project_db";//数据库名字
    public static String TABLE_NAME = "user";// 表名
    public static String TABLE_TRANSACTION = "transaction_tb";// 表名
    public static final String TRANS_TIME = "trans_time";
    public static final String TRANS_MONEY = "trans_money";
    public static final String TRANS_CATEGORY = "trans_category";
    public static String FIELD_ID = "_id";// 列名
    public static String FIELD_NAME = "name";// 列名
    public static String FIELD_PSD = "psd";// 列名
    private static final int DB_VERSION = 1;   // 数据库版本

    public DSqlHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DSqlHelper instance(Context context) {
        if (mDsql == null) {
            synchronized (DSqlHelper.class) {
                if (mDsql == null) {
                    mDsql = new DSqlHelper(context);
                }
            }
        }
        return mDsql;
    }

    private static DSqlHelper mDsql;

    /**
     * 创建数据库
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String sqlUser = "CREATE TABLE IF NOT EXISTS  " + TABLE_NAME + "(" + FIELD_ID + " integer primary key autoincrement , " + FIELD_NAME + " text not null ," + FIELD_PSD + " text not null);";
        String sqlTrans = "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTION + "(" + FIELD_ID + " integer primary key autoincrement , "
                + TRANS_MONEY + " integer  , "
                + TRANS_TIME + " integer  , "
                + TRANS_CATEGORY + " text "
                + ");";

        try {
            db.execSQL(sqlUser);
            db.execSQL(sqlTrans);
        } catch (SQLException e) {
            return;
        }
    }

    /**
     * 数据库升级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertUserData(String uName, String psd) {
        //获取写数据库
        SQLiteDatabase db = getWritableDatabase();
        //生成要修改或者插入的键值
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAME, uName);
        cv.put(FIELD_PSD, psd);
        // insert 操作
        db.insert(TABLE_NAME, null, cv);
        //关闭数据库
        db.close();
    }

    public int updateTrans(TransEntity entity){
        //生成条件语句
        StringBuilder sb = new StringBuilder();
        sb.append(" " + FIELD_ID + " = " + entity.getId());
        //生成要修改或者插入的键值
        ContentValues cv = new ContentValues();
        cv.put(TRANS_TIME, System.currentTimeMillis());
        cv.put(TRANS_MONEY, entity.getMoney());
        cv.put(TRANS_CATEGORY, entity.getCategoryName());
        //获取写数据库
        SQLiteDatabase db = getWritableDatabase();
        // update 操作
        int update = db.update(TABLE_TRANSACTION, cv, sb.toString(), null);
        //关闭数据库
        db.close();
        return update;
    }
    public  int deleteTrans(int id){
        //获取写数据库
        SQLiteDatabase db = getWritableDatabase();
        int delete = db.delete(TABLE_TRANSACTION, " " + FIELD_ID + " = " + id, null);
        db.close();
        return delete;
    }
    public void insertTransData(long money, String cName) {
        //获取写数据库
        SQLiteDatabase db = getWritableDatabase();
        //生成要修改或者插入的键值
        ContentValues cv = new ContentValues();
        cv.put(TRANS_TIME, System.currentTimeMillis());
        cv.put(TRANS_CATEGORY, cName);
        cv.put(TRANS_MONEY, money);
        // insert 操作
        db.insert(TABLE_TRANSACTION, null, cv);
        //关闭数据库
        db.close();
    }

    public ArrayList<User> queryUsers() {
        return queryUserData("");
    }

    public User queryUser(String name) {
        ArrayList<User> users = queryUserData(name);
        return users.size() != 0 ? users.get(0) : null;

    }
    public ArrayList<TransEntity> queryTransDatas(){
        //获取可读数据库
        SQLiteDatabase db = getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        ArrayList<TransEntity> transEntities = new ArrayList<>();

        try {
            cursor = db.query(TABLE_TRANSACTION, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int count = cursor.getColumnCount();
                String columName = cursor.getColumnName(0);
                int idIndex = cursor.getColumnIndex(FIELD_ID);
                int categoryIndex = cursor.getColumnIndex(TRANS_CATEGORY);
                int moneyIndex = cursor.getColumnIndex(TRANS_MONEY);
                int timeIndex = cursor.getColumnIndex(TRANS_TIME);
                String tname = cursor.getString(0);
                Log.e(TAG, "count = " + count + " columName = " + columName + "  name =  " + tname);
                TransEntity transEntity = new TransEntity();
                transEntity.setId(cursor.getInt(idIndex));
                transEntity.setCategoryName(cursor.getString(categoryIndex));
                transEntity.setMoney(cursor.getLong(moneyIndex));
                transEntity.setTime(cursor.getLong(timeIndex));
                transEntities.add(transEntity);
            }

        } catch (SQLException e) {
            Log.e(TAG, "queryDatas" + e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            //关闭数据库
            db.close();
        }
        return transEntities;
    }
    public ArrayList<TransEntity> queryTransData(String cName) {
        StringBuilder whereBuffer = new StringBuilder();
        //生成条件语句
        whereBuffer.append(TRANS_CATEGORY).append(" = ").append("'").append(cName).append("'");
        //指定要查询的是哪几列数据
//        String[] columns = {FIELD_NAME};
        //获取可读数据库
        SQLiteDatabase db = getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        ArrayList<TransEntity> transEntities = new ArrayList<>();

        try {
            cursor = db.query(TABLE_TRANSACTION, null, whereBuffer.toString(), null, null, null, null);
            while (cursor.moveToNext()) {
                int count = cursor.getColumnCount();
                String columName = cursor.getColumnName(0);
                int idIndex = cursor.getColumnIndex(FIELD_ID);
                int categoryIndex = cursor.getColumnIndex(TRANS_CATEGORY);
                int moneyIndex = cursor.getColumnIndex(TRANS_MONEY);
                int timeIndex = cursor.getColumnIndex(TRANS_TIME);
                String tname = cursor.getString(0);
                Log.e(TAG, "count = " + count + " columName = " + columName + "  name =  " + tname);
                TransEntity transEntity = new TransEntity();
                transEntity.setId(cursor.getInt(idIndex));
                transEntity.setCategoryName(cursor.getString(categoryIndex));
                transEntity.setMoney(cursor.getLong(moneyIndex));
                transEntity.setTime(cursor.getLong(timeIndex));
                transEntities.add(transEntity);
            }

        } catch (SQLException e) {
            Log.e(TAG, "queryDatas" + e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            //关闭数据库
            db.close();
        }
        return transEntities;
    }

    public ArrayList<TransEntity> queryTransData(long time) {
        StringBuilder whereBuffer = new StringBuilder();
        //生成条件语句
        whereBuffer.append(TRANS_TIME).append(" > ").append(time);
        //指定要查询的是哪几列数据
//        String[] columns = {FIELD_NAME};
        //获取可读数据库
        SQLiteDatabase db = getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        ArrayList<TransEntity> transEntities = new ArrayList<>();

        try {
            cursor = db.query(TABLE_TRANSACTION, null, whereBuffer.toString(), null, null, null, null);
            while (cursor.moveToNext()) {
                int count = cursor.getColumnCount();
                String columName = cursor.getColumnName(0);
                int idIndex = cursor.getColumnIndex(FIELD_ID);
                int categoryIndex = cursor.getColumnIndex(TRANS_CATEGORY);
                int moneyIndex = cursor.getColumnIndex(TRANS_MONEY);
                int timeIndex = cursor.getColumnIndex(TRANS_TIME);
                String tname = cursor.getString(0);
                Log.e(TAG, "count = " + count + " columName = " + columName + "  name =  " + tname);
                TransEntity transEntity = new TransEntity();
                transEntity.setId(cursor.getInt(idIndex));
                transEntity.setCategoryName(cursor.getString(categoryIndex));
                transEntity.setMoney(cursor.getLong(moneyIndex));
                transEntity.setTime(cursor.getLong(timeIndex));
                transEntities.add(transEntity);
            }

        } catch (SQLException e) {
            Log.e(TAG, "queryDatas" + e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            //关闭数据库
            db.close();
        }
        return transEntities;
    }

    public void updateUserData(int id, String name, String psd) {
        //生成条件语句
        StringBuilder sb = new StringBuilder();
//        sb.append(FIELD_NAME).append(" = ").append("'").append(name).append("'");
//        sb.append(FIELD_PSD).append(" = ").append("'").append(psd).append("'");
        sb.append(" " + FIELD_ID + " = " + id);
        //生成要修改或者插入的键值
        ContentValues cv = new ContentValues();
        cv.put(FIELD_NAME, name);
        cv.put(FIELD_PSD, psd);
        //获取写数据库
        SQLiteDatabase db = getWritableDatabase();
        // update 操作
        db.update(TABLE_NAME, cv, sb.toString(), null);
        //关闭数据库
        db.close();
    }

    private ArrayList<User> queryUserData(String name) {
        StringBuilder whereBuffer = new StringBuilder();
        //生成条件语句
        boolean empty = TextUtils.isEmpty(name);
        if (!empty) {

            whereBuffer.append(FIELD_NAME).append(" = ").append("'").append(name).append("'");
        }
        //指定要查询的是哪几列数据
//        String[] columns = {FIELD_NAME,FIELD_ID,FIELD_PSD};
        //获取可读数据库
        SQLiteDatabase db = getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            cursor = db.query(TABLE_NAME, null, empty ? null : whereBuffer.toString(), null, null, null, null);
            while (cursor.moveToNext()) {
                int count = cursor.getColumnCount();
                String columName = cursor.getColumnName(0);
                int nameIndex = cursor.getColumnIndex(FIELD_NAME);
                int psdIndex = cursor.getColumnIndex(FIELD_PSD);
                int idIndex = cursor.getColumnIndex(FIELD_ID);

                String tname = cursor.getString(0);
                Log.e(TAG, "count = " + count + " columName = " + columName + "  name =  " + tname);
                User user = new User();
                user.setId(cursor.getInt(idIndex));
                user.setName(cursor.getString(nameIndex));
                user.setPsd(cursor.getString(psdIndex));
                users.add(user);
            }

        } catch (SQLException e) {
            Log.e(TAG, "queryDatas" + e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            //关闭数据库
            db.close();
        }
        return users;
    }
}
