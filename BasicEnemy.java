package com.example;

import java.awt.*;

public class BasicEnemy extends GameObject{
    private Handler handler;
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
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
        x += -1;
        y += velY;
        y = Game.clamp(y,0,Game.HEIGHT-60);
        if (x < 0){
            HUD.HEALTH -=1;
            handler.removeObject(this);
        }
        handler.addObject(new Trail(x,y,64,64,ID.KI,Color.red,0.1f, handler));
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x,(int) y,64,64);
    }

}
