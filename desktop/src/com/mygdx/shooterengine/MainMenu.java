package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenu extends Scene
{
    private SpriteBatch batch;
    private Texture background;
    private Texture title_logo;
    private Texture startButton;
    private Texture optionsButton;
    private StretchViewport viewport;
    
    private float startButton_posX;
    private float startButton_posY;
    private float optionsButtonX;
    private float optionsButtonY;
    
    private SceneManager sceneManager;
    
    private Texture overlayImage;
    private boolean showOverlay = false;
    
    float overlayWidth;
    float overlayHeight;
    float overlayX;
    float overlayY;
    
    MainMenu(SceneManager sceneManager) 
    {
        batch = new SpriteBatch();
        background = new Texture("MainMenu.png");
        title_logo = new Texture("Logo.png");
        startButton = new Texture("StartButton.png");
        optionsButton = new Texture("OptionsButton.png");
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        
        overlayImage = new Texture("Interface.png");  
        
        this.sceneManager = sceneManager;
    }

	public void initialise() 
    {
        handleInput();  

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        draw();
    }
    
    public void draw() 
    {
    	batch.begin();

        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        float titleScale = 0.5f;
        float titleWidth = title_logo.getWidth() * titleScale;
        float titleHeight = title_logo.getHeight() * titleScale;
        float title_posX = viewport.getWorldWidth() / 2 - titleWidth / 2;
        float title_posY = viewport.getWorldHeight() - 200;
        batch.draw(title_logo, title_posX, title_posY, titleWidth, titleHeight);

        startButton_posX = viewport.getWorldWidth() / 2 - startButton.getWidth() / 4;
        startButton_posY = title_posY - 100;
        batch.draw(startButton, startButton_posX, startButton_posY, startButton.getWidth() / 2, startButton.getHeight() / 2);

        optionsButtonX = viewport.getWorldWidth() / 2 - optionsButton.getWidth() / 4;
        optionsButtonY = startButton_posY - 100;
        batch.draw(optionsButton, optionsButtonX, optionsButtonY, optionsButton.getWidth() / 2, optionsButton.getHeight() / 2);

        if (showOverlay) 
        {
            overlayWidth = viewport.getWorldWidth() * 0.8f;  // Adjust the scaling factor as needed
            overlayHeight = viewport.getWorldHeight() * 0.8f;  // Adjust the scaling factor as needed
            overlayX = (viewport.getWorldWidth() - overlayWidth) / 2;
            overlayY = (viewport.getWorldHeight() - overlayHeight) / 2;
            batch.draw(overlayImage, overlayX, overlayY, overlayWidth, overlayHeight);
        }

        batch.end();
    }

    private void handleInput() 
    {
        if (Gdx.input.isTouched()) 
        {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            Vector3 touchPos = new Vector3(touchX, touchY, 0);
            viewport.unproject(touchPos);

            if (isButtonClicked(startButton, startButton_posX, startButton_posY, startButton.getWidth() / 2, startButton.getHeight() / 2, touchPos.x, touchPos.y)) 
            {
                sceneManager.changeScene(new GameScreen(sceneManager));
            }

            if (isButtonClicked(optionsButton, optionsButtonX, optionsButtonY, optionsButton.getWidth() / 2, optionsButton.getHeight() / 2, touchPos.x, touchPos.y)) 
            {
                showOverlay = !showOverlay;
            }
        }
    }

    private boolean isButtonClicked(Texture button, float buttonX, float buttonY, float buttonWidth, float buttonHeight, float touchX, float touchY) 
    {
        boolean xCondition = touchX >= buttonX && touchX <= buttonX + buttonWidth;

        // To invert the Y-coordinate for the touch position
        boolean yCondition = Gdx.graphics.getHeight() - touchY >= buttonY && Gdx.graphics.getHeight() - touchY <= buttonY + buttonHeight;

        return xCondition && yCondition;
    }

    @Override
    public void resize(int width, int height) 
    {
        viewport.update(width, height, true);
    }


	@Override
	public void render(float delta) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void dispose() 
    {
        batch.dispose();
        title_logo.dispose();
        startButton.dispose();
        optionsButton.dispose();
    }
}
