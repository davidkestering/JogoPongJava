package pong;

import static pong.Game.SCALE;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

  public double x,y;
  public static final int WIDTH_E = Player.WIDTH_P;
  public static final int HEIGHT_E = Player.HEIGHT_P;

  public Enemy(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void tick() {
    y+=(Game.ball.y - y) * 0.4;

    if(y+(HEIGHT_E/SCALE) > Game.HEIGHT){
      y = Game.HEIGHT - (HEIGHT_E/SCALE);
    }else if(y <= 0){
      y = 0;
    }
  }

  public void render(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect((int)x,(int)y, WIDTH_E/SCALE, HEIGHT_E/SCALE);
  }
}
