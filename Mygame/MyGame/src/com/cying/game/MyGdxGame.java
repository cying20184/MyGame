package com.cying.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cying.game.MyActor.STATE;

public class MyGdxGame implements ApplicationListener {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private Stage stage;
    private Image image;
    private ImageButton up;
    private Image down;
    private Image left;
    private Image right;
    private Button button;
    private TextureRegion textureRegion;
    private ControlStage controlStage;
    private MyActor myActor;
    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

//        camera = new OrthographicCamera(1, h / w);
//        batch = new SpriteBatch();
//        stage = new Stage();


//        texture = new Texture(Gdx.files.internal("data/01.jpg"));
//        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);


        //第一种截图方式TextureRegion
        //0,0 代表坐标原点
        //480，320 代表宽高，宽高为正值时方向为原点从左到右，从上到下截图，负值则从右到左，下到上
//        textureRegion= new TextureRegion(texture, 0, 0, 480,
//                320);


        //第二种 sprite 
        //sprite是textureRegion的加强版，拥有可以设定位置，颜色，旋转等功能
        //需要传入textureRegion
//        sprite = new Sprite(textureRegion);
//        sprite.setSize(150, 150);//设置图片大小
//        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);//设置旋转的中心点
//        sprite.setPosition(200,150);//设置图片的位置
////        sprite.setColor(Color.BLACK);//设置图片颜色
//        sprite.setRotation(50);//设置旋转角度
        
        
        
//        加入stage和actor
//        image = new Image(textureRegion);
//        actor没有点击效应
//        image.setSize(240, 240);
//        image.setRotation(45);
//        image.setOrigin(120, 120);
//        设置舞台大小，第三个参数是否充满，第四个传入spritibatch
//        stage = new Stage(480,320,false);
//        stage.addActor(image);



//        //添加自定义Actor以及添加四个方向按钮
//        //并添加移动效果
//        texture = new Texture(Gdx.files.internal("data/libgdx.png"));
//        textureRegion= new TextureRegion(texture, 0, 0, 50,
//              50);
//        up = new ImageButton(new TextureRegionDrawable(textureRegion));
//        stage = new Stage(w,h,false);
//        
//        Gdx.input.setInputProcessor(stage);
//
//        myActor = new MyActor(400, 400);
//
//        up.addListener(new InputListener(){
//
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                // TODO Auto-generated method stub
//                myActor.setState(STATE.Left);
//                return true;
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                // TODO Auto-generated method stub
//                myActor.setState(STATE.Stop);
//                super.touchUp(event, x, y, pointer, button);
//            }
//
//        });
//        stage.addActor(myActor);
//        stage.addActor(up);


        //第四
        //自定义舞台:摇杆
        controlStage = new ControlStage();
        Gdx.input.setInputProcessor(controlStage);
    }

    @Override
    public void dispose() {
//        batch.dispose();
//        texture.dispose();
    }

    @Override
    public void render() {
        //清屏作用
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        // batch.setProjectionMatrix(camera.combined);
//        batch.begin();


        //第一种不使用Sprite时画图方式
//        batch.draw(textureRegion, 0, 0, 480, 320);






        //第二中使用Sprite绘图
//        sprite.draw(batch);
//        batch.end();
        
        
       
        
        //舞台绘制效果
        //.act绘制演员
//        stage.act();
//        stage.draw();

        controlStage.act();
        controlStage.draw();
       
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
