package com.cying.game;

import sun.nio.cs.ext.TIS_620;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.corba.se.spi.orbutil.fsm.State;

/**
 * Created by cying on 14-7-16.
 */
public class MyActor extends Actor {
//    public static float x;
//    public static float y;
    
    double zFlor = 0;
    double yFlor = 0;
    double xFlor = 0;
    public float statetime;
    
    private Texture texture;
    private TextureRegion textureRegion;
    
    private Animation animationDown;
    private Animation animationUp;
    private Animation animationLeft;
    private Animation animationRight;
    private Animation animationStop;
    STATE state;
    //行走状态
    //enum枚举，主要定义演员现在的状态
    public enum STATE{
        Left,Right,Up,Down,Stop
    }
    
    public MyActor(float x,float y){
        this.setX(x);
        this.setY(y);
        this.statetime = 0;
        this.AnimationChange();
//        texture = new Texture(Gdx.files.internal("data/01.jpg"));
//        textureRegion = new TextureRegion(texture, 0, 0, 480,
//                320);
        state = STATE.Stop;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        statetime += Gdx.graphics.getDeltaTime();
        this.update();
        this.check();
        batch.draw(textureRegion, getX(), getY());
    }

    //更新演员现在的位置
    public void update() {
            if (this.getX() <= 0) {
                this.setX(0);
            }
            if ((this.getX() - 75)>= Gdx.graphics.getWidth()) {
                this.setX(Gdx.graphics.getWidth());
            }
            if ((this.getY()  -75) >= Gdx.graphics.getHeight()) {
                this.setY(Gdx.graphics.getHeight());
            }
            if (this.getY() <= 0) {
                this.setY(0);
            }
        

    }

    public void check(){
        if (state == STATE.Down) {
            textureRegion = animationDown.getKeyFrame(statetime,true);
        }else if(state == STATE.Stop){
            textureRegion = animationStop.getKeyFrame(statetime, true);
        }else if(state == STATE.Up){
            textureRegion = animationUp.getKeyFrame(statetime, true);
        }else if(state == STATE.Left){
            textureRegion = animationLeft.getKeyFrame(statetime, true);
        }else if(state == STATE.Right){
            textureRegion = animationRight.getKeyFrame(statetime, true);
        }
        
        if (textureRegion == null) {
            System.out.println("textureRegion is null");
        }
    }
    
    public void AnimationChange(){
        texture = new Texture(Gdx.files.internal("data/a.png"));
        TextureRegion[][] spilt = TextureRegion.split(texture, 103, 103);
        TextureRegion[] downRegions = new TextureRegion[7];
        downRegions[0] = spilt[0][1];
        downRegions[1] = spilt[0][2];
        downRegions[2] = spilt[0][3];
        downRegions[3] = spilt[0][4];
        downRegions[4] = spilt[0][5];
        downRegions[5] = spilt[0][6];
        downRegions[6] = spilt[0][0];
        animationDown = new Animation(0.1f, downRegions);
        TextureRegion[] upRegions = new TextureRegion[7];
        upRegions[0] = spilt[1][1];
        upRegions[1] = spilt[1][2];
        upRegions[2] = spilt[1][3];
        upRegions[3] = spilt[1][4];
        upRegions[4] = spilt[1][5];
        upRegions[5] = spilt[1][6];
        upRegions[6] = spilt[1][0];
        animationUp = new Animation(0.1f, upRegions);
        TextureRegion[] leftRegions = new TextureRegion[7];
        leftRegions[0] = spilt[2][1];
        leftRegions[1] = spilt[2][2];
        leftRegions[2] = spilt[2][3];
        leftRegions[3] = spilt[2][4];
        leftRegions[4] = spilt[2][5];
        leftRegions[5] = spilt[2][6];
        leftRegions[6] = spilt[2][0];
        animationLeft = new Animation(0.1f, leftRegions);
        TextureRegion[] rightRegions = new TextureRegion[7];
        rightRegions[0] = spilt[3][1];
        rightRegions[1] = spilt[3][2];
        rightRegions[2] = spilt[3][3];
        rightRegions[3] = spilt[3][4];
        rightRegions[4] = spilt[3][5];
        rightRegions[5] = spilt[3][6];
        rightRegions[6] = spilt[3][0];
        animationRight = new Animation(0.1f, rightRegions);
        TextureRegion[] stopRegions = new TextureRegion[1];
        stopRegions[0] = spilt[0][0];
        animationStop = new Animation(0.1f, stopRegions);
    }

    public void setState(STATE state){
        this.state = state;
    }
    
    public void setDistance(float x,float y){
        xFlor = x -  this.getX();
        yFlor = y - this.getY();
        zFlor = Math.sqrt(Math.pow(xFlor, 2) + Math.pow(yFlor, 2));
    }
    
    public void moveToPoint(){
        
        this.setX((float) (this.getX() + (xFlor*5f)/zFlor));
        this.setY((float) (this.getY()+(yFlor*5f)/zFlor));
    }
    
    public double getXFlor(){
        return xFlor;
    }
}
