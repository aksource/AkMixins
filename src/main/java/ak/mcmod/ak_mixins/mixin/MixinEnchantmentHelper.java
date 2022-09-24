package ak.mcmod.ak_mixins.mixin;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * {@link EnchantmentHelper}クラスに対する操作をするクラス
 * Created by A.K. on 2022/09/22.
 */
@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

  /**
   * Enchantmentを候補に追加するかどうかの際のランダム性を決めている部分をほぼ追加する形に変更
   * @param constant 書き換え前定数値
   * @return 書き換え後定数値
   */
//  @ModifyConstant(method = "selectEnchantment", constant = {@Constant(intValue = 50)})
//  private static int hookSelectEnchantment(int constant) {
//    return 1;
//  }

  /**
   * Cost計算時の本棚ボーナス値の上限を事実上撤廃
   * @param constant 書き換え前定数値
   * @return 書き換え後低数値
   */
  @ModifyConstant(method = "getEnchantmentCost", constant = {@Constant(intValue = 15)})
  private static int hookGetEnchantmentCost(int constant) {
    return Integer.MAX_VALUE;
  }
}
