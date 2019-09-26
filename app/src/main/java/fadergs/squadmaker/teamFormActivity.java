package fadergs.squadmaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class teamFormActivity extends AppCompatActivity {

    private EditText ETTeamName;
    private Button btnCreateTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_form);

        ETTeamName = (EditText) findViewById(R.id.ETTeamName);
        btnCreateTeam = (Button) findViewById(R.id.btnCreateTeam);

        btnCreateTeam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                save();
            }
        });
    }

    private void save(){
        String name = ETTeamName.getText().toString();

        if(name.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setIcon(android.R.drawable.ic_dialog_alert);
            alert.setTitle("Atenção, vacilão");
            alert.setMessage("Preenche o time aí, tá de sacanagem?");
            alert.setPositiveButton("OK",null);
            alert.show();
        }else{
            Team t = new Team();

            t.setName(name);
            Toast.makeText(this, t.getName(), Toast.LENGTH_SHORT).show();

            TeamsDAO.insertTeam(this,t);

            this.finish();
        }
    }
}
