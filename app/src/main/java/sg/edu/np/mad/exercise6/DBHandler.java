package sg.edu.np.mad.exercise6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "accountsDB.db";
    public static String USER = "User"; //table name
    public static String COLUMN_NAME = "name";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FOLLOWED = "followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_DATABASE = "CREATE TABLE " + USER + "(" + COLUMN_NAME
                + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_ID + " TEXT, " + COLUMN_FOLLOWED + " TEXT" + ")";
        db.execSQL(CREATE_DATABASE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        onCreate(db);
    }

    public ArrayList<User> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> userArrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + USER, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            String desc = cursor.getString(1);
            int id = cursor.getInt(2);
            String followed = cursor.getString(3);
            User newUserEntry = new User(name, desc, id, Boolean.parseBoolean(followed));
            userArrayList.add(newUserEntry);
        }
        db.close();
        return userArrayList;
    }

    public User findUser(String name){
        String query = "SELECT * FROM " + USER + " WHERE " + COLUMN_NAME + "=\"" + name + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User queryData = new User();

        if(cursor.moveToFirst()){
            queryData.setName(cursor.getString(0));
            queryData.setDescription(cursor.getString(1));
            cursor.close();
        }
        else {
            queryData = null;
        }
        db.close();
        return queryData;
    }

    public void addUser(User userData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, userData.getName());
        values.put(COLUMN_DESCRIPTION, userData.getDescription());
        values.put(COLUMN_ID, userData.getID());
        values.put(COLUMN_FOLLOWED, userData.getFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USER, null, values);
        db.close();
    }

    public void updateUser(User userData){
        SQLiteDatabase db = this.getWritableDatabase();
        String changeFollowing = "UPDATE " + USER + " SET " + COLUMN_FOLLOWED + " = " + "\""+ userData.getFollowed() + "\""  + " WHERE " + COLUMN_NAME + " = " + "\""+ userData.getName()+ "\"";
        db.execSQL(changeFollowing);
        db.close();
    }


}
