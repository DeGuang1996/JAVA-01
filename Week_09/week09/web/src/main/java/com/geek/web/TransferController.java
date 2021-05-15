package com.geek.web;

import com.geek.api.dto.TransferDTO;
import com.geek.web.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer.json")
    public String transfer(@RequestBody TransferDTO transferDTO) {
        transferService.transferByDubbo(transferDTO);
        return "SUCCESS";
    }

    @GetMapping("hi")
    public String hi() {
        return "hi";
    }
}
