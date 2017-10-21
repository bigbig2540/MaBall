/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maballv1;

import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Malikoto
 */
public class MaBallV1 extends Application {

    final int height = 600;
    final int width = 1200;
    private int mode = 0;
    private Pane Homepage;
    private Scene mainScene;
    private Stage stage;
    private MediaPlayer BGM = new MediaPlayer(new Media(new File("BGM_menu.mp3").toURI().toString()));
    
    @Override
    public void start(Stage primaryStage) {
        BGM.setVolume(0.2);
        BGM.setCycleCount(AudioClip.INDEFINITE);
        BGM.play();
        stage = primaryStage;
        mainScene = new Scene(menuScene());
        stage.setTitle("MaBall Never Reach The Edge");
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
    }

    private Parent menuScene() {
        Homepage = new Pane();
        Homepage.setPrefSize(width, height);
 
        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        ImageView gameName = new ImageView(new Image("maballv1/pic/Menu/GameName.png"));
        ImageView playButton = new ImageView(new Image("maballv1/pic/Menu/PlayButton1.png"));
        ImageView howtoButton = new ImageView(new Image("maballv1/pic/Menu/HowtoplayButton1.png"));
        ImageView ScoreButton = new ImageView(new Image("maballv1/pic/Menu/HighscoreButton1.png"));
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));
        
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        gameName.setFitWidth(gameName.getBoundsInParent().getWidth() * (0.75));
        gameName.setFitHeight(gameName.getBoundsInParent().getHeight() * (0.75));
        gameName.setLayoutX((width - gameName.getFitWidth()) / 2.0);
        gameName.setLayoutY(height * (0.05));

        playButton.setFitWidth(playButton.getBoundsInParent().getWidth() * (0.75));
        playButton.setFitHeight(playButton.getBoundsInParent().getHeight() * (0.75));
        playButton.setLayoutX((width - playButton.getFitWidth()) / 2.0);
        playButton.setLayoutY(height * (0.35));

        howtoButton.setFitWidth(howtoButton.getBoundsInParent().getWidth() * (0.75));
        howtoButton.setFitHeight(howtoButton.getBoundsInParent().getHeight() * (0.75));
        howtoButton.setLayoutX((width - howtoButton.getFitWidth()) / 2.0);
        howtoButton.setLayoutY(height * (0.55));

        ScoreButton.setFitWidth(ScoreButton.getBoundsInParent().getWidth() * (0.75));
        ScoreButton.setFitHeight(ScoreButton.getBoundsInParent().getHeight() * (0.75));
        ScoreButton.setLayoutX((width - ScoreButton.getFitWidth()) / 2.0);
        ScoreButton.setLayoutY(height * (0.75));

        playButton.setOnMouseEntered(event -> {
            playButton.setImage(new Image("maballv1/pic/Menu/PlayButton2.png"));
            bubble.play();
        });
        playButton.setOnMouseExited(event -> {
            playButton.setImage(new Image("maballv1/pic/Menu/PlayButton1.png"));
            bubble.stop();
        });
        playButton.setOnMouseClicked(event -> {
            mainScene = new Scene(playerSelectScene());
            stage.setScene(mainScene);
            stage.show();
        });

        howtoButton.setOnMouseEntered(event -> {
            howtoButton.setImage(new Image("maballv1/pic/Menu/HowtoplayButton2.png"));
            bubble.play();
        });
        howtoButton.setOnMouseExited(event -> {
            howtoButton.setImage(new Image("maballv1/pic/Menu/HowtoplayButton1.png"));
            bubble.stop();
        });
        howtoButton.setOnMouseClicked(event -> {
            mainScene = new Scene(howToScene());
            stage.setScene(mainScene);
            stage.show();
        });

        ScoreButton.setOnMouseEntered(event -> {
            ScoreButton.setImage(new Image("maballv1/pic/Menu/HighscoreButton2.png"));
            bubble.play();
        });
        ScoreButton.setOnMouseExited(event -> {
            ScoreButton.setImage(new Image("maballv1/pic/Menu/HighscoreButton1.png"));
            bubble.stop();
        });
        ScoreButton.setOnMouseClicked(event -> {
            mainScene = new Scene(modeSelectHighScore());
            stage.setScene(mainScene);
            stage.show();
        });

        FadeTransition gameNameFade = new FadeTransition(Duration.millis(1000), gameName);
        FadeTransition playButtonFade = new FadeTransition(Duration.millis(1000), playButton);
        FadeTransition howtoButtonFade = new FadeTransition(Duration.millis(1000), howtoButton);
        FadeTransition ScoreButtonFade = new FadeTransition(Duration.millis(1000), ScoreButton);

        gameNameFade.setFromValue(0.0);
        gameNameFade.setToValue(1);
        gameNameFade.play();

        playButtonFade.setFromValue(0.0);
        playButtonFade.setToValue(1);
        playButtonFade.play();

        howtoButtonFade.setFromValue(0.0);
        howtoButtonFade.setToValue(1);
        howtoButtonFade.play();

        ScoreButtonFade.setFromValue(0.0);
        ScoreButtonFade.setToValue(1);
        ScoreButtonFade.play();

        Homepage.getChildren().add(backGround);
        Homepage.getChildren().add(gameName);
        Homepage.getChildren().add(playButton);
        Homepage.getChildren().add(howtoButton);
        Homepage.getChildren().add(ScoreButton);

        return Homepage;
    }

    private Parent highScoreScene() throws IOException {
        Pane highScorePage = new Pane();
        StackPane highScoreStack = new StackPane();
        StackPane highScoreTableStack = new StackPane();
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        highScorePage.setPrefSize(width, height);
        highScoreStack.setPrefSize(width, height * 0.3);
        highScoreTableStack.setPrefSize(width, height * 1.25);

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        ImageView smallPlayButton = new ImageView(new Image("maballv1/pic/Menu/SmallPlayButton1.png"));
        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
        Sprite highscoreBanner = new Sprite("maballv1/pic/Menu/Highscore.png");
        Sprite highscoreTable = new Sprite("maballv1/pic/Menu/HighscoreTable_1.png");

        smallPlayButton.setFitWidth(smallPlayButton.getBoundsInParent().getWidth() * (0.75));
        smallPlayButton.setFitHeight(smallPlayButton.getBoundsInParent().getHeight() * (0.75));
        smallPlayButton.setLayoutX((width - smallPlayButton.getFitWidth()) / 1.20);
        smallPlayButton.setLayoutY(height * (0.85));

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        smallPlayButton.setOnMouseEntered(event -> {
            smallPlayButton.setImage(new Image("maballv1/pic/Menu/SmallPlayButton2.png"));
            bubble.play();
        });
        smallPlayButton.setOnMouseExited(event -> {
            smallPlayButton.setImage(new Image("maballv1/pic/Menu/SmallPlayButton1.png"));
            bubble.stop();
        });
        smallPlayButton.setOnMouseClicked(event -> {
            mainScene = new Scene(playerSelectScene());
            stage.setScene(mainScene);
            stage.show();
        });

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });
        smallHomeButton.setOnMouseClicked(event -> {
            mainScene = new Scene(menuScene());
            stage.setScene(mainScene);
            stage.show();
        });
        String[] nameList = new String[5];
        String[] ScoreList = new String[5];

        readName1(nameList, ScoreList);
        System.out.println(Arrays.toString(nameList));
        System.out.println(Arrays.toString(ScoreList));

        VBox vbox = new VBox(15);
        vbox.setPrefSize(width, height);
        vbox.setLayoutX(-140);
        vbox.setLayoutY(125);

        VBox vbox2 = new VBox(15);
        vbox2.setPrefSize(width, height);
        vbox2.setLayoutX(150);
        vbox2.setLayoutY(125);

        vbox.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        Pane vbp = new Pane();
        vbp.setPrefSize(width, height);

        Text[] name = new Text[5];
        Text[] score = new Text[5];

        FadeTransition highscoreBannerFade = new FadeTransition(Duration.millis(1000), highscoreBanner.getPlayerSprite());
        FadeTransition smallPlayButtonFade = new FadeTransition(Duration.millis(1000), smallPlayButton);
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        FadeTransition highscoreTableFade = new FadeTransition(Duration.millis(1000), highscoreTable.getPlayerSprite());

        highscoreBannerFade.setFromValue(0.0);
        highscoreBannerFade.setToValue(1);
        highscoreBannerFade.play();

        smallPlayButtonFade.setFromValue(0.0);
        smallPlayButtonFade.setToValue(1);
        smallPlayButtonFade.play();

        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();

        highscoreTableFade.setFromValue(0.0);
        highscoreTableFade.setToValue(1);
        highscoreTableFade.play();

        highScoreStack.getChildren().add(highscoreBanner.getPlayerSprite());
        highScoreTableStack.getChildren().add(highscoreTable.getPlayerSprite());
        highScorePage.getChildren().add(backGround);
        highScorePage.getChildren().add(highScoreStack);
        highScorePage.getChildren().add(highScoreTableStack);
        highScorePage.getChildren().add(smallHomeButton);
        highScorePage.getChildren().add(smallPlayButton);
        vbp.getChildren().add(vbox);
        vbp.getChildren().add(vbox2);
        highScoreTableStack.getChildren().add(vbp);

        for (int i = 0; i < 5; i++) {
            name[i] = new Text(nameList[i]);
            name[i].setFont(Font.font("Bariol Regular", 40));
            name[i].setFill(Color.web("#3e3024"));
            vbox.getChildren().add(name[i]);
        }
        for (int i = 0; i < 5; i++) {
            score[i] = new Text(ScoreList[i]);
            score[i].setFont(Font.font("Bariol Regular", 40));
            score[i].setFill(Color.web("#3e3024"));
            score[i].setLayoutX(720);
            score[i].setLayoutY(310 + 60 * i);
            vbox2.getChildren().add(score[i]);
        }
        
        FadeTransition[] nameFade = new FadeTransition[5];
        for(int i=0;i<5;i++)
        {
            nameFade[i] = new FadeTransition(Duration.millis(1000), name[i]);
            nameFade[i].setFromValue(0.0);
            nameFade[i].setToValue(1);
            nameFade[i].play();
        }
        
        FadeTransition[] scoreFade = new FadeTransition[5];
        for(int i=0;i<5;i++)
        {
            scoreFade[i] = new FadeTransition(Duration.millis(1000), score[i]);
            scoreFade[i].setFromValue(0.0);
            scoreFade[i].setToValue(1);
            scoreFade[i].play();
        }

        return highScorePage;
    }

    private Parent highScoreScene2() throws IOException {
        Pane highScorePage = new Pane();
        StackPane highScoreStack = new StackPane();
        StackPane highScoreTableStack = new StackPane();
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        highScorePage.setPrefSize(width, height);
        highScoreStack.setPrefSize(width, height * 0.3);
        highScoreTableStack.setPrefSize(width, height * 1.25);

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        ImageView smallPlayButton = new ImageView(new Image("maballv1/pic/Menu/SmallPlayButton1.png"));
        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
        Sprite highscoreBanner = new Sprite("maballv1/pic/Menu/Highscore.png");
        Sprite highscoreTable = new Sprite("maballv1/pic/Menu/HighscoreTable_1.png");

        smallPlayButton.setFitWidth(smallPlayButton.getBoundsInParent().getWidth() * (0.75));
        smallPlayButton.setFitHeight(smallPlayButton.getBoundsInParent().getHeight() * (0.75));
        smallPlayButton.setLayoutX((width - smallPlayButton.getFitWidth()) / 1.20);
        smallPlayButton.setLayoutY(height * (0.85));

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        smallPlayButton.setOnMouseEntered(event -> {
            smallPlayButton.setImage(new Image("maballv1/pic/Menu/SmallPlayButton2.png"));
            bubble.play();
        });
        smallPlayButton.setOnMouseExited(event -> {
            smallPlayButton.setImage(new Image("maballv1/pic/Menu/SmallPlayButton1.png"));
            bubble.stop();
        });
        smallPlayButton.setOnMouseClicked(event -> {
            mainScene = new Scene(playerSelectScene());
            stage.setScene(mainScene);
            stage.show();
        });

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });
        smallHomeButton.setOnMouseClicked(event -> {
            mainScene = new Scene(menuScene());
            stage.setScene(mainScene);
            stage.show();
        });
        String[] nameList = new String[5];
        String[] ScoreList = new String[5];
        
        FadeTransition highscoreBannerFade = new FadeTransition(Duration.millis(1000), highscoreBanner.getPlayerSprite());
        FadeTransition smallPlayButtonFade = new FadeTransition(Duration.millis(1000), smallPlayButton);
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        FadeTransition highscoreTableFade = new FadeTransition(Duration.millis(1000), highscoreTable.getPlayerSprite());
        
        highscoreBannerFade.setFromValue(0.0);
        highscoreBannerFade.setToValue(1);
        highscoreBannerFade.play();

        smallPlayButtonFade.setFromValue(0.0);
        smallPlayButtonFade.setToValue(1);
        smallPlayButtonFade.play();

        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();

        highscoreTableFade.setFromValue(0.0);
        highscoreTableFade.setToValue(1);
        highscoreTableFade.play();
        
        readName2(nameList, ScoreList);
        System.out.println(Arrays.toString(nameList));
        System.out.println(Arrays.toString(ScoreList));

        VBox vbox = new VBox(15);
        vbox.setPrefSize(width, height);
        vbox.setLayoutX(-140);
        vbox.setLayoutY(125);

        VBox vbox2 = new VBox(15);
        vbox2.setPrefSize(width, height);
        vbox2.setLayoutX(150);
        vbox2.setLayoutY(125);

        vbox.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER);
        Pane vbp = new Pane();
        vbp.setPrefSize(width, height);

        Text[] name = new Text[5];
        Text[] score = new Text[5];
        highScoreStack.getChildren().add(highscoreBanner.getPlayerSprite());
        highScoreTableStack.getChildren().add(highscoreTable.getPlayerSprite());
        highScorePage.getChildren().add(backGround);
        highScorePage.getChildren().add(highScoreStack);
        highScorePage.getChildren().add(highScoreTableStack);
        highScorePage.getChildren().add(smallHomeButton);
        highScorePage.getChildren().add(smallPlayButton);
        vbp.getChildren().add(vbox);
        vbp.getChildren().add(vbox2);
        highScoreTableStack.getChildren().add(vbp);

        for (int i = 0; i < 5; i++) {
            name[i] = new Text(nameList[i]);
            name[i].setFont(Font.font("Bariol Regular", 40));
            name[i].setFill(Color.web("#3e3024"));
            vbox.getChildren().add(name[i]);
        }
        for (int i = 0; i < 5; i++) {
            score[i] = new Text(ScoreList[i]);
            score[i].setFont(Font.font("Bariol Regular", 40));
            score[i].setFill(Color.web("#3e3024"));
            score[i].setLayoutX(720);
            score[i].setLayoutY(310 + 60 * i);
            vbox2.getChildren().add(score[i]);
        }
        
        FadeTransition[] nameFade = new FadeTransition[5];
        for(int i=0;i<5;i++)
        {
            nameFade[i] = new FadeTransition(Duration.millis(1000), name[i]);
            nameFade[i].setFromValue(0.0);
            nameFade[i].setToValue(1);
            nameFade[i].play();
        }
        
        FadeTransition[] scoreFade = new FadeTransition[5];
        for(int i=0;i<5;i++)
        {
            scoreFade[i] = new FadeTransition(Duration.millis(1000), score[i]);
            scoreFade[i].setFromValue(0.0);
            scoreFade[i].setToValue(1);
            scoreFade[i].play();
        }

        return highScorePage;
    }

    private void readName1(String[] name, String[] score) throws FileNotFoundException, IOException {
        BufferedReader nameRead = new BufferedReader(new FileReader("name1.txt"));
        BufferedReader scoreRead = new BufferedReader(new FileReader("score1.txt"));
        String line;
        int i = 0;
        try {
            while ((line = scoreRead.readLine()) != null) {
                String tmp = nameRead.readLine();
                name[i] = tmp;
                score[i++] = line;
            }
            scoreRead.close();
            nameRead.close();

        } finally {
            scoreRead.close();
            nameRead.close();
        }
    }

    private void readName2(String[] name, String[] score) throws FileNotFoundException, IOException {
        BufferedReader nameRead = new BufferedReader(new FileReader("name2.txt"));
        BufferedReader scoreRead = new BufferedReader(new FileReader("score2.txt"));
        String line;
        int i = 0;
        try {
            while ((line = scoreRead.readLine()) != null) {
                String tmp = nameRead.readLine();
                name[i] = tmp;
                score[i++] = line;
            }
            scoreRead.close();
            nameRead.close();

        } finally {
            scoreRead.close();
            nameRead.close();
        }
    }

    private Parent howToScene() {
        Pane howToPage = new Pane();
        howToPage.setPrefSize(width, height);

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        ImageView howtoPlayButton = new ImageView(new Image("maballv1/pic/Menu/Howtoplay.png"));
        ImageView howtoPlayTable = new ImageView(new Image("maballv1/pic/Menu/HowtoplayTable.png"));
        ImageView smallPlayButton = new ImageView(new Image("maballv1/pic/Menu/SmallPlayButton1.png"));
        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        backGround.setFitWidth(width);
        backGround.setFitHeight(height);
        
        FadeTransition howtoPlayButtonFade = new FadeTransition(Duration.millis(1000), howtoPlayButton);
        FadeTransition smallPlayButtonFade = new FadeTransition(Duration.millis(1000), smallPlayButton);
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        FadeTransition howtoPlayTableFade = new FadeTransition(Duration.millis(1000), howtoPlayTable);
        
        howtoPlayButtonFade.setFromValue(0.0);
        howtoPlayButtonFade.setToValue(1);
        howtoPlayButtonFade.play();

        smallPlayButtonFade.setFromValue(0.0);
        smallPlayButtonFade.setToValue(1);
        smallPlayButtonFade.play();

        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();

        howtoPlayTableFade.setFromValue(0.0);
        howtoPlayTableFade.setToValue(1);
        howtoPlayTableFade.play();

        howtoPlayButton.setFitWidth(howtoPlayButton.getBoundsInParent().getWidth() * (0.75));
        howtoPlayButton.setFitHeight(howtoPlayButton.getBoundsInParent().getHeight() * (0.75));
        howtoPlayButton.setLayoutX((width - howtoPlayButton.getFitWidth()) / 2.0);
        howtoPlayButton.setLayoutY(height * (0.05));

        howtoPlayTable.setFitWidth(howtoPlayTable.getBoundsInParent().getWidth() * (0.75));
        howtoPlayTable.setFitHeight(howtoPlayTable.getBoundsInParent().getHeight() * (0.75));
        howtoPlayTable.setLayoutX((width - howtoPlayTable.getFitWidth()) / 2.0);
        howtoPlayTable.setLayoutY(height * (0.30));

        smallPlayButton.setFitWidth(smallPlayButton.getBoundsInParent().getWidth() * (0.75));
        smallPlayButton.setFitHeight(smallPlayButton.getBoundsInParent().getHeight() * (0.75));
        smallPlayButton.setLayoutX((width - smallPlayButton.getFitWidth()) / 1.20);
        smallPlayButton.setLayoutY(height * (0.85));

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        smallPlayButton.setOnMouseEntered(event -> {
            smallPlayButton.setImage(new Image("maballv1/pic/Menu/SmallPlayButton2.png"));
            bubble.play();
        });
        smallPlayButton.setOnMouseExited(event -> {
            smallPlayButton.setImage(new Image("maballv1/pic/Menu/SmallPlayButton1.png"));
            bubble.stop();
        });
        smallPlayButton.setOnMouseClicked(event -> {
            mainScene = new Scene(playerSelectScene());
            stage.setScene(mainScene);
            stage.show();
        });

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });
        smallHomeButton.setOnMouseClicked(event -> {
            mainScene = new Scene(menuScene());
            stage.setScene(mainScene);
            stage.show();
        });

        howToPage.getChildren().add(backGround);
        howToPage.getChildren().add(howtoPlayButton);
        howToPage.getChildren().add(howtoPlayTable);
        howToPage.getChildren().add(smallPlayButton);
        howToPage.getChildren().add(smallHomeButton);

        return howToPage;
    }

    private Parent playerSelectScene() {
        Pane playerSelectPage = new Pane();
        playerSelectPage.setPrefSize(width, height);
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        ImageView gameName = new ImageView(new Image("maballv1/pic/Menu/GameName.png"));
        ImageView onePlayerButton = new ImageView(new Image("maballv1/pic/Menu/1PlayerButton1.png"));
        ImageView twoPlayerButton = new ImageView(new Image("maballv1/pic/Menu/2PlayersButton1.png"));
        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));

        FadeTransition gameNameFade = new FadeTransition(Duration.millis(1000), gameName);
        FadeTransition onePlayerButtonFade = new FadeTransition(Duration.millis(1000), onePlayerButton);
        FadeTransition twoPlayerButtonFade = new FadeTransition(Duration.millis(1000), twoPlayerButton);
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        
        gameNameFade.setFromValue(0.0);
        gameNameFade.setToValue(1);
        gameNameFade.play();

        onePlayerButtonFade.setFromValue(0.0);
        onePlayerButtonFade.setToValue(1);
        onePlayerButtonFade.play();

        twoPlayerButtonFade.setFromValue(0.0);
        twoPlayerButtonFade.setToValue(1);
        twoPlayerButtonFade.play();

        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();
        
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        gameName.setFitWidth(gameName.getBoundsInParent().getWidth() * (0.75));
        gameName.setFitHeight(gameName.getBoundsInParent().getHeight() * (0.75));
        gameName.setLayoutX((width - gameName.getFitWidth()) / 2.0);
        gameName.setLayoutY(height * (0.05));

        onePlayerButton.setFitWidth(onePlayerButton.getBoundsInParent().getWidth() * (0.75));
        onePlayerButton.setFitHeight(onePlayerButton.getBoundsInParent().getHeight() * (0.75));
        onePlayerButton.setLayoutX((width - onePlayerButton.getFitWidth()) / 3);
        onePlayerButton.setLayoutY(height * (0.45));

        twoPlayerButton.setFitWidth(twoPlayerButton.getBoundsInParent().getWidth() * (0.75));
        twoPlayerButton.setFitHeight(twoPlayerButton.getBoundsInParent().getHeight() * (0.75));
        twoPlayerButton.setLayoutX((width - twoPlayerButton.getFitWidth()) / 1.5);
        twoPlayerButton.setLayoutY(height * (0.45));

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        onePlayerButton.setOnMouseEntered(event -> {
            onePlayerButton.setImage(new Image("maballv1/pic/Menu/1PlayerButton2.png"));
            bubble.play();
        });
        onePlayerButton.setOnMouseExited(event -> {
            onePlayerButton.setImage(new Image("maballv1/pic/Menu/1PlayerButton1.png"));
            bubble.stop();
        });
        onePlayerButton.setOnMouseClicked(event -> {
            mainScene = new Scene(modeSelect1Player());
            stage.setScene(mainScene);
            stage.show();
        });

        twoPlayerButton.setOnMouseEntered(event -> {
            twoPlayerButton.setImage(new Image("maballv1/pic/Menu/2PlayersButton2.png"));
            bubble.play();
        });
        twoPlayerButton.setOnMouseExited(event -> {
            twoPlayerButton.setImage(new Image("maballv1/pic/Menu/2PlayersButton1.png"));
            bubble.stop();
        });
        twoPlayerButton.setOnMouseClicked(event -> {
            mainScene = new Scene(modeSelect2Player());
            stage.setScene(mainScene);
            stage.show();
        });

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });
        smallHomeButton.setOnMouseClicked(event -> {
            mainScene = new Scene(menuScene());
            stage.setScene(mainScene);
            stage.show();
        });

        playerSelectPage.getChildren().add(backGround);
        playerSelectPage.getChildren().add(gameName);
        playerSelectPage.getChildren().add(onePlayerButton);
        playerSelectPage.getChildren().add(twoPlayerButton);
        playerSelectPage.getChildren().add(smallHomeButton);

        return playerSelectPage;
    }

    private Parent modeSelectHighScore() {
        Pane modeSelect1PlayerPage = new Pane();
        modeSelect1PlayerPage.setPrefSize(width, height);

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        ImageView selectMode = new ImageView(new Image("maballv1/pic/Menu/SelectMode.png"));
        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
        ImageView circleForEasyMode = new ImageView(new Image("maballv1/pic/Menu/CircleForEasyMode.png"));
        ImageView circleForHardMode = new ImageView(new Image("maballv1/pic/Menu/CircleForHardMode.png"));
        ImageView easyButton = new ImageView(new Image("maballv1/pic/Menu/EasyButton1.png"));
        ImageView hardButton = new ImageView(new Image("maballv1/pic/Menu/HardButton1.png"));
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        backGround.setFitWidth(width);
        backGround.setFitHeight(height);
        
        FadeTransition selectModeFade = new FadeTransition(Duration.millis(1000), selectMode);
        FadeTransition easyButtonButtonFade = new FadeTransition(Duration.millis(1000), easyButton);
        FadeTransition hardButtonFade = new FadeTransition(Duration.millis(1000), hardButton);
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        FadeTransition circleForEasyModeFade = new FadeTransition(Duration.millis(1000), circleForEasyMode);
        FadeTransition circleForHardModeFade = new FadeTransition(Duration.millis(1000), circleForHardMode);
        
        selectModeFade.setFromValue(0.0);
        selectModeFade.setToValue(1);
        selectModeFade.play();
        
        easyButtonButtonFade.setFromValue(0.0);
        easyButtonButtonFade.setToValue(1);
        easyButtonButtonFade.play();
        
        hardButtonFade.setFromValue(0.0);
        hardButtonFade.setToValue(1);
        hardButtonFade.play();
        
        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();
        
        circleForEasyModeFade.setFromValue(0.0);
        circleForEasyModeFade.setToValue(1);
        circleForEasyModeFade.play();
        
        circleForHardModeFade.setFromValue(0.0);
        circleForHardModeFade.setToValue(1);
        circleForHardModeFade.play();

        selectMode.setFitWidth(selectMode.getBoundsInParent().getWidth() * (0.75));
        selectMode.setFitHeight(selectMode.getBoundsInParent().getHeight() * (0.75));
        selectMode.setLayoutX((width - selectMode.getFitWidth()) / 2.0);
        selectMode.setLayoutY(height * (0.05));

        circleForEasyMode.setFitWidth(circleForEasyMode.getBoundsInParent().getWidth() * (0.75));
        circleForEasyMode.setFitHeight(circleForEasyMode.getBoundsInParent().getHeight() * (0.75));
        circleForEasyMode.setLayoutX((width - circleForEasyMode.getFitWidth()) / 2.8);
        circleForEasyMode.setLayoutY(height * (0.325));
        RotateTransition rtCircleForEasyMode = new RotateTransition(Duration.millis(3000), circleForEasyMode);

        circleForHardMode.setFitWidth(circleForHardMode.getBoundsInParent().getWidth() * (0.75));
        circleForHardMode.setFitHeight(circleForHardMode.getBoundsInParent().getHeight() * (0.75));
        circleForHardMode.setLayoutX((width - circleForHardMode.getFitWidth()) / 1.525);
        circleForHardMode.setLayoutY(height * (0.325));
        RotateTransition rtCircleForHardMode = new RotateTransition(Duration.millis(3000), circleForHardMode);

        easyButton.setFitWidth(easyButton.getBoundsInParent().getWidth() * (0.75));
        easyButton.setFitHeight(easyButton.getBoundsInParent().getHeight() * (0.75));
        easyButton.setLayoutX((width - easyButton.getFitWidth()) / 3.0);
        easyButton.setLayoutY(height * (0.60));

        easyButton.setOnMouseClicked(event -> {
            try {
                mainScene = new Scene(highScoreScene());
            } catch (IOException ex) {
                Logger.getLogger(MaBallV1.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(mainScene);
            stage.show();
        });

        easyButton.setOnMouseEntered(event -> {
            easyButton.setImage(new Image("maballv1/pic/Menu/EasyButton2.png"));
            rtCircleForEasyMode.setByAngle(360);
            rtCircleForEasyMode.setCycleCount(Timeline.INDEFINITE);
            rtCircleForEasyMode.play();
            bubble.play();
        });
        easyButton.setOnMouseExited(event -> {
            easyButton.setImage(new Image("maballv1/pic/Menu/EasyButton1.png"));
            rtCircleForEasyMode.stop();
            bubble.stop();
        });

        hardButton.setFitWidth(hardButton.getBoundsInParent().getWidth() * (0.75));
        hardButton.setFitHeight(hardButton.getBoundsInParent().getHeight() * (0.75));
        hardButton.setLayoutX((width - hardButton.getFitWidth()) / 1.5);
        hardButton.setLayoutY(height * (0.60));

        hardButton.setOnMouseEntered(event -> {
            hardButton.setImage(new Image("maballv1/pic/Menu/HardButton2.png"));
            rtCircleForHardMode.setByAngle(360);
            rtCircleForHardMode.setCycleCount(Timeline.INDEFINITE);
            rtCircleForHardMode.play();
            bubble.play();
        });
        hardButton.setOnMouseExited(event -> {
            hardButton.setImage(new Image("maballv1/pic/Menu/HardButton1.png"));
            rtCircleForHardMode.stop();
            bubble.stop();
        });

        hardButton.setOnMouseClicked(event -> {
            try {
                mainScene = new Scene(highScoreScene2());
            } catch (IOException ex) {
                Logger.getLogger(MaBallV1.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(mainScene);
            stage.show();
        });

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });

        smallHomeButton.setOnMouseClicked(event -> {
            mainScene = new Scene(menuScene());
            stage.setScene(mainScene);
            stage.show();
        });

        modeSelect1PlayerPage.getChildren().add(backGround);
        modeSelect1PlayerPage.getChildren().add(selectMode);
        modeSelect1PlayerPage.getChildren().add(smallHomeButton);
        modeSelect1PlayerPage.getChildren().add(circleForEasyMode);
        modeSelect1PlayerPage.getChildren().add(circleForHardMode);
        modeSelect1PlayerPage.getChildren().add(easyButton);
        modeSelect1PlayerPage.getChildren().add(hardButton);

        return modeSelect1PlayerPage;
    }

    private Parent modeSelect1Player() {
        Pane modeSelect1PlayerPage = new Pane();
        modeSelect1PlayerPage.setPrefSize(width, height);
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        ImageView selectMode = new ImageView(new Image("maballv1/pic/Menu/SelectMode.png"));
        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
        ImageView circleForEasyMode = new ImageView(new Image("maballv1/pic/Menu/CircleForEasyMode.png"));
        ImageView circleForHardMode = new ImageView(new Image("maballv1/pic/Menu/CircleForHardMode.png"));
        ImageView easyButton = new ImageView(new Image("maballv1/pic/Menu/EasyButton1.png"));
        ImageView hardButton = new ImageView(new Image("maballv1/pic/Menu/HardButton1.png"));
        
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);
        
        FadeTransition selectModeFade = new FadeTransition(Duration.millis(1000), selectMode);
        FadeTransition easyButtonButtonFade = new FadeTransition(Duration.millis(1000), easyButton);
        FadeTransition hardButtonFade = new FadeTransition(Duration.millis(1000), hardButton);
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        FadeTransition circleForEasyModeFade = new FadeTransition(Duration.millis(1000), circleForEasyMode);
        FadeTransition circleForHardModeFade = new FadeTransition(Duration.millis(1000), circleForHardMode);
        
        selectModeFade.setFromValue(0.0);
        selectModeFade.setToValue(1);
        selectModeFade.play();
        
        easyButtonButtonFade.setFromValue(0.0);
        easyButtonButtonFade.setToValue(1);
        easyButtonButtonFade.play();
        
        hardButtonFade.setFromValue(0.0);
        hardButtonFade.setToValue(1);
        hardButtonFade.play();
        
        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();
        
        circleForEasyModeFade.setFromValue(0.0);
        circleForEasyModeFade.setToValue(1);
        circleForEasyModeFade.play();
        
        circleForHardModeFade.setFromValue(0.0);
        circleForHardModeFade.setToValue(1);
        circleForHardModeFade.play();
        
        selectMode.setFitWidth(selectMode.getBoundsInParent().getWidth() * (0.75));
        selectMode.setFitHeight(selectMode.getBoundsInParent().getHeight() * (0.75));
        selectMode.setLayoutX((width - selectMode.getFitWidth()) / 2.0);
        selectMode.setLayoutY(height * (0.05));

        circleForEasyMode.setFitWidth(circleForEasyMode.getBoundsInParent().getWidth() * (0.75));
        circleForEasyMode.setFitHeight(circleForEasyMode.getBoundsInParent().getHeight() * (0.75));
        circleForEasyMode.setLayoutX((width - circleForEasyMode.getFitWidth()) / 2.8);
        circleForEasyMode.setLayoutY(height * (0.325));
        RotateTransition rtCircleForEasyMode = new RotateTransition(Duration.millis(1000), circleForEasyMode);

        circleForHardMode.setFitWidth(circleForHardMode.getBoundsInParent().getWidth() * (0.75));
        circleForHardMode.setFitHeight(circleForHardMode.getBoundsInParent().getHeight() * (0.75));
        circleForHardMode.setLayoutX((width - circleForHardMode.getFitWidth()) / 1.525);
        circleForHardMode.setLayoutY(height * (0.325));
        RotateTransition rtCircleForHardMode = new RotateTransition(Duration.millis(1000), circleForHardMode);

        easyButton.setFitWidth(easyButton.getBoundsInParent().getWidth() * (0.75));
        easyButton.setFitHeight(easyButton.getBoundsInParent().getHeight() * (0.75));
        easyButton.setLayoutX((width - easyButton.getFitWidth()) / 3.0);
        easyButton.setLayoutY(height * (0.60));

        easyButton.setOnMouseClicked(event -> {
            mainScene = new Scene(onePlayerEnterNameScene(false));
            stage.setScene(mainScene);
            stage.show();
        });

        easyButton.setOnMouseEntered(event -> {
            easyButton.setImage(new Image("maballv1/pic/Menu/EasyButton2.png"));
            rtCircleForEasyMode.setByAngle(360);
            rtCircleForEasyMode.setCycleCount(Timeline.INDEFINITE);
            rtCircleForEasyMode.play();
            bubble.play();
        });
        easyButton.setOnMouseExited(event -> {
            easyButton.setImage(new Image("maballv1/pic/Menu/EasyButton1.png"));
            rtCircleForEasyMode.stop();
            bubble.stop();
        });

        hardButton.setFitWidth(hardButton.getBoundsInParent().getWidth() * (0.75));
        hardButton.setFitHeight(hardButton.getBoundsInParent().getHeight() * (0.75));
        hardButton.setLayoutX((width - hardButton.getFitWidth()) / 1.5);
        hardButton.setLayoutY(height * (0.60));

        hardButton.setOnMouseEntered(event -> {
            hardButton.setImage(new Image("maballv1/pic/Menu/HardButton2.png"));
            rtCircleForHardMode.setByAngle(360);
            rtCircleForHardMode.setCycleCount(Timeline.INDEFINITE);
            rtCircleForHardMode.play();
            bubble.play();
        });
        hardButton.setOnMouseExited(event -> {
            hardButton.setImage(new Image("maballv1/pic/Menu/HardButton1.png"));
            rtCircleForHardMode.stop();
            bubble.stop();
        });

        hardButton.setOnMouseClicked(event -> {
            mainScene = new Scene(onePlayerEnterNameScene(true));
            stage.setScene(mainScene);
            stage.show();
        });

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });

        smallHomeButton.setOnMouseClicked(event -> {
            mainScene = new Scene(menuScene());
            stage.setScene(mainScene);
            stage.show();
        });

        modeSelect1PlayerPage.getChildren().add(backGround);
        modeSelect1PlayerPage.getChildren().add(selectMode);
        modeSelect1PlayerPage.getChildren().add(smallHomeButton);
        modeSelect1PlayerPage.getChildren().add(circleForEasyMode);
        modeSelect1PlayerPage.getChildren().add(circleForHardMode);
        modeSelect1PlayerPage.getChildren().add(easyButton);
        modeSelect1PlayerPage.getChildren().add(hardButton);

        return modeSelect1PlayerPage;
    }

    private Parent modeSelect2Player() {
        Pane modeSelect1PlayerPage = new Pane();
        modeSelect1PlayerPage.setPrefSize(width, height);
        
        MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        ImageView selectMode = new ImageView(new Image("maballv1/pic/Menu/SelectMode.png"));
        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
        ImageView circleForEasyMode = new ImageView(new Image("maballv1/pic/Menu/CircleForEasyMode.png"));
        ImageView circleForHardMode = new ImageView(new Image("maballv1/pic/Menu/CircleForHardMode.png"));
        ImageView easyButton = new ImageView(new Image("maballv1/pic/Menu/EasyButton1.png"));
        ImageView hardButton = new ImageView(new Image("maballv1/pic/Menu/HardButton1.png"));

        backGround.setFitWidth(width);
        backGround.setFitHeight(height);
        
        FadeTransition selectModeFade = new FadeTransition(Duration.millis(1000), selectMode);
        FadeTransition easyButtonButtonFade = new FadeTransition(Duration.millis(1000), easyButton);
        FadeTransition hardButtonFade = new FadeTransition(Duration.millis(1000), hardButton);
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        FadeTransition circleForEasyModeFade = new FadeTransition(Duration.millis(1000), circleForEasyMode);
        FadeTransition circleForHardModeFade = new FadeTransition(Duration.millis(1000), circleForHardMode);
        
        selectModeFade.setFromValue(0.0);
        selectModeFade.setToValue(1);
        selectModeFade.play();
        
        easyButtonButtonFade.setFromValue(0.0);
        easyButtonButtonFade.setToValue(1);
        easyButtonButtonFade.play();
        
        hardButtonFade.setFromValue(0.0);
        hardButtonFade.setToValue(1);
        hardButtonFade.play();
        
        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();
        
        circleForEasyModeFade.setFromValue(0.0);
        circleForEasyModeFade.setToValue(1);
        circleForEasyModeFade.play();
        
        circleForHardModeFade.setFromValue(0.0);
        circleForHardModeFade.setToValue(1);
        circleForHardModeFade.play();

        selectMode.setFitWidth(selectMode.getBoundsInParent().getWidth() * (0.75));
        selectMode.setFitHeight(selectMode.getBoundsInParent().getHeight() * (0.75));
        selectMode.setLayoutX((width - selectMode.getFitWidth()) / 2.0);
        selectMode.setLayoutY(height * (0.05));

        circleForEasyMode.setFitWidth(circleForEasyMode.getBoundsInParent().getWidth() * (0.75));
        circleForEasyMode.setFitHeight(circleForEasyMode.getBoundsInParent().getHeight() * (0.75));
        circleForEasyMode.setLayoutX((width - circleForEasyMode.getFitWidth()) / 2.8);
        circleForEasyMode.setLayoutY(height * (0.325));
        RotateTransition rtCircleForEasyMode = new RotateTransition(Duration.millis(1000), circleForEasyMode);

        circleForHardMode.setFitWidth(circleForHardMode.getBoundsInParent().getWidth() * (0.75));
        circleForHardMode.setFitHeight(circleForHardMode.getBoundsInParent().getHeight() * (0.75));
        circleForHardMode.setLayoutX((width - circleForHardMode.getFitWidth()) / 1.525);
        circleForHardMode.setLayoutY(height * (0.325));
        RotateTransition rtCircleForHardMode = new RotateTransition(Duration.millis(1000), circleForHardMode);

        easyButton.setFitWidth(easyButton.getBoundsInParent().getWidth() * (0.75));
        easyButton.setFitHeight(easyButton.getBoundsInParent().getHeight() * (0.75));
        easyButton.setLayoutX((width - easyButton.getFitWidth()) / 3.0);
        easyButton.setLayoutY(height * (0.60));

        easyButton.setOnMouseEntered(event -> {
            easyButton.setImage(new Image("maballv1/pic/Menu/EasyButton2.png"));
            rtCircleForEasyMode.setByAngle(360);
            rtCircleForEasyMode.setCycleCount(Timeline.INDEFINITE);
            rtCircleForEasyMode.play();
            bubble.play();
        });
        easyButton.setOnMouseExited(event -> {
            easyButton.setImage(new Image("maballv1/pic/Menu/EasyButton1.png"));
            rtCircleForEasyMode.stop();
            bubble.stop();
        });

        easyButton.setOnMouseClicked(event -> {
            mainScene = new Scene(twoPlayerEnterNameScene(false));
            stage.setScene(mainScene);
            stage.show();
        });

        hardButton.setFitWidth(hardButton.getBoundsInParent().getWidth() * (0.75));
        hardButton.setFitHeight(hardButton.getBoundsInParent().getHeight() * (0.75));
        hardButton.setLayoutX((width - hardButton.getFitWidth()) / 1.5);
        hardButton.setLayoutY(height * (0.60));

        hardButton.setOnMouseEntered(event -> {
            hardButton.setImage(new Image("maballv1/pic/Menu/HardButton2.png"));
            rtCircleForHardMode.setByAngle(360);
            rtCircleForHardMode.setCycleCount(Timeline.INDEFINITE);
            rtCircleForHardMode.play();
            bubble.play();
        });
        hardButton.setOnMouseExited(event -> {
            hardButton.setImage(new Image("maballv1/pic/Menu/HardButton1.png"));
            rtCircleForHardMode.stop();
            bubble.stop();
        });

        hardButton.setOnMouseClicked(event -> {
            mainScene = new Scene(twoPlayerEnterNameScene(true));
            stage.setScene(mainScene);
            stage.show();
        });

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });

        smallHomeButton.setOnMouseClicked(event -> {
            mainScene = new Scene(menuScene());
            stage.setScene(mainScene);
            stage.show();
        });

        modeSelect1PlayerPage.getChildren().add(backGround);
        modeSelect1PlayerPage.getChildren().add(selectMode);
        modeSelect1PlayerPage.getChildren().add(smallHomeButton);
        modeSelect1PlayerPage.getChildren().add(circleForEasyMode);
        modeSelect1PlayerPage.getChildren().add(circleForHardMode);
        modeSelect1PlayerPage.getChildren().add(easyButton);
        modeSelect1PlayerPage.getChildren().add(hardButton);

        return modeSelect1PlayerPage;
    }

    private Parent onePlayerEnterNameScene(boolean isHard) {
        Pane onePlayerEnterNamePane = new Pane();
        onePlayerEnterNamePane.setPrefSize(width, height);
        ImageView backGround = new ImageView(new Image("maballv1/pic/1PlayerEnternames.png"));
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);
        
        Text errorMessage = new Text("Enter Player Name 1-8 Charactors");
        errorMessage.setFont(Font.font("Bariol Regular", 25));
        errorMessage.setLayoutX(510);
        errorMessage.setLayoutY(370);
        errorMessage.setFill(Color.web("#d54b1a"));

        TextField playerEnterName = new TextField();
        playerEnterName.setPromptText("Enter Player Name");
        playerEnterName.setFocusTraversable(false);
        playerEnterName.setMaxSize(355, 80);
        playerEnterName.textFormatterProperty();
        playerEnterName.setFont(Font.font("Bariol Regular", 40));
        
        FadeTransition playerEnterNameFade = new FadeTransition(Duration.millis(1000), playerEnterName);
        FadeTransition errorMessageFade = new FadeTransition(Duration.millis(1000), errorMessage);
        FadeTransition backGroundFade = new FadeTransition(Duration.millis(1000), backGround);
        
        backGroundFade.setFromValue(0.0);
        backGroundFade.setToValue(1);
        backGroundFade.play();
        
        playerEnterNameFade.setFromValue(0.0);
        playerEnterNameFade.setToValue(1);
        playerEnterNameFade.play();
        
        errorMessageFade.setFromValue(0.0);
        errorMessageFade.setToValue(1);
        errorMessageFade.play();

        StackPane StackX = new StackPane();
        StackX.setPrefSize(1372.5, 577);

        onePlayerEnterNamePane.getChildren().add(backGround);
        onePlayerEnterNamePane.getChildren().add(StackX);
        StackX.getChildren().add(playerEnterName);
        onePlayerEnterNamePane.getChildren().add(errorMessage);

        playerEnterName.setOnKeyPressed(enterName -> {
            if (enterName.getCode() == KeyCode.ENTER) {
                if (playerEnterName.getText().length() <= 8) {
                    BGM.stop();
                    if (!isHard) {BGM.stop();
                        try {
                            EzMode1Player mode = new EzMode1Player(playerEnterName.getText());
                            mode.start(stage);
                        } catch (IllegalArgumentException e) {
                            EzMode1Player mode = new EzMode1Player(playerEnterName.getText());
                            mode.start(stage);
                        }

                    } else if (isHard) {
                        try {
                            HardMode1Player mode = new HardMode1Player(playerEnterName.getText());
                            mode.start(stage);
                        } catch (IllegalArgumentException e) {
                            HardMode1Player mode = new HardMode1Player(playerEnterName.getText());
                            mode.start(stage);
                        }
                    }

                }
            }
        });

        return onePlayerEnterNamePane;
    }

    private Parent twoPlayerEnterNameScene(boolean isHard) {
        Pane twoPlayerEnterNamePane = new Pane();
        twoPlayerEnterNamePane.setPrefSize(width, height);
        ImageView backGround = new ImageView(new Image("maballv1/pic/2PlayersEnternames.png"));
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);
        
        Text errorMessage = new Text("Enter Player Name 1-8 Charactors");
        errorMessage.setFont(Font.font("Bariol Regular", 25));
        errorMessage.setLayoutX(525);
        errorMessage.setLayoutY(315);
        errorMessage.setFill(Color.web("#d54b1a"));

        Text errorMessage2 = new Text("Enter Player Name 1-8 Charactors");
        errorMessage2.setFont(Font.font("Bariol Regular", 25));
        errorMessage2.setLayoutX(525);
        errorMessage2.setLayoutY(450);
        errorMessage2.setFill(Color.web("#d54b1a"));

        TextField player1EnterName = new TextField();
        player1EnterName.setPromptText("Enter Player Name");
        player1EnterName.setFocusTraversable(false);
        player1EnterName.setPrefSize(355, 80);
        player1EnterName.textFormatterProperty();
        player1EnterName.setFont(Font.font("Bariol Regular", 40));
        player1EnterName.setLayoutX(525);
        player1EnterName.setLayoutY(202);

        TextField player2EnterName = new TextField();
        player2EnterName.setPromptText("Enter Player Name");
        player2EnterName.setFocusTraversable(false);
        player2EnterName.setPrefSize(355, 80);
        player2EnterName.textFormatterProperty();
        player2EnterName.setFont(Font.font("Bariol Regular", 40));
        player2EnterName.setLayoutX(525);
        player2EnterName.setLayoutY(336);
        
        FadeTransition player1EnterNameFade = new FadeTransition(Duration.millis(1000), player1EnterName);
        FadeTransition player2EnterNameFade = new FadeTransition(Duration.millis(1000), player2EnterName);
        FadeTransition errorMessageFade = new FadeTransition(Duration.millis(1000), errorMessage);
        FadeTransition errorMessage2Fade = new FadeTransition(Duration.millis(1000), errorMessage2);
        FadeTransition backGroundFade = new FadeTransition(Duration.millis(1000), backGround);
        
        backGroundFade.setFromValue(0.0);
        backGroundFade.setToValue(1);
        backGroundFade.play();
        
        player1EnterNameFade.setFromValue(0.0);
        player1EnterNameFade.setToValue(1);
        player1EnterNameFade.play();
        
        player2EnterNameFade.setFromValue(0.0);
        player2EnterNameFade.setToValue(1);
        player2EnterNameFade.play();
        
        errorMessageFade.setFromValue(0.0);
        errorMessageFade.setToValue(1);
        errorMessageFade.play();
        
        errorMessage2Fade.setFromValue(0.0);
        errorMessage2Fade.setToValue(1);
        errorMessage2Fade.play();

        twoPlayerEnterNamePane.getChildren().add(backGround);
        twoPlayerEnterNamePane.getChildren().add(player1EnterName);
        twoPlayerEnterNamePane.getChildren().add(player2EnterName);
        twoPlayerEnterNamePane.getChildren().add(errorMessage);
        twoPlayerEnterNamePane.getChildren().add(errorMessage2);

        player1EnterName.setOnKeyPressed(enterName -> {
            if (enterName.getCode() == KeyCode.ENTER) {
                if (player1EnterName.getText().length() <= 12 && player2EnterName.getText().length() <= 8) {
                    BGM.stop();
                    if (!isHard) {
                        try {
                            EzMode2Player mode = new EzMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        } catch (IllegalArgumentException e) {
                            EzMode2Player mode = new EzMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        }
                    } else if (isHard) {
                        try {
                            HardMode2Player mode = new HardMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        } catch (IllegalArgumentException e) {
                            HardMode2Player mode = new HardMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        }
                    }

                }
            }
        });

        player2EnterName.setOnKeyPressed(enterName -> {
            if (enterName.getCode() == KeyCode.ENTER) {
                if (player1EnterName.getText().length() <= 12 && player2EnterName.getText().length() <= 8) {
                    BGM.stop();
                    if (!isHard) {
                        try {
                            EzMode2Player mode = new EzMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        } catch (IllegalArgumentException e) {
                            EzMode2Player mode = new EzMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        }
                    } else if (isHard) {
                        try {
                            HardMode2Player mode = new HardMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        } catch (IllegalArgumentException e) {
                            HardMode2Player mode = new HardMode2Player(player1EnterName.getText(), player2EnterName.getText());
                            mode.start(stage);
                        }
                    }
                }
            }
        });

        return twoPlayerEnterNamePane;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
