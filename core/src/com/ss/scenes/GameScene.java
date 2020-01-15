package com.ss.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.GMain;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.gameLogic.objects.BetTable;
import com.ss.gameLogic.objects.BowlDisk;
import com.ss.gameLogic.objects.Girl;

public class GameScene extends GScreen{
  private TextureAtlas atlas, atlasGirl, atlasChips;
  private GLayerGroup mainGroup;
  private Image bg, frame;
  private Image btnPause;
  private BetTable betTable;
  private BitmapFont font;
  public static long money = 0;
  private boolean firstTime = true;
  private BowlDisk bowlDisk;
  private Girl girl;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    initMoney();
    initTexture();
    initBitmapfont();
    initGroup();
    initUI();
  }

  private void initMoney(){
    firstTime = GMain.prefs.getBoolean("firstTime", true);
    if(firstTime){
      GMain.prefs.putBoolean("firstTime", false);
      GMain.prefs.putLong("money", 3000);
      GMain.prefs.flush();
    }
    GMain.prefs.putLong("money", 10000000);
    GMain.prefs.flush();
    money = GMain.prefs.getLong("money");
  }

  private void initTexture(){
    atlas = GAssetsManager.getTextureAtlas("PlayScene.atlas");
    atlasGirl = GAssetsManager.getTextureAtlas("Girl.atlas");
    atlasChips = GAssetsManager.getTextureAtlas("Chips.atlas");
  }

  private void initGroup(){
    mainGroup = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, mainGroup);
  }

  private void initUI(){
    bg = GUI.createImage(atlas, "bg");
    btnPause = GUI.createImage(atlas, "btnPause");
    frame = GUI.createImage(atlas, "frame");

    mainGroup.addActor(bg);
    mainGroup.addActor(btnPause);
    mainGroup.addActor(frame);
    bg.setWidth(bg.getWidth()*Config.ratioX);

    btnPause.setPosition(Config.WidthScreen - btnPause.getWidth()*1.1f,btnPause.getHeight()*0.1f);
    frame.setPosition(Config.WidthScreen/2, Config.HeightScreen - frame.getHeight()/2, Align.center);

    betTable = new BetTable(atlas, atlasChips, mainGroup, font);
    addEventBtnPause(btnPause);
    //initBetTable();
    girl = new Girl(atlasGirl);
    bowlDisk = new BowlDisk(atlasChips,girl);

  }

  private void initBitmapfont(){
    font = GAssetsManager.getBitmapFont("moneyFont.fnt");
  }

  private void addEventBtnPause(Image btn){
    btn.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Config.effectBtn(btn, ()->{
          //comment test
          //setPauseScene(true);
        });
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void setPauseScene(boolean isPause){
    mainGroup.setPause(isPause);
  }



  @Override
  public void run() {

  }
}