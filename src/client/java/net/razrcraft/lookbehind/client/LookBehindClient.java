package net.razrcraft.lookbehind.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LookBehindClient implements ClientModInitializer {
    private static KeyBinding lookBehindKey;
    private static boolean lookbehind;

    public static boolean isLookingBehind() {
        return lookbehind;
    }

    @Override
    public void onInitializeClient() {
        // Register the keybind
        lookBehindKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.lookbehind.look_behind",   // Translation key
                InputUtil.Type.KEYSYM,                      // Key type
                GLFW.GLFW_KEY_R,                            // Default key (R)
                "key.category.lookbehind.keys"              // Category for controls menu
        ));

        // Handle key press logic
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            lookbehind = lookBehindKey.isPressed();
        });
    }
}
