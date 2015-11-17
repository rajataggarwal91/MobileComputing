package com.example.rajat.secondapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.database.Cursor;
import android.content.Context;

/**
 * Created by Bhargavi on 13-11-2015.
 */
public class DatabaseOperations extends SQLiteOpenHelper{

    public SQLiteDatabase TrackMeDatabase;
    public static final String COMMA_SEPARATOR = ",";
    public static final String COLUMN_TYPE_TEXT = "TEXT";
    public static final String COLUMN_TYPE_INTEGER_ID = "INTEGER PRIMARY KEY";
    public static final String COLUMN_TYPE_INTEGER_NON_KEY = "INTEGER";
    public static final String COLUMN_TYPE_DOUBLE = "DOUBLE";
    public static final String DATABASE_NAME = "TrackMe";

    public DatabaseOperations(Context Con){
        super(Con,DATABASE_NAME,null,35);
    }



    public static abstract class Profile implements BaseColumns
    {
        public static final String TABLE_NAME = "Profile";
        public static final String COLUMN_NAME_PROFILE_ID = "ProfileID";
        public static final String COLUMN_NAME_PROFILE_NAME = "ProfileName";
        public static final String COLUMN_NAME_OPERATION_ID = "OperationID";
        public static final String COLUMN_NAME_STATUS = "Status";
        public static final String CREATE_TABLE_TEXT = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(" +COLUMN_NAME_PROFILE_ID+" "+COLUMN_TYPE_INTEGER_ID+COMMA_SEPARATOR+COLUMN_NAME_PROFILE_NAME+" "+COLUMN_TYPE_TEXT+COMMA_SEPARATOR+COLUMN_NAME_OPERATION_ID+" "+COLUMN_TYPE_INTEGER_NON_KEY+COMMA_SEPARATOR+COLUMN_NAME_STATUS+" "+COLUMN_TYPE_TEXT+");";

    }

    public static abstract class Operations implements BaseColumns
    {
        public static final String TABLE_NAME = "Operations";
        public static final String COLUMN_NAME_OPERATIONS_ID = "OperationsID";
        public static final String COLUMN_NAME_LOCATION = "LocationID";
        public static final String COLUMN_NAME_TIME = "Time";
        public static final String COLUMN_NAME_CALENDAR = "Calendar";
        public static final String COLUMN_NAME_AMBIENT_BRIGHTNESS = "AmbientBrightness";
        public static final String COLUMN_NAME_ENTITY = "Entity";
        public static final String COLUMN_NAME_ACTION = "Action";

        public static final String CREATE_TABLE_TEXT = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(" + COLUMN_NAME_OPERATIONS_ID+" "+COLUMN_TYPE_INTEGER_ID+COMMA_SEPARATOR+COLUMN_NAME_LOCATION+" "+COLUMN_TYPE_INTEGER_NON_KEY+COMMA_SEPARATOR+COLUMN_NAME_TIME+" "+COLUMN_TYPE_TEXT+COMMA_SEPARATOR+COLUMN_NAME_CALENDAR+" "+COLUMN_TYPE_TEXT+COMMA_SEPARATOR+COLUMN_NAME_AMBIENT_BRIGHTNESS+" "+COLUMN_TYPE_TEXT+COMMA_SEPARATOR+COLUMN_NAME_ENTITY+" "+COLUMN_TYPE_TEXT+COMMA_SEPARATOR+COLUMN_NAME_ACTION+" "+COLUMN_TYPE_TEXT+ ");";
    }


    public static abstract class Location implements BaseColumns
    {
        public static final String TABLE_NAME = "Location";
        public static final String COLUMN_NAME_LOCATION_ID = "LocationID";
        public static final String COLUMN_NAME_LOCATION_NAME = "LocationName";
        public static final String COLUMN_NAME_LATITUDE = "Latitude";
        public static final String COLUMN_NAME_LONGITUDE = "Longitude";
        public static final String CREATE_TABLE_TEXT = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+COLUMN_NAME_LOCATION_ID+" "+COLUMN_TYPE_INTEGER_ID+COMMA_SEPARATOR+COLUMN_NAME_LOCATION_NAME+" "+COLUMN_TYPE_TEXT+COMMA_SEPARATOR+COLUMN_NAME_LATITUDE+" "+COLUMN_TYPE_DOUBLE+COMMA_SEPARATOR+COLUMN_NAME_LONGITUDE+" "+COLUMN_TYPE_DOUBLE+");";

    }
    @Override
    public void onCreate(SQLiteDatabase DB)
    {
        DB.execSQL(Profile.CREATE_TABLE_TEXT);
        DB.execSQL(Operations.CREATE_TABLE_TEXT);
        DB.execSQL(Location.CREATE_TABLE_TEXT);
        System.out.println("On Create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB,int oldVersion, int newVersion)
    {
        DB.execSQL("DROP TABLE Profile;");
        onCreate(DB);
    }


    public Cursor retrieveProfileInformation()
    {
        TrackMeDatabase = getReadableDatabase();
        String[] Projection = {Profile.COLUMN_NAME_PROFILE_ID,Profile.COLUMN_NAME_PROFILE_NAME,Profile.COLUMN_NAME_OPERATION_ID,Profile.COLUMN_NAME_STATUS};
        Cursor c;
        c = TrackMeDatabase.query(Profile.TABLE_NAME, Projection, null, null, null, null, null);
        return c;
    }
}
