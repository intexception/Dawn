package wtf.infamous.awt.module.render;

import wtf.infamous.awt.module.*;
import java.util.*;
import wtf.infamous.awt.*;
import wtf.infamous.awt.gui.settings.*;
import net.minecraft.client.gui.*;

public class ClickGUI extends Module
{
    public ClickGUI() {
        super("clickgui", 54, Category.RENDER);
    }
    
    @Override
    public void setup() {
        final ArrayList<String> options = new ArrayList<String>();
        options.add("New");
        options.add("Dawn");
        this.mc.gameSettings.ofFastRender = false;
        Dawn.settingsManager.rSetting(new Setting("Design", this, "Dawn", options));
        Dawn.settingsManager.rSetting(new Setting("Sound", this, false));
        Dawn.settingsManager.rSetting(new Setting("GuiRed", this, 160.0, 0.0, 255.0, true));
        Dawn.settingsManager.rSetting(new Setting("GuiGreen", this, 110.0, 0.0, 255.0, true));
        Dawn.settingsManager.rSetting(new Setting("GuiBlue", this, 255.0, 0.0, 255.0, true));
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.displayGuiScreen(Dawn.clickGui);
        this.toggle();
    }
}
