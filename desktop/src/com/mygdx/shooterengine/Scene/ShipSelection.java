package com.mygdx.shooterengine.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.shooterengine.Managers.EntityManager;
import com.mygdx.shooterengine.Managers.GameManager;
import com.mygdx.shooterengine.Managers.SceneManager;

public class ShipSelection extends Scene {
    // Screen
    private StretchViewport viewport;

    // Graphics
    private SpriteBatch batch;
    private Texture SelectionBg;
    private Texture returntoMainMenuButton;
    private Texture rightArrow;
    private Texture leftArrow;
    private Texture[] ships;
    private Texture selectedShip;
    private Texture[] shipStats;

    // Scene Manager
    private SceneManager sceneManager;
    // private EntityManager entityManager;

    // Button Positions and Dimensions
    private float returntoMainMenuButtonPosX;
    private float returntoMainMenuButtonPosY;
    private float selectedButtonPosX;
    private float selectedButtonPosY;
    private float arrowWidth = 15;
    private float arrowXOffset = 4; // Adjust the X-axis offset for arrows
    private final float BUTTON_WIDTH = 15;
    private final float BUTTON_HEIGHT = 15;

    // World Parameters (Portrait Mode)
    private final int SCENE_WIDTH = 72;
    private final int SCENE_HEIGHT = 128;

    private int activeIndex = 0;

    private Vector3 touchPos = new Vector3();

    // ShipSelection(SceneManager sceneManager, EntityManager entityManager )
    ShipSelection(SceneManager sceneManager)
    {
        this.sceneManager = sceneManager;
        // this.entityManager = entityManager;

        viewport = new StretchViewport(SCENE_WIDTH, SCENE_HEIGHT);
        batch = new SpriteBatch();

        // title_logo = new Texture(Gdx.files.internal("ScreenImages\\Logo.png"));
        SelectionBg = new Texture(Gdx.files.internal("ScreenImages\\selectionBg.png"));
        returntoMainMenuButton = new Texture(Gdx.files.internal("ScreenImages\\ReturnMainMenu.png"));
        leftArrow = new Texture(Gdx.files.internal("ScreenImages\\leftArrow.png"));
        rightArrow = new Texture(Gdx.files.internal("ScreenImages\\rightArrow.png"));
        selectedShip = new Texture(Gdx.files.internal("ScreenImages\\selectedShip.png"));

        returntoMainMenuButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2 + 25;
        returntoMainMenuButtonPosY = (SCENE_HEIGHT / 2) - 25 - BUTTON_HEIGHT - 15;

        selectedButtonPosX = (SCENE_WIDTH - BUTTON_WIDTH) / 2;
        selectedButtonPosY = (SCENE_HEIGHT / 2) - 40 - BUTTON_HEIGHT;

        ships = new Texture[] {
                // new Texture(Gdx.files.internal("EntitySprites\\player.png")),
                new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_blue.png")),
                new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_red.png")),
                new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_grey.png")),
        };

        shipStats = new Texture[]{
                new Texture(Gdx.files.internal("ScreenImages\\statblue.png")),
                new Texture(Gdx.files.internal("ScreenImages\\statred.png")),
                new Texture(Gdx.files.internal("ScreenImages\\statgrey.png")),
        };
    }

    public void draw() {
        batch.begin();

        batch.draw(SelectionBg, 0, 0, SCENE_WIDTH, SCENE_HEIGHT);

        // draw arrows to select
        float arrowPosY = SCENE_HEIGHT / 2 - arrowWidth / 2; // Center the arrows vertically
        float arrowLeftPosX = arrowXOffset; // Adjust the offset for the left arrow
        float arrowRightPosX = SCENE_WIDTH - arrowWidth - arrowXOffset; // Adjust the offset for the right arrow
        batch.draw(leftArrow, arrowLeftPosX, arrowPosY, arrowWidth, arrowWidth);
        batch.draw(rightArrow, arrowRightPosX, arrowPosY, arrowWidth, arrowWidth);
        batch.draw(returntoMainMenuButton, returntoMainMenuButtonPosX, returntoMainMenuButtonPosY, BUTTON_WIDTH,
                BUTTON_HEIGHT);
        batch.draw(selectedShip, selectedButtonPosX, selectedButtonPosY, BUTTON_WIDTH,
                BUTTON_HEIGHT);

        // draw ships
        float imageWidth = 15;
        float imageHeight = 30;
        float imagePosX = (SCENE_WIDTH - imageWidth) / 2;
        float imagePosY = (SCENE_HEIGHT - imageHeight) / 2 + 25;
        batch.draw(ships[activeIndex], imagePosX, imagePosY, imageWidth, imageHeight);

        float statsImageWidth = 25; // Adjust as needed
        float statsImageHeight = 40; // Adjust as needed
        float statsImagePosX = imagePosX + (imageWidth - statsImageWidth) / 2; // Center horizontally
        float statsImagePosY = imagePosY - statsImageHeight - 5; // Position below the ship
        batch.draw(shipStats[activeIndex], statsImagePosX, statsImagePosY, statsImageWidth, statsImageHeight);

        batch.end();
    }

    public void handleInput() 
    {
        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX() * (float) SCENE_WIDTH / Gdx.graphics.getWidth();
            float touchY = (Gdx.graphics.getHeight() - Gdx.input.getY()) * (float) SCENE_HEIGHT
                    / Gdx.graphics.getHeight();

            // Check if touch is within bounds of the left arrow
            if (touchX >= 10 && touchX <= 10 + arrowWidth &&
                    touchY >= SCENE_HEIGHT / 2 - arrowWidth / 2 &&
                    touchY <= SCENE_HEIGHT / 2 + arrowWidth / 2) {
                activeIndex = (activeIndex - 1 + ships.length) % ships.length;
            }
            // Check if touch is within bounds of the right arrow
            else if (touchX >= SCENE_WIDTH - arrowWidth - 10 && touchX <= SCENE_WIDTH - 10 &&
                    touchY >= SCENE_HEIGHT / 2 - arrowWidth / 2 &&
                    touchY <= SCENE_HEIGHT / 2 + arrowWidth / 2) {
                activeIndex = (activeIndex + 1) % ships.length;
            }
            // Check if touch is within bounds of the select player button
            else if (touchX >= selectedButtonPosX && touchX <= selectedButtonPosX + BUTTON_WIDTH &&
                    touchY >= selectedButtonPosY && touchY <= selectedButtonPosY + BUTTON_HEIGHT) {
                EntityManager.getInstance().setTextureIndex(activeIndex);
                GameManager.getInstance().restart();
                sceneManager.changeScene(new GameScene(sceneManager));
            }

            // working main menu below
            // Check if touch is within bounds of the return button go back to main menu
            if (touchX >= returntoMainMenuButtonPosX && touchX <= returntoMainMenuButtonPosX + BUTTON_WIDTH &&
                    touchY >= returntoMainMenuButtonPosY && touchY <= returntoMainMenuButtonPosY + BUTTON_HEIGHT) {
                sceneManager.changeScene(new MainMenu(sceneManager));
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
    public void dispose() {
        batch.dispose();
        SelectionBg.dispose();
        returntoMainMenuButton.dispose();
        leftArrow.dispose();
        rightArrow.dispose();
        for (Texture ships : ships) {
            ships.dispose();
        }
        for (Texture shipStats : shipStats) {
            shipStats.dispose();
        }
    }
}