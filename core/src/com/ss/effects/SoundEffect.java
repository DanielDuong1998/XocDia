package com.ss.effects;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.ss.core.util.GAssetsManager;

/* renamed from: com.ss.effect.SoundEffect */
public class SoundEffect {
    public static int MAX_COMMON = 6;
    public static int MAX_MUSIC = 2;
    public static Music bgSound = null;
    public static Music bgSound2 = null;
    public static Sound[] commons = null;
    public static Music[] Music = null;
    public static boolean music = false;
    public static boolean music2 = false;
    public static boolean mute = false;

    public  static int click = 0;
    public  static int chooseChip = 1;
    public  static int openDisk = 2;
    public  static int plusMoney = 3;
    public  static int shakeDisk = 4;
    public  static int slideChip = 5;

    public static int bg0 = 0;
    public static int bg1 = 1;

    public static Audio bg = null;

    public static void initSound() {
        commons = new Sound[MAX_COMMON];
        Music = new Music[MAX_MUSIC];
        commons[click] = GAssetsManager.getSound("click.mp3");
        commons[chooseChip] = GAssetsManager.getSound("ChooseChip.mp3");
        commons[openDisk] = GAssetsManager.getSound("OpenDisk.mp3");
        commons[plusMoney] = GAssetsManager.getSound("PlusMoney.mp3");
        commons[shakeDisk] = GAssetsManager.getSound("ShakeDisk.mp3");
        commons[slideChip] = GAssetsManager.getSound("SlideChip.mp3");
//        commons[lose] = GAssetsManager.getSound("lose.mp3");
//        commons[win] = GAssetsManager.getSound("win.mp3");
//        commons[PannelOut] = GAssetsManager.getSound("Panel out.mp3");
//        commons[PannelIn] = GAssetsManager.getSound("Panel in.mp3");
//        commons[WinSpecial] = GAssetsManager.getSound("winSpecial.mp3");
        Music[bg0] = GAssetsManager.getMusic("bgSound.mp3");
        Music[bg1] = GAssetsManager.getMusic("bgSound1.mp3");


    }

    public static void Play(int i) {
        if (!mute) {
            commons[i].play();
        }
    }

    public static void Playmusic(int i) {
        Music[i].play();
        Music[i].setLooping(true);
        Music[i].setVolume(5);

    }

    public static void Stopmusic(int i){
        Music[i].stop();
    }
    public static void Pausemusic(int i){
        Music[i].pause();
    }
}
