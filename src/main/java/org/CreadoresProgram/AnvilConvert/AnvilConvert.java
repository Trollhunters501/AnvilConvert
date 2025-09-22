package org.CreadoresProgram.AnvilConvert;
import net.minecraft.world.level.storage.AnvilConverter;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
public class AnvilConvert extends PluginBase {
    public String prefix = "[AnvilConvert] ";
    @Override
    public void onEnable() {
        this.getLogger().info(this.prefix + "§eLoading...");
        this.getServer().getCommandMap().register("regionconvert", new CommandConver());
        this.getLogger().info(this.prefix + "§aDone!");
    }
    @Override
    public void onDisable() {
        this.getLogger().info(this.prefix + "§cBye!");
    }
    public class CommandConver extends Command {
        public CommandConver(){
            super("regionconvert", "Convert MCregion worlds to Anvil!", "/regionconvert <worldname>", new String[]{});
            this.setPermission("anvil.convert.world");
        }
        @Override
        public boolean execute(CommandSender sender, String label, String[] args){
            if(!sender.hasPermission("anvil.convert.world")){
                sender.sendMessage("§c"+getServer().getLanguage().translateString("commands.generic.permission"));
                return true;
            }
            if(args.length < 1){
                sender.sendMessage("§c"+getServer().getLanguage().translateString("commands.generic.usage", "/regionconvert <world>"));
                return true;
            }
            String worldName = String.join(" ", args);
            AnvilConverter.main(new String[]{ getServer().getDataPath()+"worlds", worldName});
            return true;
        }
    }
}