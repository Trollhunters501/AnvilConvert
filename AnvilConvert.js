let AnvilconvertJar;
let PrefixAnvilcon = "[AnvilConvert] ";
function enable(){
    console.info(PrefixAnvilcon+"§eLoading...");
    AnvilconvertJar = new NnClassLoader({ urls: ["https://github.com/Awzaw/AnvilConverter/releases/download/v1.0/AnvilConverter.jar"] });
    manager.createCommand("regionconvert", "Convert MCregion worlds to Anvil!", AnvilCmd, "Use /regionconvert <world>", [], "anvil.convert.world");
    console.info(PrefixAnvilcon+"§aDone!");
}
function disable(){
    console.info(PrefixAnvilcon+"§cBye!");
};
function AnvilCmd(sender, args, label){
    if(!sender.hasPermission("anvil.convert.world")){
        sender.sendMessage("§c"+server.getLanguage().translateString("commands.generic.permission"));
        return;
    }
    if(args.length < 1){
        sender.sendMessage("§c"+server.getLanguage().translateString("commands.generic.usage", "/regionconvert <world>"));
        return;
    }
    try{
      let main = AnvilconvertJar.type("net.minecraft.world.level.storage.AnvilConverter");
      main.main([server.dataPath+"worlds", args[0]]);
    }catch(error){
        sender.sendMessage("§c"+server.getLanguage().translateString("commands.generic.exception"));
        console.info("§cError in command regionconvert", error);
    }
}
module.exports = {
    onEnable: enable,
    onDisable: disable
}