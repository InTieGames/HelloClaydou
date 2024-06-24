package com.intiegames.divinecraft.helloclaydou;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
Tipo: Plugin pré alpha sem performance SMP (Sem Com Performance)
Objetivo: Testar interação de player com um NPC (PIG/Claydoy)
Resultado: O Player deve saudar o claydou.
 */
public class HelloClaydou extends JavaPlugin implements Listener {

    private final Map<UUID, Boolean> playerInteractionState = new HashMap<>();
    private final Map<UUID, Boolean> playerGreeted = new HashMap<>();
    private final Map<UUID, UUID> claydouInteractionMap = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("claydouCommunicationPlugin habilitado.");
    }

    @Override
    public void onDisable() {
        getLogger().info("claydouCommunicationPlugin desabilitado.");
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (entity.getType() == EntityType.PIG) {
            UUID playerUUID = player.getUniqueId();
            UUID pigUUID = entity.getUniqueId();

            // Verificar se o claydou já está sendo interagido por outro jogador
            if (claydouInteractionMap.containsKey(pigUUID) && !claydouInteractionMap.get(pigUUID).equals(playerUUID)) {
                player.sendMessage("Este claydou está conversando com outro jogador. Aguarde sua vez.");
                return;
            }

            // Registrar o claydou como sendo interagido pelo jogador atual
            claydouInteractionMap.put(pigUUID, playerUUID);

            boolean hasGreeted = playerInteractionState.getOrDefault(playerUUID, false);
            boolean hasBeenGreeted = playerGreeted.getOrDefault(playerUUID, false);

            if (!hasGreeted) {
                player.sendMessage("Olá Claydou");
                playerInteractionState.put(playerUUID, true);
                playerGreeted.put(playerUUID, true);
            } else {
                if (hasBeenGreeted) {
                    entity.sendMessage("Oi, já falei contigo, tenha um bom dia!");
                } else {
                    entity.sendMessage("Olá!!");
                    playerGreeted.put(playerUUID, true);
                }
                playerInteractionState.put(playerUUID, false);

                // Liberar o claydou para que outros jogadores possam interagir
                claydouInteractionMap.remove(pigUUID);
            }
        }
    }
}





