/**
 * 
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * The Enemy class gives
 * you the ability to make
 * enemies. 
 *  The Avatar classe gives the
 * ability to create an avatar. 
 * It has two constructors,
 * one default to make objects
 * without giving parameters,
 * one with parameters given.
 * 
 * 
 * 
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class Enemy extends Entity {
    
    Random rand = new Random();
    TranslateTransition trans = new TranslateTransition();
    Avatar avatar = new Avatar();
    
    Image taxi = new Image("/View/taxi.png");
    Image truck = new Image("/View/truck.png");
    Image Mini_truck = new Image("/View/Mini_truck.png");
    Image coin = new Image("/View/coin.png");
    
    private final ArrayList<Image> enemies_cars = new ArrayList<>();
    private final ArrayList<Integer> numberOfEnemies = new ArrayList<>();
    
    ImageView enemy;
    ImageView enemy2;
    ImageView enemy3;
    ImageView coins;
    
    // Data Fields
    private double pos_x = 250;
    private double pos_y = 550;
    
    public int antallEnemies;
    public int[] posList_x = {250,350,450,550};
    public int Enemy_pos_x;
    public int Enemy_pos_y = -100;
    
    public static int level;
    public static int score;
    public static int wave = 0 ;
    public static int speed = 18;
    public static int load = 0;
     
    
    public int[] getValues(){
         int score = getScore();
         int level = getLevel();
         int w = wave;
         int s = speed;
         int f = avatar.getField();
         
        int[] values = {score,level,w,s,f};
        
        return values;
    }
    public void setLoad(){
        load++;
    }
    
    public void updateScore(int sc,int lvl, int wv, int spd, int field){
        setScore(sc);
        setLevel(lvl);
        wave = wv;
        speed = spd;
        avatar.restartCar();
        avatar.setField(field);
        avatar.updateField();
        
        load++;
       
    }
    
    
    /**
     * A default constructor.
     * You can make an object
     * of Avatar without
     * giving parameter.
     * 
     */
    public Enemy(){
        
    }
    
    
     /**
     * This constructor is 
     * used when you are 
     * going to make an 
     * object of Enemy.
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
    public Enemy(ImageView rect, double w, double h, double x, double y, AnchorPane pane){
        super(rect,w,h,x,y,pane);
    }
    
    
     
   /**
    * This method generates enemies
    * in the game.
    * 
    * It is called when the game
    * starts.
    * @param pane receives a parameter with an AnchorPane value
    * @param rect receives a parameter with an ImageView value
    * @param test 
    * @param time receives a parameter with a Timeline value
    * @param box receives a parameter with a VBox value
    */
   public void enemyGenerator(AnchorPane pane, ImageView rect, TextField test, Timeline time, VBox box) {
     
         if(load == 1){
            numberOfEnemies.add(1);
            numberOfEnemies.add(2);
            numberOfEnemies.add(3);  
     
            load++;
          } 
        if(level == 0){
            level++;
            score++;
            
        }
        wave++;
        
        if(level == 1 && numberOfEnemies.isEmpty()){
            numberOfEnemies.removeAll(numberOfEnemies);
            numberOfEnemies.add(1);
            numberOfEnemies.add(2);  

        }
        
        else if(level == 4 && wave == 25){
                numberOfEnemies.removeAll(numberOfEnemies);
                numberOfEnemies.add(2);
                numberOfEnemies.add(3);
                //Extra Points for reaching level 4
                score +=15; 
                }
        else if(level == 7 && wave == 61){
                numberOfEnemies.removeAll(numberOfEnemies);
                numberOfEnemies.add(2);
                numberOfEnemies.add(3);
                 //Extra Points for reaching level 7
                score+=25;
                
                }
        
        Collections.shuffle(numberOfEnemies);
        antallEnemies = numberOfEnemies.get(0);
        
        
        if(level < 5 && wave % 8 == 0){
            level++;
            // New Level, Extra points!
            score += 2;
            speed -= 1;
            System.out.println("Levelen er: " + level);
            System.out.println("Wave er: " + wave);
            System.out.println("ST er: " + speed);

        }
        else if(level >= 5 && level <= 11 && wave >= 40 && wave % 15 == 0){
            level++;
            // New Level, Extra points!
            score +=4;

            speed -=2;
            System.out.println("Levelen er: " + level);
            System.out.println("Wave er: " + wave);
            System.out.println("ST er: " + speed);



        }
        else if(level == 12){
            level++;
            // New Level, Extra points!
            score +=100;
            System.out.println("Level: " + level);

        }
       //Legger til bil i liste
       if(enemies_cars.isEmpty()){


           enemies_cars.add(taxi);
           enemies_cars.add(truck);
           enemies_cars.add(Mini_truck);
           
       }
     
       //Setter posisjonene i liste og blander de for at enemyene skal få random posisjon
       List<Integer> position = new ArrayList<>();
                   for (int i : posList_x)
                   {
                       position.add(i);
                   }
        Collections.shuffle(position);
        // Blander bilenes rekkefølge
        Collections.shuffle(enemies_cars);
        
//        antallEnemies = rand.nextInt(4);
       if(wave % 10 == 0){
           makeCoin(pane,rect,test,coin);
       }

              
       switch (numberOfEnemies.get(0)) {
           case 1:
               {
                   score++;
                   
                   int random = rand.nextInt(posList_x.length);
                   Enemy_pos_x = posList_x[random];
                   
                   enemy = new ImageView();
                  
                   enemy.setLayoutX(Enemy_pos_x);
                   enemy.setLayoutY(Enemy_pos_y);
                   enemy.setFitWidth(getWidth());
                   enemy.setFitHeight(getHeight());
                   enemy.setImage(enemies_cars.get(0));
                   
                   
                   pane.getChildren().add(enemy);                
                   
                   driveEnemy(enemy, speed);
                   checkCollision(enemy,rect,time,box);
                   removeElement(enemy,pane,test);

                   break;
               }
           case 2:
               {    
                   score+=3;

                   enemy = new ImageView();
                   enemy2 = new ImageView();
                   
                   pos_x = position.get(0);
                   int pos_x2 = position.get(1);
                    
                   enemy.setImage(enemies_cars.get(0));
                   enemy2.setImage(enemies_cars.get(1));
                   
                   enemy.setLayoutX(pos_x);
                   enemy.setLayoutY(Enemy_pos_y);
                   enemy.setFitWidth(getWidth());
                   enemy.setFitHeight(getHeight());
                   
                   enemy2.setLayoutX(pos_x2);
                   enemy2.setLayoutY(Enemy_pos_y);
                   enemy2.setFitWidth(getWidth());
                   enemy2.setFitHeight(getHeight());
                   
                   pane.getChildren().add(enemy);
                   pane.getChildren().add(enemy2);
                 
                   driveEnemy(enemy,enemy2, speed);
                   checkCollision(enemy,rect, time,box);
                   checkCollision(enemy2,rect, time,box);
                   removeElement(enemy,pane,test);
                   removeElement(enemy2,pane,test);

                   break;
               }
           case 3:
               {    
                   score+=4;
                   
                   pos_x =  position.get(0);
                   int pos_x2 = position.get(1);
                   int pos_x3 = position.get(2);
                   
                   enemy = new ImageView();
                   enemy2 = new ImageView();
                   enemy3 = new ImageView();
                   
                   
                   enemy.setImage(enemies_cars.get(0));
                   enemy2.setImage(enemies_cars.get(1));
                   enemy3.setImage(enemies_cars.get(2));
                   
                   enemy.setLayoutX(pos_x);
                   enemy.setLayoutY(Enemy_pos_y);
                   enemy.setFitWidth(getWidth());
                   enemy.setFitHeight(getHeight());

                   enemy2.setLayoutX(pos_x2);
                   enemy2.setLayoutY(Enemy_pos_y);
                   enemy2.setFitWidth(getWidth());
                   enemy2.setFitHeight(getHeight());

                   enemy3.setLayoutX(pos_x3);
                   enemy3.setLayoutY(Enemy_pos_y);
                   enemy3.setFitWidth(getWidth());
                   enemy3.setFitHeight(getHeight());

                   pane.getChildren().add(enemy);
                   pane.getChildren().add(enemy2);
                   pane.getChildren().add(enemy3);
                   
                   driveEnemy(enemy,enemy2,enemy3, speed);
                   checkCollision(enemy,rect,time,box);
                   checkCollision(enemy2,rect,time,box);
                   checkCollision(enemy3,rect,time,box);
                   removeElement(enemy,pane,test);
                   removeElement(enemy2,pane,test);
                   removeElement(enemy3,pane,test);
                   break;
               }
           default:
               break;   
       }
           
       
   }
   
   /**
    * This method generates
    * coins in the game.
    * It is called when in the enemyGenerator method.
    * 
    * @param pane receives a parameter with an AnchorPane value
    * @param rect receives a parameter with an ImageView value
    * @param test 
    * @param img receives a parameter with an Image value
    */
   public void makeCoin(AnchorPane pane, ImageView rect, TextField test, Image img){
           int randomPowerup = rand.nextInt(posList_x.length);
           int powerUpPos = posList_x[randomPowerup];

           coins = new ImageView();
           coins.setLayoutX(powerUpPos+30);
           coins.setLayoutY(Enemy_pos_y - 100);
           coins.setFitWidth(30);
           coins.setFitHeight(30);
           coins.setImage(img);
           
           pane.getChildren().add(coins);   
           driveEnemy(coins, speed);
           checkPowerUpCollison(coins,rect,pane);
           removeElement(coins, pane, test);
   }
   
   
   
   /**
    * This method is called when it has made one enemy
    * in the enemyGenerator method.
    * The method drives one enemy down made
    * in the enemyGenerator method.
    * @param enemy1 receives a parameter of an ImageView value
    * @param speed receives a parameter of an double value
    */
   public void driveEnemy(ImageView enemy1, double speed){
        TranslateTransition driveEnemy = new TranslateTransition();
        driveEnemy.setDuration(Duration.seconds(1.5 + speed/10));
        driveEnemy.setNode(enemy1);
        driveEnemy.setByY(1000);
        driveEnemy.setAutoReverse(true);
        ParallelTransition pt = new ParallelTransition(driveEnemy);
        pt.play();
        
        
      
        
   }
   
   /**
    * This method is called when it has made two enemies
    * in the enemyGenerator method.
    * The method drives two enemy down made
    * in the enemyGenerator method.
    * @param enemy1 receives a parameter of an ImageView value
    * @param enemy2 receives a parameter of an ImageView value
    * @param speed receives a parameter of an double value
    */
   public void driveEnemy(ImageView enemy1, ImageView enemy2, double speed){
       TranslateTransition driveEnemy1 = new TranslateTransition();
       TranslateTransition driveEnemy2 = new TranslateTransition();
       
        driveEnemy1.setDuration(Duration.seconds(1 + speed/10));
        driveEnemy1.setNode(enemy1);
        driveEnemy1.setByY(800);
        driveEnemy1.setAutoReverse(true);
        
        driveEnemy2.setDuration(Duration.seconds(0.8 + speed/10));
        driveEnemy2.setNode(enemy2);
        driveEnemy2.setByY(800);
        driveEnemy2.setAutoReverse(true);
        

        ParallelTransition pt = new ParallelTransition(driveEnemy1, driveEnemy2);
        pt.play();
        
        

       
       
   }
   
   /**
    * This method is called when it has made three enemies
    * in the enemyGenerator method.
    * The method drives two enemy down made
    * in the enemyGenerator method.
    * @param enemy1 receives a parameter of an ImageView value
    * @param enemy2 receives a parameter of an ImageView value
    * @param enemy3 receives a parameter of an ImageView value
    * @param speed receives a parameter of an double value
    */
   public void driveEnemy(ImageView enemy1, ImageView enemy2, ImageView enemy3, double speed){
       TranslateTransition driveEnemy1 = new TranslateTransition();
       TranslateTransition driveEnemy2 = new TranslateTransition();
       TranslateTransition driveEnemy3 = new TranslateTransition();
       
        driveEnemy1.setDuration(Duration.seconds(1 + speed/10));
        driveEnemy1.setNode(enemy1);
        driveEnemy1.setByY(800);
        driveEnemy1.setAutoReverse(true);
        
        driveEnemy2.setDuration(Duration.seconds(0.9 + speed/10));
        driveEnemy2.setNode(enemy2);
        driveEnemy2.setByY(800);
        driveEnemy2.setAutoReverse(true);
        
        driveEnemy3.setDuration(Duration.seconds(0.95 + speed/10));
        driveEnemy3.setNode(enemy3);
        driveEnemy3.setByY(800);
        driveEnemy3.setAutoReverse(true);
        
        ParallelTransition pt = new ParallelTransition(driveEnemy1, driveEnemy2, driveEnemy3);
        pt.play();
        

       
   }
   
   
    /**
     * This method removes
     * any enemies made in the enemyGenerator method.
     * The enemies get removed after they have droven down in the pane.
     * @param enemy receives a parameter with an ImageView value.
     * @param pane receives a parameter with an AnchorPane value.
     * @param test 
     */
    public void removeElement(ImageView enemy, AnchorPane pane, TextField test){
       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
            pane.getChildren().remove(enemy);
            }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
   }
    
    
    
    
    /**
     * Sets the value of the level
     * 
     * @param lvl receives a parameter with an Integer value
     */
    public void setLevel(int lvl){
        level = lvl;
    }
    
    /**
     * Gets the value of the level
     * 
     * @return returns the Integer value of the level 
     */
    public int getLevel(){
        return level;
    }
    
    /**
     * Sets the value of the score
     * 
     * @param a receives a parameter with an Integer value 
     */
    public void setScore(int a){
        score = a;
    }
    
    /**
     * Gets the value of the score
     * 
     * @return returns the Integer value of the score 
     */
    public int getScore(){
        return score;
    }

    
    /**
     * This is an 
     * abstract method
     * from the interface SoundProducer.
     * This method produces sound 
     * for the enemies made in the game.
     * The method is called 
     * when the game starts.
     */
    @Override
    public void produceSound() {
        
        java.net.URL resource = getClass().getResource("/View/Traffic Jam Sound Effect.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    
    
}
