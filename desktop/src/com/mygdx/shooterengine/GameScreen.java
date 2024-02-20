package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends Scene
{
    // Screen
    private Camera camera;
    private Viewport viewport;

    // Graphics
    private SpriteBatch batch;
    private Texture background;
    private Texture pauselogo;
    private Texture back2mainmenuButton;
    private Texture resumeButton;
    private Texture overlayTexture;

    // Scene Manager
    private SceneManager sceneManager;

    // Button Positions and Dimensions
    private float back2MainMenuButtonPosX;
    private float back2MainMenuButtonPosY;
    private float resumeButtonPosX;
    private float resumeButtonPosY;
    private final float BUTTON_WIDTH = 20;
    private final float BUTTON_HEIGHT = 15;

    // Timing
    private int backgroundOffset;
    private boolean pause = false;

    // World Parameters (Portrait Mode)
    private final int SCENE_WIDTH = 72;
    private final int SCENE_HEIGHT = 128;
    private float scale = 0.8f;

    private Vector3 touchPos = new Vector3();

    GameScreen(SceneManager sceneManager) 
    {
        this.sceneManager = sceneManager;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(SCENE_WIDTH, SCENE_HEIGHT, camera);
        batch = new SpriteBatch();

        background = new Texture(Gdx.files.internal("ScreenImages\\SpaceShooter_Background.png"));
        backgroundOffset = 0;

        pauselogo = new Texture(Gdx.files.internal("ScreenImages\\PauseLogo.png"));
        back2mainmenuButton = new Texture(Gdx.files.internal("ScreenImages\\BackMainMenuButton.png"));
        resumeButton = new Texture(Gdx.files.internal("ScreenImages\\ResumeButton.png"));
        overlayTexture = new Texture(Gdx.files.internal("ScreenImages\\PauseBackGround.jpg"));

        back2MainMenuButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        back2MainMenuButtonPosY = (SCENE_HEIGHT / 2) - 20 - BUTTON_HEIGHT;
        resumeButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        resumeButtonPosY = (SCENE_HEIGHT / 2) + 10 - BUTTON_HEIGHT;
    }

    public void draw()
     {
        batch.begin();
        
        if (!isPaused()) 
        {
            backgroundOffset--;
            if (backgroundOffset % SCENE_HEIGHT == 0) 
            {
                backgroundOffset = 0;
            }
            batch.draw(background, 0, backgroundOffset, SCENE_WIDTH, SCENE_HEIGHT);
            batch.draw(background, 0, backgroundOffset + SCENE_HEIGHT, SCENE_WIDTH, SCENE_HEIGHT);
        }
        else 
        {
            batch.draw(background, 0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        }

        if (isPaused()) 
        {
            float scaledPauseBackgroundWidth = SCENE_WIDTH * scale;
            float scaledPauseBackgroundHeight = SCENE_HEIGHT * scale;
            batch.draw(overlayTexture, (SCENE_WIDTH - scaledPauseBackgroundWidth) / 2,
                    (SCENE_HEIGHT - scaledPauseBackgroundHeight) / 2, scaledPauseBackgroundWidth,
                    scaledPauseBackgroundHeight);
            float logoWidth = 50;
            float logoHeight = 20;
            float logoPosX = (SCENE_WIDTH - logoWidth) / 2;
            float logoPosY = SCENE_HEIGHT - 20 - logoHeight; // Adjust Y position

            batch.draw(pauselogo, logoPosX, logoPosY, logoWidth, logoHeight);

            batch.draw(back2mainmenuButton, back2MainMenuButtonPosX, back2MainMenuButtonPosY, BUTTON_WIDTH, BUTTON_HEIGHT);
            batch.draw(resumeButton, resumeButtonPosX, resumeButtonPosY, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        batch.end();
    }

    public void handleInput() 
    {
        if (Gdx.input.isTouched()) 
        {
            float touchX = Gdx.input.getX() * (float) SCENE_WIDTH / Gdx.graphics.getWidth();
            float touchY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) SCENE_HEIGHT
                    / Gdx.graphics.getHeight();

            // Check if touch is within bounds of the back to main menu button
            if (touchX >= back2MainMenuButtonPosX && touchX <= back2MainMenuButtonPosX + BUTTON_WIDTH &&
                    touchY >= back2MainMenuButtonPosY && touchY <= back2MainMenuButtonPosY + BUTTON_HEIGHT) 
            {
                sceneManager.getGameManager().restart();
                sceneManager.changeScene(new MainMenu(sceneManager));
            }
            else if (touchX >= resumeButtonPosX && touchX <= resumeButtonPosX + BUTTON_WIDTH &&
                    touchY >= resumeButtonPosY && touchY <= resumeButtonPosY + BUTTON_HEIGHT)   // Check if touch is within bounds of the resume button
            {
                resume();
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

            @Override
            public boolean keyDown(int key)
            {
                if (key == Input.Keys.ESCAPE) 
                {
                    togglePause();
                }
                return true;
            }
        });
    }

    public void initialise() 
    {
        draw();
    }

    @Override
    public void pause() 
    {
        pause = true;
    }

    boolean isPaused() 
    {
        return pause;
    }

    private void togglePause() 
    {
        if (isPaused()) 
        {
            resume();
        } else {
            pause();
        }
    }

    @Override
    public void resume() 
    {
        pause = false;
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
        background.dispose();
        pauselogo.dispose();
        back2mainmenuButton.dispose();
        resumeButton.dispose();
        overlayTexture.dispose();
    }
}
