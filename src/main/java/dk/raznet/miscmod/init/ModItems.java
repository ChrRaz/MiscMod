package dk.raznet.miscmod.init;

import com.google.common.base.Throwables;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.raznet.miscmod.item.ItemGeneric;
import dk.raznet.miscmod.reference.Reference;

import java.lang.reflect.Field;

/**
 * Created by ChrRaz on 23/07/2014.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
	//TODO: Add items

	public static void register()
	{
		for (Field field : ModItems.class.getFields())
		{
			if (ItemGeneric.class.isAssignableFrom(field.getType()))
			{
				try
				{
					ItemGeneric item = (ItemGeneric) field.get(null);
					GameRegistry.registerItem(item, item.getClass().getSimpleName());
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
