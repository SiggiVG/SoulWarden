package vikinggoth.soulwarden.world.dimension;

import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vikinggoth.soulwarden.configuration.ConfigurationHandler;

/**
 * Created by Friedrich on 6/1/2016.
 */
public class WorldProviderStygia extends WorldProvider
{

    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerStygia(worldObj.getSeed(), WorldTypeStygia.STYGIA, "");
        this.hasNoSky = true;
        this.dimensionId = ConfigurationHandler.dimStygiaID;
    }

    /**
     * Return Vec3D with biome specific fog color
     */
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3(0.1D, 0.1D, 0.1D);
    }

    /**
     * Creates the light to brightness table
     */
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {//TODO
        return new ChunkProviderStygia(this.worldObj, this.worldObj.getSeed());
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int x, int z)
    {
        return false;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_)
    {
        return 0.5F;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
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
