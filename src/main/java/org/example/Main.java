package org.example;

public class Main {
    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        char currentPlayer = '0';

        game.startGame(currentPlayer);
    }
}
