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

public class GameScene extends Scene {
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
    private float backtoMainMenuButtonPosX;
    private float backtoMainMenuButtonPosY;
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

    GameScene(SceneManager sceneManager) {
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

        // To calculat the X & Y coordiantes of the buttons on the screen relative to
        // the dimensions of scene
        backtoMainMenuButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        backtoMainMenuButtonPosY = (SCENE_HEIGHT / 2) - 20 - BUTTON_HEIGHT;
        resumeButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        resumeButtonPosY = (SCENE_HEIGHT / 2) + 10 - BUTTON_HEIGHT;
    }

    // Function responsible for rendering the scene onto the Screen
    public void draw() {
        batch.begin();

        // If game is not paused,the background will scroll upwards and is drawn twice
        // so as to create a seamless scrolling effect
        if (!isPaused()) {
            backgroundOffset--; // This will cause the background to move upwards pixel by pixel
            if (backgroundOffset % SCENE_HEIGHT == 0) // Check if the background has already scrolled by an entire
                                                      // Screen height
            {
                backgroundOffset = 0; // Set it to 0 to ensure that the background scrolling loop seamlessly without
                                      // running out of texture
            }
            batch.draw(background, 0, backgroundOffset, SCENE_WIDTH, SCENE_HEIGHT);
            batch.draw(background, 0, backgroundOffset + SCENE_HEIGHT, SCENE_WIDTH, SCENE_HEIGHT);
            // To draw the background just above the first one, which creates the scrolling
            // effect
        } else {
            batch.draw(background, 0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        }

        // If the game is paused, it will draw the Pause Interface
        if (isPaused()) {
            // To calcualte the width & height of the pause background after scaling. (With
            // a scaling factor)
            float scaledPauseBackgroundWidth = SCENE_WIDTH * scale;
            float scaledPauseBackgroundHeight = SCENE_HEIGHT * scale;
            batch.draw(overlayTexture, (SCENE_WIDTH - scaledPauseBackgroundWidth) / 2,
                    (SCENE_HEIGHT - scaledPauseBackgroundHeight) / 2, scaledPauseBackgroundWidth,
                    scaledPauseBackgroundHeight); // To calculate the center position of the interface

            float logoWidth = 50;
            float logoHeight = 20;
            float logoPosX = (SCENE_WIDTH - logoWidth) / 2;
            float logoPosY = SCENE_HEIGHT - 20 - logoHeight; // Calculate & adjust Y position

            batch.draw(pauselogo, logoPosX, logoPosY, logoWidth, logoHeight);

            batch.draw(back2mainmenuButton, backtoMainMenuButtonPosX, backtoMainMenuButtonPosY, BUTTON_WIDTH,
                    BUTTON_HEIGHT);
            batch.draw(resumeButton, resumeButtonPosX, resumeButtonPosY, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        batch.end();
    }

    public void handleInput() 
    {
        // Check if the screen is touched
        if (Gdx.input.isTouched())
        {
            // Calculate the X & Y coordinates of the touch position relative to the scene
            // width/height
            // It will then convert the touch positon from Screen to Scene coordinates.
            float touchX = Gdx.input.getX() * (float) SCENE_WIDTH / Gdx.graphics.getWidth();
            float touchY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) SCENE_HEIGHT
                    / Gdx.graphics.getHeight();

            // Check if the touch is within the bounds of the buttons
            if (touchX >= backtoMainMenuButtonPosX && touchX <= backtoMainMenuButtonPosX + BUTTON_WIDTH &&
                    touchY >= backtoMainMenuButtonPosY && touchY <= backtoMainMenuButtonPosY + BUTTON_HEIGHT) {
                sceneManager.getGameManager().restart();
                sceneManager.changeScene(new MainMenu(sceneManager));
            } else if (touchX >= resumeButtonPosX && touchX <= resumeButtonPosX + BUTTON_WIDTH &&
                    touchY >= resumeButtonPosY && touchY <= resumeButtonPosY + BUTTON_HEIGHT) {
                resume();
            }
        }
    }

    @Override
    public void show() {
        // Sets the input processor to a new instance of InputAdapter, which allows
        // handling of input events
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                // Sets the touch position using the screen coordinates. It sets the Y
                // coordinate
                // to adjust for the difference in coordinate systems between screen coordinates
                // and OpenGL coordinates.
                touchPos.set(screenX, Gdx.graphics.getHeight() - screenY, 0);

                // Calls the function to process the touch input
                handleInput();
                return true;
            }

            @Override
            public boolean keyDown(int key) {
                // Check if the ESC key is pressed
                if (key == Input.Keys.ESCAPE) {
                    togglePause();
                }
                return true;
            }
        });
    }

    // Function to call the draw method (Essentially same as Rendering)
    public void initialise() {
        draw();
    }

    // Function to set the pause flag to true
    @Override
    public void pause() {
        pause = true;
    }

    // Check if the game is in pause state
    boolean isPaused() {
        return pause;
    }

    // Function to check if it's to either resume or pause the game
    private void togglePause() {
        if (isPaused()) {
            resume();
        } else {
            pause();
        }
    }

    // Function to set the pause flag to false
    @Override
    public void resume() {
        pause = false;
    }

    @Override
    public void render(float delta) {

    }

    // Function to ensures that the viewport and rendering setup are adjusted
    // appropriately when the window size changes,
    // which helps to maintain the correct aspect ratio and scaling of the scene
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        pauselogo.dispose();
        back2mainmenuButton.dispose();
        resumeButton.dispose();
        overlayTexture.dispose();
    }
}
