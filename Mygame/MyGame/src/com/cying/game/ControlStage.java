package com.cying.game;

import sun.rmi.runtime.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cying.game.MyActor.STATE;

/**
 * 控制舞台
 * 主要负责摇杆跟技能按钮
 * Created by cying on 14/7/16.
 */
public class ControlStage extends Stage{
    private Touchpad touchpad;
    private Texture texture;
    private TextureRegionDrawable touchbackgroud;
    private TextureRegionDrawable touchcenter;
    private Touchpad.TouchpadStyle style;
    private MyActor myActor;
    private float distanceTouchX = 0;
    private float distanceTouchY = 0;
    private boolean isRun = false;
    
    public ControlStage(){
        super();
        this.myActor = new MyActor(400, 400);
        this.init();
    }
    
    public ControlStage(MyActor myActor){
        super();
        this.init();
    }

    public void init(){
        texture = new Texture(Gdx.files.internal("data/touchpad.png"));
        touchbackgroud = new TextureRegionDrawable(new TextureRegion(texture,0,0,128,128));
        touchcenter = new TextureRegionDrawable(new TextureRegion(texture,128,0,128,128));
        style = new Touchpad.TouchpadStyle(touchbackgroud,touchcenter);
        //Touchpad(distance,style),distance：从触摸板的中心到周围所需的以像素为单位的距离
        //style:touchpadStyle,代表摇杆的样式，只要指背景图片和中心图片
        touchpad = new Touchpad(1,style);
        //设置touchpad中心位置以及长宽
        touchpad.setBounds(0,0,300,300);
        this.addActor(touchpad);
        this.addActor(myActor);
        setClick();
    }
    
    private void setClick(){
        this.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                    int pointer, int button) {
//                actorFace(x, y);
                isRun = true;
                distanceTouchX = x;
                distanceTouchY = y;
                myActor.setDistance(x, y);
                actorFace(x, y);
                return false;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y,
                    int pointer, int button) {
                // TODO Auto-generated method stub
                super.touchUp(event, x, y, pointer, button);
            }

          
            
        });
    }
    
    private void update(){
        if (touchpad.isTouched()) {         
                myActor.setX(myActor.getX() + touchpad.getKnobPercentX() * 5f);
                myActor.setY(myActor.getY() + touchpad.getKnobPercentY() * 5f);
                if (myActor.getX() <= 0) {
                    myActor.setX(0);
                }
                if (myActor.getX() +103>= Gdx.graphics.getWidth()) {
                    myActor.setX(Gdx.graphics.getWidth() -103);
                }
                if (myActor.getY() +103 >= Gdx.graphics.getHeight()) {
                    myActor.setY(Gdx.graphics.getHeight() - 103);
                }
                if (myActor.getY() <= 0) {
                    myActor.setY(0);
                }
                
                float x = touchpad.getKnobPercentX();
                float y = touchpad.getKnobPercentY();
                double d = y/x;
                if (x > 0) {
                    if(y >0){
                        if(d < Math.sin(45)){
                            myActor.setState(STATE.Right);
                        }else {
                            myActor.setState(STATE.Up);
                        }
                    }else {
                        if (d > -(Math.sin(45))) {
                            myActor.setState(STATE.Right);
                        }else {
                            myActor.setState(STATE.Down);
                        }
                    }
                }else {
                    if (y>0) {
                        if (d > -(Math.sin(45))) {
                            myActor.setState(STATE.Left);
                        }else {
                            myActor.setState(STATE.Up);
                        }
                    }else {
                        if(d < Math.sin(45)){
                            myActor.setState(STATE.Left);
                        }else {
                            myActor.setState(STATE.Down);
                        }
                    }
                }

                if (touchpad.getKnobPercentY()/touchpad.getKnobPercentX()<=Math.sin(45)&& touchpad.getKnobPercentY()/touchpad.getKnobPercentX() >=0 ) {
                    System.out.println("你是对的");
                }
                System.out.println(" X === " +touchpad.getKnobPercentX());
//            if (touchpad.getKnobPercentX() > 0) {
//                myActor.setState(STATE.Right);
//            }else if(touchpad.getKnobPercentX() <0){
//                myActor.setState(STATE.Left);
//            }else if(touchpad.getKnobPercentY() >0){
//                myActor.setState(STATE.Up);
//            }else if (touchpad.getKnobPercentY() <0) {
//                myActor.setState(STATE.Down);
//            }else {
//                myActor.setState(STATE.Stop);
//            }
        }else {
            myActor.setState(STATE.Stop);
        }
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        this.update();
        if (isRun) {
            myActor.moveToPoint();
            if (myActor.getXFlor() <= 0) {
                if (myActor.getX() <= distanceTouchX) {
                    isRun = false;
                }
            }else {
                if (myActor.getX() >= distanceTouchX) {
                    isRun = false;
                }
            }
//            if (distanceTouchX <= myActor.getX()) {
//                
//            }
//            if (myActor.getX() >= distanceTouchX) {
//                isRun = false;
//            }
        }
        super.draw();
    }
    
    private void actorFace(float x1,float y1){
        System.out.println("实现了");
        float x = x1 - Gdx.graphics.getWidth()/2;
        float y = y1 - Gdx.graphics.getHeight()/2;
        double d = y/x;
        if (x > 0) {
            if(y >0){
                if(d < Math.sin(45)){
                    myActor.setState(STATE.Right);
                }else {
                    myActor.setState(STATE.Up);
                }
            }else {
                if (d > -(Math.sin(45))) {
                    myActor.setState(STATE.Right);
                }else {
                    myActor.setState(STATE.Down);
                }
            }
        }else {
            if (y>0) {
                if (d > -(Math.sin(45))) {
                    myActor.setState(STATE.Left);
                }else {
                    myActor.setState(STATE.Up);
                }
            }else {
                if(d < Math.sin(45)){
                    myActor.setState(STATE.Left);
                }else {
                    myActor.setState(STATE.Down);
                }
            }

        }
        
    }
    
}
