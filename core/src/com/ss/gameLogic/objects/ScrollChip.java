package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.effects.SoundEffect;
import com.ss.gameLogic.StaticObjects.Config;

public class ScrollChip {
    private TextureAtlas atlas;
    private Group groupchip = new Group();
    private Table container;
    private Group group;
    private Array<Image> arrChip = new Array<>();
    private Array<Image> arrChipL = new Array<>();
    private ScrollPane scroll1;
    private int index=0;
    private int indexOld=0;

    public ScrollChip(TextureAtlas atlas, Group group){
        this.group = group;
        GStage.addToLayer(GLayer.top,groupchip);
        this.atlas = atlas;
        loadListChip();
        setDefault();
        eventChip();
        loadButton();
        animationAlpha();
    }
    private void loadListChip(){
        groupchip.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,Align.center);
        groupchip.setWidth(1200);
        groupchip.setHeight(300);
        groupchip.setOrigin(Align.center);
        int key=100;
        float chipW =0;
        float paddingX = 30;
        for (int i=0;i<9;i++){
            if(i!=0){
                if(i%2==0){
                    key = key*2;

                }else {
                    key = key*5;
                }
            }
            Image chip = GUI.createImage(atlas,""+key);
            chip.setPosition(chip.getWidth()/2+20 +(chip.getWidth()+paddingX)*i,chip.getHeight(),Align.center);
            groupchip.addActor(chip);
            chipW = chip.getWidth();
            arrChip.add(chip);
            //////// chip light /////
            Image chipL = GUI.createImage(atlas,""+key+"L");
            chipL.setPosition(chip.getX() + chip.getWidth()/2,chip.getY() + chip.getHeight()/2,Align.center);
            groupchip.addActor(chipL);
            arrChipL.add(chipL);
        }
        container = new Table();
        container.setSize(730, 300);
        container.setPosition(GStage.getWorldWidth()/2 - (chipW*7)/2,GStage.getWorldHeight()-150);
        group.addActor(container);
        Table table = new Table();
        scroll1 = new ScrollPane(table);
        container.add(scroll1);
        table.add(groupchip);
        table.row();
        System.out.println("W:"+chipW);
    }
    private void setDefault(){
        for (int i=0;i<arrChipL.size;i++){
            if(i!=0)
                arrChipL.get(i).setVisible(false);
            else{
                Config.idChip=i;
                arrChipL.get(i).addAction(Actions.moveBy(0,-30));
                arrChip.get(i).addAction(Actions.moveBy(0,-30));
            }
        }
    }
    private void eventChip(){
        for (int i=0;i<arrChip.size;i++){
            int finalI = i;
            arrChip.get(i).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                SoundEffect.Play(SoundEffect.slideChip);
                setVisible(finalI);
                Config.idChip = finalI;
                index=finalI;

                }
            });
        }
    }
    private void setVisible(int index){
        for (int i=0;i<arrChipL.size;i++){      
            if(i==index){
                arrChipL.get(i).setVisible(true);
                if(indexOld!=i){
                    arrChipL.get(i).addAction(Actions.moveBy(0,-30,0.2f));
                    arrChip.get(i).addAction(Actions.moveBy(0,-30,0.2f));
                    arrChipL.get(indexOld).addAction(Actions.moveBy(0,30,0.2f));
                    arrChip.get(indexOld).addAction(Actions.moveBy(0,30,0.2f));
                    indexOld=i;

                }

            }
            else{
                arrChipL.get(i).setVisible(false);

            }
        }
    }
    private void loadButton(){
        /////// shiftLeft ////////
        Image btnLeft = GUI.createImage(atlas,"shiftLeft");
        btnLeft.setPosition(GStage.getWorldWidth()/2-btnLeft.getWidth()*7,GStage.getWorldHeight()-btnLeft.getHeight()/2-10,Align.center);
        group.addActor(btnLeft);
        btnLeft.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            SoundEffect.Play(SoundEffect.slideChip);
            scroll1.scrollTo(scroll1.getScrollX()-127,0,750,300);
            if(index>=0&&index<=8){
                if(index!=0)
                    index--;
                setVisible(index);
                Config.idChip = index;
            }

            }
        });
        /////// shiftRight //////
        Image btnRight = GUI.createImage(atlas,"shiftRight");
        btnRight.setPosition(GStage.getWorldWidth()/2+btnRight.getWidth()*8,GStage.getWorldHeight()-btnRight.getHeight()/2-10,Align.center);
        group.addActor(btnRight);
        btnRight.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            SoundEffect.Play(SoundEffect.slideChip);
            scroll1.scrollTo(scroll1.getScrollX()+127,0,750,300);
            if(index>=0&&index<=8){
                if(index!=8)
                    index++;
                setVisible(index);
                Config.idChip=index;
            }
            }
        });
    }
    private void animationAlpha(){
        for (int i=0;i<arrChipL.size;i++){
            if(arrChipL.get(i).isVisible()==true){
                arrChipL.get(i).addAction(Actions.sequence(
                    Actions.alpha(0,0.5f),
                    Actions.alpha(1,0.5f),
                    GSimpleAction.simpleAction((d,a)->{
                        animationAlpha();
                        return true;
                    })
                ));
            }

        }
    }


}
