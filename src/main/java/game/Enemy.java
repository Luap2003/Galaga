package main.java.game;

import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends GameObject {
    private int lives;
    private int damage;

    public Enemy(int x, int y, int dx, int dy, int lives, int damage, int width, int height) {
        super(x, y, dx, dy, width, height);
        this.lives = lives;
        this.damage = damage;
    }

    public Projectile shoot() {
        return new Projectile(x+width/2, y+height+1, 0, 2, damage, 2, 10, Color.RED);
    }

    public void tick() {
        x += dx;
        y += dy;
    }

    @Override
    public void render(Graphics g) {
        Image img = new Image();
        img.renderEnemy(g, x, y, width, height);
    }

    public void getHit(int damage) {
        lives -= damage;
    }

    public int getLives() {
        return this.lives;
    }
}