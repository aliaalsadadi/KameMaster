package com.example;

import java.awt.*;

public class FastEnemy extends GameObject{
    private Handler handler;
    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX=5;
        velY=5;
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y,32,32);
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
        if (y<=0 || y>= Game.HEIGHT-40) velY*=-1;
        if (x<=0 || x>= Game.WIDTH-20) velX*=-1;
        handler.addObject(new Trail(x,y,32,32,ID.FastEnemy,Color.magenta,0.1f, handler));
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect((int) x,(int) y,32,32);
    }

}
