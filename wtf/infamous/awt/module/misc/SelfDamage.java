package wtf.infamous.awt.module.misc;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import wtf.infamous.awt.event.*;

public class SelfDamage extends Module
{
    public SelfDamage() {
        super("selfdamage", 0, Category.MISC);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 3.2, this.mc.thePlayer.posZ, false));
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.thePlayer.setSprinting(false);
    }
}
