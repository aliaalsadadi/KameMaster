package com.example;

import java.awt.*;

public abstract class GameObject {
    protected float x,y;
    protected ID id;
    protected float velX, velY;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract Rectangle getBounds();
    public abstract void tick();
    public abstract  void render(Graphics g);
    public void setX(float x){
        this.x = x;
    }
    public void setVelX(float velX){this.velX = velX;}
    public void setVelY(float velY){this.velY = velY;}
    public void setY(float y){
        this.y = y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
}