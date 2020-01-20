package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.GMain;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.effects.effectWin;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.scenes.GameScene;

import java.awt.Choice;

public class BetTable{
  private TextureAtlas atlas, atlasChip, atlasGirl;
  private GLayerGroup group;
  private Array<ElementBetTable> element;
  private Array<Image> chipImg;
  private int isChoosing = 0;
  private Boolean isBlockTouch = false, isBlockTouchE = false;
  private BitmapFont font;
  private Girl girl;
  private BowlDisk bowlDisk;
  private Vector2 modeResult;
  private GameScene game;

  public BetTable(GameScene game, TextureAtlas atlas, TextureAtlas atlasChip, TextureAtlas atlasGirl, GLayerGroup group, BitmapFont font){
    this.game = game;
    this.atlas = atlas;
    this.atlasChip = atlasChip;
    this.atlasGirl = atlasGirl;
    this.group = group;
    this.font = font;
    modeResult = new Vector2(-1, -1);
    renderUI();
  }

  private void renderUI(){
    element = new Array<>();
    ElementBetTable chan = new ElementBetTable(game, atlas, atlasChip, group, 0, font);
    ElementBetTable le = new ElementBetTable(game, atlas, atlasChip, group, 1, font);
    ElementBetTable tuDo = new ElementBetTable(game, atlas, atlasChip, group, 2, font);
    ElementBetTable tuTrang = new ElementBetTable(game, atlas, atlasChip, group, 3, font);
    ElementBetTable leDo = new ElementBetTable(game, atlas, atlasChip, group, 4, font);
    ElementBetTable leTrang = new ElementBetTable(game, atlas, atlasChip, group, 5, font);
    element.add(chan, le, tuDo);
    element.add(tuTrang, leDo, leTrang);

    chan.setPosition(Config.WidthScreen*0.31f, Config.HeightScreen*0.42f);
    chan.setPoMoneyTxt(Config.WidthScreen*0.28f, Config.HeightScreen*0.378f);
    le.setPosition(Config.WidthScreen*0.68f, Config.HeightScreen*0.42f);
    le.setPoMoneyTxt(Config.WidthScreen*0.635f, Config.HeightScreen*0.378f);
    tuDo.setPosition(Config.WidthScreen*0.26f, Config.HeightScreen*0.68f);
    tuDo.setPoMoneyTxt(Config.WidthScreen*0.23f, Config.HeightScreen*0.615f);
    tuTrang.setPosition(Config.WidthScreen*0.42f, Config.HeightScreen*0.68f);
    tuTrang.setPoMoneyTxt(Config.WidthScreen*0.39f, Config.HeightScreen*0.615f);
    leTrang.setPosition(Config.WidthScreen*0.58f, Config.HeightScreen*0.68f);
    leTrang.setPoMoneyTxt(Config.WidthScreen*0.54f, Config.HeightScreen*0.615f);
    leDo.setPosition(Config.WidthScreen*0.73f, Config.HeightScreen*0.68f);
    leDo.setPoMoneyTxt(Config.WidthScreen*0.69f, Config.HeightScreen*0.615f);

    girl = new Girl(atlasGirl);
    bowlDisk = new BowlDisk(atlasChip, girl,this);
  }

  private void addEventMoneyChip(){
    for(Image img : chipImg){
      addEventSingleMoneyChip(img);
    }
  }

