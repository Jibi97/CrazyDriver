
/***
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * This controller is
 * used for main game.
 * It is used when the
 * main game starts.
 * 
 */
package Controller;

import Model.GameBoard;
import Model.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SpillController implements Initializable {
    
    // FXML
    @FXML private AnchorPane pausePane;
    @FXML private AnchorPane rootPane;
    @FXML private AnchorPane board;
    @FXML private AnchorPane objects;
    @FXML private TextField test;
    @FXML private VBox pauseBox;
    @FXML private Button resume;
    @FXML private Button restart;
    @FXML private Button exit;
    @FXML private VBox gameOver;
     
    
    // Objects
    GameBoard gameBoard = new GameBoard();
    GameBoard draw;
    Entity avatars = new Avatar();
    Entity enemies = new Enemy();
    static Enemy enemy = new Enemy();
    Avatar bil1 = new Avatar();
    FileHandler saveGame = new FileHandler();
    Entity avatar;
    ImageView car1 = bil1.getAvatar();
    ImageView bane = gameBoard.getBoard();
    
    // Data Fields
    public int theLevel;
    public int theScore;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        avatar = new Avatar(car1,95,110,250,550,objects);
        draw = new GameBoard(bane,400,911,250,0,board);
        bil1.produceSound();
        gameOver.setVisible(false);
        
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(1.2), ev -> {
                enemy.enemyGenerator(objects,car1,test,timeline,gameOver);
        }); 
        timeline.getKeyFrames().add(kf);
        timeline.play();
        
        Timeline enemy_sound = new Timeline();
        enemy_sound.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(2), ev -> {
                 enemy.produceSound();
        });
        enemy_sound.getKeyFrames().add(kf1);
        enemy_sound.play();
             
        Timeline avatar_sound = new Timeline();
        avatar_sound.setCycleCount(Animation.INDEFINITE);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(2), ev -> {
                 bil1.produceSound();
        });
        avatar_sound.getKeyFrames().add(kf2);
        avatar_sound.play();
        
        
        
        try {
            saveGame.hiScore(rootPane);
        } catch (IOException ex) {
            Logger.getLogger(SpillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Key_movement();
        Pause(timeline);
        score();

        
    }
    
    
    /**
     * Gets the value of the level.
     * 
     * @return returns the value of the level. 
     */
    public int getLevel(){
        theLevel = enemy.getLevel();
        return theLevel;
    }
    
    /**
     * Gets the value of the score.
     * 
     * @return returns the value of the score. 
     */
    public int getScore(){
        theScore = enemy.getScore();
        return theScore;
    }
    
    
    /**
     * This method displays
     * the score progress the
     * user is getting while playing
     * the game.
     * It displays to the right for the user.
     */
    public void score(){
        
        Text t = new Text();
        t.setFill(Color.RED);
        t.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
        t.setText("CrazyDriver");
        t.setLayoutX(730);
        t.setLayoutY(125);
        rootPane.getChildren().addAll(t);

        
        Text lvl = new Text();
        lvl.setFill(Color.WHITE);
        lvl.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
        
        lvl.setLayoutX(710);
        lvl.setLayoutY(175);
        
        
        Text score = new Text();
        score.setFill(Color.WHITE);
        score.setFont(Font.font("Tahoma", FontWeight.BOLD, 12));
     
        score.setLayoutX(710);
        score.setLayoutY(190);

        
        lvl.setText("Level: " + getLevel());
        rootPane.getChildren().addAll(lvl);
        score.setText("Score: " + getScore());
        rootPane.getChildren().addAll(score);
        


       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), ev -> {
                rootPane.getChildren().remove(lvl);
                lvl.setText("Level: " + getLevel());
                rootPane.getChildren().addAll(lvl);
                rootPane.getChildren().remove(score);
                score.setText("Score: " + getScore());
                rootPane.getChildren().addAll(score);
       }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();      
         
    }
    
    
    /**
     * This method restarts
     * the score progress and the game
     * when the user decides to restart the game.
     */
    public void restartGame(){
        Enemy.score = 0;
        Enemy.level = 0;
        Enemy.wave = 0;
    }
    
    
    /**
     * This method fades out the 
     * pane used in the game.
     * 
     * @param pane receives a parameter with an AnchorPane value.
     */
    public void makeFadeOut(AnchorPane pane){
        
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(0.1));
        fade.setNode(pane);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }
    
    
    /**
     * This method fades in the 
     * pane used in the game.
     * 
     * @param pane receives a parameter with an AnchorPane value.
     */
    public void makeFadeIn(AnchorPane pane){
        
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(0.1));
        fade.setNode(pane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
    
    /**
     * This method allows
     * the user to pause the 
     * game whenever he/she wants.
     * 
     * @param enemies receives a parameter with a Timeline value.
     */
    public void Pause(Timeline enemies){
        
        
        pauseBox.setVisible(false);
        
         rootPane.setOnKeyPressed((KeyEvent event) -> {
           if(event.getCode() == KeyCode.ESCAPE){
               gameOver.setVisible(false);
               makeFadeOut(objects);
               makeFadeOut(board);
               pauseBox.setVisible(true);
               enemies.pause();
               
             resume.setOnAction((ActionEvent e) -> {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Spill.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);

                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.setScene(scene);
                stage.show(); 
                 
                 makeFadeIn(objects);
                 makeFadeIn(board);
                 pauseBox.setVisible(false);
                 enemies.play();
                 
               });
             
             restart.setOnAction((ActionEvent e) -> {
                bil1.restartCar();
                 restartGame();
                 enemies.stop();
                 pauseBox.setVisible(false);
                    
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Spill.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);

                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
             });
          
             
             exit.setOnAction((ActionEvent e) -> {
                 enemies.stop();
                 restartGame();
                 bil1.restartCar();
                 
                 pauseBox.setVisible(false);
                    
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
             });
           }
       });
        
    }
    
    
    /**
     * This method allows 
     * the user to use keyboard 
     * while playing the game.
     */
    public void Key_movement(){
        
        EventHandler keyListener = (EventHandler<KeyEvent>) (KeyEvent event) -> {
            if(event.getCode() == KeyCode.LEFT){
                bil1.go_Left(car1);
            }else if(event.getCode() == KeyCode.RIGHT){
                bil1.go_Right(car1);
            }
           
        };
        objects.addEventFilter(KeyEvent.ANY, keyListener);
    }

    
     /**
     * This method allows 
     * the user to use computer mouse 
     * while playing the game.
     * @param event receives a parameter with a MouseEvent value.
     */
    @FXML
    private void Mouse_movement(MouseEvent event) {
        
       
            if(event.getButton() == MouseButton.PRIMARY){
                bil1.go_Left(car1);
                                

            }else if(event.getButton() == MouseButton.SECONDARY){
                bil1.go_Right(car1);
            }
    }

    @FXML
    private void Key_movement(KeyEvent event) {
        
        if(event.getCode() == KeyCode.LEFT){
                bil1.go_Left(car1);
            }else if(event.getCode() == KeyCode.RIGHT){
                bil1.go_Right(car1);
            }
    }

    
    /**
     * This method allows the 
     * user to save the game.
     * It saves the score progress
     * that has been made while in the game.
     * @param event receives a parameter with an ActionEvent value.
     */
    @FXML
    private void save(ActionEvent event) {
        
        
              FileChooser fileChooser = new FileChooser();
  
              //Set extension filter
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
              fileChooser.getExtensionFilters().add(extFilter);
              Stage stage = (Stage) pausePane.getScene().getWindow();
              //Show save file dialog
              File file = fileChooser.showSaveDialog(stage);
              
              if(file != null){
                  saveGame.SaveFile(enemy.getValues(), file);
                 
              }
              
    }

    
     /**
     * This method allows the 
     * user to load the game.
     * It loads the score progress
     * that has been saved.
     * @param event receives a parameter with an ActionEvent value.
     */
    @FXML
    private void load(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
                 
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                
                Stage stage = (Stage) pausePane.getScene().getWindow();
                //Show save file dialog
                File file = fileChooser.showOpenDialog(stage);
                if(file != null){
                    saveGame.loadGame(file);
                    
                }
        
        
       
    }

    
    /**
     * This method allows
     * the user to restart 
     * the game.
     * @param event receives a parameter with an ActionEvent value. 
     */
    @FXML
    private void RETRY(ActionEvent event) {
        
          Parent root = null;
            try {
                restartGame();
                root = FXMLLoader.load(getClass().getResource("/View/Welcome.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    
    /**
     * This method allows
     * the user to go back to 
     * the main menu.
     * @param event receives a parameter with an ActionEvent value. 
     */
    @FXML
    private void MENU(ActionEvent event) {
        
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
