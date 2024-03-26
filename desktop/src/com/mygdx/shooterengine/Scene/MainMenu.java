package com.mygdx.shooterengine.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.shooterengine.Managers.SceneManager;

public class MainMenu extends Scene {
    // Graphics
    private SpriteBatch batch;
    private Texture background;
    private Texture title_logo;
    private Texture startButton;

    // Screen
    private StretchViewport viewport;

    // Button Positions
    private float startButtonPosX;
    private float startButtonPosY;

    private final float BUTTON_WIDTH = 20;
    private final float BUTTON_HEIGHT = 15;

    // Scene Manager
    private SceneManager sceneManager;
    // private SceneSelection sceneSelection;
    // private EntityManager em;
    private final int SCENE_WIDTH = 72;
    private final int SCENE_HEIGHT = 128;

    public MainMenu(SceneManager sceneManager) 
    {
        this.sceneManager = sceneManager;

        viewport = new StretchViewport(SCENE_WIDTH, SCENE_HEIGHT);
        batch = new SpriteBatch();

        background = new Texture(Gdx.files.internal("ScreenImages\\SpaceBackground.png"));
        title_logo = new Texture(Gdx.files.internal("ScreenImages\\LogoSpace.png"));
        startButton = new Texture(Gdx.files.internal("ScreenImages\\StartButton.png"));

        // To calculat the X & Y coordiantes of the button on the screen relative to the
        // dimensions of scene
        startButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        startButtonPosY = (SCENE_HEIGHT / 2) + 5 - BUTTON_HEIGHT; // Adjust Y position of start button
    }

    // Function responsible for rendering the scene onto the Screen
    public void draw() {
        batch.begin();

        batch.draw(background, 0, 0, SCENE_WIDTH, SCENE_HEIGHT);

        float logoWidth = 60;
        float logoHeight = 30;
        float logoPosX = (SCENE_WIDTH - logoWidth) / 2;
        float logoPosY = SCENE_HEIGHT - 20 - logoHeight; // Calculate & adjust Y position

        batch.draw(title_logo, logoPosX, logoPosY, logoWidth, logoHeight);

        batch.draw(startButton, startButtonPosX, startButtonPosY, BUTTON_WIDTH, BUTTON_HEIGHT);

        batch.end();
    }

    public void handleInput() {
        // Check if the screen is touched
        if (Gdx.input.isTouched()) {
            // Calculate the X & Y coordinates of the touch position relative to the scene
            // width/height
            // It will then convert the touch positon from Screen to Scene coordinates.
            float touchX = Gdx.input.getX() * (float) SCENE_WIDTH / Gdx.graphics.getWidth();
            float touchY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) SCENE_HEIGHT
                    / Gdx.graphics.getHeight();

            // Check if touch is within bounds of the start button
            if (touchX >= startButtonPosX && touchX <= startButtonPosX + BUTTON_WIDTH &&
                    touchY >= startButtonPosY && touchY <= startButtonPosY + BUTTON_HEIGHT) {
                //GameManager.getInstance().restart();
                //sceneManager.changeScene(new GameScene(sceneManager));
                sceneManager.changeScene(new ShipSelection(sceneManager));
            }
        }
    }

    // Function to call the handleInput and draw method
    public void initialise() {
        handleInput();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        draw();
    }

    // Function to ensures that the viewport and rendering setup are adjusted
    // appropriately when the window size changes,
    // which helps to maintain the correct aspect ratio and scaling of the scene
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void dispose() {

    }
}
