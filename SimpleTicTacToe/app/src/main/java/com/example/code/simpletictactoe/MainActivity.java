package com.example.code.simpletictactoe;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private int buttons[] = {
            R.id.b_1_1, R.id.b_1_2, R.id.b_1_3,
            R.id.b_2_1, R.id.b_2_2, R.id.b_2_3,
            R.id.b_3_1, R.id.b_3_2, R.id.b_3_3
    };
    private ButtonState[][] buttonStates = {
            {ButtonState.EMPTY, ButtonState.EMPTY, ButtonState.EMPTY},
            {ButtonState.EMPTY, ButtonState.EMPTY, ButtonState.EMPTY},
            {ButtonState.EMPTY, ButtonState.EMPTY, ButtonState.EMPTY}
    };

    private Player currentPlayer = Player.IKS_PLAYER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < buttons.length; i++) {
            Button button = findViewById(buttons[i]);
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
                odigraPotez(view, 0, 0);
                break;
            case R.id.b_1_2:
                ispisiToast("prvi red, drugo dugme");
                odigraPotez(view, 0, 1);
                break;
            case R.id.b_1_3:
                ispisiToast("prvi red, trece dugme");
                odigraPotez(view, 0, 2);
                break;
            case R.id.b_2_1:
                ispisiToast("drugi red, prvo dugme");
                odigraPotez(view, 1, 0);
                break;
            case R.id.b_2_2:
                ispisiToast("drugi red, drugo dugme");
                odigraPotez(view, 1, 1);
                break;
            case R.id.b_2_3:
                ispisiToast("drugi red, trece dugme");
                odigraPotez(view, 1, 2);
                break;
            case R.id.b_3_1:
                ispisiToast("treci red, prvo dugme");
                odigraPotez(view, 2, 0);
                break;
            case R.id.b_3_2:
                ispisiToast("treci red, drugo dugme");
                odigraPotez(view, 2, 1);
                break;
            case R.id.b_3_3:
                ispisiToast("treci red, trece dugme");
                odigraPotez(view, 2, 2);
                break;
        }
    }

    private void odigraPotez(View button, int row, int col) {
        switch (buttonStates[row][col]) {
            case EMPTY:
                switch (currentPlayer) {
                    case IKS_PLAYER:
                        button.setBackgroundResource(R.drawable.iks_background);
                        buttonStates[row][col] = ButtonState.IKS;
                        currentPlayer = Player.OKS_PLAYER;
                        break;
                    case OKS_PLAYER:
                        button.setBackgroundResource(R.drawable.oks_background);
                        buttonStates[row][col] = ButtonState.OKS;
                        currentPlayer = Player.IKS_PLAYER;
                        break;
                }
                if (checkForWinner()) {
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

    private boolean checkForWinner() {

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
                return true;
            }
            if (brO == 3) {
                ispisiToast("O je pobednik");
                return true;
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
                return true;
            }
            if (brO == 3) {
                ispisiToast("O je pobednik");
                return true;
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
            return true;
        }
        if (brO == 3) {
            ispisiToast("O je pobednik");
            return true;
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
            return true;
        }
        if (brO == 3) {
            ispisiToast("O je pobednik");
            return true;
        }
        ispisiToast("Jos uvek nema pobednika");
        return false;
    }

    private void ispisiToast(String s) {
        Toast.makeText(MainActivity.this,
                s,
                Toast.LENGTH_SHORT)
                .show();
    }

    private void restartTable() {
        for (int i = 0; i < buttons.length; i++) {
            Button button = findViewById(buttons[i]);
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

}
