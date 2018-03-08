package navinha.com.smw.navinha;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.io.InputStream;

/**
 * Created by rafael on 3/7/18.
 */

public class Meteor extends GameObject {

    private Bitmap figure;
    private Rect src;
    private Rect dst;

    private int spriteWidth;
    private int spriteHeight;

    private int currentSprite;
    private static final String TAG = "SpaceShip";

    public Meteor(){
        try{
            InputStream is = GameParameterSingleton.assetManager.open("meteor1.png");
            figure = BitmapFactory.decodeStream(is);

            spriteWidth = figure.getWidth() / 16;
            spriteHeight = figure.getHeight();

            setWidth(spriteWidth);
            setHeight(spriteHeight);

            currentSprite = 0;

            src = new Rect(0, 0, getWidth(), getHeight());
            dst = new Rect();

        } catch (Exception e){
            Log.d(TAG, "Erro ao carregar imgem");
        }
    }


    @Override
    public void update() {
        src.top = 0;
        src.bottom = spriteHeight;
        src.left = currentSprite * spriteWidth;
        src.right = src.left + spriteWidth;

        dst.top = getY();
        dst.bottom = getY() + getHeight();
        dst.left = getX();
        dst.right = getX() + getWidth();

        currentSprite = (currentSprite + 1) % 16;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(figure, src, dst, null);
    }
}

