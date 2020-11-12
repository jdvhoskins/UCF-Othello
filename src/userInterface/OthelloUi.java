/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Jeff Hoskins
 */
public class OthelloUi extends JFrame {
    private Game game;
    private GameUi gameUi;
    private BoardUi boardUi;
        
    public OthelloUi(Game game){
        this.game = game;
        initComponents();
    }
    
    private void initComponents(){
        

        /* There is a 34px overhead in in X, and a 66px overhead in Y (includes
         * the menu bar). Each Jbutton has a 1px boarder by default.
         * The BoardUi has 8 Jbuttons each containing a (72px)^2 image with 2px
         * on each side. The GameUi will be the same width and 50px tall.
         * X = (8 * 76) + 34 = 642
         * Y = (8 * 76) + 66 + 50 = 724
         */
        this.setPreferredSize(new Dimension(642,724));
        this.setMinimumSize(new Dimension(642,724));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Instantiate gameUi and boardUi
        gameUi = new GameUi(this.game);
        boardUi = new BoardUi(this.game, this.gameUi);
        
        // Add both gameUi and boardUi (JPanels) to othelloUi(JFrame).
        this.add(gameUi, BorderLayout.NORTH);        
        this.add(boardUi, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return the gameUi
     */
    public GameUi getGameUi() {
        return gameUi;
    }

    /**
     * @param gameUi the gameUi to set
     */
    public void setGameUi(GameUi gameUi) {
        this.gameUi = gameUi;
    }

    /**
     * @return the boardUi
     */
    public BoardUi getBoardUi() {
        return boardUi;
    }

    /**
     * @param boardUi the boardUi to set
     */
    public void setBoardUi(BoardUi boardUi) {
        this.boardUi = boardUi;
    }
    
}
