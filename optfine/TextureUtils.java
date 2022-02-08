package optfine;

import java.nio.*;
import java.awt.image.*;
import net.minecraft.client.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.util.*;
import javax.imageio.stream.*;

public class TextureUtils
{
    public static final String texGrassTop = "grass_top";
    public static final String texStone = "stone";
    public static final String texDirt = "dirt";
    public static final String texCoarseDirt = "coarse_dirt";
    public static final String texGrassSide = "grass_side";
    public static final String texStoneslabSide = "stone_slab_side";
    public static final String texStoneslabTop = "stone_slab_top";
    public static final String texBedrock = "bedrock";
    public static final String texSand = "sand";
    public static final String texGravel = "gravel";
    public static final String texLogOak = "log_oak";
    public static final String texLogBigOak = "log_big_oak";
    public static final String texLogAcacia = "log_acacia";
    public static final String texLogSpruce = "log_spruce";
    public static final String texLogBirch = "log_birch";
    public static final String texLogJungle = "log_jungle";
    public static final String texLogOakTop = "log_oak_top";
    public static final String texLogBigOakTop = "log_big_oak_top";
    public static final String texLogAcaciaTop = "log_acacia_top";
    public static final String texLogSpruceTop = "log_spruce_top";
    public static final String texLogBirchTop = "log_birch_top";
    public static final String texLogJungleTop = "log_jungle_top";
    public static final String texLeavesOak = "leaves_oak";
    public static final String texLeavesBigOak = "leaves_big_oak";
    public static final String texLeavesAcacia = "leaves_acacia";
    public static final String texLeavesBirch = "leaves_birch";
    public static final String texLeavesSpuce = "leaves_spruce";
    public static final String texLeavesJungle = "leaves_jungle";
    public static final String texGoldOre = "gold_ore";
    public static final String texIronOre = "iron_ore";
    public static final String texCoalOre = "coal_ore";
    public static final String texObsidian = "obsidian";
    public static final String texGrassSideOverlay = "grass_side_overlay";
    public static final String texSnow = "snow";
    public static final String texGrassSideSnowed = "grass_side_snowed";
    public static final String texMyceliumSide = "mycelium_side";
    public static final String texMyceliumTop = "mycelium_top";
    public static final String texDiamondOre = "diamond_ore";
    public static final String texRedstoneOre = "redstone_ore";
    public static final String texLapisOre = "lapis_ore";
    public static final String texCactusSide = "cactus_side";
    public static final String texClay = "clay";
    public static final String texFarmlandWet = "farmland_wet";
    public static final String texFarmlandDry = "farmland_dry";
    public static final String texNetherrack = "netherrack";
    public static final String texSoulSand = "soul_sand";
    public static final String texGlowstone = "glowstone";
    public static final String texLeavesSpruce = "leaves_spruce";
    public static final String texLeavesSpruceOpaque = "leaves_spruce_opaque";
    public static final String texEndStone = "end_stone";
    public static final String texSandstoneTop = "sandstone_top";
    public static final String texSandstoneBottom = "sandstone_bottom";
    public static final String texRedstoneLampOff = "redstone_lamp_off";
    public static final String texRedstoneLampOn = "redstone_lamp_on";
    public static final String texWaterStill = "water_still";
    public static final String texWaterFlow = "water_flow";
    public static final String texLavaStill = "lava_still";
    public static final String texLavaFlow = "lava_flow";
    public static final String texFireLayer0 = "fire_layer_0";
    public static final String texFireLayer1 = "fire_layer_1";
    public static final String texPortal = "portal";
    public static final String texGlass = "glass";
    public static final String texGlassPaneTop = "glass_pane_top";
    public static TextureAtlasSprite iconGrassTop;
    public static TextureAtlasSprite iconGrassSide;
    public static TextureAtlasSprite iconGrassSideOverlay;
    public static TextureAtlasSprite iconSnow;
    public static TextureAtlasSprite iconGrassSideSnowed;
    public static TextureAtlasSprite iconMyceliumSide;
    public static TextureAtlasSprite iconMyceliumTop;
    public static TextureAtlasSprite iconWaterStill;
    public static TextureAtlasSprite iconWaterFlow;
    public static TextureAtlasSprite iconLavaStill;
    public static TextureAtlasSprite iconLavaFlow;
    public static TextureAtlasSprite iconPortal;
    public static TextureAtlasSprite iconFireLayer0;
    public static TextureAtlasSprite iconFireLayer1;
    public static TextureAtlasSprite iconGlass;
    public static TextureAtlasSprite iconGlassPaneTop;
    public static final String SPRITE_LOCATION_PREFIX = "minecraft:blocks/";
    private static IntBuffer staticBuffer;
    
    static {
        TextureUtils.staticBuffer = GLAllocation.createDirectIntBuffer(256);
    }
    
