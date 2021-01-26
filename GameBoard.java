/**
 * 
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * 
 * The GameBoard classe gives the
 * ability to create an gameboadr. 
 * It has two constructors,
 * one default to make objects
 * without giving parameters,
 * one with parameters given.
 * 
 */

package Model;

import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class GameBoard extends Pane{
    
    // Objects    
    Random rand = new Random();
    
    private static ImageView tracks = new ImageView();

    
    // Data Fields
    private int WIDTH;
    private int HEIGHT;
    private int PosX;
    private int PosY;
    private static int roadNumber;
   
    
    /**
     * A default constructor.
     * You can make an object
     * of GameBoard without
     * giving parameter.
     * 
     */
    public GameBoard(){
        
    }
    
    /**
     * This constructor is 
     * used when you are 
     * going to make an 
     * object of GameBoard.
     * This constructor makes the gameboard
     * in the game. 
     * It takes 6 parameters.
     * @param view receives the value of an ImageView
     * @param w receives a parameter with a double value
     * @param h receives a parameter with a double value
     * @param x receives a parameter with a double value 
     * @param y receives a parameter with a double value
     * @param pane receives a parameter with an AnchorPane value 
     */
    public GameBoard(ImageView view, int w, int h, int x, int y, AnchorPane pane){
        GameBoard.tracks = view;
        this.HEIGHT = h;
        this.WIDTH = w;
        this.PosX = x;
        this.PosY = y;

        road(view, pane);
            
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev -> {
                road(view, pane);
            
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        
    }   
    
    /**
     * This method generates
     * roads for the gameboard.
     * 
     * @param bane receives a parameter with an ImageView value.
     * @param pane receives a parameter with an AnchorPane value.
     */
    public void road(ImageView bane,AnchorPane pane){
        if(roadNumber == 0){   
            bane = getBoard();

            bane.setImage(bane.getImage());
            bane.setFitWidth(400);
            bane.setFitHeight(1200);
            bane.setX(250);
            bane.setY(-100);
            pane.getChildren().add(bane);
            moveRoad(bane);
            roadNumber++;
            removeRoad(bane, pane);
        }
        Image banetype = bane.getImage();
        bane = new ImageView();
        
        
        bane.setImage(banetype);
        bane.setFitWidth(400);
        bane.setFitHeight(950);
        bane.setX(250);
        bane.setY(-950);
        pane.getChildren().add(bane);
        moveRoad(bane);
        removeRoad(bane, pane);
    }
    
    /**
     * This method is 
     * moving the road down
     * @param view receives a parameter with an ImageView value.
     */
    public void moveRoad(ImageView view){
        TranslateTransition animation = new TranslateTransition();
        animation.setDuration(Duration.seconds(2));
        animation.setNode(view);
        
        animation.setByY(1800);
        animation.setAutoReverse(true);
        
        animation.play();
    }
    
   /**
     * This method removes
     * any roads after 
     * they have translate down.
     * @param road receives a parameter with an ImageView value.
     * @param pane receives a parameter with an AnchorPane value.
     */
    public void removeRoad(ImageView road, AnchorPane pane){
         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
            pane.getChildren().remove(road);
            }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
 
    
    // Setters
    
     /**
     * Sets the value of the number of roads.
     * 
     * @param number receives a parameter with an Integer value. 
     */
    public void setRoadNumber(int number){
        roadNumber = number;
    }
    
     /**
     * Sets the value of the tracks.
     * 
     * @param view receives a parameter with an ImageView value. 
     */
    public void setBoard(ImageView view){
        GameBoard.tracks = view;
    }
    
     /**
     * Sets the value of the width.
     * 
     * @param w receives a parameter with an Integer value. 
     */
    public void setWIDTH(int w){
        WIDTH = w;
    }
    
    /**
     * Sets the value of the height.
     * 
     * @param h receives a parameter with an Integer value. 
     */
    public void setHEIGTH(int h){
        HEIGHT = h;
    }
    
    /**
     * Sets the value of the position x.
     * 
     * @param x receives a parameter with an Integer value. 
     */
    public void setPosX(int x){
        PosX = x;
    }
    
    /**
     * Sets the value of the position y.
     * 
     * @param y receives a parameter with an Integer value. 
     */
    public void setPosY(int y){
        PosX = y;
    }
    
    
    
    // Getters
    
    /**
     * Gets the value of the number of roads.
     * 
     * @return returns the value of the number of roads.
     */
    public int getRoadNumber(){
        return roadNumber;
    }
    
   
    /**
     * Gets the value of the tracks.
     * 
     * @return returns the value of tracks.
     */
    public ImageView getBoard(){
        return GameBoard.tracks;
    }
    
    /**
     * Gets the value of the width.
     * 
     * @return returns the value of width.
     */
    public int getWIDTH(){
        return WIDTH;
    }
    
    /**
     * Gets the value of the height.
     * 
     * @return returns the value of height.
     */
    public int getHEIGTH(){
        return HEIGHT;
    }
    
    /**
     * Gets the value of the positon x.
     * 
     * @return returns the value of postiton x.
     */
    public int getPosX(){
        return PosX;
    }
    
    /**
     * Gets the value of the positon y.
     * 
     * @return returns the value of postiton y.
     */
    public int getPosY(){
        return PosY;
    }
 }