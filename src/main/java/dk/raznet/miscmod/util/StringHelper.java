package dk.raznet.miscmod.util;

import dk.raznet.miscmod.reference.Reference;

/**
 * Created by ChrRaz on 24/07/2014.
 */
public class StringHelper
{
	public static String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	public static String getClassName(Object obj)
	{
		return obj.getClass().getSimpleName();
	}

	public static String prefixTextureName(String textureName)
	{
		return Reference.MOD_ID + ":" + textureName;
	}
}
