package com.ss.gameLogic.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.GMain;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.StaticObjects.Config;

public class BowlDisk {
    private TextureAtlas atlas;
    private GLayerGroup group = new GLayerGroup();
    private BetTable betTable;
    private Group groupTimer = new Group();
    private Image Disk,Bowl,bgTimer,frmTimer,sclTimer;
    private Girl girl;
    private Array<Element> elements;
    private Image pointer;

    public BowlDisk(TextureAtlas atlas, Girl girl, BetTable betTable){
      this.girl =  girl;
      this.betTable = betTable;
      GStage.addToLayer(GLayer.top,group);
      GStage.addToLayer(GLayer.top,groupTimer);
      this.atlas = atlas;
      loadDiskBowl();
      loadTimer();
      initPointer();
    }
    private void loadDiskBowl(){
      Image shadow = GUI.createImage(atlas,"shadow");
      shadow.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-70, Align.center);
      group.addActor(shadow);
      Disk = GUI.createImage(atlas,"disk");
      Disk.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-70,Align.center);
      group.addActor(Disk);

      initElements();

      Bowl = GUI.createImage(atlas,"bowl");
      Bowl.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-78,Align.center);
      group.addActor(Bowl);
//        animationDiskBowl();
      eventBowlDisk(Disk,Bowl);
    }
    public void animationDiskBowl(){

      float duration = 0.15f;
      int move=15;
      int rotate = 20;
      Disk.setOrigin(Align.center);
      Bowl.setOrigin(Align.center);
      Disk.addAction(Actions.sequence(
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.moveBy(0,-move,duration,Interpolation.linear)
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,move,duration/2,Interpolation.linear)
          )
        ),
        Actions.delay(duration),
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.moveBy(0,-move,duration,Interpolation.linear)
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
        )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,move,duration/2,Interpolation.linear)
          )
        ),
        GSimpleAction.simpleAction((d,a)->{
//                    Bowl.setTouchable(Touchable.enabled);
//                    Disk.setTouchable(Touchable.enabled);
          girl.actionGirl1(5);
          groupTimer.setVisible(true);
          timerCountDown();
          return true;
        })


      ));

      Bowl.addAction(Actions.sequence(
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.moveBy(0,-move,duration,Interpolation.linear)
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,move,duration/2,Interpolation.linear)
          )
        ),
        Actions.delay(duration),
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.moveBy(0,-move,duration,Interpolation.linear)
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(-rotate,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,-move/2,duration/2,Interpolation.linear),
            Actions.moveBy(0,move/2,duration/2,Interpolation.linear)
          )
        ),
        Actions.parallel(
          Actions.rotateBy(rotate/2,duration, Interpolation.fastSlow),
          Actions.sequence(
            Actions.moveBy(0,move,duration/2,Interpolation.linear)
          )
        ),
        GSimpleAction.simpleAction((d, a)->{
          hiddenElements(false);
          return true;
        })
      ));
    }
    private void eventBowlDisk(Image disk, Image bowl){
        bowl.addListener(new ClickListener(){
          @Override
          public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            SoundEffect.Play(SoundEffect.shakeDisk);
            betTable.hiddenNoShape(false);
            disk.setTouchable(Touchable.disabled);
            bowl.setTouchable(Touchable.disabled);
            pointer.setVisible(false);
            shuffleElement();
            hiddenElements(true);
            setTimerBegin();
            animationDiskBowl();
          }
        });
        disk.addListener(new ClickListener(){
          @Override
          public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            SoundEffect.Play(SoundEffect.shakeDisk);
            betTable.hiddenNoShape(false);
            disk.setTouchable(Touchable.disabled);
            bowl.setTouchable(Touchable.disabled);
            pointer.setVisible(false);
            shuffleElement();
            hiddenElements(true);
            setTimerBegin();
            animationDiskBowl();
          }
        });
    }
    public void BowlUp(){
      Image textMoDia = GUI.createImage(atlas,"modia");
      textMoDia.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-50,Align.center);
      group.addActor(textMoDia);
      textMoDia.setScale(0);
      textMoDia.setOrigin(Align.center);
      textMoDia.addAction(Actions.sequence(
        Actions.parallel(
        Actions.scaleTo(2,2,1f),
        Actions.alpha(0,1)),
        GSimpleAction.simpleAction((d,a)->{
          textMoDia.remove();
          return true;
        })
      ));

      Bowl.addAction(
        Actions.sequence(
          Actions.moveBy(0,-500,0.5f),
          GSimpleAction.simpleAction((d, a)->{
            Bowl.setTouchable(Touchable.disabled);
            Disk.setTouchable(Touchable.disabled);
            Tweens.setTimeout(group,2f,()->{
              BowlDown();
            });
            return true;
          })
        )
      );
    }
    public void BowlDown(){
      Bowl.addAction(
        Actions.sequence(
          Actions.moveBy(0,500,0.5f),
          GSimpleAction.simpleAction((d, a)->{
            Bowl.setTouchable(Touchable.enabled);
            Disk.setTouchable(Touchable.enabled);
            pointer.setVisible(true);

            //show fullscreenAds
            Config.indexShowFullScreen++;
            if(Config.indexShowFullScreen == Config.countShowFullScreen){
              Config.indexShowFullScreen = 1;
              GMain.platform.ShowFullscreen();
            }
            return true;
          })
        )
      );
    }
    private void loadTimer(){
      //// bgTimer////
      bgTimer = GUI.createImage(atlas,"bgTimer");
      bgTimer.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,Align.center);
      groupTimer.addActor(bgTimer);
      //// sclTimer////
      sclTimer = GUI.createImage(atlas,"sclTimer");
      sclTimer.setPosition(GStage.getWorldWidth()/2+4,GStage.getWorldHeight()/2,Align.center);
      groupTimer.addActor(sclTimer);
      //// frmTimer////
      frmTimer = GUI.createImage(atlas,"frmTimer");
      frmTimer.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,Align.center);
      groupTimer.addActor(frmTimer);
      //// text /////
      Image text = GUI.createImage(atlas,"chodatcuoc");
      text.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,Align.center);
      groupTimer.addActor(text);
      groupTimer.setVisible(false);
    }

    private void initPointer(){
      pointer = GUI.createImage(atlas, "pointer");
      group.addActor(pointer);
      pointer.setPosition(Config.WidthScreen/2 - 2*pointer.getWidth(), Config.HeightScreen*0.4f, Align.center);
      pointerAction();
    }

    private void pointerAction(){
      pointer.addAction(Actions.sequence(
        Actions.moveBy(pointer.getWidth(), 0, 0.5f, Interpolation.slowFast),
        Actions.moveBy(-pointer.getWidth(), 0, 0.5f, Interpolation.fastSlow),
        GSimpleAction.simpleAction((d, a)->{
          pointerAction();
          return true;
        })
      ));
    }

    private void timerCountDown(){
      sclTimer.addAction(Actions.sequence(
        Actions.scaleTo(0,1,Config.TimeDown,Interpolation.linear),
        GSimpleAction.simpleAction((d,a)->{
          SoundEffect.Play(SoundEffect.openDisk);
          BowlUp();
          betTable.turnOnSignal(result());
          groupTimer.setVisible(false);
          System.out.println("het thoi gian!!");
          betTable.hiddenNoShape(true);
          return true;
        })
      ));
    }
    private void setTimerBegin(){
        sclTimer.setScaleX(1);
    }

    //elements
    private void initElements(){
      elements = new Array<>();
      Element e1 = new Element(atlas, group);
      Element e2 = new Element(atlas, group);
      Element e3 = new Element(atlas, group);
      Element e4 = new Element(atlas, group);
      elements.add(e1, e2, e3, e4);
      e1.setPosition(Config.WidthScreen/2 - e1.getWidth(), Config.HeightScreen*0.385f);
      e2.setPosition(Config.WidthScreen/2 + e2.getWidth(), Config.HeightScreen*0.385f);
      e3.setPosition(Config.WidthScreen/2 - e3.getWidth(), Config.HeightScreen*0.385f + e3.getHeight());
      e4.setPosition(Config.WidthScreen/2 + e4.getWidth(), Config.HeightScreen*0.385f + e4.getHeight());
    }

    private void hiddenElements(boolean isHidden){
      for(Element e : elements){
        e.hiddenElement(isHidden);
      }
    }

    private void shuffleElement(){
      for(Element e: elements){
        e.shuffle();
      }
    }

    public Vector2 result(){
      Vector2 rs;
      float x = 0, y = 0;
      for(Element e : elements){
        if(e.getStatus()){
          x++;
        }
        else y++;
      }

      rs = new Vector2(x, y);
      return rs;
    }
}
