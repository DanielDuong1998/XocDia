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
  public static float duaration = 5;
  public static float velocity = 500;
  public static float[] scaleTime = new float[]{1000, 1};
  public static int modeSelecting = 1; //1 -> easy, 2 -> medium, 3 -> hard
  public static int idChip = 1; // todo: use in ChipSmall.java



  //method
  public static void reset(){
    duaration = 5;
  }

  public static void setDuaration(float dua){
    duaration = dua;
  }

  public static void setVelocity(float v){
    velocity = v;
  }

  public static float module(Vector2 p1, Vector2 p2){
    return (float) Math.sqrt((p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));
  }

  public static void effectBtn(Image btn, Runnable runnable){
    btn.setOrigin(Align.center);
    //SoundEffect.Play(SoundEffect.click);
    btn.addAction(Actions.sequence(
      Actions.scaleBy(-0.5f, -0.5f, 0.08f, Interpolation.swingIn),
      Actions.scaleBy(0.5f, 0.5f, 0.08f, Interpolation.swingOut),
      Actions.run(runnable)
    ));
  }

}
