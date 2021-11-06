package fr.mastersid.meghasli.alienslayer2184.ui;

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

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import fr.mastersid.meghasli.alienslayer2184.model.Invader;
import fr.mastersid.meghasli.alienslayer2184.model.Missile;
import fr.mastersid.meghasli.alienslayer2184.model.PlayerShip;
import fr.mastersid.meghasli.alienslayer2184.R;
import fr.mastersid.meghasli.alienslayer2184.sound.Sound;
import fr.mastersid.meghasli.alienslayer2184.model.Background;
import fr.mastersid.meghasli.alienslayer2184.model.Defense;

public class GameView extends SurfaceView implements Runnable {

    Context context;
    private Thread gamethread;
    private SurfaceHolder ourHolder;
    private Canvas canvas;
    private Paint paint;
    private volatile boolean playing;
    private boolean paused = true;
    private long fps;
    private long timeThisFrame;

    int score = 0;
    private int lives = 3;
    int level = 1;
    private String playerName = "No name";
    boolean reverse =false;
    boolean bumped = false;
    boolean bumpedRight = false;
    boolean bumpedLeft = false;
    boolean start = true;
    Sound sound;
    boolean soundState = true;
    int skinID = 1;
    private int screenX;
    private int screenY;
    private Background background;
    private PlayerShip playerShip;
    private Missile missile;
    private Missile missile2;
    private Missile missile3;
    Defense[] defense = new Defense[400];
    private int numDefense;
    Invader[] invaders = new Invader[15];
    int numInvaders = 0;
    private Missile[] invadersMissile = new Missile[1000];
    private int nextMissile;
    private int maxInvadersMissile = 100;

    volatile MutableLiveData<Boolean> isItGameOver;
    int numberTotInvaders=0;
    private Bitmap bitmapInv1;
    private Bitmap bitmapInv2;
    private Bitmap bitmapInv3;
    private Bitmap bitmapInv4;
    private Bitmap bitmapInv5;
    private Bitmap bitmapInv6;
    private Bitmap bitmapInv7;
    private Bitmap bitmapInv8;
    private Bitmap bitmapInv9;
    private Bitmap bitmapInv10;
    private Bitmap bitmapInv11;
    private Bitmap bitmapInv12;
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

