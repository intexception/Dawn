package wtf.infamous.awt.gui.clickgui.elements.menu;

import wtf.infamous.awt.gui.clickgui.elements.*;
import wtf.infamous.awt.gui.settings.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import wtf.infamous.awt.gui.clickgui.util.*;

public class ElementCheckBox extends Element
{
    public ElementCheckBox(final ModuleButton iparent, final Setting iset) {
        this.parent = iparent;
        this.set = iset;
        super.setup();
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final Color temp = ColorUtil.getClickGUIColor();
        final int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 200).getRGB();
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -15066598);
        FontUtil.drawString(this.setstrg, this.x + this.width - FontUtil.getStringWidth(this.setstrg), this.y + FontUtil.getFontHeight() / 2 - 0.5, 65280);
        Gui.drawRect(this.x + 1.0, this.y + 2.0, this.x + 12.0, this.y + 13.0, this.set.getValBoolean() ? color : -16777216);
        if (this.isCheckHovered(mouseX, mouseY)) {
            Gui.drawRect(this.x + 1.0, this.y + 2.0, this.x + 12.0, this.y + 13.0, 1427181841);
        }
    }
    
    @Override
    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0 && this.isCheckHovered(mouseX, mouseY)) {
            this.set.setValBoolean(!this.set.getValBoolean());
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public boolean isCheckHovered(final int mouseX, final int mouseY) {
        return mouseX >= this.x + 1.0 && mouseX <= this.x + 12.0 && mouseY >= this.y + 2.0 && mouseY <= this.y + 13.0;
    }
}
