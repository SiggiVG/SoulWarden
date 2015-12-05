package vikinggoth.soulwarden.registries;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Friedrich on 8/18/2015.
 */
public class ConfigRecipes
{
    public static void initCrafting()
    {
        //SoulStone
        //Vanilla types can also be crafted in the sepulter
        //default to smooth
        GameRegistry.addRecipe(new ItemStack(ConfigBlocks.soulStone, 4, 3), new Object[] {"##", "##", '#', new ItemStack(ConfigBlocks.soulStone, 1, 0)});
        //smooth to brick
        GameRegistry.addRecipe(new ItemStack(ConfigBlocks.soulStone, 4, 4), new Object[] {"##", "##", '#', new ItemStack(ConfigBlocks.soulStone, 1, 3)});
        //cobble to smallbrick
        GameRegistry.addRecipe(new ItemStack(ConfigBlocks.soulStone, 4, 11), new Object[] {"##", "##", '#', new ItemStack(ConfigBlocks.soulStone, 1, 8)});
        //smooth to mossy
        GameRegistry.addShapelessRecipe(new ItemStack(ConfigBlocks.soulStone, 4, 1), new Object[]{new ItemStack(ConfigBlocks.soulStone, 1, 3), Item.getItemFromBlock(Blocks.vine)});
        //brick to mossy bick
        GameRegistry.addShapelessRecipe(new ItemStack(ConfigBlocks.soulStone, 4, 5), new Object[]{new ItemStack(ConfigBlocks.soulStone, 1, 4), Item.getItemFromBlock(Blocks.vine)});
        //cobble to mossy cobble
        GameRegistry.addShapelessRecipe(new ItemStack(ConfigBlocks.soulStone, 4, 9), new Object[]{new ItemStack(ConfigBlocks.soulStone, 1, 8), Item.getItemFromBlock(Blocks.vine)});
        //default to cracked
        GameRegistry.addSmelting(new ItemStack(ConfigBlocks.soulStone, 1, 2), new ItemStack(ConfigBlocks.soulStone, 1, 0), 0.35F );
        //cracked to cobble
        GameRegistry.addSmelting(new ItemStack(ConfigBlocks.soulStone, 1, 8), new ItemStack(ConfigBlocks.soulStone, 1, 2), 0.35F );
        //cobble to default
        GameRegistry.addSmelting(new ItemStack(ConfigBlocks.soulStone, 1, 0), new ItemStack(ConfigBlocks.soulStone, 1, 8), 0.35F );
        //smallbrick to scattered brick
        GameRegistry.addSmelting(new ItemStack(ConfigBlocks.soulStone, 1, 10), new ItemStack(ConfigBlocks.soulStone, 1, 11), 0.35F );
        //slabs to chiseled
        GameRegistry.addRecipe(new ItemStack(ConfigBlocks.soulStone, 1, 7), new Object[] {"#", "#", '#', new ItemStack(ConfigBlocks.soulStoneSlab, 1, 2)});

        //TODO soulstone slab, stair, wall recipes

        //TODO log to logwall and back, planks, slab and

        //TODO bone block, pile, wall, fence, pillar and baluster

        //Soul Gravel, it's a soul sand textured block that doesnt slow you down
        GameRegistry.addRecipe(new ItemStack(ConfigBlocks.soulGravel, 4), new Object[] {"#I", "I#", '#', Blocks.gravel, 'I', Blocks.soul_sand});
        GameRegistry.addRecipe(new ItemStack(ConfigBlocks.soulGravel, 4), new Object[] {"I#", "#I", '#', Blocks.gravel, 'I', Blocks.soul_sand});
    }
}
