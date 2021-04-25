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

    public Invader(Context context, int row, int column, int screenX, int screenY){
        rect = new RectF();

        length = screenX/6;
        height = screenY/10;

        isVisible = true;
        explode = false;

        int padding = screenX/8;

        x = column *length + padding;
        y = (row *length - screenY/2) + (padding/4);

        /*bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4dr);
        bitmap1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4dr);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4dl);
        bitmap3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4ul);
        bitmap4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4ud);*/
        bitmapEx = new Bitmap[10];

        /*bitmapEx[0]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile0);
        bitmapEx[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile1);
        bitmapEx[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile2);
        bitmapEx[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile3);
        bitmapEx[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile4);
        bitmapEx[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile5);
        bitmapEx[6]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile6);
        bitmapEx[7]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile7);
        bitmapEx[8]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile8);
        bitmapEx[9]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile9);*/

        /*bitmap =  Bitmap.createScaledBitmap(bitmap, (int)(length), (int)(height), false);
        bitmap1 =  Bitmap.createScaledBitmap(bitmap1, (int)(length), (int)(height), false);
        bitmap2 =  Bitmap.createScaledBitmap(bitmap2, (int)(length), (int)(height), false);
        bitmap3 =  Bitmap.createScaledBitmap(bitmap3, (int)(length), (int)(height), false);
        bitmap4 =  Bitmap.createScaledBitmap(bitmap4, (int)(length), (int)(height), false);


        bitmapEx[0] =  Bitmap.createScaledBitmap(bitmapEx[0], (int)(length), (int)(height), false);
        bitmapEx[1] = Bitmap.createScaledBitmap(bitmapEx[1], (int)(length), (int)(height), false);
        bitmapEx[2] = Bitmap.createScaledBitmap(bitmapEx[2], (int)(length), (int)(height), false);
        bitmapEx[3] = Bitmap.createScaledBitmap(bitmapEx[3], (int)(length), (int)(height), false);
        bitmapEx[4] = Bitmap.createScaledBitmap(bitmapEx[4], (int)(length), (int)(height), false);
        bitmapEx[5] =  Bitmap.createScaledBitmap(bitmapEx[5], (int)(length), (int)(height), false);
        bitmapEx[6] = Bitmap.createScaledBitmap(bitmapEx[6], (int)(length), (int)(height), false);
        bitmapEx[7] = Bitmap.createScaledBitmap(bitmapEx[7], (int)(length), (int)(height), false);
        bitmapEx[8] = Bitmap.createScaledBitmap(bitmapEx[8], (int)(length), (int)(height), false);
        bitmapEx[9] = Bitmap.createScaledBitmap(bitmapEx[9], (int)(length), (int)(height), false);*/

        shipSpeed = 120;
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
            randomNumber = generator.nextInt(500);
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

