package com.mech.view;

import lombok.Data;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

@Data
public class Sprite {
    private BufferedImage image;

    @SneakyThrows
    public Sprite(String path) {
        URL file = this.getClass().getClassLoader().getResource(path);
        assert file != null;
        image = ImageIO.read(file);
    }
}
