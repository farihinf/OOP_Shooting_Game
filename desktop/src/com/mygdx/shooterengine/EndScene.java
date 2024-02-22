package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class EndScene extends Scene 
{
    // Screen
    private StretchViewport viewport;

    // Graphics
    private SpriteBatch batch;
    private Texture endBackground;
    private Texture GameOverlogo;
    private Texture returntoMainMenuButton;

    // Scene Manager
    private SceneManager sceneManager;

    // Button Positions and Dimensions
    private float returntoMainMenuButtonPosX;
    private float returntoMainMenuButtonPosY;
    private final float BUTTON_WIDTH = 20;
    private final float BUTTON_HEIGHT = 15;

    // World Parameters (Portrait Mode)
    private final int SCENE_WIDTH = 72;
    private final int SCENE_HEIGHT = 128;

    private Vector3 touchPos = new Vector3();

    EndScene(SceneManager sceneManager) 
    {
        this.sceneManager = sceneManager;

        viewport = new StretchViewport(SCENE_WIDTH, SCENE_HEIGHT);
        batch = new SpriteBatch();

        endBackground = new Texture(Gdx.files.internal("ScreenImages\\EndScene.png"));
        GameOverlogo = new Texture(Gdx.files.internal("ScreenImages\\GameOverLogo.png"));
        returntoMainMenuButton = new Texture(Gdx.files.internal("ScreenImages\\ReturnToMainMenu.png"));

        returntoMainMenuButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        returntoMainMenuButtonPosY = (SCENE_HEIGHT / 2) - 10 - BUTTON_HEIGHT;
    }

    public void draw() 
    {
        batch.begin();

        batch.draw(endBackground, 0, 0, SCENE_WIDTH, SCENE_HEIGHT);

        float logoWidth = 50;
        float logoHeight = 20;
        float logoPosX = (SCENE_WIDTH - logoWidth) / 2;
        float logoPosY = SCENE_HEIGHT - 20 - logoHeight; // Calculate & adjust Y position

        batch.draw(GameOverlogo, logoPosX, logoPosY, logoWidth, logoHeight);
        batch.draw(returntoMainMenuButton, returntoMainMenuButtonPosX, returntoMainMenuButtonPosY, BUTTON_WIDTH,
                BUTTON_HEIGHT);

        batch.end();
    }

    public void handleInput() 
    {
        if (Gdx.input.isTouched())
        {
            float touchX = Gdx.input.getX() * (float) SCENE_WIDTH / Gdx.graphics.getWidth();
            float touchY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) SCENE_HEIGHT
                    / Gdx.graphics.getHeight();

             // Check if touch is within bounds of the return button
            if (touchX >= returntoMainMenuButtonPosX && touchX <= returntoMainMenuButtonPosX + BUTTON_WIDTH &&
                    touchY >= returntoMainMenuButtonPosY && touchY <= returntoMainMenuButtonPosY + BUTTON_HEIGHT) 
            {
                sceneManager.changeScene(new MainMenu(sceneManager));
            }
        }
    }

    @Override
    public void show() 
    {
        // Sets the input processor to a new instance of InputAdapter, which allows
        // handling of input events
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

    // Function to call the handleInput and draw method
    public void initialise() 
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        draw();
    }

    @Override
    public void render(float delta) 
    {

    }

    // Function to ensures that the viewport and rendering setup are adjusted appropriately when the window size changes,
    // which helps to maintain the correct aspect ratio and scaling of the scene
    @Override
    public void resize(int width, int height) 
    {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() 
    {
        batch.dispose();
        endBackground.dispose();
        GameOverlogo.dispose();
        returntoMainMenuButton.dispose();
    }
}
