package dk.raznet.miscmod.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dk.raznet.miscmod.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by ChrRaz on 21/07/2014.
 * The use of reflection in regards to config is based on a concept by OpenMods
 */
public class ConfigurationHandler
{

	public static final String rootName = "root";

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface ConfigOptions
	{
		/**
		 * The category of the property
		 * All categories are placed under a "root" category
		 * Subcategories are split using a dot (.)
		 * For example the category for someProperty:
		 * root {
		 * some {
		 * category {
		 * someProperty = ...
		 * }
		 * }
		 * }
		 * <p/>
		 * is written as "some.category"
		 * If ommitted the property will be placed directly under "root"
		 */
		String category() default "";

		String comment() default "";

	}

	public static class ConfigValues
	{
		/*
		 * All config options go here as defined variables
		 * All variables must be PUBLIC STATIC
		 * Variables may be annotated with @ConfigOptions in order to define a category and a comment
		 * Constants will be ignored as long as they are "final"
		 */

		public static final String cat = "arrays.1.2.3";

		@ConfigOptions(comment = "This is in fact just a test")
		public static boolean test = true;

		@ConfigOptions(category = cat)
		public static boolean[] booleans = {false, true, true, false, true};
		@ConfigOptions(category = cat)
		public static double[] doubles = {0, 1, 2, 3, 4, 7};
	}

	public static Configuration config;

	public static void init(File configFile)
	{
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		if (config == null)
		{
			config = new Configuration(configFile);
			loadConfig();
		}

	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfig();
		}
	}

	private static void loadConfig()
	{

		for (Field field : ConfigValues.class.getFields())
		{
			// Skip constants
			if (Modifier.isFinal(field.getModifiers()))
				continue;

			Class<?> configType = field.getType();
			ConfigOptions options = field.getAnnotation(ConfigOptions.class);
			String category = Configuration.CATEGORY_GENERAL;
			String comment = null;

			if (options != null)
			{
				String strippedCategory = StringUtils.strip(options.category(), Configuration.CATEGORY_SPLITTER);
				category = rootName + (strippedCategory.length() == 0 ? "" : Configuration.CATEGORY_SPLITTER) + strippedCategory;
				if (!options.comment().equals(""))
					comment = options.comment();
			}

			try
			{
				if (configType.equals(boolean.class))
				{
					field.set(null, config.get(category, field.getName(), (Boolean) field.get(null), comment).getBoolean());

				} else if (configType.equals(boolean[].class))
				{
					field.set(null, config.get(category, field.getName(), (boolean[]) field.get(null), comment).getBooleanList());

				} else if (configType.equals(int.class))
				{
					field.set(null, config.get(category, field.getName(), (Integer) field.get(null), comment).getInt());

				} else if (configType.equals(int[].class))
				{
					field.set(null, config.get(category, field.getName(), (int[]) field.get(null), comment).getIntList());

				} else if (configType.equals(double.class))
				{
					field.set(null, config.get(category, field.getName(), (Double) field.get(null), comment).getDouble());

				} else if (configType.equals(double[].class))
				{
					field.set(null, config.get(category, field.getName(), (double[]) field.get(null), comment).getDoubleList());

				} else if (configType.equals(String.class))
				{
					field.set(null, config.get(category, field.getName(), (String) field.get(null), comment).getString());

				} else if (configType.equals(String[].class))
				{
					field.set(null, config.get(category, field.getName(), (String[]) field.get(null), comment).getStringList());

				} else if (configType.equals(double.class))
				{
					field.set(null, config.get(category, field.getName(), (Double) field.get(null), comment).getDouble());

				} else if (configType.equals(double[].class))
				{
					field.set(null, config.get(category, field.getName(), (double[]) field.get(null), comment).getDoubleList());

				}
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}

			if (config.hasChanged())
			{
				config.save();
			}
		}
	}


}
