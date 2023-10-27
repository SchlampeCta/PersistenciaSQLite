package uni.pilotoco.myapplication;


import android.os.Bundle;
import androidx.annotation.Nullable;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DataSQLite extends SQLiteOpenHelper{

    private static final String DB_NAME = "baseDatos.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME= "reporte_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USUARIO";
    private static final String COL_3 = "FECHA";
    private static final String COL_4  = "DESCRIPCION";



    public DataSQLite(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table reporte_table(ID INTEGER PRIMARY KEY AUTOINCREMENT,USUARIO,FECHA,DESCRIPCION,TIPO)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+ db);
        onCreate(db);

    }


         public void initData(){
        SQLiteDatabase db= this.getWritableDatabase();
        onUpgrade(db,1,1);
    }

    //consultar datos

        public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from reporte_table",null);
        return result;
    }

    public Cursor getData(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor rest=db.rawQuery("select * from"+db+"where id="+id+"",null);
        return rest;
    }

    //insertar datos
    public boolean insertData(String usuario, String fecha, String descripcion){
        SQLiteDatabase db= this.getWritableDatabase();  //instancia BD
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2, usuario);
        contentValues.put(COL_3, fecha);
        contentValues.put(COL_4, descripcion);

        long resultado = db.insert(TABLE_NAME, null,contentValues);
        if (resultado == -1){
            return false;
        }
         return true;
    }
}
