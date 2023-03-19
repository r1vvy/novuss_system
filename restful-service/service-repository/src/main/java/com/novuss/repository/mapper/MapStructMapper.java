package com.novuss.repository.mapper;

import com.novuss.domain.*;
import com.novuss.repository.entity.*;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    Attachment attachmentEntityToDomain(AttachmentEntity attachmentEntity);
    AttachmentEntity attachmentDomainToEntity(Attachment attachment);

    Category categoryEntityToDomain(CategoryEntity categoryEntity);
    CategoryEntity categoryDomainToEntity(Category category);

    Club clubEntityToDomain(ClubEntity clubEntity);
    ClubEntity clubDomainToEntity(Club club);

    Competition competitionEntityToDomain(CompetitionEntity competitionEntity);
    CompetitionEntity competitionDomainToEntity(Competition competition);

    CompetitionPlayer competitionPlayerEntityToDomain(CompetitionPlayerEntity competitionPlayerEntity);
    CompetitionPlayerEntity competitionPlayerDomainToEntity(CompetitionPlayer competitionPlayer);

    CompetitionReferee competitionRefereeEntityToDomain(CompetitionRefereeEntity competitionRefereeEntity);
    CompetitionRefereeEntity competitionRefereeDomainToEntity(CompetitionReferee competitionReferee);

    ContactInfo contactInfoEntityToDomain(ContactInfoEntity contactInfoEntity);
    ContactInfoEntity contactInfoDomainToEntity(ContactInfo contactInfo);

    Licence licenceEntityToDomain(LicenceEntity licenceEntity);
    LicenceEntity licenceDomainToEntity(Licence licence);

    Location locationEntityToDomain(LocationEntity locationEntity);
    LocationEntity locationDomainToEntity(Location location);

    Person personEntityToDomain(PersonEntity personEntity);
    PersonEntity personDomainToEntity(Person person);

    Player playerEntityToDomain(PlayerEntity playerEntity);
    PlayerEntity playerDomainToEntity(Player player);

    Referee refereeEntityToDomain(RefereeEntity refereeEntity);
    RefereeEntity refereeDomainToEntity(Referee referee);

    SportsClass sportsClassEntityToDomain(SportsClassEntity sportsClassEntity);
    SportsClassEntity sportsClassDomainToEntity(SportsClass sportsClass);
}
