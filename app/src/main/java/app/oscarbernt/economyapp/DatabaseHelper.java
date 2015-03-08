package app.oscarbernt.economyapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Tabel names
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_PURCHASE = "purchase";

    //Common column names
    public static final String COLUMN_ID = "_id";

    //Categories column names
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CURRVAL = "currval";
    public static final String COLUMN_LIMIT = "lmt";

    //Purchase column names
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";


    private static final String DATABASE_NAME = "ecoapp.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_CATEGORIES = "CREATE TABLE "
            + TABLE_CATEGORIES + " ( " + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME
            + " TEXT NOT NULL, " + COLUMN_CURRVAL + " REAL, " + COLUMN_LIMIT + " REAL )";

    private static final String DATABASE_CREATE_PURCHASE = "create table "
            + TABLE_PURCHASE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_DESC
            + " text not null, " + COLUMN_COST + " double, " + COLUMN_CATEGORY
            + " text not null, " + COLUMN_DATE + " date);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tabels
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_CATEGORIES);
        database.execSQL(DATABASE_CREATE_PURCHASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PURCHASE);
        onCreate(db);
    }

    public void createCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, category.getName());
        values.put(COLUMN_LIMIT, category.getLimit());
        values.put(COLUMN_CURRVAL, category.getCurrval());

        db.insert(TABLE_CATEGORIES, null, values);
    }

    public void createPurchase(Purchase purchase){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, purchase.getDesc());
        values.put(COLUMN_LIMIT, purchase.getCost());
        values.put(COLUMN_CURRVAL, purchase.getCategory());
        values.put(COLUMN_CURRVAL, purchase.getDate());

        db.insert(TABLE_PURCHASE, null, values);
    }

    public List<String> getCategories(){

        List<String> categories = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()){

            do{
                Category category = new Category();
                category.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                category.setCurrval(c.getDouble(c.getColumnIndex(COLUMN_CURRVAL)));
                category.setLimit(c.getDouble(c.getColumnIndex(COLUMN_LIMIT)));

                categories.add(c.getString(1));
            }while(c.moveToNext());
        }

        return categories;
    }

    public List<Purchase> getPurchase(){

        List<Purchase> purchases = new ArrayList<Purchase>();

        String selectQuery = "SELECT * FROM " + TABLE_PURCHASE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()){

            do{
                Purchase purchase = new Purchase();
                purchase.setDesc(c.getString(c.getColumnIndex(COLUMN_DESC)));
                purchase.setCategory(c.getString(c.getColumnIndex(COLUMN_CATEGORY)));
                purchase.setCost(c.getDouble(c.getColumnIndex(COLUMN_COST)));
                purchase.setDate(c.getString(c.getColumnIndex(COLUMN_DATE)));

                purchases.add(purchase);
            }while(c.moveToNext());
        }

        return purchases;
    }

    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, category.getName());
        values.put(COLUMN_LIMIT, category.getLimit());
        values.put(COLUMN_CURRVAL, category.getCurrval());

        // updating row
        return db.update(TABLE_CATEGORIES, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
    }

    public int updatePurchase(Purchase purchase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESC, purchase.getDesc());
        values.put(COLUMN_COST, purchase.getCost());
        values.put(COLUMN_CATEGORY, purchase.getCategory());
        values.put(COLUMN_DATE, purchase.getDate());

        // updating row
        return db.update(TABLE_PURCHASE, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(purchase.getId()) });
    }

    public void deleteCategory(long category_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORIES, COLUMN_ID + " = ?",
                new String[] { String.valueOf(category_id) });
    }

    public void deletePurcase(long purchase_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PURCHASE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(purchase_id) });
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }



}
