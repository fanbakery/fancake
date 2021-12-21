package com.fancake.manage.service;

import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.SeenDTO;
import com.fancake.manage.dto.ZzimDTO;
import com.fancake.manage.entity.Seen;
import com.fancake.manage.entity.Zzim;
import com.fancake.manage.repository.SeenRepository;
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
public class ZzimServiceImpl implements ZzimService{

    //매우 중요한 부분
    private final ZzimRepository repository; //반드시 final 로 선언

    @Override
    public ZzimDTO view(int mbno){
        Optional<Zzim> result=repository.findById(mbno);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(ZzimDTO dto) {
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
    public PageResultDTO<ZzimDTO, Zzim> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("zzimitemseq").descending());
        Page<Zzim> result = repository.findAll( pageable );

        Function<Zzim,ZzimDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
