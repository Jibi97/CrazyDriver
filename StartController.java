
/**
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * This controller
 * is used for the 
 * front page.
 * 
 * 
 */
package Controller;

import java.io.IOException;
import javafx.fxml.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;


public class StartController implements Initializable {
    
    // FXML
    @FXML private BorderPane rootPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BackGroundMusic();
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.minutes(2), ev -> {
            BackGroundMusic();
        }); 
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
    }   
    
    
    /**
     * 
     * This method
     * produces the background music 
     * for the game
     */
    public void BackGroundMusic(){
        
        java.net.URL resource = getClass().getResource("/View/Pixel Car Racer - Theme Song.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

   
    /**
     * 
     * This method loads 
     * up a new FXML file.
     * It loads the Main Menu.
     * 
     * @param event receives a parameter with an ActionEvent value.
     * @throws IOException throws an Exception.
     */
    @FXML
    private void loadStart(ActionEvent event) throws IOException {
         
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

   
}
