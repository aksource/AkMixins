package ak.mcmod.ak_mixins.mixin;

import ak.mcmod.ak_mixins.ConfigUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import org.spongepowered.asm.mixin.Mixin;

/**
 * {@link LootBonusEnchantment}に対する操作を行うクラス<br>
 * Enchantmentを継承することで、メソッドの上書きを行う
 * Created by A.K. on 2022/09/22.
 */
@Mixin(LootBonusEnchantment.class)
public abstract class MixinLootBonusEnchantment extends Enchantment {

  protected MixinLootBonusEnchantment(Rarity rarity,
      EnchantmentCategory category,
      EquipmentSlot[] slots) {
    super(rarity, category, slots);
  }

  /**
   * 最大レベルを上書き
   * @return 最大レベル
   */
  @Override
  public int getMaxLevel() {
    return ConfigUtils.COMMON.lootBonusEnchantmentMaxLevel;
  }

  /**
   * 最小コストを上書き
   * @param level エンチャントレベル
   * @return 最小コスト
   */
//  @Override
//  public int getMinCost(int level) {
//    return 15;
//  }

  /**
   * 最大コストを上書き
   * @param level エンチャントレベル
   * @return 最大コスト
   */
  @Override
  public int getMaxCost(int level) {
    return Integer.MAX_VALUE;
  }
}
