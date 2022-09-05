package ak.mcmod.ak_mixins;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by A.K. on 2021/09/04.
 */
@Mod(AkMixins.MOD_ID)
public class AkMixins {
  public static final String MOD_ID = "ak_mixins";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
  public AkMixins() {
    final var modEventBus =
        FMLJavaModLoadingContext.get().getModEventBus();
    modEventBus.addListener(ConfigUtils::configLoading);
    ModLoadingContext.get().registerConfig(Type.COMMON, ConfigUtils.configSpec);
  }
}
