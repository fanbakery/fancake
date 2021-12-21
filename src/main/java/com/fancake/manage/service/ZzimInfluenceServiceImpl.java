package com.fancake.manage.service;

import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.ZzimDTO;
import com.fancake.manage.dto.ZzimInfluenceDTO;
import com.fancake.manage.entity.Zzim;
import com.fancake.manage.entity.ZzimInfluence;
import com.fancake.manage.repository.ZzimInfluenceRepository;
import com.fancake.manage.repository.ZzimRepository;
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
public class ZzimInfluenceServiceImpl implements ZzimInfluenceService{

    //매우 중요한 부분
    private final ZzimInfluenceRepository repository; //반드시 final 로 선언

    @Override
    public ZzimInfluenceDTO view(int mbno){
        Optional<ZzimInfluence> result=repository.findById(mbno);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(ZzimInfluenceDTO dto) {
//
//        log.info("DTO-----------------------------");
//        log.info(dto);
//
//        Address entity = dtoToEntity(dto);
//        log.info(entity);
//        repository.save(entity);
//
//        return entity.getMbno();
        return 0;

    }

    @Override
    public PageResultDTO<ZzimInfluenceDTO, ZzimInfluence> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("zziminfluenseq").descending());
        Page<ZzimInfluence> result = repository.findAll( pageable );

        Function<ZzimInfluence,ZzimInfluenceDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
