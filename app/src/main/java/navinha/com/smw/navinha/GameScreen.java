package navinha.com.smw.navinha;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by rafael on 3/7/18.
 */

public class GameScreen extends View implements Runnable {

    private boolean update;
    private int i;
    private Paint paint;
    private static final String TAG = "GameScreen";

    private InfiniteBackground bg;
    private SpaceShip ship;
    private Meteor meteor;

    public GameScreen(Context context) {
        super(context);
        init();
    }

    public void update(){
        if(update){
            bg.update();
            ship.update();
            meteor.update();
        }
    }

    protected void onDraw(Canvas canvas){
//        canvas.drawText("Valor do i = " + i, 50, 100, paint);
        bg.draw(canvas);
        ship.draw(canvas);
        meteor.draw(canvas);
    }

    public void init(){
        i = 0;
        update = true;
        paint = new Paint();
        paint.setColor(Color.BLACK);

        // criar objetos de jogo
        bg = new InfiniteBackground();

        // definir fator de distorcao
        GameParameterSingleton.DISTORTION = (float) GameParameterSingleton.SCREEN_WIDTH / bg.getWidth();

        bg.updateDistortion();

        ship = new SpaceShip();
        ship.setX(50);
        ship.setY(50);
        ship.updateDistortion();

        meteor = new Meteor();
        meteor.setX(500);
        meteor.setY(100);
        meteor.updateDistortion();
    }

    public void run() {
        while(true){
            try{
                update();
                postInvalidate();
                Thread.sleep(50);
            } catch (Exception e){
                Log.d(TAG, "Erro no loop do jogo");
            }
        }
    }
}
