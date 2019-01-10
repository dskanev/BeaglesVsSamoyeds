package denislav.kanev.beaglesvssamoyeds;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import denislav.kanev.lionortiger.R;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, NONE
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];
    int[][] winnerRowsColumns = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8}, {2,4,6}};

    private boolean gameOver = false;
    private Button btnRestart;
    private android.support.v7.widget.GridLayout gridLayout;
    private Toast mToast;
    private int dogsCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Beagles Vs Samoyeds"); // set the top title

        resetChoices();

        btnRestart = findViewById(R.id.btnRestart);
        gridLayout = findViewById(R.id.gridLayout);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

    }

    public void imageViewIsTapped(View imageView){

        ImageView tappedImageView = (ImageView)imageView;

        int tappedImageTag = Integer.parseInt(tappedImageView.getTag().toString());
        if (playerChoices[tappedImageTag] == Player.NONE && gameOver == false) {

            tappedImageView.setTranslationX(-2000);

            playerChoices[tappedImageTag] = currentPlayer;
            dogsCounter ++;
            if (dogsCounter == 9) {
                btnRestart.setVisibility(View.VISIBLE);
            }

            if (currentPlayer == Player.ONE) {

                tappedImageView.setImageResource(R.drawable.beagle);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {

                tappedImageView.setImageResource(R.drawable.samoyed);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

            if (mToast != null) mToast.cancel();
            mToast = Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT);
            mToast.show();


            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.NONE) {

                    btnRestart.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winner = "";

                    if (currentPlayer == Player.ONE) {
                        winner = "Samoyeds";
                    } else if (currentPlayer == Player.TWO) {
                        winner = "Beagles";
                    }
                    if (mToast != null) mToast.cancel();
                    mToast = Toast.makeText(this, winner + " win!", Toast.LENGTH_LONG);
                    mToast.show();
                }
            }
        }
    }

    // Restart game functionality
    private void restartGame() {
        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView imageView = (ImageView)gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);

        }

        currentPlayer = Player.ONE;
        resetChoices();
        gameOver = false;
        btnRestart.setVisibility(View.GONE);

    }

    private void resetChoices() {
        for(int i = 0; i < playerChoices.length; i++){
            playerChoices[i] = Player.NONE;
        }
    }
}
