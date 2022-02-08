package wtf.infamous.awt.module.movement;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.event.*;

public class Step extends Module
{
    public Step() {
        super("step", 45, Category.MOVEMENT);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        this.mc.thePlayer.stepHeight = 1.5f;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.thePlayer.stepHeight = 0.5f;
    }
}
