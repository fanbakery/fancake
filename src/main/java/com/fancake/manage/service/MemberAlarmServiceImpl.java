package com.fancake.manage.service;

import com.fancake.manage.dto.MemberAlarmDTO;
import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Member;
import com.fancake.manage.entity.MemberAlarm;
import com.fancake.manage.repository.MemberAlarmRepository;
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
public class MemberAlarmServiceImpl implements MemberAlarmService{

    //매우 중요한 부분
    private final MemberAlarmRepository repository; //반드시 final 로 선언

    @Override
    public MemberAlarmDTO view(int mbalarmseq){
        Optional<MemberAlarm> result=repository.findById(mbalarmseq);
        return result.isPresent() ? entityToDto(result.get()) :null ;

    }

    @Override
    public int register(MemberAlarmDTO dto) {

        log.info("DTO-----------------------------");
        log.info(dto);

        //MemberAlarm entity = dtoToEntity(dto);
        //log.info(entity);
        //repository.save(entity);

        //return entity.getMbalarmseq();
        return 0;
    }

    @Override
    public PageResultDTO<MemberAlarmDTO, MemberAlarm> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mbalarmseq").descending());
        Page<MemberAlarm> result = repository.findAll( pageable );

        Function<MemberAlarm,MemberAlarmDTO> fn=(entity ->entityToDto(entity));

        return new PageResultDTO<>(result,fn);

    }
}
