package com.fancake.manage.service;

import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.NickBlockDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Member;
import com.fancake.manage.entity.NickBlock;
import com.fancake.manage.repository.MemberRepository;
import com.fancake.manage.repository.NickBlockRepository;
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
public class NickBlockServiceImpl implements NickBlockService{

    //매우 중요한 부분
    private final NickBlockRepository repository; //반드시 final 로 선언

    @Override
    public NickBlockDTO view(int mbno){
        Optional<NickBlock> result=repository.findById(mbno);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(NickBlockDTO dto) {

        log.info("DTO-----------------------------");
        log.info(dto);

        NickBlock entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);

        return entity.getMbno();
    }

    @Override
    public PageResultDTO<NickBlockDTO, NickBlock> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mbno").descending());
        Page<NickBlock> result = repository.findAll( pageable );

        Function<NickBlock,NickBlockDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
