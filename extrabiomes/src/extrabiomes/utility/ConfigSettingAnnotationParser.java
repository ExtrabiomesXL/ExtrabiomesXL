/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;

import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.ExtrabiomesLog;
import extrabiomes.utility.ConfigSetting.IntegerSettingType;

public class ConfigSettingAnnotationParser {

	private enum FieldType {
		INTEGER, BLOCK_ID, RESTRICTED_BLOCK_ID, BOOLEAN, STRING;
	}

	private final ConfigFile	cfg;

	/**
	 * @param cfg
	 *            ConfigFile object. <b>NOTE</b>: The object must have
	 *            already had its load() method invoked.
	 */
	public ConfigSettingAnnotationParser(ConfigFile cfg) {
		checkNotNull(cfg, "Null config file cannot be parsed.");
		checkArgument(cfg.isLoaded(),
				"Unopened config file cnnot be parsed.");
		this.cfg = cfg;
	}

	private Optional<FieldType> determineFieldType(Field field,
			IntegerSettingType settingType)
	{
		final Class type = field.getType();
		if (type.equals(Boolean.TYPE))
			return Optional.of(FieldType.BOOLEAN);
		if (type.equals(String.class))
			return Optional.of(FieldType.STRING);
		if (type.equals(Integer.TYPE)) switch (settingType) {
			case BLOCK_ID:
				return Optional.of(FieldType.BLOCK_ID);
			case RESTRICTED_BLOCK_ID:
				return Optional.of(FieldType.RESTRICTED_BLOCK_ID);
			default:
				return Optional.of(FieldType.INTEGER);
		}
		return Optional.absent();
	}

	private Object loadBlockID(Field field, String key, String comment,
			Object obj) throws IllegalArgumentException,
			IllegalAccessException
	{
		final Property property = cfg.getOrCreateBlockIdProperty(key,
				field.getInt(obj));
		property.comment = comment;
		return Integer.parseInt(property.value);
	}

	private Object loadNonBlockID(Field field, String key,
			String category, String comment, FieldType fieldType,
			Object obj) throws IllegalArgumentException,
			IllegalAccessException
	{
		String defaultValue;
		if (fieldType.equals(FieldType.BOOLEAN))
			defaultValue = Boolean.toString(field.getBoolean(obj));
		else if (fieldType.equals(FieldType.INTEGER))
			defaultValue = Integer.toString(field.getInt(obj));
		else
			defaultValue = String.valueOf(obj);

		final Property property = cfg.getOrCreateProperty(key,
				category, defaultValue);
		property.comment = comment;

		if (fieldType.equals(FieldType.BOOLEAN))
			return property.getBoolean(false);
		else if (fieldType.equals(FieldType.INTEGER))
			return property.getInt();
		
		return defaultValue;
	}

	private Object loadRestrictedBlockID(Field field, String key,
			String comment, Object obj)
			throws IllegalArgumentException, RuntimeException,
			IllegalAccessException
	{
		final Property property = cfg
				.getOrCreateRestrictedBlockIdProperty(key,
						field.getInt(obj));
		property.comment = comment;
		return Integer.parseInt(property.value);
	}

	/**
	 * Load config file settings into an object's members, based on
	 * their annotations.
	 * 
	 * @param obj
	 *            the object aof a class containing fields annotated
	 *            with @ConfigSetting
	 * @param objName
	 *            the name of the object as it will appear in logs.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void parse(Object obj, String objName)
			throws IllegalArgumentException, IllegalAccessException
	{
		checkNotNull(obj, "Cannot parse null object.");
		checkArgument(!objName.isEmpty(),
				"Object name required for logging.");

		obj.getClass().getDeclaredFields();

		ExtrabiomesLog.info("Loading required settings for %s.",
				objName);

		for (final Field field : obj.getClass().getDeclaredFields())
			if (field.isAnnotationPresent(ConfigSetting.class))
				parseConfigSettingAnnotation(field, obj);

		ExtrabiomesLog.info("Done loading settings for %s.", objName);
	}

	private void parseConfigSettingAnnotation(Field field, Object obj)
			throws IllegalAccessException, IllegalArgumentException
	{
		final boolean isAccessible = field.isAccessible();
		if (!isAccessible) field.setAccessible(true);

		final Optional<ConfigSetting> configSetting = Optional.of(field
				.getAnnotation(ConfigSetting.class));
		final IntegerSettingType settingType = configSetting.get()
				.integerType();
		final String category = configSetting.get().category();
		final String comment = configSetting.get().comment();
		String key = configSetting.get().key();
		if (key.isEmpty()) key = field.getName().toLowerCase();

		final Optional<FieldType> fieldType = determineFieldType(field,
				settingType);
		if (key.isEmpty())
			key = field.getName().toLowerCase()
					+ (fieldType.get().equals(FieldType.BLOCK_ID)
							|| fieldType.get().equals(
									FieldType.RESTRICTED_BLOCK_ID) ? ".id"
							: "");
		if (!fieldType.isPresent())
			throw new IllegalArgumentException();

		try {
			Object value;
			switch (fieldType.get()) {
				case BLOCK_ID:
					value = loadBlockID(field, key, comment, obj);
					break;
				case RESTRICTED_BLOCK_ID:
					value = loadRestrictedBlockID(field, key, comment,
							obj);
					break;
				default:
					value = loadNonBlockID(field, key, category,
							comment, fieldType.get(), obj);
					break;
			}

			field.set(obj, value);
		} catch (final IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format(
					"Type mismatch on receiving field for key \"%s\".",
					key), e);
		}

		if (!isAccessible) field.setAccessible(false);
	}

}
