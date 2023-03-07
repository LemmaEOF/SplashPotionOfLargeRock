package gay.lemmaeof.spolr;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class SplashPotionOfLargeRockClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		EntityRendererRegistry.register(SplashPotionOfLargeRock.LARGE_ROCK_SPLASH_POTION_ENTITY, FlyingItemEntityRenderer::new);
	}
}
