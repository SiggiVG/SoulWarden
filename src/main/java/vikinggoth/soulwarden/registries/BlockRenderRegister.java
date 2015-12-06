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
        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.soulStone),
                modid + ":soulstone_default", modid + ":soulstone_mossy", modid + ":soulstone_cracked", modid + ":soulstone_smooth",
                modid + ":soulstone_brick", modid + ":soulstone_brick_mossy", modid + ":soulstone_brick_cracked", modid + ":soulstone_chiseled",
                modid + ":soulstone_cobble", modid + ":soulstone_cobble_mossy", modid + ":soulstone_scattered", modid + ":soulstone_small",
                modid + ":soulstone_ghost", modid + ":soulstone_skull", modid + ":soulstone_eye", modid + ":soulstone_creeper"
                );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.soulStoneSlab),
                modid + ":soulstone_default_slab", modid + ":soulstone_smooth_slab", modid + ":soulstone_brick_slab",
                modid + ":soulstone_cobble_slab", modid + ":soulstone_scattered_slab", modid + ":soulstone_small_slab"
                );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.soulStoneWall),
                modid + ":soulstone_default_wall", modid + ":soulstone_mossy_wall", modid + ":soulstone_cracked_wall", modid + ":soulstone_smooth_wall",
                modid + ":soulstone_brick_wall", modid + ":soulstone_brick_mossy_wall", modid + ":soulstone_brick_cracked_wall", modid + ":soulstone_chiseled_wall",
                modid + ":soulstone_cobble_wall", modid + ":soulstone_cobble_mossy_wall", modid + ":soulstone_scattered_wall", modid + ":soulstone_small_wall"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.logSW),
                modid + ":log_ghoul", modid + ":log_weepwillow", modid + ":log_bonebeech", modid + ":log_hand"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.logSW2),
                modid + ":log_alm", modid + ":log_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.log_wallSW),
                modid + ":log_wall_ghoul", modid + ":log_wall_weepwillow", modid + ":log_wall_bonebeech", modid + ":log_wall_hand",
                modid + ":log_wall_alm", modid + ":log_wall_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.plankSW),
                modid + ":plank_ghoul", modid + ":plank_weepwillow", modid + ":plank_bonebeech", modid + ":plank_hand",
                modid + ":plank_alm", modid + ":plank_pomegranate"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.plankSW),
                modid + ":plank_ghoul_slab", modid + ":plank_weepwillow_slab", modid + ":plank_bonebeech_slab", modid + ":plank_hand_slab",
                modid + ":plank_alm_slab", modid + ":plank_pomegranate_slab"
        );

        ModelBakery.addVariantName(Item.getItemFromBlock(ConfigBlocks.fenceSW),
                modid + ":fence_ghoul", modid + ":fence_weepwillow", modid + ":fence_bonebeech", modid + ":fence_hand",
                modid + ":fence_alm", modid + ":fence_pomegranate"
        );

        //TODO Doors
    }

    public static void registerBlockRenderer()
    {
        regBlock(ConfigBlocks.graveSoil);
        regBlock(ConfigBlocks.graveSoilTilled);
        regBlock(ConfigBlocks.grassCemetery);
        regBlock(ConfigBlocks.soulGravel);

        regBlock(ConfigBlocks.soulStone, 0, "soulstone_default");
        regBlock(ConfigBlocks.soulStone, 1, "soulstone_mossy");
        regBlock(ConfigBlocks.soulStone, 2, "soulstone_cracked");
        regBlock(ConfigBlocks.soulStone, 3, "soulstone_smooth");
        regBlock(ConfigBlocks.soulStone, 4, "soulstone_brick");
        regBlock(ConfigBlocks.soulStone, 5, "soulstone_brick_mossy");
        regBlock(ConfigBlocks.soulStone, 6, "soulstone_brick_cracked");
        regBlock(ConfigBlocks.soulStone, 7, "soulstone_chiseled");
        regBlock(ConfigBlocks.soulStone, 8, "soulstone_cobble");
        regBlock(ConfigBlocks.soulStone, 9, "soulstone_cobble_mossy");
        regBlock(ConfigBlocks.soulStone, 10, "soulstone_scattered");
        regBlock(ConfigBlocks.soulStone, 11, "soulstone_small");
        regBlock(ConfigBlocks.soulStone, 12, "soulstone_ghost");
        regBlock(ConfigBlocks.soulStone, 13, "soulstone_skull");
        regBlock(ConfigBlocks.soulStone, 14, "soulstone_eye");
        regBlock(ConfigBlocks.soulStone, 15, "soulstone_creeper");

        regBlock(ConfigBlocks.soulStoneBrickStairs);
        regBlock(ConfigBlocks.soulStoneCobbleStairs);
        regBlock(ConfigBlocks.soulStoneBrickScatteredStairs);
        regBlock(ConfigBlocks.soulStoneBrickSmallStairs);

        regBlock(ConfigBlocks.soulStoneSlab, 0, "soulstone_default_slab");
        regBlock(ConfigBlocks.soulStoneSlab, 1, "soulstone_smooth_slab");
        regBlock(ConfigBlocks.soulStoneSlab, 2, "soulstone_brick_slab");
        regBlock(ConfigBlocks.soulStoneSlab, 3, "soulstone_cobble_slab");
        regBlock(ConfigBlocks.soulStoneSlab, 4, "soulstone_scattered_slab");
        regBlock(ConfigBlocks.soulStoneSlab, 5, "soulstone_small_slab");

        regBlock(ConfigBlocks.soulStoneWall, 0, "soulstone_default_wall");
        regBlock(ConfigBlocks.soulStoneWall, 1, "soulstone_mossy_wall");
        regBlock(ConfigBlocks.soulStoneWall, 2, "soulstone_cracked_wall");
        regBlock(ConfigBlocks.soulStoneWall, 3, "soulstone_smooth_wall");
        regBlock(ConfigBlocks.soulStoneWall, 4, "soulstone_brick_wall");
        regBlock(ConfigBlocks.soulStoneWall, 5, "soulstone_brick_mossy_wall");
        regBlock(ConfigBlocks.soulStoneWall, 6, "soulstone_brick_cracked_wall");
        regBlock(ConfigBlocks.soulStoneWall, 7, "soulstone_chiseled_wall");
        regBlock(ConfigBlocks.soulStoneWall, 8, "soulstone_cobble_wall");
        regBlock(ConfigBlocks.soulStoneWall, 9, "soulstone_cobble_mossy_wall");
        regBlock(ConfigBlocks.soulStoneWall, 10, "soulstone_scattered_wall");
        regBlock(ConfigBlocks.soulStoneWall, 11, "soulstone_small_wall");
        regBlock(ConfigBlocks.soulStoneFence);

        regBlock(ConfigBlocks.logSW, 0, "log_ghoul");
        regBlock(ConfigBlocks.logSW, 1, "log_weepwillow");
        regBlock(ConfigBlocks.logSW, 2, "log_bonebeech");
        regBlock(ConfigBlocks.logSW, 3, "log_hand");
        regBlock(ConfigBlocks.logSW2, 0, "log_alm");
        regBlock(ConfigBlocks.logSW2, 1, "log_pomegranate");

        regBlock(ConfigBlocks.log_wallSW, 0, "log_wall_ghoul");
        regBlock(ConfigBlocks.log_wallSW, 1, "log_wall_weepwillow");
        regBlock(ConfigBlocks.log_wallSW, 2, "log_wall_bonebeech");
        regBlock(ConfigBlocks.log_wallSW, 3, "log_wall_hand");
        regBlock(ConfigBlocks.log_wallSW, 4, "log_wall_alm");
        regBlock(ConfigBlocks.log_wallSW, 5, "log_wall_pomegranate");

        regBlock(ConfigBlocks.plankSW, 0, "plank_ghoul");
        regBlock(ConfigBlocks.plankSW, 1, "plank_weepwillow");
        regBlock(ConfigBlocks.plankSW, 2, "plank_bonebeech");
        regBlock(ConfigBlocks.plankSW, 3, "plank_hand");
        regBlock(ConfigBlocks.plankSW, 4, "plank_alm");
        regBlock(ConfigBlocks.plankSW, 5, "plank_pomegranate");

        regBlock(ConfigBlocks.planksSWSlab, 0, "plank_ghoul_slab");
        regBlock(ConfigBlocks.planksSWSlab, 1, "plank_weepwillow_slab");
        regBlock(ConfigBlocks.planksSWSlab, 2, "plank_bonebeech_slab");
        regBlock(ConfigBlocks.planksSWSlab, 3, "plank_hand_slab");
        regBlock(ConfigBlocks.planksSWSlab, 4, "plank_alm_slab");
        regBlock(ConfigBlocks.planksSWSlab, 5, "plank_pomegranate_slab");

        regBlock(ConfigBlocks.ghoulStairs);
        regBlock(ConfigBlocks.weepwillowStairs);
        regBlock(ConfigBlocks.bonebeechStairs);
        regBlock(ConfigBlocks.handStairs);
        regBlock(ConfigBlocks.alnwickStairs);
        regBlock(ConfigBlocks.pomegranateStairs);

        regBlock(ConfigBlocks.fenceSW, 0, "fence_ghoul");
        regBlock(ConfigBlocks.fenceSW, 1, "fence_weepwillow");
        regBlock(ConfigBlocks.fenceSW, 2, "fence_bonebeech");
        regBlock(ConfigBlocks.fenceSW, 3, "fence_hand");
        regBlock(ConfigBlocks.fenceSW, 4, "fence_alm");
        regBlock(ConfigBlocks.fenceSW, 5, "fence_pomegranate");

        //TODO Doors

        //Bone
        regBlock(ConfigBlocks.bonePile);
        regBlock(ConfigBlocks.boneBlock);
        regBlock(ConfigBlocks.boneWall);
        regBlock(ConfigBlocks.boneFence);

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
