package net.minecraft.block;

import com.google.common.base.*;
import net.minecraft.block.properties.*;
import net.minecraft.creativetab.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.block.material.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;

public class BlockStem extends BlockBush implements IGrowable
{
    public static final PropertyInteger AGE;
    public static final PropertyDirection FACING;
    private final Block crop;
    
    static {
        AGE = PropertyInteger.create("age", 0, 7);
        FACING = PropertyDirection.create("facing", (Predicate<EnumFacing>)new Predicate<EnumFacing>() {
            public boolean apply(final EnumFacing p_apply_1_) {
                return p_apply_1_ != EnumFacing.DOWN;
            }
        });
    }
    
    protected BlockStem(final Block crop) {
        this.setDefaultState(this.blockState.getBaseState().withProperty((IProperty<Comparable>)BlockStem.AGE, 0).withProperty((IProperty<Comparable>)BlockStem.FACING, EnumFacing.UP));
        this.crop = crop;
        this.setTickRandomly(true);
        final float f = 0.125f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 0.25f, 0.5f + f);
        this.setCreativeTab(null);
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, final IBlockAccess worldIn, final BlockPos pos) {
        state = state.withProperty((IProperty<Comparable>)BlockStem.FACING, EnumFacing.UP);
        for (final Object enumfacing0 : EnumFacing.Plane.HORIZONTAL) {
            final EnumFacing enumfacing2 = (EnumFacing)enumfacing0;
            if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock() == this.crop) {
                state = state.withProperty((IProperty<Comparable>)BlockStem.FACING, enumfacing2);
                break;
            }
        }
        return state;
    }
    
    @Override
    protected boolean canPlaceBlockOn(final Block ground) {
        return ground == Blocks.farmland;
    }
    
    @Override
    public void updateTick(final World worldIn, BlockPos pos, IBlockState state, final Random rand) {
        super.updateTick(worldIn, pos, state, rand);
        if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
            final float f = BlockCrops.getGrowthChance(this, worldIn, pos);
            if (rand.nextInt((int)(25.0f / f) + 1) == 0) {
                final int i = state.getValue((IProperty<Integer>)BlockStem.AGE);
                if (i < 7) {
                    state = state.withProperty((IProperty<Comparable>)BlockStem.AGE, i + 1);
                    worldIn.setBlockState(pos, state, 2);
                }
                else {
                    for (final Object enumfacing0 : EnumFacing.Plane.HORIZONTAL) {
                        final EnumFacing enumfacing2 = (EnumFacing)enumfacing0;
                        if (worldIn.getBlockState(pos.offset(enumfacing2)).getBlock() == this.crop) {
                            return;
                        }
                    }
                    pos = pos.offset(EnumFacing.Plane.HORIZONTAL.random(rand));
                    final Block block = worldIn.getBlockState(pos.down()).getBlock();
                    if (worldIn.getBlockState(pos).getBlock().blockMaterial == Material.air && (block == Blocks.farmland || block == Blocks.dirt || block == Blocks.grass)) {
                        worldIn.setBlockState(pos, this.crop.getDefaultState());
                    }
                }
            }
        }
    }
    
    public void growStem(final World worldIn, final BlockPos pos, final IBlockState state) {
        final int i = state.getValue((IProperty<Integer>)BlockStem.AGE) + MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);
        worldIn.setBlockState(pos, state.withProperty((IProperty<Comparable>)BlockStem.AGE, Math.min(7, i)), 2);
    }
    
    @Override
    public int getRenderColor(final IBlockState state) {
        if (state.getBlock() != this) {
            return super.getRenderColor(state);
        }
        final int i = state.getValue((IProperty<Integer>)BlockStem.AGE);
        final int j = i * 32;
        final int k = 255 - i * 8;
        final int l = i * 4;
        return j << 16 | k << 8 | l;
    }
    
    @Override
    public int colorMultiplier(final IBlockAccess worldIn, final BlockPos pos, final int renderPass) {
        return this.getRenderColor(worldIn.getBlockState(pos));
    }
    
    @Override
    public void setBlockBoundsForItemRender() {
        final float f = 0.125f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 0.25f, 0.5f + f);
    }
    
    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess worldIn, final BlockPos pos) {
        this.maxY = (worldIn.getBlockState(pos).getValue((IProperty<Integer>)BlockStem.AGE) * 2 + 2) / 16.0f;
        final float f = 0.125f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, (float)this.maxY, 0.5f + f);
    }
    
    @Override
    public void dropBlockAsItemWithChance(final World worldIn, final BlockPos pos, final IBlockState state, final float chance, final int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
        if (!worldIn.isRemote) {
            final Item item = this.getSeedItem();
            if (item != null) {
                final int i = state.getValue((IProperty<Integer>)BlockStem.AGE);
                for (int j = 0; j < 3; ++j) {
                    if (worldIn.rand.nextInt(15) <= i) {
                        Block.spawnAsEntity(worldIn, pos, new ItemStack(item));
                    }
                }
            }
        }
    }
    
    protected Item getSeedItem() {
        return (this.crop == Blocks.pumpkin) ? Items.pumpkin_seeds : ((this.crop == Blocks.melon_block) ? Items.melon_seeds : null);
    }
    
    @Override
    public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
        return null;
    }
    
    @Override
    public Item getItem(final World worldIn, final BlockPos pos) {
        final Item item = this.getSeedItem();
        return (item != null) ? item : null;
    }
    
    @Override
    public boolean canGrow(final World worldIn, final BlockPos pos, final IBlockState state, final boolean isClient) {
        return state.getValue((IProperty<Integer>)BlockStem.AGE) != 7;
    }
    
    @Override
    public boolean canUseBonemeal(final World worldIn, final Random rand, final BlockPos pos, final IBlockState state) {
        return true;
    }
    
    @Override
    public void grow(final World worldIn, final Random rand, final BlockPos pos, final IBlockState state) {
        this.growStem(worldIn, pos, state);
    }
    
    @Override
    public IBlockState getStateFromMeta(final int meta) {
        return this.getDefaultState().withProperty((IProperty<Comparable>)BlockStem.AGE, meta);
    }
    
    @Override
    public int getMetaFromState(final IBlockState state) {
        return state.getValue((IProperty<Integer>)BlockStem.AGE);
    }
    
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { BlockStem.AGE, BlockStem.FACING });
    }
}
