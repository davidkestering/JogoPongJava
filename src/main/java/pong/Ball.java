package pong;

import static pong.Game.SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

  public double x, y;
  public static final int WIDTH_B = 10;
  public static final int HEIGHT_B = 10;

  public double dx, dy;
  public double speed = 2;

  public Ball(int x, int y) {
    this.x = x;
    this.y = y;

    int angle = new Random().nextInt((360 - 1)) + 1; //minimo de 1 e maximo de 360 graus
    while ((angle >= 1 && angle <= 30) ||
           (angle >= 70 && angle <= 130) ||
           (angle >= 170 && angle <= 210) ||
            (angle >= 260 && angle <= 300) ||
            (angle >= 340)){
      angle = new Random().nextInt((360 - 1)) + 1;
    }
    dx = Math.cos(Math.toRadians(angle));
    dy = Math.sin(Math.toRadians(angle));

    //dx = new Random().nextGaussian();
    //dy = new Random().nextGaussian();
    /*dx = randInt(-1,1);
    dy = randInt(-1,1);
    if(dx == 0){
      dx = 1;
    }
    if(dy==0){
      dy = 1;
    }*/
  }

  public void tick() {
    x += dx * speed;
    y += dy * speed;

    //colisao com paredes
    if (((y + (dy * speed)) + (HEIGHT_B/SCALE)) >= Game.HEIGHT) {
      dy *= -1;
    }else if( (y+(dy*speed)) < 0 ){
      dy *= -1;
    }

    //marcando pontos
    if(x >= Game.WIDTH){
      //ponto do player
      System.out.println("Ponto do player");
      new Game();
      return;
    }else if(x <= 0){
      //ponto do inimigo
      System.out.println("Ponto do inimigo");
      new Game();
      return;
    }

    //colisao entre bola e player e inimigos
    Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),WIDTH_B/SCALE,HEIGHT_B/SCALE);
    Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,(int)(Game.player.WIDTH_P/SCALE),(int)(Game.player.HEIGHT_P/SCALE));
    Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,(int)(Game.enemy.WIDTH_E/SCALE),(int)(Game.enemy.HEIGHT_E/SCALE));
    if(bounds.intersects(boundsPlayer)){
      int angle = new Random().nextInt((360 - 1)) + 1; //minimo de 1 e maximo de 360 graus
      while (angle >= 91 && angle <= 270){
        angle = new Random().nextInt((360 - 1)) + 1;
      }
      dx = Math.cos(Math.toRadians(angle));
      dy = Math.sin(Math.toRadians(angle));
      if(dx < 0)
        dx*=-1;
    }else if(bounds.intersects(boundsEnemy)){
      int angle = new Random().nextInt((360 - 1)) + 1; //minimo de 1 e maximo de 360 graus
      while ((angle >= 1 && angle <= 90) ||
          (angle >= 271 && angle <= 360)){
        angle = new Random().nextInt((360 - 1)) + 1;
      }
      dx = Math.cos(Math.toRadians(angle));
      dy = Math.sin(Math.toRadians(angle));
      if(dx > 0)
        dx*=-1;
    }

  }

  public void render(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect((int) x, (int) y, WIDTH_B / SCALE, HEIGHT_B / SCALE);
  }


  public static int randInt(int min, int max) {

    // NOTE: This will (intentionally) not run as written so that folks
    // copy-pasting have to think about how to initialize their
    // Random instance.  Initialization of the Random instance is outside
    // the main scope of the question, but some decent options are to have
    // a field that is initialized once and then re-used as needed or to
    // use ThreadLocalRandom (if using at least Java 1.7).
    //
    // In particular, do NOT do 'Random rand = new Random()' here or you
    // will get not very good / not very random results.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
  }

}
