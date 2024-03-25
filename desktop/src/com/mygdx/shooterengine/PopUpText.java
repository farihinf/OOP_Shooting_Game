package com.mygdx.shooterengine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PopUpText {

    private String currString;
    private BitmapFont textRender;
    private float alpha = 1f;
    private Color color;
    private float posX;
    private float posY;
    private SpriteBatch batch;
    private ArrayList<PopUpText> popUpList = new ArrayList<>();
    private Random random;
    private float randomNum;

    private static PopUpText instance = null;

    
    public void PopUp(SpriteBatch batch, String text, float r, float g, float b, float posX, float posY){
        textRender = new BitmapFont(); 
        alpha = 1f; 
        color = new Color(r, g, b, alpha);
        this.currString = text;
        this.posX = posX;
        this.posY = posY;
        this.batch = batch;
        random = new Random();
        randomNum = random.nextInt(4);
        popUpList.add(this);
    }

    public void DrawPopUpText(){
        Iterator<PopUpText> pIterator = popUpList.iterator();
		while (pIterator.hasNext()) {
            PopUpText popUpText = pIterator.next();
            if(popUpText.alpha > 0){
                if (randomNum == 0) {
                    posX += 10f * Gdx.graphics.getDeltaTime();
                }
                else if(randomNum == 1){
                    posX -= 10f * Gdx.graphics.getDeltaTime();
                }
                else if(randomNum == 2){
                    posY += 10f * Gdx.graphics.getDeltaTime();
                }
                else if(randomNum == 3){
                    posY -= 10f * Gdx.graphics.getDeltaTime();
                }
                popUpText.textRender.setColor(color.r, color.g, color.b, alpha);
                popUpText.alpha -= Gdx.graphics.getDeltaTime();
                popUpText.textRender.draw(batch, currString, posX, posY);
            }
            else{
                pIterator.remove();
            }
 
        }
       
    }

    public static PopUpText getInstance() 
    {
        if (instance == null) {
            instance = new PopUpText();
        }
        return instance;
    }

    public void CleanList(){
        popUpList.clear();
    }


}
