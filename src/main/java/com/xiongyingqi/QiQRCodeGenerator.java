package com.xiongyingqi;

import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xiongyingqi.qrcode.QRCode;
import com.xiongyingqi.qrcode.QRCodeGenerator;
import com.xiongyingqi.util.Assert;
import com.xiongyingqi.util.EntityHelper;

import java.io.File;
import java.io.IOException;

/**
 * Created by qi<a href="http://xiongyingqi.com">xiongyingqi.com</a> on 14-12-2.
 */
public class QiQRCodeGenerator {
    public void start() throws IOException, NotFoundException {
        String logoPath = getClass().getClassLoader().getResource("logo.jpg").getFile();
        String folder = new File(logoPath).getParent();

        File png = QRCodeGenerator.newGenerator()
                .content("http://xiongyingqi.com/wedding/")
                .characterSet("UTF-8")
                .errorCorrectionLevel(ErrorCorrectionLevel.H)
                .logoFlag(true)
                .format("png")
                .margin(0)
                .width(300)
                .height(300)
                .path(folder)
                .logoPath(logoPath)
                .generate();
        File pngBig = QRCodeGenerator.newGenerator()
                .content("http://xiongyingqi.com/wedding/")
                .characterSet("UTF-8")
                .errorCorrectionLevel(ErrorCorrectionLevel.H)
                .logoFlag(true)
                .format("png")
                .margin(0)
                .width(1200)
                .height(1200)
                .path(folder)
                .logoPath(logoPath)
                .generate();

        File png2 = QRCodeGenerator.newGenerator()
                .content("http://xiongyingqi.com/wedding/")
                .generate();
        EntityHelper.print(png);
        EntityHelper.print(pngBig);
        EntityHelper.print(png2);

        QRCode qrCode = new QRCode();
        String decode = qrCode.decode(png.getPath());
        Assert.equals(decode, "http://xiongyingqi.com/wedding/");
    }

    public static void main(String[] args) throws IOException, NotFoundException {
        QiQRCodeGenerator qiQRCodeGenerator = new QiQRCodeGenerator();
        qiQRCodeGenerator.start();
    }
}
