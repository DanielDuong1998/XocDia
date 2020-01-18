package com.ss.scenes;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.GMain;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.gameLogic.objects.AdsPanel;
import com.ss.gameLogic.objects.BetTable;
import com.ss.gameLogic.objects.BowlDisk;
import com.ss.gameLogic.objects.Element;
import com.ss.gameLogic.objects.Girl;
import com.ss.gameLogic.objects.ScrollChip;
import com.ss.gameLogic.objects.Setting;

import java.text.DecimalFormat;

public class GameScene extends GScreen{
  private TextureAtlas atlas, atlasGirl, atlasChips, atlasAds;
  private GLayerGroup mainGroup;
  private Image bg, frame;
  private Image btnPause;
  private BetTable betTable;
  private BitmapFont font, font2;
  public BitmapFont font3;
  public static long money = 0;
  private boolean firstTime = true;
  private ScrollChip scrollChip;
  private Image frameMoney;
  private Label moneyTxt;
  private AdsPanel adsPanel;


  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    SoundEffect.Stopmusic(Config.indexMusic);
    if(!SoundEffect.music){
      SoundEffect.Playmusic(SoundEffect.bg1);
      Config.indexMusic = SoundEffect.bg1;
    }
    initMoney();
    initTexture();
    initBitmapfont();
    initGroup();
    initUI();
    initAdsPanel();
    checkMoneyToShowAdPanel();
  }


  private void initMoney(){
    firstTime = GMain.prefs.getBoolean("firstTime", true);
    if(firstTime){
      GMain.prefs.putBoolean("firstTime", false);
      GMain.prefs.putLong("money", 10000);
      GMain.prefs.flush();
    }
//    GMain.prefs.putLong("money", 10000000);
//    GMain.prefs.flush();
    money = GMain.prefs.getLong("money");
  }

  private void initTexture(){
    atlas = GAssetsManager.getTextureAtlas("PlayScene.atlas");
    atlasGirl = GAssetsManager.getTextureAtlas("Girl.atlas");
    atlasChips = GAssetsManager.getTextureAtlas("Chips.atlas");
    atlasAds = GAssetsManager.getTextureAtlas("AtlasAdsPanel.atlas");
  }

  private void initGroup(){
    mainGroup = new GLayerGroup();
    GStage.addToLayer(GLayer.ui, mainGroup);
  }

  private void initUI(){

    bg = GUI.createImage(atlas, "bg");
    btnPause = GUI.createImage(atlas, "btnPause");
    frame = GUI.createImage(atlas, "frame");
    frameMoney = GUI.createImage(atlas, "frameMoney");

    mainGroup.addActor(bg);
    mainGroup.addActor(btnPause);
    mainGroup.addActor(frame);
    mainGroup.addActor(frameMoney);
    bg.setWidth(bg.getWidth()*Config.ratioX);

    btnPause.setPosition(Config.WidthScreen - btnPause.getWidth()*1.1f,btnPause.getHeight()*0.1f);
    frame.setPosition(Config.WidthScreen/2, Config.HeightScreen - frame.getHeight()/2, Align.center);
    frameMoney.setPosition(frameMoney.getWidth()*0.6f, frameMoney.getHeight()*0.8f, Align.center);

    betTable = new BetTable(this, atlas, atlasChips, atlasGirl, mainGroup, font);
    //addEventBtnPause(btnPause);

    scrollChip = new ScrollChip(atlasChips, mainGroup);

    moneyTxt = new Label(FortmartPrice(money), new Label.LabelStyle(font2, null));
    moneyTxt.setFontScale(0.7f);
    mainGroup.addActor(moneyTxt);
    moneyTxt.setPosition(frameMoney.getX() + frameMoney.getWidth()/2, frameMoney.getY() + frameMoney.getHeight()/2, Align.center);
    eventBtnPause(btnPause);
  }

  private void initAdsPanel(){
    adsPanel = new AdsPanel(atlasAds, this);
  }

  private void eventBtnPause(Image btn){
    btn.setOrigin(Align.center);
    btn.addListener(new ClickListener(){
      @Override
      public void clicked(InputEvent event, float x, float y) {
      super.clicked(event, x, y);
      btnPause.setTouchable(Touchable.disabled);
      SoundEffect.Play(SoundEffect.click);
      btn.addAction(Actions.sequence(
        Actions.scaleTo(0.8f,0.8f,0.1f),
        Actions.scaleTo(1f,1f,0.1f),
        GSimpleAction.simpleAction((d, a)->{
          new Setting(atlas, btn, GameScene.this);
          return true;
        })
      ));
      }
    });
  }

  public void updateMoneyTxt(){
    moneyTxt.setText(FortmartPrice(money));
  }

  private void initBitmapfont(){
    font = GAssetsManager.getBitmapFont("moneyFont.fnt");
    font2 = GAssetsManager.getBitmapFont("gold.fnt");
    font3 = GAssetsManager.getBitmapFont("font_name_bot.fnt");
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

  public void checkMoneyToShowAdPanel(){

    if(money <= 0){
      //show thong bao xem quang cao de nhan tien
      System.out.println("het tien goi, xem video lay tien thuong ne");
      adsPanel.showPanel();
    }
  }

  private String converMoneyToStr(){
    String str = "";
    if(money < 1000){
      str = money + "";
    }
    else if(money < 1000000){
      str = (float)money/1000 + "K";
    }
    else if(money < 1000000000){
      str = (float)money/1000000 + "M";
    }
    else {
      str = (float)money/1000000000 + "B";
    }
    return str;
  }

  private String FortmartPrice(Long Price) {

    DecimalFormat mDecimalFormat = new DecimalFormat("###,###,###,###");
    String mPrice = mDecimalFormat.format(Price);

    return mPrice;
  }

  @Override
  public void run() {

  }
}