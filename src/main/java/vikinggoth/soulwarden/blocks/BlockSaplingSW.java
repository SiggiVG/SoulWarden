package vikinggoth.soulwarden.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.items.itemblocks.IMetaBlockName;
import vikinggoth.soulwarden.reference.EnumWoodType;
import vikinggoth.soulwarden.registries.BlockRegistry;

import java.util.List;
import java.util.Random;

public class BlockSaplingSW extends BlockBush implements IGrowable, IMetaBlockName
{
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumWoodType.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public BlockSaplingSW()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumWoodType.GHOUL).withProperty(STAGE, Integer.valueOf(0)));
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        //this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn, pos, state, rand);
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(worldIn, pos, state, rand);
        }
    }

    /**
     * Check whether the given BlockPos has a Sapling of the given type
     *
     public boolean isTypeAt(World worldIn, BlockPos pos, BlockPlanks.EnumType type)
     {
     IBlockState iblockstate = worldIn.getBlockState(pos);
     return iblockstate.getBlock() == this && iblockstate.getValue(TYPE) == type;
     }*/

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return ((EnumWoodType)state.getValue(TYPE)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        EnumWoodType[] aenumtype = EnumWoodType.values();
        int i = aenumtype.length;

        for (int j = 0; j < i; ++j)
        {
            EnumWoodType enumtype = aenumtype[j];
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return (double)worldIn.rand.nextFloat() < 0.45D; //TODO change this value to make it harder to grow
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state, rand);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumWoodType.byMetadata(meta & 7)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((EnumWoodType)state.getValue(TYPE)).getMetadata();
        i |= ((Integer)state.getValue(STAGE)).intValue() << 3;
        return i;
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE, STAGE});
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumWoodType.byMetadata(stack.getItemDamage()).getUnlocalizedName();
    }

    /**
     * checks if there is enough room for the tree to grow
      * @param worldIn
     * @param pos
     * @param state
     * @param rand
     * @return
     */
    private boolean checkForSpace(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        int i = rand.nextInt(3) + 5;

        if(pos.getY() >= 1 && pos.getY() + i +1 <= 256)
        {

        }
        return false;
    }

    /**
     * Generates the tree
     * Will call other gen methods, which may be recursive
     * @param worldIn
     * @param pos
     * @param state
     * @param rand
     */
    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        BlockPos origin = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        BlockPos down = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        int heightLimit = 0;
        IBlockState log;
        IBlockState logWall;
        IBlockState leaf;
        IBlockState knot;
        boolean isDownSoil = worldIn.getBlockState(down).getBlock().canSustainPlant(worldIn, down, EnumFacing.UP, (BlockSaplingSW) BlockRegistry.saplingSW);

        if(worldIn.getBlockState(down).getBlock().equals(BlockRegistry.grassCemetery) || worldIn.getBlockState(down).getBlock().equals(BlockRegistry.graveSoil))
        { //on gravesoil and on grasscemetery
            switch((EnumWoodType)state.getValue(TYPE))
            {
                case GHOUL:
                    log = BlockRegistry.logSW.getStateFromMeta(0);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(0);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(0);
                    break;
                case WEEPWILLOW:
                    log = BlockRegistry.logSW.getStateFromMeta(1);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(1);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(1);
                    break;
                case BONEBEECH:
                    log = BlockRegistry.logSW.getStateFromMeta(2);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(2);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(2);
                    break;
                case HAND:
                    log = BlockRegistry.logSW.getStateFromMeta(2);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(2);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(2);
                    break;
            }
        }
        else if(isDownSoil)
        { //on dirt
            switch ((EnumWoodType) state.getValue(TYPE))
            {
                case GHOUL:
                    log = BlockRegistry.logSW.getStateFromMeta(0);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(0);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(0);
                    break;
                case WEEPWILLOW:
                    log = BlockRegistry.logSW.getStateFromMeta(1);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(1);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(1);
                    break;
                case BONEBEECH:
                    log = BlockRegistry.logSW.getStateFromMeta(2);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(2);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(2);
                    break;
                case HAND:
                    log = BlockRegistry.logSW.getStateFromMeta(2);
                    logWall = BlockRegistry.log_wallSW.getStateFromMeta(2);
                    leaf = BlockRegistry.leafSW.getStateFromMeta(2);
                    break;
            }
        }

        /*if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        Object object = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (BlockSapling.SwitchEnumType.WOOD_TYPE_LOOKUP[((BlockPlanks.EnumType)state.getValue(TYPE)).ordinal()])
        {
            case 1:
                label78:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTypeAt(worldIn, pos.add(i, 0, j), BlockPlanks.EnumType.SPRUCE) && this.isTypeAt(worldIn, pos.add(i + 1, 0, j), BlockPlanks.EnumType.SPRUCE) && this.isTypeAt(worldIn, pos.add(i, 0, j + 1), BlockPlanks.EnumType.SPRUCE) && this.isTypeAt(worldIn, pos.add(i + 1, 0, j + 1), BlockPlanks.EnumType.SPRUCE))
                        {
                            object = new WorldGenMegaPineTree(false, rand.nextBoolean());
                            flag = true;
                            break label78;
                        }
                    }
                }

                if (!flag)
                {
                    j = 0;
                    i = 0;
                    object = new WorldGenTaiga2(true);
                }

                break;
            case 2:
                object = new WorldGenForest(true, false);
                break;
            case 3:
                label93:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTypeAt(worldIn, pos.add(i, 0, j), BlockPlanks.EnumType.JUNGLE) && this.isTypeAt(worldIn, pos.add(i + 1, 0, j), BlockPlanks.EnumType.JUNGLE) && this.isTypeAt(worldIn, pos.add(i, 0, j + 1), BlockPlanks.EnumType.JUNGLE) && this.isTypeAt(worldIn, pos.add(i + 1, 0, j + 1), BlockPlanks.EnumType.JUNGLE))
                        {
                            object = new WorldGenMegaJungle(true, 10, 20, BlockPlanks.EnumType.JUNGLE.getMetadata(), BlockPlanks.EnumType.JUNGLE.getMetadata());
                            flag = true;
                            break label93;
                        }
                    }
                }

                if (!flag)
                {
                    j = 0;
                    i = 0;
                    object = new WorldGenTrees(true, 4 + rand.nextInt(7), BlockPlanks.EnumType.JUNGLE.getMetadata(), BlockPlanks.EnumType.JUNGLE.getMetadata(), false);
                }

                break;
            case 4:
                object = new WorldGenSavannaTree(true);
                break;
            case 5:
                label108:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTypeAt(worldIn, pos.add(i, 0, j), BlockPlanks.EnumType.DARK_OAK) && this.isTypeAt(worldIn, pos.add(i + 1, 0, j), BlockPlanks.EnumType.DARK_OAK) && this.isTypeAt(worldIn, pos.add(i, 0, j + 1), BlockPlanks.EnumType.DARK_OAK) && this.isTypeAt(worldIn, pos.add(i + 1, 0, j + 1), BlockPlanks.EnumType.DARK_OAK))
                        {
                            object = new WorldGenCanopyTree(true);
                            flag = true;
                            break label108;
                        }
                    }
                }

                if (!flag)
                {
                    return;
                }
            case 6:
        }

        IBlockState iblockstate1 = Blocks.air.getDefaultState();

        if (flag)
        {
            worldIn.setBlockState(pos.add(i, 0, j), iblockstate1, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate1, 4);
            worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate1, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate1, 4);
        }
        else
        {
            worldIn.setBlockState(pos, iblockstate1, 4);
        }

        if (!((WorldGenerator)object).generate(worldIn, rand, pos.add(i, 0, j)))
        {
            if (flag)
            {
                worldIn.setBlockState(pos.add(i, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
            }
            else
            {
                worldIn.setBlockState(pos, state, 4);
            }
        }
        */
    }

    /**
     * Generates the roots
     * @param worldIn
     * @param pos
     * @param state
     * @param rand
     */
    private void generateRootBulb(World worldIn, BlockPos pos, IBlockState state, Random rand, boolean gravedirt)
    {

    }

    /**
     * Generates the roots
     * @param worldIn
     * @param pos
     * @param state
     * @param rand
     */
    private void generateRoot(World worldIn, BlockPos pos, IBlockState state, Random rand, EnumFacing face, int rootLength)
    {

    }

    /**
     * Generates the tree's trunk
     * @param worldIn
     * @param pos
     * @param state
     * @param rand
     */
    private void generateTrunk(World worldIn, BlockPos pos, IBlockState state, Random rand, boolean gravedirt)
    {

    }

    /**
     * Generates the branches of the tree
     * @param worldIn
     * @param pos
     * @param state
     * @param rand
     */
    private void generateBranches(World worldIn, BlockPos pos, IBlockState state, Random rand, boolean gravedirt)
    {

    }

    /**
     * Generates the top of the tree
     * @param worldIn
     * @param pos
     * @param state
     * @param rand
     */
    private void generateCanopy(World worldIn, BlockPos pos, IBlockState state, Random rand, boolean gravedirt)
    {

    }

}
