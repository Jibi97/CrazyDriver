/**
 * 
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * 
 * The Avatar classe gives the
 * ability to create an avatar. 
 * It has two constructors,
 * one default to make objects
 * without giving parameters,
 * one with parameters given.
 * 
 */

package Model;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class Avatar extends Entity {
    
    private static ImageView avatar = new ImageView();
    
    private static int field = 0;

   
    /**
     * A default constructor.
     * You can make an object
     * of Avatar without
     * giving parameter.
     * 
     */
    public Avatar(){
        
    }
    
    /**
     * This constructor is 
     * used when you are 
     * going to make an 
     * object of Avatar.
     * It takes 6 parameters.
     * Since the class extends 
     * from an abstract class,
     * Entity, so basically
     * it uses the Entity's
     * constructor.
     * @param rect receives the value of an ImageView
     * @param w receives a parameter with a double value
     * @param h receives a parameter with a double value
     * @param x receives a parameter with a double value 
     * @param y receives a parameter with a double value
     * @param pane receives a parameter with an AnchorPane value 
     */
    public Avatar(ImageView rect, double w, double h, double x, double y, AnchorPane pane){
        super(rect,x,y,w,h,pane);
        
    }
    
    /**
     * This method is called
     * when the user is restarting
     * the game. It moves the 
     * the avatar back to its 
     * start position
     * 
     */
    public void restartCar(){
        for(int i = 0; i <= 6; i++){
                 go_Left(avatar); 
        }
        
    }
    
    /**
     * This method is called
     * when the user moves the 
     * avatar to the left.
     * This method gives the avatar
     * the ability to go left 
     * when it is needed
     * @param view  receives a parameter with a ImageView value
     */
    public void go_Left(ImageView view){
        
       TranslateTransition trans = new TranslateTransition(); 
       trans.setDuration(Duration.millis(100));
       trans.setNode(view);
            
         if(field > 0 && field <= 2){
            trans.setToX(0);
            field--;

        }
        else if(field > 2 && field <= 4){
           trans.setToX(100);
           field--;

        }
        else if(field > 4 && field <= 6){
           trans.setToX(200);
           field--;

        }
         
         
        trans.setAutoReverse(true);
        trans.playFromStart();
    }
    
    /**
     * This method is called
     * when the user moves the 
     * avatar to the right.
     * This method gives the avatar
     * the ability to go right 
     * when it is needed
     * @param view  receives a parameter with a ImageView value
     */
    public void go_Right(ImageView view){
            
        TranslateTransition trans = new TranslateTransition(); 
        
         trans.setDuration(Duration.millis(100));
         trans.setNode(view);

          if(field >= 0 && field < 2){
              trans.setToX(100);
              field++;

          }
          else if(field >= 2 && field < 4){
            trans.setToX(200);
            field++;

        }
        else if(field >= 4 && field < 6){
            
            trans.setToX(300);
           field++;

        }
        
         trans.setAutoReverse(true);

         trans.playFromStart();

    } 
    
    /**
     * This is an 
     * abstract method
     * from the interface SoundProducer.
     * This method produces sound 
     * for the avatar in the game.
     * The method is called 
     * when the game starts.
     */
    @Override
    public void produceSound(){
        
        java.net.URL resource = getClass().getResource("/View/Car-Accelerating-SoundBible.com-28596349.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
       
    }
    
    
    /**
     * Sets the value of 
     * the avatar.
     * This method gives
     * the ability to change
     * the avatar's image.
     * 
     * @param v receives a parameter of an ImageView value
     */
    public void setAvatar(ImageView v){
        Avatar.avatar = v;
    }
    
    /**
     * Gets the value of the avatar
     * 
     * @return returns the ImageView value of the avatar 
     */
    public ImageView getAvatar(){
        return avatar;
    }
    
    /**
     * Gets the value of the field
     * 
     * @return returns the integer value of the field
     */
    public int getField(){
        return field;
    }
    
    /**
     * Sets the value of the field
     * 
     * @param f receives a parameter with an integer value
     */
     public void setField(int f){
       field = f;     
    }
     
    
    /**
     * This method updates
     * the field positons 
     * for the avatar.
     */
    public void updateField(){

        switch (field) {
            case 2:
              field--;
              go_Right(avatar);
                
                break;
            case 4:
                go_Right(avatar);
                field -=2;
                go_Right(avatar);
                break;
                
            case 6:
                field -=2;
                go_Right(avatar);
                
                field -=2;
                go_Right(avatar);
                
                go_Right(avatar);
                field++;
                break;
            default:
                break;
                        

        }
    }
    
}


