/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.thermalexpansion;

import java.lang.reflect.Field;

import com.google.common.base.Optional;

class ThermalExpansionAPI {

    private Optional<SawmillManager> sawmill = Optional.absent();

    ThermalExpansionAPI() {

        Optional<Object> sawmillManager = Optional.absent();

        try {
            final Class cls = Class.forName("thermalexpansion.api.crafting.CraftingManagers");
            final Field fld = cls.getField("sawmillManager");
            sawmillManager = Optional.of(fld.get(null));
            sawmill = Optional.of(new SawmillManager(sawmillManager));
        } catch (final Exception e) {
            e.printStackTrace();
            sawmill = Optional.absent();
        }
    }

    public Optional<SawmillManager> getSawmill() {
        return sawmill;
    }
}
