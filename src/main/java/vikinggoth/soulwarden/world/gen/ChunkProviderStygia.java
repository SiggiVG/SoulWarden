package vikinggoth.soulwarden.world.gen;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;

/**
 * Created by Friedrich on 2/29/2016.
 */
public class ChunkProviderStygia implements IChunkProvider
{
    @Override
    public boolean chunkExists(int x, int z)
    {
        return false;
    }

    @Override
    public Chunk provideChunk(int x, int z)
    {
        return null;
    }

    @Override
    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return null;
    }

    @Override
    public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {

    }

    @Override
    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_)
    {
        return false;
    }

    @Override
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return false;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public boolean canSave()
    {
        return false;
    }

    @Override
    public String makeString()
    {
        return null;
    }

    @Override
    public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_)
    {
        return null;
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_)
    {
        return null;
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    @Override
    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
    {

    }

    @Override
    public void saveExtraData()
    {

    }
}
