package vikinggoth.soulwarden.world.biomes;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import vikinggoth.soulwarden.blocks.BlockSoulStone;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;
import vikinggoth.soulwarden.registers.BlockRegister;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class BiomeGenStygia extends BiomeGenBase
{
    public static final BiomeGenBase stygiaSea = (new BiomeGenStygiaSea(ConfigurationHandler.biomeStygiaSeaID)).setBiomeName("StygiaSea").setHeight(height_DeepOceans);
    public static final BiomeGenBase boneBeach = (new BiomeGenBoneBeach(ConfigurationHandler.biomeBoneBeachID)).setBiomeName("BoneBeach").setHeight(height_RockyWaters);

    public BiomeGenStygia(int id)
    {
        this(id, true);
    }

    public BiomeGenStygia(int id, boolean register)
    {
        super(id, register);
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = BlockRegister.soulStone.getDefaultState().withProperty(BlockSoulStone.VARIANT, BlockSoulStone.EnumType.CRACKED);
        this.fillerBlock = BlockRegister.soulStone.getDefaultState();
        this.waterColorMultiplier = 0x4B006E;
        this.theBiomeDecorator = new BiomeDecoratorStygia();
        this.setDisableRain();
    }

    /**
     * Adds the default flowers, as of 1.7, it is 2 yellow, and 1 red. I chose 10 to allow some wiggle room in the numbers.
     */
    @Override
    public void addDefaultFlowers()
    {

    }

    /**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     */
    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new BiomeDecoratorStygia());
    }
}
