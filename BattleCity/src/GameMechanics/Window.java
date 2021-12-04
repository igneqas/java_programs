package GameMechanics;

import GameMechanics.KeyboardInput.KeyboardMonitor;
import GameMechanics.KeyboardInput.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame implements Observer {

    //private JPanel gamePanel;
    private boolean exitWindow = false;
    private KeyboardMonitor keyboardMonitor;

    public Window(){
        initializeWindow();
        keyboardMonitor = new KeyboardMonitor();
        keyboardMonitor.AddObserver(this);
        this.addKeyListener(keyboardMonitor);
        checkForGameStart();
    }

    private void initializeWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle city");
        setPreferredSize(new Dimension(550,550));
        getContentPane().setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        paint(getGraphics());
        Graphics g = getGraphics();
        try {
            g.setFont(loadFont(40));
            g.setColor(Color.YELLOW);
            g.drawString("PRESS ENTER", 55,270);
            g.drawString("TO START", 115,350);
            g.setFont(loadFont(20));
            g.drawString("Esc to close window", 80,500);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        /*gamePanel = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle city");
        setPreferredSize(new Dimension(512,470));
        setBackground(Color.black);
        gamePanel.setMinimumSize(new Dimension(500,500));
        gamePanel.setSize(new Dimension(528,448));
        gamePanel.setLayout(new GridLayout(1,0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();*/
        //super.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private void checkForGameStart(){
        while(!exitWindow) {
            System.out.println("Press enter");
        }
        /*this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if(e.getKeyCode() == 10){
                    exitWindow = true;
                }
                else if(e.getKeyCode() == 27){
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        while(true){
            System.out.println("Press Enter");
            if(exitWindow) {
                setVisible(false);
                break;
            }
        }*/
    }

    public static Font loadFont(int fontSize) throws IOException, FontFormatException {
        Font font = null;
        font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                new File("prstart.ttf"));
        font = font.deriveFont(java.awt.Font.PLAIN, fontSize);
        GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        return font;
    }

    @Override
    public void processKeyInput(KeyEvent keyEvent) {
        System.out.println(keyEvent.getKeyCode());
        if(keyEvent.getKeyCode() == 10){
            exitWindow = true;
        }
    }

}
