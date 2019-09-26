package fadergs.squadmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    private EditText etNome, etNrCamisa;
    private Button btnSave;
    private String acao;
    private int idPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etNome = (EditText) findViewById(R.id.etNome);
        etNrCamisa = (EditText) findViewById(R.id.etNrCamisa);

        btnSave = (Button) findViewById(R.id.btnSave);

        acao = getIntent().getExtras().getString("acao");
        if(acao.equals("editar")){
            idPlayer = getIntent().getExtras().getInt("idPlayer");
            loadForm(idPlayer);
        }
    }

    private void loadForm(int idPlayer) {
        Players players = PlayersDAO.getJogadoresById()
    }
}
