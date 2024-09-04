/*
 * Copyright (C) 2022-2024. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.standard;

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

import hotstone.framework.Card;
import hotstone.framework.Player;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Game;
import hotstone.framework.Hero;


public class TestAlphaStone {
  private Game game;

  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUp() {
    game = new StandardHotStoneGame();
  }


  @Test
  public void shouldHaveFindusAsFirstPlayer() {
    // Given a game
    // When I ask for the player in turn
    Player player = game.getPlayerInTurn();
    // Then it should be Findus
    assertThat(player, is(Player.FINDUS));
  }


  @Test
  public void ShouldChangePlayerAfter1Turn() {
    // Given a game
    game.endTurn();
    // When first turn ends
    Player player = game.getPlayerInTurn();
    // Then player should be Peddersen
    assertThat(player, is(Player.PEDDERSEN));
  }

  @Test
  public void ShouldChangePlayerAfter2Turns() {
    // Given a game
    game.endTurn();
    game.endTurn();
    // When second turn ends
    Player player = game.getPlayerInTurn();
    // Then player should be Findus
    assertThat(player, is(Player.FINDUS));
  }

  @Test
  public void shouldHaveUnoDosTresCardsInitially() {
    // Given a game, Findus has 3 cards in hand
    int count = game.getHandSize(Player.FINDUS);
    assertThat(count, is(3));
    // And these are ordered Tres, Dos, Uno in slot 0,1,2

    // When I pick card 0, 1, 2.
    Card card1 = game.getCardInHand(Player.FINDUS, 0);
    Card card2 = game.getCardInHand(Player.FINDUS, 1);
    Card card3 = game.getCardInHand(Player.FINDUS, 2);

    // They should be tres, dos, uno in that order.
    assertThat(card1.getName(), is(GameConstants.TRES_CARD));
    assertThat(card2.getName(), is(GameConstants.DOS_CARD));
    assertThat(card3.getName(), is(GameConstants.UNO_CARD));
  }

  @Test
  public void GivenCardDos(){
    Card card2 = game.getCardInHand(Player.FINDUS, 1);
    // check attributes
    assertThat(card2.getManaCost(), is(2));
    assertThat(card2.getAttack(), is(2));
    assertThat(card2.getHealth(), is(2));
  }
  @Test
  public void TestIfGetHeroWorksForFindus(){
    Hero hero = game.getHero(Player.FINDUS);
    assertThat(hero.getType(), is(GameConstants.BABY_HERO_TYPE));
    assertThat(hero.getMana(), is(3));
    assertThat(hero.getHealth(), is(21));
    assertThat(hero.getOwner(), is(Player.FINDUS));
    assertThat(hero.getEffectDescription() == "Cute", is(true));
  }

  @Test
  public void TestIfGetHeroWorksForPeddersen(){
    Hero hero = game.getHero(Player.PEDDERSEN);
    assertThat(hero.getType(), is(GameConstants.BABY_HERO_TYPE));
    assertThat(hero.getMana(), is(3));
    assertThat(hero.getHealth(), is(21));
    assertThat(hero.getOwner(), is(Player.PEDDERSEN));
    assertThat(hero.getEffectDescription() == "Cute", is(true));
  }
  


  @Test
  public void ShouldReduceManaWhenPlayingCard() {
    // Given a game
    // When Findus plays Uno
    Card card1 = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, card1, 0);
    // Then Findus should have 2 mana left
    Hero hero = game.getHero(Player.FINDUS);
    assertThat(hero.getMana(), is(2));

  }

  @Test
  public void ShouldNotPlayCardWithInsufficientMana() {
    // Given a game
    // When Findus tries to play Dos and tres
    Card card1 = game.getCardInHand(Player.FINDUS, 1);
    Card card2 = game.getCardInHand(Player.FINDUS, 0);
    game.playCard(Player.FINDUS, card1, 0);
    try {
      game.playCard(Player.FINDUS, card2, 1);
      Assertions.fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      // Then an exception should be thrown
      assertThat(e.getMessage(), is("Not enough mana"));
    }
    
  }
  @Test
  public void CheckCardGetsPlayedToRightIndexOnField(){
    // Given a game
    // When Findus plays Uno at index 0
    Card card1 = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, card1, 0);
    // Then Uno should be on the field at index 0
    assertThat(game.getCardInField(Player.FINDUS, 0).getName(), is(GameConstants.UNO_CARD));
  }

  @Test
  public void TestGetFieldSizeBeforePlayingCard(){
    // Given a game
    // When Findus has not played any cards
    // Then field size should be 0
    assertThat(game.getFieldSize(Player.FINDUS), is(0));
  }


  @Test
  public void TestGetFieldSizeAfterPlayingCard(){
    // Given a game
    // When Findus plays Uno at index 0
    Card card1 = game.getCardInHand(Player.FINDUS, 2);
    game.playCard(Player.FINDUS, card1, 0);
    // Then field size should be 1
    assertThat(game.getFieldSize(Player.FINDUS), is(1));
  }

  //@Test
  public void ShouldNotPlayCardWithInvalidIndex() {
    // Given a game
    // When Findus tries to play Uno and dos at index 0 on field
    Card card1 = game.getCardInHand(Player.FINDUS, 2);
    Card card2 = game.getCardInHand(Player.FINDUS, 1);
    game.playCard(Player.FINDUS, card1, 0);
    try {
      game.playCard(Player.FINDUS, card2, 0);
      Assertions.fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      // Then an exception should be thrown
      assertThat(e.getMessage(), is("Can't play two cards in the same index"));
    }
  }

  //@Test

  @Test
  public void TurnNumberWorks(){
    assertThat(game.getTurnNumber(), is(0));
    game.endTurn();
    assertThat(game.getTurnNumber(), is(1));
    game.endTurn();
    assertThat(game.getTurnNumber(), is(2));
  }

 @Test
 public void ManaFirstRoundIs3(){
  //given a game starts
  Hero hero = new StandHero("Baby", 3, 20, Player.FINDUS);
  //when 
  assertThat(hero.getMana(), is(3));
 }
  
  //@Test
  public void FindusDeckSizeDecreases(){
    assertThat(game.getDeckSize(Player.FINDUS), is(7-3));
    game.endTurn();
    game.endTurn();
    assertThat(game.getDeckSize(Player.FINDUS), is(7-4));
  }

  //@Test
  public void CanFindusPlayCard(){
    // Given a game
    // When 
  }

  //@Test
  public void FindusPlaysUnoAndUnoAppearsOnField(){
    // Given a game
    // When Findus plays uno
    Card card1 = game.getCardInHand(Player.FINDUS, 0);
    game.playCard(Player.FINDUS, card1, 0);
    // Then Uno appears on the field at index 0
    assertThat(game.getCardInField(Player.FINDUS, 0).getName(), is(GameConstants.UNO_CARD));
  }
}
