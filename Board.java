/** 
 * A game board of NxM board of tiles.
 * 
 *  @author PLTW
 * @version 2.0
 */


import java.util.Random;

public class Board {
    private static String[] tileValues = { "lion", "lion",
            "penguin", "penguin",
            "dolphin", "dolphin",
            "fox", "fox",
            "monkey", "monkey",
            "turtle", "turtle" };

    private Tile[][] gameboard = new Tile[3][4];


    public Board() {
        Random rand = new Random();
        String[] randomizedValues = new String[tileValues.length];
        boolean[] used = new boolean[tileValues.length];

        for (int i = 0; i < tileValues.length; i++) {
            int index;
            do {
                index = rand.nextInt(tileValues.length); 
            } while (used[index]); 

            randomizedValues[i] = tileValues[index];
            used[index] = true; 
        }

        int index = 0;
        for (int row = 0; row < gameboard.length; row++) {
            for (int col = 0; col < gameboard[row].length; col++) {
                gameboard[row][col] = new Tile(randomizedValues[index]);
                index++;
            }
        }

        
    }

    public String toString() {
        String output = "";
        for (Tile[] row : gameboard) {
            for (Tile tile : row) {
                if (tile.isShowingValue()) {
                    output = output + tile.getValue() + "\t";
                } else {
                    output = output + tile.getHidden() + "\t";
                }
            }
            output = output + "\n";
        }
        return output;
    }

  
    public boolean allTilesMatch() {
        for (Tile[] row : gameboard) {
            for (Tile tile : row) {
                if (!tile.matched()) {
                    return false;
                }
            }
        }
        return true;
    }


    public void showValue(int row, int column) {
        if (validateSelection(row, column)) {
            gameboard[row][column].show();
        }
    }

  
    public String checkForMatch(int row1, int col1, int row2, int col2) {
        Tile tile1 = gameboard[row1][col1];
        Tile tile2 = gameboard[row2][col2];

        if (tile1.equals(tile2)) {
            tile1.foundMatch();
            tile2.foundMatch();
            return "It's a match!";
        } else {
            tile1.hide();
            tile2.hide();
            return "Not a match!";
        }
    }


    public boolean validateSelection(int row, int col) {
        return row >= 0 && row < gameboard.length &&
               col >= 0 && col < gameboard[0].length &&
               !gameboard[row][col].matched();
    }
}
