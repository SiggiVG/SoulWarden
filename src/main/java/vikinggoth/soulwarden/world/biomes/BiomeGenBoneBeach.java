package vikinggoth.soulwarden.world.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import vikinggoth.soulwarden.registers.BlockRegister;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class BiomeGenBoneBeach extends BiomeGenStygia
{
    //TODO custom biomedecorators
    public BiomeGenBoneBeach(int id)
    {
        super(id);
        this.spawnableCaveCreatureList.clear();
        this.topBlock = BlockRegister.bonePile.getDefaultState();
        this.fillerBlock = BlockRegister.bonePile.getDefaultState();
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 0;
    }
}
