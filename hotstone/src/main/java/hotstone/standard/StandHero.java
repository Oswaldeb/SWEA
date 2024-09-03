package hotstone.standard;
import hotstone.framework.*;

public class StandHero implements Hero {
    private String type;
    private int mana;
    private int health;
    private Player player;

    public StandHero(String type, int mana, int health, Player player){
        this.type = type;
        this.mana = mana;
        this.health = health;
        this.player = player;
    }


    int getMana(){
        return 0;
    }

    int getHealth(){
        return 0;
    }

    boolean canUsePower(){
        return false;
    }

    String getType(){
        return null;
    }

    Player getOwner(){
        return null;
    }

    String getEffectDescription(){
        return null;
    }
    
}
