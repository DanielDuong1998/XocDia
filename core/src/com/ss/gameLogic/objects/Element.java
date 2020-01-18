package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class Element {
  private TextureAtlas atlas;
  private Group group;
  private Image redShape, whiteShape;
  private boolean status = true;

  public  Element(TextureAtlas atlas, Group group){
    this.atlas = atlas;
    this.group = group;
    initUI();
    shuffle();
  }

  private void initUI(){
    redShape = GUI.createImage(atlas, "redShape");
    whiteShape = GUI.createImage(atlas, "whiteShape");
    group.addActor(redShape);
    group.addActor(whiteShape);
    whiteShape.setVisible(false);
  }

  public void setPosition(float x, float y){
    redShape.setPosition(x, y, Align.center);
    whiteShape.setPosition(x, y, Align.center);
  }

  public void shuffle(){
    int sf = (int)Math.floor(Math.random()*2);
    status = sf == 0 ? true : false; //todo: sf = 0 -> true, sf = 1 -> false
    redShape.setVisible(status);
    whiteShape.setVisible(!status);
  }

  public float getWidth(){
    return redShape.getWidth();
  }

  public float getHeight(){
    return redShape.getHeight();
  }

  public float getX(){
    return redShape.getX();
  }

  public float getY(){
    return whiteShape.getY();
  }

  public void hiddenElement(boolean isHidden){
    if(isHidden){
      redShape.setVisible(false);
      whiteShape.setVisible(false);
    }
    else {
      redShape.setVisible(status);
      whiteShape.setVisible(!status);
    }
  }

  public boolean getStatus(){
    return status;
  }
}
