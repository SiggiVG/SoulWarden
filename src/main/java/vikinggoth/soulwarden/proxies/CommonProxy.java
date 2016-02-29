package vikinggoth.soulwarden.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import vikinggoth.soulwarden.registries.BlockRegister;
import vikinggoth.soulwarden.registries.ItemRegister;
import vikinggoth.soulwarden.registries.RecipeRegistry;
import vikinggoth.soulwarden.world.biome.BiomeGenStygia;
import vikinggoth.soulwarden.world.dimension.DimensionRegistry;

/**
 * Created by Friedrich on 8/18/2015.
 */
public abstract class CommonProxy implements IProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {

        ItemRegister.createItems();
        BlockRegister.createBlocks();

        //BiomeGenStygia.init();
        DimensionRegistry.init();
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        RecipeRegistry.initCrafting();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {

    }
}
