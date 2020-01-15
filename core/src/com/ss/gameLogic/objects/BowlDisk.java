package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;

public class BowlDisk {
    private TextureAtlas atlas;
    private Group group = new Group();
    private Group groupTimer = new Group();
    private Image Disk,Bowl,bgTimer,frmTimer,sclTimer;
    private Girl girl;
    public BowlDisk(TextureAtlas atlas, Girl girl){
        this.girl =  girl;
        GStage.addToLayer(GLayer.top,group);
        GStage.addToLayer(GLayer.top,groupTimer);
        this.atlas = atlas;
        loadDiskBowl();
        loadTimer();

    }
    private void loadDiskBowl(){
        Image shadow = GUI.createImage(atlas,"shadow");
        shadow.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-70, Align.center);
        group.addActor(shadow);
        Disk = GUI.createImage(atlas,"disk");
        Disk.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2-70,Align.center);
        group.addActor(Disk);
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
                )


        ));
    }
    private void eventBowlDisk(Image disk, Image bowl){
        bowl.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                disk.setTouchable(Touchable.disabled);
                bowl.setTouchable(Touchable.disabled);
                setTimerBegin();
                animationDiskBowl();
            }
        });
        disk.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                disk.setTouchable(Touchable.disabled);
                bowl.setTouchable(Touchable.disabled);
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
    private void timerCountDown(){
        sclTimer.addAction(Actions.sequence(
                Actions.scaleTo(0,1,15,Interpolation.linear),
                GSimpleAction.simpleAction((d,a)->{
                    BowlUp();
                    groupTimer.setVisible(false);
                    System.out.println("het thoi gian!!");
                    return true;
                })
        ));
    }
    private void setTimerBegin(){
        sclTimer.setScaleX(1);
    }

}
