package com.fancake.manage.service;

import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Member;

public interface MemberService {

    int register(MemberDTO dto);
    MemberDTO view(int mbno);


    //이후에 추가한 부분
    PageResultDTO<MemberDTO, Member> getList(PageRequestDTO requestDTO);

    default Member dtoToEntity(MemberDTO dto){

        Member entity = Member.builder()
                .mbno(dto.getMbno())
                .mbprofile(dto.getMbprofile())
                .mbnick(dto.getMbnick())
                .mbemail(dto.getMbemail())
                .mbhp(dto.getMbhp())
                .mbroute(dto.getMbroute())
                .build();
        return entity;

    }

    default MemberDTO entityToDto(Member entity){

        MemberDTO dto = MemberDTO.builder()
                .mbno(entity.getMbno())
                .mbprofile(entity.getMbprofile())
                .mbnick(entity.getMbnick())
                .mbemail(entity.getMbemail())
                .mbhp(entity.getMbhp())
                .mbroute(entity.getMbroute())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

       return dto;

    }

}
