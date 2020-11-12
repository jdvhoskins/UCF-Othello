/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Constants;
import core.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jeff Hoskins
 */
public class GameUi extends JPanel{
    private JLabel nameOne;
    private JLabel nameTwo;
    private JLabel scoreOne;
    private JLabel scoreTwo;
    private Game game;
    
    public GameUi(Game game){
        this.game = game;
        initComponents();
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(642,50));
        this.setMinimumSize(new Dimension(642, 50));
        this.setBackground(Color.LIGHT_GRAY);
  
        //ImageIcon darkIcon = new ImageIcon(getClass().getResource("../images/Black_Disc.png"));
        ImageIcon darkIcon = new ImageIcon(getClass().getResource("/images/Black_Disc.png"));
        ImageIcon lightIcon = new ImageIcon(getClass().getResource("/images/Gold_Disc.png"));

        // add image resize code
        
        // Instantiate nameOne
        nameOne = new JLabel(String.valueOf(game.getPlayers().get(Constants.PLAYER_ONE).getName()));
        nameOne.setMinimumSize(new Dimension(200,50));
        nameOne.setPreferredSize(new Dimension(200,50));
        nameOne.setFont(new Font("Serif", Font.BOLD,22));
        darkIcon = imageResize(darkIcon);
        nameOne.setIcon(darkIcon);
        
        // Instantiate nameTwo
        nameTwo = new JLabel(String.valueOf(game.getPlayers().get(Constants.PLAYER_TWO).getName()));
        nameTwo.setMinimumSize(new Dimension(200,50));
        nameTwo.setPreferredSize(new Dimension(200,50));
        nameTwo.setFont(new Font("Serif", Font.BOLD,22));
        lightIcon = imageResize(lightIcon);
        nameTwo.setIcon(lightIcon);

        
        // Instantiate scoreOne
        scoreOne = new JLabel(String.valueOf(Constants.TWO));
        scoreOne.setMinimumSize(new Dimension(50,50));
        scoreOne.setPreferredSize(new Dimension(50,50));
        scoreOne.setFont(new Font("Serif", Font.BOLD,22));
        
        // Instantiate scoreTwo
        scoreTwo = new JLabel(String.valueOf(Constants.TWO));
        scoreTwo.setMinimumSize(new Dimension(50,50));
        scoreTwo.setPreferredSize(new Dimension(50,50));
        scoreTwo.setFont(new Font("Serif", Font.BOLD,22));
        
        // Add the name and score for player 1
        this.add(nameOne);
        this.add(scoreOne);
        
        // Add the name and score for player 2
        this.add(nameTwo);
        this.add(scoreTwo);

    }
    
    private ImageIcon imageResize(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(34, 34,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }


    /**
     * @return the nameOne
     */
    public JLabel getNameOne() {
        return nameOne;
    }

    /**
     * @param nameOne the nameOne to set
     */
    public void setNameOne(JLabel nameOne) {
        this.nameOne = nameOne;
    }

    /**
     * @return the nameTwo
     */
    public JLabel getNameTwo() {
        return nameTwo;
    }

    /**
     * @param nameTwo the nameTwo to set
     */
    public void setNameTwo(JLabel nameTwo) {
        this.nameTwo = nameTwo;
    }

    /**
     * @return the scoreOne
     */
    public JLabel getScoreOne() {
        return scoreOne;
    }

    /**
     * @param scoreOne the scoreOne to set
     */
    public void setScoreOne(JLabel scoreOne) {
        this.scoreOne = scoreOne;
    }

    /**
     * @return the scoreTwo
     */
    public JLabel getScoreTwo() {
        return scoreTwo;
    }

    /**
     * @param scoreTwo the scoreTwo to set
     */
    public void setScoreTwo(JLabel scoreTwo) {
        this.scoreTwo = scoreTwo;
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
}
