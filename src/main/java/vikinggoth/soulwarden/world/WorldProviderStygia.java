package vikinggoth.soulwarden.world;

import net.minecraft.client.Minecraft;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.world.biome.BiomeGenSoulWarden;

/**
 * Created by Friedrich on 2/29/2016.
 */
public class WorldProviderStygia extends WorldProvider
{
    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenSoulWarden.stygianSea, 0.0F);
        this.dimensionId = ConfigurationHandler.dimStygiaID;
        this.hasNoSky = true;
    }

    @Override
    public String getDimensionName()
    {
        return "Stygia";
    }

    @Override
    public String getInternalNameSuffix()
    {
        return "_stygia";
    }
}
