package com.fanbakery.fancake.module.account.controller;

import com.fanbakery.fancake.module.account.model.ReqAddressBook;
import com.fanbakery.fancake.module.account.service.AccountAddressService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountRestController {
    private final AccountAddressService addressService;


    @PostMapping("/address/add")
    public ResponseEntity<?> addAddressBook(ReqAddressBook reqAddrBook
                                            , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        addressService.addAddressBook(user.getMbNo(), reqAddrBook);

        return ResponseEntity.ok().build();
    }



    @PostMapping("/address/edit")
    public ResponseEntity<?> editAddressBook(ReqAddressBook reqAddrBook, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long mbNo = user.getMbNo();

        //check abookSeq null
        if( reqAddrBook.getAbookSeq() == null || reqAddrBook.getAbookSeq() == 0L)
        {
            log.error("[EDIT_ADDR_BOOK] :: no abook_seq!! MB_NO_" + mbNo);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //2. update
        addressService.editAddressBook(mbNo, reqAddrBook);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/address/delete")
    public ResponseEntity<?> deleteAddressBook(@RequestParam(name="seq") Long abookSeq
            , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        addressService.deleteAddressBook(user.getMbNo(), abookSeq);

        addressService.deleteAddressBook(41L, abookSeq);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/address/set_base")
    public ResponseEntity<?> setBaseAddressBook(@RequestParam(name="seq") Long abookSeq
            , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        addressService.setBaseAddressBook(user.getMbNo(), abookSeq);

        return ResponseEntity.ok().build();
    }
}
