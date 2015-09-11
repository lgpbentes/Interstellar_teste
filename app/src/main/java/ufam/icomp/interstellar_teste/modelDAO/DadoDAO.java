package ufam.icomp.interstellar_teste.modelDAO;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ufam.icomp.interstellar_teste.ListViewDados;
import ufam.icomp.interstellar_teste.model.Dado;
import ufam.icomp.interstellar_teste.sqlite.BancoDeDados;

/**
 * Created by lgpbentes on 04/09/15.
 */
public class DadoDAO {
    private SQLiteDatabase bancoDeDados;
    public DadoDAO(Context context){
        this.bancoDeDados = (new BancoDeDados(context).getWritableDatabase());
    }

    public boolean insert(Dado dado) {

        String sql = String.format("INSERT INTO Dados (link, data, status) VALUES ('%s', '%s', %s)", dado.getLink(), dado.getData(), dado.getStatus());
        try{
            bancoDeDados.execSQL(sql);
            Log.d("Insercao", "Inserido com sucesso");
            return true;

        } catch (SQLException e){
            Log.d("Insercao", e.getMessage());
            return false;
        }

    }

    public List<Dado> getDados(){
        List<Dado> lista = new ArrayList<>();
        Dado dado;

        String sql = "SELECT * FROM Dados";
        Cursor cursor = bancoDeDados.rawQuery(sql, null);

        while (cursor.moveToNext()){
            dado = new Dado();
            dado.setLink(cursor.getString(0));
            dado.setData(cursor.getString(1));
            dado.setStatus(Integer.valueOf(cursor.getString(2)));

            lista.add(dado);
        }
        return lista;

    }

}
