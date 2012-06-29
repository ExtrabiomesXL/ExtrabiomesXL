
package extrabiomes.api;

public interface IPlugin {
	public abstract String getName();

	public abstract void inject();

	public abstract boolean isEnabled();
}
