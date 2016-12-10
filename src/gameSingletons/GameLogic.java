package gameSingletons;

import gameAttacks.FireballAttack;
import gameAttacks.FlashAttack;
import gameAttacks.HeavyFistAttack;
import gameAttacks.SwordAttack;
import gameGUI.GameConsole;
import gameObjects.GameAttack;
import gameObjects.GameCharacter;
import gameObjects.GameData;
import gameObjects.GameEnemy;
import gameObjects.GameItem;
import gameObjects.GameObject;
import gameObjects.GamePlayer;
import gameObjects.GameRoom;
import gameObjects.GameTile;
import gameObjects.GameTilePassage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.Timer;

/**
 *
 * @author Maciej Żak
 */
public class GameLogic implements ActionListener {

    private static GameLogic instance;
    private gameGUI.GameConsole gameConsole;
    private GameData gameData;

    //Dirtection array for moving objects
    public static final int[] xDirection = {0, 1, 0, -1, 1, 1, -1, -1};
    public static final int[] yDirection = {-1, 0, 1, 0, -1, 1, 1, -1};
    //Indexes for direction array:
    public static final byte NORTH = 0;
    public static final byte EAST = 1;
    public static final byte SOUTH = 2;
    public static final byte WEST = 3;

    public static final byte NORTHEAST = 4;
    public static final byte SOUTHEAST = 5;
    public static final byte SOUTHWEST = 6;
    public static final byte NORTHWEST = 7;

    private byte turn = 0;
    public static final byte TURN_PLAYER = 0;
    public static final byte TURN_ENEMY = 1;

    private java.util.Iterator enemiesIterator;
    private java.util.Iterator attackIterator;

    private boolean busy;

    private Timer timer;

