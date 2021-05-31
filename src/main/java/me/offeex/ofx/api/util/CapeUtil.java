package me.offeex.ofx.api.util;

import com.google.common.collect.Maps;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

public class CapeUtil {
    public static HashMap<String, Identifier> capes = Maps.newHashMap();
    public static String s = "6";

    public static void parseCape(String cape, String uuid) {
        cape = "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+0uMbPfrW1K3slf+tSJX52fTFFcX4A8Af8IN/aH/ABM/tv2zy/8Alh5ezZu/2jnO79K7Ssna+hYUUUUgCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD5j1DT/ALd5f73Zsz/DnOce/tXNXVrs2fPnOe1fYNFOEpQ0voEknrbU+ZPAHj//AIQb+0P+JZ9t+2eX/wAt/L2bN3+yc53fpX03RRVTkpO9hJWCiiioGFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAf/ZhXfk7QAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAyMC0xMC0xNFQxNjoxMjo1OCswMDowMLnyqyUAAAAldEVYdGRhdGU6bW9kaWZ5ADIwMjAtMTAtMTRUMTY6MTI6NTgrMDA6MDDIrxOZAAAAGnRFWHRleGlmOkJpdHNQZXJTYW1wbGUAOCwgOCwgOBLtPicAAAARdEVYdGV4aWY6Q29sb3JTcGFjZQAxD5sCSQAAACF0RVh0ZXhpZjpEYXRlVGltZQAyMDIwOjEwOjE0IDEwOjQzOjIyP8Y2ygAAABN0RVh0ZXhpZjpFeGlmT2Zmc2V0ADE3ONzWVn4AAAATdEVYdGV4aWY6SW1hZ2VMZW5ndGgAMzJN5cc3AAAAEnRFWHRleGlmOkltYWdlV2lkdGgANjRzFeAgAAAAGnRFWHRleGlmOlNvZnR3YXJlAEdJTVAgMi4xMC4xNCpQERgAAAAkdEVYdGV4aWY6dGh1bWJuYWlsOkJpdHNQZXJTYW1wbGUAOCwgOCwgOCAb9FMAAAAcdEVYdGV4aWY6dGh1bWJuYWlsOkNvbXByZXNzaW9uADb5ZXBXAAAAHnRFWHRleGlmOnRodW1ibmFpbDpJbWFnZUxlbmd0aAAxMjj6zzWaAAAAHXRFWHRleGlmOnRodW1ibmFpbDpJbWFnZVdpZHRoADI1NogG+hQAAAAodEVYdGV4aWY6dGh1bWJuYWlsOkpQRUdJbnRlcmNoYW5nZUZvcm1hdAAzMDSsR89oAAAAL3RFWHRleGlmOnRodW1ibmFpbDpKUEVHSW50ZXJjaGFuZ2VGb3JtYXRMZW5ndGgAMzE5NDUDmHcAAAAqdEVYdGV4aWY6dGh1bWJuYWlsOlBob3RvbWV0cmljSW50ZXJwcmV0YXRpb24ANhIVihoAAAAgdEVYdGV4aWY6dGh1bWJuYWlsOlNhbXBsZXNQZXJQaXhlbAAz4dfNWgAAAABJRU5ErkJggg==";
        NativeImage capeImage = readTexture(cape);
        int imageWidth = 64;
        int imageHeight = 32;

        for (int srcWidth = capeImage.getWidth(), srcHeight = capeImage.getHeight(); imageWidth < srcWidth || imageHeight < srcHeight; ) {
            imageWidth *= 2;
            imageHeight *= 2;
        }

        NativeImage imgNew = new NativeImage(imageWidth, imageHeight, true);
        for (int x = 0; x < capeImage.getWidth(); x++) {
            for (int y = 0; y < capeImage.getHeight(); y++) {
                imgNew.setPixelColor(x, y, capeImage.getPixelColor(x, y));
            }
        }

        capeImage.close();
        Identifier id = new Identifier("ofx", "maincape.png");
        applyTexture(id, imgNew);
        capes.put(uuid, id);
    }

    private static NativeImage readTexture(String textureBase64) {
        try {
            byte[] imgBytes = Base64.decodeBase64(textureBase64);
            ByteArrayInputStream bais = new ByteArrayInputStream(imgBytes);
            return NativeImage.read(bais);
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    private static void applyTexture(Identifier identifier, NativeImage nativeImage) {
        MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().getTextureManager().registerTexture(identifier, new NativeImageBackedTexture(nativeImage)));
    }

}
