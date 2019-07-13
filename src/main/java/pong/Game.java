package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

  private static final long serialVersionUID = 1L;
  public static final int WIDTH = 160;
  public static final int HEIGHT = 120;
  public static final int SCALE = 3;

  public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

  public static Player player;
  public static final int MEIOX_P = ((((WIDTH / 2) - ((Player.WIDTH_P / SCALE) / 2))) / (SCALE*2));
  public static final int MEIOY_P = (HEIGHT / 2) - ((Player.HEIGHT_P / SCALE) / 2);
  public static final int MEIOX_E = (WIDTH - ((((WIDTH / 2) - ((Player.WIDTH_P / SCALE) / 2))) / (SCALE*2)));
  public static final int MEIOY_E = (HEIGHT / 2) - ((Enemy.HEIGHT_E / SCALE) / 2);
  public static final int MEIOX_B = WIDTH/2;
  public static final int MEIOY_B = HEIGHT/2;

  public static Enemy enemy;
  public static Ball ball;

  public Game() {
    this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    this.addKeyListener(this);
    this.setFocusable(true); //necessario para funcionar a captura automatica de teclas
    player = new Player(MEIOX_P, MEIOY_P);
    enemy = new Enemy(MEIOX_E, MEIOY_E);
    ball = new Ball(MEIOX_B,MEIOY_B);
  }

  public static void main(String[] args) {
    Game game = new Game();

    JFrame frame = new JFrame("Pong");
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(game);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    //frame.setFocusable(true);
    //frame.requestFocus();

    new Thread(game).start();
  }

  public void tick() {
    player.tick();
    enemy.tick();
    ball.tick();
  }

  public void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = layer.getGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    player.render(g);
    enemy.render(g);
    ball.render(g);
    g = bs.getDrawGraphics();
    g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
    bs.show();
  }

  @Override
  public void run() {
    while (true) {
      tick();
      render();
      try {
        Thread.sleep(1000 / 60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    //System.out.println(e.getKeyCode());
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      player.up = true;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      player.down = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //System.out.println(e.getKeyCode());
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      player.up = false;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      player.down = false;
    }
  }
}
