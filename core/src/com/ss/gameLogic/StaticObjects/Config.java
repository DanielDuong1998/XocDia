package com.ss.gameLogic.StaticObjects;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.util.GStage;
import com.ss.effects.SoundEffect;

public class Config {
  //constant
  public static float ratioX = GStage.getWorldWidth()/1280;
  public static float ratioY = GStage.getWorldHeight()/720;
  public static float WidthScreen = GStage.getWorldWidth();
  public static float HeightScreen = GStage.getWorldHeight();

  //can change

  public static int idChip = 7; // todo: use in ChipSmall.java
  public static int countShowFullScreen = 4;
  public static int indexShowFullScreen = 0;
  public static int TimeDown = 8;
  public static int indexMusic = 0;

  public static void effectBtn(Image btn, Runnable runnable){
    btn.setOrigin(Align.center);
    SoundEffect.Play(SoundEffect.click);
    btn.addAction(Actions.sequence(
      Actions.scaleBy(-0.5f, -0.5f, 0.08f, Interpolation.swingIn),
      Actions.scaleBy(0.5f, 0.5f, 0.08f, Interpolation.swingOut),
      Actions.run(runnable)
    ));
  }
}
