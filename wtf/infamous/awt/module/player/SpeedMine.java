package wtf.infamous.awt.module.player;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.event.*;

public class SpeedMine extends Module
{
    public SpeedMine() {
        super("speedmine", 0, Category.PLAYER);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        this.mc.playerController.blockHitDelay = 0;
        if (this.mc.playerController.curBlockDamageMP >= 0.7f) {
            this.mc.playerController.curBlockDamageMP = 1.0f;
        }
    }
}
