package com.fancake.manage.service;

import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.PayDTO;
import com.fancake.manage.dto.ZzimDTO;
import com.fancake.manage.entity.Pay;
import com.fancake.manage.entity.Zzim;
import com.fancake.manage.repository.PayRepository;
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
public class PayServiceImpl implements PayService{

    //매우 중요한 부분
    private final PayRepository repository; //반드시 final 로 선언

    @Override
    public PayDTO view(int mbno){
        Optional<Pay> result=repository.findById(mbno);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(PayDTO dto) {
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
    public PageResultDTO<PayDTO, Pay> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mbno").descending());
        Page<Pay> result = repository.findAll( pageable );

        Function<Pay,PayDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
