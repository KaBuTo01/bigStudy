package com.example.forum.sql;

import static android.provider.Contacts.SettingsColumns.KEY;

import static com.google.common.net.HttpHeaders.FROM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.forum.bean.User.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MySqlite
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
public class MySqlite extends SQLiteOpenHelper {
    private static final String TABLE_NAME="cwq";
    private static final String ID = "id";
    private static final String COLUMN_ID = ID;
    private static final String COLUMN_RECORD = "record";
    private static final String USER = "User";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String HEAD = "head";

    public MySqlite(@Nullable Context context) {
        super(context, "cwq.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_RECORD + " TEXT);");
        db.execSQL("CREATE TABLE  " + USER + "(" + ID + " INTEGER primary key autoincrement, " + NAME + " varchar(60), " + PASSWORD + " varchar(60)," + HEAD + " varchar(1000))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addUser(List<User> users) {
        ContentValues cv =new ContentValues();
        cv.put(ID,users.get(0).getId());
        cv.put(NAME,users.get(0).getName());
        cv.put(PASSWORD,users.get(0).getPassword());
        cv.put(HEAD,users.get(0).getHeadimg());
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.insert(USER,null,cv);
        sqLiteDatabase.close();
    }
    public List<User> getUser(){
        List<User> list = new ArrayList<>();
        String sql =" SELECT * FROM " + USER;
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        int index=cursor.getColumnIndex(ID);
        int nameIndex=cursor.getColumnIndex(NAME);
        int passwordIndex=cursor.getColumnIndex(PASSWORD);
        int headIndex=cursor.getColumnIndex(HEAD);
        while(cursor.moveToNext()){
            list.add(new User(cursor.getInt(index),cursor.getString(nameIndex),cursor.getString(passwordIndex),cursor.getString(headIndex)));
        }
        sqLiteDatabase.close();
        return list;
    }
    public void add(String record){
        ContentValues cv =new ContentValues();
        cv.put(COLUMN_RECORD,record);
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME,COLUMN_RECORD,cv);
        sqLiteDatabase.close();
    }
    public List<String> getAll(){
        List<String> list = new ArrayList<>();
        String sql =" SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        int index=cursor.getColumnIndex(COLUMN_RECORD);
        while(cursor.moveToNext()){
            list.add(cursor.getString(index));
        }
        sqLiteDatabase.close();

        return list;
    }
    /**
     * 根据name值删除数据（另一种实现方法）
     * @param name
     */
    public void deleteStudent1(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID+"=?", new String[]{name});
    }

}
