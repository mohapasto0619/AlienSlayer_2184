package fr.mastersid.meghasli.alienslayer2184;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class GameView extends SurfaceView implements Runnable {
    Context context;

    private Thread gamethread;
    private SurfaceHolder ourHolder;
    private volatile boolean playing;
    private boolean paused = true;
    boolean reverse =false;
    boolean bumped = false;
    boolean bumpedRight = false;
    boolean bumpedLeft = false;
    boolean start = true;
    Sound sound;
    boolean soundState = true;
    int skinID = 1;
    //
    private Canvas canvas;
    private Paint paint;
    private long fps;
    private long timeThisFrame;
    private int screenX;
    private int screenY;
    //
    private Background background;
    //
    private PlayerShip playerShip;
    private Missile missile;
    //
    Defense[] defense = new Defense[400];
    private int numDefense;
    //
    Invader[] invaders = new Invader[15];
    int numInvaders = 0;
    private Missile[] invadersMissile = new Missile[1000];
    private int nextMissile;
    private int maxInvadersMissile = 100;
    //
    private String playerName = "No name";
    int score = 0;
    private int lives = 3;
    volatile MutableLiveData<Boolean> isItGameOver;
    int numberTotInvaders=0;

    private Bitmap bitmapInv1;
    private Bitmap bitmapInv2;
    private Bitmap bitmapInv3;
    private Bitmap bitmapInv4;
    private Bitmap[] bitmapEx;
    float invLength;
    float invHeight;
    boolean isThereExplosion = false;


    public GameView(Context context, int x, int y) {
        super(context);
        this.context = context;
        ourHolder = getHolder(); //!
        paint = new Paint();
        sound = new Sound(context);
        screenX = x;
        screenY = y;
        invLength = screenX/6;
        invHeight = screenY/10;

        bitmapInv1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4dr);
        bitmapInv2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4dl);
        bitmapInv3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4ul);
        bitmapInv4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4ud);
        bitmapEx = new Bitmap[10];

        bitmapEx[0]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile0);
        bitmapEx[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile1);
        bitmapEx[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile2);
        bitmapEx[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile3);
        bitmapEx[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile4);
        bitmapEx[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile5);
        bitmapEx[6]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile6);
        bitmapEx[7]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile7);
        bitmapEx[8]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile8);
        bitmapEx[9]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile9);

        bitmapInv1 =  Bitmap.createScaledBitmap(bitmapInv1, (int)(invLength), (int)(invHeight), false);
        bitmapInv2 =  Bitmap.createScaledBitmap(bitmapInv2, (int)(invLength), (int)(invHeight), false);
        bitmapInv3 =  Bitmap.createScaledBitmap(bitmapInv3, (int)(invLength), (int)(invHeight), false);
        bitmapInv4 =  Bitmap.createScaledBitmap(bitmapInv4, (int)(invLength), (int)(invHeight), false);

        for (int i =0; i<bitmapEx.length; i++){
            bitmapEx[i] =  Bitmap.createScaledBitmap(bitmapEx[i], (int)(invLength), (int)(invHeight), false);
        }
        //paused =false;
        initLevel();
        // impro

    }

    private void initLevel() {
        background = new Background(context,screenX,screenY);
        playerShip = new PlayerShip(context, screenX, screenY);
        missile = new Missile(screenY);

        for (int i=0; i<invadersMissile.length; i++){
            invadersMissile[i] = new Missile(screenY);
        }

        numInvaders = 0;
        for(int column = 0; column < 3; column ++ ){
            for(int row = 0; row < 3; row ++ ){
                invaders[numInvaders] = new Invader(context, row, column, screenX, screenY);
                invaders[numInvaders].setBitmap(bitmapInv1,bitmapInv2,bitmapInv3,bitmapInv4,bitmapEx);
                numInvaders ++;
            }
        }
        numberTotInvaders = numberTotInvaders + numInvaders;

        numDefense = 0;
        for(int shelterNumber = 0; shelterNumber < 3; shelterNumber++){
            for(int column = 0; column < 10; column ++ ) {
                for (int row = 0; row < 5; row++) {
                    defense[numDefense] = new Defense(row, column, shelterNumber, screenX, screenY);
                    numDefense++;
                }
            }
        }

    }

    public void initNextLevel(){
        for (int i=0; i<invadersMissile.length; i++){
            invadersMissile[i] = new Missile(screenY);
        }

        numInvaders = 0;
        for(int column = 0; column < 3; column ++ ){
            for(int row = 0; row < 5; row ++ ){
                invaders[numInvaders] = new Invader(context, row, column, screenX, screenY);
                invaders[numInvaders].setBitmap(bitmapInv1,bitmapInv2,bitmapInv3,bitmapInv4,bitmapEx);
                numInvaders ++;
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        while (playing == true) {
            long startFrameTime = System.currentTimeMillis();

            if (paused == false) {
                update();
                isThereExplosion= checkExplode();
            }

            draw();
            checkInvaders();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void update() {


        //boolean lost = false;
        background.update(fps);
        playerShip.update(fps);

        for(int i = 0; i < numInvaders; i++){

            if(invaders[i].getVisibility()) {
                // Move the next invader
                invaders[i].update(fps);

                // Does he want to take a shot
                if(invaders[i].takeAim(playerShip.getX(),
                        playerShip.getLength())){

                    // If yes try and spawn a bullet  //improoooo
                    if(invadersMissile[nextMissile].shoot(invaders[i].getX()
                            + invaders[i].getLength() / 2, invaders[i].getY(), missile.DOWN) ||
                            invadersMissile[nextMissile + 1].shoot(invaders[i].getX()
                                    + invaders[i].getLength() / 2, invaders[i].getY(), missile.DOWN) ||
                            invadersMissile[nextMissile + 2].shoot(invaders[i].getX() + invaders[i].getLength() / 2,
                                    invaders[i].getY(), missile.DOWN)) {

                        // Shot fired
                        // Prepare for the next shot
                        sound.playLazer2Sound();
                        nextMissile = nextMissile + 3;


                        if (nextMissile == maxInvadersMissile) {
                            nextMissile = 0;
                        }
                    }
                }


                /*if (invaders[i].getX() > screenX - (invaders[i].getLength()  )
                        || invaders[i].getX() < 0  ){

                    bumped = true;
                }*/

                if (invaders[i].getX() > screenX - (invaders[i].getLength())){
                    bumpedRight = true;
                }
                else if(invaders[i].getX() < 0){
                    bumpedLeft = true;
                }
            }

        }

        for (int i=0; i < invadersMissile.length; i++){
            if(invadersMissile[i].getStatus()){
                invadersMissile[i].update(fps);
            }
        }

        if(bumpedRight){
             // impro
                for (int i = 0; i < numInvaders; i++) {
                    invaders[i].moveLeft();
                    //invaders[i].goDown();
                    // Have the invaders landed
                }
            bumpedRight =false;
        }

        if(bumpedLeft){
             // impro
                for (int i = 0; i < numInvaders; i++) {
                    invaders[i].moveRight();
                    //invaders[i].goDown();
                    // Have the invaders landed
                }
            bumpedLeft = false;
        }

        if (!reverse) { // impro
            for (int i = 0; i < numInvaders; i++) {
                //invaders[i].goDown();
                invaders[i].moveDown();
                // Have the invaders landed
                if (invaders[i].getY() > screenY - screenY / 2) {
                    //lost = true;
                    reverse = true;
                }

            }
        }
        else{
            for (int i = 0; i < numInvaders; i++) {
                //invaders[i].goUp();
                invaders[i].moveUp();
                // Have the invaders landed
                if (invaders[i].getY() <= 0 ) { //impro
                    //lost = true;
                    reverse = false;
                }
            }
        }

        /*if(bumped) {

            // Move all the invaders down and change direction
            if (!reverse) { // impro
                for (int i = 0; i < numInvaders; i++) {
                    invaders[i].dropDownAndReverse();
                    // Have the invaders landed
                    if (invaders[i].getY() > screenY - screenY / 2) {
                        //lost = true;
                        reverse = true;
                    }
                }

            } else { // impro
                for (int i = 0; i < numInvaders; i++) {
                    invaders[i].dropUPAndReverse();
                    // Have the invaders landed
                    if (invaders[i].getY() <= 0) { //impro
                        //lost = true;
                        reverse = false;
                    }
                }

            }
            bumped = false;
        }*/

        /*if (lost == true) {
            initLevel();
        }*/

        if(missile.getStatus() == true){
            missile.update(fps);
        }

        // Has the player's bullet hit the top of the screen
        if(missile.getImpactPointY() < 0){
            missile.setInactive();
        }

        for(int i = 0; i < invadersMissile.length; i++){
            if(invadersMissile[i].getImpactPointY() > screenY){
                invadersMissile[i].setInactive();
            }
        }

        if(missile.getStatus()) {
            for (int i = 0; i < numInvaders; i++) {
                if (invaders[i].getVisibility()) {
                    if (RectF.intersects(missile.getRect(), invaders[i].getRect())) {
                        //invaders[i].setInvisible();
                        invaders[i].setExplode(true);
                        missile.setInactive();
                        score = score + 10;
                        sound.playExplosion();

                        // Has the player win
                        /*if(score == numInvaders * 10){
                            if(paused == false){
                                paused = true;
                                initNextLevel();
                            }
                            else if(paused == true){
                                score = 0;
                                paused = false;
                            }
                            //initLevel();
                        }*/
                    }
                }
            }
        }


        for(int i = 0; i < invadersMissile.length; i++){
            if(invadersMissile[i].getStatus()){
                for(int j = 0; j < numDefense; j++){
                    if(defense[j].getVisibility()){
                        if(RectF.intersects(invadersMissile[i].getRect(), defense[j].getRect())){
                            //collision
                            invadersMissile[i].setInactive();
                            defense[j].setInvisible();
                        }
                    }
                }
            }

        }

        if(missile.getStatus()){
            for(int i = 0; i < numDefense; i++){
                if(defense[i].getVisibility()){
                    if(RectF.intersects(missile.getRect(), defense[i].getRect())){
                        //collision
                        missile.setInactive();
                        defense[i].setInvisible();
                    }
                }
            }
        }

        for(int i = 0; i < invadersMissile.length; i++){
            if(invadersMissile[i].getStatus()){
                if(RectF.intersects(playerShip.getRect(), invadersMissile[i].getRect())){
                    invadersMissile[i].setInactive();
                    lives --;

                    // Is it game over
                    if(lives == 0){
                        paused = true;
                        isItGameOver.postValue(true);
                        //lives = 3;
                        //score = 0;
                        //initLevel();

                       // NavDirections action = NavGraphDirections.actionSoloFragmentToSoloGameOverFragment();
                       // Navigation.findNavController(this).navigate(action);


                    }
                }
            }
        }




    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void draw() {
        if (ourHolder.getSurface().isValid()){
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 66, 90, 200));
            paint.setColor(Color.argb(255, 255, 255, 255));


            paint.setTextSize(40);
            paint.setTypeface(Typeface.create(context.getResources().getFont(R.font.lazer84),Typeface.NORMAL));
            canvas.drawBitmap(background.getBitmap(),background.getX(),background.getY(), paint);


            canvas.drawBitmap(playerShip.getBitmap(),playerShip.getX(),screenY - 250, paint);

            for(int i = 0; i < numInvaders; i++){
                if(invaders[i].getVisibility() || invaders[i].getExplode() ) {
                    canvas.drawBitmap(invaders[i].getBitmap(), invaders[i].getX(), invaders[i].getY(), paint);
                }
                /*if(invaders[i].getExplode()){
                    canvas.drawBitmap(invaders[i].getBitmap(), invaders[i].getX(), invaders[i].getY(), paint);
                }*/
            }

            paint.setColor(Color.argb(255, 198, 0, 0));
            if(missile.getStatus() == true){
                canvas.drawRect(missile.getRect(),paint);
            }

            for(int i=0; i<invadersMissile.length; i++){
                if(invadersMissile[i].getStatus()){
                    canvas.drawRect(invadersMissile[i].getRect(),paint);
                }
            }

            paint.setColor(Color.argb(255, 246, 1, 157));
            canvas.drawText("Score :   "+ score+"     Vie :    "+ lives+ "                  Nom Joueur :   "
                    + playerName,10,130,paint);

            paint.setColor(Color.argb(255, 255, 215, 0));
            // Draw the bricks if visible
            for(int i = 0; i < numDefense; i++){
                if(defense[i].getVisibility()) {
                    canvas.drawRect(defense[i].getRect(), paint);
                }
            }
            ourHolder.unlockCanvasAndPost(canvas);
            if(start){ /////////impro
                paused = false;
                start = false;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void draw2() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            paint.setColor(Color.argb(255, 246, 1, 157));
            paint.setTextSize(40);
            paint.setTypeface(Typeface.create(context.getResources().getFont(R.font.lazer84), Typeface.NORMAL));
            canvas.drawText("NEXT VAGUE",100,300,paint);
            ourHolder.unlockCanvasAndPost(canvas);
        }

    }

    public void pause(){
        playing = false;
        try{
            gamethread.join();
        }
        catch(InterruptedException e){
            Log.e("Error :", "joining thread");
        }
    }

    public void resume() {
        playing = true;
        //paused = false;
        gamethread = new Thread(this);
        gamethread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                paused = false;
                if(motionEvent.getY() > screenY - screenY /3){
                    if (motionEvent.getX() > screenX / 2){
                        playerShip.setMovementState(playerShip.RIGHT);
                    }
                    else {
                        playerShip.setMovementState(playerShip.LEFT);
                    }
                }

                if(motionEvent.getY() < screenY - screenY /3){
                    if(missile.shoot(playerShip.getX()+playerShip.getLength()/2,screenY - 500,missile.UP)){
                        if(soundState){
                            sound.playLazerSound();
                        }

                    }
                }

                break;

            case MotionEvent.ACTION_UP:
                playerShip.setMovementState(playerShip.STOPPED);
                break;
        }
        return true;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }

    public void setSoundState(boolean state){
        this.soundState = state;
    }

    public void setSkinID(int id){
        playerShip.setPlayerSkin(id);
    }

    public void setGameOverState(MutableLiveData<Boolean> isItGameOver){
        this.isItGameOver = isItGameOver;
    }
    public int getScore(){
        return this.score;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void checkInvaders(){
        if(score ==  numberTotInvaders * 10 && !isThereExplosion){
            if(paused == false){
                paused = true;
                //draw2();
                initNextLevel();

            }
            else if(paused == true){

                numberTotInvaders = numberTotInvaders + numInvaders;
                paused = false;
            }

            //initLevel();
        }
    }

    public boolean checkExplode(){
        boolean state = false;
        for (int i = 0; i<numInvaders; i++){
            if(invaders[i].getExplode()){
                state = true;
                break;
            }

        }
        return state;
    }

}

