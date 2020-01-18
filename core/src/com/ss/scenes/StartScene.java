package com.ss.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.StaticObjects.Config;

import java.text.DecimalFormat;

public class StartScene extends GScreen {
    private TextureAtlas atlas;
    private BitmapFont font;
    private long monney = GMain.prefs.getLong("money");
    private Group group = new Group();
    @Override
    public void dispose() {

    }

    @Override
    public void init() {

        initAtlas();
        initFont();
        showBg();
        showFrmAvt();
        if(SoundEffect.music!=true)
            SoundEffect.Playmusic(Config.indexMusic);
        if(monney <= 0){
            //show thong bao nhan xem quan cao de nhan tien
        }
    }

    @Override
    public void run() {

    }
    private void showBg(){
        GStage.addToLayer(GLayer.ui,group);
        Image bg = GUI.createImage(atlas,"bgStart");
        bg.setWidth(bg.getWidth()* Config.ratioX);
        bg.setHeight(bg.getHeight()*Config.ratioY);
        group.addActor(bg);
        //// btn start/////
        Image btnStart = GUI.createImage(atlas,"btnPlay");
        btnStart.setPosition(GStage.getWorldWidth()/2+btnStart.getHeight()*2,GStage.getWorldHeight()/2-btnStart.getHeight()/2, Align.center);
        group.addActor(btnStart);
        ///// btn rank/////
        Image btnRank = GUI.createImage(atlas,"btnRank");
        btnRank.setPosition(GStage.getWorldWidth()/2+btnRank.getHeight()*2,GStage.getWorldHeight()/2+btnRank.getHeight()/2, Align.center);
        group.addActor(btnRank);
        ////// event btn /////
        eventBtnSatrt(btnStart);

    }
    private void showFrmAvt(){
        Image frmAvt = GUI.createImage(atlas,"frmAvt");
        frmAvt.setPosition(frmAvt.getWidth()/2,frmAvt.getHeight()/2,Align.center);
        group.addActor(frmAvt);
        Label LbMonney = new Label(""+FortmartPrice(monney),new Label.LabelStyle(font,null));
        LbMonney.setPosition(frmAvt.getX()+frmAvt.getWidth()/2+50,frmAvt.getY()+frmAvt.getHeight()/2,Align.center);
        LbMonney.setOrigin(Align.center);
        LbMonney.setAlignment(Align.center);
        LbMonney.setFontScale(0.8f, 0.8f);
        group.addActor(LbMonney);
    }
    private void eventBtnSatrt(Image btn){
        btn.setOrigin(Align.center);
        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            btn.setTouchable(Touchable.disabled);
            SoundEffect.Play(SoundEffect.click);
            btn.addAction(Actions.sequence(
                Actions.scaleTo(0.8f,0.8f,0.1f),
                Actions.scaleTo(1f,1f,0.1f)
            ));
            Tweens.setTimeout(group,0.2f,()->{
                setScreen(new GameScene());
            });
            }
        });
    }
    private void initAtlas(){
        atlas = GAssetsManager.getTextureAtlas("PlayScene.atlas");
    }
    private void initFont(){
        font = GAssetsManager.getBitmapFont("gold.fnt");

    }
    private String FortmartPrice(Long Price) {

        DecimalFormat mDecimalFormat = new DecimalFormat("###,###,###,###");
        String mPrice = mDecimalFormat.format(Price);

        return mPrice;
    }

}
