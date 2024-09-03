package hotstone.standard;
import hotstone.framework.*;

public class StandardCard implements Card {
    private String name;
    private int manaCost;
    private int attack;
    private int health;

    public StandardCard(String name, int manaCost, int attack, int health){
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }

    public String getName() {
        return name;
    }
  
    public int getManaCost(){
        return manaCost;
    }

    public int getAttack(){
        return attack;
    }

    public int getHealth(){
        return health;
    }

    public boolean isActive(){
        return true;
    }

    public Player getOwner(){
        return null;
    }

    public String getEffectDescription(){
        return null;
    }
}
