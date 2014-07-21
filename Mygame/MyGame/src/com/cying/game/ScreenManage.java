package com.cying.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by cying on 14-7-16.
 */
public class ScreenManage extends Game {

    private Screen screen;

    @Override
    public void create() {
        setScreen(screen);
    }

    public ScreenManage(Screen screen){
        super();
        this.screen = screen;
    }

    @Override
    public void resume(){
        super.resume();
    }

}
