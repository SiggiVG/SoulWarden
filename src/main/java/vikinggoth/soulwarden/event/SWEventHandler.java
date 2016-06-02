package vikinggoth.soulwarden.event;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vikinggoth.soulwarden.registers.BlockRegister;
import vikinggoth.soulwarden.registers.ItemRegister;

/**
 * Created by Friedrich on 6/1/2016.
 */
public class SWEventHandler
{

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
        if(!event.entityLiving.getEntityWorld().isRemote) {
            if (event.source.getEntity() instanceof EntityLivingBase) {
                if (event.source.getEntity() instanceof EntityPlayer) {
                    boolean hasSoulSteal = false;
                    EntityPlayer attacker = (EntityPlayer) event.source.getEntity();
                    //if(attacker.capabilities.allowFlying) return;
                    if (attacker.getCurrentEquippedItem() != null) {
                        if (attacker.getCurrentEquippedItem().getTagCompound() != null) {
                            if (attacker.getCurrentEquippedItem().getTagCompound().getTag("ench") != null) {
                                NBTTagList enchants = (NBTTagList) attacker.getCurrentEquippedItem().getTagCompound().getTag("ench");

                                for (int i = 0; i < enchants.tagCount(); ++i) {
                                    NBTTagCompound enchant = ((NBTTagList) enchants).getCompoundTagAt(i);
                                    if (enchant.getShort("id") == 71) {
                                        hasSoulSteal = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (hasSoulSteal) {
                        //TODO check for black soul gems
                        if (event.entityLiving instanceof EntityPlayer || event.entityLiving instanceof EntityVillager) {
                            if (attacker.inventory.hasItem(ItemRegister.soulgem_black)) {
                                attacker.inventory.consumeInventoryItem(ItemRegister.soulgem_black);
                                //attacker.inventory.addItemStackToInventory(new ItemStack(ItemRegister.soulgem_black_charged));
                                attacker.inventory.addItemStackToInventory(new ItemStack(ItemRegister.soulgem_black_charged));
                            }
                        } else {
                            if (attacker.inventory.hasItem(ItemRegister.soulgem)) {
                                attacker.inventory.consumeInventoryItem(ItemRegister.soulgem);
                                //attacker.inventory.addItemStackToInventory(new ItemStack(ItemRegister.soulgem_charged));
                                attacker.inventory.addItemStackToInventory(new ItemStack(ItemRegister.soulgem_charged));
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void HoeGraveSoilEvent(UseHoeEvent event)
    {
        if(!event.entityLiving.getEntityWorld().isRemote)
        {
            Block block = event.world.getBlockState(event.pos).getBlock();
            if(block == BlockRegister.grassCemetery || block == BlockRegister.graveSoil)
            {
                event.world.playSoundEffect((double)((float)event.pos.getX() + 0.5F), (double)((float)event.pos.getY() + 0.5F), (double)((float)event.pos.getZ() + 0.5F), BlockRegister.graveSoilTilled.stepSound.getStepSound(), (BlockRegister.graveSoilTilled.stepSound.getVolume() + 1.0F) / 2.0F, BlockRegister.graveSoilTilled.stepSound.getFrequency() * 0.8F);
                event.world.setBlockState(event.pos, BlockRegister.graveSoilTilled.getDefaultState());
                event.entityPlayer.getCurrentEquippedItem().damageItem(1, event.entityPlayer);
            }
        }
    }
}
