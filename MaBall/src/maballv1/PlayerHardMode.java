
package maballv1;

public class PlayerHardMode extends Sprite{
    private int PlayerScore;
    private String PlayerName;
    private String[] PlayerStatus;
    private final int colorIndex;
    private boolean pressStatus;
    private boolean isDeath;
   // private String[][] COLORLIST = {{"Darkgreen","Brown"},{"Green","Brown"},{"Green","Darkgreen"},{"Red","Darkgreen"},{"Red","Green"},{"Red","Yellow"},{"Yellow","Green"}};
    private String[][] COLORLIST = {{"Red","Green","Brown","Darkgreen"},{"Red","Yellow","Brown","Darkgreen"},{"Red","Yellow","Brown","Green"}
                                    ,{"Red","Yellow","Darkgreen","Green"},{"Yellow","Green","Brown","Darkgreen"}};
    public PlayerHardMode() { 
        super();
        this.colorIndex = (int)(Math.random()*COLORLIST.length);
        this.setPlayerSprite("maballv1/circleFinish/"+COLORLIST[colorIndex][0]+COLORLIST[colorIndex][1]+COLORLIST[colorIndex][2]+COLORLIST[colorIndex][3]+".png");
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
    //{"Red","Yellow","Darkgreen","Green"}
    public void swapPlayerStatLeft() {
        String tmp;
        tmp = PlayerStatus[0];
        for(int i=0;i<PlayerStatus.length-1;i++)
        {  
           PlayerStatus[i]=PlayerStatus[i+1];
        }
        PlayerStatus[PlayerStatus.length-1]=tmp;
    }
    
    public void swapPlayerStatRight() {
        String tmp;
        tmp = PlayerStatus[PlayerStatus.length-1];
        for(int i=PlayerStatus.length-1;i>0;i--)
        {  
           PlayerStatus[i]=PlayerStatus[i-1];
        }
        PlayerStatus[0]=tmp;
    }
    
    public void addScore()
    {
        this.PlayerScore++;
    }
      
    public String[] getColorList() {
        return COLORLIST[colorIndex];
    }
    
    public String getColorListRandom() {
        return COLORLIST[colorIndex][(int)(Math.random()*getColorList().length)];
    }
    

    
    
}
