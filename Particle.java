package com.example;

import java.awt.*;
import java.util.Random;

public class Particle extends GameObject{
    private Handler handler;
    Random random = new Random();
    private Color color;
    public Particle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX=random.nextInt(10);
        velY=random.nextInt(10);
        if (velX==0) velX=1;
        if(velY==0) velY=1;
        color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y,16,16);
    }

    public void tick() {
//        Random random = new Random();
        x += velX;
        y += velY;
        if (y<=0 || y>= Game.HEIGHT-40) velY*=-1;
        if (x<=0 || x>= Game.WIDTH-20) velX*=-1;
        handler.addObject(new Trail(x,y,16,16,ID.Particle,color,0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x,(int) y,16,16);
    }

}
