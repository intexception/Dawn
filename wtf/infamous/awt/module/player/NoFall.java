package wtf.infamous.awt.module.player;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import wtf.infamous.awt.event.*;

public class NoFall extends Module
{
    public NoFall() {
        super("nofall", 49, Category.PLAYER);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (this.mc.thePlayer.fallDistance > 2.0f) {
            this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
    }
}
