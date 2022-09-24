package ak.mcmod.ak_mixins.mixin;

import ak.mcmod.ak_mixins.ConfigUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.spongepowered.asm.mixin.Mixin;

/**
 * {@link Block}に対する操作をするクラス
 * Created by A.K. on 2022/09/23.
 */
@Mixin(Block.class)
public abstract class MixinBlock implements IForgeBlock {

  /**
   * ブロックのエンチャントボーナス値を返却するメソッド<br>
   * IForgeBlockのデフォルト実装をいじることはできないので、Blockクラス側にメソッドを追加する形で対応
   * @param state BlockState
   * @param level The level
   * @param pos Block position in level
   * @return エンチャントボーナス値
   */
  @Override
  public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
    return state.is(Blocks.BOOKSHELF) ? ConfigUtils.COMMON.bookShelfEnchantmentPowerBonus : 0;
  }
}
