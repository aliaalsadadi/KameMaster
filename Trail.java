package com.example;

import java.awt.*;

public class Trail extends GameObject{
    private float aplha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    private float life;

    public Trail(float x, float y,int width, int height ,ID id,Color color,float life ,Handler handler) {
        super(x, y, id);
        this.color = color;
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.life = life;
    }

    public Rectangle getBounds() {
        return null;
    }

    public void tick() {
        if (aplha > life){
            aplha-=(life-0.01f);
        }
        else{
            handler.removeObject(this);
        }
    }
    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type,alpha);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(aplha));
        g.setColor(color);
        g.fillRect((int) x,(int) y,width,height);
        g2d.setComposite(makeTransparent(1));
    }
}
