package maballv1;

public class BallEzMode extends Sprite {

    private boolean isCollision;
    private String ballStatus;
    
    public BallEzMode(String color) {
        super();
        isCollision = false;
        this.setPlayerSprite("maballv1/circleFinish/" + color + ".png");
        this.ballStatus = color;
        this.setHeight(this.getHeight() * 0.04);
        this.setWidth(this.getWidth() * 0.04);
    }

    public void setBallStatus(String ballStatus) {
        this.ballStatus = ballStatus;
    }

    public String getBallStatus() {
        return ballStatus;
    }

    public boolean isIsCollision() {
        return isCollision;
    }

    public void setIsCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }

    public void ballSetXandSetY(double x, double y) {
        this.setPositionX(x);
        this.setPositionY(y);
    }

}