    private GameLogic() {
        this.busy = false;
        this.timer = new Timer(120, this);
        this.timer.setRepeats(false);
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public void setGameConsole(GameConsole gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean writeInConsole(String message) {
        if (this.gameConsole != null) {
            gameConsole.write(message);
            return true;
        } else {
            return false;
        }
    }

    public boolean writelnInConsole(String message) {
        if (this.gameConsole != null) {
            gameConsole.writeln(message);
            return true;
        } else {
            return false;
        }
    }

    public GameTilePassage getPassageAtXY(int x, int y) {
        GameTilePassage gTP = null;
        if (gameData != null) {
            for (GameTilePassage g : gameData.getCurrentRoom().getPassages()) {
                if (g.getX() == x && g.getY() == y) {
                    gTP = g;
                }
            }
        }
        return gTP;
    }

    public GameItem getItemAtXY(int x, int y) {
        GameItem gI = null;
        if (gameData != null) {
            for (GameItem g : gameData.getCurrentRoom().getItems()) {
                if (g.getX() == x && g.getY() == y) {
                    gI = g;
                }
            }
        }
        return gI;
    }

    public void pickItemAtXY(int x, int y) {
        if (canMove(gameData.getPlayer())) {
            GameItem gI = getItemAtXY(x, y);
            if (gI != null) {
                writelnInConsole("Podnosisz " + gI.getName());
                gameData.getPlayer().addItem(gI);
                gameData.getCurrentRoom().getItems().remove(gI);
                gameSingletons.GameSounds.getInstance().playSound("pick.wav");
            } else {
                writelnInConsole("Nic tu nie ma");
            }
        }
    }

    public void dropItem(int index) {
        if (canMove(gameData.getPlayer())) {
            GameItem gI = gameData.getPlayer().removeItem(index);
            if (gI != null) {
                gI.setX(gameData.getPlayer().getX());
                gI.setY(gameData.getPlayer().getY());
                gI.updateGraphicPosition();
                gameData.getCurrentRoom().getItems().add(gI);
                gameSingletons.GameSounds.getInstance().playSound("drop.wav");
            }
        }

    }

    public boolean isCollisionAtXY(int x, int y) {
        boolean collision = false;
        if (gameData != null) {
            for (GameTile gT : gameData.getCurrentRoom().getRoomLayout()) {
                if (gT.isCollidingAtXY(x, y)) {
                    collision = true;
                }
            }
            if (!collision) {
                for (GameCharacter gC : gameData.getCurrentRoom().getEnemies()) {
                    if (gC.isCollidingAtXY(x, y)) {
                        collision = true;
                    }
                }
            }
        }
        return collision;
    }
    
    public boolean isOutOfBunds(int x,int y){
        return x<0||x>GameRoom.DEFAULT_WIDTH-1||y<0||y>GameRoom.DEFAULT_HEIGHT-1;
    }

    public void moveObject(GameObject o, int direction) {
        moveObject(o, xDirection[direction], yDirection[direction]);
    }

    public void moveObject(GameObject o, int xDirection, int yDirection) {
        if (canMove(o)) {
            int nX = o.getX() + xDirection;
            int nY = o.getY() + yDirection;
            GameTilePassage gTP = getPassageAtXY(nX, nY);
            if (gTP != null && o instanceof GamePlayer) {
                gameData.setCurrentRoom(gTP.getDestination(), gTP.getDestiantionX(), gTP.getDestiantionY());
                gameSingletons.GameSounds.getInstance().playSound("door.wav");
                o.updateGraphicPosition();
            } else {
                if (!isCollisionAtXY(nX, nY)) {
                    o.move(xDirection, yDirection);
                }
            }
        }
    }

    public boolean canMove(GameObject o) {
        return  (gameData != null) && ((o instanceof GamePlayer)&&(!busy) && (gameData.getPlayer().isAlive()) && (turn == TURN_PLAYER) || (o instanceof GameEnemy) && (turn == TURN_ENEMY));
    }

    public void rest() {
        if (canMove(gameData.getPlayer())) {
            if (gameData.getCurrentRoom().getEnemies().isEmpty()) {
                if (gameData.getPlayer().isAlive()) {
                    gameData.getPlayer().rest();
                }
                for (GameRoom gR : gameData.getWorld()) {
                    for (GameCharacter gC : gR.getEnemies()) {
                        if (gC.isAlive()) {
                            gC.rest();
                        }
                    }
                }
            } else {
                writelnInConsole("Nie możesz odpoczywać w pobliżu przeciwników");
            }
        }
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
        this.attackIterator = gameData.getAttacks().iterator();
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void setEnemiesIterator(Iterator enemiesIterator) {
        this.enemiesIterator = enemiesIterator;
    }

    public void dealDamageAtXY(int attack, int x, int y) {
        if (gameData.getPlayer().getX() == x && gameData.getPlayer().getY() == y) {
            gameData.getPlayer().giveNormalDamage(attack);
        }
        for (GameEnemy gE : gameData.getCurrentRoom().getEnemies()) {
            if (gE.getX() == x && gE.getY() == y) {
                gE.giveNormalDamage(attack);
            }
        }
    }

    public void dealMagicDamageAtXY(int magicAttack, int spellPower, int x, int y) {
        if (gameData.getPlayer().getX() == x && gameData.getPlayer().getY() == y) {
            gameData.getPlayer().giveMagicDamage(magicAttack, spellPower);
        }
        for (GameEnemy gE : gameData.getCurrentRoom().getEnemies()) {
            if (gE.getX() == x && gE.getY() == y) {
                gE.giveMagicDamage(magicAttack, spellPower);
            }
        }
    }

    public boolean isInMeleeRange(GameObject ob) {
        return (Math.abs(gameData.getPlayer().getX() - ob.getX()) < 2) && (Math.abs(gameData.getPlayer().getY() - ob.getY()) < 2);
    }

    public void moveTowardPlayer(GameObject ob) {
        int xDirection = gameData.getPlayer().getX() - ob.getX();
        int yDirection = gameData.getPlayer().getY() - ob.getY();
        if (xDirection != 0) {
            xDirection /= Math.abs(xDirection);
        }
        if (yDirection != 0) {
            yDirection /= Math.abs(yDirection);
        }
        moveObject(ob, xDirection, yDirection);
    }

    public void swordAttackAtXY(int attack, int x, int y) {
        gameData.getAttacks().add(new SwordAttack(x, y, attack));
        attackIterator = gameData.getAttacks().iterator();
    }
    
    public void fistAttackAtPlayerPos(int attack) {
        gameData.getAttacks().add(new HeavyFistAttack(gameData.getPlayer().getX(), gameData.getPlayer().getY(), attack));
        attackIterator = gameData.getAttacks().iterator();
    }

    public void swordAttackAtPlayerPos(int attack) {
        swordAttackAtXY(attack, gameData.getPlayer().getX(), gameData.getPlayer().getY());
    }

    public void swordAttackInDirection(int direction) {
        if (canMove(gameData.getPlayer())) {
            swordAttackAtXY(gameData.getPlayer().getAttack(), gameData.getPlayer().getX() + xDirection[direction], gameData.getPlayer().getY() + yDirection[direction]);
        }
    }

    public void flashAttack(int attack, int x, int y, int xDirection, int yDirection) {
        if (x < GameRoom.DEFAULT_WIDTH && x > -1 && y < GameRoom.DEFAULT_HEIGHT && y > -1) {
            gameData.getAttacks().add(new FlashAttack(x, y, attack, xDirection, yDirection));
            attackIterator = gameData.getAttacks().iterator();
        }
    }
    
    public void fireballAttack(int attack, int x, int y, int xDirection, int yDirection) {
        if (x < GameRoom.DEFAULT_WIDTH && x > -1 && y < GameRoom.DEFAULT_HEIGHT && y > -1) {
            gameData.getAttacks().add(new FireballAttack(x, y, attack, xDirection, yDirection));
            attackIterator = gameData.getAttacks().iterator();
        }
    }

    public void spellInDirection(String spell, int direction) {
        if (canMove(gameData.getPlayer())) {
            if (spell.equals(FlashAttack.NAME)) {
                if (direction == NORTH || direction == SOUTH || direction == EAST || direction == WEST) {
                    flashAttack(gameData.getPlayer().getMagicAttack(), gameData.getPlayer().getX()+xDirection[direction],
                            gameData.getPlayer().getY()+yDirection[direction], xDirection[direction], yDirection[direction]);
                } else {
                    writelnInConsole("Nie możesz czarować w tym kierunku");
                }
            }else if(spell.equals(FireballAttack.NAME)) {
                    fireballAttack(gameData.getPlayer().getMagicAttack(), gameData.getPlayer().getX()+xDirection[direction],
                            gameData.getPlayer().getY()+yDirection[direction], xDirection[direction], yDirection[direction]);
            }
        }

    }

    public void nextTurn() {
        if (turn == TURN_PLAYER) {
            turn = TURN_ENEMY;
            enemiesIterator = gameData.getCurrentRoom().getEnemies().iterator();
        }
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            if (!gameData.getAttacks().isEmpty()) {
                attackIterator = gameData.getAttacks().listIterator();
                while (attackIterator.hasNext()) {
                    GameAttack gA = (GameAttack) attackIterator.next();
                    if (gA.isMarkedToRemove()) {
                        attackIterator.remove();
                    }
                }
                nextTurn();
            } else {
                if (turn == TURN_PLAYER) {
                    turn = TURN_ENEMY;
                    enemiesIterator = gameData.getCurrentRoom().getEnemies().iterator();
                } else if (turn == TURN_ENEMY) {
                    if (enemiesIterator.hasNext()) {
                        GameEnemy gE = (GameEnemy) enemiesIterator.next();
                        if (gE.isAlive()) {
                            gE.takeTurn();
                        } else {
                            gameData.getPlayer().addExperience(gE.getExpReward());
                            enemiesIterator.remove();
                            nextTurn();
                        }
                    } else {
                        turn = TURN_PLAYER;
                    }
                }
            }
    }

}
