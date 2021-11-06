package fr.mastersid.meghasli.alienslayer2184.model;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class Missile {
    private float x;
    private float y;
    private RectF rect;
    public final int UP = 0;
    public final int DOWN = 1;
    int heading = -1;
    float speed = 350;
    private int width = 10;
    private int height;
    private boolean isActive;
    private boolean explode ;
    int explodeId = 0;

    Bitmap bitmap;
    private Bitmap[] bitmapEx;

    public Missile(int screenY){
        height = screenY/30;
        isActive = false;
        explode = false;

        rect = new RectF();

        bitmapEx = new Bitmap[64];
    }

    public RectF getRect(){
        return rect;
    }

    public boolean getStatus(){
        return isActive;
    }

    public void setInactive(){
        isActive = false;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getImpactPointY(){
        if (heading == DOWN){
            return y + height;
        }
        else{
            return y;
        }
    }

    public boolean shoot(float startX, float startY, int direction){
        if(isActive == false){
            x = startX;
            y = startY;
            heading = direction;
            isActive = true;
            return true;
        }
        return false;
    }

    public void setExplode(boolean state){
        explode = state;
    }

    public boolean getExplode(){
        return explode;
    }

    public void setBitmap(Bitmap[] bitmapEx){
        this.bitmapEx = bitmapEx;
        this.bitmap = bitmapEx[0];
    }

    public Bitmap getBitmap(){
        return this.bitmap;
    }

    public void update(long fps){

        if(heading == UP){
            y = y - speed/fps;
        }
        else{
            y = y + speed/fps;
        }

        if(explode){
            if (explodeId <10){
                bitmap = bitmapEx[explodeId];
                explodeId++;
            }
            else{
                explode = false;
                setInactive();
            }
        }


        rect.left = x;
        rect.right = x + width;
        rect.top = y;
        rect.bottom = y + height;
    }
}

