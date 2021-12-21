package com.fanbakery.fancake.module.account.service;

import com.fanbakery.fancake.module.account.model.ReqAddressBook;
import com.fanbakery.fancake.repository.mapper.AccountMapper;
import com.fanbakery.fancake.repository.model.ItMbAddressBookEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountAddressService {

    private final AccountMapper accountMapper;



    /**
     * 배송지 리스트 조회
     * @param mbNo
     * @return
     */
    public List<ItMbAddressBookEntity> getAddressBookList(Long mbNo) {
        List<ItMbAddressBookEntity> addrbookList = accountMapper.selectAddressBookListByMbNo(mbNo);

        return addrbookList;
    }


    /**
     *  배송지 정보 조회
     * @param mbNo
     * @param abookSeq
     * @return
     */
    public ItMbAddressBookEntity getAddressBook(Long mbNo, Long abookSeq) {

        ItMbAddressBookEntity addressBook = accountMapper.selectAddressBookByMbNoSeq(mbNo, abookSeq);
        return addressBook;
    }


    /**
     * 입찰 에 필요한 배송지 정보 조회
     * @param mbNo
     * @return
     */
    public ItMbAddressBookEntity getBidAddressBook(Long mbNo) {

        ItMbAddressBookEntity addressBook = accountMapper.selectBidAddressBookByMbNo(mbNo);
        return addressBook;
    }




    /**
     * 배송지 등록
     * @param mbNo
     * @param reqAddrBook
     */
    public void addAddressBook( Long mbNo, ReqAddressBook reqAddrBook) {

        //1. set db req param
        ItMbAddressBookEntity addressBook = setAddressBookParam(mbNo, reqAddrBook);

        //2. db insert
         accountMapper.insertAddressBook(addressBook);
        Long seq = addressBook.getAbookSeq();

        return;
    }


    /**
     * 배송지 수정
     * @param mbNo
     * @param reqAddrBook
     */
    public void editAddressBook( Long mbNo, ReqAddressBook reqAddrBook) {

        //1. set db req param
        ItMbAddressBookEntity addressBook = setAddressBookParam(mbNo, reqAddrBook);

        //2. db update
        accountMapper.updateAddressBook(addressBook);

        return;
    }




    /**
     * 배송지 삭제
     * @param mbNo
     * @param abookSeq
     */
    public void deleteAddressBook(Long mbNo, Long abookSeq) {
        accountMapper.deleteAddressBook(mbNo, abookSeq);
        return;
    }


    /**
     * 기본 배송지 설정
     * @param mbNo
     * @param abookSeq
     */
    @Transactional
    public void setBaseAddressBook(Long mbNo, Long abookSeq) {

        //기존 기본 배송지 리셋
        accountMapper.updateUnsetBaseAddressBook(mbNo);

        //신규 기본 배송지 설정
        accountMapper.updateSetBaseAddressBook(mbNo, abookSeq);
        return;
    }




    /**
     * DB reqeust Parameter setting
     * @param mbNo
     * @param reqAddrBook
     * @return
     */
    private ItMbAddressBookEntity setAddressBookParam(Long mbNo, ReqAddressBook reqAddrBook) {

        if(reqAddrBook.getAbookAddress2() == null){
            reqAddrBook.setAbookAddress2("");
        }

        if(reqAddrBook.getAbookAddress3() == null){
            reqAddrBook.setAbookAddress3("");
        }

        if(reqAddrBook.getAbookInfo() == null) {
            reqAddrBook.setAbookInfo("");
        }

        ItMbAddressBookEntity addressBook = ItMbAddressBookEntity.builder()
                .abookMbNo(mbNo)
                .abookSeq(reqAddrBook.getAbookSeq())
                .abookBaseYn(false)
                .abookTitle(reqAddrBook.getAbookTitle())
                .abookReciever(reqAddrBook.getAbookReciever())
                .abookPhone(reqAddrBook.getAbookPhone())
                .abookAddress1(reqAddrBook.getAbookAddress1())
                .abookAddress2(reqAddrBook.getAbookAddress2())
                .abookAddress3(reqAddrBook.getAbookAddress3())
                .abookZipCode(reqAddrBook.getAbookZipCode())
                .abookInfo(reqAddrBook.getAbookInfo())
                .abookRegTime(LocalDateTime.now())
                .build();

        return addressBook;
    }

}
