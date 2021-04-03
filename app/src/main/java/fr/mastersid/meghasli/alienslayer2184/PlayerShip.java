package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class PlayerShip {
    RectF rect;
    Context context;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;

    private float length;
    private float height;

    private float x;
    private float y;

    private float shipSpeed;

    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT =2;

    private volatile int shipMoving = STOPPED;

    public PlayerShip(Context context, int screenX, int screenY){
        rect = new RectF();
        this.context = context;
        length = screenX/5;
        height = screenY/7;
        x = screenX/2;
        y = screenY - 200;

        shipSpeed = 350;
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

    public float getLength(){
        return length;
    }

    public void setMovementState(int state){
        shipMoving = state;
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

        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;
    }

    public void setPlayerSkin(int id){
        if(id ==1){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.playership2);
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


    }
}
