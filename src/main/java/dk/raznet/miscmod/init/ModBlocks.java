package dk.raznet.miscmod.init;

import com.google.common.base.Throwables;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.raznet.miscmod.block.BlockGeneric;
import dk.raznet.miscmod.block.BlockTest;
import dk.raznet.miscmod.item.ItemGeneric;
import dk.raznet.miscmod.reference.Reference;

import java.lang.reflect.Field;

/**
 * Created by ChrRaz on 23/07/2014.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BlockGeneric blockTest = new BlockTest();

	public static void register()
	{
		for (Field field : ModBlocks.class.getFields())
		{
			if (BlockGeneric.class.isAssignableFrom(field.getType()))
			{
				try
				{
					BlockGeneric block = (BlockGeneric) field.get(null);
					GameRegistry.registerBlock(block, block.getClass().getSimpleName());
				}
				catch (IllegalAccessException e)
				{
					// Really should not ever happen
					throw Throwables.propagate(e);
				}
			}
		}
	}
}
