package wtf.infamous.awt.module.movement;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.*;
import java.util.*;
import wtf.infamous.awt.gui.settings.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import wtf.infamous.awt.event.events.*;
import io.netty.util.internal.*;
import wtf.infamous.awt.utils.*;
import net.minecraft.client.gui.*;
import wtf.infamous.awt.event.*;

public class Fly extends Module
{
    private String mode;
    
    public Fly() {
        super("flight", 34, Category.MOVEMENT);
        this.mode = Dawn.settingsManager.getSettingByName("Fly Mode").getValString();
    }
    
    @Override
    public void setup() {
        final ArrayList<String> options = new ArrayList<String>();
        Dawn.settingsManager.rSetting(new Setting("Fly Mode", this, "watchdog", options));
        options.add("watchdog");
        options.add("minemen");
        options.add("pvp");
        options.add("hazelmc");
        options.add("vanilla");
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        if (this.mc.theWorld.getCollidingBoundingBoxes(this.mc.thePlayer, this.mc.thePlayer.getEntityBoundingBox().offset(0.0, 3.0001, 0.0).expand(0.0, 0.0, 0.0)).isEmpty()) {
            this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 3.0001, this.mc.thePlayer.posZ, false));
            this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));
            this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
        }
    }
    
    @EventTarget
    public void onPre(final EventPreMotionUpdate event) {
        int viewBobbing = 0;
        final int hypixelLagback = 0;
        if (this.mode.equalsIgnoreCase("watchdog")) {
            this.setDisplayName("fly §7" + this.mode);
            if (this.mc.thePlayer.ticksExisted % 2 != 0) {
                event.y += 300001.0;
            }
            final double deez = ThreadLocalRandom.current().nextDouble(1.3, 1.6);
            this.mc.thePlayer.motionY = (this.mc.thePlayer.movementInput.jump ? deez : (this.mc.thePlayer.movementInput.sneak ? (-deez) : 0.0));
            MovementUtils.setMotion(deez);
            this.mc.thePlayer.onGround = true;
            this.mc.thePlayer.cameraYaw = 0.105f;
            this.mc.thePlayer.cameraPitch = 0.105f;
            ++viewBobbing;
            if (this.mode.equalsIgnoreCase("minemen")) {
                this.setDisplayName("fly §7" + this.mode);
                this.mc.thePlayer.capabilities.isFlying = true;
                this.mc.displayGuiScreen(null);
                this.mc.thePlayer.setSpeed(1.2f);
                if (!this.mc.thePlayer.onGround) {
                    this.mc.gameSettings.thirdPersonView = 0;
                }
                this.mc.thePlayer.cameraYaw = (float)((float)Math.random() - Math.random());
                this.mc.timer.timerSpeed = 0.3f;
            }
            if (this.mode.equalsIgnoreCase("pvp")) {
                this.setDisplayName("fly §7" + this.mode);
                this.mc.thePlayer.capabilities.isFlying = true;
                this.mc.displayGuiScreen(null);
                this.mc.thePlayer.setSpeed(1.2f);
                if (!this.mc.thePlayer.onGround) {
                    this.mc.gameSettings.thirdPersonView = 0;
                }
                this.mc.thePlayer.cameraYaw = (float)((float)Math.random() - Math.random());
            }
            if (this.mode.equalsIgnoreCase("hazelmc")) {
                this.setDisplayName("fly §7" + this.mode);
                this.mc.thePlayer.capabilities.isFlying = true;
                this.mc.displayGuiScreen(null);
            }
            if (this.mode.equalsIgnoreCase("vanilla")) {
                this.setDisplayName("fly §7" + this.mode);
                this.mc.thePlayer.capabilities.isFlying = true;
                this.mc.gameSettings.thirdPersonView = 0;
            }
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.thePlayer.capabilities.isFlying = false;
        this.mc.timer.timerSpeed = 1.0f;
    }
}
