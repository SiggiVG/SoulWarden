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
        //Do I even need any of this?
        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.soulStone),
                modid + ":soulstone_default", modid + ":soulstone_mossy", modid + ":soulstone_cracked", modid + ":soulstone_smooth",
                modid + ":soulstone_brick", modid + ":soulstone_brick_mossy", modid + ":soulstone_brick_cracked", modid + ":soulstone_chiseled",
                modid + ":soulstone_cobble", modid + ":soulstone_cobble_mossy", modid + ":soulstone_scattered", modid + ":soulstone_small",
                modid + ":soulstone_ghost", modid + ":soulstone_skull", modid + ":soulstone_eye", modid + ":soulstone_creeper"
                );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.soulStoneSlab),
                modid + ":soulstone_default_slab", modid + ":soulstone_smooth_slab", modid + ":soulstone_brick_slab",
                modid + ":soulstone_cobble_slab", modid + ":soulstone_scattered_slab", modid + ":soulstone_small_slab"
                );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.soulStoneWall),
                modid + ":soulstone_default_wall", modid + ":soulstone_mossy_wall", modid + ":soulstone_cracked_wall", modid + ":soulstone_smooth_wall",
                modid + ":soulstone_brick_wall", modid + ":soulstone_brick_mossy_wall", modid + ":soulstone_brick_cracked_wall", modid + ":soulstone_chiseled_wall",
                modid + ":soulstone_cobble_wall", modid + ":soulstone_cobble_mossy_wall", modid + ":soulstone_scattered_wall", modid + ":soulstone_small_wall"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.logSW),
                modid + ":log_ghoul", modid + ":log_weepwillow", modid + ":log_bonebeech", modid + ":log_hand"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.logSW2),
                modid + ":log_alm", modid + ":log_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.log_wallSW),
                modid + ":log_wall_ghoul", modid + ":log_wall_weepwillow", modid + ":log_wall_bonebeech", modid + ":log_wall_hand",
                modid + ":log_wall_alm", modid + ":log_wall_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.plankSW),
                modid + ":plank_ghoul", modid + ":plank_weepwillow", modid + ":plank_bonebeech", modid + ":plank_hand",
                modid + ":plank_alm", modid + ":plank_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.planksSWSlab),
                modid + ":plank_ghoul_slab", modid + ":plank_weepwillow_slab", modid + ":plank_bonebeech_slab", modid + ":plank_hand_slab",
                modid + ":plank_alm_slab", modid + ":plank_pomegranate_slab"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.fenceSW),
                modid + ":fence_ghoul", modid + ":fence_weepwillow", modid + ":fence_bonebeech", modid + ":fence_hand",
                modid + ":fence_alm", modid + ":fence_pomegranate"
        );

        //TODO Doors

        //Ores
        ModelBakery.addVariantName(Item.getItemFromBlock(BlockRegistry.ore_soulgem),
                modid + ":ore_soulgem", modid + ":ore_soulgem_nether", modid + ":ore_soulgem_necro"
        );
    }

    public static void registerBlockRenderer()
    {
        regBlock(BlockRegistry.graveSoil);
        regBlock(BlockRegistry.graveSoilTilled);
        regBlock(BlockRegistry.grassCemetery);
        regBlock(BlockRegistry.soulGravel);

        regBlock(BlockRegistry.soulStone, 0, "soulstone_default");
        regBlock(BlockRegistry.soulStone, 1, "soulstone_mossy");
        regBlock(BlockRegistry.soulStone, 2, "soulstone_cracked");
        regBlock(BlockRegistry.soulStone, 3, "soulstone_smooth");
        regBlock(BlockRegistry.soulStone, 4, "soulstone_brick");
        regBlock(BlockRegistry.soulStone, 5, "soulstone_brick_mossy");
        regBlock(BlockRegistry.soulStone, 6, "soulstone_brick_cracked");
        regBlock(BlockRegistry.soulStone, 7, "soulstone_chiseled");
        regBlock(BlockRegistry.soulStone, 8, "soulstone_cobble");
        regBlock(BlockRegistry.soulStone, 9, "soulstone_cobble_mossy");
        regBlock(BlockRegistry.soulStone, 10, "soulstone_scattered");
        regBlock(BlockRegistry.soulStone, 11, "soulstone_small");
        regBlock(BlockRegistry.soulStone, 12, "soulstone_ghost");
        regBlock(BlockRegistry.soulStone, 13, "soulstone_skull");
        regBlock(BlockRegistry.soulStone, 14, "soulstone_eye");
        regBlock(BlockRegistry.soulStone, 15, "soulstone_creeper");

        regBlock(BlockRegistry.soulStoneBrickStairs);
        regBlock(BlockRegistry.soulStoneCobbleStairs);
        regBlock(BlockRegistry.soulStoneBrickScatteredStairs);
        regBlock(BlockRegistry.soulStoneBrickSmallStairs);

        regBlock(BlockRegistry.soulStoneSlab, 0, "soulstone_default_slab");
        regBlock(BlockRegistry.soulStoneSlab, 1, "soulstone_smooth_slab");
        regBlock(BlockRegistry.soulStoneSlab, 2, "soulstone_brick_slab");
        regBlock(BlockRegistry.soulStoneSlab, 3, "soulstone_cobble_slab");
        regBlock(BlockRegistry.soulStoneSlab, 4, "soulstone_scattered_slab");
        regBlock(BlockRegistry.soulStoneSlab, 5, "soulstone_small_slab");

        regBlock(BlockRegistry.soulStoneWall, 0, "soulstone_default_wall");
        regBlock(BlockRegistry.soulStoneWall, 1, "soulstone_mossy_wall");
        regBlock(BlockRegistry.soulStoneWall, 2, "soulstone_cracked_wall");
        regBlock(BlockRegistry.soulStoneWall, 3, "soulstone_smooth_wall");
        regBlock(BlockRegistry.soulStoneWall, 4, "soulstone_brick_wall");
        regBlock(BlockRegistry.soulStoneWall, 5, "soulstone_brick_mossy_wall");
        regBlock(BlockRegistry.soulStoneWall, 6, "soulstone_brick_cracked_wall");
        regBlock(BlockRegistry.soulStoneWall, 7, "soulstone_chiseled_wall");
        regBlock(BlockRegistry.soulStoneWall, 8, "soulstone_cobble_wall");
        regBlock(BlockRegistry.soulStoneWall, 9, "soulstone_cobble_mossy_wall");
        regBlock(BlockRegistry.soulStoneWall, 10, "soulstone_scattered_wall");
        regBlock(BlockRegistry.soulStoneWall, 11, "soulstone_small_wall");
        regBlock(BlockRegistry.soulStoneFence);

        regBlock(BlockRegistry.logSW, 0, "log_ghoul");
        regBlock(BlockRegistry.logSW, 1, "log_weepwillow");
        regBlock(BlockRegistry.logSW, 2, "log_bonebeech");
        regBlock(BlockRegistry.logSW, 3, "log_hand");
        regBlock(BlockRegistry.logSW2, 0, "log_alm");
        regBlock(BlockRegistry.logSW2, 1, "log_pomegranate");

        regBlock(BlockRegistry.log_wallSW, 0, "log_wall_ghoul");
        regBlock(BlockRegistry.log_wallSW, 1, "log_wall_weepwillow");
        regBlock(BlockRegistry.log_wallSW, 2, "log_wall_bonebeech");
        regBlock(BlockRegistry.log_wallSW, 3, "log_wall_hand");
        regBlock(BlockRegistry.log_wallSW, 4, "log_wall_alm");
        regBlock(BlockRegistry.log_wallSW, 5, "log_wall_pomegranate");

        regBlock(BlockRegistry.plankSW, 0, "plank_ghoul");
        regBlock(BlockRegistry.plankSW, 1, "plank_weepwillow");
        regBlock(BlockRegistry.plankSW, 2, "plank_bonebeech");
        regBlock(BlockRegistry.plankSW, 3, "plank_hand");
        regBlock(BlockRegistry.plankSW, 4, "plank_alm");
        regBlock(BlockRegistry.plankSW, 5, "plank_pomegranate");

        regBlock(BlockRegistry.planksSWSlab, 0, "plank_ghoul_slab");
        regBlock(BlockRegistry.planksSWSlab, 1, "plank_weepwillow_slab");
        regBlock(BlockRegistry.planksSWSlab, 2, "plank_bonebeech_slab");
        regBlock(BlockRegistry.planksSWSlab, 3, "plank_hand_slab");
        regBlock(BlockRegistry.planksSWSlab, 4, "plank_alm_slab");
        regBlock(BlockRegistry.planksSWSlab, 5, "plank_pomegranate_slab");

        regBlock(BlockRegistry.ghoulStairs);
        regBlock(BlockRegistry.weepwillowStairs);
        regBlock(BlockRegistry.bonebeechStairs);
        regBlock(BlockRegistry.handStairs);
        regBlock(BlockRegistry.alnwickStairs);
        regBlock(BlockRegistry.pomegranateStairs);

        regBlock(BlockRegistry.fenceSW, 0, "fence_ghoul");
        regBlock(BlockRegistry.fenceSW, 1, "fence_weepwillow");
        regBlock(BlockRegistry.fenceSW, 2, "fence_bonebeech");
        regBlock(BlockRegistry.fenceSW, 3, "fence_hand");
        regBlock(BlockRegistry.fenceSW, 4, "fence_alm");
        regBlock(BlockRegistry.fenceSW, 5, "fence_pomegranate");

        //TODO Doors

        //Bone
        regBlock(BlockRegistry.bonePile);
        regBlock(BlockRegistry.boneBlock);
        regBlock(BlockRegistry.boneWall);
        regBlock(BlockRegistry.boneFence);

        //Urns
        regBlock(BlockRegistry.urnlarge, 0, "urnlarge_soulstone");
        regBlock(BlockRegistry.urnlarge, 1, "urnlarge_porcelain");
        regBlock(BlockRegistry.urnlarge, 2, "urnlarge_clay");
        regBlock(BlockRegistry.urnlarge, 3, "urnlarge_greek");
        regBlock(BlockRegistry.urnmedium, 0, "urnmedium_soulstone");
        regBlock(BlockRegistry.urnmedium, 1, "urnmedium_porcelain");
        regBlock(BlockRegistry.urnmedium, 2, "urnmedium_clay");
        regBlock(BlockRegistry.urnmedium, 3, "urnmedium_greek");
        regBlock(BlockRegistry.urnsmall, 0, "urnsmall_soulstone");
        regBlock(BlockRegistry.urnsmall, 1, "urnsmall_porcelain");
        regBlock(BlockRegistry.urnsmall, 2, "urnsmall_clay");
        regBlock(BlockRegistry.urnsmall, 3, "urnsmall_greek");

        //Ores
        regBlock(BlockRegistry.ore_rostygold);
        regBlock(BlockRegistry.ore_pewter);
        regBlock(BlockRegistry.ore_soulgem_black);
        regBlock(BlockRegistry.ore_hematite);
        regBlock(BlockRegistry.ore_soulgem, 0, "ore_soulgem");
        regBlock(BlockRegistry.ore_soulgem, 1, "ore_soulgem_nether");
        regBlock(BlockRegistry.ore_soulgem, 2, "ore_soulgem_necro");

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
