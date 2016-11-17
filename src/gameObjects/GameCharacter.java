package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameCharacter extends GameObject{
    private String name;
    
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int magicAttack;
    private int magicDefense;
    private int level;

    public GameCharacter(String name, int health, int maxHealth, int attack, int defense, int magicAttack, int magicDefense, int level, int x, int y, int imageIndex, int frameIndex, int graphicX, int graphicY, double graphicRotation) {
        super(x, y, imageIndex, frameIndex, graphicX, graphicY, graphicRotation);
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.magicAttack = magicAttack;
        this.magicDefense = magicDefense;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(int magicAttack) {
        this.magicAttack = magicAttack;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
    
}
