package com.github.julyss2019.mcsp.julylibrary.commandv2.registered;

import com.github.julyss2019.mcsp.julylibrary.commandv2.InvalidArgumentException;
import com.github.julyss2019.mcsp.julylibrary.commandv2.SubCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegisteredSubCommand {
    private RegisteredCommand registeredCommand;
    private SubCommand subCommand;
    private Object obj;
    private Method method;
    private String description;
    private String[] args;

    public RegisteredSubCommand(@NotNull RegisteredCommand registeredCommand, @NotNull SubCommand subCommand, @NotNull Object obj, @NotNull Method method) {
        this.registeredCommand = registeredCommand;
        this.obj = obj;
        this.subCommand = subCommand;
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        for (String arg : args) {
            if (arg == null) {
                throw new RuntimeException("不能包含 null 元素");
            }
        }

        this.args = args;
    }

    public RegisteredCommand getRegisteredCommand() {
        return registeredCommand;
    }

    public SubCommand getSubCommand() {
        return subCommand;
    }

    public void execute(@NotNull CommandSender cs, @NotNull String[] args) throws InvalidArgumentException, IllegalAccessError, InvocationTargetException, IllegalAccessException {
        method.invoke(obj, cs, args);
    }
}
