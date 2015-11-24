package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

    public class Bullet {
        private boolean active;
        public Vector2 position;
        public Rectangle bounds;
        private int speed;
        private int direction;
        public Texture BulletImage;


        Bullet(float x, float y, int Direction, int setSpeed) {
            active = false;
            position = new Vector2(x,y);
            bounds = new Rectangle();
            bounds.set(x, y, 10, 10);
            speed = setSpeed;
            direction = Direction;
            BulletImage = new Texture("bullet.png");

        }
        public void update() {
            position.x += speed * direction;
            bounds.set(position.x, position.y, BulletImage.getWidth(), BulletImage.getHeight());
            active = true;

        }
        public void FlipUpdte(){
            position.x -= speed * direction;
            bounds.set(position.x, position.y, BulletImage.getWidth(), BulletImage.getHeight());
            active = true;
        }

        public static void setXY(){

        }


    }



