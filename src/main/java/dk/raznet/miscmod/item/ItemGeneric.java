package dk.raznet.miscmod.item;

import dk.raznet.miscmod.reference.Reference;
import dk.raznet.miscmod.util.StringHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by ChrRaz on 23/07/2014.
 */
public class ItemGeneric extends Item
{
	public ItemGeneric()
	{
		this.setUnlocalizedName(StringHelper.getClassName(this));
		this.setTextureName(StringHelper.getUnwrappedUnlocalizedName(getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s:%s", Reference.MOD_ID, StringHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return getUnlocalizedName();
	}

	/*
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()));
		// this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1
	}
	*/

}
