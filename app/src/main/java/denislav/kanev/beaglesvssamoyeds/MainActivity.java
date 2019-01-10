package denislav.kanev.beaglesvssamoyeds;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Beagles Vs Samoyeds"); // set the top title

        playerChoices[0] = Player.NONE;
        playerChoices[1] = Player.NONE;
        playerChoices[2] = Player.NONE;
        playerChoices[3] = Player.NONE;
        playerChoices[4] = Player.NONE;
        playerChoices[5] = Player.NONE;
        playerChoices[6] = Player.NONE;
        playerChoices[7] = Player.NONE;
        playerChoices[8] = Player.NONE;

    }

    public void imageViewIsTapped(View imageView){

        ImageView tappedImageView = (ImageView)imageView;
        tappedImageView.setTranslationX(-2000);

        int tappedImageTag = Integer.parseInt(tappedImageView.getTag().toString());
        playerChoices[tappedImageTag] = currentPlayer;

        if (currentPlayer == Player.ONE) {

            tappedImageView.setImageResource(R.drawable.beagle);
            currentPlayer = Player.TWO;
        } else if( currentPlayer == Player.TWO) {

            tappedImageView.setImageResource(R.drawable.samoyed);
            currentPlayer = Player.ONE;
        }

        tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
        Toast.makeText(this, tappedImageView.getTag().toString(),Toast.LENGTH_SHORT).show();

        for (int[] winnerColumns : winnerRowsColumns) {
            if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                    && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                    && playerChoices[winnerColumns[0]] != Player.NONE) {

                if (currentPlayer == Player.ONE) {
                    Toast.makeText(this, "Samoyeds win!", Toast.LENGTH_LONG).show();
                } else if (currentPlayer == Player.TWO) {
                    Toast.makeText(this, "Beagles win!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
