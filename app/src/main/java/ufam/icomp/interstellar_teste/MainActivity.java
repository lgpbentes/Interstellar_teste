package ufam.icomp.interstellar_teste;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ufam.icomp.interstellar_teste.model.Dado;
import ufam.icomp.interstellar_teste.modelDAO.DadoDAO;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button cadastrar, listar;
    EditText link, data, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadastrar = (Button) findViewById(R.id.buttonCadastrar);
        listar = (Button) findViewById(R.id.buttonListar);

        link = (EditText) findViewById(R.id.editTextLink);
        data = (EditText) findViewById(R.id.editTextData);
        status = (EditText) findViewById(R.id.editTextStatus);

        cadastrar.setOnClickListener(this);
        listar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCadastrar:
                String lnk = link.getText().toString();
                String date = data.getText().toString();
                int st = Integer.parseInt(status.getText().toString());

                Dado dado = new Dado(lnk, date, st);

                DadoDAO dadoDAO = new DadoDAO(getApplicationContext());
                if (dadoDAO.insert(dado)) {
                    Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    limpaCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "Algo deu errado :(", Toast.LENGTH_LONG).show();
                }
            case R.id.buttonListar:
                Intent intent = new Intent(this, Listagem.class);
                startActivity(intent);
                break;


        }
    }

    public void limpaCampos(){

    }
}
