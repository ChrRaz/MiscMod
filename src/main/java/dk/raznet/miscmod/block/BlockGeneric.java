package dk.raznet.miscmod.block;

import dk.raznet.miscmod.reference.Reference;
import dk.raznet.miscmod.util.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

/**
 * Created by ChrRaz on 11/08/2014.
 */
public class BlockGeneric extends Block
{
	public BlockGeneric(Material material)
	{
		super(material);
		this.setBlockName(StringHelper.getClassName(this));
		this.setBlockTextureName(StringHelper.getUnwrappedUnlocalizedName(getUnlocalizedName()));
	}

	public BlockGeneric()
	{
		this(Material.rock);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s:%s", Reference.MOD_ID, StringHelper.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}


}
