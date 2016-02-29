package vikinggoth.soulwarden.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;

/**
 * Created by Friedrich on 2/29/2016.
 */
public class BiomeGenSoulWarden extends BiomeGenBase
{
    //Heights
    protected static final BiomeGenBase.Height height_StygianSea = new BiomeGenBase.Height(-1.0F, 0.5F);

    public static final BiomeGenBase stygianSea = (new BiomeGenOcean(64)).setColor(112).setBiomeName("StygianSea").setHeight(height_StygianSea);

    public BiomeGenSoulWarden(int id, boolean isStygian)
    {
        super(id);
    }
}
