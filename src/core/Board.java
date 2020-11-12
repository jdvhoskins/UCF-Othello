/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeff Hoskins
 */
public class Board {
    private Disc[][] board;
    private int darkCount;
    private int lightCount;
    private ArrayList<Player> players; 
    
    public Board(){
        this.initObjects();
    }
    
    
    // Initialize the board
    private void initObjects(){
        this.setBoard(new Disc[Constants.ROWS][Constants.COLUMNS]);
        for(int i=0; i<Constants.ROWS; i++ ){
            for(int j=0; j< Constants.COLUMNS; j++){
                getBoard()[i][j] = new Disc();
            }
        }
        
        // Set the colors of the four center discs.
        getBoard()[3][3].setDiscColor(Constants.LIGHT);
        getBoard()[3][4].setDiscColor(Constants.DARK);
        getBoard()[4][3].setDiscColor(Constants.DARK);
        getBoard()[4][4].setDiscColor(Constants.LIGHT);
        
        // I think there should be some code here to initialize this.players.
        
    }
    
    // This method calculates the score of the two players
    public void calculateScore(){
        // initialize the counts
        setDarkCount(0);
        setLightCount(0);
        
        for(int row=0; row<Constants.ROWS; row++ ){
            for(int col=0; col< Constants.COLUMNS; col++){
                if(board[row][col].getDiscColor() == Constants.DARK){
                    darkCount++;
                }
                else if(board[row][col].getDiscColor() == Constants.LIGHT){
                    lightCount++;
                }
            }
        }
        
        players.get(Constants.PLAYER_ONE).setScore(darkCount);
        players.get(Constants.PLAYER_TWO).setScore(lightCount);
        
    }

    /**
     * This method determines if a move is valid. If the boolean value fliDiscs
     * is true, then the appropriate discs are flipped if the move is valid.
     * Otherwise, isValidMove just returns if the move would be valid.
     */
    public boolean isValidMove(int row, int col, Color color, boolean flipDiscs){
        boolean validMove = false;
        
        validMove = (checkUp(row, col, color, flipDiscs) || validMove);
        validMove = (checkUpLeft(row, col, color, flipDiscs) || validMove);
        validMove = (checkLeft(row, col, color, flipDiscs) || validMove);
        validMove = (checkDownLeft(row, col, color, flipDiscs) || validMove);
        validMove = (checkDown(row, col, color, flipDiscs) || validMove);
        validMove = (checkDownRight(row, col, color, flipDiscs) || validMove);
        validMove = (checkRight(row, col, color, flipDiscs) || validMove);
        validMove = (checkUpRight(row, col, color, flipDiscs) || validMove);
        
        if (validMove && flipDiscs){
            board[row][col].setDiscColor(color);
            calculateScore();
            gameOver();
        }
        
        
        
        return validMove;
    }
    
    private boolean checkUp(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curRow=row-1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curRow>=0 && colorFound==false){
            if(board[curRow][col].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[curRow][col].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curRow--;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    row--;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }

    private boolean checkDown(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curRow=row+1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curRow<Constants.ROWS && colorFound==false){
            if(board[curRow][col].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[curRow][col].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curRow++;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    row++;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }    
    
    private boolean checkLeft(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curCol=col-1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curCol>=0 && colorFound==false){
            if(board[row][curCol].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[row][curCol].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curCol--;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    col--;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }

    private boolean checkRight(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curCol=col+1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curCol<Constants.COLUMNS && colorFound==false){
            if(board[row][curCol].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[row][curCol].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curCol++;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    col++;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }    
    
    private boolean checkUpLeft(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curRow=row-1;
        int curCol=col-1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curRow>=0 && curCol>=0 && colorFound==false){
            if(board[curRow][curCol].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[curRow][curCol].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curRow--;
            curCol--;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    row--;
                    col--;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }

    private boolean checkUpRight(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curRow=row-1;
        int curCol=col+1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curRow>=0 && curCol<Constants.COLUMNS && colorFound==false){
            if(board[curRow][curCol].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[curRow][curCol].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curRow--;
            curCol++;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    row--;
                    col++;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }

    private boolean checkDownLeft(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curRow=row+1;
        int curCol=col-1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curRow<Constants.ROWS && curCol>=0 && colorFound==false){
            if(board[curRow][curCol].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[curRow][curCol].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curRow++;
            curCol--;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    row++;
                    col--;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }

    private boolean checkDownRight(int row, int col, Color color, boolean flip){
        int flipNumber = 0;
        int curRow=row+1;
        int curCol=col+1;
        boolean isValid = false;
        boolean colorFound = false;
        
        while(curRow<Constants.ROWS && curCol<Constants.COLUMNS && colorFound==false){
            if(board[curRow][curCol].getDiscColor()==null){
                // If null color is found anywhere, then the move is not valid,
                return isValid = false;
            }
            
            else if(board[curRow][curCol].getDiscColor()!=color){
                // If oponent's color is present, count it to be flipped.
                flipNumber++;
            }
            else{
                /**
                 * If color is not null and not opponent's color, then it is
                 * the current player's color.
                 */
                colorFound = true;
            }
            
            // move on to the next square
            curRow++;
            curCol++;
        }
        
        /**
         * Flip the discs if "flip" is true and there are discs to flip.
         * Otherwise just set the value of isValid
         */
        if(colorFound && flipNumber>0){
            if(flip){
                do{
                    row++;
                    col++;
                    flipNumber--;
                    board[row][col].setDiscColor(color);

                }while(flipNumber > 0);
            }
            isValid = true;
        }
        else{
            /**
             * This is only here because the instructions say to put it here.
             * Because isValid is initialized to false, this statement is
             * redundant
             */
            isValid = false;
        }
        
        return isValid;
    }

    /**
     * Returns true if there are no moves for either player.
     * A full board is not explicitly checked for because it would evaluate
     * as having no moves for either player. Thus, an explicit check would
     * be redundant.
     * 
     * The code to display the score and close the program is implemented in
     * BoardUi -> boardListener -> actionPerformed()
     */
    public boolean gameOver(){
        return (!hasMove(Constants.DARK) && !hasMove(Constants.LIGHT));
    }
    
    public boolean hasMove(Color color){
        boolean isMove = false;
        
        for(int row = 0; row<Constants.ROWS; row++){
            for(int col = 0; col<Constants.COLUMNS; col++){
                if(board[row][col].getDiscColor()==Constants.EMPTY && !isMove){
                    isMove = isValidMove(row, col, color, false);
                }
            }
        }
        // returns true if there is at least 1 move for the specified color
        return isMove;
}
    
    /**
     * @return the board
     */
    public Disc[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Disc[][] board) {
        this.board = board;
    }

    /**
     * @return the darkCount
     */
    public int getDarkCount() {
        return darkCount;
    }

    /**
     * @param darkCount the darkCount to set
     */
    public void setDarkCount(int darkCount) {
        this.darkCount = darkCount;
    }

    /**
     * @return the lightCount
     */
    public int getLightCount() {
        return lightCount;
    }

    /**
     * @param lightCount the lightCount to set
     */
    public void setLightCount(int lightCount) {
        this.lightCount = lightCount;
    }

    /**
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
}