        bitmapInv1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader2dr);
        bitmapInv2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader2dl);
        bitmapInv3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader2ul);
        bitmapInv4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader2ur);
        bitmapInv5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4dr);
        bitmapInv6 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4dl);
        bitmapInv7 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4ul);
        bitmapInv8 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader4ud);
        bitmapInv9 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader3dr);
        bitmapInv10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader3dl);
        bitmapInv11 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader3ul);
        bitmapInv12 = BitmapFactory.decodeResource(context.getResources(),R.drawable.invader3ur);
        bitmapEx = new Bitmap[64];

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
        bitmapEx[10]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile10);
        bitmapEx[11]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile011);
        bitmapEx[12]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile012);
        bitmapEx[13]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile013);
        bitmapEx[14]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile014);
        bitmapEx[15]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile015);
        bitmapEx[16]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile016);
        bitmapEx[17]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile017);
        bitmapEx[18]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile018);
        bitmapEx[19]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile019);
        bitmapEx[20]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile020);
        bitmapEx[21]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile021);
        bitmapEx[22]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile022);
        bitmapEx[23]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile023);
        bitmapEx[24]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile024);
        bitmapEx[25]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile025);
        bitmapEx[26]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile026);
        bitmapEx[27]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile027);
        bitmapEx[28]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile028);
        bitmapEx[29]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile029);
        bitmapEx[30]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile030);
        bitmapEx[31]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile031);
        bitmapEx[32]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile032);
        bitmapEx[33]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile033);
        bitmapEx[34]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile034);
        bitmapEx[35]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile035);
        bitmapEx[36]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile036);
        bitmapEx[37]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile037);
        bitmapEx[38]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile038);
        bitmapEx[39]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile039);
        bitmapEx[40]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile040);
        bitmapEx[41]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile041);
        bitmapEx[42]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile042);
        bitmapEx[43]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile043);
        bitmapEx[44]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile044);
        bitmapEx[45]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile045);
        bitmapEx[46]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile046);
        bitmapEx[47]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile047);
        bitmapEx[48]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile048);
        bitmapEx[49]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile049);
        bitmapEx[50]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile050);
        bitmapEx[51]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile051);
        bitmapEx[52]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile052);
        bitmapEx[53]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile053);
        bitmapEx[54]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile054);
        bitmapEx[55]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile055);
        bitmapEx[56]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile056);
        bitmapEx[57]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile057);
        bitmapEx[58]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile058);
        bitmapEx[59]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile059);
        bitmapEx[60]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile060);
        bitmapEx[61]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile061);
        bitmapEx[62]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile062);
        bitmapEx[63]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tile063);


        bitmapInv1 =  Bitmap.createScaledBitmap(bitmapInv1, (int)(invLength), (int)(invHeight), false);
        bitmapInv2 =  Bitmap.createScaledBitmap(bitmapInv2, (int)(invLength), (int)(invHeight), false);
        bitmapInv3 =  Bitmap.createScaledBitmap(bitmapInv3, (int)(invLength), (int)(invHeight), false);
        bitmapInv4 =  Bitmap.createScaledBitmap(bitmapInv4, (int)(invLength), (int)(invHeight), false);
        bitmapInv5 =  Bitmap.createScaledBitmap(bitmapInv5, (int)(invLength), (int)(invHeight), false);
        bitmapInv6 =  Bitmap.createScaledBitmap(bitmapInv6, (int)(invLength), (int)(invHeight), false);
        bitmapInv7 =  Bitmap.createScaledBitmap(bitmapInv7, (int)(invLength), (int)(invHeight), false);
        bitmapInv8 =  Bitmap.createScaledBitmap(bitmapInv8, (int)(invLength), (int)(invHeight), false);
        bitmapInv9 =  Bitmap.createScaledBitmap(bitmapInv9, (int)(invLength), (int)(invHeight), false);
        bitmapInv10 =  Bitmap.createScaledBitmap(bitmapInv10, (int)(invLength), (int)(invHeight), false);
        bitmapInv11 =  Bitmap.createScaledBitmap(bitmapInv11, (int)(invLength), (int)(invHeight), false);
        bitmapInv12 =  Bitmap.createScaledBitmap(bitmapInv12, (int)(invLength), (int)(invHeight), false);

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
        playerShip.setEx(bitmapEx);
        missile = new Missile(screenY);
        missile2 = new Missile(screenY);
        missile3 = new Missile(screenY);

        for (int i=0; i<invadersMissile.length; i++){
            invadersMissile[i] = new Missile(screenY);
            invadersMissile[i].setBitmap(bitmapEx);
        }

        numInvaders = 0;
        for(int row = 0; row < 3; row ++ ){
            if(row % 2 == 0){
                for(int column = 0; column< 2; column ++ ){
                    invaders[numInvaders] = new Invader(context, row, column*2, screenX, screenY,500);
                    invaders[numInvaders].setBitmap(bitmapInv1,bitmapInv2,bitmapInv3,bitmapInv4,bitmapEx);
                    numInvaders ++;
                }
            }
            else {
                for(int column = 0; column< 1; column ++ ){
                    invaders[numInvaders] = new Invader(context, row, column+1, screenX, screenY,500);
                    invaders[numInvaders].setBitmap(bitmapInv1,bitmapInv2,bitmapInv3,bitmapInv4,bitmapEx);
                    numInvaders ++;
                }
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
    public void initNextLevel(int level){
        if(level == 2){
            for (int i=0; i<invadersMissile.length; i++){
                invadersMissile[i] = new Missile(screenY);
                invadersMissile[i].setBitmap(bitmapEx);
            }

            numInvaders = 0;
            for(int row = 0; row < 6; row ++ ){
                if(row % 2 == 0){
                    for(int column = 0; column< 2; column ++ ){
                        invaders[numInvaders] = new Invader(context, row, column*2, screenX, screenY,90);
                        invaders[numInvaders].setBitmap(bitmapInv5,bitmapInv6,bitmapInv7,bitmapInv8,bitmapEx);
                        numInvaders ++;
                    }
                }
                else {
                    for(int column = 0; column< 1; column ++ ){
                        invaders[numInvaders] = new Invader(context, row, column+1, screenX, screenY,90);
                        invaders[numInvaders].setBitmap(bitmapInv5,bitmapInv6,bitmapInv7,bitmapInv8,bitmapEx);
                        numInvaders ++;
                    }
                }

            }
        }

        if(level == 3){
            for (int i=0; i<invadersMissile.length; i++){
                invadersMissile[i] = new Missile(screenY);
                invadersMissile[i].setBitmap(bitmapEx);
            }

            numInvaders = 0;
            for(int row = 0; row < 6; row ++ ){
                if(row % 2 == 0){
                    for(int column = 0; column< 2; column ++ ){
                        invaders[numInvaders] = new Invader(context, row, column*2, screenX, screenY,40);
                        invaders[numInvaders].setBitmap(bitmapInv9,bitmapInv10,bitmapInv11,bitmapInv12,bitmapEx);
                        numInvaders ++;
                    }
                }
                else {
                    for(int column = 0; column< 1; column ++ ){
                        invaders[numInvaders] = new Invader(context, row, column+1, screenX, screenY,40);
                        invaders[numInvaders].setBitmap(bitmapInv9,bitmapInv10,bitmapInv11,bitmapInv12,bitmapEx);
                        numInvaders ++;
                    }
                }

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

        if(missile.getStatus()){
            missile.update(fps);
        }

        if(missile2.getStatus()){
            missile2.update(fps);
        }

        if(missile3.getStatus()){
            missile3.update(fps);
        }

        // Has the player's bullet hit the top of the screen
        if(missile.getImpactPointY() < 0){
            missile.setInactive();
        }

        if(missile2.getImpactPointY() < 0){
            missile2.setInactive();
        }

        if(missile3.getImpactPointY() < 0){
            missile3.setInactive();
        }

        for(int i = 0; i < invadersMissile.length; i++){
            if(invadersMissile[i].getImpactPointY() > screenY){
                invadersMissile[i].setInactive();
            }
        }

        if(missile.getStatus()) {
            for (int i = 0; i < numInvaders; i++) {
                if (invaders[i].getVisibility()) {
                    if (RectF.intersects(missile.getRect(), invaders[i].getRect()) && !invaders[i].getExplode()) {
                        invaders[i].setExplode(true);
                        missile.setInactive();
                        score = score + 10;
                        sound.playExplosion();
                        // Has the player win
                    }
                }
            }
        }

        if(missile2.getStatus()) {
            for (int i = 0; i < numInvaders; i++) {
                if (invaders[i].getVisibility()) {
                    if (RectF.intersects(missile2.getRect(), invaders[i].getRect()) && !invaders[i].getExplode()) {
                        invaders[i].setExplode(true);
                        missile2.setInactive();
                        score = score + 10;
                        sound.playExplosion();
                        // Has the player win
                    }
                }
            }
        }

        if(missile3.getStatus()) {
            for (int i = 0; i < numInvaders; i++) {
                if (invaders[i].getVisibility()) {
                    if (RectF.intersects(missile3.getRect(), invaders[i].getRect()) && !invaders[i].getExplode()) {
                        invaders[i].setExplode(true);
                        missile3.setInactive();
                        score = score + 10;
                        sound.playExplosion();
                        // Has the player win
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

        if(missile2.getStatus()){
            for(int i = 0; i < numDefense; i++){
                if(defense[i].getVisibility()){
                    if(RectF.intersects(missile2.getRect(), defense[i].getRect())){
                        //collision
                        missile2.setInactive();
                        defense[i].setInvisible();
                    }
                }
            }
        }

        if(missile3.getStatus()){
            for(int i = 0; i < numDefense; i++){
                if(defense[i].getVisibility()){
                    if(RectF.intersects(missile3.getRect(), defense[i].getRect())){
                        //collision
                        missile3.setInactive();
                        defense[i].setInvisible();
                    }
                }
            }
        }

        for(int i = 0; i < invadersMissile.length; i++){
            if(invadersMissile[i].getStatus() && !invadersMissile[i].getExplode()){
                if(RectF.intersects(playerShip.getRect(), invadersMissile[i].getRect())){
                    invadersMissile[i].setExplode(true);
                    sound.playExplosion();
                    //invadersMissile[i].setInactive();
                    lives --;

                    // Is it game over
                    if (lives <= 0 && playerShip.getVisibility()){
                        playerShip.setExplode(true);
                    }

                }
            }
        }

        if(lives <= 0 && !playerShip.getVisibility()){
            paused = true;
            isItGameOver.postValue(true);
            //lives = 3;
            //score = 0;
            //initLevel();

            // NavDirections action = NavGraphDirections.actionSoloFragmentToSoloGameOverFragment();
            // Navigation.findNavController(this).navigate(action);


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


            if(playerShip.getVisibility()){
                canvas.drawBitmap(playerShip.getBitmap(),playerShip.getX(),playerShip.getY(), paint);
            }


            for(int i = 0; i < numInvaders; i++){
                if(invaders[i].getVisibility() || invaders[i].getExplode() ) {
                    canvas.drawBitmap(invaders[i].getBitmap(), invaders[i].getX(), invaders[i].getY(), paint);
                }
            }

            paint.setColor(Color.argb(255, 6, 12, 189));
            if(missile.getStatus()){
                canvas.drawRect(missile.getRect(),paint);
            }

            if(missile2.getStatus()){
                canvas.drawRect(missile2.getRect(),paint);
            }

            if(missile3.getStatus()){
                canvas.drawRect(missile3.getRect(),paint);
            }

            paint.setColor(Color.argb(255, 198, 0, 0));
            for(int i=0; i<invadersMissile.length; i++){
                if(invadersMissile[i].getStatus() && invadersMissile[i].getExplode()){
                    canvas.drawBitmap(invadersMissile[i].getBitmap(),invadersMissile[i].getX() - (invadersMissile[i].getBitmap().getWidth()/2)
                            , invadersMissile[i].getY() - (invadersMissile[i].getBitmap().getHeight()/2),paint);
                }
                else if(invadersMissile[i].getStatus()){
                    canvas.drawRect(invadersMissile[i].getRect(),paint);
                }

            }

            paint.setColor(Color.argb(255, 223, 223, 52));
            canvas.drawText("Score :   "+ score+"     Vie :    "+ lives,20,40,paint);

            paint.setColor(Color.argb(255, 3, 94, 232));
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
                    if(!missile.getStatus()){
                        if(missile.shoot(playerShip.getX()+playerShip.getLength()/2,playerShip.getY(),missile.UP)){
                            if(soundState){
                                sound.playLazerSound();
                            }

                        }
                    }

                    else if(missile.getStatus() && !missile2.getStatus()){
                        if(missile2.shoot(playerShip.getX()+playerShip.getLength()/2,playerShip.getY(),missile2.UP)){
                            if(soundState){
                                sound.playLazerSound();
                            }

                        }
                    }

                    else if(missile2.getStatus() && !missile3.getStatus()){
                        if(missile3.shoot(playerShip.getX()+playerShip.getLength()/2,playerShip.getY(),missile3.UP)){
                            if(soundState){
                                sound.playLazerSound();
                            }

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
                level++;
                if(level == 2){
                    initNextLevel(2);
                }
                else{
                    initNextLevel(3);
                }


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

}

