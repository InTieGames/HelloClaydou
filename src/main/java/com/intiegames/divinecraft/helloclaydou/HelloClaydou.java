package com.intiegames.divinecraft.helloclaydou;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
Tipo: Plugin pré alpha sem performance CMP (Com Performance)
Objetivo: Testar interação de player com um NPC (Porco)
Resultado: O Player deve saudar o Porco.
 */
public class HelloClaydou extends JavaPlugin implements Listener {

    private final Map<UUID, Long> greetedPlayers = new HashMap<>();
    private static final long GREET_INTERVAL = 5000; // 5 segundos
    private static final Logger LOGGER = Bukkit.getLogger();

    @Override
    public void onEnable() {
        // Registra o evento ao habilitar o plugin
        Bukkit.getPluginManager().registerEvents(this, this);
        LOGGER.info(ChatColor.GREEN + "HelloClaydou Plugin habilitado com sucesso." + ChatColor.RESET);

        // Tarefa para limpar jogadores do Map após o intervalo
        new BukkitRunnable() {
            @Override
            public void run() {
                cleanUpGreetedPlayers();
            }
        }.runTaskTimer(this, 20, 20); // Executa a cada segundo
    }

    @Override
    public void onDisable() {
        LOGGER.info(ChatColor.RED + "HelloClaydou Plugin desabilitado." + ChatColor.RESET);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        // Verifica se o jogador já foi saudado recentemente
        if (greetedPlayers.containsKey(playerId)) {
            long lastGreetTime = greetedPlayers.get(playerId);
            if (System.currentTimeMillis() - lastGreetTime < GREET_INTERVAL) {
                return; // Ignora se o intervalo não passou
            }
        }

        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity.getType() == EntityType.PIG) {
                player.sendMessage(ChatColor.GREEN + "Olá Claydou");
                greetedPlayers.put(playerId, System.currentTimeMillis());
                LOGGER.info(ChatColor.YELLOW + player.getName() + " encontrou um porco e recebeu uma saudação." + ChatColor.RESET);
                break;
            }
        }
    }

    private void cleanUpGreetedPlayers() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<UUID, Long>> iterator = greetedPlayers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Long> entry = iterator.next();
            if (currentTime - entry.getValue() >= GREET_INTERVAL) {
                iterator.remove();
            }
        }
    }
}





