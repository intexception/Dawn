package net.minecraft.client.gui.inventory;

import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import net.minecraft.inventory.*;
import java.io.*;

public class GuiChest extends GuiContainer
{
    private static final ResourceLocation CHEST_GUI_TEXTURE;
    private IInventory upperChestInventory;
    public IInventory lowerChestInventory;
    public int inventoryRows;
    
    static {
        CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    }
    
    public GuiChest(final IInventory upperInv, final IInventory lowerInv) {
        super(new ContainerChest(upperInv, lowerInv, Minecraft.getMinecraft().thePlayer));
        this.upperChestInventory = upperInv;
        this.lowerChestInventory = lowerInv;
        this.allowUserInput = false;
        final int i = 222;
        final int j = i - 108;
        this.inventoryRows = lowerInv.getSizeInventory() / 9;
        this.ySize = j + this.inventoryRows * 18;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
        this.fontRendererObj.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8.0, 6.0, 4210752);
        this.fontRendererObj.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8.0, this.ySize - 96 + 2, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(GuiChest.CHEST_GUI_TEXTURE);
        final int i = (this.width - this.xSize) / 2;
        final int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
    
    @Override
    public void initGui() {
        super.initGui();
        final int posY = (this.height - this.ySize) / 2 + 2;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 150, posY - 20, 40, 20, "Steal"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 150, posY - 5, 40, 20, "Store"));
    }
    
    public void actionPerformed(final GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < GuiChest.this.inventoryRows * 9; ++i) {
                            final Slot slot = GuiChest.this.inventorySlots.inventorySlots.get(i);
                            if (slot.getStack() != null) {
                                Thread.sleep(250L);
                                GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 1);
                                GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 6);
                            }
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        else if (button.id == 2) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = GuiChest.this.inventoryRows * 9; i < GuiChest.this.inventoryRows * 9 + 44; ++i) {
                            final Slot slot = GuiChest.this.inventorySlots.inventorySlots.get(i);
                            if (slot.getStack() != null) {
                                Thread.sleep(250L);
                                GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 1);
                                GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 6);
                            }
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
