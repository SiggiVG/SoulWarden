package vikinggoth.soulwarden.registers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vikinggoth.soulwarden.SoulWarden;
import vikinggoth.soulwarden.blocks.*;
import vikinggoth.soulwarden.items.itemblocks.ItemBlockMeta;
import vikinggoth.soulwarden.items.itemblocks.ItemPlanksSWSlab;
import vikinggoth.soulwarden.items.itemblocks.ItemSoulStoneSlab;
import vikinggoth.soulwarden.reference.EnumWoodType;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class BlockRegister
{
    //Decorative and Building
    public static Block soulGravel;
    public static Block graveSoil; //deathly dirt
    public static Block grassCemetery; //grass block that spreads along graveSoil //TODO for 1.9 make shovels make paths
    public static Block graveSoilTilled; //tilled graveSoil TODO make hoes till it

    //SoulStone //default, mossysmooth, cracked, smooth, brick, mossybrick, crackedbrick, chiseled, cobble, mossycobble, brickscattered, bricksmall
    public static Block soulStone;
    //SoulStoneStairs //come in brick, cobble, brickscattered, and bricksmall
    public static Block soulStoneBrickStairs;
    public static Block soulStoneCobbleStairs;
    public static Block soulStoneBrickScatteredStairs;
    public static Block soulStoneBrickSmallStairs;
    //SoulStoneSlabs //there are up to 8 different variants, as one bit is top/bottom
    //default(special), smooth, brick, cobble, brick, brickscattered, bricksmall
    public static Block soulStoneSlab;
    public static Block soulStoneSlabDouble;
    //Walls //first 12 variants, cracked has a fence
    public static Block soulStoneWall; //first 12 variants
    public static Block soulStoneFence; //is just the cracked variant //"#", "#", '#', new ItemStack(soulstone, 1, 2); //a cracked on top of a cracked

    //Wood
    //Logs
    public static Block logSW;
    //public static Block logSWRot;
    public static Block logSW2;
    //public static Block logSW2Rot;

    /*
    All the varieties have at least 2 different growth algorithms
    1 for being grown on vanilla ground, these will generate like vanilla trees
    and 1 for being grown on gravesoil, which will be rampant/larger than default
     */
    //Walls
    public static Block log_wallSW;
    //public static Block log_wallSWRot;
    //Leaves
    public static Block leafSW;
    //Saplings
    public static Block saplingSW;
    //Planks
    public static Block plankSW;
    //public static Block plankSWROT;
    //Stairs
    public static Block ghoulStairs;
    public static Block weepwillowStairs;
    public static Block bonebeechStairs;
    public static Block handStairs;
    public static Block alnwickStairs;
    public static Block pomegranateStairs;
    /*public static Block ghoulStairsRot;
    public static Block weepwillowStairsRot;
    public static Block bonebeechStairsRot;
    public static Block handStairsRot;
    public static Block alnwickStairsRot;
    public static Block pomegranateStairsRot;*/

    //Slabs
    public static Block planksSWSlab;
    public static Block planksSWSlabDouble;
    //public static Block planksSWSlabRot;
    //public static Block planksSWSlabDoubleRot;
    //Fences
    public static Block fenceSW;
    //public static Block fenceSWRot;
    //Doors TODO
    public static Block ghoulDoor;
    public static Block weepwillowDoor;
    public static Block bonebeechDoor;
    public static Block handDoor;
    public static Block alnwickDoor;
    public static Block pomegranateDoor;

    public static Block bonebooBase;
    public static Block boneboo;
    public static Block bonebooFruitBlock;

    //Bone
    public static Block bonePile; //gravity block, compress to make boneBlock
    public static Block boneBlock; //full block, compress to mate itemNetherQuartz
    public static Block boneWall; //connectable wall
    public static Block boneFence; //connectable fence
    public static Block bonePillar; //wall width block, rotatable column
    public static Block boneBaluster; //fence width block, rotatable column

    //Urn
    public static Block urnlarge;
    public static Block urnmedium;
    public static Block urnsmall;

    //Ores
    public static Block ore_soulgem;
    public static Block ore_soulgem_black;
    //public static Block ore_rostygold;
    public static Block ore_pewter;
    public static Block ore_hematite;
    public static Block ore_ember;

    //Metals
    public static Block block_soulgem;
    public static Block block_soulgem_black;
    //public static Block block_rostygold;
    public static Block block_pewter;
    public static Block block_hematite;
    //public static Block block_silver;
    //public static Block block_copper;
    //public static Block block_tin;
    //public static Block block_bronze;

    //Portal
    /**
     * this is a twisted version of the smlla brick variant of soulstone
     * it formas when the portal is made
     */
    public static Block soulstone_twisted;
    public static Block portalNecro;

    //Crops + Flowers
    //TODO: Boneboo - farmable bones.

    //Soul Transport
    /*
    public static Block soulPylon; //main componant for soul transport
    public static Block soulTrans; //main componant for soul transport, forms pairs
    public static Block soulCapacitor; //active soul storage
    public static Block soulDais; //acts as a recharge station

    //Production
    public static Block soulSepulter; //makes soulcharged blocks
    public static Block soulExhumer; //unmakes soulcharged blocks, fills soul transport system*/
    //public static Block soulFurnace_on; //a furnace that runs on souls
    //public static Block soulFurnace_off; //a furnace that runs on souls

    /*public static Block boneGrinder; //ore doubler(by making <ore> dust), bonus bonemeal, skeleton head source
    public static Block soulForge; //multiblock, craft advanced items and bone construct components
    public static Block soulCircle; //multiblock, for assembling constructs

    //Constructs
    public static bonecable; acts as a path for bone constructs to move along, attached to Dais or Pylon?
     */

    /*
    //Post Release Stuff
    //Stygian landscape
    public static Block stygianStone; //is purpley
    public static Block stygianGeode; //renders based on surrounding blocks, drops Stygian Shards when broken
    public static Block ghostVapor; //form in clumps throughout the Necropolis Dimension, like clouds, give off light and can be walked through, drops an item
    public static Block ghostVaporLamp; //craftable form ghostVapor that functions like glowstone lamps, cant be walked through
    public static Block glowCoral; //purple glowing block found under stygianWater. drops glowPolyps if not broken with silk touch
    public static Block glowCoralLamp; //purple glowstone lamp
    public static Block stygianWater; //dark waters
    public static Block vineMourningGlory;
    public static Block flowerMonkshood;
     */

    public static void createBlocks()
    {
        //Decorative and Building
        //TODO MAKE HOE-ABLE
        graveSoil = new BlockGraveSoil().setUnlocalizedName("gravesoil");
        graveSoilTilled = new BlockGraveFarmland().setUnlocalizedName("grave_farmland");//;
        grassCemetery = new BlockGrassCemetery().setUnlocalizedName("grass_cemetery");

        //soulGravel = new BlockSoulGravel().setUnlocalizedName("soulgravel");

        //SoulStone
        soulStone = new BlockSoulStone().setUnlocalizedName("soulstone");
        //SoulStoneStairs
        soulStoneBrickStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.BRICK)).setUnlocalizedName("soulstone_brick_stairs");
        soulStoneCobbleStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.COBBLE)).setUnlocalizedName("soulstone_cobble_stairs");
        soulStoneBrickScatteredStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.SCATTERED)).setUnlocalizedName("soulstone_scattered_stairs");
        soulStoneBrickSmallStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.SMALL)).setUnlocalizedName("soulstone_small_stairs");
        //soulStoneSlabs
        soulStoneSlab = new BlockHalfSoulStoneSlab(Material.rock).setUnlocalizedName("soulstone_slab");
        soulStoneSlabDouble = new BlockDoubleSoulStoneSlab(Material.rock).setUnlocalizedName("soulstone_double_slab");
        //Walls
        soulStoneWall = new BlockSoulStoneWall(soulStone).setUnlocalizedName("soulstone_wall");
        soulStoneFence = new BlockFence(soulStone.getMaterial()).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("soulstone_fence");

        //Wood
        //Logs
        logSW = new BlockLogSW().setUnlocalizedName("logSW");
        logSW2 = new BlockLogSW2().setUnlocalizedName("logSW2");
        //Walls
        log_wallSW = new BlockLogSWWall(logSW).setUnlocalizedName("log_wallSW");
        //Leaves
        leafSW = new BlockLeafSW().setUnlocalizedName("leafSW");
        //Sapling
        saplingSW = new BlockSaplingSW().setUnlocalizedName("saplingSW");
        //Planks
        plankSW = new BlockPlanksSW().setUnlocalizedName("plankSW");
        //Stairs
        ghoulStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, EnumWoodType.GHOUL)).setUnlocalizedName("planks_ghoul_stairs");
        weepwillowStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, EnumWoodType.WEEPWILLOW)).setUnlocalizedName("planks_weepwillow_stairs");
        bonebeechStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, EnumWoodType.BONEBEECH)).setUnlocalizedName("planks_bonebeech_stairs");
        handStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, EnumWoodType.HAND)).setUnlocalizedName("planks_hand_stairs");
        alnwickStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, EnumWoodType.ALNWICK)).setUnlocalizedName("planks_alnwick_stairs");
        pomegranateStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, EnumWoodType.POMEGRANATE)).setUnlocalizedName("planks_pomegranate_stairs");
        //Slabs
        planksSWSlab = new BlockHalfPlanksSWSlab(Material.wood).setUnlocalizedName("plankSW_slab");
        planksSWSlabDouble = new BlockDoublePlanksSWSlab(Material.wood).setUnlocalizedName("plankSW_double_slab");
        //logSWSlab = new BlockHalfLogSWSlab(Material.wood).setUnlocalizedName("plankSW_slab");
        //logSWSlabDouble = new BlockDoubleLogSWSlab(Material.wood).setUnlocalizedName("plankSW_double_slab");
        //8 variants, 2 top/bottom, 4 X/Y/Z/BARK //I'm going to have to create a custom ItemBlock to place these
        //Fences
        fenceSW = new BlockFenceSW().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setUnlocalizedName("fenceSW");
        //Doors TODO
        ghoulDoor = new BlockDoorSW().setUnlocalizedName("door_ghoul");
        weepwillowDoor = new BlockDoorSW().setUnlocalizedName("door_weepwillow");
        bonebeechDoor = new BlockDoorSW().setUnlocalizedName("door_bonebeech");
        handDoor = new BlockDoorSW().setUnlocalizedName("door_hand");
        alnwickDoor = new BlockDoorSW().setUnlocalizedName("door_alnwick");
        pomegranateDoor = new BlockDoorSW().setUnlocalizedName("door_pomegranate");

        //Plants TODO make boneboo spawn (sapling) craftable by crafting a skeleton skull with Boneboo Fruit
        bonebooBase = new BlockBonebooBase().setUnlocalizedName("boneboo_base");
        boneboo = new BlockBonebooGrowth().setUnlocalizedName("boneboo").setCreativeTab(null);
        bonebooFruitBlock = new BlockBonebooFruit().setUnlocalizedName("boneboo_fruit_block").setCreativeTab(null);

        //Bone
        bonePile = new BlockBonePile().setUnlocalizedName("bonepile");
        (boneBlock = new Block(Material.coral).setStepSound(Block.soundTypeLadder).setHardness(1.0F).setResistance(5.0F).setUnlocalizedName("boneblock")).setHarvestLevel("pickaxe", 0);
        boneWall = new BlockBoneWall(boneBlock).setStepSound(Block.soundTypeLadder).setUnlocalizedName("bonewall");
        boneFence = new BlockFence(boneBlock.getMaterial()).setStepSound(Block.soundTypeLadder).setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeLadder).setUnlocalizedName("bonefence");

        //Urn
        urnlarge = new BlockUrnLarge().setUnlocalizedName("urn_large");
        urnmedium = new BlockUrnMedium().setUnlocalizedName("urn_medium");
        urnsmall = new BlockUrnSmall().setUnlocalizedName("urn_small");

        //Ores
        //ore_rostygold = new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("ore_rostygold");
        //ore_rostygold.setHarvestLevel("pickaxe", 2);
        ore_pewter = new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("ore_pewter");
        ore_pewter.setHarvestLevel("pickaxe", 2);
        ore_soulgem = new BlockSoulgemOre().setUnlocalizedName("ore_soulgem");
        ore_soulgem_black = new BlockSoulgemBlackOre().setUnlocalizedName("ore_soulgem_black");
        ore_hematite = new BlockHematiteOre().setUnlocalizedName("ore_hematite");
        ore_ember = new BlockOre().setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setUnlocalizedName("ore_ember");

        //Metals
        block_soulgem = new BlockSoulgem().setUnlocalizedName("block_soulgem");
        block_soulgem_black = new BlockSoulgem().setUnlocalizedName("block_soulgem_black");
        (block_hematite = new Block(Material.rock, MapColor.redColor).setHardness(5F).setResistance(10F).setStepSound(Block.soundTypeMetal).setUnlocalizedName("block_hematite")).setHarvestLevel("pickaxe", 0);

        //Portal
        soulstone_twisted = new BlockSoulstoneTwisted().setUnlocalizedName("soulstone_twisted");
        portalNecro = new BlockPortalNecro().setUnlocalizedName("portal_necro");

        //Soul Transport
        /*
        soulPylon = new BlockSoulPylon().setUnlocalizedName("soulPylon");
        */
        //soulFurnace_on = new BlockSoulFurnace(true).setUnlocalizedName("soulfurnace_on");
        //soulFurnace_off = new BlockSoulFurnace(false).setUnlocalizedName("soulfurnace_on");

        registerBlocks();
    }

    public static void registerBlocks()
    {
        //Decorative and Building
        regBlock(graveSoil);
        regBlock(graveSoilTilled);
        regBlock(grassCemetery);
        //regBlock(soulGravel);
        regMetaBlock(soulStone);
        //SoulStoneStairs
        regBlock(soulStoneBrickStairs);
        regBlock(soulStoneCobbleStairs);
        regBlock(soulStoneBrickScatteredStairs);
        regBlock(soulStoneBrickSmallStairs);
        //SoulStoneSlabs
        GameRegistry.registerBlock(soulStoneSlab, ItemSoulStoneSlab.class, soulStoneSlab.getUnlocalizedName().substring(5), soulStoneSlab, soulStoneSlabDouble);
        GameRegistry.registerBlock(soulStoneSlabDouble, ItemSoulStoneSlab.class, soulStoneSlabDouble.getUnlocalizedName().substring(5), soulStoneSlab, soulStoneSlabDouble);
        //Walls
        regMetaBlock(soulStoneWall);
        regBlock(soulStoneFence);

        //Wood
        //Logs
        regMetaBlock(logSW);
        regMetaBlock(logSW2);
        //Walls
        regMetaBlock(log_wallSW);
        //Leaves

        //Planks
        regMetaBlock(plankSW);
        //Stairs
        regBlock(ghoulStairs);
        regBlock(weepwillowStairs);
        regBlock(bonebeechStairs);
        regBlock(handStairs);
        regBlock(alnwickStairs);
        regBlock(pomegranateStairs);

        //Slabs
        GameRegistry.registerBlock(planksSWSlab, ItemPlanksSWSlab.class, planksSWSlab.getUnlocalizedName().substring(5), planksSWSlab, planksSWSlabDouble);
        GameRegistry.registerBlock(planksSWSlabDouble, ItemPlanksSWSlab.class, planksSWSlabDouble.getUnlocalizedName().substring(5), planksSWSlab, planksSWSlabDouble);
        //Fences
        regMetaBlock(fenceSW);
        //Doors TODO
        //GameRegistry.registerBlock(ghoulDoor, ghoulDoor.getUnlocalizedName().substring(5));
        //GameRegistry.registerBlock(weepwillowDoor, weepwillowDoor.getUnlocalizedName().substring(5));
        //GameRegistry.registerBlock(bonebeechDoor, bonebeechDoor.getUnlocalizedName().substring(5));
        //GameRegistry.registerBlock(handDoor, handDoor.getUnlocalizedName().substring(5));

        //Plants
        regBlock(bonebooBase);
        GameRegistry.registerBlock(boneboo, boneboo.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(bonebooFruitBlock, bonebooFruitBlock.getUnlocalizedName().substring(5));

        //Bone
        regBlock(bonePile);
        regBlock(boneBlock);
        regBlock(boneWall);
        regBlock(boneFence);

        //Urn
        regMetaBlock(urnlarge);
        regMetaBlock(urnmedium);
        regMetaBlock(urnsmall);

        //Ores
        //regBlock(ore_rostygold);
        regBlock(ore_pewter);
        regMetaBlock(ore_soulgem);
        regBlock(ore_soulgem_black);
        regBlock(ore_hematite);
        regBlock(ore_ember);

        //Metals
        regBlock(block_soulgem);
        regBlock(block_soulgem_black);
        regBlock(block_hematite);

        //Portal
        regBlock(soulstone_twisted);
        regBlock(portalNecro);
        //GameRegistry.registerBlock(portal_necro, portal_necro.getUnlocalizedName().substring(5));

        //Soul Transport
        //GameRegistry.registerBlock(soulPylon, ItemSoulPylon.class, soulPylon.getUnlocalizedName().substring(5));
    }

    private static void regBlock(Block block)
    {
        block.setCreativeTab(SoulWarden.SWTab);
        GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
    }

    private static void regMetaBlock(Block block)
    {
        block.setCreativeTab(SoulWarden.SWTab);
        GameRegistry.registerBlock(block, ItemBlockMeta.class, block.getUnlocalizedName().substring(5));
    }




}
