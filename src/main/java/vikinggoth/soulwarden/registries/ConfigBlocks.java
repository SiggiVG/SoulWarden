package vikinggoth.soulwarden.registries;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vikinggoth.soulwarden.SoulWarden;
import vikinggoth.soulwarden.blocks.*;
import vikinggoth.soulwarden.blocks.containers.*;
import vikinggoth.soulwarden.items.itemblocks.*;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class ConfigBlocks
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
    public static Block logSW2;
    /*
    All the varieties have at least 2 different growth algorithms
    1 for being grown on vanilla ground, these will generate like vanilla trees
    and 1 for being grown on gravesoil, which will be rampant/larger than default
     */
    //Walls
    public static Block log_wallSW;
    //Leaves
    public static Block leafSW;
    //Saplings
    public static Block saplingSW;
    //Planks
    public static Block plankSW;
    //Stairs
    public static Block ghoulStairs;
    public static Block weepwillowStairs;
    public static Block bonebeechStairs;
    public static Block handStairs;
    public static Block almStairs;
    public static Block pomegranateStairs;

    //Slabs
    public static Block planksSWSlab;
    public static Block planksSWSlabDouble;
    //Fences
    public static Block fenceSW;
    //Doors TODO
    public static Block ghoulDoor;
    public static Block weepwillowDoor;
    public static Block bonebeechDoor;
    public static Block handDoor;
    public static Block almDoor;
    public static Block pomegranateDoor;

    //Bone
    public static Block bonePile; //gravity block
    public static Block boneBlock; //full block
    public static Block boneWall; //connectable wall
    public static Block boneFence; //connectable fence
    public static Block bonePillar; //wall width block, rotatable column
    public static Block boneBaluster; //fence width block, rotatable column

    //Urn
    public static Block urnlarge;
    public static Block urnmedium;
    public static Block urnsmall;

    //Soul Transport
    /*
    public static Block soulPylon; //main componant for soul transport
    public static Block soulTrans; //main componant for soul transport, forms pairs
    public static Block soulCapacitor; //active soul storage
    public static Block soulDais; //acts as a recharge station

    //Production
    public static Block soulSepulter; //makes soulcharged blocks
    public static Block soulExhumer; //unmakes soulcharged blocks, fills soul transport system*/
    public static Block soulFurnace_on; //a furnace that runs on souls
    public static Block soulFurnace_off; //a furnace that runs on souls
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
        graveSoil = new BlockGraveSoil().setUnlocalizedName("gravesoil").setCreativeTab(SoulWarden.SWTab);
        graveSoilTilled = new BlockGraveFarmland().setUnlocalizedName("grave_farmland");//.setCreativeTab(SoulWarden.SWTab);
        grassCemetery = new BlockGrassCemetery().setUnlocalizedName("grass_cemetery").setCreativeTab(SoulWarden.SWTab);
        soulGravel = new BlockSoulGravel().setUnlocalizedName("soulgravel").setCreativeTab(SoulWarden.SWTab);
        soulStone = new BlockSoulStone().setUnlocalizedName("soulstone").setCreativeTab(SoulWarden.SWTab);
        //SoulStoneStairs
        soulStoneBrickStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.BRICK)).setUnlocalizedName("soulstone_brick_stairs");
        soulStoneCobbleStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.COBBLE)).setUnlocalizedName("soulstone_cobble_stairs");
        soulStoneBrickScatteredStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.SCATTERED)).setUnlocalizedName("soulstone_scattered_stairs");
        soulStoneBrickSmallStairs = new BlockStairsSW(soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.SMALL)).setUnlocalizedName("soulstone_small_stairs");
        //soulStoneSlabs
        soulStoneSlab = new BlockHalfSoulStoneSlab(Material.rock).setUnlocalizedName("soulstone_slab");
        soulStoneSlabDouble = new BlockDoubleSoulStoneSlab(Material.rock).setUnlocalizedName("soulstone_double_slab");
        //Walls
        soulStoneWall = new BlockSoulStoneWall(soulStone).setUnlocalizedName("soulstone_wall").setCreativeTab(SoulWarden.SWTab);
        soulStoneFence = new BlockFence(soulStone.getMaterial()).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("soulstone_fence").setCreativeTab(SoulWarden.SWTab);

        //Wood
        //Logs
        logSW = new BlockLogSW().setUnlocalizedName("logSW").setCreativeTab(SoulWarden.SWTab);
        logSW2 = new BlockLogSW2().setUnlocalizedName("logSW2").setCreativeTab(SoulWarden.SWTab);
        //Walls
        log_wallSW = new BlockLogSWWall(logSW).setUnlocalizedName("log_wallSW").setCreativeTab(SoulWarden.SWTab);
        //Leaves
        leafSW = new BlockLeafSW().setUnlocalizedName("leafSW").setCreativeTab(SoulWarden.SWTab);
        //Sapling
        //saplingSW = new BlockSaplingSW().setUnlocalizedName("saplingSW").setCreativeTab(SoulWarden.SWTab);
        //Planks
        plankSW = new BlockPlanksSW().setUnlocalizedName("plankSW").setCreativeTab(SoulWarden.SWTab);
        //Stairs
        ghoulStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, WoodType.GHOUL)).setUnlocalizedName("planks_ghoul_stairs");
        weepwillowStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, WoodType.WEEPWILLOW)).setUnlocalizedName("planks_weepwillow_stairs");
        bonebeechStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, WoodType.BONEBEECH)).setUnlocalizedName("planks_bonebeech_stairs");
        handStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, WoodType.HAND)).setUnlocalizedName("planks_hand_stairs");
        almStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, WoodType.HAND)).setUnlocalizedName("planks_alm_stairs");
        pomegranateStairs = new BlockStairsSW(plankSW.getDefaultState().withProperty(BlockPlanksSW.VARIANT, WoodType.HAND)).setUnlocalizedName("planks_pomegranate_stairs");
        //Slabs
        planksSWSlab = new BlockHalfPlanksSWSlab(Material.wood).setUnlocalizedName("plankSW_slab");
        planksSWSlabDouble = new BlockDoublePlanksSWSlab(Material.wood).setUnlocalizedName("plankSW_double_slab");
        //Fences
        fenceSW = new BlockFenceSW().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setUnlocalizedName("fenceSW").setCreativeTab(SoulWarden.SWTab);
        //Doors TODO
        ghoulDoor = new BlockDoorSW().setUnlocalizedName("door_ghoul");
        weepwillowDoor = new BlockDoorSW().setUnlocalizedName("door_weepwillow");
        bonebeechDoor = new BlockDoorSW().setUnlocalizedName("door_bonebeech");
        handDoor = new BlockDoorSW().setUnlocalizedName("door_hand");
        almDoor = new BlockDoorSW().setUnlocalizedName("door_alm");
        pomegranateDoor = new BlockDoorSW().setUnlocalizedName("door_pomegranate");

        //Bone
        bonePile = new BlockBonePile().setUnlocalizedName("bonepile").setCreativeTab(SoulWarden.SWTab);
        boneBlock = new Block(Material.coral).setStepSound(Block.soundTypeLadder).setUnlocalizedName("boneblock").setCreativeTab(SoulWarden.SWTab);
        boneWall = new BlockBoneWall(boneBlock).setStepSound(Block.soundTypeLadder).setUnlocalizedName("bonewall").setCreativeTab(SoulWarden.SWTab);
        boneFence = new BlockFence(boneBlock.getMaterial()).setStepSound(Block.soundTypeLadder).setHardness(1.0F).setResistance(3.0F).setStepSound(Block.soundTypeLadder).setUnlocalizedName("bonefence").setCreativeTab(SoulWarden.SWTab);

        //Urn
        urnlarge = new BlockUrnLarge().setUnlocalizedName("urn_large").setCreativeTab(SoulWarden.SWTab);
        urnmedium = new BlockUrnMedium().setUnlocalizedName("urn_medium").setCreativeTab(SoulWarden.SWTab);
        urnsmall = new BlockUrnSmall().setUnlocalizedName("urn_small").setCreativeTab(SoulWarden.SWTab);

        //Soul Transport
        /*
        soulPylon = new BlockSoulPylon().setUnlocalizedName("soulPylon").setCreativeTab(SoulWarden.SWTab);
        */
        soulFurnace_on = new BlockSoulFurnace().setUnlocalizedName("soulfurnace_on").setCreativeTab(SoulWarden.SWTab);
        soulFurnace_off = new BlockSoulFurnace().setUnlocalizedName("soulfurnace_on").setCreativeTab(SoulWarden.SWTab);

        registerBlocks();
    }

    public static void registerBlocks()
    {
        //Decorative and Building
        regBlock(graveSoil);
        regBlock(graveSoilTilled);
        regBlock(grassCemetery);
        regBlock(soulGravel);
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
        regBlock(almStairs);
        regBlock(pomegranateStairs);

        //Slabs
        GameRegistry.registerBlock(planksSWSlab, ItemPlanksSWSlab.class, planksSWSlab.getUnlocalizedName().substring(5), planksSWSlab, planksSWSlabDouble);
        GameRegistry.registerBlock(planksSWSlabDouble, ItemPlanksSWSlab.class, planksSWSlabDouble.getUnlocalizedName().substring(5), planksSWSlab, planksSWSlabDouble);
        //Fences
        regMetaBlock(fenceSW);
        //Doors TODO
        GameRegistry.registerBlock(ghoulDoor, ghoulDoor.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(weepwillowDoor, weepwillowDoor.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(bonebeechDoor, bonebeechDoor.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(handDoor, handDoor.getUnlocalizedName().substring(5));

        //Bone
        regBlock(bonePile);
        regBlock(boneBlock);
        regBlock(boneWall);
        regBlock(boneFence);

        //Urn
        regMetaBlock(urnlarge);
        regMetaBlock(urnmedium);
        regMetaBlock(urnsmall);

        //Soul Transport
        //GameRegistry.registerBlock(soulPylon, ItemSoulPylon.class, soulPylon.getUnlocalizedName().substring(5));
    }

    private static void regBlock(Block block)
    {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
    }

    private static void regMetaBlock(Block block)
    {
        GameRegistry.registerBlock(block, ItemBlockMeta.class, block.getUnlocalizedName().substring(5));
    }

    public static enum WoodType implements IStringSerializable
    {
        GHOUL(0, "ghoul"),
        WEEPWILLOW(1, "weepwillow"),
        BONEBEECH(2, "bonebeech"),
        HAND(3, "hand"), //wax hand
        ALM(4, "alm"),
        POMEGRANATE(5, "pomegranate");

        private static final WoodType[] META_LOOKUP = new WoodType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private WoodType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        private WoodType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static WoodType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            WoodType[] stateArray = values();
            int var1 = stateArray.length;

            for (int i = 0; i < var1; ++i)
            {
                WoodType metas = stateArray[i];
                META_LOOKUP[metas.getMetadata()] = metas;
            }
        }
    }




    /**
     * Things to add:
     * Flowers: Calla Lilies(Overworld tombs that aren't dangerous), Black Rose(two blocks tall), Marigold(in tombs identical to the Calla Lily tombs, but spawn a wight when the seal is broken), Mourning Glory(is a vine in the Necropolis), and Monkshood(Necropolis flower, right clicking a wolf with it instantly kills the wolf)
     * Crops: Nighshade(berries are gotten from a special process requiring a poisonous potato and stygian water, are also found in dungeons)
     *
     * Stygian Stone: crafted by dropping soulstone and obsidian into the Stygian Water that's found in the Necropolis
     *
     * Dimensions: Necropolis
     *  The ceiling of the necropolis is covered in stalagtites of Soulstone(default) and has veins of Soulstone(cracked) running through it.
     *  The ground is composed similarly, often having stalagmites and boulders. there are occaisional patches of Gravesoil
     *  The Necropolis generates structures based upon biome
     *  There are seas/lakes of Stygian Water, which are difficult for the player to see through (apply wither?)
     *  scattered throughout this Stygian Sea are islands built onto giant stalagmites, with NecropoliCenter biome at the higher altitudes
     *  and NecropoliTownship at the lower ones. There are also shorter islands with BoneForest biomes or IslandCrag structures
     *
     * Biomes:
     *  Overworld: Pumpkin Patch(lots of pumpkins, White Pumpkins, Scarecrows, occasional ruined farmhouse), Haunted Woods(Horrors spawn in it, may contain Terror Glen)
     *      Graveyard(leafless trees, lots of gravestones, some tombs, random walls)
     *  Nether:
     *  Necropolis: Necropoli Center(acropolis, is higher in elevation), Necropoli Township, Bone Forrest, Stygian Sea,
     *
     *
     * Structures:
     *  Overworld: Soulstone Monolithe,
     *      White Tomb(with Calla Lilies, 3 sizes), Gold Tomb(with Marigolds, 3 sizes, spawns wight when seal is broken),
     *      Ruined Tower, Ruined House/Farmhouse, *      Waystone(random stack of stones),
     *      Gallows Tree(leafless tree with metal bars hanging from it and bone piles and skeleton skulls around it, has skeleton spawner in it),
     *
     *
     *  Nether: Cracked Soulstone Monolithe, Evil Eye Hive,
     *  Necropolis:
     *
     * Mobs:
     *  Passive: Lost Soul,
     *  Neutral: Noisy Soul(lost souls that fight back, they're noisier),
     *      Evil Eye(tameable, spawn naturally in the nether, if spawned from Eye Hive spawner, they are hostile),
     *
     *  Hostile: Angry Soul(aggresive lost souls), Vehement Soul(lost souls that seek out players from a far distance)
     *      Reassembling Skeleton(turn into a pile of bones on death, if pile isnt killed, they reassemble),
     *      Horror(Gives the player the shiver effect when the player looks at them)
     *  Pet: Calm Eye(Evil that has been tamed using a Ghast Tear, act like ranged wolves),
     *  NPC: Necropolitan(Zombie/Skeletons that live in the Necropolis and function like Overworld Villagers, they trade Stygian Crystals)
     *  Construct: Skull Bat(skeleton head, 2 bat wings, and a charged soulgem make these, they dont need to recharge)
     *
     * Boss:
     *  Overworld: Masked Horror,
     *  Nether:
     *  Necropolis: Gravelord/Lich, Boneyard,
     *  Construct: Pumpking,
     *
     */
}
