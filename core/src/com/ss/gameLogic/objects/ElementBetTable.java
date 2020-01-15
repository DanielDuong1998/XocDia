package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.scenes.GameScene;

public class ElementBetTable {
  private TextureAtlas atlas, atlasChip;
  private GLayerGroup group, groupChips;
  private int mode;
  private String strName;
  private int mul;
  private long total;
  private Image shape, yellowShape;
  public static boolean touchElement = false;
  private BitmapFont font;
  private Label moneyTxt;

  public ElementBetTable(TextureAtlas atlas, TextureAtlas atlasChip, GLayerGroup group, int mode, BitmapFont font){
    this.atlas = atlas;
    this.atlasChip = atlasChip;
    this.group = group;
    this.mode = mode;
    this.font = font;
    total = 0;
    initGroupChip();
    initMul();
    initShape();
    initLabel();
    effectLight(0, 5);
    addEventShape();
  }

  private void initGroupChip(){
    groupChips = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, groupChips);
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

  private void initLabel(){
    moneyTxt = new Label("" + total, new Label.LabelStyle(font, null));
    group.addActor(moneyTxt);
  }

  private void initShape(){
    yellowShape = GUI.createImage(atlas, strName + "L");
    shape = GUI.createImage(atlas, strName);
    group.addActor(yellowShape);
    group.addActor(shape);
    yellowShape.getColor().a = 0;
  }

  public void setPoMoneyTxt(float x, float y){
    moneyTxt.setPosition(x, y, Align.center);
  }

  public void setPosition(float x, float y){
    shape.setPosition(x, y, Align.center);
    yellowShape.setPosition(x, y, Align.center);
    if(mode <= 1){
      yellowShape.setPosition(x, y + 0.1f*yellowShape.getHeight(), Align.center);
    }
  }

  private void updateTxtMoney(){
    String str1 = converMoneyToStr();
    System.out.println("str1: " + str1);
    moneyTxt.setText(str1);
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

  private void addEventShape(){
    shape.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!touchElement){
          if(GameScene.money >= getValueMoney()){
            touchElement = true;
            ChipSmall chip = new ChipSmall(atlasChip, groupChips, shape.getX() + shape.getWidth()/2, shape.getY() + shape.getHeight()/2);
            total += chip.getValue();
            GameScene.money -= chip.getValue();
            // todo: update to prefs (!)
            updateTxtMoney();
          }
          else {
            System.out.println("out of money!!!!!!!!!!!!!!!!!!!!!!!1");
          }
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        touchElement = false;
      }
    });
  }

  private int getValueMoney(){
    switch (Config.idChip){
      case 0: {
        return 100;
      }
      case 1: {
        return 500;
      }
      case 2: {
        return 1000;
      }
      case 3: {
        return 5000;
      }
      case 4: {
        return 10000;
      }
      case 5: {
        return 50000;
      }
      case 6: {
        return 100000;
      }
      case 7: {
        return 500000;
      }
      case 8: {
        return 1000000;
      }
      default: return -1;
    }
  }

  public void reset(){
    total = 0;
    yellowShape.setVisible(false);
  }

  private String converMoneyToStr(){
    String str = "";
    if(total < 1000){
      str = total + "";
    }
    else if(total < 1000000){
      str = (float)total/1000 + "K";
    }
    else if(total < 1000000000){
      str = (float)total/1000000 + "M";
    }
    else {
      str = (float)total/1000000000 + "B";
    }
    return str;
  }
}
