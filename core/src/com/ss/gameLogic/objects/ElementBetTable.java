package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class ElementBetTable {
  private TextureAtlas atlas;
  private GLayerGroup group;
  private int mode;
  private String strName;
  private int mul;
  private float total;
  private Image shape, yellowShape;

  public ElementBetTable(TextureAtlas atlas, GLayerGroup group, int mode){
    this.atlas = atlas;
    this.group = group;
    this.mode = mode;
    initMul();
    total = 0;
  }

  private void initMul(){
    switch (mode){
      case 0: {
        mul = 2;
        break;
      }
      case 1: {
        mul = 2;
        break;
      }
      case 2: {
        mul = 16;
        break;
      }
      case 3: {
        mul = 16;
        break;
      }
      case 4: {
        mul = 4;
        break;
      }
      case 5: {
        mul = 4;
        break;
      }
      default: mul = 1;
    }
  }



  public void addMoney(float money){
    total+= money;
  }

  public void reset(){
    total = 0;
  }
}
