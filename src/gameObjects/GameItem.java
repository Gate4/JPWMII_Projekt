package gameObjects;

import gameSingletons.GameGraphics;

/**
 *
 * @author Maciej Żak
 */
public class GameItem extends GameObject{
    public static final byte TYPE_WEAPON=0;
    public static final byte TYPE_ARMOR=2;
    private static String[] prefixes={"Potężny","Mroczny","Żelazny","Stary","Starożytny","Boski","Złowieszczy","Przeklęty","Stalowy","Magiczny","Legendarny","Zardzewiały","Zabrudzony","Egzotyczny","Podniszczony"};
    private String name;
    private int attack;
    private int defense;
    private int magicAttack;
    private int magicDefense;
    private int level;
    private byte type;

    public GameItem(int x, int y,byte type,int level) {
        super(x, y,(type==TYPE_WEAPON)?GameGraphics.getInstance().getRandomWeapon():GameGraphics.getInstance().getRandomArmor(), 0, GameGraphics.DEFAULT_TILE_SIZE*x, GameGraphics.DEFAULT_TILE_SIZE*y, 0, true, false);
        this.type=type;
        this.name=prefixes[(int)Math.floor(Math.random()*prefixes.length)]+" ";
        if(type==TYPE_WEAPON){
            name+="Miecz";
            this.attack=(int)Math.round((Math.random()*25+1)*(level+1));
            if(Math.random()>0.5){
                this.attack+=(int)Math.round((Math.random()*20+1)*(level+1));
            }else{
                this.magicAttack=(int)Math.round((Math.random()*20+1)*(level+1));
            }
        }else if(type==TYPE_ARMOR){
            name+="Pancerz";
            this.defense=(int)Math.round((Math.random()*25+1)*(level+1));
            if(Math.random()>0.5){
                this.defense+=(int)Math.round((Math.random()*20+1)*(level+1));
            }else{
                this.magicDefense=(int)Math.round((Math.random()*20+1)*(level+1));
            }
        }
    }

    @Override
    public String toString() {
        return "A:"+attack+" D:"+defense+" MA:"+magicAttack+" MD:"+magicDefense+" "+name;
    }
    public String getName() {
        return name;
    }

    public byte getType() {
        return type;
    }
    
    

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public int getLevel() {
        return level;
    }
    
    
}
