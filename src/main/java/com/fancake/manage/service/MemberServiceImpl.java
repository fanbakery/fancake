package com.fancake.manage.service;

import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Member;
import com.fancake.manage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동주입
public class MemberServiceImpl implements MemberService{

    //매우 중요한 부분
    private final MemberRepository repository; //반드시 final 로 선언

    @Override
    public MemberDTO view(int mbno){
        Optional<Member> result=repository.findById(mbno);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(MemberDTO dto) {

        log.info("DTO-----------------------------");
        log.info(dto);

        Member entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);

        return entity.getMbno();
    }

    @Override
    public PageResultDTO<MemberDTO, Member> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mbno").descending());
        Page<Member> result = repository.findAll( pageable );

        Function<Member,MemberDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
