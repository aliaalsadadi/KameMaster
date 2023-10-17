package com.example;

import java.awt.*;

public class KI extends GameObject{
    private Handler handler;
    private Color color;
    public KI(float x, float y, ID id, Handler handler,Color color) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
    }
    public Rectangle getBounds() {
        return new Rectangle((int) x,(int)y,16,16);
    }

    public void tick() {
        x += 10;
        y += velY;
        if (x > Game.WIDTH){
            handler.removeObject(this);
        }
        handler.addObject(new Trail(x,y,16,16,ID.KI,color,0.1f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y,16,16);
    }
}
