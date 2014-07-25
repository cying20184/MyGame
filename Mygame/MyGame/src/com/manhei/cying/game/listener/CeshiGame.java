package com.manhei.cying.game.listener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class CeshiGame extends Game {
    private Screen screen;
    @Override
    public void create() {
        // TODO Auto-generated method stub
        
    }

    public CeshiGame(Screen screen){
        super();
        this.screen = screen;
    }
    @Override
    public void resume() {
        // TODO Auto-generated method stub
        super.resume();
    }
    public CeshiGame getGame(){
        return this;
    }
}
