/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraftforge.common.Configuration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigSetting {

	public enum IntegerSettingType {
		INTEGER, BLOCK_ID, RESTRICTED_BLOCK_ID
	}

	/**
	 * @return Name of category in which to store this value in config
	 *         file. Default uses "general" category.
	 *         <em>Ignored for BLOCK_ID and RESTRICTED_BLOCK_ID types.</em>
	 */
	String category() default Configuration.CATEGORY_GENERAL;

	/**
	 * @return the comment that will be written in the config file for
	 *         this setting.
	 */
	String comment() default "";

	/**
	 * @return The type of setting if an integer value. (INTEGER,
	 *         BLOCK_ID, RESTRICTED_BLOCK_ID) RESTRICTED_BLOCK_ID types
	 *         are validated to have a value above 0 and less than 256.
	 */
	IntegerSettingType integerType() default IntegerSettingType.INTEGER;

	/**
	 * @return Name of key in config file. Default uses the name of the
	 *         field.
	 */
	String key() default "";

}
