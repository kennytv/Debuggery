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

package io.zachbr.debuggery;

import io.zachbr.debuggery.commands.*;
import io.zachbr.debuggery.commands.base.CommandBase;
import io.zachbr.debuggery.reflection.types.TypeHandler;
import io.zachbr.debuggery.reflection.types.handlers.bukkit.BootstrapBukkitHandlers;
import io.zachbr.debuggery.util.DebugUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class DebuggeryBukkit extends DebuggeryBase {
    private final DebuggeryJavaPlugin javaPlugin;
    private final Map<String, CommandBase> commands = new HashMap<>();
    private final DebugUtil debugUtil = new DebugUtil(this);

    DebuggeryBukkit(DebuggeryJavaPlugin plugin, Logger logger) {
        super(logger);
        this.javaPlugin = plugin;
    }

    void onEnable() {
        debugUtil.printSystemInfo();
        BootstrapBukkitHandlers.init(getTypeHandler(), getLogger());

        this.registerCommands();
    }

    void onDisable() {
        this.getMethodMapProvider().clearCache();
    }

    private void registerCommands() {
        this.registerCommand(new BlockCommand(this));
        this.registerCommand(new ChunkCommand(this));
        this.registerCommand(new DebugCommand(this));
        this.registerCommand(new DebuggeryCommand(this));
        this.registerCommand(new EntityCommand(this));
        this.registerCommand(new ItemCommand(this));
        this.registerCommand(new PlayerCommand(this));
        this.registerCommand(new ServerCommand(this));
        this.registerCommand(new WorldCommand(this));

        commands.values().forEach(c -> this.getJavaPlugin().getCommand(c.getName()).setExecutor(c));
    }

    private void registerCommand(final CommandBase command) {
        this.commands.put(command.getName(), command);
    }

    public Map<String, CommandBase> getAllCommands() {
        return Collections.unmodifiableMap(commands);
    }

    public DebugUtil getDebugUtil() {
        return this.debugUtil;
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }
}
