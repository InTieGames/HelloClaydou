package com.intiegames.divinecraft.helloclaydou;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Manages language files and provides translation functionality for the HelloClaydou plugin.
 * Supports multiple languages including Portuguese, English, Spanish, Russian, and Hebrew.
 */
public class LanguageManager {
    private final JavaPlugin plugin;
    private final String defaultLanguage;
    private final Map<String, FileConfiguration> languages;
    
    /**
     * Creates a new LanguageManager instance.
     * 
     * @param plugin The JavaPlugin instance
     */
    public LanguageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.languages = new HashMap<>();
        
        // Load the main config
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        
        // Get default language from config, default to English if not specified
        this.defaultLanguage = config.getString("language", "en_US");
        
        // Load all language files
        loadLanguage("pt_BR"); // Portuguese
        loadLanguage("en_US"); // English
        loadLanguage("es_ES"); // Spanish
        loadLanguage("ru_RU"); // Russian
        loadLanguage("he_IL"); // Hebrew
    }
    
    /**
     * Loads a language file from the resources directory.
     * 
     * @param lang The language code to load
     */
    private void loadLanguage(String lang) {
        File langFile = new File(plugin.getDataFolder(), "lang/" + lang + ".yml");
        
        // If the language file doesn't exist, create it from the template
        if (!langFile.exists()) {
            langFile.getParentFile().mkdirs();
            plugin.saveResource("lang/" + lang + ".yml", false);
        }
        
        // Load the language file
        FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);
        
        // Check for missing keys by comparing with the default language file in resources
        InputStream defaultLangStream = plugin.getResource("lang/" + lang + ".yml");
        if (defaultLangStream != null) {
            YamlConfiguration defaultLangConfig = YamlConfiguration.loadConfiguration(
                    new InputStreamReader(defaultLangStream, StandardCharsets.UTF_8));
            
            // Add any missing keys from the default language file
            for (String key : defaultLangConfig.getKeys(true)) {
                if (!langConfig.contains(key)) {
                    langConfig.set(key, defaultLangConfig.get(key));
                }
            }
            
            // Save the updated language file
            try {
                langConfig.save(langFile);
            } catch (IOException e) {
                plugin.getLogger().log(Level.WARNING, "Could not save " + lang + " language file", e);
            }
        }
        
        // Add the language to the map
        languages.put(lang, langConfig);
    }
    
    /**
     * Gets a translated message for the specified key.
     * 
     * @param key The message key
     * @return The translated message, or the key itself if not found
     */
    public String getMessage(String key) {
        return getMessage(key, defaultLanguage);
    }
    
    /**
     * Gets a translated message for the specified key and language.
     * 
     * @param key The message key
     * @param lang The language code
     * @return The translated message, or the key itself if not found
     */
    public String getMessage(String key, String lang) {
        // If the language is not loaded, use the default language
        if (!languages.containsKey(lang)) {
            lang = defaultLanguage;
        }
        
        // Get the message from the language file
        FileConfiguration langConfig = languages.get(lang);
        if (langConfig.contains(key)) {
            return langConfig.getString(key);
        }
        
        // If the message is not found in the specified language, try the default language
        if (!lang.equals(defaultLanguage) && languages.containsKey(defaultLanguage)) {
            langConfig = languages.get(defaultLanguage);
            if (langConfig.contains(key)) {
                return langConfig.getString(key);
            }
        }
        
        // If the message is not found in any language, return the key itself
        return key;
    }
    
    /**
     * Gets a translated message for the specified key for a player.
     * In the future, this could be extended to support per-player language preferences.
     * 
     * @param key The message key
     * @param player The player to get the message for
     * @return The translated message, or the key itself if not found
     */
    public String getMessage(String key, Player player) {
        // For now, just use the default language
        // In the future, this could be extended to support per-player language preferences
        return getMessage(key);
    }
}