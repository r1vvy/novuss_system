package com.novuss.restfulservice.repository.adapter.sportsClass;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.sportsClass.SaveSportsClassPort;
import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.repository.converter.SportsClassDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.SportsClassEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.SportsClassJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveSportsClassAdapter implements SaveSportsClassPort {
    private final SportsClassJpaRepository sportsClassJpaRepository;
    @Override
    public SportsClass save(SportsClass sportsClass) {
        sportsClassJpaRepository.findByTitle(sportsClass.title())
                .ifPresent(
                        (existingSportsClass) -> {
                            throw new EntityExistsException("SportsClass already exists with title = " + sportsClass.title());
                        }
                );
        var sportsClassEntity = SportsClassDomainToEntityConverter.convert(sportsClass);
        var updatedEntity = sportsClassJpaRepository.save(sportsClassEntity);

        return SportsClassEntityToDomainConverter.convert(updatedEntity);
    }
}
