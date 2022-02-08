package wtf.infamous.awt.module.render;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import wtf.infamous.awt.event.*;

public class ChestESP extends Module
{
    private float oldBrightness;
    
    public ChestESP() {
        super("chestesp", 0, Category.RENDER);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.oldBrightness = this.mc.gameSettings.gammaSetting;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        this.mc.gameSettings.gammaSetting = 10.0f;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.gameSettings.gammaSetting = this.oldBrightness;
    }
}
