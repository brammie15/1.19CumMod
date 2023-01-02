package dev.brammie15.cummod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.brammie15.cummod.CumMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CumCentrifugeScreen extends AbstractContainerScreen<CumCentrifugeMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CumMod.MODID, "textures/gui/cum_centrifuge.png");

    public CumCentrifugeScreen(CumCentrifugeMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);

        renderIsRunning(stack, x, y);
    }

    private void renderIsRunning(PoseStack stack, int x, int y) {
        if(menu.isRunning()){
            blit(stack, 176, 0, 176, 0, 14, 14, 256, 256);
        }

    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }
}
