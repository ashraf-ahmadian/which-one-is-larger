package com.example.whichoneislargerwithfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button scoreButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        clickStartGame();
        clickBestScore();
    }
    private void findView(){
        startButton = findViewById(R.id.game_page_btn);
        scoreButton = findViewById(R.id.score_page_btn);
    }
    private void clickStartGame() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUserNameDialog dialog = new GetUserNameDialog(MainActivity.this,
                        new PlayerNameListener() {
                            @Override
                            public void getPlayerName(String playerName) {
                                Bundle bundle = new Bundle();
                                bundle.putString("player_name", playerName);
                                GameFragment gameFragment = new GameFragment();
                                gameFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.fragment_container, gameFragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        });
                dialog.show();
            }
        });
    }
    private void clickBestScore(){
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BestScoreFragment bestScoreFragment = new BestScoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, bestScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }




}
