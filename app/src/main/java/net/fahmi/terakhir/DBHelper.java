package net.fahmi.terakhir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "login";
    public static final String table_name = "user";

    public static final String row_id = "_id";
    public static final String row_username = "username";
    public static final String row_password  = "password";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+table_name+" ("+row_id+" integer primary key  autoincrement," +
                row_username+" varchar,"+row_password+" varchar)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table_name);
    }
    public void insertData(ContentValues values){
        db.insert(table_name,null, values);
    }
    public boolean checkUser(String username, String password){
        String[] columns = {row_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_username + "=? and "+row_password+"=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(table_name,columns,selection,selectionArgs,null,null,null);
        int count  = cursor.getCount();
        cursor.close();
        db.close();
        if(count > 0)
            return  true;
        else
            return false;
    }
}
