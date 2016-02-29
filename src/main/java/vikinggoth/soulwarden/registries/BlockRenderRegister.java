package vikinggoth.soulwarden.registries;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import vikinggoth.soulwarden.reference.Reference;

/**
 * Created by Friedrich on 8/18/2015.
 */
public final class BlockRenderRegister
{
    private static String modid = Reference.MODID;

    public static void preInit()
    {

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.soulStone),
                modid + ":soulstone_default", modid + ":soulstone_mossy", modid + ":soulstone_cracked", modid + ":soulstone_smooth",
                modid + ":soulstone_brick", modid + ":soulstone_brick_mossy", modid + ":soulstone_brick_cracked", modid + ":soulstone_chiseled",
                modid + ":soulstone_cobble", modid + ":soulstone_cobble_mossy", modid + ":soulstone_scattered", modid + ":soulstone_small",
                modid + ":sodalite", modid + ":sodalite_smooth", modid + ":nachtite", modid + ":nachtite_smooth"
                );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.soulStoneSlab),
                modid + ":soulstone_default_slab", modid + ":soulstone_smooth_slab", modid + ":soulstone_brick_slab",
                modid + ":soulstone_cobble_slab", modid + ":soulstone_scattered_slab", modid + ":soulstone_small_slab"
                );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.soulStoneWall),
                modid + ":soulstone_default_wall", modid + ":soulstone_mossy_wall", modid + ":soulstone_cracked_wall", modid + ":soulstone_smooth_wall",
                modid + ":soulstone_brick_wall", modid + ":soulstone_brick_mossy_wall", modid + ":soulstone_brick_cracked_wall", modid + ":soulstone_chiseled_wall",
                modid + ":soulstone_cobble_wall", modid + ":soulstone_cobble_mossy_wall", modid + ":soulstone_scattered_wall", modid + ":soulstone_small_wall"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.logSW),
                modid + ":log_ghoul", modid + ":log_weepwillow", modid + ":log_bonebeech", modid + ":log_hand"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.logSW2),
                modid + ":log_alnwick", modid + ":log_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.log_wallSW),
                modid + ":log_wall_ghoul", modid + ":log_wall_weepwillow", modid + ":log_wall_bonebeech", modid + ":log_wall_hand",
                modid + ":log_wall_alnwick", modid + ":log_wall_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.plankSW),
                modid + ":plank_ghoul", modid + ":plank_weepwillow", modid + ":plank_bonebeech", modid + ":plank_hand",
                modid + ":plank_alnwick", modid + ":plank_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.planksSWSlab),
                modid + ":plank_ghoul_slab", modid + ":plank_weepwillow_slab", modid + ":plank_bonebeech_slab", modid + ":plank_hand_slab",
                modid + ":plank_alnwick_slab", modid + ":plank_pomegranate_slab"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.fenceSW),
                modid + ":fence_ghoul", modid + ":fence_weepwillow", modid + ":fence_bonebeech", modid + ":fence_hand",
                modid + ":fence_alnwick", modid + ":fence_pomegranate"
        );

        //TODO Doors

        //Ores
        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegister.ore_soulgem),
                modid + ":ore_soulgem", modid + ":ore_soulgem_nether", modid + ":ore_soulgem_necro"
        );
    }

    //These are the item models for the blocks
    public static void registerBlockRenderer()
    {
        regBlock(BlockRegister.graveSoil);
        regBlock(BlockRegister.graveSoilTilled);
        regBlock(BlockRegister.grassCemetery);
        regBlock(BlockRegister.soulGravel);

        regBlock(BlockRegister.soulStone, 0, "soulstone_default");
        regBlock(BlockRegister.soulStone, 1, "soulstone_mossy");
        regBlock(BlockRegister.soulStone, 2, "soulstone_cracked");
        regBlock(BlockRegister.soulStone, 3, "soulstone_smooth");
        regBlock(BlockRegister.soulStone, 4, "soulstone_brick");
        regBlock(BlockRegister.soulStone, 5, "soulstone_brick_mossy");
        regBlock(BlockRegister.soulStone, 6, "soulstone_brick_cracked");
        regBlock(BlockRegister.soulStone, 7, "soulstone_chiseled");
        regBlock(BlockRegister.soulStone, 8, "soulstone_cobble");
        regBlock(BlockRegister.soulStone, 9, "soulstone_cobble_mossy");
        regBlock(BlockRegister.soulStone, 10, "soulstone_scattered");
        regBlock(BlockRegister.soulStone, 11, "soulstone_small");
        regBlock(BlockRegister.soulStone, 12, "sodalite");
        regBlock(BlockRegister.soulStone, 13, "sodalite_smooth");
        regBlock(BlockRegister.soulStone, 14, "nachtite");
        regBlock(BlockRegister.soulStone, 15, "nachtite_smooth");

        regBlock(BlockRegister.soulStoneBrickStairs);
        regBlock(BlockRegister.soulStoneCobbleStairs);
        regBlock(BlockRegister.soulStoneBrickScatteredStairs);
        regBlock(BlockRegister.soulStoneBrickSmallStairs);

        regBlock(BlockRegister.soulStoneSlab, 0, "soulstone_default_slab");
        regBlock(BlockRegister.soulStoneSlab, 1, "soulstone_smooth_slab");
        regBlock(BlockRegister.soulStoneSlab, 2, "soulstone_brick_slab");
        regBlock(BlockRegister.soulStoneSlab, 3, "soulstone_cobble_slab");
        regBlock(BlockRegister.soulStoneSlab, 4, "soulstone_scattered_slab");
        regBlock(BlockRegister.soulStoneSlab, 5, "soulstone_small_slab");

        regBlock(BlockRegister.soulStoneWall, 0, "soulstone_default_wall");
        regBlock(BlockRegister.soulStoneWall, 1, "soulstone_mossy_wall");
        regBlock(BlockRegister.soulStoneWall, 2, "soulstone_cracked_wall");
        regBlock(BlockRegister.soulStoneWall, 3, "soulstone_smooth_wall");
        regBlock(BlockRegister.soulStoneWall, 4, "soulstone_brick_wall");
        regBlock(BlockRegister.soulStoneWall, 5, "soulstone_brick_mossy_wall");
        regBlock(BlockRegister.soulStoneWall, 6, "soulstone_brick_cracked_wall");
        regBlock(BlockRegister.soulStoneWall, 7, "soulstone_chiseled_wall");
        regBlock(BlockRegister.soulStoneWall, 8, "soulstone_cobble_wall");
        regBlock(BlockRegister.soulStoneWall, 9, "soulstone_cobble_mossy_wall");
        regBlock(BlockRegister.soulStoneWall, 10, "soulstone_scattered_wall");
        regBlock(BlockRegister.soulStoneWall, 11, "soulstone_small_wall");
        regBlock(BlockRegister.soulStoneFence);

        regBlock(BlockRegister.stoneSW, 0, "sodalite");
        regBlock(BlockRegister.stoneSW, 1, "sodalite_smooth");

        regBlock(BlockRegister.logSW, 0, "log_ghoul");
        regBlock(BlockRegister.logSW, 1, "log_weepwillow");
        regBlock(BlockRegister.logSW, 2, "log_bonebeech");
        regBlock(BlockRegister.logSW, 3, "log_hand");
        regBlock(BlockRegister.logSW2, 0, "log_alnwick");
        regBlock(BlockRegister.logSW2, 1, "log_pomegranate");

        regBlock(BlockRegister.log_wallSW, 0, "log_wall_ghoul");
        regBlock(BlockRegister.log_wallSW, 1, "log_wall_weepwillow");
        regBlock(BlockRegister.log_wallSW, 2, "log_wall_bonebeech");
        regBlock(BlockRegister.log_wallSW, 3, "log_wall_hand");
        regBlock(BlockRegister.log_wallSW, 4, "log_wall_alnwick");
        regBlock(BlockRegister.log_wallSW, 5, "log_wall_pomegranate");

        regBlock(BlockRegister.plankSW, 0, "plank_ghoul");
        regBlock(BlockRegister.plankSW, 1, "plank_weepwillow");
        regBlock(BlockRegister.plankSW, 2, "plank_bonebeech");
        regBlock(BlockRegister.plankSW, 3, "plank_hand");
        regBlock(BlockRegister.plankSW, 4, "plank_alnwick");
        regBlock(BlockRegister.plankSW, 5, "plank_pomegranate");

        regBlock(BlockRegister.planksSWSlab, 0, "plank_ghoul_slab");
        regBlock(BlockRegister.planksSWSlab, 1, "plank_weepwillow_slab");
        regBlock(BlockRegister.planksSWSlab, 2, "plank_bonebeech_slab");
        regBlock(BlockRegister.planksSWSlab, 3, "plank_hand_slab");
        regBlock(BlockRegister.planksSWSlab, 4, "plank_alnwick_slab");
        regBlock(BlockRegister.planksSWSlab, 5, "plank_pomegranate_slab");

        regBlock(BlockRegister.ghoulStairs);
        regBlock(BlockRegister.weepwillowStairs);
        regBlock(BlockRegister.bonebeechStairs);
        regBlock(BlockRegister.handStairs);
        regBlock(BlockRegister.alnwickStairs);
        regBlock(BlockRegister.pomegranateStairs);

        regBlock(BlockRegister.fenceSW, 0, "fence_ghoul");
        regBlock(BlockRegister.fenceSW, 1, "fence_weepwillow");
        regBlock(BlockRegister.fenceSW, 2, "fence_bonebeech");
        regBlock(BlockRegister.fenceSW, 3, "fence_hand");
        regBlock(BlockRegister.fenceSW, 4, "fence_alnwick");
        regBlock(BlockRegister.fenceSW, 5, "fence_pomegranate");

        //TODO Doors

        //Bone
        regBlock(BlockRegister.bonePile);
        regBlock(BlockRegister.boneBlock);
        regBlock(BlockRegister.boneWall);
        regBlock(BlockRegister.boneFence);

        //Urns
        regBlock(BlockRegister.urnlarge, 0, "urnlarge_soulstone");
        regBlock(BlockRegister.urnlarge, 1, "urnlarge_porcelain");
        regBlock(BlockRegister.urnlarge, 2, "urnlarge_clay");
        regBlock(BlockRegister.urnlarge, 3, "urnlarge_greek");
        regBlock(BlockRegister.urnmedium, 0, "urnmedium_soulstone");
        regBlock(BlockRegister.urnmedium, 1, "urnmedium_porcelain");
        regBlock(BlockRegister.urnmedium, 2, "urnmedium_clay");
        regBlock(BlockRegister.urnmedium, 3, "urnmedium_greek");
        regBlock(BlockRegister.urnsmall, 0, "urnsmall_soulstone");
        regBlock(BlockRegister.urnsmall, 1, "urnsmall_porcelain");
        regBlock(BlockRegister.urnsmall, 2, "urnsmall_clay");
        regBlock(BlockRegister.urnsmall, 3, "urnsmall_greek");

        //Ores
        regBlock(BlockRegister.ore_rostygold);
        regBlock(BlockRegister.ore_pewter);
        regBlock(BlockRegister.ore_soulgem_black);
        regBlock(BlockRegister.ore_hematite);
        regBlock(BlockRegister.ore_soulgem, 0, "ore_soulgem");
        regBlock(BlockRegister.ore_soulgem, 1, "ore_soulgem_nether");
        regBlock(BlockRegister.ore_soulgem, 2, "ore_soulgem_necro");

        //Metals
        regBlock(BlockRegister.block_soulgem);
        regBlock(BlockRegister.block_soulgem_black);
        regBlock(BlockRegister.block_hematite);

    }

    private static void regBlock(Block block)
    {
        regBlock(block, 0, block.getUnlocalizedName().substring(5));
    }

    private static void regBlock(Block block, int meta, String file)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(modid + ":" + file, "inventory"));
    }
}
