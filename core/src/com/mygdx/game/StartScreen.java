package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class StartScreen {

    static Texture startScreen;
    static Rectangle playbuttonbox;
    static Texture Playbutton;
    static SpriteBatch batch;
    static Texture sky;
    static Texture platform1;
    static int width;
    static int height;
    static Vector2 playPosition;
    static boolean atmenu;
    static Texture clouds;

    public static void create() {
        batch = new SpriteBatch();
        startScreen = new Texture("startScreen.png");
        Playbutton = new Texture("play_button.png");
        sky = new Texture("sky.png");
        platform1 = new Texture("platform1.png");
        clouds = new Texture("cloud.png");
        height = Gdx.graphics.getHeight() / 2;
        width = Gdx.graphics.getWidth() / 2;
        playPosition = new Vector2();
        playbuttonbox = new Rectangle();
        playbuttonbox.set(width/2 + 50, 347, Playbutton.getWidth(), Playbutton.getHeight());
        playPosition.set(width/2, 100);
        atmenu = true;
    }

    public static void updategame(){
        if (Gdx.input.isTouched()) {
            float X = Gdx.input.getX();
            float Y = Gdx.input.getY();
            System.out.println(X + " " + Y);
            if (playbuttonbox.contains(X, Y)) {
                System.out.println("HIT ME");
                atmenu = false;
            }
        }
    }
    public static void render() {
        batch.begin();
        batch.draw(sky, 0, 66);
        batch.draw(platform1, 0, 0);
        batch.draw(clouds, width/2 - 250, 325);
        batch.draw(startScreen, width / 2 - 45, 300);
        batch.draw(Playbutton, width / 2 + 50, 100);
        batch.end();
    }
}
