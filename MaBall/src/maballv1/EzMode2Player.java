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
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EzMode2Player extends Application {

    final int height = 600;
    final int width = 1200;
    private final int mode = 0;
    private Pane twoPlayerGamePage;
    private Scene mainScene;
    private Stage stage;
    private int randomY;
    private int randomY2;
    private int waitFinish = 0;
    private int waitFinish2 = 0;
    private StackPane stack;
    private StackPane stack2;
    private PlayerEzMode player1;
    private PlayerEzMode player2;
    private AudioClip spark = new AudioClip(new File("spark.mp3").toURI().toString());
    private AudioClip pop = new AudioClip(new File("Pop.mp3").toURI().toString());
    private AudioClip pop2 = new AudioClip(new File("Pop.mp3").toURI().toString());
    private AudioClip lose = new AudioClip(new File("lose.mp3").toURI().toString());
    private MediaPlayer bubble = new MediaPlayer(new Media(new File("Bubble.mp3").toURI().toString()));
    private MediaPlayer win = new MediaPlayer(new Media(new File("winner.mp3").toURI().toString()));
    //private AudioClip BGM = new AudioClip(new File("BGM_play.mp3").toURI().toString());
    private MediaPlayer BGM = new MediaPlayer(new Media(new File("BGM_play.mp3").toURI().toString()));
    
    public EzMode2Player(String n1, String n2) {
        player1 = new PlayerEzMode();
        player1.setPlayerName(n1);
        player2 = new PlayerEzMode();
        player2.setPlayerName(n2);
    }

    @Override
    public void start(Stage primaryStage) {
        BGM.setVolume(0.2);
        BGM.setCycleCount(Timeline.INDEFINITE);
        BGM.play();
        stage = primaryStage;
        stack = new StackPane();
        stack2 = new StackPane();
        twoPlayerGamePage = new Pane();
        twoPlayerGamePage.setPrefSize(width, height);
        stack.setPrefSize(width / 2, height);
        stack2.setPrefSize(width * 1.5, height);

        bubble.setCycleCount(1);
        bubble.setStartTime(Duration.millis(500));
        bubble.setStopTime(Duration.millis(525));

        win.setCycleCount(1);
        win.setStopTime(Duration.millis(2000));

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/FullGamepage2.png"));
        ImageView stick = new ImageView(new Image("maballv1/pic/Menu/LongStick.png"));

        stick.setFitHeight(600);
        stick.setLayoutX(width / 2);

        FadeTransition stickFade = new FadeTransition(Duration.millis(1000), stick);
        stickFade.setFromValue(0.0);
        stickFade.setToValue(1);
        stickFade.play();

        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        Node nodePlayer = player1.getPlayerSprite();
        Node nodePlayer2 = player2.getPlayerSprite();
        twoPlayerGamePage.getChildren().add(backGround);
        stack.getChildren().add(nodePlayer);
        stack2.getChildren().add(nodePlayer2);

        Text score1 = new Text(Integer.toString(player1.getPlayerScore()));
        Text player1Name = new Text(player1.getPlayerName());
        score1.setFont(Font.font("Futura-Normal", 45));

        Text score2 = new Text(Integer.toString(player2.getPlayerScore()));
        Text player2Name = new Text(player2.getPlayerName());
        score2.setFont(Font.font("Futura-Normal", 45));

        FadeTransition player1Fade = new FadeTransition(Duration.millis(1000), nodePlayer);
        FadeTransition player2Fade = new FadeTransition(Duration.millis(1000), nodePlayer2);
        FadeTransition score1Fade = new FadeTransition(Duration.millis(1000), score1);
        FadeTransition score2Fade = new FadeTransition(Duration.millis(1000), score2);
        FadeTransition backGroundFade = new FadeTransition(Duration.millis(1000), backGround);
        FadeTransition player1NameFade = new FadeTransition(Duration.millis(1000), player1Name);
        FadeTransition player2NameFade = new FadeTransition(Duration.millis(1000), player2Name);

        player1Fade.setFromValue(0.0);
        player1Fade.setToValue(1);
        player1Fade.play();

        player2Fade.setFromValue(0.0);
        player2Fade.setToValue(1);
        player2Fade.play();

        score1Fade.setFromValue(0.0);
        score1Fade.setToValue(1);
        score1Fade.play();

        score2Fade.setFromValue(0.0);
        score2Fade.setToValue(1);
        score2Fade.play();

        player1NameFade.setFromValue(0.0);
        player1NameFade.setToValue(1);
        player1NameFade.play();

        player2NameFade.setFromValue(0.0);
        player2NameFade.setToValue(1);
        player2NameFade.play();

        backGroundFade.setFromValue(0.0);
        backGroundFade.setToValue(1);
        backGroundFade.play();

        player1Name.setFont(Font.font("Bariol Regular", 45));
        player1Name.setFill(Color.web("#3e3024"));
        player1Name.setLayoutX(195);
        player1Name.setLayoutY(50);

        player2Name.setFont(Font.font("Bariol Regular", 45));
        player2Name.setFill(Color.web("#3e3024"));
        player2Name.setLayoutX(800);
        player2Name.setLayoutY(50);

        twoPlayerGamePage.getChildren().add(stick);
        twoPlayerGamePage.getChildren().add(player1Name);
        twoPlayerGamePage.getChildren().add(player2Name);
        stack.getChildren().add(score1);
        stack2.getChildren().add(score2);
        twoPlayerGamePage.getChildren().add(stack);
        twoPlayerGamePage.getChildren().add(stack2);

        mainScene = new Scene(twoPlayerGamePage);

        primaryStage.setTitle("MaBall Never Reach The Edge");
        primaryStage.setScene(mainScene);

        System.out.println(player2.getPlayerStatus(0) + player2.getPlayerStatus(1));
        Timeline x = new Timeline(new KeyFrame(Duration.millis(1500), (ActionEvent e) -> {
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (!player1.isIsDeath()) {
                        spawnBall1(player1);
                    }
                    if (!player2.isIsDeath()) {
                        spawnBall2(player2);
                    }
                    if (player1.isIsDeath() && player2.isIsDeath()) {
                        try {
                            mainScene = new Scene(GameOverScene(player1, player2));
                        } catch (IOException ex) {
                            Logger.getLogger(EzMode2Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        this.stop();
                        stage.setScene(mainScene);
                        stage.show();

                    }

                    score1.setText(Integer.toString(player1.getPlayerScore()));
                    score1.setFill(Color.web("#3e3024"));

                    score2.setText(Integer.toString(player2.getPlayerScore()));
                    score2.setFill(Color.web("#3e3024"));
                }
            }.start();
        }
        ));
        x.play();

        primaryStage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    if (player1.isPressStatus()) {
                        player1.setPressStatus(false);
                        RotateTransition rt = new RotateTransition(Duration.millis(150), nodePlayer);
                        rt.setByAngle(180);
                        rt.setCycleCount(1);
                        rt.play();
                        rt.setOnFinished((ActionEvent e) -> {
                            player1.setPressStatus(true);
                        });
                        new Timeline(new KeyFrame(Duration.millis(150 / 2), (ActionEvent e) -> {
                            player1.swapPlayerStat();
                        })).play();
                    }
                    break;
                case UP:
                    if (player2.isPressStatus()) {
                        player2.setPressStatus(false);
                        RotateTransition rt2 = new RotateTransition(Duration.millis(150), nodePlayer2);
                        rt2.setByAngle(180);
                        rt2.setCycleCount(1);
                        rt2.play();
                        rt2.setOnFinished((ActionEvent e) -> {
                            player2.setPressStatus(true);
                        });
                        new Timeline(new KeyFrame(Duration.millis(150 / 2), (ActionEvent e) -> {
                            player2.swapPlayerStat();
                        })).play();
                    }
                    break;
                default:
            }
        });

        primaryStage.show();
    }

    private void spawnBall1(PlayerEzMode player1) {

        if (waitFinish == 0) {

            BallEzMode ballObj = new BallEzMode(player1.getColorListRandom());
            waitFinish = 1;
            randomY = (int) ((Math.random()) * 2);

            if (randomY == 0) {
                ballObj.ballSetXandSetY((width - ballObj.getWidth()) / 4.05, -100);
            } else if (randomY == 1) {
                ballObj.ballSetXandSetY((width - ballObj.getWidth()) / 4.05, height + 100);
            }

            twoPlayerGamePage.getChildren().add(ballObj.getPlayerSprite());
            new AnimationTimer() {
                private BallEzMode ballObj;
                int randomSpeed = 4 + (int) ((Math.random()) * 2);

                @Override
                public void handle(long now) {
                    Sprite youLose = new Sprite("maballv1/pic/Menu/YouLose.png");
                    StackPane dBox = new StackPane();
                    dBox.setPrefSize(width / 2, height * 1.45);

                    ScaleTransition ballScale = new ScaleTransition(Duration.millis(400), ballObj.getPlayerSprite());
                    FadeTransition ballFade = new FadeTransition(Duration.millis(400), ballObj.getPlayerSprite());

                    ScaleTransition gameOverScale = new ScaleTransition(Duration.millis(700), youLose.getPlayerSprite());
                    FadeTransition gameOverFade = new FadeTransition(Duration.millis(700), youLose.getPlayerSprite());

                    if (randomY == 0) {
                        ballObj.setPositionY(ballObj.getYPosition() + randomSpeed);
                        if (ballObj.getPlayerSprite().getBoundsInParent().intersects(player1.getPlayerSprite().getBoundsInParent())) {
                            if (player1.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) == 0) {
                                waitFinish = 0;
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                pop.stop();
                                pop.play();
                                ballObj = null;
                                ballFade = null;
                                ballScale = null;
                                player1.addScore();
                                this.stop();
                            } else if (player1.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) != 0) {
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                spark.stop();
                                spark.play();
                                ballFade.setOnFinished((ActionEvent e) -> {
                                    player1.setIsDeath(true);
                                });
                                // youLose.playerSetXandSetY(135, height * 0.70);
                                youLose.setHeight(youLose.getHeight());
                                youLose.setWidth(youLose.getWidth());
                                if (!player2.isIsDeath()) {
                                    Timeline x = new Timeline(new KeyFrame(Duration.millis(300), (ActionEvent e) -> {
                                        lose.stop();
                                        lose.play();
                                        dBox.getChildren().add(youLose.getPlayerSprite());
                                        twoPlayerGamePage.getChildren().add(dBox);
                                    }));
                                    x.play();
                                    gameOverScale.setFromX(0);
                                    gameOverScale.setFromY(0);
                                    gameOverScale.setToX(1);
                                    gameOverScale.setToY(1);
                                    gameOverFade.setFromValue(0.0);
                                    gameOverFade.setToValue(1.0);
                                    gameOverFade.play();
                                    gameOverScale.play();
                                }
                                this.stop();
                            }
                        }
                    } else if (randomY == 1) {
                        ballObj.setPositionY(ballObj.getYPosition() - randomSpeed);
                        if (ballObj.getPlayerSprite().getBoundsInParent().intersects(player1.getPlayerSprite().getBoundsInParent())) {
                            if (player1.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) == 0) {
                                waitFinish = 0;
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                pop.stop();
                                pop.play();
                                ballObj = null;
                                ballFade = null;
                                ballScale = null;
                                player1.addScore();
                                this.stop();
                            } else if (player1.getPlayerStatus(randomY).compareTo(ballObj.getBallStatus()) != 0) {
                                ballFade.setFromValue(0.5);
                                ballFade.setToValue(0.0);
                                ballScale.setByX(10f);
                                ballScale.setByY(10f);
                                ballScale.play();
                                ballFade.play();
                                spark.stop();
                                spark.play();
                                ballFade.setOnFinished((ActionEvent e) -> {
                                    player1.setIsDeath(true);
                                });
                                // youLose.playerSetXandSetY(135, height * 0.70);
                                youLose.setHeight(youLose.getHeight());
                                youLose.setWidth(youLose.getWidth());
                                if (!player2.isIsDeath()) {
                                    Timeline x = new Timeline(new KeyFrame(Duration.millis(300), (ActionEvent e) -> {
                                        lose.stop();
                                        lose.play();
                                        dBox.getChildren().add(youLose.getPlayerSprite());
                                        twoPlayerGamePage.getChildren().add(dBox);
                                    }));
                                    x.play();
                                    gameOverScale.setFromX(0);
                                    gameOverScale.setFromY(0);
                                    gameOverScale.setToX(1);
                                    gameOverScale.setToY(1);
                                    gameOverFade.setFromValue(0.0);
                                    gameOverFade.setToValue(1.0);
                                    gameOverFade.play();
                                    gameOverScale.play();
                                }
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

    private void spawnBall2(PlayerEzMode player2) {

        if (waitFinish2 == 0) {

            BallEzMode ballObj2 = new BallEzMode(player2.getColorListRandom());
            waitFinish2 = 1;
            randomY2 = (int) ((Math.random()) * 2);

            if (randomY2 == 0) {
                ballObj2.ballSetXandSetY((width - ballObj2.getWidth()) / 1.325, -100);
            } else if (randomY2 == 1) {
                ballObj2.ballSetXandSetY((width - ballObj2.getWidth()) / 1.325, height + 100);
            }
            System.out.println(ballObj2.getBallStatus());

            twoPlayerGamePage.getChildren().add(ballObj2.getPlayerSprite());
            new AnimationTimer() {
                private BallEzMode ballObj2;
                int randomSpeed = 4 + (int) ((Math.random()) * 2);

                @Override
                public void handle(long now) {
                    Sprite youLose = new Sprite("maballv1/pic/Menu/YouLose.png");

                    ScaleTransition ballScale2 = new ScaleTransition(Duration.millis(400), ballObj2.getPlayerSprite());
                    FadeTransition ballFade2 = new FadeTransition(Duration.millis(400), ballObj2.getPlayerSprite());

                    ScaleTransition gameOverScale2 = new ScaleTransition(Duration.millis(700), youLose.getPlayerSprite());
                    FadeTransition gameOverFade2 = new FadeTransition(Duration.millis(700), youLose.getPlayerSprite());

                    StackPane dBox2 = new StackPane();
                    dBox2.setPrefSize(width * 1.5, height * 1.45);

                    if (randomY2 == 0) {
                        ballObj2.setPositionY(ballObj2.getYPosition() + randomSpeed);
                        if (ballObj2.getPlayerSprite().getBoundsInParent().intersects(player2.getPlayerSprite().getBoundsInParent())) {
                            if (player2.getPlayerStatus(randomY2).compareTo(ballObj2.getBallStatus()) == 0) {
                                waitFinish2 = 0;
                                ballFade2.setFromValue(0.5);
                                ballFade2.setToValue(0.0);
                                ballScale2.setByX(10f);
                                ballScale2.setByY(10f);
                                ballScale2.play();
                                ballFade2.play();
                                pop2.stop();
                                pop2.play();
                                ballObj2 = null;
                                ballFade2 = null;
                                ballScale2 = null;
                                player2.addScore();
                                this.stop();
                            } else if (player2.getPlayerStatus(randomY2).compareTo(ballObj2.getBallStatus()) != 0) {
                                ballFade2.setFromValue(0.5);
                                ballFade2.setToValue(0.0);
                                ballScale2.setByX(10f);
                                ballScale2.setByY(10f);
                                ballScale2.play();
                                ballFade2.play();
                                spark.stop();
                                spark.play();
                                ballFade2.setOnFinished((ActionEvent e) -> {
                                    player2.setIsDeath(true);
                                });
                                //youLose.playerSetXandSetY(760, height * 0.70);
                                youLose.setHeight(youLose.getHeight());
                                youLose.setWidth(youLose.getWidth());
                                if (!player1.isIsDeath()) {
                                    Timeline x = new Timeline(new KeyFrame(Duration.millis(300), (ActionEvent e) -> {
                                        lose.stop();
                                        lose.play();
                                        dBox2.getChildren().add(youLose.getPlayerSprite());
                                        twoPlayerGamePage.getChildren().add(dBox2);
                                    }));
                                    x.play();
                                    gameOverScale2.setFromX(0);
                                    gameOverScale2.setFromY(0);
                                    gameOverScale2.setToX(1);
                                    gameOverScale2.setToY(1);
                                    gameOverFade2.setFromValue(0.0);
                                    gameOverFade2.setToValue(1.0);
                                    gameOverFade2.play();
                                    gameOverScale2.play();
                                }
                                this.stop();
                            }
                        }
                    } else if (randomY2 == 1) {
                        ballObj2.setPositionY(ballObj2.getYPosition() - randomSpeed);
                        if (ballObj2.getPlayerSprite().getBoundsInParent().intersects(player2.getPlayerSprite().getBoundsInParent())) {
                            if (player2.getPlayerStatus(randomY2).compareTo(ballObj2.getBallStatus()) == 0) {
                                waitFinish2 = 0;
                                ballFade2.setFromValue(0.5);
                                ballFade2.setToValue(0.0);
                                ballScale2.setByX(10f);
                                ballScale2.setByY(10f);
                                ballScale2.play();
                                ballFade2.play();
                                pop2.stop();
                                pop2.play();
                                ballObj2 = null;
                                ballFade2 = null;
                                ballScale2 = null;
                                player2.addScore();
                                this.stop();
                            } else if (player2.getPlayerStatus(randomY2).compareTo(ballObj2.getBallStatus()) != 0) {
                                ballFade2.setFromValue(0.5);
                                ballFade2.setToValue(0.0);
                                ballScale2.setByX(10f);
                                ballScale2.setByY(10f);
                                ballScale2.play();
                                ballFade2.play();
                                spark.stop();
                                spark.play();
                                ballFade2.setOnFinished((ActionEvent e) -> {
                                    player2.setIsDeath(true);
                                });
                                //youLose.playerSetXandSetY(760, height * 0.70);
                                youLose.setHeight(youLose.getHeight());
                                youLose.setWidth(youLose.getWidth());
                                if (!player1.isIsDeath()) {
                                    Timeline x = new Timeline(new KeyFrame(Duration.millis(300), (ActionEvent e) -> {
                                        lose.stop();
                                        lose.play();
                                        dBox2.getChildren().add(youLose.getPlayerSprite());
                                        twoPlayerGamePage.getChildren().add(dBox2);
                                    }));
                                    x.play();
                                    gameOverScale2.setFromX(0);
                                    gameOverScale2.setFromY(0);
                                    gameOverScale2.setToX(1);
                                    gameOverScale2.setToY(1);
                                    gameOverFade2.setFromValue(0.0);
                                    gameOverFade2.setToValue(1.0);
                                    gameOverFade2.play();
                                    gameOverScale2.play();

                                }
                                this.stop();
                            }
                        }
                    }
                }

                private AnimationTimer init(BallEzMode ball2) {
                    ballObj2 = ball2;
                    return this;
                }
            }.init(ballObj2).start();
        }
    }

    private Parent GameOverScene(PlayerEzMode player1, PlayerEzMode player2) throws IOException {
        Pane GameOverPage = new Pane();
        GameOverPage.setPrefSize(width, height);

        StackPane Box = new StackPane();
        StackPane Box2 = new StackPane();
        StackPane wBox = new StackPane();
        StackPane wBox2 = new StackPane();
        StackPane displayScoreBox = new StackPane();
        StackPane displayScoreBox2 = new StackPane();
        StackPane dBox = new StackPane();
        StackPane dBox2 = new StackPane();

        Box.setPrefSize(width / 2, height);
        displayScoreBox.setPrefSize(width / 2, height * 0.45);

        wBox.setPrefSize(width / 2, height * 1.4);
        wBox2.setPrefSize(width * 1.5, height * 1.4);

        Box2.setPrefSize(width * 1.5, height);
        displayScoreBox2.setPrefSize(width * 1.5, height * 0.45);

        dBox.setPrefSize(width / 2, height * 1.45);
        dBox2.setPrefSize(width * 1.5, height * 1.45);

        ImageView backGround = new ImageView(new Image("maballv1/pic/Menu/FullGamepage2.png"));

        ImageView stick = new ImageView(new Image("maballv1/pic/Menu/LongStick.png"));
        stick.setFitHeight(600);
        stick.setLayoutX(width / 2);
        stick.setLayoutY(-50);
        ScaleTransition stickScale = new ScaleTransition(Duration.millis(700), stick);
        stickScale.setByY(1.0);
        stickScale.setToY(0.8);
        stickScale.play();

        Sprite playAgainButton = new Sprite("maballv1/pic/Menu/PlayAgainButton1.png");
        Sprite highScoreForGameover = new Sprite("maballv1/pic/Menu/HighScoreForGameover1.png");
        Sprite smallHomeButton = new Sprite("maballv1/pic/Menu/SmallHomeButton1.png");
        Sprite winner = new Sprite("maballv1/pic/Menu/Winner.png");
        playAgainButton.getPlayerSprite().setVisible(false);
        highScoreForGameover.getPlayerSprite().setVisible(false);
        smallHomeButton.getPlayerSprite().setVisible(false);

        RotateTransition winnerRotate = new RotateTransition(Duration.millis(1000), winner.getPlayerSprite());
        ScaleTransition winnerScale = new ScaleTransition(Duration.millis(1000), winner.getPlayerSprite());
        FadeTransition winnerFade = new FadeTransition(Duration.millis(1000), winner.getPlayerSprite());

        winnerScale.setFromX(0);
        winnerScale.setFromY(0);
        winnerScale.setToX(1);
        winnerScale.setToY(1);
        winnerFade.setFromValue(0.0);
        winnerFade.setToValue(1.0);
        winnerRotate.setByAngle(360);
        winnerRotate.setCycleCount(1);

        if (player1.getPlayerScore() > player2.getPlayerScore()) {
            lose.stop();
            win.stop();
            win.play();
            Timeline x = new Timeline(new KeyFrame(Duration.millis(500), (ActionEvent e) -> {
                dBox.getChildren().add(winner.getPlayerSprite());
            }));
            x.play();
            winnerRotate.play();
            winnerFade.play();
            winnerScale.play();
        } else if (player2.getPlayerScore() > player1.getPlayerScore()) {
            lose.stop();
            win.stop();
            win.play();
            Timeline x = new Timeline(new KeyFrame(Duration.millis(500), (ActionEvent e) -> {
                dBox2.getChildren().add(winner.getPlayerSprite());
            }));
            x.play();
            winnerRotate.play();
            winnerFade.play();
            winnerScale.play();
        } else if (player2.getPlayerScore() == player1.getPlayerScore()) {
            lose.stop();
            lose.play();
            Sprite draw2 = new Sprite("maballv1/pic/Menu/Draw.png");
            Sprite draw = new Sprite("maballv1/pic/Menu/Draw.png");

            RotateTransition draw2Rotate = new RotateTransition(Duration.millis(1000), draw2.getPlayerSprite());
            ScaleTransition draw2Scale = new ScaleTransition(Duration.millis(1000), draw2.getPlayerSprite());
            FadeTransition draw2Fade = new FadeTransition(Duration.millis(1000), draw2.getPlayerSprite());

            RotateTransition draw1Rotate = new RotateTransition(Duration.millis(1000), draw.getPlayerSprite());
            ScaleTransition draw1Scale = new ScaleTransition(Duration.millis(1000), draw.getPlayerSprite());
            FadeTransition draw1Fade = new FadeTransition(Duration.millis(1000), draw.getPlayerSprite());

            draw2Scale.setFromX(0);
            draw2Scale.setFromY(0);
            draw2Scale.setToX(1);
            draw2Scale.setToY(1);
            draw2Fade.setFromValue(0.0);
            draw2Fade.setToValue(1.0);
            draw2Rotate.setByAngle(360);
            draw2Rotate.setCycleCount(1);

            draw1Scale.setFromX(0);
            draw1Scale.setFromY(0);
            draw1Scale.setToX(1);
            draw1Scale.setToY(1);
            draw1Fade.setFromValue(0.0);
            draw1Fade.setToValue(1.0);
            draw1Rotate.setByAngle(360);
            draw1Rotate.setCycleCount(1);

            Timeline x = new Timeline(new KeyFrame(Duration.millis(500), (ActionEvent e) -> {
                dBox.getChildren().add(draw.getPlayerSprite());
                dBox2.getChildren().add(draw2.getPlayerSprite());
            }));
            x.play();

            draw2Rotate.play();
            draw2Fade.play();
            draw2Scale.play();

            draw1Rotate.play();
            draw1Fade.play();
            draw1Scale.play();

        }

        playAgainButton.playerSetXandSetY((width - playAgainButton.getWidth()) / 4 - 45, height * 0.85);
        highScoreForGameover.playerSetXandSetY((width - playAgainButton.getWidth()) / 1.75 - 45, height * 0.85);
        smallHomeButton.playerSetXandSetY((width - playAgainButton.getWidth()) / 1.1 - 45, height * 0.85);

        smallHomeButton.setHeight(smallHomeButton.getHeight() * 1.25);
        smallHomeButton.setWidth(smallHomeButton.getWidth() * 1.25);

        smallHomeButton.getPlayerSprite().setOnMouseEntered(event -> {
            smallHomeButton.setPlayerSprite("maballv1/pic/Menu/SmallHomeButton2.png");
            bubble.stop();
            bubble.play();
        });
        smallHomeButton.getPlayerSprite().setOnMouseExited(event -> {
            smallHomeButton.setPlayerSprite("maballv1/pic/Menu/SmallHomeButton1.png");
            bubble.stop();
        });
        smallHomeButton.getPlayerSprite().setOnMouseClicked(event -> {
            win.stop();
            BGM.stop();
            lose.stop();
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
            win.stop();
            BGM.stop();
            lose.stop();
            try {
                EzMode2Player reStage = new EzMode2Player(player1.getPlayerName(), player2.getPlayerName());
                reStage.start(stage);
            } catch (IllegalArgumentException e) {
                EzMode2Player reStage = new EzMode2Player(player1.getPlayerName(), player2.getPlayerName());
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
            lose.stop();
            try {
                mainScene = new Scene(highScoreScene());
            } catch (IOException ex) {
                Logger.getLogger(EzMode1Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(mainScene);
            stage.show();
        });

        String[] nameList1 = new String[5];
        String[] scoreList1 = new String[5];

        readName1(nameList1, scoreList1);
        writeScore(nameList1, scoreList1, player1.getPlayerScore(), player1.getPlayerName());

        readName1(nameList1, scoreList1);
        writeScore(nameList1, scoreList1, player2.getPlayerScore(), player2.getPlayerName());

        backGround.setFitWidth(width);
        backGround.setFitHeight(height);

        Text score = new Text(Integer.toString(player1.getPlayerScore()));
        score.setFont(Font.font("Futura-Normal", 45));
        score.setFill(Color.web("#3e3024"));
        Box.getChildren().add(score);

        Text score2 = new Text(Integer.toString(player2.getPlayerScore()));
        score2.setFont(Font.font("Futura-Normal", 45));
        score2.setFill(Color.web("#3e3024"));
        Box2.getChildren().add(score2);

        Text scoreDisplay = new Text("Score : " + Integer.toString(player1.getPlayerScore()));
        scoreDisplay.setFont(Font.font("Bariol Regular", FontWeight.LIGHT, 60));
        scoreDisplay.setFill(Color.web("#3e3024"));

        Text scoreDisplay2 = new Text("Score : " + Integer.toString(player2.getPlayerScore()));
        scoreDisplay2.setFont(Font.font("Bariol Regular", FontWeight.LIGHT, 60));
        scoreDisplay2.setFill(Color.web("#3e3024"));

        Text player1Name = new Text(player1.getPlayerName());
        player1Name.setFont(Font.font("Bariol Regular", 45));
        player1Name.setFill(Color.web("#3e3024"));
        player1Name.setLayoutX(195);
        player1Name.setLayoutY(50);

        Text player2Name = new Text(player2.getPlayerName());
        player2Name.setFont(Font.font("Bariol Regular", 45));
        player2Name.setFill(Color.web("#3e3024"));
        player2Name.setLayoutX(800);
        player2Name.setLayoutY(50);

        scoreDisplay.setVisible(false);
        scoreDisplay2.setVisible(false);

        Timeline x = new Timeline(new KeyFrame(Duration.millis(10), (ActionEvent e) -> {
            playAgainButton.getPlayerSprite().setVisible(true);
            highScoreForGameover.getPlayerSprite().setVisible(true);
            smallHomeButton.getPlayerSprite().setVisible(true);
            scoreDisplay.setVisible(true);
            scoreDisplay2.setVisible(true);
            backGround.setVisible(true);
        }));
        x.play();

        ScaleTransition score1DisplayTran = new ScaleTransition(Duration.millis(1000), scoreDisplay);
        ScaleTransition score2DisplayTran = new ScaleTransition(Duration.millis(1000), scoreDisplay2);

        FadeTransition score1DisplayFade = new FadeTransition(Duration.millis(1000), scoreDisplay);
        FadeTransition score2DisplayFade = new FadeTransition(Duration.millis(1000), scoreDisplay2);

        score1DisplayTran.setFromX(1.5);
        score1DisplayTran.setToX(1);
        score1DisplayTran.play();

        score2DisplayTran.setFromX(1.5);
        score2DisplayTran.setToX(1);
        score2DisplayTran.play();

        score1DisplayFade.setFromValue(0.0);
        score1DisplayFade.setToValue(1);
        score1DisplayFade.play();

        score2DisplayFade.setFromValue(0.0);
        score2DisplayFade.setToValue(1);
        score2DisplayFade.play();

        FadeTransition playAgainButtonFade = new FadeTransition(Duration.millis(1000), playAgainButton.getPlayerSprite());
        FadeTransition highScoreForGameoverFade = new FadeTransition(Duration.millis(1000), highScoreForGameover.getPlayerSprite());
        FadeTransition smallHomeButtonFade = new FadeTransition(Duration.millis(1000), smallHomeButton.getPlayerSprite());

        FadeTransition player1nameFade = new FadeTransition(Duration.millis(1000), player1Name);
        FadeTransition player2nameFade = new FadeTransition(Duration.millis(1000), player2Name);

        player1nameFade.setFromValue(0.0);
        player1nameFade.setToValue(1);
        // player1nameFade.play();

        player2nameFade.setFromValue(0.0);
        player2nameFade.setToValue(1);
        //player2nameFade.play();

        playAgainButtonFade.setFromValue(0);
        playAgainButtonFade.setToValue(1);
        playAgainButtonFade.play();

        highScoreForGameoverFade.setFromValue(0);
        highScoreForGameoverFade.setToValue(1);
        highScoreForGameoverFade.play();

        smallHomeButtonFade.setFromValue(0);
        smallHomeButtonFade.setToValue(1);
        smallHomeButtonFade.play();

        displayScoreBox.getChildren().add(scoreDisplay);
        Box.getChildren().add(player1.getPlayerSprite());

        displayScoreBox2.getChildren().add(scoreDisplay2);
        Box2.getChildren().add(player2.getPlayerSprite());

        GameOverPage.getChildren().add(backGround);

        GameOverPage.getChildren().add(stick);
        GameOverPage.getChildren().add(player1Name);
        GameOverPage.getChildren().add(player2Name);

        GameOverPage.getChildren().add(dBox);
        GameOverPage.getChildren().add(dBox2);

        GameOverPage.getChildren().add(Box);
        GameOverPage.getChildren().add(wBox);
        GameOverPage.getChildren().add(displayScoreBox);

        GameOverPage.getChildren().add(Box2);
        GameOverPage.getChildren().add(wBox2);
        GameOverPage.getChildren().add(displayScoreBox2);

        GameOverPage.getChildren().add(playAgainButton.getPlayerSprite());
        GameOverPage.getChildren().add(highScoreForGameover.getPlayerSprite());
        GameOverPage.getChildren().add(smallHomeButton.getPlayerSprite());
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

    Parent highScoreScene() throws IOException {
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
            MaBallV1 reStage = new MaBallV1();
            reStage.start(stage);
        });

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
