package fadergs.squadmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogadoresDAO {

    public class CadastroJogadorDAO {

        public static void inserirJogador (Context contexto, Jogador jogador){

            Banco banco = new Banco(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nome",jogador.getNomejogador());
            valores.put("idtime",jogador.getIdtime());
            db.insert("jogador",null,valores);
        }

        public static void editarJogador(Context contexto , Jogador jogador){
            Banco banco = new Banco(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("nome",jogador.getNomejogador())
            valores.put("idtime",jogador.getIdtime());
            db.update("jogador",valores,"idjogador="+jogador.getIdjogador(),null);
        }


        public static void excluir(Context contexto , int idjogador){
            Banco banco = new Banco(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();
            db.delete("jogador","idjogador="+idjogador,null);


        }

        public static List<Jogador> getjogador(Context contexto){

            List<Jogador> listajogador = new ArrayList<>();

            Banco banco = new Banco(contexto);
            SQLiteDatabase db = banco.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM jogador ORDER BY nome",null);

            if(cursor.getCount()>0){
                cursor.moveToFirst();

                do {
                    Jogador j = new Jogador();
                    j.setIdjogador(cursor.getInt(0));
                    j.setnome(cursor.getString(1));
                    j.setIdtime(cursor.getInt(3));
                    listajogador.add(j);

                }while(cursor.moveToNext());
            }
            return listajogador;

        }

        public static List <Jogadores > getJogadoresById(Context contexto){
            List<Jogadores> listaJogadores = new ArrayList<>();
            Banco banco = new Banco(contexto);
            SQLiteDatabase db = banco.getReadableDatabase();

            String sql = "SELECT * FROM jogador WHERE id = Idjogador " ;
            Cursor cursor = db.rawQuery( sql ,null);

            if ( cursor.getCount() > 0 ){
                cursor.moveToFirst();

                Jogador j = new Jogador();
                j.setId(  cursor.getInt( 0 ) );
                j.setNome( cursor.getString( 1 ) );
                j.setIdTime(cursor.getInt(2));

                return j;
            }else {
                return null;
            }
        }





}
