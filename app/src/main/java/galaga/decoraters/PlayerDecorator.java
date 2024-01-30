package galaga.decoraters;

import java.awt.Graphics;

import galaga.game.GameObject;

public abstract class PlayerDecorator {
    protected GameObject player;

    public PlayerDecorator(GameObject player) {
        this.player = player;
    }
}