    public static void update() {
        final TextureMap texturemap = getTextureMapBlocks();
        if (texturemap != null) {
            final String s = "minecraft:blocks/";
            TextureUtils.iconGrassTop = texturemap.getSpriteSafe(String.valueOf(s) + "grass_top");
            TextureUtils.iconGrassSide = texturemap.getSpriteSafe(String.valueOf(s) + "grass_side");
            TextureUtils.iconGrassSideOverlay = texturemap.getSpriteSafe(String.valueOf(s) + "grass_side_overlay");
            TextureUtils.iconSnow = texturemap.getSpriteSafe(String.valueOf(s) + "snow");
            TextureUtils.iconGrassSideSnowed = texturemap.getSpriteSafe(String.valueOf(s) + "grass_side_snowed");
            TextureUtils.iconMyceliumSide = texturemap.getSpriteSafe(String.valueOf(s) + "mycelium_side");
            TextureUtils.iconMyceliumTop = texturemap.getSpriteSafe(String.valueOf(s) + "mycelium_top");
            TextureUtils.iconWaterStill = texturemap.getSpriteSafe(String.valueOf(s) + "water_still");
            TextureUtils.iconWaterFlow = texturemap.getSpriteSafe(String.valueOf(s) + "water_flow");
            TextureUtils.iconLavaStill = texturemap.getSpriteSafe(String.valueOf(s) + "lava_still");
            TextureUtils.iconLavaFlow = texturemap.getSpriteSafe(String.valueOf(s) + "lava_flow");
            TextureUtils.iconFireLayer0 = texturemap.getSpriteSafe(String.valueOf(s) + "fire_layer_0");
            TextureUtils.iconFireLayer1 = texturemap.getSpriteSafe(String.valueOf(s) + "fire_layer_1");
            TextureUtils.iconPortal = texturemap.getSpriteSafe(String.valueOf(s) + "portal");
            TextureUtils.iconGlass = texturemap.getSpriteSafe(String.valueOf(s) + "glass");
            TextureUtils.iconGlassPaneTop = texturemap.getSpriteSafe(String.valueOf(s) + "glass_pane_top");
        }
    }
    
    public static BufferedImage fixTextureDimensions(final String p_fixTextureDimensions_0_, final BufferedImage p_fixTextureDimensions_1_) {
        if (p_fixTextureDimensions_0_.startsWith("/mob/zombie") || p_fixTextureDimensions_0_.startsWith("/mob/pigzombie")) {
            final int i = p_fixTextureDimensions_1_.getWidth();
            final int j = p_fixTextureDimensions_1_.getHeight();
            if (i == j * 2) {
                final BufferedImage bufferedimage = new BufferedImage(i, j * 2, 2);
                final Graphics2D graphics2d = bufferedimage.createGraphics();
                graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics2d.drawImage(p_fixTextureDimensions_1_, 0, 0, i, j, null);
                return bufferedimage;
            }
        }
        return p_fixTextureDimensions_1_;
    }
    
    public static int ceilPowerOfTwo(final int p_ceilPowerOfTwo_0_) {
        int i;
        for (i = 1; i < p_ceilPowerOfTwo_0_; i *= 2) {}
        return i;
    }
    
    public static int getPowerOfTwo(final int p_getPowerOfTwo_0_) {
        int i;
        int j;
        for (i = 1, j = 0; i < p_getPowerOfTwo_0_; i *= 2, ++j) {}
        return j;
    }
    
    public static int twoToPower(final int p_twoToPower_0_) {
        int i = 1;
        for (int j = 0; j < p_twoToPower_0_; ++j) {
            i *= 2;
        }
        return i;
    }
    
    public static void refreshBlockTextures() {
    }
    
    public static ITextureObject getTexture(final String p_getTexture_0_) {
        return getTexture(new ResourceLocation(p_getTexture_0_));
    }
    
    public static ITextureObject getTexture(final ResourceLocation p_getTexture_0_) {
        final ITextureObject itextureobject = Config.getTextureManager().getTexture(p_getTexture_0_);
        if (itextureobject != null) {
            return itextureobject;
        }
        if (!Config.hasResource(p_getTexture_0_)) {
            return null;
        }
        final SimpleTexture simpletexture = new SimpleTexture(p_getTexture_0_);
        Config.getTextureManager().loadTexture(p_getTexture_0_, simpletexture);
        return simpletexture;
    }
    
    public static void resourcesReloaded(final IResourceManager p_resourcesReloaded_0_) {
        if (getTextureMapBlocks() != null) {
            Config.dbg("*** Reloading custom textures ***");
            CustomSky.reset();
            TextureAnimations.reset();
            update();
            NaturalTextures.update();
            BetterGrass.update();
            BetterSnow.update();
            TextureAnimations.update();
            CustomColorizer.update();
            CustomSky.update();
            RandomMobs.resetTextures();
            Config.updateTexturePackClouds();
            Config.getTextureManager().tick();
        }
    }
    
    public static TextureMap getTextureMapBlocks() {
        return Minecraft.getMinecraft().getTextureMapBlocks();
    }
    
