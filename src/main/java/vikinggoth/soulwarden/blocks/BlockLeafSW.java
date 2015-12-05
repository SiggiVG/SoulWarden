package vikinggoth.soulwarden.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.items.itemblocks.IMetaBlockName;
import vikinggoth.soulwarden.registries.ConfigBlocks;
import vikinggoth.soulwarden.registries.ConfigItems;

import java.util.List;
import java.util.Random;

/**
 * Created by Friedrich on 11/20/2015.
 */
public class BlockLeafSW extends BlockLeaves implements IMetaBlockName
{
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", ConfigBlocks.WoodType.class, new Predicate()
    {
        public boolean apply(ConfigBlocks.WoodType type)
        {
            return type.getMetadata() < 4;
        }
        public boolean apply(Object p_apply_1_)
        {
            return this.apply((ConfigBlocks.WoodType)p_apply_1_);
        }
    });

    public BlockLeafSW()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, ConfigBlocks.WoodType.GHOUL).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        /*if (state.getBlock() != this)
        {
            return super.getRenderColor(state);
        }
        else
        {
            ConfigBlocks.WoodType enumtype = (ConfigBlocks.WoodType)state.getValue(VARIANT);
            return enumtype == ConfigBlocks.WoodType.SPRUCE ? ColorizerFoliage.getFoliageColorPine() : (enumtype == ConfigBlocks.WoodType.BIRCH ? ColorizerFoliage.getFoliageColorBirch() : super.getRenderColor(state));
        }*/
        return super.getRenderColor(state);
    }

    @SideOnly(Side.CLIENT) //TODO
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this)
        {
            /*ConfigBlocks.WoodType enumtype = (ConfigBlocks.WoodType)iblockstate.getValue(VARIANT);

            if (enumtype == ConfigBlocks.WoodType.SPRUCE)
            {
                return ColorizerFoliage.getFoliageColorPine();
            }

            if (enumtype == ConfigBlocks.WoodType.BIRCH)
            {
                return ColorizerFoliage.getFoliageColorBirch();
            }*/
        }

        return super.colorMultiplier(worldIn, pos, renderPass);
    }

    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
        if (state.getValue(VARIANT) == ConfigBlocks.WoodType.BONEBEECH && worldIn.rand.nextInt(chance) == 0)
        {
            spawnAsEntity(worldIn, pos, new ItemStack(Items.bone, 1, 0)); //TODO
        }
        else if (state.getValue(VARIANT) == ConfigBlocks.WoodType.POMEGRANATE && worldIn.rand.nextInt(chance) == 0)
        {
            spawnAsEntity(worldIn, pos, new ItemStack(ConfigItems.pomegranate, 1, 0)); //TODO
        }
    }

    protected int getSaplingDropChance(IBlockState state)
    {
        return state.getValue(VARIANT) == ConfigBlocks.WoodType.BONEBEECH ? 40 : super.getSaplingDropChance(state);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(itemIn, 1, ConfigBlocks.WoodType.GHOUL.getMetadata()));
        list.add(new ItemStack(itemIn, 1, ConfigBlocks.WoodType.WEEPWILLOW.getMetadata()));
        list.add(new ItemStack(itemIn, 1, ConfigBlocks.WoodType.BONEBEECH.getMetadata()));
        list.add(new ItemStack(itemIn, 1, ConfigBlocks.WoodType.HAND.getMetadata()));
        list.add(new ItemStack(itemIn, 1, ConfigBlocks.WoodType.ALM.getMetadata()));
        list.add(new ItemStack(itemIn, 1, ConfigBlocks.WoodType.POMEGRANATE.getMetadata()));
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((ConfigBlocks.WoodType)state.getValue(VARIANT)).getMetadata());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, this.getWoodType(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((ConfigBlocks.WoodType)state.getValue(VARIANT)).getMetadata();

        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return null;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT, CHECK_DECAY, DECAYABLE});
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return ((ConfigBlocks.WoodType)state.getValue(VARIANT)).getMetadata();
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
    {
        if (!worldIn.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears)
        {
            player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        IBlockState state = world.getBlockState(pos);
        return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 1, ((ConfigBlocks.WoodType)state.getValue(VARIANT)).getMetadata())));
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.sapling); //TODO SWsapling
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return ConfigBlocks.WoodType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
    }
}
