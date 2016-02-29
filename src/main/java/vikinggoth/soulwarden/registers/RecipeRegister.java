package vikinggoth.soulwarden.registers;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class RecipeRegister
{
    public static void initCrafting()
    {
        initBlockCrafting();
        initItemCrafting();
        //Soul Forge Recipes
        //initSoulForgeCrafting();
        //
        //initModCrafting();
    }

    public static void initBlockCrafting()
    {
        //SoulStone
        //Vanilla types can also be crafted in the sepulter
        //cobble to default
        GameRegistry.addSmelting(new ItemStack(BlockRegister.soulStone, 1, 0), new ItemStack(BlockRegister.soulStone, 1, 8), 0.35F );
        //smooth to mossy
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegister.soulStone, 4, 1), new ItemStack(BlockRegister.soulStone, 1, 3), Item.getItemFromBlock(Blocks.vine));
        //default to cracked
        GameRegistry.addSmelting(new ItemStack(BlockRegister.soulStone, 1, 2), new ItemStack(BlockRegister.soulStone, 1, 0), 0.35F );
        //default to smooth
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStone, 4, 3), "##", "##", '#', new ItemStack(BlockRegister.soulStone, 1, 0));
        //smooth to brick
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStone, 4, 4), "##", "##", '#', new ItemStack(BlockRegister.soulStone, 1, 3));
        //brick to mossy brick
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegister.soulStone, 4, 5), new ItemStack(BlockRegister.soulStone, 1, 4), Item.getItemFromBlock(Blocks.vine));
        //brick to cracked brick
        GameRegistry.addSmelting(new ItemStack(BlockRegister.soulStone, 1, 6), new ItemStack(BlockRegister.soulStone, 1, 4), 0.35F);
        //slabs to chiseled
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStone, 1, 7), "#", "#", '#', new ItemStack(BlockRegister.soulStoneSlab, 1, 2));
        //cracked to cobble
        GameRegistry.addSmelting(new ItemStack(BlockRegister.soulStone, 1, 8), new ItemStack(BlockRegister.soulStone, 1, 2), 0.35F );
        //cobble to mossy cobble
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegister.soulStone, 4, 9), new ItemStack(BlockRegister.soulStone, 1, 8), Item.getItemFromBlock(Blocks.vine));
        //smallbrick to scattered brick
        GameRegistry.addSmelting(new ItemStack(BlockRegister.soulStone, 1, 10), new ItemStack(BlockRegister.soulStone, 1, 11), 0.35F );
        //cobble to smallbrick
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStone, 4, 11), "##", "##", '#', new ItemStack(BlockRegister.soulStone, 1, 8));

        //Slabs
        //default
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneSlab, 6, 0), "###", '#', new ItemStack(BlockRegister.soulStone, 1, 0));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneSlab, 6, 1), "###", '#', new ItemStack(BlockRegister.soulStone, 1, 2));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneSlab, 6, 2), "###", '#', new ItemStack(BlockRegister.soulStone, 1, 3));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneSlab, 6, 3), "###", '#', new ItemStack(BlockRegister.soulStone, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneSlab, 6, 4), "###", '#', new ItemStack(BlockRegister.soulStone, 1, 8));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneSlab, 6, 5), "###", '#', new ItemStack(BlockRegister.soulStone, 1, 10));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneSlab, 6, 6), "###", '#', new ItemStack(BlockRegister.soulStone, 1, 11));

        //Stairs
        //brick
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneBrickStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneBrickStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 4));
        //cobble
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneCobbleStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 8));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneCobbleStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 8));
        //scattered
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneBrickScatteredStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 10));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneBrickScatteredStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 10));
        //small
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneBrickSmallStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 11));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneBrickSmallStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 11));

        //Walls
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 0), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 0));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 1), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 1));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 2), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 2));
        //GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 3), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 3));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 4), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 5), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 5));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 6), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 6));
        //GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 7), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 7));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 8), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 8));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 9), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 9));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 10), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 10));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneWall, 6, 11), "###", "###", '#', new ItemStack(BlockRegister.soulStone, 1, 11));

        //Fence
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulStoneFence, 2), "#", "#", '#', new ItemStack(BlockRegister.soulStone, 1, 3));

        //TODO log to logwall and back, planks, slab and fence
        //Walls
        //To
        GameRegistry.addRecipe(new ItemStack(BlockRegister.log_wallSW, 6, 0), "###", "###", '#', new ItemStack(BlockRegister.logSW, 1, 0));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.log_wallSW, 6, 1), "###", "###", '#', new ItemStack(BlockRegister.logSW, 1, 1));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.log_wallSW, 6, 2), "###", "###", '#', new ItemStack(BlockRegister.logSW, 1, 2));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.log_wallSW, 6, 3), "###", "###", '#', new ItemStack(BlockRegister.logSW, 1, 3));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.log_wallSW, 6, 4), "###", "###", '#', new ItemStack(BlockRegister.logSW2, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.log_wallSW, 6, 5), "###", "###", '#', new ItemStack(BlockRegister.logSW2, 1, 5));
        //Back
        GameRegistry.addRecipe(new ItemStack(BlockRegister.logSW, 6, 0), "###", "###", '#', new ItemStack(BlockRegister.log_wallSW, 1, 0));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.logSW, 6, 1), "###", "###", '#', new ItemStack(BlockRegister.log_wallSW, 1, 1));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.logSW, 6, 2), "###", "###", '#', new ItemStack(BlockRegister.log_wallSW, 1, 2));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.logSW, 6, 3), "###", "###", '#', new ItemStack(BlockRegister.log_wallSW, 1, 3));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.logSW2, 6, 4), "###", "###", '#', new ItemStack(BlockRegister.log_wallSW, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.logSW2, 6, 5), "###", "###", '#', new ItemStack(BlockRegister.log_wallSW, 1, 5));

        //Planks
        GameRegistry.addRecipe(new ItemStack(BlockRegister.plankSW, 4, 0), "#", '#', new ItemStack(BlockRegister.logSW, 1, 0));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.plankSW, 4, 1), "#", '#', new ItemStack(BlockRegister.logSW, 1, 1));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.plankSW, 4, 2), "#", '#', new ItemStack(BlockRegister.logSW, 1, 2));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.plankSW, 4, 3), "#", '#', new ItemStack(BlockRegister.logSW, 1, 3));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.plankSW, 4, 4), "#", '#', new ItemStack(BlockRegister.logSW, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.plankSW, 4, 5), "#", '#', new ItemStack(BlockRegister.logSW, 1, 5));

        //Plank Slab
        GameRegistry.addRecipe(new ItemStack(BlockRegister.planksSWSlab, 6, 0), "###", '#', new ItemStack(BlockRegister.plankSW, 1, 0));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.planksSWSlab, 6, 1), "###", '#', new ItemStack(BlockRegister.plankSW, 1, 2));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.planksSWSlab, 6, 2), "###", '#', new ItemStack(BlockRegister.plankSW, 1, 3));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.planksSWSlab, 6, 3), "###", '#', new ItemStack(BlockRegister.plankSW, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.planksSWSlab, 6, 4), "###", '#', new ItemStack(BlockRegister.plankSW, 1, 8));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.planksSWSlab, 6, 5), "###", '#', new ItemStack(BlockRegister.plankSW, 1, 10));

        //Stairs
        //ghoul
        GameRegistry.addRecipe(new ItemStack(BlockRegister.ghoulStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 1));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.ghoulStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 1));
        //weepwillow
        GameRegistry.addRecipe(new ItemStack(BlockRegister.weepwillowStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 2));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.weepwillowStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 2));
        //bonebeech
        GameRegistry.addRecipe(new ItemStack(BlockRegister.bonebeechStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 3));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.bonebeechStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 3));
        //hand
        GameRegistry.addRecipe(new ItemStack(BlockRegister.handStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 4));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.handStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 4));
        //alnwick
        GameRegistry.addRecipe(new ItemStack(BlockRegister.alnwickStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 5));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.alnwickStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 5));
        //pomegranate
        GameRegistry.addRecipe(new ItemStack(BlockRegister.pomegranateStairs, 4), "#  ", "## ", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 6));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.pomegranateStairs, 4), "  #", " ##", "###", '#', new ItemStack(BlockRegister.plankSW, 1, 6));

        //Fence
        GameRegistry.addRecipe(new ItemStack(BlockRegister.fenceSW, 3, 0), "#S#", "#S#", '#', new ItemStack(BlockRegister.plankSW, 1, 0), 'S', new ItemStack(Items.stick));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.fenceSW, 3, 1), "#S#", "#S#", '#', new ItemStack(BlockRegister.plankSW, 1, 1), 'S', new ItemStack(Items.stick));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.fenceSW, 3, 2), "#S#", "#S#", '#', new ItemStack(BlockRegister.plankSW, 1, 2), 'S', new ItemStack(Items.stick));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.fenceSW, 3, 3), "#S#", "#S#", '#', new ItemStack(BlockRegister.plankSW, 1, 3), 'S', new ItemStack(Items.stick));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.fenceSW, 3, 4), "#S#", "#S#", '#', new ItemStack(BlockRegister.plankSW, 1, 4), 'S', new ItemStack(Items.stick));
        GameRegistry.addRecipe(new ItemStack(BlockRegister.fenceSW, 3, 5), "#S#", "#S#", '#', new ItemStack(BlockRegister.plankSW, 1, 5), 'S', new ItemStack(Items.stick));



        //TODO bone block, pile, wall, fence, pillar and baluster

        //Soul Gravel, it's a soul sand textured block that doesnt slow you down
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulGravel, 4), "#I", "I#", '#', Blocks.gravel, 'I', Blocks.soul_sand);
        GameRegistry.addRecipe(new ItemStack(BlockRegister.soulGravel, 4), "I#", "#I", '#', Blocks.gravel, 'I', Blocks.soul_sand);
    }

    public static void initItemCrafting()
    {

    }
}
