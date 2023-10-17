package com.example;

import java.awt.*;

public class SmartEnemy extends GameObject{
    private Handler handler;
    private GameObject player;
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for (int i=0;i<handler.object.size();i++){
            if (handler.object.get(i).getId() == ID.Player){
                player = handler.object.get(i);
            }
        }

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
        float diffX = x - player.getX()-16;
        float diffY = y - player.getY()-16;
        float distance = (float) Math.sqrt((x- player.getX())*(x - player.getX()) + (y - player.getY())*(y- player.getY()));
        velX =  ((-1/distance)*diffX);
        velY =  ((-1/distance)*diffY);
        y = Game.clamp(y,0,Game.HEIGHT-60);
        if (x < 0){
            HUD.HEALTH -=1;
            handler.removeObject(this);
        }
        handler.addObject(new Trail(x,y,32,32,ID.KI,Color.green,0.1f, handler));
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x,(int) y,32,32);
    }

}
