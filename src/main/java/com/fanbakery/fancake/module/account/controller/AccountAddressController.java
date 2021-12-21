package com.fanbakery.fancake.module.account.controller;

import com.fanbakery.fancake.module.account.service.AccountAddressService;
import com.fanbakery.fancake.repository.model.ItMbAddressBookEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/account/address-book")
public class AccountAddressController {

    private final AccountAddressService addressService;

    @GetMapping("/")
    public String addressBook(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        //1. get address book list
        List<ItMbAddressBookEntity> addrBookList = addressService.getAddressBookList(user.getMbNo());

        model.addAttribute("addrBookList", addrBookList);
        return "account/address-book/addressbook";
    }

    @GetMapping("/add")
    public String addAddressBook(Model model, HttpSession session){

        model.addAttribute("addressBook", new ItMbAddressBookEntity());
        return "account/address-book/addressbook2";
    }

    @GetMapping("/edit")
    public String addressBook3(@RequestParam(name="seq") Long abookSeq
                                ,Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        ItMbAddressBookEntity addressBook = addressService.getAddressBook(user.getMbNo(), abookSeq);

        model.addAttribute("addressBook", addressBook);
        return "account/address-book/addressbook3";
    }
}
