package com.example.code.simpletictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

    private void ispisiToast(String s) {
        Toast.makeText(MainActivity.this,
                s,
                Toast.LENGTH_SHORT)
                .show();
    }

    public enum ButtonState {EMPTY, IKS, OKS}

    public enum Player {IKS_PLAYER, OKS_PLAYER}

}
