package wtf.infamous.awt.module.combat;

import wtf.infamous.awt.module.*;
import java.util.*;
import wtf.infamous.awt.*;
import wtf.infamous.awt.gui.settings.*;
import wtf.infamous.awt.event.*;
import wtf.infamous.awt.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import wtf.infamous.awt.utils.*;

public class Criticals extends Module
{
    public Criticals() {
        super("criticals", 0, Category.COMBAT);
    }
    
    @Override
    public void setup() {
        final ArrayList<String> options = new ArrayList<String>();
        options.add("packet");
        options.add("MiniJump");
        Dawn.settingsManager.rSetting(new Setting("Criticals Mode", this, "packet", options));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        final String mode = Dawn.settingsManager.getSettingByName("Criticals Mode").getValString();
        this.setDisplayName("criticals §7" + mode);
    }
    
    @EventTarget
    public void onSendPacket(final EventSendPacket event) {
        final String mode = Dawn.settingsManager.getSettingByName("Criticals Mode").getValString();
        if (this.canCrit()) {
            if (event.getPacket() instanceof C02PacketUseEntity) {
                final C02PacketUseEntity packet = (C02PacketUseEntity)event.getPacket();
                if (packet.getAction() == C02PacketUseEntity.Action.ATTACK && mode.equalsIgnoreCase("packet")) {
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 0.1625, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 4.0E-6, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 1.0E-6, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
                }
            }
            if (mode.equalsIgnoreCase("MiniJump")) {
                this.mc.thePlayer.jump();
                final EntityPlayerSP thePlayer = this.mc.thePlayer;
                thePlayer.motionY -= 0.3000000119209288;
            }
        }
    }
    
    private boolean canCrit() {
        return !PlayerUtils.isInLiquid() && this.mc.thePlayer.onGround;
    }
}
