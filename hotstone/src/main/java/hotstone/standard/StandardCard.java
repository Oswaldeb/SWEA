package hotstone.standard;
import hotstone.framework.*;

public class StandardCard implements Card {
    public String getName() {
        return null;
    }
  
    public int getManaCost(){
        return 0;
    }

    public int getAttack(){
        return 0;
    }

    public int getHealth(){
        return 0;
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
