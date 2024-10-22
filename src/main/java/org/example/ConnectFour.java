package org.example;

import java.util.Scanner;

public class ConnectFour {
    private final char[][] board;
    private final int rows = 6;
    private final int columns = 7;

    public ConnectFour() {
        board = new char[columns][rows];
    }

    public int getBoardRows() {
        return rows;
    }

    public int getBoardColumns() {
        return columns;
    }

    public boolean throwChip(int column, char player) {
        for (int row = 0; row < this.rows; row++) {
            if (board[column][row] == '\0') {
                board[column][row] = player;
                return true;
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int column = 0; column < columns; column++) {
            if (board[column][0] == '\0') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWinner(char player) {
        return checkHorizontal(player) || checkVertical(player) || checkDiagonal(player);
    }

    private boolean checkDirection(int startRow, int startCol, int rowStep, int colStep, char player) {
        for (int i = 0; i < 4; i++) {
            int row = startRow + i * rowStep;
            int col = startCol + i * colStep;
            if (board[col][row] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(char player) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col <= columns - 4; col++) {
                if (checkDirection(row, col, 0, 1, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(char player) {
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row <= rows - 4; row++) {
                if (checkDirection(row, col, 1, 0, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(char player) {
        for (int col = 3; col < columns; col++) {
            for (int row = 0; row <= rows - 4; row++) {
                if (checkDirection(row, col, 1, -1, player)) {
                    return true;
                }
            }
        }
        for (int col = 0; col <= columns - 4; col++) {
            for (int row = 0; row <= rows - 4; row++) {
                if (checkDirection(row, col, 1, 1, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard() {
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = 0; col < columns; col++) {
                char chip = board[col][row];
                System.out.print((chip == '\0' ? '.' : chip) + " ");
            }
            System.out.println();
        }
    }

    public char switchPlayer(char currentPlayer) {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return currentPlayer;
    }


    public void startGame(char currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Current board:");
            printBoard();

            System.out.print("Player " + currentPlayer + ", choose a column (0-6)");
            if (!scanner.hasNextInt()) {
                System.out.println("Enter a valid value");
                scanner.next();
                continue;
            }

            int column = scanner.nextInt();
            if (column < 0 || column >= getBoardColumns()) {
                System.out.println("Column out of bounds");
                continue;
            }

            if (throwChip(column, currentPlayer)) {
                if (checkWinner(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins");
                    printBoard();
                    break;
                } else if (isBoardFull()) {
                    System.out.println("Draw");
                    printBoard();
                    break;
                }
                currentPlayer = switchPlayer(currentPlayer);
            } else {
                System.out.println("Column full");
            }
        }

        scanner.close();
    }
}

