/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Constants;
import core.Disc;
import core.Game;
import core.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jeff Hoskins
 */
public class BoardUi extends JPanel {
    private JButton[][] board;
    private BoardListener listener;
    private Game game;
    private GameUi gameUi;

    public BoardUi(Game game, GameUi gameUi){
        this.game = game;
        this.gameUi = gameUi;
        
        initComponents();
        
        listener.updateUi();

    }
    
    // Override drawImage to use my own background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(
            getClass().getResource("/images/Board.png")).getImage(), 0, 0,
              this.getWidth(), this.getHeight(), this);
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(642,674));
        this.setMinimumSize(new Dimension(642, 674));
        
        // Set BoardUi layout to gridLayout.
        this.setLayout(new GridLayout(Constants.ROWS, Constants.COLUMNS));
        
        // Instantiate the board array and the listener
        board = new JButton[Constants.ROWS][Constants.COLUMNS];
        listener = new BoardListener();
        
        // Loop through the individual buttons, instantiating each one.
        for (int row=0; row<Constants.ROWS; row++){
            for(int col=0; col<Constants.COLUMNS; col++){
                
                board[row][col] = new JButton();
                board[row][col].setPreferredSize(new Dimension(120, 120));
                board[row][col].putClientProperty("row", row);
                board[row][col].putClientProperty("col", col);
                board[row][col].putClientProperty("color", Constants.EMPTY);
                board[row][col].setBackground(new Color(0x00,0x70,0x00));
                
                /**
                 * This makes the button transparent, allowing the background
                 * panel to be seen.
                 */
                board[row][col].setOpaque(false); 
                
                board[row][col].addActionListener(listener);
                
                // Add the button to the boardUi (JButton added to JPanel)
                this.add(board[row][col]);
            }
        
        }
        
        // Set the color of the jbutton to match the initial setup
        board[3][3].putClientProperty("color", Constants.LIGHT);
        board[3][4].putClientProperty("color", Constants.DARK);
        board[4][3].putClientProperty("color", Constants.DARK);
        board[4][4].putClientProperty("color", Constants.LIGHT);

    }

    /**
     * @return the board
     */
    public JButton[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(JButton[][] board) {
        this.board = board;
    }

    private class BoardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if(ae.getSource() instanceof JButton){
                JButton button = (JButton) ae.getSource();
                int row = (int) button.getClientProperty("row");
                int col = (int) button.getClientProperty("col");
                Color color = (Color) button.getClientProperty("color");
                
                if(color==Constants.EMPTY){
                    if(game.getBoard().isValidMove(row, col,
                      game.getCurrentPlayer().getDiscColor(), true)){
                        
                        updateUi();
                        
                        if(game.getBoard().gameOver()){
                         
                            Player winner = null;
                            if(game.getBoard().getPlayers().get(Constants.PLAYER_ONE).getScore() > 
                              game.getBoard().getPlayers().get(Constants.PLAYER_TWO).getScore()){
                                winner = game.getBoard().getPlayers().get(Constants.PLAYER_ONE);
                            }
                            else if(game.getBoard().getPlayers().get(Constants.PLAYER_TWO).getScore() > 
                              game.getBoard().getPlayers().get(Constants.PLAYER_ONE).getScore()){
                                winner = game.getBoard().getPlayers().get(Constants.PLAYER_TWO);
                            }
                            else{
                                winner = game.getBoard().getPlayers().get(Constants.PLAYER_ONE);
                                JOptionPane.showMessageDialog(null, 
                                  "The game is a draw.\n" +
                                  "Both players have a score of " + 
                                  winner.getScore() + ".\n" +
                                  "Click OK to exit program.");
                            System.exit(0);
                            }
                            
                            JOptionPane.showMessageDialog(null, "Game Over.\n" + 
                              winner.getName() + " wins with a score of " + 
                              winner.getScore() + ".\n" + "Click OK to exit program.");
                            System.exit(0);
                        }

                        changePlayer();
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                          "That move is not valid. Select another square");
                    }
                }
            }
        }
        
        private void updateUi(){
            /**
             * BoardUi has a game object called game. The game object has a
             * board object called board. The board object has a 2D array of
             * discs also called board. To get the array we must get the game's
             * board, then get the board's "board". Thus we have back-to-back
             * getBoard() calls in the next line.
             */
            Disc[][] discBoard = game.getBoard().getBoard();
            
            // Not sure why we were instructed to make this ImageIcon
            ImageIcon myDisc = null;
            
            for(int row=0; row<Constants.ROWS; row++){
                for(int col=0; col<Constants.COLUMNS; col++){
                    if(discBoard[row][col].getDiscColor()==Constants.DARK){
                        board[row][col].putClientProperty("color", Constants.DARK);
                        myDisc = new ImageIcon(getClass().getResource("/images/Black_Disc.png"));
                        myDisc = imageResize(myDisc);
                        board[row][col].setIcon(myDisc);
                    }
                    if(discBoard[row][col].getDiscColor()==Constants.LIGHT){
                        board[row][col].putClientProperty("color", Constants.LIGHT);
                        myDisc = new ImageIcon(getClass().getResource("/images/Gold_Disc.png"));
                        myDisc = imageResize(myDisc);
                        board[row][col].setIcon(myDisc);
                    }
                }
            }
            
            gameUi.getScoreOne().setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_ONE).getScore()));
            gameUi.getScoreTwo().setText(String.valueOf(game.getBoard().getPlayers().get(Constants.PLAYER_TWO).getScore()));
        }
        
        private ImageIcon imageResize(ImageIcon icon)
        {
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(68, 68,java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            return icon;
        }
        
        private void changePlayer(){
            if(game.getCurrentPlayer().getDiscColor()==Constants.DARK){
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_TWO));
            }
            else{
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_ONE));
            }
            
            if(!game.getBoard().hasMove(game.getCurrentPlayer().getDiscColor())){
                JOptionPane.showMessageDialog(null, "No moves available for " + 
                        game.getCurrentPlayer().getName() + ".\n"
                        + "Turn Forfited.");
                changePlayer();
            }
        }
    }
}
