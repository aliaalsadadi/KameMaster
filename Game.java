package com.example;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.sql.SQLException;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Random random= new Random();
    public enum STATE{
        Menu,
        Game,
        Help,
        End,
    }
    public STATE gameState = STATE.Menu;
    public Game() throws MalformedURLException {
        Stats stats = new Stats();
        double strength = stats.strength();
        System.out.println(strength);
        Color color;
        if (strength>0.75){
            color = Color.white;
        } else if (strength > 0.5) {
            color = Color.cyan;
        }
        else if (strength > 0.25) {
            color = Color.green;
        }else{
            color = Color.red;
        }
        handler = new Handler(this);
        hud = new HUD();
        menu = new Menu(this,handler,hud);
        this.addKeyListener(new Keyinput(handler,color));
        this.addMouseListener(menu);

        new Window(WIDTH,HEIGHT,"KameMaster", this);
        Random random = new Random();
        spawner = new Spawn(handler,hud);
        if (gameState == STATE.Game){
            handler.addObject(new Player(0, HEIGHT/2-32, ID.Player, handler));
        }else{
            for (int i=0;i<20;i++){
                handler.addObject(new Particle(random.nextInt(WIDTH),random.nextInt(HEIGHT),ID.Particle,handler));
            }
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void gameSound() throws MalformedURLException {
        File file = new File("src/game.wav");
        URL url = file.toURI().toURL();
        System.out.println(url);
        AudioClip clip = Applet.newAudioClip(url);

        while (!Thread.currentThread().isInterrupted()) {
            clip.play();

            try {
                Thread.sleep(3*60*1000 + 5*1000); // Delay for 3 minutes and 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running) {
                try {
                    render();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    private void tick(){
        handler.tick();

        if (gameState == STATE.Game){
            hud.tick();
            spawner.tick();
            if (HUD.HEALTH <=0){
                HUD.HEALTH = 100;
                gameState = STATE.End;
                handler.clearEnemies();
                for (int i=0;i<20;i++){
                    handler.addObject(new Particle(random.nextInt(WIDTH),random.nextInt(HEIGHT),ID.Particle,handler));
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End) {
            menu.tick();
        }
    }
    private  void render() throws SQLException {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();
        graphics.setColor(Color.blue);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        handler.render(graphics);
        if (gameState == STATE.Game){
            hud.render(graphics);
        }else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
            menu.render(graphics);
        }
        graphics.dispose();
        bs.show();
    }
    public static float clamp(float var,float min, float max){
        if (var >= max) return var=max;
        else if (var <= min) return var=min;
        else return var;
    }
    public static void main(String[] args) throws MalformedURLException {
        new Game();
        Thread soundThread = new Thread(() -> {
            try {
                gameSound();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
        soundThread.start();

    }
}