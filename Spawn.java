package com.example;
import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random random = new Random();
    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }
    public void tick(){
        scoreKeep++;
        if (scoreKeep >= 250){
            scoreKeep =0;
            hud.setLevel(hud.getLevel()+1);

            if (hud.getLevel() == 2){
                handler.addObject(new BasicEnemy(Game.WIDTH, random.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
            }
            else if (hud.getLevel() == 3) {
                handler.addObject(new BasicEnemy(Game.WIDTH, random.nextInt(Game.HEIGHT-60), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(Game.WIDTH, random.nextInt(Game.HEIGHT-60), ID.BasicEnemy, handler));
            }
            else if (hud.getLevel() == 4) {
                handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH-50), Game.HEIGHT/2-32, ID.FastEnemy, handler));
            }
            else if (hud.getLevel() == 5) {
                handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH-50), Game.HEIGHT/2-32, ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 6) {
                handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH-50), Game.HEIGHT/2-32, ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 7) {
                handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH-50), Game.HEIGHT/2-32, ID.FastEnemy, handler));
                handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH-50), Game.HEIGHT/2-32, ID.FastEnemy, handler));
            } else if (hud.getLevel() == 8) {
                handler.addObject(new BasicEnemy(Game.WIDTH, random.nextInt(Game.HEIGHT-60), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(Game.WIDTH, random.nextInt(Game.HEIGHT-60), ID.BasicEnemy, handler));
            } else if (hud.getLevel() == 9) {
                handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH-50), Game.HEIGHT/2-32, ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 10) {
                handler.clearEnemies();
                handler.addObject(new Boss1((Game.WIDTH/2)-48,-120, ID.Boss1, handler));
            }
            handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH-50), Game.HEIGHT/2-32, ID.FastEnemy, handler));
        }
    }
}
