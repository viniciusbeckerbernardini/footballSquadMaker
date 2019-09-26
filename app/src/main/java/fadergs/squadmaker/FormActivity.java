package fadergs.squadmaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save(){

        String name = etNome.getText().toString();

        String numberShirt = etNrCamisa.getText().toString();

        if(name.isEmpty() ){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setIcon(android.R.drawable.ic_dialog_alert);
            alert.setTitle("Anteção!!");
            alert.setMessage("Vo deve Informar o nome do çproduto.");
            alert.setPositiveButton("OK", null);
            alert.show();
        }else{
            Players players = new Players();

            players.setName(name);
            if(numberShirt.isEmpty() ){
                players.setNumberShirt(0);
            }else{
                players.setNumberShirt(Integer.valueOf(numberShirt));
            }
            if(acao.equals(("inserir"))){
                PlayersDAO.insertPlay(this,players);
            }
            if(acao.equals("editar")){
                players.setIdPlayer(idPlayer);
                PlayersDAO.editPlayers(this,players);
            }
            this.finish();
        }

    }



    private void loadForm(int idPlayer) {
        Players players = PlayersDAO.getPlayersById(this, idPlayer);
        etNome.setText(players.getName() );
        etNrCamisa.setText(String.valueOf(players.getNumberShirt() ) );
    }
}
