package pong;

import static pong.Game.SCALE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Scrollbar;

public class Player {

  public boolean up = false,down = false;
  public int x,y,speed = 3;
  public static final int WIDTH_P = 10;
  public static final int HEIGHT_P = 40;

  public Player(int x, int y){
    //this.x = (((WIDTH/2)-((10/SCALE)/2))/SCALE)-10; --meio
    //this.y = (HEIGHT/2)-((40/SCALE)/2); --meio
    this.x = x;
    this.y = y;
  }

  public void tick() {
    if(up){
      if(speed > 0){
        y = y-speed;
      }else{
        y--;
      }
    }else if(down){
      if(speed > 0){
        y = y+speed;
      }else{
        y++;
      }
    }

    if(y+(HEIGHT_P/SCALE) > Game.HEIGHT){
      y = Game.HEIGHT - (HEIGHT_P/SCALE);
    }else if(y <= 0){
      y = 0;
    }

  }

  public void render(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(x,y, WIDTH_P/SCALE, HEIGHT_P/SCALE);
  }

}
