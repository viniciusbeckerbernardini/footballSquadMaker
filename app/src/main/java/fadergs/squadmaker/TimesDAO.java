package fadergs.squadmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TimesDAO {

    public static void inserirTime(Context contexto , Team time){
        DBTeams banco = new DBTeams(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nome",time.getNome());

        db.insert("time",null,valores);
    }


    public static void editartime(Context contexto , Teams time){
        DBTeams banco = new DBTeams(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put("nome",time.getNome());
        db.update("time",valores,"id="+time.getId(),null);

    }

    public static void excluirTime(Context contexto, int idtime){
        DBTeams banco = new DBTeams(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.delete("time","id="+idtime,null);

    }

    public static List<Teams> getTime(Context contexto){

        List<Teams> listaTimes = new ArrayList<>();

        DBTeams banco = new DBTeams(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM time ORDER BY nome",null);

        if(cursor.getCount()>0){
            cursor.moveToFirst();

            do {
                Teams t = new Teams();
                t.setId(cursor.getInt(0));
                t.setNome(cursor.getString(1));
                lista.add(t);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public static Teams getTimeByID(Context contexto , int idtime){
        DBTeams banco = new DBTeams(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        Cursor cursor = db.rawQuery( "SELECT * FROM time WHERE id = " + idtime ,null);

        if(cursor.getCount()>0){

            cursor.moveToFirst();

            Teams t = new Teams();

            t.setId(cursor.getInt(0));
            t.setNome(cursor.getString(1));

            return t;
        }else{

            return null;
        }

    }

}
