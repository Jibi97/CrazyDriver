/**
 * 
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 *This class gives 
 * the ability to make 
 * any entites you need
 * to make.
 * In this case,
 * we are using it to make
 * an avatar and enemies.
 * We made the class abstract,
 * because we could make subclasses of it.
 * It cannot be instantiated, but they can be subclassed.
 * So therefore, Avatar and Enemy. 
 * The class implements a interface SoundProducer. 
 */

package Model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public abstract class Entity implements SoundProducer{
    
    
    private double WIDTH = 95;
    private double HEIGHT = 110;
    private double pos_x;
    private double pos_y;
    static int i = 0;
    
    ImageView entites = new ImageView();
    static Enemy enemyy = new Enemy();
    GameBoard gameBoard = new GameBoard();
    FileHandler fileHandler = new FileHandler();
    
    /**
     * A default constructor.
     */
    public Entity(){
        
    }
    
    
    /**
     * This constructor is 
     * used when you are 
     * going to make an 
     * object of Avatar or of Enemy.
     * It makes an entity to the pane in the game.
     * An entity can be an Avatar or an Enemy
     * It takes 6 parameters.
     * @param rect receives the value of an ImageView
     * @param w receives a parameter with a double value
     * @param h receives a parameter with a double value
     * @param x receives a parameter with a double value 
     * @param y receives a parameter with a double value
     * @param pane receives a parameter with an AnchorPane value 
     */
    public Entity(ImageView rect, double x, double y, double w, double h, AnchorPane pane){
       this.entites = rect;
       this.HEIGHT = h;
       this.WIDTH = w;
       this.pos_x = x;
       this.pos_y = y;
       
       rect.setLayoutX(x);
       rect.setLayoutY(y);
       rect.setFitWidth(w);
       rect.setFitHeight(h);
        
       pane.getChildren().add(rect);
       
    }
    
    
   /**
     * This method check for collisions.
     * It is checking if the avatar and the enemy 
     * are colliding with each other. 
     * @param enemy receives a parameter with an ImageView value
     * @param avatar receives a parameter with an ImageView value
     * @param time receives a parameter with a Timeline value
     * @param box receives a parameter with a VBox value
     */
   public void checkCollision(ImageView enemy, ImageView avatar, Timeline time, VBox box){
       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), ev -> {
           collision(enemy,avatar,time,box);
       }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
   }
   
    /**
     * This method check for collisions.
     * It is checking if the avatar and the enemy 
     * are colliding with each other. 
     * @param enemy receives a parameter with an ImageView value
     * @param avatar receives a parameter with an ImageView value
     * @param time receives a parameter with a Timeline value
     * @param box receives a parameter with a VBox value
     */
    public void collision(ImageView enemy, ImageView avatar, Timeline time, VBox box){
     
         if(enemy.getBoundsInParent().intersects(avatar.getBoundsInParent())){
            System.out.println("GAME OVER");
            
            java.net.URL resource = getClass().getResource("/View/car_brake_crash-Cam_Martinez-567114981.mp3");
            Media media = new Media(resource.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            time.stop();
            
            if(enemyy.getScore() > fileHandler.getLowestScore() && i == 0)  {
               i++;
               fileHandler.saveHiScore(enemyy.getScore());
            }
            
            box.setVisible(true);
         }
    }
 
   /**
    * This method checks 
    * if the coin, made in the enemyGenerator method,
    * collides with the avatar.
    * @param coin receives a parameter with an ImageView value
    * @param avatar receives a parameter with an ImageView value
    * @param pane receives a parameter with an AnchorPane value
    */ 
   public void checkPowerUpCollison(ImageView coin, ImageView avatar, AnchorPane pane){
       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), ev -> {
           collisionPowerUp(coin,avatar,pane);
       }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
   }
   
   
    /**
    * This method checks 
    * if the coin, made in the enemyGenerator method,
    * collides with the avatar.
    * If it does, the score will be added with 30.
    * @param coin receives a parameter with an ImageView value
    * @param avatar receives a parameter with an ImageView value
    * @param pane receives a parameter with an AnchorPane value
    */ 
    public void collisionPowerUp(ImageView coin, ImageView avatar,AnchorPane pane){
         if(coin.getBoundsInParent().intersects(avatar.getBoundsInParent())){
             removeElement(coin, pane);
             Enemy.score += 30;
         }
   } 
    
    /**
     * This method removes the 
     * coin, made in enemyGenerator method, 
     * if it has not collided with the avatar.
     * @param coin receives a parameter with an ImageView value.
     * @param pane receives a parameter with an AnchorPane value.
     */
    public void removeElement(ImageView coin, AnchorPane pane){
            pane.getChildren().remove(coin); 
   }
    
    
    /**
     * Gets the value of the entity made.
     * 
     * @return returns the ImageView value of the entity.
     */
    public ImageView getEntity(){
        return this.entites;
    }
    
    /**
     * Gets the value of the width.
     * 
     * @return returns the Integer value of the width.
     */
    public double getWidth(){
        return this.WIDTH;
    }
    
    /**
     * Gets the value of the height.
     * 
     * @return returns the  
     */
    public double getHeight(){
        return this.HEIGHT;
    }
    
    /**
     * Gets the value of the position x.
     * 
     * @return returns the Integer value of the position x. 
     */
    public double getPos_x(){
        return this.pos_x;
    }
    
    
    /**
     * Gets the value of the position y.
     * 
     * @return returns the Integer value of the position y. 
     */
    public double getPos_y(){
        return this.pos_y;
    }
    
}
