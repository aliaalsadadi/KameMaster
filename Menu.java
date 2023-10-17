package com.example;
import java.sql.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private HUD hud;
    private Random random = new Random();
    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        if (game.gameState == Game.STATE.Menu){
            //playbutton
            if (mouseOver(mx,my,210,150,200,64)){
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(0, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(Game.WIDTH, random.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
            }
            //QUITBUTTON
            if (mouseOver(mx,my,210,350,200,64)){
                System.exit(1);
            }
            //help button
            if (mouseOver(mx,my,210, 250, 200, 64)){
                game.gameState = Game.STATE.Help;
            }
        }
        //back button for help
        if (game.gameState == Game.STATE.Help){
            if (mouseOver(mx,my,210,350,200,64)){
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
        //restart
        if (game.gameState == Game.STATE.End){
            if (mouseOver(mx,my,210, 350, 200, 64)){
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(0, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new Player(0, Game.HEIGHT/2-32, ID.Player, handler));
                handler.addObject(new BasicEnemy(Game.WIDTH, random.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));            }
        }


    }

    public void mouseReleased(MouseEvent e) {

    }
    private boolean mouseOver(int mx, int my,int x, int y, int width, int height){
        if (mx > x && mx < x+width){
            if (my > y && my < y+height){
                return true;
            }else return false;
        }else return false;
    }
    public void render(Graphics graphics) throws SQLException {
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            graphics.setFont(fnt);
            graphics.setColor(Color.white);
            graphics.drawString("Menu", 240, 70);
            graphics.setFont(fnt2);
            graphics.setColor(Color.white);
            graphics.drawRect(210, 150, 200, 64);
            graphics.drawString("Play", 270, 190);

            graphics.setColor(Color.white);
            graphics.drawRect(210, 250, 200, 64);
            graphics.drawString("Help", 270, 290);

            graphics.setColor(Color.white);
            graphics.drawRect(210, 350, 200, 64);
            graphics.drawString("Quit", 270, 390);
        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            graphics.setFont(fnt);
            graphics.setColor(Color.white);
            graphics.drawString("Help",240,70);

            graphics.setFont(fnt3);
            graphics.drawString("USE ASDW keys to move player and dodge enemies",50,200);
            graphics.drawString("you can shoot with the spacebar!",50,250);


            graphics.setFont(fnt2);
            graphics.setColor(Color.white);
            graphics.drawRect(210, 350, 200, 64);
            graphics.drawString("Back", 270, 390);
        }else if (game.gameState == Game.STATE.End) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            graphics.setFont(fnt);
            graphics.setColor(Color.white);
            graphics.drawString("Game over",180,70);

            graphics.setFont(fnt3);
            graphics.drawString("you lost \nscore:" + hud.getScore(),175,200);
            graphics.drawString("HighScore: " + SQL(hud.getScore()),175,250);

            graphics.setFont(fnt2);
            graphics.setColor(Color.white);
            graphics.drawRect(210, 350, 200, 64);
            graphics.drawString("Restart", 245, 390);
        }
    }
    private int SQL(int score) throws SQLException {
        int high_score = 0;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "alooi999");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        stmt = c.createStatement();
        String sql = "SELECT * from SCORE;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            high_score = rs.getInt("score");
        }
        if (score>high_score) {
            sql = "UPDATE SCORE set score = " + score;
            stmt.executeUpdate(sql);
        }
        stmt.close();
        c.close();
        return high_score;
    }
    public void tick(){

    }
}
