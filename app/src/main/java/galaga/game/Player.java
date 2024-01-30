package galaga.game;

import java.awt.Graphics;
import java.awt.Color;

public class Player extends GameObject {
    private static Player uniqueInstance;
    private int lives;
    private int speed;
    private int damage;

    
    private Player(int x, int y, int dx, int dy, int width, int height) {
        super(x, y, dx, dy, width, height);
        this.lives = 3;
        this.speed = 2;
        this.damage = 1;
        // Initialize player-specific attributes like lives, speed, damage
    }

    public static Player getInstance(int x, int y, int dx, int dy, int width, int height) {
        if (uniqueInstance == null) {
            uniqueInstance = new Player(x, y, dx, dy, width, height); // Define startingX and startingY
        }
        return uniqueInstance;
    }

    @Override
    public void tick() {
        // Player movement logic
        x += dx*speed;
        y += dy*speed;

        if (x <= 0) {
            x = 0;
        }
        if (x >= 2000) {
            x = 2000;
        }
        if (y <= 0) {
            y = 0;
        }
        if (y >= 2000) {
            y = 2000;
        }
    }

    @Override
    public void render(Graphics g) {
        Image img = new Image();
        img.renderPlayer(g, x, y, width, height);
    }

    public Projectile shoot() {
        // Player shooting logic
        // return new Projectile(...); // Provide necessary parameters
        return new Projectile(x+width/2, y-11, 0, -2, damage, 2, 10,Color.BLUE, "player");
        
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getDy() {
        return this.dy;
    }

    public int getDx() {
        return this.dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    


    //public void applyPowerUp(PlayerDecorator powerUp) {
        // Apply power-up to player
    //}

}