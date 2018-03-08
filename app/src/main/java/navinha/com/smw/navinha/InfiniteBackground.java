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

public class InfiniteBackground {
    private Bitmap figure;
    private int height;
    private int width;
    private Rect src;
    private Rect first;
    private Rect second;

    private static final String TAG = "InfiniteBackground";

    private final int STEP = 5;

    public InfiniteBackground(){
        try {
            InputStream is = GameParameterSingleton.assetManager.open("cenario1.png");
            figure = BitmapFactory.decodeStream(is);
            height = figure.getHeight();
            width = figure.getWidth();

            src = new Rect(0, 0, width, height);
            first = new Rect();
            second = new Rect();

        } catch (Exception e){
            Log.d(TAG, "Erro ao decodificar imagem");
        }
    }

    public void updateDistortion(){
        setWidth((int) (getWidth() * GameParameterSingleton.DISTORTION));
        setHeight((int) (getHeight() * GameParameterSingleton.DISTORTION));

        first.left = 0;
        first.top = 0;
        first.right = width;
        first.bottom = height;

        second.top = height;
        second.left = 0;
        second.right = width;
        second.bottom = first.top + height;
    }

    public void update(){
        // como ele se move
        int distortionStep = (int) (STEP * GameParameterSingleton.DISTORTION);
        first.left = 0;
        first.right = getWidth();
        first.top += distortionStep;
        first.bottom += distortionStep;

        second.left = 0;
        second.right = getWidth();
        second.top += distortionStep;
        second.bottom += distortionStep;

        if(first.top >= height){
            first.bottom = second.top;
            first.top = second.top - height;
        }

        if(second.top >= height){
            second.bottom = first.top;
            second.top = first.top - height;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(figure, src, first, null);
        canvas.drawBitmap(figure, src, second, null);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
