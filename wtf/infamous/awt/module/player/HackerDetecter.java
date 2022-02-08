package wtf.infamous.awt.module.player;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.utils.*;
import wtf.infamous.awt.event.*;

public class HackerDetecter extends Module
{
    public HackerDetecter() {
        super("hackerdetector", 0, Category.PLAYER);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (this.mc.thePlayer.isCollidedHorizontally && this.mc.thePlayer.noClip) {
            ChatUtil.print(String.valueOf(this.mc.thePlayer.getName()) + " has flagged for for PHASE C");
        }
        if (this.mc.thePlayer.velocityChanged) {
            ChatUtil.print(String.valueOf(this.mc.thePlayer.getName()) + " has flagged for for VELOCITY");
        }
    }
}
