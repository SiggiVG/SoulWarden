package vikinggoth.soulwarden.world.dimension;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Friedrich on 6/2/2016.
 */
public class WorldTypeStygia extends WorldType
{
    private boolean hasNotificationData;

    public static WorldType STYGIA;

    public WorldTypeStygia(String name) {
        super(name);
    }

    public static void addWorldTypes()
    {
        STYGIA = new WorldTypeStygia("stygia");
    }

    @Override
    public WorldChunkManager getChunkManager(World world) {
        return new WorldChunkManagerStygia();
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderGenerate(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), "");
    }


    @Override
    public int getMinimumSpawnHeight(World world)
    {
        return 128;
    }

    /**
     * enables the display of generator.[worldtype].info message on the customize world menu
     */
    private WorldType setNotificationData()
    {
        this.hasNotificationData = true;
        return this;
    }

    /**
     * returns true if selecting this worldtype from the customize menu should display the generator.[worldtype].info
     * message
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean showWorldInfoNotice()
    {
        return this.hasNotificationData;
    }
}
