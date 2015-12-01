
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import java.util.ArrayList;


import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
    int PLAYER_SPEED = 5;
    Rectangle bulletBounds;

    Matrix4 IDMATRIX;

    Animation koala;
    Animation hurt;
    Texture[] koalared;
    Texture Player;
    Texture sky;
    Texture youdead;
    boolean movingForward = true;

    Texture tileset, tileset2, tileset3, tileset4, tileset5, tileset6, tileset7, tileset8;
    TiledMapRenderer mapRenderer;
    TiledMap map;
    MapObjects objects, spikes;
    MapLayer GroundObject, SpikesObjects;
    int mapWidth, mapHeight, mapPixelWidth, mapPixelHeight, tilePixelWidth, tilePixelHeight;

    SpriteBatch batch;
    SpriteBatch absoluteBatch;
    ArrayList<Bullet> bullets;
    Texture right, Button, Secondbutton;
    Texture restartButton, exitButton;
    Rectangle restartbuttonbox, exitbuttonbox;
    Rectangle rightbox, buttonbox, seconbuttonbox;

    int jumpCount;
    Sprite bg;
    Texture healthbar;
    Texture healthbar3;
    Texture healthbar2;
    Texture healthbar1;
    TextureRegion[] playerRun;
    TextureRegion playerFrame;

    Vector2 playerPosition;
    Vector2 platformPosition;
    Vector2 wallPosition;
    Vector2 gravity;
    Vector2 playerVelocity;
    Vector2 getX;
    Vector2 getY;
    Vector2 setLocation;
    float health;
    boolean dead;
    Sound shot;
    Music song;
    boolean atmenu;

    OrthographicCamera cam;
    Rectangle playerBounds;
    Texture platform;
    Rectangle platformBounds;
    Texture wall;
    Rectangle wallBounds;

    Boolean isrunning = false;
    float timer;
    int width;
    int height;
    float dt;
    float stateTime;
    float lastStateTime;
    boolean inAir;

    MapProperties prop;


    @Override
    public void create() {
        bulletBounds = new Rectangle();
        IDMATRIX = new Matrix4();

        shot = Gdx.audio.newSound(Gdx.files.internal("gunshot.wav"));
        song = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));

        atmenu = false;
        batch = new SpriteBatch();
        absoluteBatch = new SpriteBatch();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        gravity = new Vector2();
        inAir = false;
        wall = new Texture("gummybear.png");
        health = 3;
        dead = false;
        playerVelocity = new Vector2();
        playerPosition = new Vector2();
        platformPosition = new Vector2();
        wallPosition = new Vector2();

        bg = new Sprite(new Texture("sky.png"));
        bg.scale(2.5f);
        tileset = new Texture("tile-duke-example.png");
        tileset2 = new Texture("spike2.png");
        tileset3 = new Texture("map1.png");
        tileset4 = new Texture("tree1.png");
        tileset5 = new Texture("vines1.png");
        tileset6 = new Texture("floatingplatform.png");
        tileset7 = new Texture("New Piskel (2) copy.png");
        tileset8 = new Texture("New Piskel (3) copy.png");
        map = new TmxMapLoader().load("lyleiscool.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        GroundObject = map.getLayers().get("GroundObject");
        objects = GroundObject.getObjects();
        SpikesObjects = map.getLayers().get("SpikesObject");
        spikes = SpikesObjects.getObjects();
        prop = new MapProperties();
        prop = map.getProperties();

        mapWidth = prop.get("width", Integer.class);
        mapHeight = prop.get("height", Integer.class);
        tilePixelWidth = prop.get("tilewidth", Integer.class);
        tilePixelHeight = prop.get("tileheight", Integer.class);

        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;

        getX = new Vector2();
        getY = new Vector2();
        setLocation = new Vector2();
        youdead = new Texture("youdead.png");
        sky = new Texture("sky.png");
        right = new Texture("buttonright.png");
        Player = new Texture("koalaidle.png");
        platform = new Texture("platform.png");
        Button = new Texture("rsz_onebutton.png");
        Secondbutton = new Texture("rsz_twobutton_2.png");
        exitButton = new Texture("exitbutton.png");
        restartButton = new Texture("restartbutton.png");
        healthbar = new Texture("healthbar4.png");
        healthbar3 = new Texture("healthbar3.png");
        healthbar2 = new Texture("healthbar2.png");
        healthbar1 = new Texture("healthbar1.png");

        playerBounds = new Rectangle();
        platformBounds = new Rectangle();
        wallBounds = new Rectangle();
        rightbox = new Rectangle();
        rightbox.set(25, 425, right.getWidth(), right.getHeight());
        buttonbox = new Rectangle();
        buttonbox.set(800, 425, Button.getWidth(), Button.getHeight());
        exitbuttonbox = new Rectangle();
        exitbuttonbox.set(588, 125, exitButton.getWidth(), exitButton.getHeight());
        restartbuttonbox = new Rectangle();
        restartbuttonbox.set(200, 425, restartButton.getWidth(), restartButton.getHeight());
        seconbuttonbox = new Rectangle();
        seconbuttonbox.set(900, 425, Secondbutton.getWidth(), Secondbutton.getHeight());



        bullets = new ArrayList<Bullet>();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, width, height);

        StartScreen.create();

        koalared = new Texture[2];
        koalared[0] = new Texture("koalaidle.png");
        koalared[1] = new Texture("koalared.png");
        hurt = new Animation(.1f, new TextureRegion(koalared[0]), new TextureRegion(koalared[1]));
        hurt.setPlayMode(Animation.PlayMode.LOOP);

        playerRun = new TextureRegion[7];
        playerRun[0] = new TextureRegion(new Texture("koalarunning2.png"));
        playerRun[1] = new TextureRegion(new Texture("koalarunning3.png"));
        playerRun[2] = new TextureRegion(new Texture("koalarunning4.png"));
        playerRun[3] = new TextureRegion(new Texture("koalarunning5.png"));
        playerRun[4] = new TextureRegion(new Texture("koalarunning6.png"));
        playerRun[5] = new TextureRegion(new Texture("koalarunning7.png"));
        playerRun[6] = new TextureRegion(new Texture("koalaidle.png"));
        koala = new Animation(.1f, new TextureRegion(playerRun[0]), new TextureRegion(playerRun[1]), new TextureRegion(playerRun[2]), new TextureRegion(playerRun[3]), new TextureRegion(playerRun[4]), new TextureRegion(playerRun[5]));
        koala.setPlayMode(Animation.PlayMode.LOOP);
        stateTime = 0f;
        dt = 0f;
        resetGame();
    }

    private void resetGame() {
        PLAYER_SPEED = 5;
        playerPosition.set(300, 200);
        playerBounds.set(playerPosition.x, playerPosition.y, Player.getWidth(), Player.getHeight());
        platformPosition.set(0, 0);
        platformBounds.set(platformPosition.x, platformPosition.y, platform.getWidth(), platform.getHeight());
        wallBounds.set(wallPosition.x, wallPosition.y, wall.getWidth(), wall.getHeight());
        wallPosition.set(800, 150);
        playerVelocity.set(0, 0);
        gravity.set(0, -10);
        jumpCount = 0;
        health = 3;
        dead = false;
    }


    public void updategame() {
        dt = Gdx.graphics.getDeltaTime();

        if (StartScreen.atmenu) {
            StartScreen.updategame();
        } else {
            song.setVolume(.5f);
            song.play();
            song.isLooping();

            isrunning = false;
            float dt = Gdx.app.getGraphics().getDeltaTime();
            timer = timer - dt;

            for (Bullet bullet : bullets) {
                bullet.update();
            }


            if (true) {
                isrunning = true;
                playerPosition.x = playerPosition.x + PLAYER_SPEED;
                movingForward = false;
            }

            if (dead == true) {
                playerVelocity.x = 0;
            }

            for (int i = 0; i < 4; i++) {
                if (Gdx.input.isTouched(i) && jumpCount < 1 && playerPosition.x > 505) {
                    float X = Gdx.input.getX(i);
                    float Y = Gdx.input.getY(i);

                    jumpCount = jumpCount + 1;
                    inAir = true;
                    playerVelocity.y = 500;
                    gravity.set(0, -10);
                    playerVelocity.add(gravity);
                    playerPosition.mulAdd(playerVelocity, dt);
                    System.out.println("JUMP HAPPENED");


                    }
                }

                //checks collision with ground
            gravity.set(0, -10);
            jumpCount = 1;

            if(wallBounds.overlaps(playerBounds)){
                dead = true;
            }

            if(platformBounds.overlaps(playerBounds)){
                playerVelocity.y = 0;
                //playerPosition.y = rectangle.y + rectangle.getHeight() + 1;
                gravity.set(0, 0);
                jumpCount = 0;
                System.out.println("GRAVITY");
            }

//        checks PLAYER collision with ground

            for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
                Rectangle rectangle = rectangleObject.getRectangle();
                if (Intersector.overlaps(playerBounds, rectangle)) {
                    playerVelocity.y = 0;
                    playerPosition.y = rectangle.y + rectangle.getHeight() + 1;
                    gravity.set(0, 0);
                    jumpCount = 0;
                    System.out.println("GRAVITY");
                }
            }


            playerVelocity.add(gravity);
            playerPosition.mulAdd(playerVelocity, dt);
            playerBounds.setX(playerPosition.x);
            playerBounds.setY(playerPosition.y);
            platformBounds.setX(platformPosition.x);
            platformBounds.setY(platformPosition.y);
            wallBounds.setX(wallPosition.x);
            wallBounds.setY(wallPosition.y);

            if (playerPosition.y < 0) {
                dead = true;
            }
        }
    }



    public void drawGame() {

        cam.position.set(playerPosition.x, playerPosition.y, 0);
        cam.update();
        mapRenderer.setView(cam);
        mapRenderer.render();
        batch.setProjectionMatrix(cam.combined);


        absoluteBatch.begin();
        absoluteBatch.draw(bg, 0, 0);
        absoluteBatch.end();

        batch.begin();
        //batch.draw(wall, 800, 150);
        //batch.draw(platform, 0, 0);
        batch.end();


        mapRenderer.setView(cam);
        mapRenderer.render();

        if (StartScreen.atmenu) {

            batch.begin();
            StartScreen.render();
            batch.end();
        } else {
            batch.begin();
            if (isrunning) {
                playerFrame = koala.getKeyFrame(stateTime);
            } else {
                playerFrame = playerRun[6];
            }

            batch.draw(playerFrame, playerPosition.x, playerPosition.y);
            batch.end();

            if (dead == true) {
                absoluteBatch.begin();
                absoluteBatch.draw(youdead, 100, 100);
                absoluteBatch.draw(exitButton, 588, 125);
                absoluteBatch.draw(restartButton, 170, 126);
                absoluteBatch.end();
                playerVelocity.x = 0;
                isrunning = false;
                movingForward = false;
                PLAYER_SPEED = 0;
                jumpCount = 1;
                StartScreen.updategame();
                //FUCK


                if (Gdx.input.isTouched()) {
                    long x = Gdx.input.getX();
                    long y = Gdx.graphics.getHeight() - Gdx.input.getY();
                    if (restartbuttonbox.contains(x, y)) {
                        resetGame();
                    }

                    if (exitbuttonbox.contains(x, y)) {
                        StartScreen.atmenu = true;
                        dead = false;
                    }
                }
            }
        }
    }


    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        lastStateTime = stateTime;
        stateTime += Gdx.graphics.getDeltaTime();

        updategame();
        drawGame();

        }
    }