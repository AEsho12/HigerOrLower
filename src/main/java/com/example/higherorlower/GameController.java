package com.example.higherorlower;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {

    @FXML
    private Label cardLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label streakLabel;

    @FXML
    private Button higherButton;

    @FXML
    private Button lowerButton;

    @FXML
    private ImageView cardImageView;

    private List<Integer> deck;
    private int currentCard;
    private int points = 0;
    private int streak = 0;

    @FXML
    private void initialize() {
        initializeDeck();
        shuffleDeck();
        drawInitialCard();
        updateUI();
    }

    private void initializeDeck() {
        deck = new ArrayList<>();
        for (int i = 2; i <= 14; i++) {
            deck.add(i);
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void drawInitialCard() {
        if (!deck.isEmpty()) {
            currentCard = deck.remove(0);
        } else {
            endGame();
        }
        loadCardImage(currentCard);
    }

    private void loadCardImage(int cardValue) {
        String imagePath = getCardImagePath(cardValue);
        System.out.println("Loading image from: " + imagePath);
        InputStream imageStream = getClass().getResourceAsStream(imagePath);

        if (imageStream == null) {
            System.out.println("Image not found for card: " + cardValue);
            return;
        }

        Image cardImage = new Image(imageStream);
        cardImageView.setImage(cardImage);
    }

    private String getCardImagePath(int cardValue) {
        String cardName = getCardName(cardValue);
        return "/com/example/higherorlower/cards/" + cardName;
    }

    private String getCardName(int cardValue) {
        switch (cardValue) {
            case 11:
                return "J-R.png";
            case 12:
                return "Q-H.png";
            case 13:
                return "K-D.png";
            case 14:
                return "A-S.png";
            default:
                return cardValue + "-C.png";
        }
    }

    @FXML
    private void playHigher() {
        playTurn(true);
    }

    @FXML
    private void playLower() {
        playTurn(false);
    }

    private void playTurn(boolean guessHigher) {
        if (deck.isEmpty()) {
            endGame();
            return;
        }

        int nextCard = deck.remove(0);
        boolean isHigher = nextCard > currentCard;

        if (guessHigher == isHigher) {
            points++;
            streak++;
        } else {
            streak = 0;
        }

        currentCard = nextCard;
        loadCardImage(currentCard);
        updateUI();
    }

    private void updateUI() {
        if (deck.isEmpty()) {
            cardLabel.setText("Game Over!");
            higherButton.setDisable(true);
            lowerButton.setDisable(true);
        } else {
            cardLabel.setText("Current Card: " + currentCard);
        }
        pointsLabel.setText("Points: " + points);
        streakLabel.setText("Streak: " + streak);
    }

    private void endGame() {
        cardLabel.setText("Game Over!");
        higherButton.setDisable(true);
        lowerButton.setDisable(true);
    }
}
