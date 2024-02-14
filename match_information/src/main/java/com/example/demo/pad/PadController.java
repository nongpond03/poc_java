package com.example.demo.pad;

import com.example.demo.models.pad.PadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/pad")
public class PadController {

    @Autowired
    private PadService service;

    @PostMapping("/runningNumber/v0")
    public String generateRunningNumberV0(@RequestBody PadRequest req) throws InterruptedException {
        return service.getRunningNumberV0(req);
    }

    @PostMapping("/runningNumber/v1")
    public String generateRunningNumberV1(@RequestBody PadRequest req) throws InterruptedException {
        return service.getRunningNumberV1(req);
    }

    @PostMapping("/runningNumber/v2")
    public String generateRunningNumberV2(@RequestBody PadRequest req) throws InterruptedException {
        return service.getRunningNumberV2(req);
    }

    @PostMapping("/ktbAuthRequestUID/v0")
    public String generateKTBAuthRequestUIDV0(@RequestBody PadRequest req) {
        return service.getKTBAuthRequestUIDV0(req);
    }

    @PostMapping("/ktbAuthRequestUID/v1")
    public String generateKTBAuthRequestUIDV1(@RequestBody PadRequest req) {
        return service.getKTBAuthRequestUIDV1(req);
    }

    @PostMapping("/ktbAuthRequestUID/v2")
    public String generateKTBAuthRequestUIDV2(@RequestBody PadRequest req) {
        return service.getKTBAuthRequestUIDV2(req);
    }

    @PostMapping("/kbankCreditRunningNumber/v0")
    public String generateKBANKCreditRunningNumberV0(@RequestBody PadRequest req) {
        return service.getKBANKCreditRunningNumberV0(req);
    }

    @PostMapping("/kbankCreditRunningNumber/v1")
    public String generateKBANKCreditRunningNumberV1(@RequestBody PadRequest req) {
        return service.getKBANKCreditRunningNumberV1(req);
    }

    @PostMapping("/kbankCreditRunningNumber/v2")
    public String generateKBANKCreditRunningNumberV2(@RequestBody PadRequest req) {
        return service.getKBANKCreditRunningNumberV2(req);
    }

    @PostMapping("/ttb/v0")
    public String generateTTBRunningNumberV0(@RequestBody PadRequest req) {
        return service.getTTBRunningNumberV0(req);
    }

    @PostMapping("/runningNumberWithThreads")
    public String generateRunningNumberWithThreads(@RequestBody PadRequest req) throws InterruptedException {
        service.getRunningNumberWithThreads(req);
        return "";
    }
}
