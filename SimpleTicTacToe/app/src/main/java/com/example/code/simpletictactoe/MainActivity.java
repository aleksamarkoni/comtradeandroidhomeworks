package com.example.code.simpletictactoe;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private int buttonIds[] = {
            R.id.b_1_1, R.id.b_1_2, R.id.b_1_3,
            R.id.b_2_1, R.id.b_2_2, R.id.b_2_3,
            R.id.b_3_1, R.id.b_3_2, R.id.b_3_3
    };
    private Button buttonViews[] = new Button[9];
    private ButtonState[][] buttonStates = {
            {ButtonState.EMPTY, ButtonState.EMPTY, ButtonState.EMPTY},
            {ButtonState.EMPTY, ButtonState.EMPTY, ButtonState.EMPTY},
            {ButtonState.EMPTY, ButtonState.EMPTY, ButtonState.EMPTY}
    };

    private Player currentPlayer = Player.OKS_PLAYER;
    private GameType gameType = GameType.VS_COMPUTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            buttonViews[i] = button;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonClick(view);
                }
            });
        }
        restartTable();
    }

    private void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.b_1_1:
                ispisiToast("prvi red, prvo dugme");
                odigraPotez(0, 0);
                break;
            case R.id.b_1_2:
                ispisiToast("prvi red, drugo dugme");
                odigraPotez(0, 1);
                break;
            case R.id.b_1_3:
                ispisiToast("prvi red, trece dugme");
                odigraPotez(0, 2);
                break;
            case R.id.b_2_1:
                ispisiToast("drugi red, prvo dugme");
                odigraPotez(1, 0);
                break;
            case R.id.b_2_2:
                ispisiToast("drugi red, drugo dugme");
                odigraPotez(1, 1);
                break;
            case R.id.b_2_3:
                ispisiToast("drugi red, trece dugme");
                odigraPotez(1, 2);
                break;
            case R.id.b_3_1:
                ispisiToast("treci red, prvo dugme");
                odigraPotez(2, 0);
                break;
            case R.id.b_3_2:
                ispisiToast("treci red, drugo dugme");
                odigraPotez(2, 1);
                break;
            case R.id.b_3_3:
                ispisiToast("treci red, trece dugme");
                odigraPotez(2, 2);
                break;
        }
    }

    private void odigraPotez(int row, int col) {
        switch (buttonStates[row][col]) {
            case EMPTY:
                switch (currentPlayer) {
                    case IKS_PLAYER:
                        buttonViews[row * 3 + col].setBackgroundResource(R.drawable.iks_background);
                        buttonStates[row][col] = ButtonState.IKS;
                        currentPlayer = Player.OKS_PLAYER;
                        if (checkForWinner() != Winner.NO_WINNER) {
                            restartTable();
                            return;
                        }

                        if (gameType == GameType.VS_COMPUTER) {
                            computerMove(ButtonState.OKS, R.drawable.oks_background);
                            currentPlayer = Player.IKS_PLAYER;
                        }
                        break;
                    case OKS_PLAYER:
                        buttonViews[row * 3 + col].setBackgroundResource(R.drawable.oks_background);
                        buttonStates[row][col] = ButtonState.OKS;
                        currentPlayer = Player.IKS_PLAYER;
                        if (checkForWinner() != Winner.NO_WINNER) {
                            restartTable();
                            return;
                        }
                        if (gameType == GameType.VS_COMPUTER) {
                            computerMove(ButtonState.IKS, R.drawable.iks_background);
                            currentPlayer = Player.OKS_PLAYER;
                        }
                        break;
                }
                if (checkForWinner() != Winner.NO_WINNER) {
                    restartTable();
                }
                break;
//            case IKS:
//                button.setBackgroundResource(R.drawable.oks_background);
//                buttonStates[row][col] = ButtonState.OKS;
//                break;
//            case OKS:
//                button.setBackgroundColor(getResources().getColor(R.color.white));
//                buttonStates[row][col] = ButtonState.EMPTY;
//                break;

        }
    }

    private void computerMove(ButtonState state, int drawable) {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);
            if (buttonStates[x][y] == ButtonState.EMPTY) {
                buttonStates[x][y] = state;
                buttonViews[x * 3 + y].setBackgroundResource(drawable);
                break;
            }
        }
    }

    private Winner checkForWinner() {

        for (int j = 0; j < 3; j++) {
            int brX = 0;
            int brO = 0;
            for (int i = 0; i <= 2; i++) {
                if (buttonStates[j][i] == ButtonState.IKS) {
                    brX++;
                }
                if (buttonStates[j][i] == ButtonState.OKS) {
                    brO++;
                }
            }
            if (brX == 3) {
                ispisiToast("X je pobednik");
                return Winner.IKS;
            }
            if (brO == 3) {
                ispisiToast("O je pobednik");
                return Winner.OKS;
            }
        } //kraj reda
        for (int i = 0; i <= 2; i++) {
            int brX = 0;
            int brO = 0;
            for (int j = 0; j < 3; j++) {
                if (buttonStates[j][i] == ButtonState.IKS) {
                    brX++;
                }
                if (buttonStates[j][i] == ButtonState.OKS) {
                    brO++;
                }
            }
            if (brX == 3) {
                ispisiToast("X je pobednik");
                return Winner.IKS;
            }
            if (brO == 3) {
                ispisiToast("O je pobednik");
                return Winner.OKS;
            }
        } //kraj kolone
//        for (int i = 0; i < 3; i++) {
//            boolean imaTriX = true;
//            boolean imaTriO = true;
//            for (int j = 0; j < 3; j++) {
//                if (buttonStates[i][j] != ButtonState.IKS) {
//                    imaTriX = false;
//                }
//                if (buttonStates[i][j] != ButtonState.OKS) {
//                    imaTriO = false;
//                }
//            }
//            if (imaTriX || imaTriO) {
//                ispisiToast("Imam pobednika");
//            }
//        }
        int brX = 0;
        int brO = 0;
        for (int i = 0; i < 3; i++) {
            if (buttonStates[i][i] == ButtonState.IKS) {
                brX++;
            }
            if (buttonStates[i][i] == ButtonState.OKS) {
                brO++;
            }
        }
        if (brX == 3) {
            ispisiToast("X je pobednik");
            return Winner.IKS;
        }
        if (brO == 3) {
            ispisiToast("O je pobednik");
            return Winner.OKS;
        }
        brX = 0;
        brO = 0;
        for (int i = 0; i < 3; i++) {
            if (buttonStates[i][2-i] == ButtonState.IKS) {
                brX++;
            }
            if (buttonStates[i][2-i] == ButtonState.OKS) {
                brO++;
            }
        }
        if (brX == 3) {
            ispisiToast("X je pobednik");
            return Winner.IKS;
        }
        if (brO == 3) {
            ispisiToast("O je pobednik");
            return Winner.OKS;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonStates[i][j] == ButtonState.EMPTY) {
                    ispisiToast("Jos uvek nema pobednika");
                    return Winner.NO_WINNER;
                }
            }
        }
        //e pa ovde je draw
        ispisiToast("Nema pobednika");
        return Winner.DRAW;
    }

    private void ispisiToast(String s) {
        Toast.makeText(MainActivity.this,
                s,
                Toast.LENGTH_SHORT)
                .show();
    }

    private void restartTable() {
        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonStates[i][j] = ButtonState.EMPTY;
            }
        }
    }

    public enum ButtonState {EMPTY, IKS, OKS}

    public enum Player {IKS_PLAYER, OKS_PLAYER}

    public enum GameType {VS_HUMMAN, VS_COMPUTER}

    public enum Winner {IKS, OKS, NO_WINNER, DRAW}
}
