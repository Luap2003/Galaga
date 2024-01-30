package main.java.views;

import java.util.List;
import main.java.game.*;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

public class Model {
    private List<GameObject> gameObjects;
    private int[][] map = new int[2000][2000];
    private Player player;
    private Timer timer;

    public Model() {
        gameObjects = new CopyOnWriteArrayList<>();
        initGame();
    }

    private void initGame() {
        // Initialize game objects (player, enemies, etc.)
        player = Player.getInstance((int) map.length/2, (int) map[0].length, 0, 0, 100,100);
        gameObjects.add(player);
        gameObjects.add(new Enemy((int) map.length/2, 0, 0, 0, 10, 0, 100, 100));
        //gameObjects.add(new Asteroid(200, 200, 0, 1, 10, 100, 100));


        // Schedule a new TimerTask to run every second
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {@Override public void run() {enemyAutoShoot();}}, 0, 2000);
        // Add other game objects like enemies, asteroids, etc.
        
    }

    public void update() {
        spawnEnemies();

        for (Iterator<GameObject> iterator = gameObjects.iterator(); iterator.hasNext();) {
            GameObject obj = iterator.next();
            obj.tick();
        }
        gameObjects.removeIf(obj -> (obj instanceof Projectile || obj instanceof Asteroid) && 
        (obj.getX() < 0 || obj.getX() > map.length || obj.getY() < 0 || obj.getY() > map[0].length));
        gameObjects.removeIf(obj -> obj instanceof Enemy && ((Enemy) obj).getLives() <= 0);
        gameObjects.removeIf(obj -> obj instanceof Asteroid && ((Asteroid) obj).getHealth() <= 0);
        checkCollisions();

    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    public int[][] getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }   

    public void enemyAutoShoot() {
        for (GameObject obj : gameObjects) {
            if (obj instanceof Enemy) {
                // Enemy shooting logic
                gameObjects.add(((Enemy) obj).shoot());
            }
        }
    }

    public void checkCollisions() {
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject obj1 = gameObjects.get(i);
                GameObject obj2 = gameObjects.get(j);

                if (obj1.getBounds().intersects(obj2.getBounds())) {
                    if (obj1 instanceof Player && obj2 instanceof Projectile) {
                        ((Player) obj1).setLives(((Player) obj1).getLives() - 1);
                        System.out.println("Player lives: " + ((Player) obj1).getLives());
                        gameObjects.remove(obj2);
                    }
                    if (obj1 instanceof Enemy && obj2 instanceof Projectile) {
                        ((Enemy) obj1).getHit(((Projectile) obj2).getDamage());
                        gameObjects.remove(obj2);
                    } else if (obj1 instanceof Projectile && obj2 instanceof Enemy) {
                        ((Enemy) obj2).getHit(((Projectile) obj1).getDamage());
                        gameObjects.remove(obj1);
                    }
                    if (obj1 instanceof Asteroid && obj2 instanceof Projectile) {
                        ((Asteroid) obj1).getHit(((Projectile) obj2).getDamage());
                        gameObjects.remove(obj2);
                    }
                    if (obj1 instanceof Player && obj2 instanceof Asteroid) {
                        ((Player) obj1).setLives(((Player) obj1).getLives() - 1);
                        gameObjects.remove(obj2);
                    }
                }
            }
        }
    }

    public void spawnEnemies() {
        Random random = new Random();
        if (random.nextInt(200) < 1) {
            int x = 800+random.nextInt(400); // Random x-coordinate
            int y = 0; // Top of the screen
            int dx = random.nextInt(2) * 2 - 1; // Random horizontal speed between -1 and 1
            int dy = 2; // Random vertical speed between 1 and 3
            int lives = random.nextInt(30) + 1; // Random number of lives between 1 and 3
            Enemy enemy = new Enemy(x, y, dx, dy, 1, lives,100,100 ); // Create new enemy
            gameObjects.add(enemy); // Add enemy to game objects
        }

    }


}