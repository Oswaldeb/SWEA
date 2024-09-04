package hotstone.standard;
import hotstone.framework.*;

public class StandHero implements Hero {
    private String type;
    private int mana;
    private int health;
    private Player player;
    private int currentMana;

    public StandHero(String type, int mana, int health, Player player){
        this.type = type;
        this.mana = mana;
        this.health = health;
        this.player = player;
        this.currentMana = mana;
    }


    public int getMana(){
        return currentMana;
        
    }
    public void ReduceMana(int manacost){
        if (currentMana - manacost >= 0){
            currentMana -= manacost;
        } else {
            throw new IllegalArgumentException("Not enough mana");
        }
    }

    public int getHealth(){
        return health;
    }

    public boolean canUsePower(){
        return false;
    }

    public String getType(){
        return type;
    }

    public Player getOwner(){
        return player;
    }

    public String getEffectDescription(){
        return "Cute";
    }
    
}
