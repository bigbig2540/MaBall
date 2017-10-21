
package maballv1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    private ImageView playerSprite;

    public Sprite() {
        playerSprite = new ImageView();
    }
    
    public Sprite(String Directory) {
        playerSprite = new ImageView(new Image(Directory));
        this.setHeight(this.getHeight()*0.75);
        this.setWidth(this.getWidth()*0.75);      
    }

    public ImageView getPlayerSprite() {
        return playerSprite;
    }
    
    public void setPlayerSprite(String Directory) {
        playerSprite.setImage(new Image(Directory));
    }

    public void setPositionX(double xPosition) {
        playerSprite.setLayoutX(xPosition);
    }

    public void setPositionY(double yPosition) {
        playerSprite.setLayoutY(yPosition);
    }
    
    public double getPositionX() {
        return playerSprite.getLayoutX();
    }

    public double getYPosition() {
        return playerSprite.getLayoutY();
    }
    
    public void setHeight(double Height) {
        playerSprite.setFitHeight(Height);
    }

    public void setWidth(double Width) {
        playerSprite.setFitWidth(Width);
    }
    
    public double getHeight() {
        return playerSprite.getBoundsInParent().getHeight();
    }

    public double getWidth() {
        return playerSprite.getBoundsInParent().getWidth();
    }
    public void playerSetXandSetY(double x,double y)
    {
        this.setPositionX(x);
        this.setPositionY(y);
    }
    
    
    
    
}
