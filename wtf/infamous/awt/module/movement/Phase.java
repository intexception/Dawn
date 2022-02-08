package wtf.infamous.awt.module.movement;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.event.*;

public class Phase extends Module
{
    public Phase() {
        super("phase", 25, Category.MOVEMENT);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        this.mc.gameSettings.keyBindSneak.pressed = true;
        this.mc.thePlayer.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 4.0, this.mc.thePlayer.posZ);
        this.mc.thePlayer.motionY = -1.0;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.gameSettings.keyBindSneak.pressed = false;
    }
}
