package fr.mastersid.meghasli.alienslayer2184.model;

import android.graphics.RectF;

public class Defense {
    private RectF rect;
    private boolean isVisible;

    public Defense(int row, int column, int shelterNumber, int screenX, int screenY){
        int width = screenX/65;
        int height = screenY/70;

        isVisible = true;

        int brickPadding = 1;

        int shelterPadding = screenX/7;
        int startHeight = screenY - (screenY/7 * 2);

        rect = new RectF(column * width + brickPadding +
                (shelterPadding * shelterNumber) +
                shelterPadding + shelterPadding * shelterNumber,
                row * height + brickPadding + startHeight,
                column * width + width - brickPadding +
                        (shelterPadding * shelterNumber) +
                        shelterPadding + shelterPadding * shelterNumber,
                row * height + height - brickPadding + startHeight);

    }
    public RectF getRect(){
        return this.rect;
    }
    public void setInvisible(){
        isVisible = false;
    }
    public boolean getVisibility(){
        return isVisible;
    }
}
