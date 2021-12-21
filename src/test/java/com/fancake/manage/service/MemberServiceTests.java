package com.fancake.manage.service;

import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    private MemberService service;

//    @Test
//    public void testRegister() {
//        MemberDTO memberDTO = MemberDTO.builder()
//                .mb_name("Sample mb_name..")
//                .mb_nick("Sample mb_nick")
//                .mb_email("user0")
//                .build();
//        System.out.println( service.register(memberDTO) );
//    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO= PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<MemberDTO,Member> resultDTO=service.getList(pageRequestDTO);

        System.out.println("PREV:"+resultDTO.isPrev());
        System.out.println("NEXT:"+resultDTO.isNext());
        System.out.println("TOTAL:"+resultDTO.getTotalPage());
        System.out.println("-----------------------------------");

        for(MemberDTO memberDTO: resultDTO.getDtoList()){
            System.out.println(memberDTO);
        }

        System.out.println("-----------------------------------");
        resultDTO.getPageList().forEach(i->System.out.println(i));
    }

}
