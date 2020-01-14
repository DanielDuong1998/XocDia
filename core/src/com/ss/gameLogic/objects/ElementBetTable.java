package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.action.exAction.GSimpleAction;
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
    total = 0;

    initMul();
    initShape();
    effectLight(0, 5);
  }

  private void initMul(){
    switch (mode){
      case 0: {
        strName = "chan";
        mul = 2;
        break;
      }
      case 1: {
        strName = "le";
        mul = 2;
        break;
      }
      case 2: {
        strName = "tuDo";
        mul = 16;
        break;
      }
      case 3: {
        strName = "tuTrang";
        mul = 16;
        break;
      }
      case 4: {
        strName = "leDo";
        mul = 4;
        break;
      }
      case 5: {
        strName = "leTrang";
        mul = 4;
        break;
      }
      default: mul = 1;
    }
  }

  private void initShape(){
    yellowShape = GUI.createImage(atlas, strName + "L");
    shape = GUI.createImage(atlas, strName);
    group.addActor(yellowShape);
    group.addActor(shape);
    yellowShape.getColor().a = 0;
  }

  public void setPosition(float x, float y){
    shape.setPosition(x, y, Align.center);
    yellowShape.setPosition(x, y, Align.center);
    if(mode <= 1){
      yellowShape.setPosition(x, y + 0.1f*yellowShape.getHeight(), Align.center);
    }
  }

  private void effectLight(int turnMm, int turnAll){
    if(turnMm < turnAll) {
      yellowShape.addAction(Actions.sequence(
        Actions.alpha(1, 0.5f, Interpolation.linear),
        Actions.alpha(0, 0.5f, Interpolation.linear),
        GSimpleAction.simpleAction((d, a) -> {
          effectLight(turnMm + 1, turnAll);
          return true;
        })
      ));
    }
  }

  public void addMoney(float money){
    total+= money;
  }

  public void reset(){
    total = 0;
    yellowShape.setVisible(false);
  }
}
