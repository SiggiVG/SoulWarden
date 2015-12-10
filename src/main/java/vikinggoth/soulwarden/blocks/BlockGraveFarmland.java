package vikinggoth.soulwarden.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.registries.BlockRegister;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Friedrich on 11/18/2015.
 */
public class BlockGraveFarmland extends BlockFarmland
{
    public BlockGraveFarmland()
    {
        super();
        this.setStepSound(Block.soundTypeGravel);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int i = ((Integer)state.getValue(MOISTURE)).intValue();

        if (!this.hasWater(worldIn, pos) && !worldIn.canLightningStrike(pos.up()))
        {
            if (i > 0)
            {
                worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(i - 1)), 2);
            }
            else if (!this.hasCrops(worldIn, pos))
            {
                worldIn.setBlockState(pos, BlockRegister.graveSoil.getDefaultState());
            }
        }
        else if (i < 7)
        {
            worldIn.setBlockState(pos, state.withProperty(MOISTURE, Integer.valueOf(7)), 2);
        }
    }

    @Override/**
     * Block's chance to react to a living entity falling on it.
     *
     * @param fallDistance The distance the entity has fallen before landing
     */
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        if (entityIn instanceof EntityLivingBase)
        {
            if (!worldIn.isRemote && worldIn.rand.nextFloat() < fallDistance - 0.5F)
            {
                if (!(entityIn instanceof EntityPlayer) && !worldIn.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                {
                    return;
                }

                worldIn.setBlockState(pos, BlockRegister.graveSoil.getDefaultState());
            }

            entityIn.fall(fallDistance, 1.0F);
        }
    }

    private boolean hasCrops(World worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return block instanceof net.minecraftforge.common.IPlantable && canSustainPlant(worldIn, pos, net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)block);
    }

    private boolean hasWater(World worldIn, BlockPos pos)
    {
        Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();
        BlockPos.MutableBlockPos mutableblockpos;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            mutableblockpos = (BlockPos.MutableBlockPos)iterator.next();
        }
        while (worldIn.getBlockState(mutableblockpos).getBlock().getMaterial() != Material.water);

        return true;
    }

    @Override
    /**
     * Called when a neighboring block changes.
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);

        if (worldIn.getBlockState(pos.up()).getBlock().getMaterial().isSolid())
        {
            worldIn.setBlockState(pos, BlockRegister.graveSoil.getDefaultState());
        }
    }

    @Override
    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return BlockRegister.graveSoil.getItemDropped(BlockRegister.graveSoil.getDefaultState(), rand, fortune);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(BlockRegister.graveSoil);
    }


}
