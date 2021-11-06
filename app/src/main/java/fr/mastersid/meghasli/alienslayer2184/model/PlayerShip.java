package fr.mastersid.meghasli.alienslayer2184.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import fr.mastersid.meghasli.alienslayer2184.R;

public class PlayerShip {

    RectF rect;
    Context context;
    private float length;
    private float height;
    private int screenY;
    private float x;
    private float y;
    private float shipSpeed;
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT =2;
    private volatile int shipMoving = STOPPED;
    boolean isVisible;
    boolean explode;
    int explodeId = 0;


    private Bitmap bitmap;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap[] bitmapEx;


    public PlayerShip(Context context, int screenX, int screenY){
        rect = new RectF();
        this.context = context;
        isVisible = true;
        this.screenY = screenY;
        length = screenX/5;
        height = screenY/7;
        x = screenX/2;
        y = 0;

        shipSpeed = 350;
        bitmapEx = new Bitmap[64];

    }

    public RectF getRect(){
        return rect;
    }

    public Bitmap getBitmap(){
        return bitmap;
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

    public void setMovementState(int state){
        shipMoving = state;
    }

    public boolean getVisibility(){
        return isVisible;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public boolean getExplode(){
        return explode;
    }

    public void setExplode(boolean state){
        explode = state;
    }

    public void update(long fps){
        if(shipMoving == LEFT){
            x = x - (shipSpeed/fps);
            bitmap = bitmap3;
        }
        if(shipMoving == RIGHT){
            x = x + (shipSpeed/fps);
            bitmap = bitmap2;
        }
        if(shipMoving == STOPPED){
            bitmap = bitmap1;
        }

        if(explode){
            if (explodeId <bitmapEx.length){
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

    public void setEx(Bitmap[] bitmapEx){
        this.bitmapEx = bitmapEx;
    }

    public void setPlayerSkin(int id){
        if(id ==1){
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playership2);
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)(length),(int)(height),false);
            bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership2);
            bitmap1 = Bitmap.createScaledBitmap(bitmap1,(int)(length),(int)(height),false);
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership2r);
            bitmap2 = Bitmap.createScaledBitmap(bitmap2,(int)(length),(int)(height),false);
            bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership2l);
            bitmap3 = Bitmap.createScaledBitmap(bitmap3,(int)(length),(int)(height),false);
        }
        else if(id == 2){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership1);
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)(length),(int)(height),false);
            bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership1);
            bitmap1 = Bitmap.createScaledBitmap(bitmap1,(int)(length),(int)(height),false);
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership1r);
            bitmap2 = Bitmap.createScaledBitmap(bitmap2,(int)(length),(int)(height),false);
            bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership1l);
            bitmap3 = Bitmap.createScaledBitmap(bitmap3,(int)(length),(int)(height),false);
        }

        else if(id == 3){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership3);
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)(length),(int)(height),false);
            bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership3);
            bitmap1 = Bitmap.createScaledBitmap(bitmap1,(int)(length),(int)(height),false);
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership3r);
            bitmap2 = Bitmap.createScaledBitmap(bitmap2,(int)(length),(int)(height),false);
            bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership3l);
            bitmap3 = Bitmap.createScaledBitmap(bitmap3,(int)(length),(int)(height),false);
        }

        else if(id == 4){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership4);
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)(length),(int)(height),false);
            bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership4);
            bitmap1 = Bitmap.createScaledBitmap(bitmap1,(int)(length),(int)(height),false);
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership4r);
            bitmap2 = Bitmap.createScaledBitmap(bitmap2,(int)(length),(int)(height),false);
            bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership4l);
            bitmap3 = Bitmap.createScaledBitmap(bitmap3,(int)(length),(int)(height),false);
        }

        else if(id == 5){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership6);
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)(length),(int)(height),false);
            bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership6);
            bitmap1 = Bitmap.createScaledBitmap(bitmap1,(int)(length),(int)(height),false);
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership6r);
            bitmap2 = Bitmap.createScaledBitmap(bitmap2,(int)(length),(int)(height),false);
            bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership6l);
            bitmap3 = Bitmap.createScaledBitmap(bitmap3,(int)(length),(int)(height),false);
        }

        else if(id == 6){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.xwings_n);
            bitmap = Bitmap.createScaledBitmap(bitmap,(int)(length*0.7),(int)(height*0.6),false);
            bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.xwings_n);
            bitmap1 = Bitmap.createScaledBitmap(bitmap1,(int)(length*0.7),(int)(height*0.6),false);
            bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.xwings_r);
            bitmap2 = Bitmap.createScaledBitmap(bitmap2,(int)(length*0.7),(int)(height*0.6),false);
            bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.xwings_l);
            bitmap3 = Bitmap.createScaledBitmap(bitmap3,(int)(length*0.7),(int)(height*0.6),false);
        }

        y = screenY - bitmap.getHeight();
    }
}
