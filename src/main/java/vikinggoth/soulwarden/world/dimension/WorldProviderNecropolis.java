package vikinggoth.soulwarden.world.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Friedrich on 12/6/2015.
 */
public class WorldProviderNecropolis extends WorldProvider
{

    @Override
    /** tells Minecraft to use our new Terrain Generator */
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderNecro(this.worldObj, this.worldObj.getSeed(), true);
    }

    @Override
    public void registerWorldChunkManager()
    {
        //can be overworld or hell??? TODO Make our own
        this.worldChunkMgr = new WorldChunkManagerNecro();
        this.dimensionId = DimensionRegistry.DIM_NECRO_ID;
        this.hasNoSky = true;
    }

    /** Get Provider for Dimension **/
    public static WorldProvider getProviderForDimension(int id)
    {
        return DimensionManager.createProviderFor(DimensionRegistry.DIM_NECRO_ID);
    }

    @Override
    public String getDimensionName() {
        return "Stygia";
    }

    @Override
    /** sets/creates the save folder */
    public String getSaveFolder() {
        return "DIM" + DimensionRegistry.DIM_NECRO_ID;
    }

    @SideOnly(Side.CLIENT)
    /** @return the player speed */
    public double getMovementFactor() {
        return 1.0;
    }


    @Override
    /** can the player respawn in this dimension? */
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    /** is this a surface world or an underworld */
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    /** the light value in this dimension */
    protected void generateLightBrightnessTable()
    {
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    /** @return the dimension join message */
    public String getWelcomeMessage()
    {
        return "What is Death?";
    }

    @Override
    @SideOnly(Side.CLIENT)
    /** @return the dimension leave message */
    public String getDepartMessage()
    {
        return "May Never Die";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2)
    {
        float f2 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = 0.7529412F;
        float f4 = 0.84705883F;
        float f5 = 1.0F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return new Vec3((double)f3, (double)f4, (double)f5);
    }

    @Override
    public String getInternalNameSuffix() {
        return "STYGIA";
    }
}
