package ak.mcmod.ak_mixins;

import static ak.mcmod.ak_mixins.AkMixins.LOGGER;
import static ak.mcmod.ak_mixins.AkMixins.MOD_ID;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

/**
 * Created by A.K. on 2022/09/05.
 */
public class ConfigUtils {
  public static final Common COMMON;
  static final ForgeConfigSpec configSpec;
  static {
    var builder = new ForgeConfigSpec.Builder();
    COMMON = new Common(builder);
    configSpec = builder.build();
  }

  @SubscribeEvent
  public static void configLoading(final ModConfigEvent.Loading event) {
    LOGGER.debug("Loaded AkMixins config file {}",
        event.getConfig().getFileName());
    COMMON.playerServerMovingLimit = COMMON.playerServerMovingLimitValue.get();
    COMMON.modifyPlayerServerMovingLimit = COMMON.modifyPlayerServerMovingLimitValue.get();
  }

  public static class Common {

    public double playerServerMovingLimit = 100.0;
    public boolean modifyPlayerServerMovingLimit = false;
    private final DoubleValue playerServerMovingLimitValue;
    private final BooleanValue modifyPlayerServerMovingLimitValue;

    Common(Builder builder) {
      builder.comment("Common settings")
          .push(MOD_ID);
      playerServerMovingLimitValue = builder.comment("Set player moving amount limitation")
          .defineInRange("PlayerMovingLimit", playerServerMovingLimit, 1.0, Float.MAX_VALUE);
      modifyPlayerServerMovingLimitValue = builder.comment("Modify player moving amout limitation")
          .define("ModifyPlayerMovingLimit", modifyPlayerServerMovingLimit);
      builder.pop();
    }
  }
}
