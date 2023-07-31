package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TicTacToe {
    private static final int SIZE = 3;

    public static void saveGameBoard(int[][] gameBoard, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    writer.write(String.valueOf(gameBoard[row][col]));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении игрового поля: " + e.getMessage());
        }
    }

    public static int[][] loadGameBoard(String filePath) {
        int[][] gameBoard = new int[SIZE][SIZE];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int row = 0; row < SIZE; row++) {
                String line = reader.readLine();
                for (int col = 0; col < SIZE; col++) {
                    gameBoard[row][col] = Character.getNumericValue(line.charAt(col));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке игрового поля: " + e.getMessage());
        }
        return gameBoard;
    }

    public static void printGameBoard(int[][] gameBoard) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                switch (gameBoard[row][col]) {
                    case 0:
                        System.out.print("• ");
                        break;
                    case 1:
                        System.out.print("X ");
                        break;
                    case 2:
                        System.out.print("O ");
                        break;
                    default:
                        System.out.print("? ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] gameBoard = {
                {1, 0, 2},
                {0, 1, 0},
                {2, 0, 1}
        };

        String filePath = "src/main/java/resources/gameboard.txt";

        saveGameBoard(gameBoard, filePath);

        int[][] loadedGameBoard = loadGameBoard(filePath);

        System.out.println("Игровое поле:");
        printGameBoard(gameBoard);

        System.out.println("\nИгоровое поле прочитанное из файла:");
        printGameBoard(loadedGameBoard);
    }
}

