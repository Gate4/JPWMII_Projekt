package gameObjects;

import gameAttacks.FireballAttack;
import gameAttacks.FlashAttack;
import gameSingletons.GameLogic;
import gameSingletons.GameSounds;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maciej Żak
 */
public class GamePlayer extends GameCharacter{
    //private boolean canMove;
    private int experience;
    private int skillPoints;
    public static final int EXP_FOR_LEVEL[]={100,300,700,1500,3100,6300,12700};
    public static final int MAX_LEVEL=6;
    
    private int strength;
    private int intelligence;
    
    private List<GameItem> items;
    private GameItem weapon;
    private GameItem armor;
    
    private List<String> spells;
    private String spell;
    
    public void addExperience(int experience){
        if(this.experience>=0){
            this.experience+=experience;
            int level=this.getLevel();
            if((this.experience>=EXP_FOR_LEVEL[level])&&(level<MAX_LEVEL)){
                setLevel(level+1);
                this.skillPoints+=15;
                GameLogic.getInstance().writelnInConsole(getName()+" awansuje na "+(level+1)+" poziom!");
                GameSounds.getInstance().playSound("level.wav");
                if(level==0)this.spells.add(FireballAttack.NAME);
            }
        }
        notifyObservers();
    }

    /*public boolean CanMove() {
        return canMove;
    }*/
    
    public GamePlayer(String name, int x, int y) {
        super(name, 150, 150, 0, 0, 0,0, 0, x, y, 0, 0, x*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, y*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, 1);
        this.strength=20;
        this.intelligence=15;
        this.items=new ArrayList<>();
        this.spells=new ArrayList<>();
        this.spell=null;
        this.weapon=null;
        this.armor=null;
        this.experience=0;
        this.spells.add(FlashAttack.NAME);
        this.spell=FlashAttack.NAME;
        notifyObservers();
    }

    @Override
    public void rest() {
        int health=getHealth();
        int maxHealth=getMaxHealth();
        if(health<maxHealth){
            setHealth(health+15);
            if(getHealth()>maxHealth)setHealth(maxHealth);
            GameLogic.getInstance().writelnInConsole("Odpoczywasz i regenerujesz część punktów życia");
            notifyObservers();
            GameLogic.getInstance().nextTurn();
        }else{
            GameLogic.getInstance().writelnInConsole("Odpoczywasz, lecz nie regenerujesz punktów życia");
        }
    }

    @Override
    public void moveTo(int newX, int newY) {
        super.moveTo(newX, newY); //To change body of generated methods, choose Tools | Templates.
        GameLogic.getInstance().nextTurn();
    }

    @Override
    public void move(int xDirection, int yDirection) {
        super.move(xDirection, yDirection); //To change body of generated methods, choose Tools | Templates.
        GameLogic.getInstance().nextTurn();
    }
    
    

    
    public List<GameItem> getItems() {
        return items;
    }
    
    public void addItem(GameItem gI){
        this.items.add(gI);
        notifyObservers();
    }
    
    public GameItem removeItem(int index){
        if(index>-1&&index<items.size()&&isAlive()){
            GameItem gI=items.remove(index);
            notifyObservers();
            return gI;
        }
        return null;
    }
    
    public void equipItem(int index){
        if(index>-1&&index<items.size()&&isAlive()){
            GameItem gI=items.get(index);
            if(gI.getType()==GameItem.TYPE_WEAPON){
                if(weapon!=null)items.add(weapon);
                weapon=gI;
                GameLogic.getInstance().writelnInConsole(getName()+" uzbraja się w: "+this.weapon.getName());
            }else if(gI.getType()==GameItem.TYPE_ARMOR){
                if(armor!=null)items.add(armor);
                armor=gI;
                GameLogic.getInstance().writelnInConsole(getName()+" zakłada: "+this.armor.getName());
            }
            GameSounds.getInstance().playSound("itemSelect.wav");
            items.remove(gI);
            notifyObservers();
        }
    }
    
    public String getItemDescription(int index){
        String desc="";
        GameItem i=null;
        if(index>-1&&index<items.size()&&isAlive()){
            GameItem gI=items.get(index);
            if(gI.getType()==GameItem.TYPE_WEAPON){
                i=weapon;
            }else if(gI.getType()==GameItem.TYPE_ARMOR){
                i=armor;
            }
            if(i!=null)desc+="Nosisz: "+i.toString()+"\n";
            desc+="Zazn.:  "+gI.toString()+"\n";
        }
        return desc;
    }
    
    public String getSpellDescription(int index){
        String desc="";
        if(index>-1&&index<spells.size()&&isAlive()){
            if(spells.get(index).equals(FlashAttack.NAME)){
                return FlashAttack.DESCRIPTION;
            }
            if(spells.get(index).equals(FireballAttack.NAME)){
                return FireballAttack.DESCRIPTION;
            }
        }
        return desc;
    }

    public List<String> getSpells() {
        return spells;
    }

    public String getSpell() {
        if(spell==null)return "";
        return spell;
    }

    public void setSpell(int index) {
        if(index>-1&&index<spells.size()&&isAlive()){
            this.spell=spells.get(index);
            GameLogic.getInstance().writelnInConsole(getName()+" wybiera nowy czar: "+this.spell);
            GameSounds.getInstance().playSound("spellSelect.wav");
        }
    }
    
    
    
    public GameItem getWeapon() {
        return weapon;
    }

    public GameItem getArmor() {
        return armor;
    }

    public int getExperience() {
        return experience;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }
    
    public void incrStrength(int val){
        strength+=val;
        notifyObservers();
    }
    
    public void incrIntelligence(int val){
        intelligence+=val;
        notifyObservers();
    }
    
    public void removeSkillPoints(int val){
        if(skillPoints>0)skillPoints--;
    }

    @Override
    public int getMagicDefense() {
        int magicDefense=super.getMagicDefense();
        if(weapon!=null)magicDefense+=weapon.getMagicDefense();
        if(armor!=null)magicDefense+=armor.getMagicDefense();
        magicDefense+=(int)Math.round(this.intelligence*1.25);
        return magicDefense;
    }

    @Override
    public int getMagicAttack() {
        int magicAttack=super.getMagicAttack();
        if(weapon!=null)magicAttack+=weapon.getMagicAttack();
        if(armor!=null)magicAttack+=armor.getMagicAttack();
        magicAttack+=(int)Math.round(this.intelligence*1.8);
        return magicAttack;
    }

    @Override
    public int getDefense() {
        int defense=super.getDefense();
        if(weapon!=null)defense+=weapon.getDefense();
        if(armor!=null)defense+=armor.getDefense();
        defense+=(int)Math.round(this.strength*0.25);
        return defense;
    }

    @Override
    public int getAttack() {
        int attack=super.getAttack();
        if(weapon!=null)attack+=weapon.getAttack();
        if(armor!=null)attack+=armor.getAttack();
        attack+=(int)Math.round(this.strength*1.25);
        return attack;
    }

    @Override
    public int getMaxHealth() {
        int maxHealth=super.getMaxHealth();
        return maxHealth*(getLevel()+1);
    }

    @Override
    public void giveMagicDamage(int magicAttack, int spellPower) {
        super.giveMagicDamage(magicAttack, spellPower);
        if(!isAlive())GameSounds.getInstance().playSound("dead.wav");
    }

    @Override
    public void giveNormalDamage(int attack) {
        super.giveNormalDamage(attack);
        if(!isAlive())GameSounds.getInstance().playSound("dead.wav");
    }

    
    
    
    
}
