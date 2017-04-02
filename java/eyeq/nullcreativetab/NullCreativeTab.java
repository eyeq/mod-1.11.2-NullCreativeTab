package eyeq.nullcreativetab;

import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.creativetab.UCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import static eyeq.nullcreativetab.NullCreativeTab.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class NullCreativeTab {
    public static final String MOD_ID = "eyeq_nullcreativetab";

    @Mod.Instance(MOD_ID)
    public static NullCreativeTab instance;

    public static final CreativeTabs TAB_NULL = new UCreativeTab("Null", () -> new ItemStack(Blocks.BARRIER));

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(event.getSide().isServer()) {
            return;
        }
        createFiles();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if(event.getSide().isServer()) {
            return;
        }
        setNullCreativeTabs();
    }

    public static void createFiles() {
        File project = new File("../1.11.2-NullCreativeTab");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, TAB_NULL, "null");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }

    @SideOnly(Side.CLIENT)
    public static void setNullCreativeTabs() {
        for(Block block : Block.REGISTRY) {
            if(block != null && block.getCreativeTabToDisplayOn() == null) {
                block.setCreativeTab(TAB_NULL);
            }
        }
        for(Item item : Item.REGISTRY) {
            if(item != null && item.getCreativeTab() == null) {
                item.setCreativeTab(TAB_NULL);
            }
        }
    }
}
