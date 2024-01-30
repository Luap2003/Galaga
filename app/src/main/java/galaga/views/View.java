package galaga.views;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import galaga.game.*;
import java.awt.Color;

public class View extends JPanel {
    private Model model;

    public View(Model model) {
        this.model = model;

        // Add a key listener to this view
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_RIGHT) {
                    // Move player to the right
                    model.getPlayer().setDx(model.getPlayer().getSpeed());
                } else if (key == KeyEvent.VK_LEFT) {
                    // Move player to the left
                    model.getPlayer().setDx(-model.getPlayer().getSpeed());
                } else if (key == KeyEvent.VK_UP) {
                    // Move player up
                    model.getPlayer().setDy(-model.getPlayer().getSpeed());
                } else if (key == KeyEvent.VK_DOWN) {
                    // Move player down
                    model.getPlayer().setDy(model.getPlayer().getSpeed());
                } 
                if (key == KeyEvent.VK_SPACE) {
                    // Shoot when space bar is pressed
                    model.addGameObject(model.getPlayer().shoot());
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_RIGHT && model.getPlayer().getDx() > 0) {
                    model.getPlayer().setDx(0);
                }   else if (key == KeyEvent.VK_LEFT && model.getPlayer().getDx() < 0) {
                    model.getPlayer().setDx(0);
                } 
                else if (key == KeyEvent.VK_UP  && model.getPlayer().getDy() < 0) {
                    // Stop vertical movement
                        model.getPlayer().setDy(0);
                }
                else if (key == KeyEvent.VK_DOWN && model.getPlayer().getDy() > 0) {
                    // Stop vertical movement
                        model.getPlayer().setDy(0);
                }
            }
            
        });

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());


        // Render game objects
        List<GameObject> gameObjects = model.getGameObjects();
        for (GameObject obj : gameObjects) {
            obj.render(g);
        }
    }



}
