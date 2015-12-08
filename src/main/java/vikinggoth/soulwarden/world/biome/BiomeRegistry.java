package vikinggoth.soulwarden.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class BiomeRegistry
{
    public static BiomeGenBase biomeStygianSea;

    public static void init()
    {
        //TODO this is temporary
        biomeStygianSea = new BiomeGenOcean(64);

        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeStygianSea, 80));
    }

    public static List<BiomeGenBase> getNecroBiomeList()
    {
        List<BiomeGenBase> list = new ArrayList<BiomeGenBase>();

        list.add(biomeStygianSea);

        return list;
    }
}
