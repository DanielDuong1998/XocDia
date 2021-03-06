package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.platform.ToggleHandler;
import com.ss.commons.Tweens;
import com.ss.commons._ToggleButton;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.transitions.GTransitionSlice;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.StaticObjects.Config;
import com.ss.scenes.GameScene;
import com.ss.scenes.StartScene;

public class Setting implements  ToggleHandler {
    TextureAtlas atlas;
    BitmapFont font;
    Group group = new Group();
    Image btnSetting;
    GameScene gamePlay;
    public Setting(TextureAtlas atlas, Image setting,GameScene gamePlay){
        this.gamePlay = gamePlay;
        this.btnSetting = setting;
        this.atlas = atlas;
        GStage.addToLayer(GLayer.top,group);
        showframeSetting();
    }
    void showframeSetting(){
      //  SoundEffect.Play(SoundEffect.PannelOut);
        group.setPosition(-500,GStage.getWorldHeight()/2);
        Image frame = GUI.createImage(atlas,"frmSetting");
        frame.setPosition(0,0, Align.center);
        group.addActor(frame);
        group.addAction(Actions.moveTo(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,0.5f,Interpolation.swingOut));
        ////// btn close//////
        Image btnClose = GUI.createImage(atlas,"btnClose");
        btnClose.setPosition(   frame.getWidth()/2-btnClose.getWidth()/2-5,-frame.getHeight()/2+btnClose.getHeight()/2+3,Align.center);
        group.addActor(btnClose);
        eventbtnClose(btnClose);
        //////// btn Exit /////
        Image btnExit = GUI.createImage(atlas,"btnExit");
        btnExit.setPosition(0,170,Align.center);
        group.addActor(btnExit);
        eventbtnExit(btnExit);
        ////////// btn TurnOn /////////
        Image btnTurnOnSound = GUI.createImage(atlas,"turnOn");
        btnTurnOnSound.setPosition(150,-45,Align.center);
        group.addActor(btnTurnOnSound);
        ///////// btn TurnOff ///////
        Image btnTurnOffSound = GUI.createImage(atlas,"turnOff");
        btnTurnOffSound.setPosition(150,-45,Align.center);
        group.addActor(btnTurnOffSound);
        if(SoundEffect.mute==false){
            btnTurnOffSound.setVisible(false);
        }else {
            btnTurnOnSound.setVisible(false);
        }
        new _ToggleButton(btnTurnOnSound,btnTurnOffSound,"sound",this);
        ////////////////// music///////////////////
        ////////// btn TurnOn /////////
        Image btnTurnOnMusic = GUI.createImage(atlas,"turnOn");
        btnTurnOnMusic.setPosition(150,50,Align.center);
        group.addActor(btnTurnOnMusic);
        ///////// btn TurnOff ///////
        Image btnTurnOffMusic = GUI.createImage(atlas,"turnOff");
        btnTurnOffMusic.setPosition(150,50,Align.center);
        group.addActor(btnTurnOffMusic);
        if(SoundEffect.music==false){
            btnTurnOffMusic.setVisible(false);
        }else {
            btnTurnOnMusic.setVisible(false);
        }
        new _ToggleButton(btnTurnOnMusic,btnTurnOffMusic,"music",this);

    }
    void eventbtnClose(Image btn){
        btn.setOrigin(Align.center);
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            SoundEffect.Play(SoundEffect.click);
            btn.setTouchable(Touchable.disabled);
            btn.addAction(Actions.sequence(
                Actions.scaleTo(0.8f,0.8f,0.1f),
                Actions.scaleTo(1f,1f,0.1f)
            ));
            Tweens.setTimeout(group,0.2f,()->{
//                    SoundEffect.Play(SoundEffect.PannelIn);
                group.addAction(Actions.sequence(
                    Actions.moveTo(GStage.getWorldWidth()+500,GStage.getWorldHeight()/2,0.3f, Interpolation.swingOut),
                    GSimpleAction.simpleAction((d, a)->{
                        btnSetting.setTouchable(Touchable.enabled);
                        group.clear();
                        return true;
                    })

                ));

            });
            }
        });
    }
    void eventbtnExit(Image btn){
        btn.setOrigin(Align.center);
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            SoundEffect.Play(SoundEffect.click);
            SoundEffect.Stopmusic(Config.indexMusic);
            Config.indexMusic= SoundEffect.bg0;
            btn.setTouchable(Touchable.disabled);
            btn.addAction(Actions.sequence(
                Actions.scaleTo(0.8f,0.8f,0.1f),
                Actions.scaleTo(1f,1f,0.1f)
            ));
            Tweens.setTimeout(group,0.2f,()->{
                gamePlay.setScreen(new StartScene());
            });
            }
        });
    }

    @Override
    public void activeHandler(String str) {
        if(str=="sound"){
            SoundEffect.mute = true;
        }
        if(str=="music"){
            SoundEffect.Stopmusic(Config.indexMusic);
            SoundEffect.music = true;
        }

    }
    @Override
    public void deactiveHandler(String str) {
        if(str=="sound"){
            SoundEffect.mute = false;
        }
        if(str=="music"){
            SoundEffect.music = false;
            SoundEffect.Playmusic(Config.indexMusic);
        }
    }
}
