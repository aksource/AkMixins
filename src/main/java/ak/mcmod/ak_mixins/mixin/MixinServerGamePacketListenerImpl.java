package ak.mcmod.ak_mixins.mixin;

import ak.mcmod.ak_mixins.ConfigUtils;
import javax.annotation.Nonnull;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * {@link ServerGamePacketListenerImpl}に対する操作を行うクラス
 * このクラス自体がServerGamePacketListenerImplになるが、メンバー変数、メソッドを呼び出す場合はShadowアノテーションを付けた変数を宣言する Created by A.K.on
 * 2022/08/24.
 */
@Mixin(ServerGamePacketListenerImpl.class)
public class MixinServerGamePacketListenerImpl {

  @Shadow
  private int tickCount;

  @Shadow
  public ServerPlayer getPlayer() {
    return null;
  }

  /**
   * handleMovePlayerのローカル変数f2のLOAD時の処理に割り込む
   *
   * @param f2 プレイヤーの移動距離の制限判定時に利用されるfloat型変数
   * @return f2のLOADの際に代わりに返却する値
   */
  @ModifyVariable(method = "handleMovePlayer", at = @At(value = "LOAD"), name = "f2")
  private float hookHandleMovePlayerForMovingLimit(float f2) {
    // thisの使用例 tickCountはprivate変数
//    AkMixins.LOGGER.info(this.tickCount);
    // thisの使用例 getPlayerメソッドはServerPlayerConnectionインターフェースのメソッド
    this.getPlayer();
    return ConfigUtils.COMMON.modifyPlayerServerMovingLimit
        ? (float) ConfigUtils.COMMON.playerServerMovingLimit : f2;
  }

  /**
   * isSingleplayerOwnerメソッドの最初に処理を割り込ませるメソッド
   *
   * @param callbackInfo CallbackInfo
   */
  @Inject(method = "isSingleplayerOwner", at = @At(value = "HEAD"), cancellable = true)
  private void hookIsSinglePlayerOwner(@Nonnull CallbackInfoReturnable<Boolean> callbackInfo) {
    // ローカルで移動制限の判定を確認する場合はfalseを常に返却するようにする
    // ただし、この場合、ゲーム終了処理が正常に行われないので、ゲームを辞める場合はランチャー側で強制終了する必要あり
//    callbackInfo.setReturnValue(false);
  }
}
