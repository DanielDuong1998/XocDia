package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class ChipSmall {
  TextureAtlas atlas;
  GLayerGroup group;
  Image shape;
  int id;
  String strName;

  public ChipSmall(TextureAtlas atlas, GLayerGroup group, int id){
    this.atlas = atlas;
    this.group = group;
    this.id = id;
    initStrName();
  }

  private void initStrName(){
    switch (id){
      case 0: {
        strName = "100SM";
        break;
      }
      case 1: {
        strName = "500SM";
        break;
      }
      case 2: {
        strName = "1000SM";
        break;
      }
      case 3: {
        strName = "5000SM";
        break;
      }
      case 4: {
        strName = "10000SM";
        break;
      }
      case 5: {
        strName = "50000SM";
        break;
      }
      case 6: {
        strName = "100000SM";
        break;
      }
      case 7: {
        strName = "500000SM";
        break;
      }
      case 8: {
        strName = "1000000SM";
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
  }

  public void setPosition(float x, float y){
    shape.setPosition(x, y, Align.center);
  }
}
