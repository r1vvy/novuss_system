package com.novuss.restfulservice.repository.converter;


import com.novuss.restfulservice.repository.entity.*;
import com.restfulservice.domain.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

//    Attachment attachmentEntityToDomain(AttachmentEntity attachmentEntity);
//    AttachmentEntity attachmentDomainToEntity(Attachment attachment);
//
//    Category categoryEntityToDomain(CategoryEntity categoryEntity);
//    CategoryEntity categoryDomainToEntity(Category category);
//
//    Club clubEntityToDomain(ClubEntity clubEntity);
//    ClubEntity clubDomainToEntity(Club club);
//
//    Competition competitionEntityToDomain(CompetitionEntity competitionEntity);
//    CompetitionEntity competitionDomainToEntity(Competition competition);
//
//    CompetitionPlayer competitionPlayerEntityToDomain(CompetitionPlayerEntity competitionPlayerEntity);
//    CompetitionPlayerEntity competitionPlayerDomainToEntity(CompetitionPlayer competitionPlayer);
//
//    CompetitionReferee competitionRefereeEntityToDomain(CompetitionRefereeEntity competitionRefereeEntity);
//    CompetitionRefereeEntity competitionRefereeDomainToEntity(CompetitionReferee competitionReferee);
//
//    ContactInfo contactInfoEntityToDomain(ContactInfoEntity contactInfoEntity);
//    ContactInfoEntity contactInfoDomainToEntity(ContactInfo contactInfo);
//
//    Licence licenceEntityToDomain(LicenceEntity licenceEntity);
//    LicenceEntity licenceDomainToEntity(Licence licence);
//
//    Location locationEntityToDomain(LocationEntity locationEntity);
//    LocationEntity locationDomainToEntity(Location location);

    Player playerEntityToDomain(PlayerEntity playerEntity);
    PlayerEntity playerDomainToEntity(Player player);

//    Referee refereeEntityToDomain(RefereeEntity refereeEntity);
//    RefereeEntity refereeDomainToEntity(Referee referee);
//
//    SportsClass sportsClassEntityToDomain(SportsClassEntity sportsClassEntity);
//    SportsClassEntity sportsClassDomainToEntity(SportsClass sportsClass);

    User userEntityToDomain(UserEntity userEntity);
    UserEntity userDomainToEntity(User user);
    Person personEntityToDomain(PersonEntity personEntity);
    PersonEntity personDomainToEntity(Person person);
}
