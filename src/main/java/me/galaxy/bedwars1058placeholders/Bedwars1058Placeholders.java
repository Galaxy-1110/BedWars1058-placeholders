package me.galaxy.bedwars1058placeholders;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Taskable;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Bedwars1058Placeholders extends PlaceholderExpansion implements Taskable {

    private BedWars api;

    @Override
    public String getIdentifier() {
        return "bw+";
    }

    @Override
    public String getAuthor() {
        return "Galaxy_";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getRequiredPlugin() {
        return "BedWars1058";
    }

    @Override
    public List<String> getPlaceholders() {
        List<String> list = new ArrayList<>(Arrays.asList("team_letter",
                "team_color"));
        for (String placeholder : list)
            list.set(list.indexOf(placeholder), "%bw+_" + placeholder + "%");
        return list;
    }

    @Override
    public void start() {
        api = Bukkit.getServicesManager().getRegistration(BedWars .class).getProvider();
    }

    @Override
    public void stop() {

    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(player == null) return "";
        Player p = player.getPlayer();
        if(p == null) return "";

        IArena arena = api.getArenaUtil().getArenaByPlayer(p);
        if(arena == null) return "";
        if(params.startsWith("team_color")) {
            ITeam team;
            if(params.equalsIgnoreCase("team_color"))
                team = arena.getTeam(p);
            else team = arena.getTeam(params.replace("team_color_", ""));
            if(team != null)
                return team.getColor().chat().toString();
        }
        if(params.startsWith("team_letter")) {
            ITeam team;
            if(params.equalsIgnoreCase("team_letter"))
                team = arena.getTeam(p);
            else team = arena.getTeam(params.replace("team_letter_", ""));
            if(team != null)
                return team.getName().substring(0,1).toUpperCase();
            else
                if(arena.getStatus().equals(GameState.playing))
                    return "&7[SPEC]";
                else
                    return "";
        }
        return "";
    }
}
