package com.juliusyam.mobileinteractionappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDeck extends AppCompatActivity {

    private TextView card_result;

    private Random random = new Random();

    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_deck);

        card_result = findViewById(R.id.card_result);

        String[] cardType = {"Clubs", "Spades", "Diamonds", "Hearts"};
        String[] cardValue = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (int cardTypeAmount = 0; cardTypeAmount < cardType.length; cardTypeAmount++) {
            for (int cardValueAmount = 0; cardValueAmount <  cardValue.length; cardValueAmount++) {
                list.add(cardValue[cardValueAmount] + " of " + cardType[cardTypeAmount]);
            }
        }

//        list.add("4 of hearts");
//        list.add("5 of diamonds");
//        list.add("Q of spade");
    }

    public void getRandomCard(View view) {

        int randomCard = random.nextInt(list.size());
        card_result.setText(list.get(randomCard));
        list.remove(randomCard);

        if (list.size() == 0) {
            card_result.setText("No More Cards!");
            //list.add(randomCard);
//            Intent intent = new Intent (this, CardDeck. class);
//            startActivity(intent);
        }
    }

    public void homePressed(View view) {
        Intent intent = new Intent (this, MainActivity. class);
        startActivity(intent);
    }
    public void dicePressed(View view) {
        Intent intent = new Intent (this, DiceRoller. class);
        startActivity(intent);
    }
    public void deckPressed(View view) {
        Intent intent = new Intent (this, CardDeck. class);
        startActivity(intent);
    }
}