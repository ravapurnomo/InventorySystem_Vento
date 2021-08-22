package com.rava.ventoapp.version2;

 import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 import android.widget.Toast;
import androidx.annotation.Nullable;

class  MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Vento.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Vento";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "nama_barang";
    private static final String COLUMN_QUANTITY = "jumlah_barang";
    private static final String COLUMN_DESC = "ket_barang";

    private static final String TABLE_NAME_HISTORY = "VentoHst";
    private static final String COLUMN_ID_HISTORY = "_id";
    private static final String COLUMN_DESC_HISTORY = "ket_barang_history";
    private static final String COLUMN_DATE_HISTORY = "tgl_barang_history";
    private static final String COLUMN_QUANTITY_HISTORY = "update_jumlah_history";
    private static final String DROPDOWN_HISTORY = "dropdown_history";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_QUANTITY + " INTEGER, " +
                        COLUMN_DESC + " TEXT);";
        db.execSQL(query);

        String query_history = "CREATE TABLE " + TABLE_NAME_HISTORY +
                " (" + COLUMN_ID_HISTORY + " INTEGER, " +
                COLUMN_DESC_HISTORY + " TEXT, " +
                COLUMN_DATE_HISTORY + " TEXT, " +
                COLUMN_QUANTITY_HISTORY + " INTEGER, " +
                DROPDOWN_HISTORY + " TEXT);";
        db.execSQL(query_history);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HISTORY );
        onCreate(db);
    }

    void addBarang(String nama_barang, int jumlah_barang, String keterangan_input){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, nama_barang);
        cv.put(COLUMN_QUANTITY, jumlah_barang);
        cv.put(COLUMN_DESC, keterangan_input);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sukses!", Toast.LENGTH_SHORT).show();
        }
    }
    //tambahan 2
    void addBarangHistory(int id_history, int jumlah_barang_history, String keterangan_history, String tanggal_history, String proses_history){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_HISTORY, id_history);
        cv.put(COLUMN_DESC_HISTORY, keterangan_history);
        cv.put(COLUMN_QUANTITY_HISTORY, jumlah_barang_history);
        cv.put(COLUMN_DATE_HISTORY, tanggal_history);
        cv.put(DROPDOWN_HISTORY, proses_history);

        long result = db.insert(TABLE_NAME_HISTORY,null, cv);
        if(result == -1){
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sukses!", Toast.LENGTH_SHORT).show();
        }
    }

    void updateBarang(int _id, String ket_barang_history, String tgl_barang_history, int update_jumlah_history, String dropdown){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_HISTORY, _id);
        cv.put(COLUMN_DESC_HISTORY, ket_barang_history);
        cv.put(COLUMN_DATE_HISTORY, tgl_barang_history);
        cv.put(COLUMN_QUANTITY_HISTORY, update_jumlah_history);
        cv.put(DROPDOWN_HISTORY, dropdown);
        long result = db.insert(TABLE_NAME_HISTORY,null, cv);
        if(result == -1){
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sukses!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataHistory(){
        String query = "SELECT ket_barang_history FROM " + TABLE_NAME_HISTORY;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String nama_barang, String jumlah_barang, String ket_barang){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, nama_barang);
        cv.put(COLUMN_QUANTITY, jumlah_barang);
        cv.put(COLUMN_DESC, ket_barang);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sukses Diperbarui!", Toast.LENGTH_SHORT).show();
        }

    }
    //tambahan 3
    Cursor selectID(String nama_barang){
        String query = "SELECT _id FROM Vento WHERE nama_barang = '" +nama_barang+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Gagal Dihapus", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Sukses dihapus!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
