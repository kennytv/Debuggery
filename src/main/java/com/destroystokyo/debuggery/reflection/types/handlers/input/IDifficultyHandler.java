/*
 * This file is part of Debuggery.
 *
 * Debuggery is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Debuggery is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Debuggery.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.destroystokyo.debuggery.reflection.types.handlers.input;

import com.destroystokyo.debuggery.reflection.types.handlers.base.IHandler;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IDifficultyHandler implements IHandler {

    @Nonnull
    @Override
    public Difficulty instantiateInstance(String input, Class clazz, @Nullable CommandSender sender) {
        try {
            int val = Integer.parseInt(input);
            //noinspection deprecation
            return Difficulty.getByValue(val);
        } catch (NumberFormatException ignored) {
        }

        return IEnumHandler.getEnumValue(input, Difficulty.class);
    }

    @Nonnull
    @Override
    public Class<?> getRelevantClass() {
        return Difficulty.class;
    }
}
