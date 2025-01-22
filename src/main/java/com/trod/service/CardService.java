package com.trod.service;

import com.trod.dto.card.MonsterRequestDto;
import com.trod.dto.main.character.MainCharacterRequestDto;
import com.trod.entity.MainCharacter;
import com.trod.entity.Monster;
import com.trod.entity.User;
import com.trod.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardMapper cardMapper;

    public Monster createMonster(MonsterRequestDto monsterRequestDto, User user) {
        Monster monster = new Monster();
        monster.setName(monsterRequestDto.name());
        monster.setRarity(monsterRequestDto.rarityEnum());
        monster.setDescription(monsterRequestDto.description());
        monster.setCost(monsterRequestDto.cost());
        monster.setHealth(monsterRequestDto.health());
        monster.setMana(monsterRequestDto.mana());
        monster.setAttack(monsterRequestDto.attack());
        monster.setDefense(monsterRequestDto.defense());
        monster.setCreatedUser(user);
        monster.setCreatedTime(System.currentTimeMillis());
        cardMapper.insertMonsterCard(monster);
        return monster;
    }

    public MainCharacter createMainCharacter(MainCharacterRequestDto mainCharacterRequestDto, User user) {
        MainCharacter mainCharacter = new MainCharacter();
        mainCharacter.setName(mainCharacterRequestDto.name());
        mainCharacter.setRarity(mainCharacterRequestDto.rarity());
        mainCharacter.setDescription(mainCharacterRequestDto.description());
        mainCharacter.setCost(mainCharacterRequestDto.cost());
        mainCharacter.setHealth(mainCharacterRequestDto.health());
        mainCharacter.setMana(mainCharacterRequestDto.mana());
        mainCharacter.setCreatedUser(user);
        cardMapper.insertMainCharacter(mainCharacter);
        return mainCharacter;
    }
}
