package main.java.decoraters;

import java.awt.Graphics;

import main.java.game.GameObject;

public abstract class PlayerDecorator {
    protected GameObject player;

    public PlayerDecorator(GameObject player) {
        this.player = player;
    }
}