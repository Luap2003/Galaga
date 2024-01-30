package main.java.game;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObject {
    protected int x, y, dx, dy, width, height;
    
    public GameObject(int x, int y, int dx, int dy, int width, int height) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDx() {
        return this.dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }


}