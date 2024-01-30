package main.java.game;

import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends GameObject {
    private int lives;
    private int damage;
    private enum State { ENTERING, RETURNING, LOOPING, DIVING }
    private State state;
    private int stopping_x;
    private int stopping_y;
    private double theta = 0; // Add this field to your Enemy class
    private double r;
    private int cx;
    private int cy;

    public Enemy(int x, int y, int dx, int dy, int lives, int damage, int width, int height) {
        super(x, y, dx, dy, width, height);
        this.lives = lives;
        this.damage = damage;
        this.state = State.ENTERING;
        this.stopping_x = x;
        this.stopping_y = y+100;
        this.cx = ((stopping_x+500*dx)/2 + x+500*dx)/2;
        this.cy = 1000;
        this.r = Math.abs(cx - (x+500*dx));

    }

    public Projectile shoot() {
        return new Projectile(x+width/2, y+height+1, 0, 2, damage, 2, 10, Color.RED);
    }

    public void tick() {
        switch (state) {
            case ENTERING:
                if (y < 1000) {
                    y += dy;
                    x += dx;
                } else {
                    state = State.RETURNING;
                }
                break;
            case RETURNING:
            if (y >= 1000) {
                // Increment the angle
                theta += 0.02; // Change this to adjust the speed of the circular motion
                // Calculate the new x and y coordinates
                x = (int) (cx + r * Math.cos(theta));
                y = (int) (cy + r * Math.sin(theta));

            } else {
                // Same as before
                int diffX = stopping_x - x;
                x += diffX * 0.02;
                int diffY = stopping_y - y;
                y += diffY * 0.02;
                if (Math.abs(diffX) < 5 && Math.abs(diffY) < 5) {
                    state = State.LOOPING;
                }
            }
                break;
            case LOOPING:
                x += dx;
                if (x < 0 || x > 2000 - width) { // Change 2000 to the width of your game area
                    dx = -dx; // Reverse direction when reaching the edge
                }
                break;
            case DIVING:
                // Implement diving behavior here
                break;
        }
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