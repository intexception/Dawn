package wtf.infamous.awt.module.movement;

import wtf.infamous.awt.module.*;
import java.util.*;
import wtf.infamous.awt.*;
import wtf.infamous.awt.gui.settings.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.event.*;
import net.minecraft.entity.*;
import io.netty.util.internal.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;

public class LongJump extends Module
{
    public LongJump() {
        super("longjump", 21, Category.MOVEMENT);
    }
    
    @Override
    public void setup() {
        final ArrayList<String> options = new ArrayList<String>();
        options.add("packet");
        options.add("normal");
        Dawn.settingsManager.rSetting(new Setting("longjump Mode", this, "packet", options));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        final String mode = Dawn.settingsManager.getSettingByName("longjump Mode").getValString();
        this.setDisplayName("longjump §7" + mode);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        final String mode = Dawn.settingsManager.getSettingByName("longjump Mode").getValString();
        if (mode.equalsIgnoreCase("packet")) {
            if (this.mc.theWorld.getCollidingBoundingBoxes(this.mc.thePlayer, this.mc.thePlayer.getEntityBoundingBox().offset(0.0, 3.0001, 0.0).expand(0.0, 0.0, 0.0)).isEmpty()) {
                final double deez = ThreadLocalRandom.current().nextDouble(0.5, 1.2);
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 3.0001, this.mc.thePlayer.posZ, false));
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
                this.mc.thePlayer.motionY = deez;
            }
            if (mode.equalsIgnoreCase("normal")) {
                this.mc.thePlayer.jump();
                final EntityPlayerSP thePlayer = this.mc.thePlayer;
                thePlayer.motionY -= 0.3000000119209288;
            }
        }
    }
}