    public static void registerResourceListener() {
        final IResourceManager iresourcemanager = Config.getResourceManager();
        if (iresourcemanager instanceof IReloadableResourceManager) {
            final IReloadableResourceManager ireloadableresourcemanager = (IReloadableResourceManager)iresourcemanager;
            final IResourceManagerReloadListener iresourcemanagerreloadlistener = new IResourceManagerReloadListener() {
                @Override
                public void onResourceManagerReload(final IResourceManager resourceManager) {
                    TextureUtils.resourcesReloaded(resourceManager);
                }
            };
            ireloadableresourcemanager.registerReloadListener(iresourcemanagerreloadlistener);
        }
        final ITickableTextureObject itickabletextureobject = new ITickableTextureObject() {
            @Override
            public void tick() {
                TextureAnimations.updateCustomAnimations();
            }
            
            @Override
            public void loadTexture(final IResourceManager resourceManager) throws IOException {
            }
            
            @Override
            public int getGlTextureId() {
                return 0;
            }
            
            @Override
            public void setBlurMipmap(final boolean p_174936_1_, final boolean p_174936_2_) {
            }
            
            @Override
            public void restoreLastBlurMipmap() {
            }
        };
        final ResourceLocation resourcelocation = new ResourceLocation("optifine/TickableTextures");
        Config.getTextureManager().loadTickableTexture(resourcelocation, itickabletextureobject);
    }
    
    public static String fixResourcePath(String p_fixResourcePath_0_, String p_fixResourcePath_1_) {
        final String s = "assets/minecraft/";
        if (p_fixResourcePath_0_.startsWith(s)) {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(s.length());
            return p_fixResourcePath_0_;
        }
        if (p_fixResourcePath_0_.startsWith("./")) {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);
            if (!p_fixResourcePath_1_.endsWith("/")) {
                p_fixResourcePath_1_ = String.valueOf(p_fixResourcePath_1_) + "/";
            }
            p_fixResourcePath_0_ = String.valueOf(p_fixResourcePath_1_) + p_fixResourcePath_0_;
            return p_fixResourcePath_0_;
        }
        final String s2 = "mcpatcher/";
        if (p_fixResourcePath_0_.startsWith("~/")) {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);
            p_fixResourcePath_0_ = String.valueOf(s2) + p_fixResourcePath_0_;
            return p_fixResourcePath_0_;
        }
        if (p_fixResourcePath_0_.startsWith("/")) {
            p_fixResourcePath_0_ = String.valueOf(s2) + p_fixResourcePath_0_.substring(1);
            return p_fixResourcePath_0_;
        }
        return p_fixResourcePath_0_;
    }
    
    public static String getBasePath(final String p_getBasePath_0_) {
        final int i = p_getBasePath_0_.lastIndexOf(47);
        return (i < 0) ? "" : p_getBasePath_0_.substring(0, i);
    }
    
    public static void applyAnisotropicLevel() {
        if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic) {
            final float f = GL11.glGetFloat(34047);
            float f2 = (float)Config.getAnisotropicFilterLevel();
            f2 = Math.min(f2, f);
            GL11.glTexParameterf(3553, 34046, f2);
        }
    }
    
    public static void bindTexture(final int p_bindTexture_0_) {
        GlStateManager.bindTexture(p_bindTexture_0_);
    }
    
    public static boolean isPowerOfTwo(final int p_isPowerOfTwo_0_) {
        final int i = MathHelper.roundUpToPowerOfTwo(p_isPowerOfTwo_0_);
        return i == p_isPowerOfTwo_0_;
    }
    
    public static BufferedImage scaleToPowerOfTwo(final BufferedImage p_scaleToPowerOfTwo_0_, final int p_scaleToPowerOfTwo_1_) {
        if (p_scaleToPowerOfTwo_0_ == null) {
            return p_scaleToPowerOfTwo_0_;
        }
        final int i = p_scaleToPowerOfTwo_0_.getWidth();
        final int j = p_scaleToPowerOfTwo_0_.getHeight();
        int k = Math.max(i, p_scaleToPowerOfTwo_1_);
        k = MathHelper.roundUpToPowerOfTwo(k);
        if (k == i) {
            return p_scaleToPowerOfTwo_0_;
        }
        final int l = j * k / i;
        final BufferedImage bufferedimage = new BufferedImage(k, l, 2);
        final Graphics2D graphics2d = bufferedimage.createGraphics();
        Object object = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
        if (k % i != 0) {
            object = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
        }
        graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, object);
        graphics2d.drawImage(p_scaleToPowerOfTwo_0_, 0, 0, k, l, null);
        return bufferedimage;
    }
    
    public static Dimension getImageSize(final InputStream p_getImageSize_0_, final String p_getImageSize_1_) {
        final Iterator iterator = ImageIO.getImageReadersBySuffix(p_getImageSize_1_);
        while (iterator.hasNext()) {
            final ImageReader imagereader = iterator.next();
            Dimension dimension;
            try {
                final ImageInputStream imageinputstream = ImageIO.createImageInputStream(p_getImageSize_0_);
                imagereader.setInput(imageinputstream);
                final int i = imagereader.getWidth(imagereader.getMinIndex());
                final int j = imagereader.getHeight(imagereader.getMinIndex());
                dimension = new Dimension(i, j);
            }
            catch (IOException var11) {
                continue;
            }
            finally {
                imagereader.dispose();
            }
            imagereader.dispose();
            return dimension;
        }
        return null;
    }
}
