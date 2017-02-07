package me.beepbeat;

import com.bulletphysics.dynamics.character.KinematicCharacterController;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import me.beepbeat.util.InventoryEventBus;
import me.beepbeat.util.Kampfregel;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class Main extends SimpleApplication implements ActionListener {
    public static InventoryEventBus inventoryEventBus = new InventoryEventBus();

    private RigidBodyControl landscape;
    private CharacterControl player;
    private Vector3f walkDirection = new Vector3f();
    private boolean left = false, right = false, up = false, down = false;
    float jumpspeed = 35;

    private Vector3f camDir = new Vector3f();
    private Vector3f camLeft = new Vector3f();

    public static void main(String[] args){
        new Main().start();
    }

    private static void critRechner(){
        int i, k = 0;
        List<Integer> j = new ArrayList<>();
        for (int z = 0; z < 10;z++) {
            for (i = 0; i < 100; i++) {
                if (Kampfregel.crit(null)) {
                    k++;
                }
            }
            j.add(k);
            k = 0;
        }
        int mean = 0, mc = 0;
        for (Integer it : j) {
            mean += it;
            mc++;
            //System.out.println(it);
        }
        System.out.println(mean / mc);
    }

    @Override
    public void simpleInitApp() {
        assetManager.registerLocator("town.zip", ZipLocator.class);
        Spatial scene = assetManager.loadModel("main.scene");

        Material mat_particle = new Material(assetManager,"Common/MatDefs/Misc/Particle.j3md");
        mat_particle.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));

        ParticleEmitter fire = new ParticleEmitter("fire2", ParticleMesh.Type.Triangle, 30);

        fire.setMaterial(mat_particle);
        fire.setImagesX(2);
        fire.setImagesY(2);
        fire.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
        fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
        fire.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0));
        fire.setStartSize(1.5f);
        fire.setEndSize(0.1f);
        fire.setGravity(0, 0, 0);
        fire.setLowLife(1f);
        fire.setHighLife(3f);
        fire.getParticleInfluencer().setVelocityVariation(0.3f);
        rootNode.attachChild(fire);

        BulletAppState bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
        flyCam.setMoveSpeed(100);
        setUpKeys();
        setUpLight();
        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape((Node) scene);
        landscape = new RigidBodyControl(sceneShape, 0);
        scene.addControl(landscape);

        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        player = new CharacterControl(capsuleShape, 0.02f);
        player.setJumpSpeed(jumpspeed);
        player.setFallSpeed(60);
        player.setGravity(30);
        player.setPhysicsLocation(new Vector3f(0, 10, 0));

        rootNode.attachChild(scene);
        bulletAppState.getPhysicsSpace().add(landscape);
        bulletAppState.getPhysicsSpace().add(player);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Black);
        scene.setLocalTranslation(0, -5.2f, 0);
        scene.setLocalScale(2);
        rootNode.attachChild(scene);
    }

    private void setUpLight(){
        Light ambientLight = new AmbientLight();
        ambientLight.setColor(ColorRGBA.Yellow);
        rootNode.addLight(ambientLight);
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);
    }

    private void setUpKeys() {
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Left");
        inputManager.addListener(this, "Right");
        inputManager.addListener(this, "Up");
        inputManager.addListener(this, "Down");
        inputManager.addListener(this, "Jump");
    }

    public void onAction(String binding, boolean isPressed, float tpf) {
        switch (binding) {
            case "Left":{
                left = isPressed;
                break;}
            case "Right":{
                right = isPressed;
                break;}
            case "Up":{
                up = isPressed;
                break;}
            case "Down":{
                down = isPressed;
                break;}
            case "Jump":{
                if (isPressed) {
                    System.out.println("Jump?");
                    player.jump();
                }
                break;
            }
        }
    }

        public void simpleUpdate(float tpf) {
            camDir.set(cam.getDirection()).multLocal(0.6f);
            camLeft.set(cam.getLeft()).multLocal(0.4f);
            walkDirection.set(0, 0, 0);
            if (left) {
                walkDirection.addLocal(camLeft);
            }
            if (right) {
                walkDirection.addLocal(camLeft.negate());
            }
            if (up) {
                walkDirection.addLocal(camDir);
            }
            if (down) {
                walkDirection.addLocal(camDir.negate());
            }
            player.setWalkDirection(walkDirection);
            cam.setLocation(player.getPhysicsLocation());
        }
}
