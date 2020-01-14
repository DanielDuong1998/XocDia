package com.ss.gameLogic.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;

public class Chip {
  TextureAtlas atlas;
  GLayerGroup group;
  Image shape;
  int value;

  public Chip(TextureAtlas atlas, GLayerGroup group, int value){
    this.atlas = atlas;
    this.group = group;
    this.value = value;
    renderChip();
  }

  private void renderChip(){
    shape = GUI.createImage(atlas, value + "");
    group.addActor(shape);
  }

  public void setPosition(float x, float y){
    shape.setPosition(x, y, Align.center);
  }
}
