package ak.mcmod.ak_mixins;

import static ak.mcmod.ak_mixins.AkMixins.LOGGER;
import static ak.mcmod.ak_mixins.AkMixins.MOD_ID;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
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
    COMMON.damageEnchantmentMaxLevel = COMMON.damageEnchantmentMaxLevelValue.get();
    COMMON.diggingEnchantmentMaxLevel = COMMON.diggingEnchantmentMaxLevelValue.get();
    COMMON.digDurabilityEnchantmentMaxLevel = COMMON.digDurabilityEnchantmentMaxLevelValue.get();
    COMMON.lootBonusEnchantmentMaxLevel = COMMON.lootBonusEnchantmentMaxLevelValue.get();
    COMMON.bookShelfEnchantmentPowerBonus = COMMON.bookShelfEnchantmentPowerBonusValue.get();
  }

  public static class Common {

    public double playerServerMovingLimit = 100.0;
    public boolean modifyPlayerServerMovingLimit = false;
    public int damageEnchantmentMaxLevel = 10;
    public int diggingEnchantmentMaxLevel = 10;
    public int digDurabilityEnchantmentMaxLevel = 10;
    public int lootBonusEnchantmentMaxLevel = 10;
    public int bookShelfEnchantmentPowerBonus = 10;
    private final DoubleValue playerServerMovingLimitValue;
    private final BooleanValue modifyPlayerServerMovingLimitValue;
    private final IntValue damageEnchantmentMaxLevelValue;
    private IntValue diggingEnchantmentMaxLevelValue;
    private IntValue digDurabilityEnchantmentMaxLevelValue;
    private IntValue lootBonusEnchantmentMaxLevelValue;
    private IntValue bookShelfEnchantmentPowerBonusValue;

    Common(Builder builder) {
      builder.comment("Common settings")
          .push(MOD_ID);
      playerServerMovingLimitValue = builder.comment("Set player moving amount limitation")
          .defineInRange("PlayerMovingLimit", playerServerMovingLimit, 1.0, Float.MAX_VALUE);
      modifyPlayerServerMovingLimitValue = builder.comment("Modify player moving amout limitation")
          .define("ModifyPlayerMovingLimit", modifyPlayerServerMovingLimit);
      damageEnchantmentMaxLevelValue = builder.comment("Set Max Level of Damage Enchantment")
              .defineInRange("DamageEnchantmentMaxLevel", damageEnchantmentMaxLevel,1, Integer.MAX_VALUE);
      diggingEnchantmentMaxLevelValue = builder.comment("Set Max Level of Digging Enchantment")
          .defineInRange("DiggingEnchantmentMaxLevel", diggingEnchantmentMaxLevel,1, Integer.MAX_VALUE);
      digDurabilityEnchantmentMaxLevelValue = builder.comment("Set Max Level of Dig Durability Enchantment")
          .defineInRange("DigDurabilityEnchantmentMaxLevel", digDurabilityEnchantmentMaxLevel,1, Integer.MAX_VALUE);
      lootBonusEnchantmentMaxLevelValue = builder.comment("Set Max Level of Loot Bonus Enchantment")
          .defineInRange("LootBonusEnchantmentMaxLevel", lootBonusEnchantmentMaxLevel,1, Integer.MAX_VALUE);
      bookShelfEnchantmentPowerBonusValue = builder.comment("Set Enchantment Power Bonus of BookShelf")
              .defineInRange("BookShelfEnchantmentPowerBonus", bookShelfEnchantmentPowerBonus, 1, Integer.MAX_VALUE);
      builder.pop();
    }
  }
}
