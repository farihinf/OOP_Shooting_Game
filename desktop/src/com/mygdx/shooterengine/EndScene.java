package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EndScene extends Scene
{
    // Screen
    private Camera camera;
    private Viewport viewport;

    // Graphics
    private SpriteBatch batch;
    private Texture endBackground;
    private Texture GameOverlogo;
    private Texture backtomainmenuButton;
    private Texture restartButton;

    // Scene Manager
    private SceneManager sceneManager;

    // Button Positions and Dimensions
    private float restartButtonPosX;
    private float restartButtonPosY;
    private float backtomainmenuButtonPosX;
    private float backtomainmenuButtonPosY;
    private final float BUTTON_WIDTH = 20;
    private final float BUTTON_HEIGHT = 15;

    // World Parameters (Portrait Mode)
    private final int SCENE_WIDTH = 72;
    private final int SCENE_HEIGHT = 128;

    private Vector3 touchPos = new Vector3();

    EndScene(SceneManager sceneManager) 
    {
        this.sceneManager = sceneManager;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        batch = new SpriteBatch();

        endBackground = new Texture(Gdx.files.internal("ScreenImages\\EndScene.png"));

        GameOverlogo = new Texture(Gdx.files.internal("ScreenImages\\GameOverLogo.png"));
        restartButton = new Texture(Gdx.files.internal("ScreenImages\\RestartButton.png"));
        backtomainmenuButton = new Texture(Gdx.files.internal("ScreenImages\\BackMainMenuButton.png"));
       
        restartButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        restartButtonPosY = (SCENE_HEIGHT / 2) + 10 - BUTTON_HEIGHT;
        backtomainmenuButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        backtomainmenuButtonPosY = (SCENE_HEIGHT / 2) - 20 - BUTTON_HEIGHT;
    }

    public void draw()
     {
        batch.begin();

        batch.draw(endBackground, 0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        
        float logoWidth = 50;
        float logoHeight = 20;
        float logoPosX = (SCENE_WIDTH - logoWidth) / 2;
        float logoPosY = SCENE_HEIGHT - 20 - logoHeight; // Adjust Y position

        batch.draw(GameOverlogo, logoPosX, logoPosY, logoWidth, logoHeight);
        batch.draw(restartButton, restartButtonPosX, restartButtonPosY, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(backtomainmenuButton, backtomainmenuButtonPosX, backtomainmenuButtonPosY, BUTTON_WIDTH, BUTTON_HEIGHT);
        
        batch.end();
    }

    public void handleInput() 
    {
        if (Gdx.input.isTouched()) 
        {
            float touchX = Gdx.input.getX() * (float) SCENE_WIDTH / Gdx.graphics.getWidth();
            float touchY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) SCENE_HEIGHT
                    / Gdx.graphics.getHeight();

            // Check if touch is within bounds of the back to buttons
            if (touchX >= restartButtonPosX && touchX <= restartButtonPosX + BUTTON_WIDTH &&
                    touchY >= restartButtonPosY && touchY <= restartButtonPosY + BUTTON_HEIGHT) 
            {
                sceneManager.changeScene(new GameScreen(sceneManager));
            } 
            else if (touchX >= backtomainmenuButtonPosX && touchX <= backtomainmenuButtonPosX + BUTTON_WIDTH &&
                    touchY >= backtomainmenuButtonPosY && touchY <= backtomainmenuButtonPosY + BUTTON_HEIGHT)
            {
                sceneManager.changeScene(new MainMenu(sceneManager));
            }
        }
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(new InputAdapter() 
        {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) 
            {
                touchPos.set(screenX, Gdx.graphics.getHeight() - screenY, 0);
                viewport.unproject(touchPos);
                handleInput();
                return true;
            }
        });
    }

    public void initialise() 
    {
        draw();
    }

    @Override
    public void render(float delta) 
    {

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void dispose() 
    {
        batch.dispose();
        endBackground.dispose();
        GameOverlogo.dispose();
        restartButton.dispose();
        backtomainmenuButton.dispose();
    }
}
