/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeff Hoskins
 */
public class Game {
    private ArrayList<Player> players;
    private Board board;
    private Player currentPlayer;
    
    public Game(){
        this.initObjects();
    }
    
    /**
     * Initialize the game.
     */
    private void initObjects(){
        this.board = new Board();
        this.createPlayers();
        board.setPlayers(players);
        this.currentPlayer = players.get(Constants.PLAYER_ONE);
        
//        // Ditch this line
//        this.printPlayers();
    }
    
    /**
     * Calculate the current score.
     * I'm not sure if this is really needed.
     */
    public void calculateScore(){
        board.calculateScore();
        players.get(Constants.PLAYER_ONE).setScore(board.getDarkCount());
        players.get(Constants.PLAYER_TWO).setScore(board.getLightCount());

    }
    
    /**
     * Creates and fills the list of players.
     */
    private void createPlayers(){
        this.players = new ArrayList<Player>();
        
        for(int i=0; i<Constants.MAX_PLAYERS; i++){
            String name = JOptionPane.showInputDialog(null, "Enter the name of player " + (i+1) + ".");
            Player newPlayer = new Player();
            newPlayer.setName(name);
            if (i == Constants.PLAYER_ONE){
                newPlayer.setDiscColor(Constants.DARK);
            }
            else{
                newPlayer.setDiscColor(Constants.LIGHT);
            }
            
            this.players.add(i,newPlayer);
        }
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

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
}
