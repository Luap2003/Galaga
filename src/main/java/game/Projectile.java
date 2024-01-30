package main.java.game;

import java.awt.Graphics;
import java.awt.Color;

public class Projectile extends GameObject {
    private int damage;
    private Color color;

    public Projectile(int x, int y, int dx, int dy, int damage, int width, int height, Color color) {
        super(x, y, dx, dy, width, height);
        this.damage = damage;
        this.color = color;
        // Initialize projectile attributes
    }   

    @Override
    public void tick() {
        // Projectile movement logic
        x += dx;
        y += dy;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color); // Set the color to yellow
        g.fillOval(x, y, 2, 10); // Draw an oval to represent the projectile
    }

    public int getDamage() {
        return this.damage;
    }
}