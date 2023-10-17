package com.example;

import java.awt.*;
import java.util.Random;

public class Boss1 extends GameObject{
    private Handler handler;
    private int timer = 80;
    private int timer2 = 50;
    Random random = new Random();

    public Boss1 (int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX=0;
        velY=2;
    }
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y,64,64);
    }
    private void collision(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.KI && tempObject.getBounds() != null){
                if (getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public void tick() {
//        Random random = new Random();
        x += velX;
        y += velY;
        if(timer <= 0 )velY=0;
        else timer--;
        if(timer <=0) timer2--;
        if (timer2 <= 0){
            if(velX == 0) velX=2;
            if (velX> 0) velX+=0.005f;
            else if (velX < 0) {
                velX -=0.005f;
            }
            velX = Game.clamp(velX,-10,10);
            int spawn = random.nextInt(10);
            if(spawn==0) handler.addObject(new Boss1Bullet((int)x,(int)y,ID.BasicEnemy,handler));
        }
//        if (y<=0 || y>= Game.HEIGHT-40) velY*=-1;
        if (x<=0 || x>= Game.WIDTH-96) velX*=-1;
        //handler.addObject(new Trail(x,y,64,64,ID.KI,Color.yellow,0.1f, handler));
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) x,(int) y,64,64);
    }

}
