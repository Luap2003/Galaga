package galaga.views;

import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);

        JFrame frame = new JFrame("Galaga Game");
        frame.add(view);
        frame.setSize(model.getMap().length+100, model.getMap()[0].length+150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        final long OPTIMAL_TIME = 10000000; // 100,000,000 nanoseconds = 0.1 seconds
        final long MIN_FRAME_TIME = 5000000; // 5,000,000 nanoseconds = 0.005 seconds

        while (frame.isVisible()) {
            long startTime = System.nanoTime();

            model.update();
            view.repaint();

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
    
            long sleepTime = Math.max(MIN_FRAME_TIME, OPTIMAL_TIME - elapsedTime);

            try {
                // Convert the sleep time to milliseconds and sleep
                Thread.sleep(sleepTime / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
