/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maballv1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EzMode1Player extends Application {

    final int height = 600;
    final int width = 1200;
    private final int mode = 0;
    private Pane onePlayerGamePage;
    private Scene mainScene;
    private Stage stage;
    private int randomY;
    private int waitFinish = 0;
    private StackPane stack;
    private PlayerEzMode player;
    private AudioClip spark = new AudioClip(new File("spark.mp3").toURI().toString());
    private AudioClip pop = new AudioClip(new File("Pop.mp3").toURI().toString());
    private MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
    private MediaPlayer win = new MediaPlayer(new Media(new File("winner.mp3").toURI().toString()));
   // private AudioClip BGM = new AudioClip(new File("BGM_play.mp3").toURI().toString());
    private MediaPlayer BGM = new MediaPlayer(new Media(new File("BGM_play.mp3").toURI().toString()));
    
    public EzMode1Player(String n) {
        player = new PlayerEzMode();
        player.setPlayerName(n);
    }

    @Override
    public void start(Stage primaryStage) {
        BGM.setVolume(0.2);
        BGM.setCycleCount(Timeline.INDEFINITE);
        BGM.play();
        stage = primaryStage;
        stack = new StackPane();
        onePlayerGamePage = new Pane();
        onePlayerGamePage.setPrefSize(width, height);
        stack.setPrefSize(width, height);

        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        win.setCycleCount(1);
        win.setStopTime(Duration.millis(2000));

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/1PlayerFullGamePage.png"));
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);
        // player.playerSetXandSetY((width - player.getWidth()) / 2, (height - player.getHeight()) / 2);
        Node nodePlayer = player.getPlayerSprite();

        onePlayerGamePage.getChildren().add(backGround);
        stack.getChildren().add(nodePlayer);

        Text score = new Text(Integer.toString(player.getPlayerScore()));
        Text playerName = new Text(player.getPlayerName());
        score.setFont(Font.font("Futura-Normal", 45));

        playerName.setFont(Font.font("Bariol Regular", 45));
        playerName.setFill(Color.web("#3e3024"));
        playerName.setLayoutX(150);
        playerName.setLayoutY(50);

        onePlayerGamePage.getChildren().add(playerName);
        stack.getChildren().add(score);
        onePlayerGamePage.getChildren().add(stack);

        mainScene = new Scene(onePlayerGamePage);

        primaryStage.setTitle("MaBall Never Reach The Edge");
        primaryStage.setScene(mainScene);

        System.out.println(Arrays.toString(player.getColorList()));

        FadeTransition playerFade = new FadeTransition(Duration.millis(1000), nodePlayer);
        FadeTransition scoreFade = new FadeTransition(Duration.millis(1000), score);
        FadeTransition backGroundFade = new FadeTransition(Duration.millis(1000), backGround);
        FadeTransition playerNameFade = new FadeTransition(Duration.millis(1000), playerName);

        playerFade.setFromValue(0.0);
        playerFade.setToValue(1);
        playerFade.play();

        scoreFade.setFromValue(0.0);
        scoreFade.setToValue(1);
        scoreFade.play();

        playerNameFade.setFromValue(0.0);
        playerNameFade.setToValue(1);
        playerNameFade.play();

        backGroundFade.setFromValue(0.0);
        backGroundFade.setToValue(1);
        backGroundFade.play();

        Timeline x = new Timeline(new KeyFrame(Duration.millis(1500), (ActionEvent e) -> {
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    spawnBall(player);
                    score.setText(Integer.toString(player.getPlayerScore()));
                    score.setFill(Color.web("#3e3024"));
                }
            }.start();
        }
        ));
        x.play();

        primaryStage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    if (player.isPressStatus()) {
                        player.setPressStatus(false);
                        RotateTransition rt = new RotateTransition(Duration.millis(150), nodePlayer);
                        rt.setByAngle(180);
                        rt.setCycleCount(1);
                        rt.play();
                        rt.setOnFinished((ActionEvent e) -> {
                            player.setPressStatus(true);
                        });
                        new Timeline(new KeyFrame(Duration.millis(150 / 2), (ActionEvent e) -> {
                            player.swapPlayerStat();
                        })).play();
                    }
                    break;
                default:
            }
        });

        primaryStage.show();
    }

    private void spawnBall(PlayerEzMode player) {

        if (waitFinish == 0) {

            BallEzMode ballObj = new BallEzMode(player.getColorListRandom());
            waitFinish = 1;
            randomY = (int) ((Math.random()) * 2);

            if (randomY == 0) {
                ballObj.ballSetXandSetY((width - ballObj.getWidth()) / 2.0, -100);
            } else if (randomY == 1) {
                ballObj.ballSetXandSetY((width - ballObj.getWidth()) / 2.0, height + 100);
            }

            onePlayerGamePage.getChildren().add(ballObj.getPlayerSprite());
            new AnimationTimer() {
                private BallEzMode ballObj;

                int randomSpeed = 4 + (int) ((Math.random()) * 2);

                @Override
                public void handle(long now) {
                    ScaleTransition ballScale = new ScaleTransition(Duration.millis(400), ballObj.getPlayerSprite());
                    FadeTransition ballFade = new FadeTransition(Duration.millis(400), ballObj.getPlayerSprite());
                    if (randomY == 0) {
                        ballObj.setPositionY(ballObj.getYPosition() + randomSpeed);
                        if (ballObj.getPlayerSprite().getBoundsInParent().intersects(player.getPlayerSprite().getBoundsInParent())) {

                            if (player.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) == 0) {
                                waitFinish = 0;
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                player.addScore();
                                pop.stop();
                                pop.play();
                                ballObj = null;
                                ballFade = null;
                                ballScale = null;
                                this.stop();
                            } else if (player.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) != 0) {
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                spark.stop();
                                spark.play();
                                ballFade.setOnFinished((ActionEvent e) -> {
                                    try {
                                        mainScene = new Scene(GameOverScene(player));
                                        stage.setScene(mainScene);
                                        stage.show();
                                        win.play();
                                        this.stop();
                                    } catch (IOException ex) {

                                    }
                                });
                                this.stop();
                            }
                        }
                    } else if (randomY == 1) {
                        ballObj.setPositionY(ballObj.getYPosition() - randomSpeed);
//                        ScaleTransition ballScale = new ScaleTransition(Duration.millis(400), ballObj.getPlayerSprite());
//                        FadeTransition ballFade = new FadeTransition(Duration.millis(400), ballObj.getPlayerSprite());
                        if (ballObj.getPlayerSprite().getBoundsInParent().intersects(player.getPlayerSprite().getBoundsInParent())) {
                            if (player.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) == 0) {
                                waitFinish = 0;
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                player.addScore();
                                pop.stop();
                                pop.play();
                                ballObj = null;
                                ballFade = null;
                                ballScale = null;
                                this.stop();
                            } else if (player.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) != 0) {
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                spark.stop();
                                spark.play();
                                ballFade.setOnFinished((ActionEvent e) -> {
                                    try {
                                        mainScene = new Scene(GameOverScene(player));
                                        win.play();
                                        this.stop();
                                        stage.setScene(mainScene);
                                        stage.show();
                                    } catch (IOException ex) {

                                    }

                                });
                                this.stop();

                            }
                        }
                    }
                }

                private AnimationTimer init(BallEzMode ball) {
                    ballObj = ball;
                    return this;
                }
            }.init(ballObj).start();
        }
    }

    private Parent GameOverScene(PlayerEzMode player) throws IOException {
        Pane GameOverPage = new Pane();
        StackPane Box = new StackPane();
        StackPane displayScoreBox = new StackPane();
        GameOverPage.setPrefSize(width, height);
        Box.setPrefSize(width, height);
        displayScoreBox.setPrefSize(width, height * 0.5);
        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/1PlayerFullGamePage.png"));

        Sprite playAgainButton = new Sprite("maballv1/pic/Menu/PlayAgainButton1.png");
        Sprite highScoreForGameover = new Sprite("maballv1/pic/Menu/HighScoreForGameover1.png");
        Sprite smallHomeButton = new Sprite("maballv1/pic/Menu/SmallHomeButton1.png");

        playAgainButton.getPlayerSprite().setVisible(false);
        highScoreForGameover.getPlayerSprite().setVisible(false);
        smallHomeButton.getPlayerSprite().setVisible(false);

        playAgainButton.playerSetXandSetY((width - playAgainButton.getWidth()) / 4 - 45, height * 0.75);
        highScoreForGameover.playerSetXandSetY((width - playAgainButton.getWidth()) / 1.75 - 45, height * 0.75);
        smallHomeButton.playerSetXandSetY((width - playAgainButton.getWidth()) / 1.1 - 45, height * 0.75);
        smallHomeButton.setHeight(smallHomeButton.getHeight() * 1.25);
        smallHomeButton.setWidth(smallHomeButton.getWidth() * 1.25);

        smallHomeButton.getPlayerSprite().setOnMouseEntered(event -> {
            smallHomeButton.setPlayerSprite("maballv1/pic/Menu/SmallHomeButton2.png");
            bubble.stop();
            bubble.play();
        });
        smallHomeButton.getPlayerSprite().setOnMouseExited(event -> {
            bubble.stop();
            smallHomeButton.setPlayerSprite("maballv1/pic/Menu/SmallHomeButton1.png");
        });
        smallHomeButton.getPlayerSprite().setOnMouseClicked(event -> {
            BGM.stop();
            win.stop();
            MaBallV1 reStage = new MaBallV1();
            reStage.start(stage);
        });

        playAgainButton.getPlayerSprite().setOnMouseEntered(event -> {
            playAgainButton.setPlayerSprite("maballv1/pic/Menu/PlayAgainButton2.png");
            bubble.stop();
            bubble.play();
        });
        playAgainButton.getPlayerSprite().setOnMouseExited(event -> {
            playAgainButton.setPlayerSprite("maballv1/pic/Menu/PlayAgainButton1.png");
            bubble.stop();
        });
        playAgainButton.getPlayerSprite().setOnMouseClicked(event -> {
            BGM.stop();
            win.stop();
            try {
                EzMode1Player reStage = new EzMode1Player(player.getPlayerName());
                reStage.start(stage);
            } catch (IllegalArgumentException e) {
                EzMode1Player reStage = new EzMode1Player(player.getPlayerName());
                reStage.start(stage);
            }
        });

        highScoreForGameover.getPlayerSprite().setOnMouseEntered(event -> {
            highScoreForGameover.setPlayerSprite("maballv1/pic/Menu/HighScoreForGameover2.png");
            bubble.stop();
            bubble.play();
        });
        highScoreForGameover.getPlayerSprite().setOnMouseExited(event -> {
            highScoreForGameover.setPlayerSprite("maballv1/pic/Menu/HighScoreForGameover1.png");
            bubble.stop();
        });
        highScoreForGameover.getPlayerSprite().setOnMouseClicked(event -> {
            win.stop();
            try {
                mainScene = new Scene(highScoreScene());
            } catch (IOException ex) {
                Logger.getLogger(EzMode1Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(mainScene);
            stage.show();
        });

        String[] nameList = new String[5];
        String[] scoreList = new String[5];

        readName1(nameList, scoreList);
        writeScore(nameList, scoreList, player.getPlayerScore(), player.getPlayerName());

        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        Text score = new Text(Integer.toString(player.getPlayerScore()));
        score.setFont(Font.font("Futura-Normal", 45));
        score.setFill(Color.web("#3e3024"));

        Box.getChildren().add(score);

        Text scoreDisplay = new Text("Score : " + Integer.toString(player.getPlayerScore()));
        scoreDisplay.setVisible(false);
        scoreDisplay.setFont(Font.font("Bariol Regular", FontWeight.LIGHT, 60));
        scoreDisplay.setFill(Color.web("#3e3024"));
        Text playerName = new Text(player.getPlayerName());
        playerName.setFill(Color.web("#3e3024"));
        playerName.setFont(Font.font("Bariol Regular", FontWeight.LIGHT, 45));
        playerName.setLayoutX(150);
        playerName.setLayoutY(50);

        Timeline x = new Timeline(new KeyFrame(Duration.millis(1), (ActionEvent e) -> {
            playAgainButton.getPlayerSprite().setVisible(true);
            highScoreForGameover.getPlayerSprite().setVisible(true);
            smallHomeButton.getPlayerSprite().setVisible(true);
            scoreDisplay.setVisible(true);
        }));
        x.play();

        ScaleTransition scoreDisplayTran = new ScaleTransition(Duration.millis(1000), scoreDisplay);
        scoreDisplayTran.setFromX(1.5);
        scoreDisplayTran.setToX(1);
        scoreDisplayTran.play();

        FadeTransition score1DisplayFade = new FadeTransition(Duration.millis(1000), scoreDisplay);
        score1DisplayFade.setFromValue(0);
        score1DisplayFade.setToValue(1);
        score1DisplayFade.play();

        FadeTransition pAG = new FadeTransition(Duration.millis(1000), playAgainButton.getPlayerSprite());
        pAG.setFromValue(0);
        pAG.setToValue(1);
        pAG.play();

        FadeTransition hSG = new FadeTransition(Duration.millis(1000), highScoreForGameover.getPlayerSprite());
        hSG.setFromValue(0);
        hSG.setToValue(1);
        hSG.play();

        FadeTransition sHB = new FadeTransition(Duration.millis(1000), smallHomeButton.getPlayerSprite());
        sHB.setFromValue(0);
        sHB.setToValue(1);
        sHB.play();

        displayScoreBox.getChildren().add(scoreDisplay);
        Box.getChildren().add(player.getPlayerSprite());
        GameOverPage.getChildren().add(backGround);
        GameOverPage.getChildren().add(Box);
        GameOverPage.getChildren().add(displayScoreBox);
        // GameOverPage.getChildren().add(player.getPlayerSprite());
        GameOverPage.getChildren().add(playAgainButton.getPlayerSprite());
        GameOverPage.getChildren().add(highScoreForGameover.getPlayerSprite());
        GameOverPage.getChildren().add(smallHomeButton.getPlayerSprite());
        GameOverPage.getChildren().add(playerName);
        return GameOverPage;
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

    public void writeScore(String[] a, String[] b, int score, String name) {
        String[] c = new String[5];
        String[] d = new String[5];
        int i;
        for (i = 0; i < 5; i++) {

            if (Integer.parseInt(b[i]) < score) {
                System.out.println("yeah");
                c[i] = Integer.toString(score);
                d[i] = name;
                System.out.println(c[i]);
                i++;
                break;
            }
            c[i] = b[i];
            d[i] = a[i];
        }
        for (int j = i - 1; i < 5; j++) {
            c[i] = b[j];
            d[i++] = a[j];
        }
        try {
            FileWriter writer = new FileWriter("score1.txt", false);
            FileWriter writer2 = new FileWriter("name1.txt", false);
            try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                for (int j = 0; j < 5; j++) {
                    bufferedWriter.write(c[j]);
                    bufferedWriter.newLine();
                }

            }
            try (BufferedWriter bufferedWriter2 = new BufferedWriter(writer2)) {
                for (int j = 0; j < 5; j++) {
                    bufferedWriter2.write(d[j]);
                    bufferedWriter2.newLine();
                }

            }
        } catch (IOException e) {
        }
        // System.out.println(Arrays.toString(c));
        // System.out.println(Arrays.toString(d));
    }

    private Parent highScoreScene() throws IOException {
        Pane highScorePage = new Pane();
        StackPane highScoreStack = new StackPane();
        StackPane highScoreTableStack = new StackPane();

        highScorePage.setPrefSize(width, height);
        highScoreStack.setPrefSize(width, height * 0.3);
        highScoreTableStack.setPrefSize(width, height * 1.25);

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/Background.png"));
        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        ImageView smallHomeButton = new ImageView(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
        Sprite highscoreBanner = new Sprite("maballv1/pic/Menu/Highscore.png");
        Sprite highscoreTable = new Sprite("maballv1/pic/Menu/HighscoreTable_1.png");

        FadeTransition highscoreBannerFade = new FadeTransition(Duration.millis(1000), highscoreBanner.getPlayerSprite());
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton);
        FadeTransition highscoreTableFade = new FadeTransition(Duration.millis(1000), highscoreTable.getPlayerSprite());

        highscoreBannerFade.setFromValue(0.0);
        highscoreBannerFade.setToValue(1);
        highscoreBannerFade.play();

        smallHomeButtonFade.setFromValue(0.0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();

        highscoreTableFade.setFromValue(0.0);
        highscoreTableFade.setToValue(1);
        highscoreTableFade.play();

        smallHomeButton.setFitWidth(smallHomeButton.getBoundsInParent().getWidth() * (0.75));
        smallHomeButton.setFitHeight(smallHomeButton.getBoundsInParent().getHeight() * (0.75));
        smallHomeButton.setLayoutX((width - smallHomeButton.getFitWidth()) / 1.04);
        smallHomeButton.setLayoutY(height * (0.85));

        smallHomeButton.setOnMouseEntered(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton2.png"));
            bubble.stop();
            bubble.play();
        });
        smallHomeButton.setOnMouseExited(event -> {
            smallHomeButton.setImage(new Image("maballv1/pic/Menu/SmallHomeButton1.png"));
            bubble.stop();
        });
        smallHomeButton.setOnMouseClicked(event -> {
            BGM.stop();
            win.stop();
            MaBallV1 reStage = new MaBallV1();
            reStage.start(stage);
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
        highScoreStack.getChildren().add(highscoreBanner.getPlayerSprite());
        highScoreTableStack.getChildren().add(highscoreTable.getPlayerSprite());
        highScorePage.getChildren().add(backGround);
        highScorePage.getChildren().add(highScoreStack);
        highScorePage.getChildren().add(highScoreTableStack);
        highScorePage.getChildren().add(smallHomeButton);
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
        for (int i = 0; i < 5; i++) {
            nameFade[i] = new FadeTransition(Duration.millis(1000), name[i]);
            nameFade[i].setFromValue(0.0);
            nameFade[i].setToValue(1);
            nameFade[i].play();
        }

        FadeTransition[] scoreFade = new FadeTransition[5];
        for (int i = 0; i < 5; i++) {
            scoreFade[i] = new FadeTransition(Duration.millis(1000), score[i]);
            scoreFade[i].setFromValue(0.0);
            scoreFade[i].setToValue(1);
            scoreFade[i].play();
        }

        return highScorePage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
