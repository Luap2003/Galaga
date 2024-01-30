package main.java.game;

import java.awt.Graphics;
import java.awt.Color;

public class Asteroid extends GameObject {
    private int health;

    public Asteroid(int x, int y, int dx, int dy, int health, int width, int height) {
        super(x, y, dx, dy, width, height);
        this.health = health;
    }

    @Override
    public void tick() {
        // Asteroid movement logic
        x += dx;
        y += dy;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK); // Set the color to gray
        g.fillOval(x, y, width, height); // Draw an oval to represent the asteroid
    }

    public void getHit(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return this.health;
    }

}
