package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.registries.BlockRegistry;
import vikinggoth.soulwarden.world.dimension.DimensionRegistry;

import java.util.Random;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class BlockPortalNecro extends BlockBreakable
{
    public BlockPortalNecro()
    {
        super(Material.portal, false);
        this.setTickRandomly(true);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        //All of this is mob spawning for pigmen
        /*
        if (worldIn.provider.isSurfaceWorld() && worldIn.getGameRules().getGameRuleBooleanValue("doMobSpawning") && rand.nextInt(2000) < worldIn.getDifficulty().getDifficultyId())
        {
            int i = pos.getY();
            BlockPos blockpos1;

            for (blockpos1 = pos; !World.doesBlockHaveSolidTopSurface(worldIn, blockpos1) && blockpos1.getY() > 0; blockpos1 = blockpos1.down())
            {
                ;
            }

            //This spawns in creatures
            if (i > 0 && !worldIn.getBlockState(blockpos1.up()).getBlock().isNormalCube())
            {
                Entity entity = ItemMonsterPlacer.spawnCreature(worldIn, 57, (double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 1.1D, (double)blockpos1.getZ() + 0.5D);

                if (entity != null)
                {
                    entity.timeUntilPortal = entity.getPortalCooldown();
                }
            }
        }*/
    }

    //There is no colision box
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }

    //because It's a block with an Axis that's shorter on 2 sides
    /*
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        EnumFacing.Axis axis = (EnumFacing.Axis)worldIn.getBlockState(pos).getValue(AXIS);
        float f = 0.125F;
        float f1 = 0.125F;

        if (axis == EnumFacing.Axis.X)
        {
            f = 0.5F;
        }

        if (axis == EnumFacing.Axis.Z)
        {
            f1 = 0.5F;
        }

        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
    }*/

    /*public static int getMetaForAxis(EnumFacing.Axis axis)
    {
        return axis == EnumFacing.Axis.X ? 1 : (axis == EnumFacing.Axis.Z ? 2 : 0);
    }*/

    public boolean isFullCube()
    {
        return false;
    }

    //This is all stuff for variable size
    /*
    public boolean func_176548_d(World worldIn, BlockPos p_176548_2_)
    {
        Size size = new Size(worldIn, p_176548_2_, EnumFacing.Axis.X);

        if (size.func_150860_b() && size.field_150864_e == 0)
        {
            size.func_150859_c();
            return true;
        }
        else
        {
            Size size1 = new Size(worldIn, p_176548_2_, EnumFacing.Axis.Z);

            if (size1.func_150860_b() && size1.field_150864_e == 0)
            {
                size1.func_150859_c();
                return true;
            }
            else
            {
                return false;
            }
        }
    }*/

    /**
     * Called when a neighboring block changes.
     *
     * This is what deletes the portal if it's not full
     */
    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        byte b0 = 0;
        byte b1 = 1;

        //check if on x axis by looking to sides
        if((worldIn.getBlockState(pos.west()).getBlock() == BlockRegistry.soulstone_twisted || worldIn.getBlockState(pos.east()).getBlock() == BlockRegistry.soulstone_twisted) ||
                (worldIn.getBlockState(pos.west()).getBlock() == this || worldIn.getBlockState(pos.east()).getBlock() == this))
        {
            b0 = 1;
            b1 = 0;
        }

        int i;

        for(i = 0; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i)
        {

        }
        if(worldIn.getBlockState(pos.down()) != BlockRegistry.soulstone_twisted)
        {
            worldIn.setBlockToAir(pos);
        }
        else
        {
            int j;

            for(j = 0; j < 4 && worldIn.getBlockState(pos.up(j)).getBlock() == this; ++j)
            {

            }
            //if(j  == 3 && worldIn.getBlockState())
        }

        /*
        EnumFacing.Axis axis = (EnumFacing.Axis)state.getValue(AXIS);
        //Size size;

        if (axis == EnumFacing.Axis.X)
        {
            //size = new BlockPortal.Size(worldIn, pos, EnumFacing.Axis.X);

            if (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g)
            {
                worldIn.setBlockState(pos, Blocks.air.getDefaultState());
            }
        }
        else if (axis == EnumFacing.Axis.Z)
        {
            size = new BlockPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g)
            {
                worldIn.setBlockState(pos, Blocks.air.getDefaultState());
            }
        }*/
    }

    /**
     * Rendering stuff
     *
     * @param worldIn
     * @param pos
     * @param side
     * @return
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate)
        {
            return true;
        }

        return super.shouldSideBeRendered(worldIn, pos, side);

        /*
        EnumFacing.Axis axis = null;
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (worldIn.getBlockState(pos).getBlock() == this)
        {
            //axis = (EnumFacing.Axis)iblockstate.getValue(AXIS);

            if (axis == null)
            {
                return false;
            }

            if (axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST)
            {
                return false;
            }

            if (axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH)
            {
                return false;
            }
        }

        boolean flag = worldIn.getBlockState(pos.west()).getBlock() == this && worldIn.getBlockState(pos.west(2)).getBlock() != this;
        boolean flag1 = worldIn.getBlockState(pos.east()).getBlock() == this && worldIn.getBlockState(pos.east(2)).getBlock() != this;
        boolean flag2 = worldIn.getBlockState(pos.north()).getBlock() == this && worldIn.getBlockState(pos.north(2)).getBlock() != this;
        boolean flag3 = worldIn.getBlockState(pos.south()).getBlock() == this && worldIn.getBlockState(pos.south(2)).getBlock() != this;
        boolean flag4 = flag || flag1 || axis == EnumFacing.Axis.X;
        boolean flag5 = flag2 || flag3 || axis == EnumFacing.Axis.Z;
        return flag4 && side == EnumFacing.WEST ? true : (flag4 && side == EnumFacing.EAST ? true : (flag5 && side == EnumFacing.NORTH ? true : flag5 && side == EnumFacing.SOUTH));
        */
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn.ridingEntity == null && entityIn.riddenByEntity == null)
        {
            entityIn.travelToDimension(DimensionRegistry.DIM_NECRO_ID);
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    //public IBlockState getStateFromMeta(int meta)
    {
        //return this.getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
    }

    /**
     * THIS MAKES IT THANSPARENT
     * @return
     */
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    /**
     * This generates particles and makes noise
     *
     * @param worldIn
     * @param pos
     * @param state
     * @param rand
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        //Noise
        //if (rand.nextInt(100) == 0)
        {
            //worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        //Particles TODO make only sides of blocks that touch air drop particles
        for (int i = 0; i < 4; ++i)
        {
            //x coord
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            //y coord
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            //z coord
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            //x offset
            double d3 = 0;//((double)rand.nextFloat() - 0.5D) * 0.5D;
            //y offset
            double d4 = 0;//((double)rand.nextFloat() - 0.5D) * 0.5D;
            //z offset
            double d5 = 0;//((double)rand.nextFloat() - 0.5D) * 0.5D;
            //inverter
            int j = rand.nextInt(2) * 2 - 1;

            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this)
            {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                //d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }
            if (worldIn.getBlockState(pos.north()).getBlock() != this && worldIn.getBlockState(pos.south()).getBlock() != this)
            {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                //d5 = (double)(rand.nextFloat() * 2.0 * (float)j);
            }

            worldIn.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, d0, d1, d2, d3, d4, d5, new int[0]);
        }
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    //public int getMetaFromState(IBlockState state)
    {
        //return getMetaForAxis((EnumFacing.Axis)state.getValue(AXIS));
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return null;
    }

    //protected BlockState createBlockState()
    {
        //return new BlockState(this, new IProperty[] {AXIS});
    }

}
