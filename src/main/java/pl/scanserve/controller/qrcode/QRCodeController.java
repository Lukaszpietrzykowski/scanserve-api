package pl.scanserve.controller.qrcode;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.scanserve.service.qrcode.QRCodeService;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/qrCode")
@RequiredArgsConstructor
public class QRCodeController {

    @GetMapping(value = "/generate/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage createQRCode(@PathVariable("barcode") String barcode) throws Exception {
        return QRCodeService.generateQRCodeImage(barcode);
    }
}
