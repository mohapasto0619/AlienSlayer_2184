package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import java.util.Random;

public class Invader {
    RectF rect;

    Random generator = new Random();

    private Bitmap bitmap;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private Bitmap[] bitmapEx;

    private float length;
    private float height;

    private float x;
    private float y;

    private float shipSpeed;

    public final int LEFT = 1;
    public final int RIGHT =2;
    public final int UP = 3;
    public final int DOWN = 4;

    private int shipMoving = RIGHT;
    private int shipMoving2 = DOWN;

    boolean isVisible;
    boolean explode;
    int explodeId = 0;
    int difficulty = 500;

    public Invader(Context context, int row, int column, int screenX, int screenY, int difficulty){
        rect = new RectF();

        length = screenX/6;
        height = screenY/10;

        isVisible = true;
        explode = false;

        int padding = screenX/8;

        x = column *length + padding;
        y = (row *length - screenY/2) + (padding/4);

        bitmapEx = new Bitmap[10];
        shipSpeed = 160;
        this.difficulty = difficulty;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public boolean getVisibility(){
        return isVisible;
    }

    public RectF getRect(){
        return rect;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public Bitmap getBitmap1(){
        return bitmap1;
    }

    public Bitmap getBitmap2(){
        return bitmap2;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getLength(){
        return length;
    }
    public float getHeight(){ return height;}

    public boolean getExplode(){
        return explode;
    }

    public void setExplode(boolean state){
        explode = state;
    }

    public void update (long fps){
        if(shipMoving == LEFT){
            x = x - shipSpeed/fps;

        }

        if(shipMoving == RIGHT){
            x = x + shipSpeed/fps;

        }

        if (shipMoving2 == DOWN){
            y = y + shipSpeed/fps;
        }

        if(shipMoving2 == UP){
            y = y - shipSpeed/fps;
        }

        if(shipMoving == RIGHT && shipMoving2 == DOWN){
            bitmap = bitmap1;
        }
        else if(shipMoving == LEFT && shipMoving2 == DOWN){
            bitmap = bitmap2;
        }
        else if(shipMoving == LEFT && shipMoving2 == UP){
            bitmap = bitmap3;
        }
        else if(shipMoving == RIGHT && shipMoving2 == UP){
            bitmap = bitmap4;
        }

        if(explode){
            if (explodeId <10){
            bitmap = bitmapEx[explodeId];
            explodeId++;
            }
            else{
                explode = false;
                setInvisible();
            }
        }



        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;
    }


    public void moveLeft(){
        shipMoving = LEFT;
    }

    public void moveRight(){
        shipMoving = RIGHT;
    }

    public void moveUp(){
        shipMoving2 = UP;
    }

    public void moveDown(){
        shipMoving2 = DOWN;
    }

    public void exploding(){
        if (explode){

        }
    }

    public void setBitmap(Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap[] bitmapEx){
        this.bitmap = bitmap1;
        this.bitmap1 = bitmap1;
        this.bitmap2 = bitmap2;
        this.bitmap3 = bitmap3;
        this.bitmap4 = bitmap4;
        this.bitmapEx = bitmapEx;
    }

    public void goDown(){
        y = y + height;
    }
    public void goUp(){
        y = y - height;
    }

    public boolean takeAim(float playerShipX,float playerShipLength){
        int randomNumber = -1;
        if ((playerShipX + playerShipLength > x && playerShipX + playerShipLength < x) ||
                (playerShipX > x && playerShipX < x + length)){
            randomNumber = generator.nextInt(difficulty);
            if(randomNumber == 0){
                return true;
            }

        }
        randomNumber = generator.nextInt(2000);
        if(randomNumber == 0){
            return true;
        }
        return false;
    }
}

