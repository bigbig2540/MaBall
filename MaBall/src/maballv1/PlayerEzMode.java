
package maballv1;

import java.util.Arrays;

public class PlayerEzMode extends Sprite implements RandomColor{
    private int PlayerScore;
    private String PlayerName;
    private String[] PlayerStatus;
    private final int colorIndex;
    private boolean pressStatus;
    private boolean isDeath;
    
    public PlayerEzMode() { 
        super();
        this.colorIndex = (int)(Math.random()*COLORLIST.length);
        System.out.println(COLORLIST[colorIndex][0]+COLORLIST[colorIndex][1]);
        this.setPlayerSprite("maballv1/circleFinish/"+COLORLIST[colorIndex][0]+COLORLIST[colorIndex][1]+".png");
        this.PlayerStatus = new String[getColorList().length];
        for(int i=0;i<PlayerStatus.length;i++)
        {  
            PlayerStatus[i]= COLORLIST[colorIndex][i];
        }
        this.PlayerScore = 0 ;
        this.pressStatus=true;
        this.setHeight(this.getHeight()*0.04);
        this.setWidth(this.getWidth()*0.04);   
        this.isDeath=false;
        
    }
    
    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }
    
    public boolean isPressStatus() {
        return pressStatus;
    }

    public void setPressStatus(boolean pressStatus) {
        this.pressStatus = pressStatus;
    }

    public int getPlayerScore() {
        return PlayerScore;
    }

    public String getPlayerStatus(int index) {
        return PlayerStatus[index];
    }
    
    public boolean isIsDeath() {
        return isDeath;
    }

    public void setIsDeath(boolean isDeath) {
        this.isDeath = isDeath;
    }
    
    public void swapPlayerStat() {
        String tmp;
        for(int i=0;i<PlayerStatus.length-1;i++)
        {  
            tmp = PlayerStatus[i+1];
            PlayerStatus[i+1]= PlayerStatus[i];
            PlayerStatus[i]=tmp;
        }
    }
    
    public void addScore()
    {
        this.PlayerScore++;
    }
 
    @Override   
    public String[] getColorList() {
        return COLORLIST[colorIndex];
    }
    
    public String getColorListRandom() {
        return COLORLIST[colorIndex][(int)(Math.random()*getColorList().length)];
    }
    

    
    
}