  private void addEventSingleMoneyChip(Image imgC){
    imgC.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!isBlockTouch){
          isBlockTouch = true;
          isChoosing = chipImg.indexOf(imgC, true);
          updateLightMoneyChip();
          imgC.setColor(Color.WHITE);
        }
        return super.touchDown(event, x, y, pointer, button);
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        isBlockTouch = false;
      }
    });
  }

  private void updateLightMoneyChip(){
    for(int i = 0; i < chipImg.size; i++) {
      chipImg.get(i).setColor(Color.GRAY);
    }
  }

  public void turnOnSignal(Vector2 rs) {
    if(rs.x == rs.y){
      modeResult.x = 0; //todo: chan
      modeResult.y = -1;
    }
    else if(rs.x == 4){ //todo: tudo
      modeResult.x = 0;
      modeResult.y = 2;
    }
    else if(rs.y == 4){ //todo: tuTrang
      modeResult.x = 0;
      modeResult.y = 3;
    }
    else if(rs.x == 1){
      modeResult.x = 1;
      modeResult.y = 5;
    }
    else if(rs.x == 3){
      modeResult.x = 1;
      modeResult.y = 4;
    }

    for(ElementBetTable e : element){
      if(e.getMode() == modeResult.x || e.getMode() == modeResult.y){
        e.effectLight(0, 7);
      }
    }

    // add Particle here!!!
    System.out.println("check Win: " + checkWinLose());

    for(ElementBetTable e : element){
      if(e.getMode() != modeResult.x && e.getMode() != modeResult.y){
        e.reset();
        for(Actor at : e.getGroupChips().getChildren()){
          at.addAction(Actions.sequence(
            Actions.moveTo(Config.WidthScreen/2, Config.HeightScreen*0.3f, 1f, Interpolation.linear),
            Actions.alpha(0f, 1f),
            GSimpleAction.simpleAction((d, a)->{
              at.remove();
              at.clear();
              return true;
            })
          ));
        }
      }
    }

    boolean isPlay = false;

    for(ElementBetTable e : element){
      if(e.getMode() == modeResult.x || e.getMode() == modeResult.y){
        sendMoney(e);
      }
    }
  }

  private int checkWinLose(){ // todo: return 0 -> equal , 1 -> win, -1 -> lose
    long moneyWin = 0, moneyLose = 0;
    long result = 0;
    for(ElementBetTable e : element){
      if(e.getMode() == modeResult.x || e.getMode() == modeResult.y){
        moneyWin += e.getTotal();
      }
      else {
        moneyLose += e.getTotal();
      }
    }

    return moneyWin - moneyLose == 0 ? 0 : moneyWin - moneyLose > 0 ? 1 : -1;
  }

  private void sendMoney(ElementBetTable e){
    Group groupC = new Group();
    GStage.addToLayer(GLayer.ui, groupC);
    Array<Image> listSmallChips = new Array<>();
    long total = e.getTotal();
    while (total > 0){
      if(total < 500){
        int size = 0;
        size = (int)total / 100;
        total = total - size*100;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "100SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total < 1000){
        int size = 0;
        size = (int) total/500;
        total = total - size*500;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "500SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total < 5000){
        int size = 0;
        size = (int) total/1000;
        total = total - size*1000;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "1000SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total < 10000){
        int size = 0;
        size = (int) total/5000;
        total = total - size*5000;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "5000SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total < 50000){
        int size = 0;
        size = (int) total/10000;
        total = total - size*10000;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "10000SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total < 100000){
        int size = 0;
        size = (int) total/50000;
        total = total - size*50000;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "50000SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX(), e.getShape().getY(), 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total < 500000){
        int size = 0;
        size = (int) total/100000;
        total = total - size*100000;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "100000SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total < 1000000){
        int size = 0;
        size = (int) total/500000;
        total = total - size*500000;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "500000SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
      else if(total >= 1000000){
        int size = 0;
        size = (int) total/1000000;
        total = total - size*1000000;
        for(int i = 0; i < size; i++) {
          Image chip = GUI.createImage(atlasChip, "1000000SM");
          chip.setPosition(Config.WidthScreen/2, Config.HeightScreen*0.3f, Align.center);
          int pxTemp = (int)Math.floor(Math.random()*3);
          int pyTemp = (int)Math.floor(Math.random()*3);
          pxTemp *= chip.getWidth();
          pyTemp *= chip.getHeight();
          groupC.addActor(chip);
          listSmallChips.add(chip);
          chip.addAction(Actions.sequence(
            Actions.moveTo(e.getShape().getX() + pxTemp, e.getShape().getY() + pyTemp, 0.5f, Interpolation.linear)
          ));
        }
      }
    }

    Tweens.setTimeout(group, 1, ()->{
      if(listSmallChips.size > 0){
        SoundEffect.Play(SoundEffect.plusMoney);
        aniOver(true);
      }else {
        aniOver(false);

      }
      for(Image chip : listSmallChips){
        chip.addAction(Actions.sequence(
          Actions.moveTo(Config.WidthScreen/2, Config.HeightScreen*1.1f, 0.5f, Interpolation.slowFast),
          GSimpleAction.simpleAction((d, a)->{
            chip.remove();
            chip.clear();
            return true;
          })
          )
        );
      }
      for(Actor at : e.getGroupChips().getChildren()){
        at.addAction(Actions.sequence(
          Actions.moveTo(Config.WidthScreen/2, Config.HeightScreen*1.1f, 0.5f, Interpolation.slowFast),
          GSimpleAction.simpleAction((d, a)->{
            GameScene.money += e.getTotal()*e.getMul();
            GMain.prefs.putLong("money", GameScene.money);
            GMain.prefs.flush();
            game.updateMoneyTxt();
            e.reset();
            at.remove();
            at.clear();
            return true;
          })
        ));
      }
      Tweens.setTimeout(group, 1, ()->{
        game.checkMoneyToShowAdPanel();
      });
    });
  }
  private void aniOver(Boolean check){
    Group groupWin = new Group();
    GStage.addToLayer(GLayer.top,groupWin);
    final GShapeSprite blackOverlay = new GShapeSprite();
    blackOverlay.createRectangle(true, -GMain.screenWidth/2,-GMain.screenHeight/2, GMain.screenWidth*2, GMain.screenHeight*2);
    blackOverlay.setColor(0,0,0,0.8f);
    groupWin.addActor(blackOverlay);
    if(check==true){
      effectWin ef = new effectWin(1,GStage.getWorldWidth()/2,GStage.getWorldHeight()/2);
      groupWin.addActor(ef);
      ef.start();
      Image win = GUI.createImage(atlas,"winVi");
      win.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,Align.center);
      groupWin.addActor(win);
      win.setScale(0);
      win.setOrigin(Align.center);
      win.addAction(Actions.scaleTo(1,1,0.5f));
      Tweens.setTimeout(groupWin,2f,()->{
        groupWin.remove();
        groupWin.clear();
      });
    }else {
      effectWin ef = new effectWin(2,GStage.getWorldWidth()/2,GStage.getWorldHeight()/2);
      groupWin.addActor(ef);
      ef.start();
      Image lose = GUI.createImage(atlas,"loseVi");
      lose.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,Align.center);
      groupWin.addActor(lose);
      lose.setScale(0);
      lose.setOrigin(Align.center);
      lose.addAction(Actions.scaleTo(1,1,0.5f));
      Tweens.setTimeout(groupWin,2f,()->{
        groupWin.remove();
        groupWin.clear();
      });
    }

  }

  public void hiddenNoShape(boolean isHidden){
    for(ElementBetTable e : element){
      e.hiddenNoShape(!isHidden);
    }
  }
}
