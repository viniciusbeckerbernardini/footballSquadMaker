package fadergs.squadmaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fadergs.squadmaker.DAO.PlayersDAO;
import fadergs.squadmaker.DAO.TeamsDAO;
import fadergs.squadmaker.Model.Players;
import fadergs.squadmaker.Model.Team;

public class PlayerFormActivity extends AppCompatActivity {

    private EditText etNome, etNrCamisa;
    private Button btnSave;
    private int idTeam;
    private boolean updatePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_form);

        etNome = (EditText) findViewById(R.id.etNome);
        etNrCamisa = (EditText) findViewById(R.id.etNrCamisa);

        btnSave = (Button) findViewById(R.id.btnSave);


        final Bundle extras = getIntent().getExtras();

        idTeam = extras.getInt("teamID");
        updatePlayer = extras.getBoolean("update");


        if (updatePlayer) {
            idTeam = extras.getInt("teamID");
            etNome.setText(extras.getString("namePlayer"));
            etNrCamisa.setText( String.valueOf( extras.getInt("numberShirt") ));
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(idTeam);
                }
            });
        } else {
            idTeam = extras.getInt("teamID");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    save(extras);
                }
            });
        }
    }

    protected void update(Integer team) {
        String name = etNome.getText().toString();
        String nShirt = etNrCamisa.getText().toString();
        if(name.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setIcon(android.R.drawable.ic_dialog_alert);
            alert.setTitle(getString(R.string.txtAttention));
            alert.setMessage(getString(R.string.txtMessageTeam));
            alert.setPositiveButton("OK",null);
            alert.show();
        }else{
            Players t = new Players();

            t.setIdTeam(idTeam);
            t.setName(name);
            t.setNumberShirt(Integer.parseInt(nShirt));

            PlayersDAO.editPlayers(this,t);

            this.finish();

        }
    }

    private void save(Bundle extras){
      //  int idTeam = extras.getInt("teamID");
        String name = etNome.getText().toString();
        String numberShirt = etNrCamisa.getText().toString();


        if(name.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setIcon(android.R.drawable.ic_dialog_alert);
            alert.setTitle(getString(R.string.txtAttention));
            alert.setMessage(getString(R.string.txtMessagePlayer));
            alert.setPositiveButton("OK",null);
            alert.show();
        }else{
            Players t = new Players();

            t.setIdTeam(idTeam);
            t.setName(name);
            t.setNumberShirt( Integer.valueOf(numberShirt) );
            //t.setIdTeam(1);
            PlayersDAO.insertPlay(this,t);

            this.finish();
        }
    }

}