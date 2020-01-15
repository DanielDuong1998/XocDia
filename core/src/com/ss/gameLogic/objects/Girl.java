package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.gameLogic.StaticObjects.Config;

public class Girl {
  private TextureAtlas atlas;
  private Group group;
  private Image body, closeEyes;
  private Array<Image> hands;
  private Image chatBox;

  public Girl(TextureAtlas atlas){
    this.atlas = atlas;
    initGroup();
    initShape();
    //actionGirl1(5);
    setPoCloseEyes();
    blinkEyes();
  }


  private void initGroup(){
    group = new Group();
    GStage.addToLayer(GLayer.ui, group);
  }

  private void initShape(){
    body = GUI.createImage(atlas, "body");
    closeEyes = GUI.createImage(atlas, "closeEyes");
    chatBox = GUI.createImage(atlas, "chatBox");
    group.addActor(body);
    group.addActor(closeEyes);
    group.addActor(chatBox);

    closeEyes.setVisible(false);

    chatBox.setX(-body.getWidth()*1.7f/2);
    chatBox.setVisible(false);

    hands = new Array<>();
    for(int i = 1; i <= 6; i++ ){
      Image hand = GUI.createImage(atlas, "h" + i);
      group.addActor(hand);
      hands.add(hand);
    }
    setPoHands();

    group.setPosition(Config.WidthScreen/2 - body.getWidth()/2, Config.HeightScreen*0.18f - body.getHeight()/2);
  }

  private void setPoCloseEyes(){
    closeEyes.setPosition(body.getWidth()*0.503f, body.getHeight()*0.225f, Align.center);

//    body.addListener(new ClickListener(){
//      @Override
//      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//        actionGirl1(5);
//        return super.touchDown(event, x, y, pointer, button);
//      }
//
//      @Override
//      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//        super.touchUp(event, x, y, pointer, button);
//        closeEyes.setVisible(false);
//      }
//    });
  }

  private void blinkEyes(){
    int dua = (int)Math.floor(Math.random()*3 + 2);
    closeEyes.setVisible(true);
    closeEyes.addAction(Actions.sequence(
      Actions.delay(0.1f),
      GSimpleAction.simpleAction((d, a)->{
        closeEyes.setVisible(false);
        Tweens.setTimeout(group, dua, ()->{
          blinkEyes();
        });
        return true;
      })
    ));
  }

  private void setPoHands(){
    hands.get(0).setY(body.getHeight()*0.42f);
    hands.get(0).setVisible(false);
    hands.get(1).setY(body.getHeight()*0.45f);
    hands.get(1).setVisible(false);
    hands.get(2).setY(body.getHeight()*0.52f);
    hands.get(2).setVisible(false);
    hands.get(3).setY(body.getHeight()*0.62f);
    hands.get(3).setVisible(false);
    hands.get(4).setY(body.getHeight()*0.72f);
    hands.get(4).setVisible(false);
    hands.get(5).setY(body.getHeight()*0.78f);
    hands.get(5).setVisible(true);
  }

  public void actionGirl1(int index){
    if(index > 0){
      hands.get(index).setVisible(true);
      Tweens.setTimeout(group, 0.08f, ()->{
        hands.get(index).setVisible(false);
        actionGirl1(index-1);
      });
    }
    if(index == 0){
      hands.get(index).setVisible(true);
      chatBox.setVisible(true);
      Tweens.setTimeout(group, 1f, ()->{
        hands.get(index).setVisible(false);
        chatBox.setVisible(false);
        actionGirl2(1, 5);
      });
    }
  }

  public void actionGirl2(int index, int total){
    if(index < total){
      hands.get(index).setVisible(true);
      Tweens.setTimeout(group, 0.08f,()->{
        hands.get(index).setVisible(false);
        actionGirl2(index+1, total);
      });
    }
    if(index == total){
      hands.get(index).setVisible(true);
    }
  }


}
