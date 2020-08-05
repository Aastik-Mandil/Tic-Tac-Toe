package com.game.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity implements View.OnClickListener {
    Button[][] button = new Button[3][3];
    TextView tvp1score,tvp2score;
    Button btnreset;

    int count;
    boolean player1turn=true;
    int p1point=0,p2point=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonid ="btn_"+i+j;
                int resid = getResources().getIdentifier(buttonid,"id",getPackageName());
                button[i][j] = findViewById(resid);
                button[i][j].setOnClickListener(this);
            }
        }
        tvp1score = findViewById(R.id.tvp1score);
        tvp2score = findViewById(R.id.tvp2score);
        btnreset = findViewById(R.id.btnreset);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1point=0;
                p2point=0;
                updatescore();
                resetgame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if(player1turn){
            ((Button) v).setText("X");
        }
        else{
            ((Button) v).setText("O");
        }
        count++;

        if(checkforwin()){
            if(player1turn){
                p1point++;
                Toast.makeText(this, "Player 1 win!", Toast.LENGTH_SHORT).show();
                updatescore();
                resetgame();
            }
            else{
                p2point++;
                Toast.makeText(this, "Player 2 win!", Toast.LENGTH_SHORT).show();
                updatescore();
                resetgame();
            }
        }
        else if(count==9){
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            resetgame();
        }
        else{
            player1turn = !player1turn;
        }

    }

    private void updatescore() {
        tvp1score.setText("Player 1: "+p1point);
        tvp2score.setText("Player 2: "+p2point);
    }

    private void resetgame(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button[i][j].setText("");
            }
        }

        count=0;
        player1turn=true;
    }

    public boolean checkforwin(){
        String[][] field = new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j] = button[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){ return true; }
        }

        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){ return true; }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){ return true; }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){ return true; }

        return false;
    }

}
