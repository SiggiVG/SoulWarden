package vikinggoth.soulwarden.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import vikinggoth.soulwarden.world.biomes.BiomeGenStygia;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class GenLayerStygiaBiomes extends GenLayer
{
    protected BiomeGenBase[] allowedBiomes = {
            BiomeGenStygia.stygiaSea, BiomeGenStygia.boneBeach
    };

    public GenLayerStygiaBiomes(long seed) {
        super(seed);
    }

    public GenLayerStygiaBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
            }
        }
        return dest;
    }
}
