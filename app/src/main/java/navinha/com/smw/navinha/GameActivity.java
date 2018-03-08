package navinha.com.smw.navinha;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends Activity {

    private GameScreen gameScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setupParameters();
        this.gameScreen = new GameScreen(this);

        super.setContentView(gameScreen);

        Thread t = new Thread(gameScreen);
        t.start();
    }

    public void setupParameters(){
        GameParameterSingleton.ORIENTATION = GameParameterSingleton.PORTRAIT;
        GameParameterSingleton.SCREEN_HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
        GameParameterSingleton.SCREEN_WIDTH = getWindowManager().getDefaultDisplay().getWidth();

        GameParameterSingleton.assetManager = getAssets();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        hideNavigationBar();
    }

    private void hideNavigationBar(){
        if (Build.VERSION.SDK_INT > 16) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
