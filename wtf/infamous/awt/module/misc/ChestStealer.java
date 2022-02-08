package wtf.infamous.awt.module.misc;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.event.events.*;
import java.io.*;
import wtf.infamous.awt.event.*;

public class ChestStealer extends Module
{
    public ChestStealer() {
        super("cheststealer", 0, Category.MISC);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) throws IOException {
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.thePlayer.setSprinting(false);
    }
}
