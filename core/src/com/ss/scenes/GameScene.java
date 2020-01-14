package com.ss.scenes;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.gameLogic.objects.BetTable;

public class GameScene extends GScreen{
  private TextureAtlas atlas;
  private GLayerGroup mainGroup;
  private Image bg, table, title, aroundFrameMoney, frameMoney;
  private Image btnPause;
  private BetTable betTable;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    initTexture();
    initGroup();
    initUI();
  }

  private void initTexture(){
    atlas = GAssetsManager.getTextureAtlas("PlayScene.atlas");
  }

  private void initGroup(){
    mainGroup = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, mainGroup);
  }

  private void initUI(){
    bg = GUI.createImage(atlas, "bg");
    btnPause = GUI.createImage(atlas, "btnPause");

    mainGroup.addActor(bg);
    mainGroup.addActor(btnPause);
    System.out.println("ratiow: " + Config.ratioX);
    bg.setWidth(bg.getWidth()*Config.ratioX);


    btnPause.setPosition(Config.WidthScreen - btnPause.getWidth()*1.1f,btnPause.getHeight()*0.1f);



    addEventBtnPause(btnPause);

    //initBetTable();
  }

  private void addEventBtnPause(Image btn){
    btn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Config.effectBtn(btn, ()->{
          setPauseScene(true);
        });
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void setPauseScene(boolean isPause){
    mainGroup.setPause(isPause);
  }

  private void initBetTable(){
    betTable = new BetTable(atlas, mainGroup);
  }


  @Override
  public void run() {

  }
}