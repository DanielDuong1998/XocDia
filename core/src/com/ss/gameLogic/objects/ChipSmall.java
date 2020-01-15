package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;
import com.ss.gameLogic.StaticObjects.Config;

public class ChipSmall {
  private TextureAtlas atlas;
  private GLayerGroup group;
  private Image shape;
  private int id;
  private int value;
  private String strName;
  private float x, y;


  public ChipSmall(TextureAtlas atlas, GLayerGroup group, float x, float y){
    this.atlas = atlas;
    this.group = group;
    this.id = Config.idChip;
    this.x = x;
    this.y = y;
    initStrName();
    renderChip();
  }

  private void initStrName(){
    switch (id){
      case 0: {
        strName = "100SM";
        value = 100;
        break;
      }
      case 1: {
        strName = "500SM";
        value = 500;
        break;
      }
      case 2: {
        strName = "1000SM";
        value = 1000;
        break;
      }
      case 3: {
        strName = "5000SM";
        value = 5000;
        break;
      }
      case 4: {
        strName = "10000SM";
        value = 10000;
        break;
      }
      case 5: {
        strName = "50000SM";
        value = 50000;
        break;
      }
      case 6: {
        strName = "100000SM";
        value = 100000;
        break;
      }
      case 7: {
        strName = "500000SM";
        value = 500000;
        break;
      }
      case 8: {
        strName = "1000000SM";
        value = 1000000;
        break;
      }
      default:{
        strName = "code error at ChipSmall.java, at initStrName()";
        break;
      }
    }
  }

  private void renderChip(){
    shape = GUI.createImage(atlas, strName);
    group.addActor(shape);
    float px = ((int)Math.floor(Math.random()*10 - 5))*10;
    float py = ((int)Math.floor(Math.random()*8 - 2))*10;
    shape.addAction(Actions.moveTo(x + px, y + py,0.5f, Interpolation.fastSlow));
  }

  public void setPosition(float x, float y){
    shape.setPosition(x, y, Align.center);
  }

  public int getValue(){
    return value;
  }
}
