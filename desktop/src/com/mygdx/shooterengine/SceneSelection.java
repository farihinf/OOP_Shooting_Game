package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class SceneSelection extends Scene {
    // Screen
    private StretchViewport viewport;

    // Graphics
    private SpriteBatch batch;
    private Texture sceneSelectionBG;
    private Texture returntoMainMenuButton;
    private Texture rightArrow;
    private Texture leftArrow;
    private Texture[] scene;
    private Texture confirmButton;

    // Scene Manager
    private SceneManager sceneManager;

    // Button Positions and Dimensions
    private float returntoMainMenuButtonPosX;
    private float returntoMainMenuButtonPosY;
    private float confirmButtonPosX;
    private float confirmButtonPosY;
    private float arrowWidth = 15;
    private float arrowXOffset = 4; // Adjust the X-axis offset for arrows
    private final float BUTTON_WIDTH = 20;
    private final float BUTTON_HEIGHT = 15;

    // World Parameters (Portrait Mode)
    private final int SCENE_WIDTH = 72;
    private final int SCENE_HEIGHT = 128;

    private int activeIndex = 0;

    private Vector3 touchPos = new Vector3();

    SceneSelection(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        viewport = new StretchViewport(SCENE_WIDTH, SCENE_HEIGHT);
        batch = new SpriteBatch();

        sceneSelectionBG = new Texture(Gdx.files.internal("ScreenImages\\selectionBg.png"));
        returntoMainMenuButton = new Texture(Gdx.files.internal("ScreenImages\\ReturnMainMenu.png"));
        leftArrow = new Texture(Gdx.files.internal("ScreenImages\\leftArrow.png"));
        rightArrow = new Texture(Gdx.files.internal("ScreenImages\\rightArrow.png"));
        confirmButton = new Texture(Gdx.files.internal("ScreenImages\\selectedShip.png"));

        returntoMainMenuButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2 + 25;
        returntoMainMenuButtonPosY = (SCENE_HEIGHT / 2) - 25 - BUTTON_HEIGHT;

        confirmButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        confirmButtonPosY = (SCENE_HEIGHT / 2) - 30 - BUTTON_HEIGHT;

        scene = new Texture[] {
                new Texture(Gdx.files.internal("ScreenImages\\Scene\\SpaceBackground(1).png")),
                new Texture(Gdx.files.internal("ScreenImages\\Scene\\SpaceBackground(2).png")),
        };
    }

    public void draw() {
        batch.begin();

        batch.draw(sceneSelectionBG, 0, 0, SCENE_WIDTH, SCENE_HEIGHT);

        float arrowPosY = (SCENE_HEIGHT / 2 - arrowWidth / 2); // Center the arrows vertically
        float arrowLeftPosX = arrowXOffset; // Adjust the offset for the left arrow
        float arrowRightPosX = SCENE_WIDTH - arrowWidth - arrowXOffset; // Adjust the offset for the right arrow
        batch.draw(leftArrow, arrowLeftPosX, arrowPosY, arrowWidth, arrowWidth);
        batch.draw(rightArrow, arrowRightPosX, arrowPosY, arrowWidth, arrowWidth);
        batch.draw(returntoMainMenuButton, returntoMainMenuButtonPosX, returntoMainMenuButtonPosY, BUTTON_WIDTH,
                BUTTON_HEIGHT);
        batch.draw(confirmButton, confirmButtonPosX, confirmButtonPosY, BUTTON_WIDTH,
                BUTTON_HEIGHT);

        float imageWidth = 25;
        float imageHeight = 50;
        float imagePosX = (SCENE_WIDTH - imageWidth) / 2;
        float imagePosY = (SCENE_HEIGHT - imageHeight) / 2;
        batch.draw(scene[activeIndex], imagePosX, imagePosY, imageWidth, imageHeight);

        batch.end();
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX() * (float) SCENE_WIDTH / Gdx.graphics.getWidth();
            float touchY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) SCENE_HEIGHT
                    / Gdx.graphics.getHeight();

            // Check if touch is within bounds of the left arrow
            if (touchX >= 10 && touchX <= 10 + arrowWidth &&
                    touchY >= SCENE_HEIGHT / 2 - arrowWidth / 2 &&
                    touchY <= SCENE_HEIGHT / 2 + arrowWidth / 2) {
                activeIndex = (activeIndex - 1 + scene.length) % scene.length;
            }
            // Check if touch is within bounds of the right arrow
            else if (touchX >= SCENE_WIDTH - arrowWidth - 10 && touchX <= SCENE_WIDTH - 10 &&
                    touchY >= SCENE_HEIGHT / 2 - arrowWidth / 2 &&
                    touchY <= SCENE_HEIGHT / 2 + arrowWidth / 2) {
                activeIndex = (activeIndex + 1) % scene.length;
            }
            // Check if touch is within bounds of the select player button
            else if (touchX >= confirmButtonPosX && touchX <= confirmButtonPosX + BUTTON_WIDTH &&
                    touchY >= confirmButtonPosY && touchY <= confirmButtonPosY + BUTTON_HEIGHT) 
            {
              
            }

            if (touchX >= returntoMainMenuButtonPosX && touchX <= returntoMainMenuButtonPosX + BUTTON_WIDTH &&
                    touchY >= returntoMainMenuButtonPosY && touchY <= returntoMainMenuButtonPosY + BUTTON_HEIGHT) {
                sceneManager.changeScene(new MainMenu(sceneManager));
            }
        }
    }

    @Override
    public void show() 
    {
        // Sets the input processor to a new instance of InputAdapter, which allows
        // handling of input events
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                touchPos.set(screenX, Gdx.graphics.getHeight() - screenY, 0);
                viewport.unproject(touchPos);
                handleInput();
                return true;
            }
        });
    }

    // Function to call the handleInput and draw method
    public void initialise() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        draw();
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
    }

    @Override
    public void dispose() 
    {
        batch.dispose();
        sceneSelectionBG.dispose();
        returntoMainMenuButton.dispose();
        confirmButton.dispose();
        leftArrow.dispose();
        rightArrow.dispose();
    }
}
