package vikinggoth.soulwarden.world.biomes;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class BiomeGenStygiaForest extends BiomeGenStygia
{
    public BiomeGenStygiaForest(int id)
    {
        super(id);
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 2;
    }
}
