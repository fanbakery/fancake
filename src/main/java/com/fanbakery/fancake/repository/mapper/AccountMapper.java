package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.repository.model.ItMbAddressBookEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    /**  ----------------
     * it_mb_address_book table
     ** ---------------------- */

    //select
    public List<ItMbAddressBookEntity>  selectAddressBookListByMbNo(Long mbNo);
    public ItMbAddressBookEntity selectAddressBookByMbNoSeq(Long mbNo, Long abookSeq);

    public ItMbAddressBookEntity selectBidAddressBookByMbNo(Long mbNo);

    //insert
    public Long insertAddressBook( ItMbAddressBookEntity addressBook);


    //update
    public void updateAddressBook( ItMbAddressBookEntity addressBook);
    public void updateUnsetBaseAddressBook(Long mbNo);
    public void updateSetBaseAddressBook(Long mbNo, Long abookSeq);


    //delete
    public void deleteAddressBook(Long mbNo, Long abookSeq);
}
