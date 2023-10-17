package com.example;

import java.awt.*;
import java.util.Random;

public class Boss1Bullet extends GameObject{
    private Handler handler;
    Random random = new Random();
    public Boss1Bullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (random.nextInt(5 - -5) + 5);
        velY = 5;
    }
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y,16,16);
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
        x += -1;
        y += velY;
        if (x < 0){
            handler.removeObject(this);
        }
        handler.addObject(new Trail(x,y,16,16,ID.KI,Color.red,0.1f, handler));
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x,(int) y,16,16);
    }

}
