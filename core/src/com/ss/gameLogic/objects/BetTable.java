package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;
import com.ss.gameLogic.StaticObjects.Config;

public class BetTable {
  private TextureAtlas atlas, atlasChip;
  private GLayerGroup group;
  private Array<ElementBetTable> element;
  private Array<Image> chipImg;
  private Image img500, img200, img100, img50, img20, img10;
  private int isChoosing = 0;
  private Boolean isBlockTouch = false, isBlockTouchE = false;
  private BitmapFont font;



  public BetTable(TextureAtlas atlas, TextureAtlas atlasChip, GLayerGroup group, BitmapFont font){
    this.atlas = atlas;
    this.atlasChip = atlasChip;
    this.group = group;
    this.font = font;
    renderUI();
  }

  private void renderUI(){
    element = new Array<>();
    ElementBetTable chan = new ElementBetTable(atlas, atlasChip, group, 0, font);
    ElementBetTable le = new ElementBetTable(atlas, atlasChip, group, 1, font);
    ElementBetTable tuDo = new ElementBetTable(atlas, atlasChip, group, 2, font);
    ElementBetTable tuTrang = new ElementBetTable(atlas, atlasChip, group, 3, font);
    ElementBetTable leDo = new ElementBetTable(atlas, atlasChip, group, 4, font);
    ElementBetTable leTrang = new ElementBetTable(atlas, atlasChip, group, 5, font);
    element.add(chan, le, tuDo);
    element.add(tuTrang, leDo, leTrang);

    chan.setPosition(Config.WidthScreen*0.31f, Config.HeightScreen*0.42f);
    chan.setPoMoneyTxt(Config.WidthScreen*0.31f, Config.HeightScreen*0.378f);
    le.setPosition(Config.WidthScreen*0.68f, Config.HeightScreen*0.42f);
    le.setPoMoneyTxt(Config.WidthScreen*0.665f, Config.HeightScreen*0.378f);
    tuDo.setPosition(Config.WidthScreen*0.26f, Config.HeightScreen*0.68f);
    tuDo.setPoMoneyTxt(Config.WidthScreen*0.26f, Config.HeightScreen*0.615f);
    tuTrang.setPosition(Config.WidthScreen*0.42f, Config.HeightScreen*0.68f);
    tuTrang.setPoMoneyTxt(Config.WidthScreen*0.42f, Config.HeightScreen*0.615f);
    leTrang.setPosition(Config.WidthScreen*0.58f, Config.HeightScreen*0.68f);
    leTrang.setPoMoneyTxt(Config.WidthScreen*0.57f, Config.HeightScreen*0.615f);
    leDo.setPosition(Config.WidthScreen*0.73f, Config.HeightScreen*0.68f);
    leDo.setPoMoneyTxt(Config.WidthScreen*0.72f, Config.HeightScreen*0.615f);
  }



  private void renderImg(Image img, float x, float y){
    group.addActor(img);
    img.setPosition(x, y, Align.center);
  }

  private void renderMoneyChip(){
    chipImg = new Array<>();
    Image mnTest = GUI.createImage(atlas, "500");

//    renderMoneyChipImg(img500, "500", Config.WidthScreen/2 + Config.WidthTable/2 - mnTest.getWidth()/2, Config.tablePY + mnTest.getHeight()/2);
//    renderMoneyChipImg(img200, "200", Config.WidthScreen/2 + Config.WidthTable/2 - mnTest.getWidth()/2, Config.tablePY + 1.5f*mnTest.getHeight());
//    renderMoneyChipImg(img100, "100", Config.WidthScreen/2 + Config.WidthTable/2 - mnTest.getWidth()/2, Config.tablePY + 2.5f*mnTest.getHeight());
//    renderMoneyChipImg(img50, "50", Config.WidthScreen/2 + Config.WidthTable/2 - mnTest.getWidth()/2, Config.tablePY + 3.5f*mnTest.getHeight());
//    renderMoneyChipImg(img20, "20", Config.WidthScreen/2 + Config.WidthTable/2 - mnTest.getWidth()/2, Config.tablePY + 4.5f*mnTest.getHeight());
//    renderMoneyChipImg(img10, "10", Config.WidthScreen/2 + Config.WidthTable/2 - mnTest.getWidth()/2, Config.tablePY + 5.5f*mnTest.getHeight());
  }

  private void renderMoneyChipImg(Image img, String str, float x, float y){
    img = GUI.createImage(atlas, str);
    group.addActor(img);
    img.setPosition(x, y, Align.center);
    chipImg.add(img);
    if(str != "500")
      img.setColor(Color.GRAY);
    if(str == "10"){
      addEventMoneyChip();
    }
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

  private void addEventElementBetTable(){
//    chan.addListener(new ClickListener(){
//      @Override
//      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//        if(!isBlockTouchE){
//          isBlockTouchE = true;
//          chan.setColor(255, 255, 0, 0.3f);
//        }
//        return super.touchDown(event, x, y, pointer, button);
//      }
//
//      @Override
//      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//        super.touchUp(event, x, y, pointer, button);
//        isBlockTouchE = false;
//      }
//    });

  }
}
