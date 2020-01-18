package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.GMain;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.scenes.GameScene;

public class AdsPanel {
  private TextureAtlas atlas;
  private Group group;
  private GShapeSprite blackOverlay;
  private Image panelReward, panelShowAd, panelSupport;
  private Image btnClose1, btnClose2, btnClose3, btnWatch;
  private GameScene game;

  public AdsPanel(TextureAtlas atlas, GameScene game){
    this.atlas = atlas;
    this.game = game;
    initGroup();
    initUI();
    addEventBtn();
  }

  private void initGroup(){
    group = new Group();
    GStage.addToLayer(GLayer.top, group);
  }

  private void initUI(){
    blackOverlay = new GShapeSprite();
    blackOverlay.createRectangle(true, -Config.WidthScreen/2, -Config.HeightScreen/2, Config.WidthScreen*2, Config.HeightScreen*2);
    blackOverlay.setColor(0, 0, 0, 0.8f);
    blackOverlay.setVisible(false);

    panelReward = GUI.createImage(atlas, "panelReward");
    panelShowAd = GUI.createImage(atlas, "panelShowAd");
    panelSupport = GUI.createImage(atlas, "panelSupport");
    btnClose1 = GUI.createImage(atlas, "btnClose");
    btnClose2 = GUI.createImage(atlas, "btnClose");
    btnClose3 = GUI.createImage(atlas, "btnClose");
    btnWatch = GUI.createImage(atlas, "btnWatch");

    group.addActor(blackOverlay);
    group.addActor(panelReward);
    group.addActor(panelShowAd);
    group.addActor(panelSupport);
    group.addActor(btnClose1);
    group.addActor(btnClose2);
    group.addActor(btnClose3);
    group.addActor(btnWatch);

    panelReward.setVisible(false);
    panelShowAd.setVisible(false);
    panelSupport.setVisible(false);
    btnClose1.setVisible(false);
    btnClose2.setVisible(false);
    btnClose3.setVisible(false);
    btnWatch.setVisible(false);
  }

  public void showPanelShowAd(boolean isShow){
    if(isShow){
      blackOverlay.setVisible(true);
      panelShowAd.setVisible(true);
      btnClose1.setVisible(true);
      btnWatch.setVisible(true);

      panelShowAd.setPosition(Config.WidthScreen/2, Config.HeightScreen/2, Align.center);
      btnClose1.setPosition(panelShowAd.getX() + panelShowAd.getWidth() - btnClose1.getWidth(), panelShowAd.getY());
      btnWatch.setPosition(Config.WidthScreen/2, panelShowAd.getY() + panelShowAd.getHeight()*0.8f, Align.center);
    }
    else {
      blackOverlay.setVisible(false);
      panelShowAd.setVisible(false);
      btnClose1.setVisible(false);
      btnWatch.setVisible(false);
    }
  }

  public void showPanelSupport(boolean isShow){
    if(isShow){
      blackOverlay.setVisible(true);
      panelSupport.setVisible(true);
      btnClose2.setVisible(true);

      panelSupport.setPosition(Config.WidthScreen/2, Config.HeightScreen/2, Align.center);
      btnClose2.setPosition(panelSupport.getX() + panelSupport.getWidth() - btnClose2.getWidth(), panelSupport.getY());
    }
    else {
      blackOverlay.setVisible(false);
      panelSupport.setVisible(false);
      btnClose2.setVisible(false);
    }
  }

  public void showPanelReward(boolean isShow){
    if(isShow){
      blackOverlay.setVisible(true);
      panelReward.setVisible(true);
      btnClose3.setVisible(true);

      panelReward.setPosition(Config.WidthScreen/2, Config.HeightScreen/2, Align.center);
      btnClose3.setPosition(panelReward.getX() + panelReward.getWidth() - btnClose2.getWidth(), panelReward.getY());
    }
    else {
      blackOverlay.setVisible(false);
      panelReward.setVisible(false);
      btnClose3.setVisible(false);
    }
  }

  private void addEventBtn(){
    btnClose1.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Config.effectBtn(btnClose1, ()->{
          showPanelShowAd(false);
          showPanelSupport(true);
          GameScene.money += 500;
          game.updateMoneyTxt();
          GMain.prefs.putLong("money", GameScene.money);
          GMain.prefs.flush();
        });
        return super.touchDown(event, x, y, pointer, button);
      }
    });

    btnClose2.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Config.effectBtn(btnClose2, ()->{
          showPanelSupport(false);
        });
        return super.touchDown(event, x, y, pointer, button);
      }
    });

    btnClose3.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Config.effectBtn(btnClose3, ()->{
          showPanelReward(false);
        });
        return super.touchDown(event, x, y, pointer, button);
      }
    });

    btnWatch.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Config.effectBtn(btnWatch, ()->{
          showAds();
        });
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void showAds(){
    GMain.platform.ShowVideoReward((boolean success)->{
      if(success){
        GMain.platform.ShowFullscreen();
        showPanelShowAd(false);
        showPanelReward(true);
        GameScene.money += 5000;
        game.updateMoneyTxt();
        GMain.prefs.putLong("money", GameScene.money);
        GMain.prefs.flush();
      }
      else {
        GMain.platform.ShowFullscreen();
        showPanelShowAd(false);
        showPanelSupport(true);
        GameScene.money += 500;
        game.updateMoneyTxt();
        GMain.prefs.putLong("money", GameScene.money);
        GMain.prefs.flush();
      }
    });
  }

  public void showPanel(){
    if(GMain.platform.isVideoRewardReady()){
      showPanelShowAd(true);
    }
    else {
      showPanelSupport(true);
      GameScene.money += 500;
      game.updateMoneyTxt();
      GMain.prefs.putLong("money", GameScene.money);
      GMain.prefs.flush();
    }
  }


}
