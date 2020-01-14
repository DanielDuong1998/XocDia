package com.ss.effects;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.ss.core.util.GAssetsManager;

/* renamed from: com.ss.effect.SoundEffect */
public class SoundEffect {
  public static int MAX_COMMON = 5;
  public static Music bgSound = null;
  public static Music bgSound2 = null;
  public static Music bgSound3 = null;
  public static Sound[] commons = null;
  public static boolean music = false;
  public static boolean mute = false;
  public static int click = 0;
  public static int broken = 1;
  public static int slideBall = 2;
  public static int lose = 3;
  public static int tick = 4;





  public static void initSound() {
    commons = new Sound[MAX_COMMON];
    commons[click] = GAssetsManager.getSound("click.mp3");
    commons[broken] = GAssetsManager.getSound("broken.mp3");
    commons[slideBall] = GAssetsManager.getSound("slideBall.mp3");
    commons[lose] = GAssetsManager.getSound("loseGame.mp3");
    commons[tick] = GAssetsManager.getSound("tick.mp3");

//        commons[coins] = GAssetsManager.getSound("Coin.mp3");
//        commons[coins].setVolume(2,5);
    bgSound = GAssetsManager.getMusic("soundBg.mp3");
    bgSound2 = GAssetsManager.getMusic("soundBg2.mp3");
    bgSound3 = GAssetsManager.getMusic("soundBg3.mp3");

  }

  public static void Play(int i) {
    if (!mute) {
      commons[i].play();
    }
  }

  public static void Playmusic(int mode) {
    music = false;
    switch (mode) {
      case 1: {
        bgSound.play();
        bgSound.setLooping(true);
        bgSound.setVolume(0.2f);
        break;
      }
      case 2: {
        bgSound2.play();
        bgSound2.setLooping(true);
        bgSound2.setVolume(0.2f);
        break;
      }
      case 3: {
        bgSound3.play();
        bgSound3.setLooping(true);
        bgSound3.setVolume(0.2f);
        break;
      }
      default:{
        bgSound.play();
        bgSound.setLooping(true);
        bgSound.setVolume(0.2f);
        break;
      }
    }
  }

  public static void Stopmusic(int mode) {
    music = true;
    switch (mode){
      case 1: {
        bgSound.stop();
        break;
      }
      case 2: {
        bgSound2.stop();
        break;
      }
      case 3: {
        bgSound3.pause();
        break;
      }
      default:{
        bgSound.stop();
        break;
      }
    }
  }
}
