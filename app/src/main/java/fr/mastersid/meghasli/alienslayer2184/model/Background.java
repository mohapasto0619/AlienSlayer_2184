package fr.mastersid.meghasli.alienslayer2184.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import fr.mastersid.meghasli.alienslayer2184.R;

public class Background {
    private Bitmap bitmap;
    private float length;
    private float height;
    private float newLength;
    private float newHeight;
    private float screenY;
    private float ratio;
    private float speed;
    private float x = 0;
    private float y = 0;

    public Background(Context context, int screenX, int screenY){

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.starfield);
        height = bitmap.getHeight();
        length = bitmap.getWidth();
        this.screenY = screenY;
        ratio = height/length;
        newLength = screenX;
        newHeight = (int)(ratio * screenX);
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)(newLength),(int)(newHeight),false);
        speed = 160;
        y = -newHeight + screenY + screenY/3 ;

    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public void update(long fps){
        y = y + (speed/fps);
        reset();
    }
    public void reset(){
        if(y >= 0){
            y = -newHeight + (screenY *2) ;

        }
    }


}
