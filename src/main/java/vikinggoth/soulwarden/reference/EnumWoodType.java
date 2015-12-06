package vikinggoth.soulwarden.reference;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Friedrich on 12/5/2015.
 */
public enum EnumWoodType implements IStringSerializable
{
    GHOUL(0, "ghoul"),
    WEEPWILLOW(1, "weepwillow"),
    BONEBEECH(2, "bonebeech"),
    HAND(3, "hand"), //wax hand
    ALM(4, "alm"),
    POMEGRANATE(5, "pomegranate");

    private static final EnumWoodType[] META_LOOKUP = new EnumWoodType[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;

    private EnumWoodType(int meta, String name, String unlocalizedName)
    {
        this.meta = meta;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }

    private EnumWoodType(int meta, String name)
    {
        this.meta = meta;
        this.name = name;
        this.unlocalizedName = name;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.name;
    }

    public static EnumWoodType byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String getName()
    {
        return this.name;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    static
    {
        EnumWoodType[] stateArray = values();
        int var1 = stateArray.length;

        for (int i = 0; i < var1; ++i)
        {
            EnumWoodType metas = stateArray[i];
            META_LOOKUP[metas.getMetadata()] = metas;
        }
    }
}
